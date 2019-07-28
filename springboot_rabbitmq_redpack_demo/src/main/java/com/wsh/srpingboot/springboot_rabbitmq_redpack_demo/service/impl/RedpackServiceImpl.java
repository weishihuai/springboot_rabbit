package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.service.impl;

import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.repository.RedpackMapper;
import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.service.RedpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedpackServiceImpl implements RedpackService {

    @Autowired
    private RedpackMapper redpackMapper;

    /**
     * 获取红包剩余个数
     *
     * @param id
     * @return
     */
    public int getRedPackRemain(Integer id) {
        return redpackMapper.selectRemainByPrimaryKey(id);
    }

    /**
     * 扣减红包剩余个数
     */
    public int deducteRedPack(Integer id) {
        return redpackMapper.updateRemainRedPack(id);
    }

}
