package com.wsh.springboot.springboot_rabbotmq_topic_exchange.scheduler;

import com.alibaba.fastjson.JSONObject;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.constant.Constants;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity.BrokerMessageLog;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity.OrderInfo;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.mapper.BrokerMessageLogMapper;
import com.wsh.springboot.springboot_rabbotmq_topic_exchange.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Description: 消息重发定时器
 * @Author: weixiaohuai
 * @Date: 2019/7/28
 * @Time: 15:50
 */
@Component
public class MessageReSendScheduler {
    private static final Logger logger = LoggerFactory.getLogger(MessageReSendScheduler.class);

    @Autowired
    private Producer producer;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    /**
     * 容器启动后,延迟5秒后执行定时器,以后每10秒再执行一次该定时器
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend() {
        //查询出状态为0以及超时的消息
        List<BrokerMessageLog> list = brokerMessageLogMapper.queryTimeoutBrokerMessageLog();
        if (null != list && list.size() > 0) {
            for (BrokerMessageLog brokerMessageLog : list) {
                if (null != brokerMessageLog) {
                    if (brokerMessageLog.getTryCount() >= 3) {
                        logger.info("消息【{}】重试三次之后仍失败..", brokerMessageLog.getMessageId());
                        //重试失败三次，更新消息状态为发送失败
                        brokerMessageLogMapper.changeBrokerMessageLogStatus(brokerMessageLog.getMessageId(), Constants.ORDER_SEND_FAILURE, new Date());
                    } else {
                        // 如果重试次数小于三次，那么进行重发
                        brokerMessageLogMapper.updateBrokerMessageLogRetryCount(brokerMessageLog.getMessageId(), new Date());
                        OrderInfo orderInfo = JSONObject.parseObject(brokerMessageLog.getMessage(), OrderInfo.class);
                        try {
                            logger.info("消息【{}】即将进行重发尝试..", brokerMessageLog.getMessageId());
                            producer.sendOrder(orderInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error("消息处理异常..");
                        }
                    }
                }
            }
        }
    }
}
