package com.music.musicservice.repository;

import com.music.musicservice.model.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CommentRepository extends Neo4jRepository<Comment,String> {


    String GET_SONG_COMMENTS_QUERY = """
    MATCH (song:Song {id: $songId})<-[HAS_COMMENT]-(comment:Comment)<-[:POSTED_COMMENT]-(user:User)
    RETURN song.id as songId, comment.text as text, comment.edited as edited, 
           comment.createdAt as createdAt, user.id as userId, user.name as name
    SKIP $page
    LIMIT $pageSize """;


    @Query("MATCH (user:User {id: $userId})" +
            "            MATCH (comment:Comment {id: $commentId})" +
            "            MATCH (song:Song {id: $songId})" +
            "            MERGE (user)-[:POSTED_COMMENT]->(comment)" +
            "            MERGE (comment)-[:HAS_COMMENT]->(song)")
    void addCommentRelationship(@Param("commentId")String commentId,
                                @Param("songId")String songId,
                                @Param("userId")String userId);


    @Query("""
    MATCH (comment: Comment {id: $commentId})
    SET comment.text = $text
    SET comment.edited = $edited
    SET comment.updatedAt = $updatedAt
    RETURN comment""")
    Comment updateComment(@Param("commentId") String songId,
                          @Param("text") String text,
                          @Param("edited") Boolean edited,
                          @Param("updatedAt") LocalDateTime updatedAt);

}
