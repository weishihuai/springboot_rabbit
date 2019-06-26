package com.wsh.springboot.springboot_rabbit_fanout_exchange.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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
        return new Queue("fanout_exchange_queue01");
    }

    @Bean
    public Queue queue02() {
        //创建名称为fanout_exchange_queue02的队列
        return new Queue("fanout_exchange_queue02");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        //创建名称为fanout.exchange的广播式交换机
        return new FanoutExchange("fanout.exchange");
    }

    @Bean
    public Binding bindQueue01() {
        //绑定队列fanout_exchange_queue01到交换机fanout.exchange
        return BindingBuilder.bind(queue01()).to(fanoutExchange());
    }

    @Bean
    public Binding bindQueue02() {
        //绑定队列fanout_exchange_queue02到交换机fanout.exchange
        return BindingBuilder.bind(queue02()).to(fanoutExchange());
    }

}
