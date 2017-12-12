package com.sh.designpattern.singleton;

/**
 * 单例模式 饱汉模式
 * UnThreadSafe
 * 饱汉，即已经吃饱，不着急再吃，饿的时候再吃。所以他就先不初始化单例，等第一次使用的时候再初始化
 * 饱汉模式的核心就是懒加载。好处是更启动速度快、节省资源，一直到实例被第一次访问，才需要初始化单例；小坏处是写起来麻烦，大坏处是线程不安全，if语句存在竞态条件
 * Created by lkn on 2017/12/4.
 */
public class Singleton1 {
	public static Singleton1 singleton = null;

	public Singleton1() {
	}

	public static Singleton1 getInstance() {
		if (singleton == null) {
			singleton = new Singleton1();
		}
		return singleton;
	}
}
