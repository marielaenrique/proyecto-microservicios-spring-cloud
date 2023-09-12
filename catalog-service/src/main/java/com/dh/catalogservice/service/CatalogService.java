package com.dh.catalogservice.service;

import com.dh.catalogservice.feign.IMovieClient;
import com.dh.catalogservice.feign.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.IMovieRepository;
import com.dh.catalogservice.repository.ISerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final IMovieClient iMovieClient;

    private final ISerieClient iSerieClient;

    private final IMovieRepository iMovieRepository;

    private final ISerieRepository iSerieRepository;;

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

    public Genre getGenre(String genre) {
        List<Movie> movies = iMovieRepository.findByGenre(genre);
        List<Serie> series = iSerieRepository.findByGenre(genre);
        Genre newGenre = new Genre();
        newGenre.setMovies(movies);
        newGenre.setSeries(series);

        return newGenre;
    }
}
