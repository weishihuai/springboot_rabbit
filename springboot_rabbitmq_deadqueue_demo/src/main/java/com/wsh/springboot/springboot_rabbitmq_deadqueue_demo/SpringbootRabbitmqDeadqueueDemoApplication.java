package com.wsh.springboot.springboot_rabbitmq_deadqueue_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描的mapper文件路径
@MapperScan("com.wsh.springboot.springboot_rabbitmq_deadqueue_demo.repository")
public class SpringbootRabbitmqDeadqueueDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqDeadqueueDemoApplication.class, args);
    }

}
