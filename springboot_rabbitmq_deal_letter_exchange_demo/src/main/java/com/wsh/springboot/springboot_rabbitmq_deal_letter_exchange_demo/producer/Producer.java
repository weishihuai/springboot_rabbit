package com.wsh.springboot.springboot_rabbitmq_deal_letter_exchange_demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: 生产者
 * @author: weishihuai
 * @Date: 2019/6/29 15:59
 */
@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String EXCHANGE_NAME = "test_order_dlx_exchange_name";
    private static final String ROUTING_KEY = "order.add";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void orderAddSendMessage() {
        //模拟生成5条订单信息
        for (int i = 1; i <= 5; i++) {
            Map<String, Object> orderMap = new HashMap<>(10);
            orderMap.put("orderName", "订单" + i);
            orderMap.put("orderId", "ORDER_ID_" + i);
            String message = "订单生成成功，请在5秒内支付，如未支付，将会自动取消订单.....";
            orderMap.put("msg", message);
            logger.info("【订单生成成功，请在5秒内支付，如未支付，将会自动取消订单.....】");
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, orderMap, new CorrelationData(UUID.randomUUID().toString()));
        }
    }

}
