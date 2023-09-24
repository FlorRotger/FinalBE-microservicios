package com.dh.catalogservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Genre {

    public Genre(List<Movie> movies, List<Serie> series) {
        this.movies = movies;
        this.series = series;
    }

    private List<Movie> movies;
    private List<Serie> series;

}
