package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.repository")
public class SpringbootRabbitmqRedpackDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqRedpackDemoApplication.class, args);
    }

}
