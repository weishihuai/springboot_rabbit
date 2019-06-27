package com.wsh.springboot.springboot_rabbitmq_dead_letter_exchange;

import com.wsh.springboot.springboot_rabbitmq_dead_letter_exchange.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqDeadLetterExchangeApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() {
        producer.sendMessage();
    }

}
