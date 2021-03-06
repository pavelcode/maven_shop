package com.cblue.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	
	/**
	 * 单机版
	 */
	@Test
	public void testJedis() throws Exception{
		Jedis jedis = new Jedis("192.168.0.128",6379);
		jedis.set("a", "a100");
		String value = jedis.get("a");
		System.out.println(value);
		jedis.close();
	}
	/**
	 * 通过连接池获得Jedis对象
	 */
     @Test
     public void testJedisPool()throws Exception{
    	 JedisPool jedisPool = new JedisPool("192.168.0.128",6379);
    	 Jedis jedis = jedisPool.getResource();
 		 jedis.set("b", "b100");
 		 String value = jedis.get("b");
 		 System.out.println(value);
 		 jedis.close();
 		 jedisPool.close();
     }
     /**
      * 集群版
      */
     @Test
     public void testJediCluster()throws Exception{
    	 Set<HostAndPort> nodes = new HashSet<HostAndPort>();
    	 nodes.add(new HostAndPort("192.168.0.128", 6001));
    	 nodes.add(new HostAndPort("192.168.0.128", 6002));
    	 nodes.add(new HostAndPort("192.168.0.128", 6003));
    	 nodes.add(new HostAndPort("192.168.0.128", 6004));
    	 nodes.add(new HostAndPort("192.168.0.128", 6005));
    	 nodes.add(new HostAndPort("192.168.0.128", 6006));
    	 JedisCluster jedisCluster = new JedisCluster(nodes);
    	 jedisCluster.set("c","c200");
    	 String value = jedisCluster.get("c");
    	 System.out.println(value);
    	 jedisCluster.close(); 
     }
	
}
