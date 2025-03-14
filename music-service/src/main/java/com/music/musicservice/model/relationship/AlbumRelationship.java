package com.music.musicservice.model.relationship;

import com.music.musicservice.model.Album;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;

@RelationshipProperties
@Getter
@Setter
public class AlbumRelationship {

    @Id
    @GeneratedValue
    private Long id;
    @TargetNode
    private Album album;
    private LocalDateTime createdAt;
}
