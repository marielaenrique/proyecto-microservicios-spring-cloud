package com.dh.catalogservice.service;

import com.dh.catalogservice.feign.IMovieClient;
import com.dh.catalogservice.feign.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CatalogService {

    private final IMovieClient iMovieClient;

    private final ISerieClient iSerieClient;

    public CatalogService(IMovieClient iMovieClient, ISerieClient iSerieClient) {
        this.iMovieClient = iMovieClient;
        this.iSerieClient = iSerieClient;
    }

    public ResponseEntity<List<Movie>> getMovieByGenre(String genre){
        return iMovieClient.getMovieByGenre(genre);
    }

    public ResponseEntity<Movie> saveMovie(Movie movie){
        return iMovieClient.saveMovie(movie);
    }


    public List<Serie> getSerieByGenre(String genre) { return iSerieClient.getSerieByGenre(genre); }

    public String create(Serie serie) { return iSerieClient.create(serie); }

}
