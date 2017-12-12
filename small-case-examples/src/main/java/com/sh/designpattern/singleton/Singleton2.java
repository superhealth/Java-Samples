package com.sh.designpattern.singleton;

/**
 * 单例模式 饿汉模式
 * ThreadSafe
 *
 * 与饱汉相对，饿汉很饿，只想着尽早吃到。所以他就在最早的时机，即类加载时初始化单例，以后访问时直接返回即可
 *
 * 饿汉的好处是天生的线程安全（得益于类加载机制），写起来超级简单，使用时没有延迟；坏处是有可能造成资源浪费（如果类加载后就一直不使用单例的话)
 *
 * 注意：单线程环境下，饿汉与饱汉在性能上没什么差别；但多线程环境下，由于饱汉需要加锁，饿汉的性能反而更优。
 *
 * Created by lkn on 2017/12/4.
 */
public class Singleton2 {
	public static final Singleton2 singleton = new Singleton2();

	public Singleton2() {
	}

	public static Singleton2 getInstance() {
		return singleton;
	}
}
