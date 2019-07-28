package com.wsh.springboot.springboot_rabbotmq_topic_exchange.service;

import com.alibaba.fastjson.JSONObject;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.constant.Constants;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity.BrokerMessageLog;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity.OrderInfo;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.mapper.BrokerMessageLogMapper;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.mapper.OrderMapper;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.producer.Producer;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: 订单服务层接口
 * @Author: weixiaohuai
 * @Date: 2019/7/28
 * @Time: 15:36
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;
    @Autowired
    private Producer producer;

    public void saveOrderInfo(OrderInfo order) throws Exception {
        Date nowDate = new Date();
        // 1.保存订单信息
        orderMapper.saveOrder(order);

        // 2.保存消息记录信息
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        brokerMessageLog.setMessageId(order.getMessageId());
        brokerMessageLog.setMessage(JSONObject.toJSONString(order));
        // 初始化消息状态为0
        brokerMessageLog.setStatus(Constants.ORDER_SENDING);
        // 初始化已重试次数为0
        brokerMessageLog.setTryCount(0);
        // 设置消息超时时间为一分钟
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(nowDate, Constants.ORDER_TIMEOUT));
        brokerMessageLog.setCreateTime(nowDate);
        brokerMessageLog.setUpdateTime(nowDate);
        brokerMessageLogMapper.saveBrokerMessageLog(brokerMessageLog);

        // 3.消息发送者发送消息到MQ
        producer.sendOrder(order);
    }

}
