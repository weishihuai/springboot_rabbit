package com.wsh.springboot.springboot_rabbitmq_demo_email.config;


import com.wsh.springboot.springboot_rabbitmq_demo_email.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ配置类
 * @Author: weixiaohuai
 * @Date: 2019/7/1
 * @Time: 21:05
 * <p>
 * 说明：
 * 声明了两个队列： 会员注册队列、发送邮件队列
 * 两个队列同时绑定到同一个交换机rabbitmq_topic_exchange_name上面，两个队列接收到消息之后，发送邮件队列异步发送邮件通知用户
 */
@Configuration
public class RabbitMQConfig {
    /**
     * 创建通配符交换机实例
     *
     * @return 通配符交换机实例
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(Constants.MEMBER_TOPIC_EXCHANGE_NAME);
    }

    /**
     * 创建会员注册队列实例，并持久化
     *
     * @return 会员注册队列实例
     */
    @Bean
    public Queue memberRegisterQueue() {
        return new Queue(Constants.MEMBER_REGISTER_QUEUE_NAME, true);
    }

    /**
     * 创建会员发送邮件队列实例，并持久化
     *
     * @return 会员发送邮件队列实例
     */
    @Bean
    public Queue memberSendMailQueue() {
        return new Queue(Constants.MEMBER_SEND_MAIL_QUEUE_NAME, true);
    }

    /**
     * 绑定会员注册队列到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding memberRegisterBinding() {
        return BindingBuilder.bind(memberRegisterQueue()).to(topicExchange()).with(Constants.MEMBER_REGISTER_QUEUE_ROUTE_KEY);
    }

    /**
     * 绑定会员发送邮件队列到交换机
     *
     * @return 绑定对象
     */
    @Bean
    public Binding memberSendMailBinding() {
        return BindingBuilder.bind(memberSendMailQueue()).to(topicExchange()).with(Constants.MEMBER_SEND_MAIL_QUEUE_ROUTE_KEY);
    }
}