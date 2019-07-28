package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static final String ROUTING_KEY = "info";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void sendMessage() {
        for (int i = 1; i <= 500; i++) {
            String userId = String.valueOf(i);
            rabbitTemplate.convertAndSend(ROUTING_KEY, userId);
        }
    }

}