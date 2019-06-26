package com.wsh.springboot.springboot_rabbitmq_direct_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ配置类
 * @author: weishihuai
 * @Date: 2019/6/26 10:34
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue01() {
        //创建名称为fanout_exchange_queue01的队列
        return new Queue("direct_exchange_queue01");
    }

    @Bean
    public Queue queue02() {
        //创建名称为direct_exchange_queue02的队列
        return new Queue("direct_exchange_queue02");
    }

    @Bean
    public DirectExchange directExchange() {
        //创建名称为direct.exchange的广播式交换机
        return new DirectExchange("direct.exchange");
    }

    @Bean
    public Binding bindQueue01() {
        //绑定队列direct_exchange_queue01到交换机direct.exchange,并且指定routingKey 为 user.add
        return BindingBuilder.bind(queue01()).to(directExchange()).with("user.add");
    }

    @Bean
    public Binding bindQueue011() {
        //绑定队列direct_exchange_queue01到交换机direct.exchange,并且指定routingKey 为 user.delete
        return BindingBuilder.bind(queue01()).to(directExchange()).with("user.delete");
    }

    @Bean
    public Binding bindQueue02() {
        //绑定队列direct_exchange_queue02到交换机direct.exchange
        return BindingBuilder.bind(queue02()).to(directExchange()).with("user.delete");
    }

}
