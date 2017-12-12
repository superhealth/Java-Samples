package com.sh.designpattern.singleton;

/**
 * 单例模式 饱汉模式
 * UnThreadSafe
 * 
 * 饱汉，即已经吃饱，不着急再吃，饿的时候再吃。所以他就先不初始化单例，等第一次使用的时候再初始化
 * 加上synchronized内层的check，即所谓“双重检查锁”（Double Check Lock，简称DCL）
 * 懒加载+线程安全。可惜的是，正如注释中所说，DCL仍然是线程不安全的，由于指令重排序，你可能会得到“半个对象”
 * Created by lkn on 2017/12/4.
 */
public class Singleton1_2 {
	public static Singleton1_2 singleton = null;

	public Singleton1_2() {
	}

	public static Singleton1_2 getInstance() {
		if (singleton == null) {
			synchronized (Singleton1_2.class) {
				if (singleton == null) {

					singleton = new Singleton1_2();
				}
			}
		}
		return singleton;
	}
}
