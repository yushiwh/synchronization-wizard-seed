package com.jzt.sync.configurer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname RabbitMQConfig
 * @Description TODO
 * @Date 2020/11/2 10:04
 * @Created by nick
 */
@Configuration
public class RabbitMQConfig {
    // 交换机有四种类型,分别为Direct,topic,headers,Fanout.


    /**
     * @author: nick
     * @Date: 2020/11/2 10:08
     * @Description:Direct 模式创建队列 创建队列
     */
    @Bean(name = "nickQueue")
    public Queue testQueue() {
        return new Queue("nickQueue");
    }


    /**
     * @author: nick
     * @Date: 2020/11/2 10:08
     * @Description: 创建一个交换机
     */
    @Bean(name = "nickExchange")
    public DirectExchange testExchange() {
        return new DirectExchange("nickExchange");
    }


    /**
     * @author: nick
     * @Date: 2020/11/2 10:08
     * @Description:把队列和交换机绑定在一起
     */
    @Bean
    public Binding testBinding(@Qualifier("nickQueue") Queue queue, @Qualifier("nickExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routingKey");
    }
}
