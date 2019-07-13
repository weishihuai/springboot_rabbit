package com.wsh.springboot.springboot_rabbitmq_rabbitadmin.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Description: 生产者
 * @Author: weixiaohuai
 * @Date: 2019/7/13
 * @Time: 15:42
 */
@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String EXCHANGE_NAME = "rabbit_admin_exchange_name";
    private static final String ROUTING_KEY = "user.add";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        //创建消息属性
        MessageProperties messageProperties = new MessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();
        headers.put("name", "weixiaohuai");
        headers.put("sex", "male");
        headers.put("hobby", "basketball");
        //消息过期时间
        messageProperties.setPriority(10000);
        //消息持久化
        messageProperties.setReceivedDeliveryMode(MessageDeliveryMode.PERSISTENT);
//        messageProperties.setContentEncoding();
//        messageProperties.setCorrelationIdString();
//        messageProperties.setReplyTo();

        //创建消息对象
        Message message = new Message("Hello MessageProperties!!".getBytes(), messageProperties);
        //通常用来设置消息的Header以及消息的属性,允许在消息被MessageConverter处理后进一步修改消息的属性
        MessagePostProcessor messagePostProcessor = message1 -> {
            MessageProperties properties = message1.getMessageProperties();
            Map<String, Object> header = properties.getHeaders();
            String receiveMessage = new String(message1.getBody(), StandardCharsets.UTF_8);
            logger.info("【生产者】发送消息：" + receiveMessage);
            //覆盖原有的属性
            header.put("hobby", "run");
            //添加新属性到消息头部
            header.put("city", "guangzhou");
            return message1;
        };
        //发送消息
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message, messagePostProcessor);
    }

}
