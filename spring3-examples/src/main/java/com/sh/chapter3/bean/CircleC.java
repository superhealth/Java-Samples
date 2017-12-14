package com.sh.chapter3.bean;

/**
 * Created by lkn on 2017/12/14.
 */
public class CircleC {

	private CircleA circleA;

	public CircleC() {
	}

	public CircleC(CircleA circleA) {
		this.circleA = circleA;
	}

	public void setCircleA(CircleA circleA) {
		this.circleA = circleA;
	}

	public void c() {
		circleA.a();
	}
}
