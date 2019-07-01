package com.wsh.springboot.springboot_rabbitmq_demo_email.service;

import com.wsh.springboot.springboot_rabbitmq_demo_email.entity.Member;
import com.wsh.springboot.springboot_rabbitmq_demo_email.producer.MemberRegisterProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRegisterProducer memberRegisterProducer;

    public Long memberRegister(Member member) throws Exception {
        //会员注册
        memberRegisterProducer.sendMessage(member);
        return member.getId();
    }

}