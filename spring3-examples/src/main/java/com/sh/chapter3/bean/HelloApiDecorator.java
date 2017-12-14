package com.sh.chapter3.bean;

import com.sh.chapter2.helloworld.HelloApi;

/**
 * Created by lkn on 2017/12/14.
 */
public class HelloApiDecorator implements HelloApi {

	private HelloApi helloApi;

	private String message;

	public HelloApiDecorator() {
	}

	public HelloApiDecorator(HelloApi helloApi) {
		this.helloApi = helloApi;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setHelloApi(HelloApi helloApi) {
		this.helloApi = helloApi;
	}

	@Override
	public void sayHello() {
		System.out.println("==========装饰开始===========");
		helloApi.sayHello();
		System.out.println("==========装饰完毕===========");
	}
}
