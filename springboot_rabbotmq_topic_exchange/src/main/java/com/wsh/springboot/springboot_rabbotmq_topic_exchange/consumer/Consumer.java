package com.wsh.springboot.springboot_rabbotmq_topic_exchange.consumer;

import com.wsh.springboot.springboot_rabbotmq_topic_exchange.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息消费者
 * @author: weishihuai
 * @Date: 2019/7/28 11:30
 */
@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = {Constants.QUEUE_NAME})
    public void receiveMessage(Message message) {
        logger.info("【消费者接收到消息：{}】", message);
    }

}
