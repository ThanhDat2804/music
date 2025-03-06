package com.music.musicservice.repository;

import com.music.musicservice.model.Artist;
import com.music.musicservice.model.projection.ArtistProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ArtistRepository extends Neo4jRepository<Artist,String> {

    @Query("MATCH (artist: Artist {id: $artistId}) RETURN artist.id As id, artist.name as name")
    Optional<ArtistProjection>  findByIdProjection(@Param("artistId")String id);

    @Query("MATCH (artist: Artist {id: $artistId}), (year: Year {year: $releasedYear}), (song: Song {id: $songId}) , (genre: Genre {id: $genreId})" +
            " MERGE(artist)-[:CREATED {createdAt: $createdAt}]->(song)" +
            " MERGE(artist)-[:CREATED {createdAt: $createdAt}]->(song)" +
            " MERGE(song)-[:BELONGS_TO_GENRE {createdAt: $createdAt}]->(genre)" +
            " MERGE(song)-[:RELEASED_IN {createdAt: $createdAt}]->(year)")
    void addArtistAndYearRelationship(@Param("artistId")String artistId, @Param("releasedYear") Integer releasedYear, @Param("songId") String songId,
                                      @Param("createdAt") LocalDateTime localDateTime, @Param("genreId") String genreId);
}
