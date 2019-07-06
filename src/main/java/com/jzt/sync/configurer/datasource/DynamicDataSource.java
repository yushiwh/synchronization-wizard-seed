/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: DynamicDataSource
 * Author:   nick
 * Date:     2019/7/6 11:34
 * Description: 动态的取出我们在切面里设置的数据源的字符串即可
 * History:
 */
package com.jzt.sync.configurer.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * 〈动态的取出我们在切面里设置的数据源的字符串即可〉
 *
 * @author nick
 * @create 2019/7/6
 * @since 1.0.0
 */
@Slf4j
public class DynamicDataSource  extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}