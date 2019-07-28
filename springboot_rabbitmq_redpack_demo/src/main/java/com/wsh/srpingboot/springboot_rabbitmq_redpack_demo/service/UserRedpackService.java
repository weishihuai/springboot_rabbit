package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.service;

import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.entity.UserRedpack;

public interface UserRedpackService {

    //新增用户抢红包记录
    int insertGradReadPack(UserRedpack userRedpack);
}
