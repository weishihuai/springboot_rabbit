package com.wsh.springboot.springboot_rabbit_simple_example.consumer;

import com.wsh.springboot.springboot_rabbit_simple_example.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息消费者
 * @author: weishihuai
 * @Date: 2019/6/26 10:30
 */
@Component
//@RabbitListener指定监听的队列名称,可以传入多个队列同时监听
@RabbitListener(queues = {"hello"})
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    //@RabbitHandler: 指定处理消息的类
    @RabbitHandler
    public void receiveMessage(String msg) {
        logger.info("receive message : " + msg);
    }

}
