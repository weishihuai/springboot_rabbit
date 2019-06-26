package com.wsh.springboot.springboot_rabbotmq_topic_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ配置类
 * @author: weishihuai
 * @Date: 2019/6/26 10:34
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue01() {
        //创建名称为fanout_exchange_queue01的队列
        return new Queue("topic_exchange_queue01");
    }

    @Bean
    public Queue queue02() {
        //创建名称为topic_exchange_queue02的队列
        return new Queue("topic_exchange_queue02");
    }

    @Bean
    public TopicExchange topicExchange() {
        //创建名称为topic.exchange的广播式交换机
        return new TopicExchange("topic.exchange");
    }

    @Bean
    public Binding bindQueue01() {
        //绑定队列topic_exchange_queue01到交换机topic.exchange,并且指定routingKey 为 user.#
        return BindingBuilder.bind(queue01()).to(topicExchange()).with("user.#");
    }

    @Bean
    public Binding bindQueue02() {
        //绑定队列topic_exchange_queue01到交换机topic.exchange,并且指定routingKey 为 user.*
        return BindingBuilder.bind(queue02()).to(topicExchange()).with("user.*");
    }

}
