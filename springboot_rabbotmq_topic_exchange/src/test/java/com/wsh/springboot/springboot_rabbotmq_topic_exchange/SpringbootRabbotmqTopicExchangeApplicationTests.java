package com.wsh.springboot.springboot_rabbotmq_topic_exchange;

import com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity.OrderInfo;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.producer.Producer;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbotmqTopicExchangeApplicationTests {

    @Autowired
    private Producer producer;
    @Autowired
    private OrderService orderService;

    @Test
    public void contextLoads() throws Exception {
        OrderInfo order = new OrderInfo();
        order.setId(UUID.randomUUID().toString());
        order.setName("商品订单");
        order.setMessageId(UUID.randomUUID().toString());
        producer.sendOrder(order);
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderInfo order = new OrderInfo();
        order.setId(UUID.randomUUID().toString());
        order.setName("图书订单");
        order.setMessageId(UUID.randomUUID().toString());
        orderService.saveOrderInfo(order);
    }

}
