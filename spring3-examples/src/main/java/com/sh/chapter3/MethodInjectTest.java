package com.sh.chapter3;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sh.chapter2.helloworld.HelloApi;
import com.sh.chapter3.bean.Printer;

/**
 * 方法注入
 * Created by lkn on 2017/12/15.
 */
public class MethodInjectTest {

	/**
	 * 查找方法注入：又称为Lookup方法注入，用于注入方法返回结果，也就是说能通过配置方式替换方法返回结果。使用<lookup-method name
	 * ="方法名" bean="bean名字"/>配置；其中name属性指定方法名，bean属性指定方法需返回的Bean
	 */
	@Test
	public void testLookup() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/lookupMethodInject.xml");
		System.out.println("=======singleton sayHello======");
		HelloApi helloApi1 = context.getBean("helloApi1", HelloApi.class);
		helloApi1.sayHello();
		helloApi1 = context.getBean("helloApi1", HelloApi.class);
		helloApi1.sayHello();
		System.out.println("=======prototype sayHello======");
		HelloApi helloApi2 = context.getBean("helloApi2", HelloApi.class);
		helloApi2.sayHello();
		helloApi2 = context.getBean("helloApi2", HelloApi.class);
		helloApi2.sayHello();
	}

	/**
	 * 替换方法注入：也叫“MethodReplacer”注入，和查找注入方法不一样的是，他主要用来替换方法体。通过首先定义一个MethodReplacer接口实现，然后如下配置来实现
	 */
	@Test
	public void testMethodReplacer() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"chapter3/methodReplacerInject.xml");
		Printer printer = context.getBean("printer", Printer.class);
		printer.print("我将被替换");
	}
}
