package com.wsh.springboot.springboot_rabbitmq_deadqueue_demo.entity;


import java.io.Serializable;

public class OrderInfo implements Serializable {
    private String pkid;
    private String orderId;
    private String orderStatus;

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
