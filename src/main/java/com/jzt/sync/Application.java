package com.jzt.sync;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 此处需要去掉datasource的自动的注入，改为手动注入数据源的方式
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
/**
 * 开启扫描自定义的filter
 */
@ServletComponentScan
//开启swagger
@EnableSwagger2Doc
//@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

