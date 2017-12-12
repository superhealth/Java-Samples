package com.sh.chapter2.helloworld;

import org.springframework.stereotype.Service;

/**
 * Created by lkn on 2017/12/12.
 */
@Service
public class HelloImpl implements HelloApi {

	@Override
	public void sayHello() {
		System.out.println("Hello World!");
	}

	public void destroy() {
		System.out.println("destroy");
	}
}
