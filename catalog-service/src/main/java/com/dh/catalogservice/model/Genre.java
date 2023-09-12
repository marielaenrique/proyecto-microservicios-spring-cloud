package com.dh.catalogservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Genre {
    @MongoId
    private Long id;
    private List <Movie> movies;
    private List <Serie> series;

}
