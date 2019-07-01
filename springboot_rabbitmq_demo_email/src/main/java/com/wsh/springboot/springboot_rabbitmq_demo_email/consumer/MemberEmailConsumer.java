package com.wsh.springboot.springboot_rabbitmq_demo_email.consumer;

import com.wsh.springboot.springboot_rabbitmq_demo_email.constants.Constants;
import com.wsh.springboot.springboot_rabbitmq_demo_email.entity.Member;
import com.wsh.springboot.springboot_rabbitmq_demo_email.utils.EmailSendUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 *@Description: 监听邮件发送队列的消费者
 *@Author: weixiaohuai
 *@Date: 2019/7/1
 *@Time: 21:07
 */
@Component
@RabbitListener(queues = Constants.MEMBER_SEND_MAIL_QUEUE_NAME)
public class MemberEmailConsumer {

    private static Logger logger = LoggerFactory.getLogger(MemberEmailConsumer.class);

    @Transactional
    @RabbitHandler
    public void sendMail(Member member) throws Exception {
        logger.info("会员用户名:{}，注册成功,准备发送邮件...", member.getUsername());

        //执行发送邮件操作
        new EmailSendUtils()
                .setTitle("会员注册成功通知邮件")
                .setContent("恭喜你，你已注册成为我们的会员")
                .setContentType(Constants.SEND_MAIL_TEXT_TYPE)
                .setSendMailTargets(new ArrayList<String>() {{
                    add("2897318264@qq.com");
                }}).send();
    }

}
