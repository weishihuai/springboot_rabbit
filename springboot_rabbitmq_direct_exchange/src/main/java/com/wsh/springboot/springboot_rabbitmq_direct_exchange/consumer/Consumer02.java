package com.wsh.springboot.springboot_rabbitmq_direct_exchange.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息消费者2
 * @author: weishihuai
 * @Date: 2019/6/26 10:30
 */
@Component
@RabbitListener(queues = {"direct_exchange_queue02"})
public class Consumer02 {
    private static final Logger logger = LoggerFactory.getLogger(Consumer02.class);

    @RabbitHandler
    public void receiveMessage(String msg) {
        logger.info("【Consumer02】receive message : " + msg);
    }

}
