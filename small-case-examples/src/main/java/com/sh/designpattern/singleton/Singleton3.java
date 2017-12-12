package com.sh.designpattern.singleton;

/**
 * 单例模式 Holder模式
 * ThreadSafe
 *
 * 我们既希望利用饿汉模式中静态变量的方便和线程安全；又希望通过懒加载规避资源浪费。
 * Holder模式满足了这两点要求：核心仍然是静态变量，足够方便和线程安全；通过静态的Holder类持有真正实例，间接实现了懒加载。
 *
 * 相对于饿汉模式，Holder模式仅增加了一个静态内部类的成本，与饱汉的变种1_3效果相当（略优），都是比较受欢迎的实现方式。同样建议考虑
 *
 * Created by lkn on 2017/12/4.
 */
public class Singleton3 {
	public static class SingletonHolder {
		public static final Singleton3 singleton = new Singleton3();

		public SingletonHolder() {
		}
	}

	public Singleton3() {
	}

	public static Singleton3 getInstance() {
		return SingletonHolder.singleton;
	}
}
