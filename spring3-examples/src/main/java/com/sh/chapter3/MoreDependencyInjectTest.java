package com.sh.chapter3;

import com.sh.chapter3.bean.DependentBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 更多的依赖注入知识
 * Created by lkn on 2017/12/14.
 */
public class MoreDependencyInjectTest {

	/**
	 * 延迟初始化Bean
	 * 使用depends-on
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDependOn() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/depends-on.xml");
		context.registerShutdownHook();
		DependentBean dependentBean = context.getBean("dependentBean", DependentBean.class);
		dependentBean.write("aaa");
	}
}
