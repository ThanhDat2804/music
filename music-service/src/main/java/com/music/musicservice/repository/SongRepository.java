package com.music.musicservice.repository;

import com.music.musicservice.model.Song;
import com.music.musicservice.model.SongType;
import com.music.musicservice.model.Status;
import com.music.musicservice.model.projection.SongProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SongRepository extends Neo4jRepository<Song, String> {

    String GET_ARTIST_SONGS = "MATCH (artist:Artist {id: $artistId})-[:CREATED]->(song:Song)\n" +
            "OPTIONAL MATCH (song)-[releasedYear:RELEASED_IN]->(year:Year)\n" +
            "return \n" +
            "    artist.id as artistId,\n" +
            "    song.id as id,\n" +
            "    song.name as name,\n" +
            "    song.description as description,\n" +
            "    song.status as status,\n" +
            "    song.duration as duration,\n" +
            "    song.storageId as storageId,\n" +
            "    song.storageType as storageType,\n" +
            "    song.type as type,\n" +
            "    year.year as year\n" +
            "SKIP $page\n" +
            "LIMIT $pageSize;";

    @Query("MATCH (song:Song {id: $songId}) " +
            "RETURN song.id AS id, " +
            "song.name AS name, " +
            "song.description AS description, " +
            "song.status AS status, " +
            "song.duration AS duration, " +
            "song.storageId AS storageId, " +
            "song.storageType AS storageType, " +
            "song.type AS type, " +
            "song.year AS year")
    Optional<SongProjection> findByProjection(@Param("songId") String id);



    @Query("MATCH (song:Song {id: $songId}), (user: User {id: $userId}) " +
            "MERGE (user)-[:LIKES {createdAt: $createdAt}]->(song)")
    void userLikeASong(@Param("songId") String songId, @Param("userId") String userId, @Param("createdAt") LocalDateTime createdAt);

    @Query("MATCH (song:Song {id: $songId})<-[relationship:LIKES]- (user: User {id: $userId}) " +
            "DELETE relationship")
    void userDisLikeASong(@Param("songId") String songId, @Param("userId") String userId);

    @Query("MATCH (song:Song {id: $songId}), (user: User {id: $userId})" +
            " MERGE (user)-[relationship:PLAYS]->(song) " +
            " ON CREATE SET " +
            " relationship.createdAt = $createdAtOrUpdatedAt, " +
            " relationship.updatedAt = $createdAtOrUpdatedAt, " +
            " relationship.counter = $counter " +
            " ON MATCH SET " +
            " relationship.updatedAt = $createdAtOrUpdatedAt, " +
            " relationship.counter = relationship.counter + $counter")
    void userPlaysASong(@Param("songId") String songId,
                       @Param("userId") String userId,
                       @Param("createdAtOrUpdatedAt") LocalDateTime createdAtOrUpdatedAt,
                       @Param("counter") Integer counter);

    @Query("MATCH (song: Song {id: $songId}), (year: Year {year: $releasedYear}) " +
            "MERGE (song)-[relationship:RELEASED_IN]->(year) " +
            "SET song.name = $name " +
            "SET song.description = $description " +
            "SET song.releaseDate = $releasedDate " +
            "SET song.type = $type " +
            "RETURN song")
    Song updateSong(@Param("songId") String songId,
                    @Param("description") String description,
                    @Param("name") String name,
                    @Param("releasedDate") LocalDateTime releasedDate,
                    @Param("type") SongType type,
                    @Param("releasedYear") Integer releasedYear
    );

    @Query("MATCH (song:Song {id: $songId})-[relationship:RELEASED_IN]->(year:Year) " +
            "DELETE relationship")
    void removeSongReleasedYear(@Param("songId") String songId,
                                @Param("year") Integer year);

    @Query("MATCH (song:Song {id: $songId}) " +
            "SET song.status = $status " +
            "RETURN song")
    Song updateSongStatus(@Param("songId") String songId, @Param("status") Status status);

    @Query("""
    MATCH(song: Song {id: $songId})
    SET song.status = $status
    SET song.taskSchedulerId = $taskSchedulerId
    RETURN song
""")
    Song updateScheduledSongStatus(
            @Param("songId") String songId,
            @Param("status") Status status,
            @Param("taskSchedulerId") String taskSchedulerId
    );

    Status status(Status status);
}
