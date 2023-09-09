package com.dh.movieservice.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitSenderConfiguration {

    @Value("${queue.movie.name}")
    private String movieQueue;

    @Bean
    public Queue movieQueue() {
        return new Queue(this.movieQueue, true);
    }


}