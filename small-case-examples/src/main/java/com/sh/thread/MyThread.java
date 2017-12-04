package com.sh.thread;

/**
 * MyThread class
 *
 * @author lkn
 * @date 2017/11/14
 */
public class MyThread extends Thread {

	private int i = 0;

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
		super.run();
	}

	public static void main(String[] args) {

	}
}
