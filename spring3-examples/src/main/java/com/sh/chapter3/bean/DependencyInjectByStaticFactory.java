package com.sh.chapter3.bean;

import com.sh.chapter2.helloworld.HelloApi;

/**
 * 静态工厂方法注入
 * Created by lkn on 2017/12/14.
 */
public class DependencyInjectByStaticFactory {

	public static HelloApi newInstance(String message, int index) {
		return new HelloImpl3(message, index);
	}
}
