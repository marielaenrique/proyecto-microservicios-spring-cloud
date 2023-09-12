package com.dh.catalogservice.service;

import com.dh.catalogservice.feign.IMovieClient;
import com.dh.catalogservice.feign.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.IGenreRepository;
import com.dh.catalogservice.repository.IMovieRepository;
import com.dh.catalogservice.repository.ISerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final IMovieClient iMovieClient;

    private final ISerieClient iSerieClient;

    private final IMovieRepository iMovieRepository;

    private final ISerieRepository iSerieRepository;

    private final IGenreRepository iGenreRepository;

    public ResponseEntity<Movie> saveMovie(Movie movie){
        return iMovieClient.saveMovie(movie);
    }

    public String create(Serie serie) { return iSerieClient.create(serie); }

    public Movie saveMovieMongo(Movie movie) {
        return iMovieRepository.save(movie);
    }

    public Serie saveSerieMongo(Serie serie) {
        return iSerieRepository.save(serie);
    }

    public List<Movie> findMovieByGenreMongo(String genre) {
        return iMovieRepository.findByGenre(genre);
    }

    public List<Serie> findSerieByGenreMongo(String genre) {
        return iSerieRepository.findByGenre(genre);
    }

    public List<Object> getGenreItems(String genre) {
        List<Movie> movies = iMovieRepository.findByGenre(genre);
        List<Serie> series = iSerieRepository.findByGenre(genre);
        List<Object> genreItems = new ArrayList<>();
        genreItems.addAll(movies);
        genreItems.addAll(series);

        return genreItems;
    }
}
