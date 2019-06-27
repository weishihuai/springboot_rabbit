package com.wsh.springboot.springboot_rabbitmq_message_confirm2.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: 消费者2
 * @author: weishihuai
 * @Date: 2019/6/27 10:42
 */
@Component
public class Consumer02 {
    private static final Logger logger = LoggerFactory.getLogger(Consumer02.class);

    @RabbitListener(queues = "message_confirm_queue")
    public void receiveMessage02(String msg, Channel channel, Message message) throws IOException {
        try {
            logger.info("【Consumer02成功接收到消息】>>> {}", msg);
            // 确认收到消息，只确认当前消费者的一个消息收到
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.info("【Consumer02】消息已经回滚过，拒绝接收消息 ： {}", msg);
                // 拒绝消息，并且不再重新进入队列
                //public void basicReject(long deliveryTag, boolean requeue)
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                logger.info("【Consumer02】消息即将返回队列重新处理 ：{}", msg);
                //设置消息重新回到队列处理
                // requeue表示是否重新回到队列，true重新入队
                //public void basicNack(long deliveryTag, boolean multiple, boolean requeue)
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        }
    }

}
