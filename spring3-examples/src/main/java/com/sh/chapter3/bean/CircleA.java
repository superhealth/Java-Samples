package com.sh.chapter3.bean;

/**
 * Created by lkn on 2017/12/14.
 */
public class CircleA {
    private CircleB circleB;

    public CircleA() {
    }

    public CircleA(CircleB circleB) {
        this.circleB = circleB;
    }

    public void setCircleB(CircleB circleB) {
        this.circleB = circleB;
    }

    public void a() {
        circleB.b();
    }
}
