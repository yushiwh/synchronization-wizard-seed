/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: RabbitMqTest
 * Author:   yushi
 * Date:     2019/6/17 15:45
 * Description: mq的测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.conpany.project;

import com.jzt.sync.model.QueueEnum;
import com.jzt.sync.mq.provide.MessageProvider;
import com.jzt.sync.service.impl.Sender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 〈mq的测试〉
 *
 * @author yushi
 * @create 2019/6/17
 * @since 1.0.0
 */
public class RabbitMqTest extends Tester {
    @Autowired
    private Sender sender;

    @Autowired
    private MessageProvider messageProvider;


    /**
     * 及时消费
     *
     * @throws Exception
     */
    @Test
    public void hello() throws Exception {
        sender.send();
    }

    /**
     * 延迟消费
     *
     * @throws Exception
     */
    @Test
    public void lazyHello() throws Exception {
        // 测试延迟10秒
        messageProvider.sendMessage("测试延迟消费,写入时间：" + new Date(),
                QueueEnum.MESSAGE_TTL_QUEUE.getExchange(),
                QueueEnum.MESSAGE_TTL_QUEUE.getRouteKey(),
                10000);
    }

}