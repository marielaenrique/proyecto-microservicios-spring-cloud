package com.dh.catalogservice.controller;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/catalog/movie/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre){
        return catalogService.getMovieByGenre(genre);
    }
    @PostMapping("/catalog/movie/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        return catalogService.saveMovie(movie);
    }

    @GetMapping("/catalog/serie/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) { return catalogService.getSerieByGenre(genre); }

    @PostMapping("/catalog/serie/save")
    public String create(@RequestBody Serie serie) { return catalogService.create(serie); }






}
