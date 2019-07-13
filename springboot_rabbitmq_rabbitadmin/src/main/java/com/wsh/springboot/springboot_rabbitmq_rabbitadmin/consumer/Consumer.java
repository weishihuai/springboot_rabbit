package com.wsh.springboot.springboot_rabbitmq_rabbitadmin.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 消费者
 * @Author: weixiaohuai
 * @Date: 2019/7/10
 * @Time: 20:42
 */
@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private static final String QUEUE_NAME = "rabbit_admin_queue_name";

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(String msg, Message message, Channel channel) {
        try {
            MessageProperties messageProperties = message.getMessageProperties();
            Map<String, Object> headers = messageProperties.getHeaders();
            logger.info("【Consumer  receiveMessage】接收到消息消息头为:[{}]", headers);
            logger.info("【Consumer  receiveMessage】接收到消息为:[{}]", new String(message.getBody()));
            //手动Ack消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.info("【Consumer  receiveMessage】接收消息后的处理发生异常", e);
        }
    }

}
