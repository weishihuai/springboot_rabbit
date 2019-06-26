package com.wsh.springboot.springboot_rabbotmq_topic_exchange.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息消费者1
 * @author: weishihuai
 * @Date: 2019/6/26 10:30
 */
@Component
@RabbitListener(queues = {"topic_exchange_queue01"})
public class Consumer01 {
    private static final Logger logger = LoggerFactory.getLogger(Consumer01.class);

    @RabbitHandler
    public void receiveMessage(String msg) {
        logger.info("【Consumer01】receive message : " + msg);
    }

}
