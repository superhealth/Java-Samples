package com.sh.chapter2.helloworld;

/**
 * Created by lkn on 2017/12/12.
 */
public class HelloImpl2 implements HelloApi {

	private String message;

	public HelloImpl2() {
		this.message = "Hello World 2!";
	}

	public HelloImpl2(String message) {
		this.message = message;
	}

	@Override
	public void sayHello() {
		System.out.println(message);
	}
}
