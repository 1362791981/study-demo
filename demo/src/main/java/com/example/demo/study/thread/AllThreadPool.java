package com.example.demo.study.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @Description: 常用线程池类型
 * @ClassName: AllThreadPool
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-03 15:40
 */
public class AllThreadPool {

    public static void main(String[] args) {

//        Executors.newCachedThreadPool();
//        Executors.newSingleThreadExecutor();
//        Executors.newFixedThreadPool();
//        Executors.newScheduledThreadPool(4);

    }
}

class ThreadPoolUtil {

    private int corePoolSize = 10;
    private int maximumPoolSize = 100;
    private int keepAliveTime = 60;
    TimeUnit unit = TimeUnit.SECONDS;
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
    ThreadFactory threadFactory = new NameTreadFactory();
    RejectedExecutionHandler handler = new MyIgnorePolicy();

    /**
     * 自定义线程池
     * 自定义核心线程数，最大线程数，空闲线程存活时间，自定义拒绝策略
     * newFixedPoolExecutor、newCachedPoolExecutor、newSingleThreadExecutor等都是继承自ThreadPoolExecutor
     */
    ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
            workQueue, threadFactory, handler);

    /**
     * 预定义线程池，核心线程池数与最大数相等，全是核心线程
     * 没有存活时间，因为全是核心线程
     * workQueue为无限阻塞队列LinkedBlockingQueue，最大值为Integer.MAX_VALUE
     * 缺点：由于是无限阻塞队列，可能会造成内存溢出
     * 使用场景：用于瞬间削峰，要注意内存溢出问题
     *
     * @param nThreads
     * @return
     */
    public static ExecutorService newFixedPoolExecutor(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 核心线程数为0，最大线程数为Integer.MAX_VALUE，几乎为无限
     * keepAliveTime为60秒，空闲线程60秒后自动释放
     * workQueue为同步队列 SynchronousQueue，入队出队必须同时传递
     * 因为CachedThreadPool线程创建无限制，不会有队列等待，所以使用SynchronousQueue
     * 使用场景：快速处理请求耗时短任务，例如Netty的NIO请求
     *
     * @return
     */
    public static ExecutorService newCachedPoolExecutor() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    /**
     * FinalizableDelegatedExecutorService保证了当前为真正的单核心线程池，无法向下转化成其他
     *
     * @return
     */
    public static ExecutorService newSingelPoolExecutor() {
        return new ThreadPoolExecutor(1, 1,
                0, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());
    }

    //java.util.concurrent.Executors下源码
    /*public ExecutorService newSingleThreadExecutor() {
        return new Executors.FinalizableDelegatedExecutorService
                (new ThreadPoolExecutor(1, 1,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>()));
    }


    *//**
     * ScheduledThreadPoolExecutor适用于多个后台线程执行周期性任务，同时为了满足资源管理的需求而需要限制后台线程数量的应用场景
     * 此类下的schedule为延迟执行方法
     * command：就是一个实现Runnable接口的类
     * delay：延迟多久后执行。
     * unit：用于指定keepAliveTime参数的时间单位，这是一个枚举，常用的有TimeUnit.MILLISECONDS(毫秒)，TimeUnit.SECONDS(秒)以及TimeUnit.MINUTES(分钟)等。
     * @return
     *//*

    //java.util.concurrent.Executors下源码

    *//**
     * Creates and executes a one-shot action that becomes enabled
     * after the given delay.
     *
     * @param command the task to execute
     * @param delay   the time from now to delay execution
     * @param unit    the time unit of the delay parameter
     * @return a ScheduledFuture representing pending completion of
     * the task and whose {@code get()} method will return
     * {@code null} upon completion
     * @throws RejectedExecutionException if the task cannot be
     *                                    scheduled for execution
     * @throws NullPointerException       if command is null
     *//*
    public ScheduledFuture<?> schedule(Runnable command,
                                       long delay, TimeUnit unit);


    *//**
     * Creates a thread pool that can schedule commands to run after a
     * given delay, or to execute periodically.
     *
     * @param corePoolSize the number of threads to keep in the pool,
     *                     even if they are idle
     * @return a newly created scheduled thread pool
     * @throws IllegalArgumentException if {@code corePoolSize < 0}
     *//*
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    public ScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
                new ScheduledThreadPoolExecutor.DelayedWorkQueue());
    }

    *//**
     * Creates a new {@code ScheduledThreadPoolExecutor} with the
     * given initial parameters.
     *
     * @param corePoolSize  the number of threads to keep in the pool, even
     *                      if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param threadFactory the factory to use when the executor
     *                      creates a new thread
     * @throws IllegalArgumentException if {@code corePoolSize < 0}
     * @throws NullPointerException     if {@code threadFactory} is null
     *//*
    public ScheduledThreadPoolExecutor(int corePoolSize,
                                       ThreadFactory threadFactory) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
                new ScheduledThreadPoolExecutor.DelayedWorkQueue(), threadFactory);
    }

    *//**
     * Creates a new ScheduledThreadPoolExecutor with the given
     * initial parameters.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *                     if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param handler      the handler to use when execution is blocked
     *                     because the thread bounds and queue capacities are reached
     * @throws IllegalArgumentException if {@code corePoolSize < 0}
     * @throws NullPointerException     if {@code handler} is null
     *//*
    public ScheduledThreadPoolExecutor(int corePoolSize,
                                       RejectedExecutionHandler handler) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
                new ScheduledThreadPoolExecutor.DelayedWorkQueue(), handler);
    }*/

}
class NameTreadFactory implements ThreadFactory {

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
        System.out.println(t.getName() + " has been created");
        return t;
    }
}

class MyIgnorePolicy implements RejectedExecutionHandler {

    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        doLog(r, e);
    }

    private void doLog(Runnable r, ThreadPoolExecutor e) {
        // 可做日志记录等
        System.err.println(r.toString() + " rejected");
    }
}
