package com.jzt.sync.util;

import org.springframework.context.annotation.Configuration;

/**
 * @Author：BluesZhao
 * @Description：id生成器
 * @Date： Create in 下午3:57 2018/5/16
 */
@Configuration
public class IdWorker {
    private static final Sequence worker = new Sequence();

    public IdWorker() {
    }

    public static long getId() {
        return worker.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(worker.nextId());
    }
}
