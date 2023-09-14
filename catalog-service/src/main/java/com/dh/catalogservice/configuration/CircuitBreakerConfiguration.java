package com.dh.catalogservice.configuration;

import com.dh.catalogservice.feign.IMovieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.repository.IMovieRepository;
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
    private IMovieRepository iMovieRepository;

    @Autowired
    public CircuitBreakerConfiguration(IMovieClient iMovieClient, IMovieRepository iMovieRepository) {
        this.iMovieClient = iMovieClient;
        this.iMovieRepository = iMovieRepository;
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "findAllCatalog")
    @Retry(name = "movies")
    public List<Movie> findAll() {
        log.info("Calling movie service...");
        return iMovieClient.findAll(false);
    }

    private List<Movie> findAllCatalog(CallNotPermittedException exception) {
        List<Movie> list = iMovieRepository.findAll();
        return list;
    }

}