/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: RestTemplateConfig
 * Author:   nick
 * Date:     2020/1/16 9:49
 * Description: RestTemplate配置
 * History:
 */
package com.jzt.sync.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 〈RestTemplate配置〉
 *
 * @author nick
 * @create 2020/1/16
 * @since 1.0.0
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 默认使用OKHttp3
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(okHttp3ClientHttpRequestFactory());
        return restTemplate;
    }


    @Bean("urlConnection")
    public RestTemplate urlConnectionRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory());
        return restTemplate;
    }

    @Bean("httpClient")
    public RestTemplate httpClientRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory());
        return restTemplate;
    }

    @Bean("OKHttp3")
    public RestTemplate OKHttp3RestTemplate() {
        RestTemplate restTemplate = new RestTemplate(okHttp3ClientHttpRequestFactory());
        return restTemplate;
    }

    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        //默认的是JDK提供http连接，需要的话可以//通过setRequestFactory方法替换为例如Apache HttpComponents、Netty或//OkHttp等其它HTTP library。
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        //单位为ms
        factory.setReadTimeout(5000);
        //单位为ms
        factory.setConnectTimeout(5000);
        return factory;
    }


    public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        //默认的是JDK提供http连接，需要的话可以//通过setRequestFactory方法替换为例如Apache HttpComponents、Netty或//OkHttp等其它HTTP library。
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        //单位为ms
        factory.setReadTimeout(5000);
        //单位为ms
        factory.setConnectTimeout(5000);
        return factory;
    }


    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        //默认的是JDK提供http连接，需要的话可以//通过setRequestFactory方法替换为例如Apache HttpComponents、Netty或//OkHttp等其它HTTP library。
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //单位为ms
        factory.setReadTimeout(5000);
        //单位为ms
        factory.setConnectTimeout(5000);
        return factory;
    }
}