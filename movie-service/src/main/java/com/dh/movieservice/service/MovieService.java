package com.dh.movieservice.service;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.queue.MovieSender;
import com.dh.movieservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vaninagodoy
 */

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieSender movieSender;

    public MovieService(MovieRepository movieRepository, MovieSender movieSender) {
        this.movieRepository = movieRepository;
        this.movieSender = movieSender;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        movieSender.send(movie);
        return movieRepository.save(movie);
    }
}
