package com.music.musicservice.proxy;

import com.music.musicservice.dto.BunnyNetVideoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "bunnynet-service", url = "https://video.bunnycdn.com/library/")
public interface BunnynetProxy {

    @PostMapping("{libraryId}/videos/{videoId}")
    BunnyNetVideoDTO getVideo(@PathVariable Long libraryId,
                              @PathVariable String videoId,
                              @RequestHeader("AccessKey") String accessKey);
}
