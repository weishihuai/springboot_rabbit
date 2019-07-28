package com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.repository;

import com.wsh.srpingboot.springboot_rabbitmq_redpack_demo.entity.Redpack;
import org.springframework.stereotype.Repository;

@Repository
public interface RedpackMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Redpack record);

    int insertSelective(Redpack record);

    Redpack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Redpack record);

    int updateByPrimaryKey(Redpack record);

    /**
     * 查询红包剩余个数
     *
     * @param id
     * @return
     */
    int selectRemainByPrimaryKey(Integer id);

    /**
     * 扣减红包剩余个数
     *
     * @param id
     * @return
     */
    int updateRemainRedPack(Integer id);

}


