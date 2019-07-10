package com.wsh.springboot.springboot_rabbitmq_consumerlimiting.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description: 生产者
 * @Author: weixiaohuai
 * @Date: 2019/7/10
 * @Time: 20:42
 */
@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String EXCHANGE_NAME = "consumer_limit_exchange_name";
    private static final String ROUTING_KEY = "user.add";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        for (int i = 1; i <= 10; i++) {
            String message = "消息" + i;
            logger.info("【生产者】发送消息：" + message);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message, new CorrelationData(UUID.randomUUID().toString()));
        }
    }

}
