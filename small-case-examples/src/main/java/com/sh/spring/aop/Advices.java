package com.sh.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Advices {

	@Before("execution(* com.sh.spring.aop.Math.*(..))")
    public void before(JoinPoint jp){
        System.out.println("----------前置通知----------");
        System.out.println(jp.getSignature().getName());
    }
    
    //execution切点函数
    @After("execution(* com.sh.spring.aop.*.*(..))")
    public void after(JoinPoint jp){
        System.out.println("----------最终通知----------");
    }
}
