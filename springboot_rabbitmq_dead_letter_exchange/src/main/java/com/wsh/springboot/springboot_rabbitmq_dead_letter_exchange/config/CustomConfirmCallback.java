package com.wsh.springboot.springboot_rabbitmq_dead_letter_exchange.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description: 自定义消息发送确认的回调
 * @author: weishihuai
 * @Date: 2019/6/27 15:18
 */
@Component
public class CustomConfirmCallback implements RabbitTemplate.ConfirmCallback {
    private static final Logger logger = LoggerFactory.getLogger(CustomConfirmCallback.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * PostConstruct: 用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化.
     */
    @PostConstruct
    public void init() {
        //指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String error) {
        logger.info("(start)生产者消息确认=========================");
        if (!isSendSuccess) {
            logger.info("消息可能未到达rabbitmq服务器");
        }
        logger.info("(end)生产者消息确认=========================");
    }

}
