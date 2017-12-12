package com.sh.designpattern.singleton;

/**
 * 单例模式 饱汉模式
 * ThreadSafe
 * 
 * 饱汉，即已经吃饱，不着急再吃，饿的时候再吃。所以他就先不初始化单例，等第一次使用的时候再初始化
 * 针对变种1_2的“半个对象”问题，变种3在instance上增加了volatile关键字
 *
 * Created by lkn on 2017/12/4.
 */
public class Singleton1_3 {
	public static volatile Singleton1_3 singleton = null;

	public Singleton1_3() {
	}

	public static Singleton1_3 getInstance() {
		if (singleton == null) {
			synchronized (Singleton1_3.class) {
				// must be a complete instance
				if (singleton == null) {

					singleton = new Singleton1_3();
				}
			}
		}
		return singleton;
	}
}
