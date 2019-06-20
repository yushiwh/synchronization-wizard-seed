/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: MessageRabbitMqConfiguration
 * Author:   yushi
 * Date:     2019/6/20 9:43
 * Description: 消息通知-消息队列配置信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.configurer;

import com.jzt.sync.model.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈消息通知-消息队列配置信息〉
 *
 * @author yushi
 * @create 2019/6/20
 * @since 1.0.0
 */
@Configuration
public class MessageRabbitMqConfiguration {
    /**
     * 消息中心实际消费队列交换配置
     *
     * @return
     */
    @Bean
    DirectExchange messageDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.MESSAGE_QUEUE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 消息中心延迟消费交换配置
     *
     * @return
     */
    @Bean
    DirectExchange messageTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.MESSAGE_TTL_QUEUE.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 消息中心实际消费队列配置
     *
     * @return
     */
    @Bean
    public Queue messageQueue() {
        return new Queue(QueueEnum.MESSAGE_QUEUE.getName());
    }


    /**
     * 消息中心TTL队列
     *
     * @return
     */
    @Bean
    Queue messageTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.MESSAGE_TTL_QUEUE.getName())
                // 配置到期后转发的交换
                .withArgument("x-dead-letter-exchange", QueueEnum.MESSAGE_QUEUE.getExchange())
                // 配置到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.MESSAGE_QUEUE.getRouteKey())
                .build();
    }

    /**
     * 消息中心实际消息交换与队列绑定
     *
     * @param messageDirect 消息中心交换配置
     * @param messageQueue  消息中心队列
     * @return
     */
    @Bean
    Binding messageBinding(DirectExchange messageDirect, Queue messageQueue) {
        return BindingBuilder
                .bind(messageQueue)
                .to(messageDirect)
                .with(QueueEnum.MESSAGE_QUEUE.getRouteKey());
    }

    /**
     * 消息中心TTL绑定实际消息中心实际消费交换机
     *
     * @param messageTtlQueue
     * @param messageTtlDirect
     * @return
     */
    @Bean
    public Binding messageTtlBinding(Queue messageTtlQueue, DirectExchange messageTtlDirect) {
        return BindingBuilder
                .bind(messageTtlQueue)
                .to(messageTtlDirect)
                .with(QueueEnum.MESSAGE_TTL_QUEUE.getRouteKey());
    }
}