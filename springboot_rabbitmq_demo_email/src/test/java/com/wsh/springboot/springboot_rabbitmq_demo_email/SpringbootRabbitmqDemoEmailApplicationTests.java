package com.wsh.springboot.springboot_rabbitmq_demo_email;

import com.wsh.springboot.springboot_rabbitmq_demo_email.entity.Member;
import com.wsh.springboot.springboot_rabbitmq_demo_email.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqDemoEmailApplicationTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void contextLoads() throws Exception {
        Member member = new Member();
        member.setUsername("weixiaohuai");
        member.setPassword("123456");
        member.setEmail("2897318264@qq.com");
        memberService.memberRegister(member);
    }

}
