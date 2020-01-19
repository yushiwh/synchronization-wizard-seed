/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: Sender
 * Author:   yushi
 * Date:     2019/6/17 15:44
 * Description: 发送消息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.service.impl;

import com.jzt.sync.controller.TestUserController;
import com.jzt.sync.model.TestUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 〈发送消息〉
 *
 * @author yushi
 * @create 2019/6/17
 * @since 1.0.0
 */
@Component
@Slf4j
public class Sender {
    private static final Logger logger = LoggerFactory.getLogger(Sender.class);
    @Autowired
   // private AmqpTemplate rabbitTemplate;

    private  RabbitTemplate rabbitTemplate;

    public void send() {
        TestUser user = new TestUser();
        user.setId(1L);
        user.setUserName("哈哈，测试一下");
        user.setPassword("1312321");

        String context = "hello " + new Date();

        logger.info("发送消息" + user);
        this.rabbitTemplate.convertAndSend("hellotest", user);
    }
}