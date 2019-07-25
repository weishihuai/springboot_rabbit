package com.wsh.springboot.springboot_rabbitmq_deadqueue_demo.producer;

import com.wsh.springboot.springboot_rabbitmq_deadqueue_demo.repository.OrderInfoMapper;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Producer {
    private static final String EXCHANGE_NAME = "test_dlx_exchange_name";
    private static final String ROUTING_KEY = "order.add";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 发送消息
     */
    public void send() {
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            // 设置编码
            messageProperties.setContentEncoding("utf-8");
            // 设置过期时间(60秒过期)
            int expiration = 1000 * 60;
            messageProperties.setExpiration(String.valueOf(expiration));
            return message;
        };

        //模拟创建五条订单消息
        for (int i = 0; i < 5; i++) {
            String orderId = String.valueOf(i);
            //订单初始化状态都为未支付
            orderInfoMapper.saveOrderInfo(UUID.randomUUID().toString(), String.valueOf(i), "0");
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, orderId, messagePostProcessor, new CorrelationData(UUID.randomUUID().toString()));
        }
    }

}