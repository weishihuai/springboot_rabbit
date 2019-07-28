package com.wsh.springboot.springboot_rabbotmq_topic_exchange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wsh.springboot.springboot_rabbotmq_topic_exchange.mapper")
public class SpringbootRabbotmqTopicExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbotmqTopicExchangeApplication.class, args);
    }

}
