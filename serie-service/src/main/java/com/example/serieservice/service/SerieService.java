package com.example.serieservice.service;

import com.example.serieservice.model.Serie;
import com.example.serieservice.queue.SerieSender;
import com.example.serieservice.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vaninagodoy
 */

@Service
public class SerieService {

    private final SerieRepository repository;

    private final SerieSender sender;
    public SerieService(SerieRepository repository, SerieSender sender) {
        this.repository = repository;
        this.sender = sender;
    }

    public List<Serie> getAll() {
        return repository.findAll();
    }

    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public String create(Serie serie) {
        repository.save(serie);
        sender.send(serie);
        return serie.getId();
    }
}
