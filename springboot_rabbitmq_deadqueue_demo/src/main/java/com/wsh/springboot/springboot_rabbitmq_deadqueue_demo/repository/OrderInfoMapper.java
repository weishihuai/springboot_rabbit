package com.wsh.springboot.springboot_rabbitmq_deadqueue_demo.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoMapper {

    String findByOrderStatus(@Param("orderId") String orderId);

    void updateOrderStatus(@Param("orderId") String orderId);

    void saveOrderInfo(@Param("pkid") String pkid, @Param("orderId") String orderId, @Param("orderStatus") String orderStatus);
}


