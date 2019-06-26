package com.wsh.springboot.springboot_rabbit_simple_example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ配置类
 * @author: weishihuai
 * @Date: 2019/6/26 10:34
 */

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        //创建名称为hello的队列
        return new Queue("hello");
    }

}
