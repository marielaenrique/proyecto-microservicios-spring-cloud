package com.dh.movieservice.controller;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        List<Movie> movies = movieService.findByGenre(genre);

        int actualPort = request.getServerPort();

        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
        responseBuilder.header("Custom-Port", String.valueOf(actualPort));

        return responseBuilder.body(movies);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveMovie(@RequestBody Movie movie) {
        movieService.save(movie);
        return movie.getName();
    }

    @GetMapping("/findAll")
    public List<Movie> findAll(@RequestParam(defaultValue = "false") Boolean throwError) {
        return movieService.findAll(throwError);
    }
}
