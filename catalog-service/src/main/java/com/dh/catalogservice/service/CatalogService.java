package com.dh.catalogservice.service;

import com.dh.catalogservice.feign.IMovieClient;
import com.dh.catalogservice.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final IMovieClient iMovieClient;

    public CatalogService(IMovieClient iMovieClient) {
        this.iMovieClient = iMovieClient;
    }

    public ResponseEntity<List<Movie>> getMovieByGenre(String genre){
        return iMovieClient.getMovieByGenre(genre);
    }

    public ResponseEntity<Movie> saveMovie(Movie movie){
        return iMovieClient.saveMovie(movie);
    }


}
