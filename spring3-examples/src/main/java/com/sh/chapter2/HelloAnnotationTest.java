package com.sh.chapter2;

import com.sh.chapter2.helloworld.HelloApi;
import com.sh.chapter2.helloworld.HelloImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 无XML初始化Bean
 * Created by lkn on 2017/12/12.
 */
public class HelloAnnotationTest {

	@Bean(name = "hello")
	public HelloApi hello() {
		return new HelloImpl();
	}

	@Test
	public void testHelloWorld() {
		// 1、读取Annotation配置实例化一个IoC容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(HelloAnnotationTest.class);
		context.refresh();

		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
		HelloApi helloApi = context.getBean("hello", HelloApi.class);
		// 3、执行业务逻辑
		helloApi.sayHello();
	}

	/**
	 * 通过扫码@Service注解获取
	 */
	@Test
	public void testHelloWorld1() {
		// 1、读取Annotation配置实例化一个IoC容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.sh.chapter2.helloworld");
		context.refresh();

		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
		HelloApi helloApi = context.getBean(HelloImpl.class);
		// 3、执行业务逻辑
		helloApi.sayHello();
	}

}
