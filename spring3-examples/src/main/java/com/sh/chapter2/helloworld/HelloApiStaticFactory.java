package com.sh.chapter2.helloworld;

/**
 * Created by lkn on 2017/12/12.
 */
public class HelloApiStaticFactory {

	public static HelloApi newInstance(String message) {
		return new HelloImpl2(message);
	}
}
