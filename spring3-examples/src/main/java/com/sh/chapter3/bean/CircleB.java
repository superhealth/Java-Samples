package com.sh.chapter3.bean;

/**
 * Created by lkn on 2017/12/14.
 */
public class CircleB {
    private CircleC circleC;

    public CircleB() {
    }

    public CircleB(CircleC circleC) {
        this.circleC = circleC;
    }

    public void setCircleC(CircleC circleC) {
        this.circleC = circleC;
    }
    public void b() {
        circleC.c();
    }
}
