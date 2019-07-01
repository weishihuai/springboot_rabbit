package com.wsh.springboot.springboot_rabbitmq_demo_email.repository;

import com.wsh.springboot.springboot_rabbitmq_demo_email.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: JPA持久层接口
 * @Author: weixiaohuai
 * @Date: 2019/7/1
 * @Time: 21:08
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

}
