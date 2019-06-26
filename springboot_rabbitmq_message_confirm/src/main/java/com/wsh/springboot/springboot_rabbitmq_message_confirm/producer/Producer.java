package com.wsh.springboot.springboot_rabbitmq_message_confirm.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 消息发送者
 * @author: weishihuai
 * @Date: 2019/6/26 15:27
 */
@Component
public class Producer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String EXCHANGE_NAME = "message_confirm_exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        //指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("【消息id】--->" + correlationData.getId());
        String msg = new Date() + "--> message confirm!";
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "user.add.submit", msg, correlationData);
        logger.info(msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String error) {
        logger.info("confirm回调方法>>>>>>>>>>>>>回调消息ID为: " + correlationData.getId());
        if (isSendSuccess) {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送成功");
        } else {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送失败" + error);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("returnedMessage回调方法>>>>>>>>>>>>>" + new String(message.getBody(), StandardCharsets.UTF_8) + ",replyCode:" + replyCode
                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }

}
