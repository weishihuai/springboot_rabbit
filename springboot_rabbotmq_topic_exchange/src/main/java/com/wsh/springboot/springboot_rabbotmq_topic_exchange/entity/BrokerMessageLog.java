package com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity;

import java.util.Date;

/**
 * @Description: 消息记录实体类
 * @Author: weixiaohuai
 * @Date: 2019/7/28
 * @Time: 9:00
 */
public class BrokerMessageLog {
    /**
     * 消息ID
     */
    private String messageId;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 重试次数
     */
    private Integer tryCount;
    /**
     * 消息的状态
     */
    private String status;
    /**
     * 下一次重试时间
     */
    private Date nextRetry;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
