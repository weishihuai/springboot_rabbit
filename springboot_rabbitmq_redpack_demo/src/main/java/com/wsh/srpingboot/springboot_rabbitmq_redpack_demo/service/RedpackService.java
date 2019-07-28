package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.service;

public interface RedpackService {

    //查询红包个数
    int getRedPackRemain(Integer id);

    //扣减红包个数
    int deducteRedPack(Integer id);

}