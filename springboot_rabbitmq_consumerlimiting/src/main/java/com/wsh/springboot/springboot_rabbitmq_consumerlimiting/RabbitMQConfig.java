package com.wsh.springboot.springboot_rabbitmq_consumerlimiting;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description: Rabbitmq 配置类
 * @Author: weixiaohuai
 * @Date: 2019/7/10
 * @Time: 20:43
 */
@Component
public class RabbitMQConfig {
    private static final String QUEUE_NAME = "consumer_limit_queue_name";
    private static final String EXCHANGE_NAME = "consumer_limit_exchange_name";
    private static final String ROUTING_KEY = "user.#";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

}