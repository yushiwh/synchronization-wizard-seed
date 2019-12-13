/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: ThredPool
 * Author:   nick
 * Date:     2019/12/13 11:50
 * Description: 线程池
 * History:
 */
package com.jzt.sync.controller;

import cn.hutool.system.UserInfo;
import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jzt.sync.core.Result;
import com.jzt.sync.core.ResultGenerator;
import com.jzt.sync.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 〈线程池〉
 *
 * @author nick
 * @create 2019/12/13
 * @since 1.0.0
 */
@RestController
@RequestMapping("/thread")
@Slf4j
public class ThredPool {

    /**
     * 参数：corePoolSize。the number of threads to keep in the pool, even if they are idle, unless {@code
     * allowCoreThreadTimeOut} is set。线程池中保持的线程数量，尽管这些线程是空闲的，除非设置allowCoreThreadTimeOut参数为true，则在空闲时间超过keepAliveTime
     * 时，会被终止掉。allowCoreThreadTimeOut默认为false。
     * <p>
     * 参数：maximumPoolSize。线程池中允许的最大线程数量。
     * <p>
     * 参数：keepAliveTime。保持活跃的时间，也就是当线程池中的线程数量超过corePoolSize时，这些超量的线程在等待被新任务使用前的最大等待时间，超过找个时间就要被终止掉了。
     * <p>
     * 参数：unit。保持活跃时间的单位。可选为：NANOSECONDS，MICROSECONDS，MILLISECONDS，SECONDS，MINUTES，HOURS，DAYS等。
     * <p>
     * 参数：workQueue。工作队列。这队列用来保持那些execute()方法提交的还没有执行的任务。常用的队列有SynchronousQueue,LinkedBlockingQueue,
     * ArrayBlockingQueue。一般我们需要根据自己的实际业务需求选择合适的工作队列。
     * <p>
     * SynchronousQueue：直接传递。对于一个好的默认的工作队列选择是SynchronousQueue
     * ，该队列传递任务到线程而不持有它们。在这一点上，试图向该队列压入一个任务，如果没有可用的线程立刻运行任务，那么就会入列失败，所以一个新的线程就会被创建。当处理那些内部依赖的任务集合时，这个选择可以避免锁住。直接接传递通常需要无边界的最大线程数来避免新提交任务被拒绝处理。当任务以平均快于被处理的速度提交到线程池时，它依次地确认无边界线程增长的可能性；
     * <p>
     * LinkedBlockingQueue
     * ：无界队列。没有预先定义容量的无界队列，在核心线程数都繁忙的时候会使新提交的任务在队列中等待被执行，所以将不会创建更多的线程，因此，最大线程数的值将不起作用。当每个任务之间是相互独立的时比较适合该队列，所以任务之间不能互相影响执行。例如，在一个WEB页面服务器，当平滑的出现短暂的请求爆发时这个类型的队列是非常有用的，当任务以快于平均处理速度被提交时该队列会确认无边界队列增长的可能性。
     * <p>
     * ArrayBlockingQueue：有界阻塞队列，遵循FIFO
     * 原则，一旦创建容量不能改变，当向一个已经满了的该队列中添加元素和向一个已经为空的该队列取出元素都会导致阻塞；当线程池使用有限的最大线程数时该队列可以帮助保护资源枯竭，但它更难协调和控制。队列大小和最大线程数在性能上可以互相交换：使用大队列和小线程池会降低CPU使用和OS资源与上下文切换开销，但会导致人为降低吞吐量，如果任务频繁阻塞，系统的线程调度时间会超过我们的允许值；如果使用小队列大池，这将会使CPU较为繁忙但会出现难以接受的调度开销，这也会导致降低吞吐量。
     */

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("yzorder-pool-%d").build();

    private static ExecutorService executor = new ThreadPoolExecutor(5, 10,
            30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


    @PostMapping("/test")
    public Result add() {
        executor.execute(() -> saveYzDb());
        return null;
    }


    @Transactional(rollbackFor = Exception.class)
    protected void saveYzDb() {
        System.out.println("dadas");
    }
}