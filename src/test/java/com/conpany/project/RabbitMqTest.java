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

import com.jzt.sync.service.impl.Sender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Test
    public void hello() throws Exception {
        sender.send();
    }
}