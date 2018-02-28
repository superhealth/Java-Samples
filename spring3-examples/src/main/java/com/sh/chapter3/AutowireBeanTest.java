package com.sh.chapter3;

import com.sh.chapter2.helloworld.HelloApi;
import com.sh.chapter3.bean.ListBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by lkn on 2017/12/15.
 */
public class AutowireBeanTest {

	/**
	 * autowire="byName" 意思是根据名字进行自动装配
	 * 
	 * @throws IOException
	 */
	@Test
	public void testAutowireByName() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/autowire-byName.xml");
		HelloApi helloApi = context.getBean("bean", HelloApi.class);
		helloApi.sayHello();
	}

	/**
	 * autowire="byType"，意思是指根据类型注入
	 *
	 * @throws IOException
	 */
	@Test
	public void testAutowireByType1() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/autowire-byType1.xml");
		HelloApi helloApi = context.getBean("bean", HelloApi.class);
		helloApi.sayHello();
	}

	@Test
	public void testAutowireByType2_1() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/autowire-byType2-1.xml");
		HelloApi helloApi = context.getBean("bean", HelloApi.class);
		helloApi.sayHello();
	}

	@Test
	public void testAutowireByType2_2() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/autowire-byType2-2.xml");
		HelloApi helloApi = context.getBean("bean", HelloApi.class);
		helloApi.sayHello();
	}

	@Test
	public void testAutowireByType2_3() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/autowire-byType2-2.xml");
		ListBean listBean = context.getBean("listBean", ListBean.class);
		// 对于集合接口根据类型注入将自动查找所有匹配的注入
		Assert.assertTrue(listBean.getList1().size() > 0);
		// 对于集合具体类型将根据具体类型进行注入，而不是选择所有泛型类型信息匹配的Bean
		Assert.assertTrue(listBean.getList2() == null);
	}

    /**
     * 因为有空构造器将使用byType方式
     * @throws IOException
     */
	@Test
	public void testAutowireByConstructor() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"chapter3/autowire-byConstructor.xml");
		HelloApi helloApi = context.getBean("bean", HelloApi.class);
		helloApi.sayHello();
	}

    /**
     * autowire="autodetect"
     * @throws IOException
     */
	@Test
	public void testAutowireByAutoDetect() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/autowire-autodetect.xml");
		HelloApi helloApi = context.getBean("bean", HelloApi.class);
		helloApi.sayHello();
	}
}
