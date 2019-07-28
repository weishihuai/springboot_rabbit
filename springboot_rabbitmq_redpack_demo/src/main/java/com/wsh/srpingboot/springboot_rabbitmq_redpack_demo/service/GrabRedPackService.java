package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.service;

public interface GrabRedPackService {
    /**
     * 抢红包业务
     *
     * @param userId 用户ID
     */
    void grabRedPack(String userId);
}
