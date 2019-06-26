package com.wsh.springboot.springboot_rabbit_simple_example;

import com.wsh.springboot.springboot_rabbit_simple_example.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitSimpleExampleApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() {
        producer.sendMessage();
    }

}
