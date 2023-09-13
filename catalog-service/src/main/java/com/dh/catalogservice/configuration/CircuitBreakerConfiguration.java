package com.dh.catalogservice.configuration;

import com.dh.catalogservice.feign.IMovieClient;
import com.dh.catalogservice.model.Movie;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class CircuitBreakerConfiguration {

    private IMovieClient iMovieClient;

    @Autowired
    public CircuitBreakerConfiguration(IMovieClient iMovieClient) {
        this.iMovieClient = iMovieClient;
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "findAllEmpty")
    @Retry(name = "movies")
    public List<Movie> findAll() {
        log.info("Calling movie service...");
        return iMovieClient.findAll(true);
    }

    private List<Movie> findAllEmpty(CallNotPermittedException exception) {
        return new ArrayList<>();
    }

}
