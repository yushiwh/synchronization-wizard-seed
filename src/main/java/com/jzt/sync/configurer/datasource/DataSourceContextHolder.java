/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: DataSourceContextHolder
 * Author:   nick
 * Date:     2019/7/6 11:33
 * Description: 使用ThreadLocal安全的管理当前进程使用的数据源连接
 * History:
 */
package com.jzt.sync.configurer.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 〈使用ThreadLocal安全的管理当前进程使用的数据源连接 〉
 *
 * @author nick
 * @create 2019/7/6
 * @since 1.0.0
 */
@Slf4j
public class DataSourceContextHolder {
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DATASOURCE = DataSources.MASTER_DB;

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        log.debug("切换到{}数据源", dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}