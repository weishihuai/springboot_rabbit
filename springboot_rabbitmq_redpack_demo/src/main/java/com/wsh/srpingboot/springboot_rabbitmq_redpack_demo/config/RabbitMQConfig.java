package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: RabbitMQ配置类
 * @Author: weixiaohuai
 * @Date: 2019/7/27
 * @Time: 21:59
 * <p>
 */
@Component
public class RabbitMQConfig {
    private static final String QUEUE_NAME = "redPack-queue";
    private static final String EXCHANGE_NAME = "redPack-exchange";
    private static final String ROUTING_KEY = "info";

    /**
     * 声明抢红包队列
     */
    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-expires", 10000);
        return new Queue(QUEUE_NAME, true, false, false, args);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false, null);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY).noargs();
    }

}