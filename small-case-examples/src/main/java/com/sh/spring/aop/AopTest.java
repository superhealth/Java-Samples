package com.sh.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {


	    public static void main(String[] args) {
	        @SuppressWarnings("resource")
			ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/sh/spring/aop/aop.xml");
	        Math math = ctx.getBean("math", Math.class);
	        int n1 = 100, n2 = 5;
	        math.add(n1, n2);
	        math.sub(n1, n2);
	        math.mut(n1, n2);
	        math.div(n1, n2);
	        System.out.println(1 << 1);
	    }

}
