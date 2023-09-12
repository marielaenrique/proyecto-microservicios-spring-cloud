package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IGenreRepository extends MongoRepository <Genre, Long> {
    Genre findByGenre(String genre);

}
