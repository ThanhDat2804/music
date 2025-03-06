package com.music.musicservice.repository;

import com.music.musicservice.model.User;
import com.music.musicservice.model.projection.UserProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User,String> {


    @Query("MATCH (user:User {id: $userId}) RETURN user.id As id, user.name As name, user.dob As dob, user.gender As gender, user.language As language, user.countryIso2 As countryIso2")
    Optional<UserProjection> findByIdProjection(@Param("userId") String id);

    @Query("MATCH (artist:Artist {id: $artistId})" +
            "            MATCH (user:User {id: $userId})" +
            "            MERGE (user)-[:FOLLOWS {createdAt: $createdAt}]->(artist)")
    void userFollowArtist(@Param("userId")String userId,
                          @Param("artistId") String artistId,
                          @Param("createdAt") LocalDateTime createdAt);

    @Query("MATCH (artist:Artist {id: $artistId}) , (user:User {id: $userId})" +
            " MERGE (user)-[:IS_AN {createdAt: $createdAt}]->(artist)")
    void addArtistAndUserRelationship(@Param("userId")String userId,
                                      @Param("artistId") String artistId,
                                      @Param("createdAt") LocalDateTime createdAt);

    @Query("MATCH(u:User {id: $userId})-[relation:FOLLOWS]->(a:Artist {id:$artistId})" +
            " DELETE relation")
    void userUnFollowArtist(@Param("userId")String userId,
                            @Param("artistId") String artistId);

    @Query("MATCH(user: User {id: $userId})\n" +
            "SET user.gender = $gender\n" +
            "SET user.dob = $dob\n" +
            "SET user.language = $language\n" +
            "SET user.name = $name\n" +
            "SET user.countryIso2 = $countryIso2\n" +
            "RETURN user")
    User updateUser(
            @Param("userId") String userId,
            @Param("gender") String gender,
            @Param("dob") LocalDate dob,
            @Param("language") String language,
            @Param("name") String name,
            @Param("countryIso2") String countryIso2
    );
    @Query("""
    MATCH (user:User {id:$userId}), (song:Song {id:$songId})
    MATCH (song)<-[:CREATED]-(artist:Artist)
    MATCH (user)-[relationship:FOLLOWS]->(artist)
    return count(relationship) >= 1
    """)
    boolean isUserFollowsTheArtist(@Param("userId") String userId, @Param("songId") String songId);

    @Query("MATCH (song:Song {id: $songId})" +
            " MATCH (user:User {id: $userId})" +
            " MATCH (song)<-[:CREATED]-(artist:Artist)" +
            " MERGE (user)-[:INTERESTED]->(artist)")
    void userInterestedWithArtistBySongId(@Param("userId") String userId,
                                          @Param("songId") String songId);
    @Query("""
    MATCH (user:User {id:$userId}), (song:Song {id:$songId}) 
    MATCH (song)<-[:CREATED]-(artist:Artist) 
    MATCH (user)-[relationship:INTERESTED]->(artist) 
    RETURN count(relationship) >= 1
    """)
    boolean isUserInterestedWithTheArtist(@Param("userId") String userId, @Param("songId") String songId);

}
