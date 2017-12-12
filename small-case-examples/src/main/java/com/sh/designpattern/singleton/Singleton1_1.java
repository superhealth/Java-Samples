package com.sh.designpattern.singleton;

/**
 * 单例模式 饱汉模式
 * ThreadSafe
 * 通过synchronized 保证现场安全
 * 
 * 饱汉，即已经吃饱，不着急再吃，饿的时候再吃。所以他就先不初始化单例，等第一次使用的时候再初始化
 * 绝对线程安全；坏处是并发性能极差，事实上完全退化到了串行。单例只需要初始化一次，但就算初始化以后，synchronized的锁也无法避开，
 * 从而getInstance()完全变成了串行操作。性能不敏感的场景建议使用
 * Created by lkn on 2017/12/4.
 */
public class Singleton1_1 {
	public static Singleton1_1 singleton = null;

	public Singleton1_1() {
	}

	public synchronized static Singleton1_1 getInstance() {
		if (singleton == null) {
			singleton = new Singleton1_1();
		}
		return singleton;
	}
}
