/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: MessageConsumer
 * Author:   yushi
 * Date:     2019/6/20 9:47
 * Description: 延迟消费
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈延迟消费〉
 *
 * @author yushi
 * @create 2019/6/20
 * @since 1.0.0
 */
@Component
@RabbitListener(queues = "message.center.create")
public class MessageConsumer {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @RabbitHandler
    public void handler(String content) {
        logger.info("消费内容：{}", content);
    }
}