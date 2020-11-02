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

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

/**
 * 〈RabbitMQ的配置〉
 *
 * @author yushi
 * @create 2019/6/17
 * @since 1.0.0
 */
@Configuration
public class RabbitConfig {
    public static final String SyncOrderQUEUE = "hellotest";

    @Bean
    public Queue helloQueue() {
        return new Queue(SyncOrderQUEUE, true);
    }





    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}