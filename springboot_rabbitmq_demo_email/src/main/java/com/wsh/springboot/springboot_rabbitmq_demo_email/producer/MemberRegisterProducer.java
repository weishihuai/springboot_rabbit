package com.wsh.springboot.springboot_rabbitmq_demo_email.producer;

import com.wsh.springboot.springboot_rabbitmq_demo_email.constants.Constants;
import com.wsh.springboot.springboot_rabbitmq_demo_email.entity.Member;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberRegisterProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送会员注册通知消息
     *
     * @param message 消息内容
     */
    public void sendMessage(Member message) {
        rabbitTemplate.convertAndSend(Constants.MEMBER_TOPIC_EXCHANGE_NAME, Constants.MEMBER_TOPIC_EXCHANGE_ROUTE_KEY, message);
    }

}