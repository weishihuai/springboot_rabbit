package com.wsh.springboot.springboot_rabbit_simple_example.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 消息发送者
 * @author: weishihuai
 * @Date: 2019/6/26 10:27
 */
@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        String msg = new Date() + "hello rabbitmq!";
        logger.info(msg);
        rabbitTemplate.convertAndSend("hello", msg);
    }

}
