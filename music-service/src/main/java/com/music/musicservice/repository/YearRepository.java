package com.music.musicservice.repository;

import com.music.musicservice.model.Year;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface YearRepository extends Neo4jRepository<Year,String> {

    Optional<Year>  findByYear(Integer year);
}
