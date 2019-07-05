package com.sh.threadpool;

public interface ThreadPool {

    // 提交任务到线程池
    void execute(Runnable runnable);

    // 关闭
    void shutdown();

    int getInitSize();

    int getMaxSize();

    int getCoreSize();

    int getQueueSize();

    int getActiveCount();

    boolean isShutdown();
}
