package com.wsh.springboot.springboot_rabbitmq_message_idempotent;

import com.wsh.springboot.springboot_rabbitmq_message_idempotent.producer.MessageIdempotentProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqMessageIdempotentApplicationTests {

    @Autowired
    private MessageIdempotentProducer producer;

    @Test
    public void contextLoads() {
        producer.sendMessage();
    }

}
