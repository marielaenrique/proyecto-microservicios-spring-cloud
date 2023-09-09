package com.dh.catalogservice.feign;

import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "serie-service")
public interface ISerieClient {

    @GetMapping("/api/v1/series/{genre}")
    List<Serie> getSerieByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/series")
    String create(@RequestBody Serie serie);


}
