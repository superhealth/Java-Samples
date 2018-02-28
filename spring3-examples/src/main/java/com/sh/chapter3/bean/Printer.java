package com.sh.chapter3.bean;

/**
 * Created by lkn on 2017/12/15.
 */
public class Printer {

	private int counter = 0;

	public void print(String type) {
		System.out.println(type + " printer: " + counter++);
	}
}
