package com.wsh.springboot.springboot_rabbotmq_topic_exchange.producer;

import com.wsh.springboot.springboot_rabbotmq_topic_exchange.constant.Constants;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity.OrderInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息发送者
 * @author: weishihuai
 * @Date: 2019/7/28 10:27
 */
@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrder(OrderInfo order) throws Exception {
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        //正常发送消息
//        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, "order.save", order, correlationData);
        
        //指定一个不存在的交换机，这样触发confirmCallback失败回调，进行重发尝试
        rabbitTemplate.convertAndSend(Constants.ERROR_EXCHANGE_NAME, "order.save", order, correlationData);
    }
}

