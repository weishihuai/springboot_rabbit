package com.wsh.springboot.springboot_rabbitmq_message_idempotent.consumer;

import com.rabbitmq.client.Channel;
import com.wsh.springboot.springboot_rabbitmq_message_idempotent.entity.MessageIdempotent;
import com.wsh.springboot.springboot_rabbitmq_message_idempotent.repository.MessageIdempotentRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Description: RabbitMQ消费者
 * @Author: weixiaohuai
 * @Date: 2019/7/21
 * @Time: 14:59
 * <p>
 */
@Component
public class MessageIdempotentConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageIdempotentConsumer.class);

    @Autowired
    private MessageIdempotentRepository messageIdempotentRepository;

    @RabbitHandler
    //org.springframework.amqp.AmqpException: No method found for class [B 这个异常，并且还无限循环抛出这个异常。
    //注意@RabbitListener位置，笔者踩坑，无限报上面的错，还有另外一种解决方案: 配置转换器
    @RabbitListener(queues = "message_idempotent_queue")
    @Transactional
    public void handler(Message message, Channel channel) throws IOException {
        /**
         * 发送消息之前，根据消息全局ID去数据库中查询该条消息是否已经消费过，如果已经消费过，则不再进行消费。
         */

        // 获取消息Id
        String messageId = message.getMessageProperties().getMessageId();
        if (StringUtils.isBlank(messageId)) {
            logger.info("获取消费ID为空！");
            return;
        }
        MessageIdempotent messageIdempotent = messageIdempotentRepository.findOne(messageId);
        //如果找不到，则进行消费此消息
        if (null == messageIdempotent) {
            //获取消费内容
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            logger.info("-----获取生产者消息-----------------" + "messageId:" + messageId + ",消息内容:" + msg);
            //手动ACK
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            //存入到表中，标识该消息已消费
            MessageIdempotent idempotent = new MessageIdempotent();
            idempotent.setMessageId(messageId);
            idempotent.setMessageContent(msg);
            messageIdempotentRepository.save(idempotent);
        } else {
            //如果根据消息ID(作为主键)查询出有已经消费过的消息，那么则不进行消费；
            logger.error("该消息已消费，无须重复消费！");
        }
    }

}