package com.conpany.project;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadPool extends Tester {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);


    @Test
    public void getData() throws Exception {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(() -> System.out.println("延迟三秒"), 3, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("延迟 1 秒后每三秒执行一次"), 1, 3, TimeUnit.SECONDS);
    }


}
