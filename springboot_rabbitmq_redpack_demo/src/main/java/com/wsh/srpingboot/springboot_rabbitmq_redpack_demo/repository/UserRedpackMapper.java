package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.repository;

import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.entity.UserRedpack;

public interface UserRedpackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRedpack record);

    int insertSelective(UserRedpack record);

    UserRedpack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRedpack record);

    int updateByPrimaryKey(UserRedpack record);
}
