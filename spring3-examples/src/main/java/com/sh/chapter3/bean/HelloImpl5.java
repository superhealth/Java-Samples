package com.sh.chapter3.bean;

import com.sh.chapter2.helloworld.HelloApi;

/**
 * Created by lkn on 2017/12/15.
 */
public abstract class HelloImpl5 implements HelloApi {

	private Printer printer;

	public abstract Printer createPrototypePrinter();

	@Override
	public void sayHello() {
		printer.print("setter");
		createPrototypePrinter().print("prototype");
		createSingletonPrinter().print("singleton");
	}

	public Printer createSingletonPrinter() {
		System.out.println("该方法不会被执行，如果输出就错了");
		return new Printer();
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
}
