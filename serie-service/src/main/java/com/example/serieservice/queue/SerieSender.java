package com.example.serieservice.queue;

import com.example.serieservice.model.Serie;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class SerieSender {

    private final RabbitTemplate rabbitTemplate;

    private final Queue serieSender;

    public void sendMsg(Serie serie){
        this.rabbitTemplate.convertAndSend(this.serieSender.getName(), serie);
    }

}
