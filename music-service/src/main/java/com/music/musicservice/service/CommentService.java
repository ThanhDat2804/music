package com.music.musicservice.service;

import com.music.musicservice.dto.CommentDto;
import com.music.musicservice.dto.SongCommentProjectionDto;
import com.music.musicservice.model.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment, String userId, String songId);

    Comment update(String id, CommentDto comment );

    List<SongCommentProjectionDto> getSongComments(String songId, Integer page, Integer pageSize );

    void deleteById(String id);

}
