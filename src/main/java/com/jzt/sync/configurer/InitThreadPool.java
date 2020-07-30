/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: InitThreadPool
 * Author:   nick
 * Date:     2019/12/16 15:04
 * Description: 通用线程池
 * History:
 */
package com.jzt.sync.configurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 〈通用线程池〉
 *
 * 使用
 *
 *   @Resource(name = "Executor")
 *     private Executor executor;
 *
 * @author nick
 * @create 2019/12/16
 * @since 1.0.0
 */
@Configuration
@EnableAsync
public class InitThreadPool {

    private static final Logger logger = LoggerFactory.getLogger(InitThreadPool.class);

    /**
     * 线程池维护线程的最少数量
     */
    private static final int CORE_POOL_SIZE = 5;

    /**
     * 线程池维护线程的最大数量
     */
    private static final int MAX_POOL_SIZE = 10;

    /**
     * 缓存队列
     */
    private static final int QUEUE_CAPACITY = 10;

    /**
     * 允许的空闲时间
     */
    private static final int KEEP_ALIVE = 30;

    private static final String POOL_NAME = "yzOrder-";


    /**
     * 线程池维护线程的最少数量
     */
    private static final int CORE_POOL_SIZE_CLIENT = 10;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int MAX_POOL_SIZE_CLIENT = 15;
    /**
     * 缓存队列
     */
    private static final int QUEUE_CAPACITY_CLIENT = 10;
    /**
     * 允许的空闲时间
     */
    private static final int KEEP_ALIVE_CLIENT = 30;

    private static final String POOL_CLIENT_NAME = "clientData-";


    @Primary
    @Bean(name = "Executor")
    public Executor myExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(POOL_NAME);
        /*
         *  使用此策略，如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(KEEP_ALIVE);
        executor.initialize();
        logger.info("通用线程池yzOrder创建成功");
        return executor;


    }

    @Bean(name = "ClientExecutor")
    public Executor ClientExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE_CLIENT);
        executor.setMaxPoolSize(MAX_POOL_SIZE_CLIENT);
        executor.setQueueCapacity(QUEUE_CAPACITY_CLIENT);
        executor.setThreadNamePrefix(POOL_CLIENT_NAME);
        /*
         *  使用此策略，如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(KEEP_ALIVE_CLIENT);
        executor.initialize();
        logger.info("通用线程池CLIENT_DATA创建成功");
        return executor;


    }


}