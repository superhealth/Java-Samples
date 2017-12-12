package com.sh.designpattern.singleton;

/**
 * 单例模式 枚举模式
 * ThreadSafe
 *
 * 代码量比饿汉模式更少。但用户只能直接访问实例Singleton4.SINGLETON——事实上，这样的访问方式作为单例使用也是恰当的，只是牺牲了静态工厂方法的优点，如无法实现懒加载
 *
 * Created by lkn on 2017/12/4.
 */
public enum Singleton4 {
	SINGLETON;
}
