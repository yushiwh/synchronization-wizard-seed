/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: RabbitMQController
 * Author:   nick
 * Date:     2020/1/19 8:43
 * Description: RabbitMQ测试
 * History:
 */
package com.jzt.sync.controller;

import com.jzt.sync.configurer.RabbitConfig;
import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.model.TestUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 〈RabbitMQ测试〉
 *
 * @author nick
 * @create 2020/1/19
 * @since 1.0.0
 */
public class RabbitMQController {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("/send")
    public Result send() {
        TestUser testUser = new TestUser();
        logger.info(StringUtils.join("消息队列-->", RabbitConfig.SyncOrderQUEUE, "发送消息", testUser));
        this.rabbitTemplate.convertAndSend(RabbitConfig.SyncOrderQUEUE, testUser);
        return ResultGenerator.genSuccessResult();
    }


}