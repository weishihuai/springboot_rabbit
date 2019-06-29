package com.wsh.springboot.springboot_rabbitmq_deal_letter_exchange_demo;

import com.wsh.springboot.springboot_rabbitmq_deal_letter_exchange_demo.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqDealLetterExchangeDemoApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() {
        producer.orderAddSendMessage();
    }

}
