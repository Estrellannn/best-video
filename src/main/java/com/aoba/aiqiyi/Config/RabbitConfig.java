package com.aoba.aiqiyi.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitConfig {

    public static final String VIDEO_EXCHANGE = "video_exchange";

    public static final String VIDEO_QUEUE = "video_process_queue";

    public static final String VIDEO_ROUTING_KEY = "video.upload";

    @Bean
    public DirectExchange videoExchange(){
        return ExchangeBuilder.directExchange(VIDEO_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue videoQueue(){
        return QueueBuilder.durable(VIDEO_QUEUE).build();
    }

    @Bean
    public Binding videoBinding(Queue videoQueue, DirectExchange videoExchange){
        return BindingBuilder.bind(videoQueue).to(videoExchange).with(VIDEO_ROUTING_KEY);
    }
}
