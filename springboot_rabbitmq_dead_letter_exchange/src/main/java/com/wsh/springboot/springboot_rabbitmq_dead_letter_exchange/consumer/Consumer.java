package com.wsh.springboot.springboot_rabbitmq_dead_letter_exchange.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

/**
 * @Description: 消费者
 * @author: weishihuai
 * @Date: 2019/6/27 16:07
 */
@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "test_dlx_queue_name")
    public void receiveMessage(String receiveMessage, Message message, Channel channel) {
        try {
            logger.info("【Consumer】接收到消息:[{}]", receiveMessage);
            if (new Random().nextInt(10) < 5) {
                logger.info("【Consumer】拒绝一条信息:[{}]，该消息将会被转发到死信交换器中", receiveMessage);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } else {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            logger.info("【Consumer】接消息后的处理发生异常", e);
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                logger.error("手动确认消息异常.", e1);
            }
        }
    }

}
