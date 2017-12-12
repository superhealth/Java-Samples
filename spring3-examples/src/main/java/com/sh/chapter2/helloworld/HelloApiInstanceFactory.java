package com.sh.chapter2.helloworld;

import com.sh.chapter2.helloworld.HelloApi;
import com.sh.chapter2.helloworld.HelloImpl2;

/**
 * Created by lkn on 2017/12/12.
 */
public class HelloApiInstanceFactory {

	public HelloApi newInstance(String message) {
		return new HelloImpl2(message);
	}
}
