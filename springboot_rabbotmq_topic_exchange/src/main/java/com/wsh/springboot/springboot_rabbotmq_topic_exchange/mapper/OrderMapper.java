package com.wsh.springboot.springboot_rabbotmq_topic_exchange.mapper;

import com.wsh.springboot.springboot_rabbotmq_topic_exchange.entity.OrderInfo;
import org.springframework.stereotype.Repository;

/**
 * @Description: 订单Mapper
 * @Author: weixiaohuai
 * @Date: 2019/7/28
 * @Time: 15:47
 */
@Repository
public interface OrderMapper {

    /**
     * 保存订单信息
     *
     * @param orderInfo 订单信息
     */
    void saveOrder(OrderInfo orderInfo);

}
