package com.tigerobo.dasheng.datap.jobhandler.message;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池工具类
 *
 * @author jiancongchen on 2020/06/12
 */
@Slf4j
public class ThreadPoolUtil {

    private static final Map<String, ThreadPoolExecutor> threadCache = new ConcurrentHashMap<>();

    private static final Object lock = new Object();

    /**
     * 创建一个线程池，必须要起名字
     *
     * @param name
     * @return
     */
    public static ThreadPoolExecutor getInstance(String name) {
        return getInstance(name, Runtime.getRuntime().availableProcessors(), 100, new CustomRejectedExecutionHandler());
    }

    /**
     * 创建一个线程池
     * 使用默认拒绝策略
     *
     * @param name
     * @param corePoolSize
     * @param queueSize
     * @return
     */
    public static ThreadPoolExecutor getInstance(String name, int corePoolSize, int queueSize) {
        if (threadCache.containsKey(name)) {
            return threadCache.get(name);
        } else {
            synchronized (lock) {
                return threadCache.computeIfAbsent(name, (k) -> {
                    ThreadFactory threadFactory = getThreadFactory(k);
                    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(queueSize);
                    return new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS, arrayBlockingQueue, threadFactory);
                });
            }
        }
    }

    /**
     * 创建线程池，使用提供的拒绝策略
     *
     * @param name
     * @param corePoolSize
     * @param queueSize
     * @param rejectedExecutionHandler
     * @return
     */
    public static ThreadPoolExecutor getInstance(String name, int corePoolSize, int queueSize, RejectedExecutionHandler rejectedExecutionHandler) {
        if (threadCache.containsKey(name)) {
            return threadCache.get(name);
        } else {
            synchronized (lock) {
                return threadCache.computeIfAbsent(name, (k) -> {
                    ThreadFactory threadFactory = getThreadFactory(k);
                    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(queueSize);
                    return new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS, arrayBlockingQueue, threadFactory, rejectedExecutionHandler);
                });
            }
        }
    }

    /**
     * 获取一个线程工厂
     *
     * @param name
     * @return
     */
    public static ThreadFactory getThreadFactory(String name) {
        return new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, name + "-" + threadNumber.incrementAndGet());
                thread.setDaemon(false);
                return thread;
            }

        };
    }

    /**
     * 自定义线程池的拒绝策略
     * 把线程放回到等待队列中，
     * 使用阻塞方法，保证任务不丢失
     */
    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
            try {
                // 将走到拒绝策略的线程重新放回到等待队列中，使用blockingQueue的put方法
                executor.getQueue().put(runnable);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Custom Rejected Execution Handler put queue error !", e);
            }
        }
    }
}
