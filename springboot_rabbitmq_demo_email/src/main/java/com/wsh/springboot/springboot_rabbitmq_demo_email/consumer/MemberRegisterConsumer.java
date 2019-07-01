package com.wsh.springboot.springboot_rabbitmq_demo_email.consumer;

import com.wsh.springboot.springboot_rabbitmq_demo_email.constants.Constants;
import com.wsh.springboot.springboot_rabbitmq_demo_email.entity.Member;
import com.wsh.springboot.springboot_rabbitmq_demo_email.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @Description: 监听用户注册队列的消费者
 * @Author: weixiaohuai
 * @Date: 2019/7/1
 * @Time: 21:08
 */
@Component
@RabbitListener(queues = Constants.MEMBER_REGISTER_QUEUE_NAME)
public class MemberRegisterConsumer {

    @Autowired
    private MemberRepository memberRepository;

    private static Logger logger = LoggerFactory.getLogger(MemberRegisterConsumer.class);

    @RabbitHandler
    @Transactional
    public void handler(Member member) throws Exception {
        logger.info("会员用户名: {}, 注册成功, 准备创建会员信息...", member.getUsername());
        //保存会员消息
        memberRepository.save(member);
    }

}