package com.wsh.springboot.springboot_rabbit_fanout_exchange;

import com.wsh.springboot.springboot_rabbit_fanout_exchange.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitFanoutExchangeApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() {
        producer.sendMessage();
    }

}
