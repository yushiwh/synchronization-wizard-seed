/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: RabbitConfig
 * Author:   yushi
 * Date:     2019/6/17 15:34
 * Description: RabbitMQ的配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.configurer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 〈RabbitMQ的配置〉
 *
 * @author yushi
 * @create 2019/6/17
 * @since 1.0.0
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hellotest");
    }
}