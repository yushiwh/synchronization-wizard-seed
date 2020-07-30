package com.jzt.sync.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁的例子
 * <p>
 *
 * @author nick
 */
public class LockTest {

    private static final Logger logger = LoggerFactory.getLogger(LockTest.class);


    /////////公平锁和非公平锁//////////////
    /**
     *
     *  公平锁
     * 定义：就是很公平，在并发环境中，每个线程在获取锁时会先查看此锁维护的等待队列，
     * 如果为空，或者当前线程线程是等待队列的第一个，就占有锁，否则就会加入到等待队列中，以后会按照FIFO的规则从队列中取到自己。 *
     * 优点：所有的线程都能得到资源，不会饿死在队列中。
     * 缺点：吞吐量会下降很多，队列里面除了第一个线程，其他的线程都会阻塞，cpu唤醒阻塞线程的开销会很大。
     * <p>
     * <p>
     * 非公平锁
     * 定义：线程加锁时直接尝试获取锁，获取不到就自动到队尾等待。 *
     * 优点：非公平锁性能高于公平锁性能，非公平锁能更充分的利用cpu的时间片，尽量的减少cpu空闲的状态时间。 *
     * 缺点：可能导致队列中间的线程一直获取不到锁或者长时间获取不到锁，导致饿死。 *
     *
     * true为公平锁,false为非公平锁
     */
    //公平锁
//    private ReentrantLock lock = new ReentrantLock(true);
    //非公平锁
//    private ReentrantLock lock = new ReentrantLock(false);
//
//    public void testFail() {
//        try {
//            lock.lock();
//            logger.info(Thread.currentThread().getName() + "获得了锁");
//        } finally {
//            lock.unlock();
//        }
//    }

//    public static void main(String[] args) {
//        LockTest lockTest = new LockTest();
//        Runnable runnable = () -> {
//            logger.info(Thread.currentThread().getName() + "启动");
//            lockTest.testFail();
//        };
//        Thread[] threadArray = new Thread[10];
//        for (int i = 0; i < 10; i++) {
//            threadArray[i] = new Thread(runnable);
//        }
//        for (int i = 0; i < 10; i++) {
//            threadArray[i].start();
//        }
//    }


    ///////////重入锁例子/////////////

    /**
     * 可重入锁指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁（前提得是同一个对象或者class），
     * 这样的锁就叫做可重入锁。ReentrantLock和synchronized都是可重入锁
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> A()).start();
        }
    }

    public static synchronized void A() {
        logger.info(Thread.currentThread().getName());
        B();
    }

    public static synchronized void B() {
        logger.info(Thread.currentThread().getName());
    }

    ////////////读写锁/////////////
    /**
     Java里面ReentrantReadWriteLock读写锁特性
     公平选择性： 支持非公平（默认）和公平的锁获取方式，吞吐量还是非公平优于公平。
     重进入： 读锁和写锁都支持线程重进入。
     锁降级： 锁降级是指把持住（当前拥有的）写锁，再获取到读锁，随后释放（先前拥有的）写锁的过程。
     */


}
