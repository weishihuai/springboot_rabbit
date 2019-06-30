package com.wsh.springboot.springbooy_rabbitmq_message_persistence;

import com.wsh.springboot.springbooy_rabbitmq_message_persistence.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbooyRabbitmqMessagePersistenceApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() {
        producer.sendMessage();
    }

}
