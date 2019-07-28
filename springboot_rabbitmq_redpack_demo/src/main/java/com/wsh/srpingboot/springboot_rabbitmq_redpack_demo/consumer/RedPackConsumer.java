package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.consumer;

import com.rabbitmq.client.Channel;
import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.service.GrabRedPackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RedPackConsumer {

    private static final Logger log = LoggerFactory.getLogger(RedPackConsumer.class);

    @Autowired
    private GrabRedPackService grabRedPackService;

    /**
     * 监听用户抢红包信息
     */
    @RabbitListener(queues = "redPack-queue")
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            String userId = new String(message.getBody(), StandardCharsets.UTF_8);
            log.info("监听到抢红包用户：" + userId);
            //执行抢红包业务
            grabRedPackService.grabRedPack(userId);
            //手动确认
            channel.basicAck(tag, false);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户抢红包发生异常：" + e.getMessage());
            //拒绝接收
            channel.basicReject(tag, false);
        }
    }

}
