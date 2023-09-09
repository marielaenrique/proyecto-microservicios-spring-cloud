package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends MongoRepository<Movie, Long> {


}
