/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: RabbitMqListener
 * Author:   nick
 * Date:     2020/1/19 8:47
 * Description: 监听消息队列
 * History:
 */
package com.jzt.sync.controller;

import com.jzt.sync.configurer.RabbitConfig;
import com.jzt.sync.model.TestUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈监听消息队列〉
 *
 * @author nick
 * @create 2020/1/19
 * @since 1.0.0
 */
@Component
@RabbitListener(queues = RabbitConfig.SyncOrderQUEUE)
@Slf4j
public class RabbitMqListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);


    @RabbitHandler
    public void saveYzDbMq(TestUser testUser) {
        logger.info(StringUtils.join("进入", RabbitConfig.SyncOrderQUEUE, "的消费端:", testUser));
        //后续可以对testUser里面的值进行各种处理
    }

}