package com.jzt.sync.configurer.datasource;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nick
 * @create 2019-07-06 11:36
 */
@Service
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface RoutingDataSource {
    //默认都走主库
    String value() default DataSources.MASTER_DB;
}
