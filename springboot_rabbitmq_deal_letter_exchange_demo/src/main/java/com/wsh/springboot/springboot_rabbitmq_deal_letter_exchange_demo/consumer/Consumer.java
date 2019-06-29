package com.wsh.springboot.springboot_rabbitmq_deal_letter_exchange_demo.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 消费者
 * @author: weishihuai
 * @Date: 2019/6/29 15:59
 */
@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    /**
     * 消费者直接监听死信队列，如果有消息，那么就是超时未支付的订单,那么就可以进一步进行处理
     */
    @RabbitListener(queues = "order_dead_letters_queue_name")
    public void receiveMessage(Map<String, Object> msgMap, Message message, Channel channel) {
        try {
            logger.info("【Consumer】接收到的超时订单消息为:[{}]", msgMap);
            //取消订单、还库存等后续操作
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.info("【Consumer】接收消息后的处理发生异常", e);
        }
    }

}
