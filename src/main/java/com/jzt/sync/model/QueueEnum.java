/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: QueueEnum
 * Author:   yushi
 * Date:     2019/6/20 9:12
 * Description: 消息队列枚举配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.model;

import lombok.Getter;

/**
 * 〈消息队列枚举配置〉
 *
 * @author yushi
 * @create 2019/6/20
 * @since 1.0.0
 */
@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    MESSAGE_QUEUE("message.center.direct", "message.center.create", "message.center.create"),

    /**
     * 消息通知ttl队列
     */
    MESSAGE_TTL_QUEUE("message.center.topic.ttl", "message.center.create.ttl", "message.center.create.ttl");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}