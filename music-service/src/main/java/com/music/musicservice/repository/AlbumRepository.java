package com.music.musicservice.repository;

import com.music.musicservice.model.Album;
import com.music.musicservice.model.Status;
import com.music.musicservice.model.projection.AlbumProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AlbumRepository extends Neo4jRepository<Album,String> {

    String GET_ALBUM_WITH_SONGS_QUERY = """
    MATCH (artist:Artist {id: $artistId})-[:CREATED]->(album:Album)
    OPTIONAL MATCH (album)-[releasedYear:RELEASED_IN]->(year:Year)
    OPTIONAL MATCH (song:Song)-[:IS_IN]->(album)
    RETURN album.id as id,
           album.name as name,
           album.status as status,
           COLLECT(
               CASE WHEN song IS NOT NULL THEN {
                   id: song.id,
                   name: song.name,
                   description: song.description,
                   releasedDate: song.releasedDate,
                   storageId: song.storageId,
                   storageType: song.storageType,
                   releasedYear: year.year,
                   type: song.type,
                   status: song.status
               } END
           ) as songs
    """;

    String GET_ALBUM_SONGS_QUERY = """
MATCH (album:Album {id: $albumId})-[:IS_IN]->(song:Song)
RETURN song.id as id,
       song.name as name,
       song.description as description,
       song.releasedDate as releasedDate,
       song.storageId as storageId,
       song.storageType as storageType,
       song.type as type,
       song.status as status
""";

    @Query("MATCH (user:User {id: $userId})" +
            "            MATCH (album:Album {id: $albumId})" +
            "            MERGE (user)-[:LIKES {createdAt: $createdAt}]->(album)")
    void userLikesAlbum(@Param("userId")String userId,
                        @Param("albumId") String albumId,
                        @Param("createdAt") LocalDateTime createdAt);


    @Query("MATCH (user:User {id: $userId}) -[relationship:LIKES]-> (album:Album {id: $albumId})" +
            " DELETE relationship")
    void userDislikeAlbum(@Param("userId")String userId,
                          @Param("albumId") String albumId);


    @Query("MATCH (artist:Artist {id: $artistId}), (album:Album {id: $albumId}), (year:Year {year: $year})" +
            " MERGE (album)-[:RELEASED_IN {createdAt: $createdAt}]->(year)" +
            " MERGE (artist)-[:CREATED {createdAt: $createdAt}]->(album)")
    void addReleasedYearAndArtist(@Param("artistId")String artistId,
                                  @Param("albumId") String albumId,
                                  @Param("year") Integer year,
                                  @Param("createdAt") LocalDateTime createdAt);
    @Query("""
    MATCH(album: Album {id: $userId})
    SET album.description = $description
    SET album.name = $name
    SET album.releasedDate = $releasedDate
    RETURN album""")
    Album updateAlbum(
            @Param("albumId") String albumId,
            @Param("description") String description,
            @Param("name") String name,
            @Param("releasedDate") LocalDateTime releaseDate
    );

    @Query("MATCH (song:Song {id: $songId}), (IS_IN:Album {id: $albumId})" +
            " MERGE (song)-[:IS_IN {createdAt: $createdAt}]->(IS_IN)")
    void addSongToAlbum(@Param("songId") String songId,
                        @Param("albumId") String albumId,
                        @Param("createdAt")LocalDateTime createdAt);
    @Query("MATCH (album:Album {id: $albumId})" +
            " RETURN album.id as id, album.name as name, album.description as description, album.status as status")
    Optional<AlbumProjection> findByIdProjection(@Param("userId") String id);

    @Query("""
    MATCH (album:Album {id: $albumId}), (year:Year {year: $year})
    MERGE (album)-[relationship:RELEASED_IN]->(year)
    ON CREATE SET 
        relationship.createdAt = $createdAtOrUpdatedAt,
        relationship.updatedAt = $createdAtOrUpdatedAt
    ON MATCH SET 
        relationship.updatedAt = $createdAtOrUpdatedAt """)
    void updateReleasedYear(
            @Param("albumId") String albumId,
            @Param("year") Integer year,
            @Param("createdAtOrUpdatedAt") LocalDateTime createdAtOrUpdatedAt
    );

    @Query("MATCH (album:Album {id: $albumId})-[relationship:RELEASED_IN]->(year:Year)" +
            " DELETE relationship")
    void removeAlbumReleaseYear(
            @Param("albumId") String albumId,
            @Param("year") Integer year
    );

    @Query("MATCH (album:Album {id: $albumId}) SET album.status = $status RETURN album")
    Album updateAlbumStatus(@Param("albumId") String albumId, @Param("status") Status status);

}
