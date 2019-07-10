package com.wsh.springboot.springboot_rabbitmq_consumerlimiting.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消费者
 * @Author: weixiaohuai
 * @Date: 2019/7/10
 * @Time: 20:42
 */
@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private static final String QUEUE_NAME = "consumer_limit_queue_name";

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(String msg, Message message, Channel channel) {
        try {
            //一次最多能处理多少条消息
            channel.basicQos(1);

            logger.info("【Consumer  receiveMessage】接收到消息为:[{}]", msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.info("【Consumer  receiveMessage】接收消息后的处理发生异常", e);
        }
    }

}
