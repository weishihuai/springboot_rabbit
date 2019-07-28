package com.wsh.springboot.springboot_rabbotmq_topic_exchange.config;

import com.wsh.springboot.springboot_rabbotmq_topic_exchange.constant.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ配置类
 * @author: weishihuai
 * @Date: 2019/7/28 10:34
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue(Constants.QUEUE_NAME);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(Constants.EXCHANGE_NAME);
    }

    @Bean
    public Binding bindQueue() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(Constants.ROUTE_KEY);
    }

}
