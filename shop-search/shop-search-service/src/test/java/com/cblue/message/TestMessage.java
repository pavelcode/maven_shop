package com.cblue.message;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMessage {

	
	@Test
	public void getMsg() throws Exception {
		//初始化spring容器
		ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//等待
		System.in.read();
	}
}
