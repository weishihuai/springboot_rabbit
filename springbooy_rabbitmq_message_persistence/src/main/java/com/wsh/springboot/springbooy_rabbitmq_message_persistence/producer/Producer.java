package com.wsh.springboot.springbooy_rabbitmq_message_persistence.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description: 生产者
 * @author: weishihuai
 * @Date: 2019/6/30 15:38
 */
@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String DURABLE_EXCHANGE_NAME = "durable_exchange_name";
    private static final String ROUTING_KEY = "user.add";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        for (int i = 1; i <= 3; i++) {
            String message = "消息" + i;
            logger.info("【生产者】发送消息：" + message);
            rabbitTemplate.convertAndSend(DURABLE_EXCHANGE_NAME, ROUTING_KEY, message, new CorrelationData(UUID.randomUUID().toString()));
        }
    }

}
