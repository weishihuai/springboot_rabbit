package com.wsh.springboot.springboot_rabbitmq_message_confirm.consumer;

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
@RabbitListener(queues = {"message_confirm_queue"})
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitHandler
    public void receiveMessage(String msg) {
        logger.info("【Consumer】receive message : " + msg);
    }

}
