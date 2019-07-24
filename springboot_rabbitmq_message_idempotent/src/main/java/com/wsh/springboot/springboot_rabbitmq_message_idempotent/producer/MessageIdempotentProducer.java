package com.wsh.springboot.springboot_rabbitmq_message_idempotent.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageIdempotentProducer {
    private static final String EXCHANGE_NAME = "message_idempotent_exchange";
    private static final String ROUTE_KEY = "message.insert";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void sendMessage() {
        //创建消费对象，并指定全局唯一ID(这里使用UUID，也可以根据业务规则生成，只要保证全局唯一即可)
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId(UUID.randomUUID().toString());
        messageProperties.setContentType("text/plain");
        messageProperties.setContentEncoding("utf-8");
        Message message = new Message("hello,message idempotent!".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTE_KEY, message);
    }

}