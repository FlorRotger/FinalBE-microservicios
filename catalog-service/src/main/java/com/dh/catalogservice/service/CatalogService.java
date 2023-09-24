package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repositories.MovieRepository;
import com.dh.catalogservice.repositories.SerieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CatalogService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SerieRepository serieRepository;

    public void guardarMovie(Movie moviee){
        movieRepository.save(moviee);
    }

    public void guardarSerie(Serie seriee){
        serieRepository.save(seriee);
    }






    @CircuitBreaker(name = "catalog", fallbackMethod = "findAllEmptyFallback")
    @Retry(name = "catalog")
       public Genre getAllByGenre(String genre, Boolean throwError) {
        List<Serie> listSerie = serieRepository.findAllByGenre(genre);
        List<Movie> listMovie = movieRepository.findAllByGenre(genre);
        if (throwError) {
            throw new RuntimeException();
        }
        return new Genre(listMovie, listSerie);

    }

    public Genre findAllEmptyFallback(String genre, Boolean throwError) {
        List<Movie> listMovie = new ArrayList<>();
        List<Serie> listSerie = new ArrayList<>();
        return new Genre(listMovie, listSerie);
    }


}
