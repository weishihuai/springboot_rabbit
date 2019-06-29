package com.wsh.springboot.springboot_rabbitmq_deal_letter_exchange_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: RabbitMQ配置信息，绑定交换器、队列、路由键设置
 * @author: weishihuai
 * @Date: 2019/6/29 15:59
 * <p>
 * 说明：
 */
@Component
public class RabbitMQConfig {
    /**
     * 订单死信队列、交换机
     */
    private static final String ORDER_DEAD_LETTERS_QUEUE_NAME = "order_dead_letters_queue_name";
    private static final String ORDER_DEAD_LETTERS_EXCHANGE_NAME = "order_dead_letters_exchange_name";

    private static final String QUEUE_NAME = "test_order_dlx_queue_name";
    private static final String EXCHANGE_NAME = "test_order_dlx_exchange_name";
    private static final String ROUTING_KEY = "order.add";

    /**
     * 声明死信队列、死信交换机、绑定队列到死信交换机
     */
    @Bean
    public Queue deadLettersQueue() {
        return new Queue(ORDER_DEAD_LETTERS_QUEUE_NAME);
    }

    @Bean
    public FanoutExchange deadLettersExchange() {
        return new FanoutExchange(ORDER_DEAD_LETTERS_EXCHANGE_NAME);
    }

    @Bean
    public Binding deadLettersBinding() {
        return BindingBuilder.bind(deadLettersQueue()).to(deadLettersExchange());
    }

    /**
     * 声明普通队列，并指定订单死信交换机
     */
    @Bean
    public Queue queue() {
        Map<String, Object> arguments = new HashMap<>(10);
        //指定死信发送的Exchange
        arguments.put("x-dead-letter-exchange", ORDER_DEAD_LETTERS_EXCHANGE_NAME);
        //设置队列超时时间为5秒，如果该队列中的消息到达队列顶部时5秒内未被消费，那么该消息就会变为“死信”消息，消息将会自动进入死信队列中
        arguments.put("x-message-ttl", 5000);
        return new Queue(QUEUE_NAME, true, false, false, arguments);
    }

    @Bean
    public Exchange exchange() {
        Map<String, Object> arguments = new HashMap<>(10);
        return new DirectExchange(EXCHANGE_NAME, true, false, arguments);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY).noargs();
    }

}