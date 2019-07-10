package com.example.demo.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消费者
 * @author: weishihuai
 * @Date: 2019/6/30 15:38
 */
@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "durable_queue_name")
    public void receiveMessage(String msg, Message message, Channel channel) {
        try {
            logger.info("【Consumer  receiveMessage】接收到消息为:[{}]", msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.info("【Consumer  receiveMessage】接收消息后的处理发生异常", e);
        }
    }

}
