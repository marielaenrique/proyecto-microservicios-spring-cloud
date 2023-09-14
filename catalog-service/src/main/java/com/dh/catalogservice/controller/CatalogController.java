package com.dh.catalogservice.controller;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.MovieListener;
import com.dh.catalogservice.queue.SerieListener;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.configuration.CircuitBreakerConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
    private final MovieListener movieListener;
    private final SerieListener serieListener;
    private final CircuitBreakerConfiguration circuitBreakerConfiguration;


    @GetMapping("/movie/{genre}")
    public List<Movie> getMovieByGenreMongo(@PathVariable String genre){
        return catalogService.findMovieByGenreMongo(genre);
    }

    @PostMapping("/movie/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        catalogService.saveMovie(movie);
        movieListener.receive(movie);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/serie/{genre}")
    public List<Serie> getSerieByGenreMongo(@PathVariable String genre){
        return catalogService.findSerieByGenreMongo(genre);
    }

    @PostMapping("/serie/save")
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        serieListener.receive(serie);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genre/{genre}")
    public Genre getGenre(@PathVariable String genre) {
        return catalogService.getGenre(genre);
    }

    @GetMapping("/movie-service/findAll")
    public List<Movie> findAll() {
        return circuitBreakerConfiguration.findAll();
    }

}