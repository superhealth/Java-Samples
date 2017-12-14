package com.sh.chapter3.bean;

import com.sh.chapter2.helloworld.HelloApi;

/**
 * Created by lkn on 2017/12/14.
 */
public class DependencyInjectByInstanceFactory {

	public HelloApi newInstance(String message, int index) {
		return new HelloImpl3(message, index);
	}
}
