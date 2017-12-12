package com.sh.chapter2;

import com.sh.chapter2.helloworld.HelloApi;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lkn on 2017/12/12.
 */
public class HelloTest {

	@Test
	public void testHelloWorld() {
		// 1、读取配置文件实例化一个IoC容器
		ApplicationContext context = new ClassPathXmlApplicationContext("chapter2/helloworld.xml");
		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
		HelloApi helloApi = context.getBean("hello", HelloApi.class);
		// 3、执行业务逻辑
		helloApi.sayHello();
	}
}
