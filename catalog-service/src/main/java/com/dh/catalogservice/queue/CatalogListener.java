package com.dh.catalogservice.queue;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CatalogListener {

    @Autowired
    private  CatalogService catalogService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogListener.class);

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receive(@Payload Movie moviee) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("Received message -> %s", moviee));
        catalogService.guardarMovie(moviee);

    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie seriee) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("Received message -> %s", seriee));
        catalogService.guardarSerie(seriee);

    }


}
