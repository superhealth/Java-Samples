package com.sh.chapter3.bean;

import com.sh.chapter2.helloworld.HelloApi;

/**
 * Created by lkn on 2017/12/13.
 */
public class HelloImpl4 implements HelloApi {

	private String message;
	private int index;

	public HelloImpl4() {
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void sayHello() {
		System.out.println(index + ":" + message);
	}

}
