package com.music.musicservice.repository;

import com.music.musicservice.model.Playlist;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PlaylistRepository extends Neo4jRepository<Playlist,String> {

    String GET_USER_PLAYLISTS_QUERY = "MATCH (user:User {id: $userId})-[:CREATED]->(playlist:Playlist)\n" +
            " OPTIONAL MATCH (playlist)-[:CONTAINS]->(song)\n" +
            " OPTIONAL MATCH (song)-[:RELEASED_IN]->(year:Year)\n" +
            " return playlist.id as id,\n" +
            " playlist.title as title,\n" +
            " COLLECT(CASE WHEN song IS NOT NULL THEN {\n" +
            "   id: song.id,\n" +
            "   name: song.name,\n" +
            "   description: song.description,\n" +
            "   releasedDate: song.releasedDate,\n" +
            "   storageId: song.storageId,\n" +
            "   storageType: song.storageType,\n" +
            "   releasedYear: year.year,\n" +
            "   type: song.type,\n" +
            "   status: song.status\n" +
            " } END) as songs";

    @Query("MATCH (user: User {id: $userId}), (playlist: Playlist {id: $playlistId})" +
            " MERGE (user)-[:CREATED {createdAt: $createdAt}]->(playlist)")
    void addPlaylistAndUserRelationship(@Param("playlistId")String playlistId,
                                        @Param("userId")String userId,
                                        @Param("createdAt") LocalDateTime createdAt);


    @Query("MATCH (song: Song {id: $songId}), (playlist: Playlist {id: $playlistId})" +
            " MERGE (playlist)-[:CONTAINS {createdAt: $createdAt}]->(song)")
    void addSongToPlaylist(@Param("playlistId")String playlistId,
                           @Param("songId")String songId,
                           @Param("createdAt") LocalDateTime createdAt);
    @Query("MATCH (song: Song {id: $songId}) <-[relationship:CONTAINS]- (playlist: Playlist {id: $playlistId})" +
            " DELETE relationship")
    void removeSongFromPlaylist(@Param("playlistId")String playlistId,
                                @Param("songId")String songId);
    @Query("""
    MATCH (playlist: Playlist {id: $playlistId})
    SET playlist.description = $description
    SET playlist.title = $title
    RETURN playlist
""")
    Playlist updatePlaylist(
            @Param("playlistId") String playlistId,
            @Param("description") String description,
            @Param("title") String title
    );

}
