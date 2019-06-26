package com.wsh.springboot.springboot_rabbitmq_direct_exchange.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 消息发送者 - 直连交换机
 * @author: weishihuai
 * @Date: 2019/6/26 10:27
 */
@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        String msg = new Date() + "--> hello direct exchange!";
        logger.info(msg);
        //发送消息到名称为direct.exchange的交换机，然后与这个交换机绑定的队列上面的所有消费者都可以接收到该消息
        rabbitTemplate.convertAndSend("direct.exchange", "user.add", msg);

        //如果指定routingKey为user.delete，那么消费者1和消费者2都可以接收到消息
        //rabbitTemplate.convertAndSend("direct.exchange", "user.delete", msg);
    }

}
