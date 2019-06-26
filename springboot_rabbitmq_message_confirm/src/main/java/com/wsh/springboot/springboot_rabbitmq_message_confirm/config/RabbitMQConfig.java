package com.wsh.springboot.springboot_rabbitmq_message_confirm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.nio.charset.StandardCharsets;

/**
 * @Description: RabbitMQ配置类
 * @author: weishihuai
 * @Date: 2019/6/26 14:34
 */
@Configuration
public class RabbitMQConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);
    private static final String EXCHANGE_NAME = "message_confirm_exchange";
    private static final String QUEUE_NAME = "message_confirm_queue";
    private static final String ROUTING_KEY = "user.#";

    /**
     * 主机名称
     */
    @Value("${spring.rabbitmq.host}")
    private String mqHost;

    /**
     * 端口号
     */
    @Value("${spring.rabbitmq.port}")
    private String mqPort;

    /**
     * 用户名
     */
    @Value("${spring.rabbitmq.username}")
    private String mqUsername;

    /**
     * 密码
     */
    @Value("${spring.rabbitmq.password}")
    private String mqPassword;

    /**
     * 虚拟主机目录
     */
    @Value("${spring.rabbitmq.virtual-host}")
    private String mqVirtualHost;

    /**
     * 是否开启消息确认
     */
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean isPublisherConfirms;

    /**
     * 创建MQ连接工厂
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(mqHost + ":" + mqPort);
        connectionFactory.setUsername(mqUsername);
        connectionFactory.setPassword(mqPassword);
        connectionFactory.setVirtualHost(mqVirtualHost);
        connectionFactory.setPublisherConfirms(isPublisherConfirms);
        return connectionFactory;
    }

    @Bean
    public Queue queue() {
        //创建名称为message_confirm_queue的队列
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange topicExchange() {
        //创建名称为message_confirm_exchange的主题交换机
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindQueue() {
        //绑定队列message_confirm_queue到交换机message_confirm_exchange,并且指定routingKey 为 user.#
        return BindingBuilder.bind(queue()).to(topicExchange()).with(ROUTING_KEY);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory());
        //设置队列
        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        //设置最小和最大并发消费者
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(1);
        //开启手动确认消息模式
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        simpleMessageListenerContainer.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            MessageProperties messageProperties = message.getMessageProperties();
            long deliveryTag = 0;
            try {
                String receivedRoutingKey = messageProperties.getReceivedRoutingKey();
                String receiveMsg = new String(message.getBody(), StandardCharsets.UTF_8);
                deliveryTag = messageProperties.getDeliveryTag();
                logger.info("【接收消息】" + "-->receivedRoutingKey-->" + receivedRoutingKey + "-->receiveMsg->" + receiveMsg + "-->deliveryTag-->" + deliveryTag);

                if (1 == deliveryTag || 2 == deliveryTag) {
                    throw new Exception();
                }

                //手动确认消息
                channel.basicAck(deliveryTag, false);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("【messageProperties.getRedelivered()】-->" + messageProperties.getRedelivered());

                if (messageProperties.getRedelivered()) {
                    //public void basicReject(long deliveryTag, boolean requeue)
                    logger.info("【拒绝消息】......");
                    //拒绝消息，requeue=false 表示不再重新入队
                    channel.basicReject(deliveryTag, false);
                } else {
                    //public void basicNack(long deliveryTag, boolean multiple, boolean requeue)
                    // requeue为是否重新回到队列，true重新入队
                    //设置消息重新回到队列
                    logger.info("【消息重新回到队列】.......");
                    channel.basicNack(deliveryTag, false, true);
                }
            }
        });

        return simpleMessageListenerContainer;
    }

}
