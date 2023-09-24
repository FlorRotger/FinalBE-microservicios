package com.dh.movieservice.queue;

import com.dh.movieservice.model.Movie;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MovieSender {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    private final String routingKey = "quick.orange.rabbit";

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieSender.class);


    public void sendMsg(Movie movie) {
        LOGGER.info(String.format("Json message sent to only match routing key-> %s", movie.toString()));
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, movie);

    }
}
