package com.cblue.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cblue.common.jedis.JedisClient;

public class TestJedisSpring {
	
	
	/**
	 * 使用spring管理jedis，操作redis
	 */
	@Test
	public void testJedisClient(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		//从容器中获得JedisClient对象
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.set("mytest", "jedisClient");
		String string = jedisClient.get("mytest");
		System.out.println(string);
	}

}
