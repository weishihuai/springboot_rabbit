package com.wsh.springboot.springboot_rabbotmq_topic_exchange.mapper;

import com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity.BrokerMessageLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description: 消息发送记录Mapper
 * @Author: weixiaohuai
 * @Date: 2019/7/28
 * @Time: 15:34
 */
@Repository
public interface BrokerMessageLogMapper {

    /**
     * 查询消息状态为0(发送中) 且已经超时的消息集合
     *
     * @return 消息集合
     */
    List<BrokerMessageLog> queryTimeoutBrokerMessageLog();

    /**
     * 更新消息重试发送次数
     *
     * @param messageId  消息ID
     * @param updateTime 更新时间
     */
    void updateBrokerMessageLogRetryCount(@Param("messageId") String messageId, @Param("updateTime") Date updateTime);

    /**
     * 更新消息发送状态
     *
     * @param messageId  消息ID
     * @param status     消息的状态
     * @param updateTime 更新时间
     */
    void changeBrokerMessageLogStatus(@Param("messageId") String messageId, @Param("status") String status, @Param("updateTime") Date updateTime);

    /**
     * 保存消息发送记录消息
     *
     * @param brokerMessageLog 消息发送记录
     */
    void saveBrokerMessageLog(BrokerMessageLog brokerMessageLog);
}
