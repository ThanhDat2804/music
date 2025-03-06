package com.music.userservice.api;

import com.music.userservice.dto.UserDto;
import com.music.userservice.mapper.DtoMapper;
import com.music.userservice.model.StorageProvider;
import com.music.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserApi {


    private final UserService userService;
    private final DtoMapper mapper;

    @PostMapping("/users")
    public ResponseEntity<UserDto>  saveUser(@RequestBody UserDto user){

        userService.save(mapper.map(user));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto>  getUserById(@PathVariable String id){

        return ResponseEntity.ok(mapper.map(userService.getById(id)));
    }
    @DeleteMapping("/users/{id}")
    @ResponseBody
    public void  deleteUserById(@PathVariable String id){

        userService.deleteUserById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto>  updateUser(@PathVariable String id,@RequestBody UserDto user){

        userService.update(mapper.map(user),id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/users/{id}/upload-profile-picture",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto>  uploadProfileImage(@RequestParam(value = "file") MultipartFile file, @PathVariable String id
            , @RequestParam String key, @RequestParam StorageProvider storageProvider) throws Exception {

        userService.uploadProfilePicture(id,key,file,storageProvider);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
