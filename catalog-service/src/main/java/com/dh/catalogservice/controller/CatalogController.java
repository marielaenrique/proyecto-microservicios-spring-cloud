package com.dh.catalogservice.controller;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.MovieListener;
import com.dh.catalogservice.queue.SerieListener;
import com.dh.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
    private final MovieListener movieListener;
    private final SerieListener serieListener;

    @GetMapping("/catalog/movie/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre){
        return catalogService.getMovieByGenre(genre);
    }

    @PostMapping("catalog/movie/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        catalogService.saveMovie(movie);
        movieListener.receive(movie);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/catalog/serie/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) { return catalogService.getSerieByGenre(genre); }

    @PostMapping("/catalog/serie/save")
    public String create(@RequestBody Serie serie) { return catalogService.create(serie); }


    @PostMapping("/serie/save")
    public ResponseEntity<Serie> saveSerieMongo(@RequestBody Serie serie) {
        serieListener.receive(serie);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/movie/{genre}")
    public List<Movie> getMovieByGenreMongo(@PathVariable String genre){
        return catalogService.findMovieByGenreMongo(genre);
    }

    @GetMapping("/serie/{genre}")
    public List<Serie> getSerieByGenreMongo(@PathVariable String genre){
        return catalogService.findSerieByGenreMongo(genre);
    }


}
