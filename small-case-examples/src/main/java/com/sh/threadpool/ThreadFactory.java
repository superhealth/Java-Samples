package com.sh.threadpool;

@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable Runnable);

}
