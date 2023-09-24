package com.dh.catalogservice.controller;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.CatalogListener;
import com.dh.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogListener catalogListener;

    @Autowired
    private IMovieClient iMovieClient;

    @Autowired
    private ISerieClient iSerieClient;

    @Autowired
    private CatalogService catalogService;

    private static Logger log = Logger.getLogger(CatalogController.class.getName());

    @GetMapping("/catalog/movie/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre){
        log.info("Pelicula de  " + genre);
        return iMovieClient.getMovieByGenre(genre);
    }

    @GetMapping("/catalog/serie/{genre}")
    public ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre){
        log.info("Serie de " + genre);
        return (ResponseEntity<List<Serie>>) iSerieClient.getSerieByGenre(genre);
    }

    @PostMapping("/catalog/movie/guardar")
    public ResponseEntity<Movie> guardarMovie(@RequestBody Movie moviee) {
        log.info("Pelicula guardada");
        catalogListener.receive(moviee);
        return iMovieClient.saveMovieToCatalog(moviee);
    }

    @PostMapping("/catalog/serie/guardar")
    public String guardarSerie(@RequestBody Serie seriee) {
        log.info("Serie guardada");
        catalogListener.receive(seriee);
        return iSerieClient.createSerie(seriee);
    }

    @GetMapping("/catalog/{genre}")
    public Genre getAllByGenre(@RequestParam(defaultValue = "false") Boolean throwError, HttpServletResponse response, @PathVariable String genre) {
        log.info("Peliculas y series de " + genre);
        return catalogService.getAllByGenre(genre, throwError);
    }


}
