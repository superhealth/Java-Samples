package com.sh.chapter3;

import com.sh.chapter3.bean.DependentBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by lkn on 2017/12/14.
 */
public class MoreDependencyInjectTest {

    @Test
    public void testDependOn() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/depends-on.xml");
        context.registerShutdownHook();
        DependentBean dependentBean = context.getBean("dependentBean", DependentBean.class);
        dependentBean.write("aaa");
    }
}
