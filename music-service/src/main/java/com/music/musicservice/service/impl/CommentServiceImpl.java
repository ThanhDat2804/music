package com.music.musicservice.service.impl;

import com.music.musicservice.dto.CommentDto;
import com.music.musicservice.dto.SongCommentProjectionDto;
import com.music.musicservice.model.Comment;
import com.music.musicservice.model.Sentiment;
import com.music.musicservice.repository.CommentRepository;
import com.music.musicservice.service.CommentService;
import com.music.musicservice.service.SentimentAnalyzer;
import com.music.musicservice.service.UserService;
import com.music.musicservice.utils.JsonUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.music.musicservice.repository.CommentRepository.GET_SONG_COMMENTS_QUERY;


@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final Neo4jClient neo4jClient;
    private final SentimentAnalyzer sentimentAnalyzer;
    private final UserService userService;

    @Override
    public Comment create(Comment comment, String userId, String songId) {

        Comment comment1 = Comment.builder()
                .text(comment.getText())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .edited(false).build();

        Comment saved = commentRepository.save(comment1);
        commentRepository.addCommentRelationship(saved.getId(),songId,userId);

        analyzeComment(saved, userId, songId );

        return saved;
    }

    private void analyzeComment(Comment saved, String userId, String songId) {
        try {
            boolean userInterestedWithTheArtist = userService.isUserInterestWithTheArtist(userId, songId);

            if(userInterestedWithTheArtist) {
                Sentiment sentiment = sentimentAnalyzer.analyzeSentimentOf(saved.getText());

                if(Objects.equals(sentiment, Sentiment.POSITIVE) || Objects.equals(sentiment, Sentiment.NEUTRAL)) {
                    userService.userInterestWithArtistBySongId(userId, songId);
                } else {
                    System.out.println("User has interest " + userId);
                }
            } else {
                System.out.println("User follows artist >>>> user=" + userId);
            }
        } catch (Exception e) {
            log.error("CommentServiceImpl analyzeComment", e);
        }
    }

    @Override
    public Comment update(String id, CommentDto comment) {

        return commentRepository.updateComment(id, comment.getText(), true, LocalDateTime.now());
    }

    @Override
    public List<SongCommentProjectionDto> getSongComments(String songId, Integer page, Integer pageSize) {
        return neo4jClient.query(GET_SONG_COMMENTS_QUERY)
                .bind(songId).to("songId")
                .bind(page).to("page")
                .bind(pageSize).to("pageSize")
                .fetch()
                .all()
                .stream()
                .map(map -> JsonUtility.fromMap(map, SongCommentProjectionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
