package com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity;

import java.io.Serializable;

/**
 * @Description: 订单实体类
 * @Author: weixiaohuai
 * @Date: 2019/7/28
 * @Time: 8:59
 */
public class OrderInfo implements Serializable {
    /**
     * 订单ID
     */
    private String id;
    /**
     * 订单名称
     */
    private String name;
    /**
     * 消息ID
     */
    private String messageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
