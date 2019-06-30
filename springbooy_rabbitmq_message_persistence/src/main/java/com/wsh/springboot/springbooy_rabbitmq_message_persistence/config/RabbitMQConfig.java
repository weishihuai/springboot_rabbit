package com.wsh.springboot.springbooy_rabbitmq_message_persistence.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description: RabbitMQ配置信息，绑定交换器、队列、路由键设置
 * @author: weishihuai
 * @Date: 2019/6/30 15:38
 * <p>
 * 说明：
 * 1. 队列持久化：需要在声明队列的时候设置durable=true，如果只对队列进行持久化，那么mq重启之后队列里面的消息不会保存
 * 如果需要队列里面的消息也保存下来，那么还需要对消息进行持久化；
 * <p>
 * 2. 消息持久化：设置消息的deliveryMode = 2，消费者重启之后还能够继续消费持久化之后的消息；
 * 使用convertAndSend方式发送消息，消息默认就是持久化的，下面是源码：
 * new MessageProperties() --> DEFAULT_DELIVERY_MODE = MessageDeliveryMode.PERSISTENT --> deliveryMode = 2;
 * <p>
 * 3.重启mq: CMD命令行下执行 net stop RabbitMQ && net start RabbitMQ
 */
@Component
public class RabbitMQConfig {
    private static final String DURABLE_QUEUE_NAME = "durable_queue_name";
    private static final String DURABLE_EXCHANGE_NAME = "durable_exchange_name";
    private static final String ROUTING_KEY = "user.#";

    private static final String QUEUE_NAME = "not_durable_queue_name";
    private static final String EXCHANGE_NAME = "not_durable_exchange_name";

    @Bean
    public Queue durableQueue() {
//        public Queue(String name) {
//            this(name, true, false, false);
//        }
//        public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
        //不指定durable的话默认好像也是true

        //public Queue(String name, boolean durable)
        //durable：是否将队列持久化 true表示需要持久化 false表示不需要持久化
        return new Queue(DURABLE_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange durableExchange() {
//        public AbstractExchange(String name) {
//            this(name, true, false);
//        }
//        public AbstractExchange(String name, boolean durable, boolean autoDelete) {
//            this(name, durable, autoDelete, (Map)null);
//        }
        //声明交换机的时候默认也是持久化的
        return new TopicExchange(DURABLE_EXCHANGE_NAME);
    }

    @Bean
    public Binding durableBinding() {
        //如果exchange和queue都是持久化的，那么它们之间的binding也是持久化的。如果exchange和queue两者之间有一个持久化，一个非持久化，就不允许建立绑定
        return BindingBuilder.bind(durableQueue()).to(durableExchange()).with(ROUTING_KEY);
    }


    @Bean
    public Queue queue() {
        //public Queue(String name, boolean durable)
        //durable：是否将队列持久化 true表示需要持久化 false表示不需要持久化
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

}