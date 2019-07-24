package com.wsh.springboot.springboot_rabbitmq_message_idempotent.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ配置类
 * @Author: weixiaohuai
 * @Date: 2019/7/21
 * @Time: 14:59
 * <p>
 */
@Configuration
public class RabbitMQConfig {
    private static final String EXCHANGE_NAME = "message_idempotent_exchange";
    private static final String QUEUE_NAME = "message_idempotent_queue";
    private static final String ROUTE_KEY = "message.#";

    /**
     * 创建通配符交换机实例
     *
     * @return 通配符交换机实例
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    /**
     * 创建队列实例，并持久化
     *
     * @return 队列实例
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    /**
     * 绑定队列到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(ROUTE_KEY);
    }

}