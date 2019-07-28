package com.wsh.springboot.springboot_rabbotmq_topic_exchange.config;

import com.wsh.springboot.springboot_rabbotmq_topic_exchange.constant.Constants;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.mapper.BrokerMessageLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @Description 自定义消息发送确认的回调
 * @Author weishihuai
 * @Date 2019/7/28 10:42
 */
@Component
public class CustomConfirmAndReturnCallback implements RabbitTemplate.ConfirmCallback {
    private static final Logger logger = LoggerFactory.getLogger(CustomConfirmAndReturnCallback.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    /**
     * PostConstruct: 用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化.
     */
    @PostConstruct
    public void init() {
        //指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 如果消息没有到达交换机,则该方法中isSendSuccess = false,error为错误信息;
     * 如果消息正确到达交换机,则该方法中isSendSuccess = true;
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String error) {
        String messageId = correlationData.getId();
        if (isSendSuccess) {
            //如果消息到达MQ Broker，更新消息
            brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
        } else {
            logger.error("消息发送异常...");
        }
    }

}