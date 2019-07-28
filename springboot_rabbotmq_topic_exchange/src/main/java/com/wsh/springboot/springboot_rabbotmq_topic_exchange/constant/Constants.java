package com.wsh.springboot.springboot_rabbotmq_topic_exchange.constant;

/**
 * @Description: 常量类
 * @Author: weixiaohuai
 * @Date: 2019/7/28
 * @Time: 9:09
 */
public class Constants {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "order-queue";

    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME = "order-exchange";

    /**
     * 错误交换机名称
     */
    public static final String ERROR_EXCHANGE_NAME = "error-order-exchange";

    /**
     * 路由键
     */
    public static final String ROUTE_KEY = "order.#";

    /**
     * 消息状态(发送中)
     */
    public static final String ORDER_SENDING = "0";

    /**
     * 消息状态(成功)
     */
    public static final String ORDER_SEND_SUCCESS = "1";

    /**
     * 消息状态(失败)
     */
    public static final String ORDER_SEND_FAILURE = "2";

    /**
     * 消息超时时间(一分钟)
     */
    public static final int ORDER_TIMEOUT = 1;

}
