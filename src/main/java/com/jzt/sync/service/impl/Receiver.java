/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: Receiver
 * Author:   yushi
 * Date:     2019/6/17 15:39
 * Description: RabbitMq的测试收到消息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.service.impl;

import com.jzt.sync.model.TestUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈RabbitMq的测试收到消息〉
 *
 * @author yushi
 * @create 2019/6/17
 * @since 1.0.0
 */
@Component
@Slf4j
@RabbitListener(queues = "hellotest")
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitHandler
    public void process(String hello) {
        logger.info("收到消息" + hello);
    }


    @RabbitHandler
    public void process(TestUser user) {
        logger.info("收到消息" + user);
    }

}