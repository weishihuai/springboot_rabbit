package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.service.impl;

import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.entity.UserRedpack;
import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.repository.UserRedpackMapper;
import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.service.UserRedpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRedpackServiceImpl implements UserRedpackService {

    @Autowired
    private UserRedpackMapper userRedpackMapper;

    /**
     * 插入用户抢红包记录
     */
    public int insertGradReadPack(UserRedpack userRedpack) {
        return userRedpackMapper.insertSelective(userRedpack);
    }

}
