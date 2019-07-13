package com.wsh.springboot.springboot_rabbitmq_rabbitadmin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Rabbitmq 配置类
 *
 * @author weishihuai
 * <p>
 */
@Component
public class RabbitMQConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);
    private static final String QUEUE_NAME = "rabbit_admin_queue_name";
    private static final String EXCHANGE_NAME = "rabbit_admin_exchange_name";
    private static final String ROUTING_KEY = "user.#";

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

    /**
     * 简单消息监听容器
     * 作用:
     * 监听单个或多个队列、自动启动、自动声明功能;
     * 设置事务特性、事务管理器、事务属性、事务并发、是否开启事务、回滚消息等;
     * 设置消费者数量、最小最大数量、批量消费;
     * 设置消息确认和自动确认模式、是否重回队列、异常捕获 Handler 函数;
     * 设置消费者标签生成策略、是否独占模式、消费者属性等;
     * 设置具体的监听器、消息转换器等等;
     * 注意: SimpleMessageListenerContainer 可以进行动态设置，比如在运行中的应用可以动态的修改其消费者数量的大小、接收消息的模式等;
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        //当前消费者并发度
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        //消费者的最大并行度，不能小于ConcurrentConsumers，默认为ConcurrentConsumers
        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
        //是否重回队列
        simpleMessageListenerContainer.setDefaultRequeueRejected(false);
        //手动ACK
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //消息监听器
        simpleMessageListenerContainer.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            MessageProperties messageProperties = message.getMessageProperties();
            Map<String, Object> headers = messageProperties.getHeaders();
            logger.info("【SimpleMessageListenerContainer  simpleMessageListenerContainer】接收到消息消息头为:[{}]", headers);
            logger.info("【SimpleMessageListenerContainer  simpleMessageListenerContainer】接收到消息为:[{}]", new String(message.getBody()));
        });

        //设置监听的队列
//        public void setQueues(Queue... queues)
        //这里设置监听rabbit_admin_queue_name队列
        simpleMessageListenerContainer.setQueues(queue());

        //设置自动声明
//        simpleMessageListenerContainer.setAutoDeclare();
        //设置消费者标签生成策略
//        simpleMessageListenerContainer.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String s) {
//                return null;
//            }
//        });
//        simpleMessageListenerContainer.setMessagePropertiesConverter(new MessagePropertiesConverter() {
//            @Override
//            public MessageProperties toMessageProperties(AMQP.BasicProperties basicProperties, Envelope envelope, String s) {
//                return null;
//            }
//
//            @Override
//            public AMQP.BasicProperties fromMessageProperties(MessageProperties messageProperties, String s) {
//                return null;
//            }
//        });

        //设置消息转换器
//        simpleMessageListenerContainer.setMessageConverter(new MessageConverter() {
//            @Override
//            public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
//                return null;
//            }
//
//            @Override
//            public Object fromMessage(Message message) throws MessageConversionException {
//                return null;
//            }
//        });

        logger.info("【SimpleMessageListenerContainer  simpleMessageListenerContainer】:[{}]", simpleMessageListenerContainer);
        return simpleMessageListenerContainer;
    }

}