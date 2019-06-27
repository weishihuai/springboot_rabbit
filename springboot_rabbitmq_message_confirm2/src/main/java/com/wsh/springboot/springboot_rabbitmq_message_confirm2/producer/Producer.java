package com.wsh.springboot.springboot_rabbitmq_message_confirm2.producer;

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
 * @Date: 2019/6/27 10:39
 */
@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String EXCHANGE_NAME = "message_confirm_exchange";
    private static final String ROUTING_KEY = "user.add.submit";

    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        for (int i = 1; i <= 3; i++) {
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            logger.info("【Producer】发送的消费ID = {}", correlationData.getId());
            String msg = "hello confirm message" + i;
            logger.info("【Producer】发送的消息 = {}", msg);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, msg, correlationData);
        }
    }

}
