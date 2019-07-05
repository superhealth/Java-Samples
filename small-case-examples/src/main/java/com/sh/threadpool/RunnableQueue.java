package com.sh.threadpool;

public interface RunnableQueue {

    // 新的队列先到offer队列中
    void offer(Runnable runnable);

    // 工作线程通过take获取runnable
    Runnable take();

    // 任务中队列数量
    int size();

}
