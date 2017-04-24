package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Top;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLiveTest {


	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
//	private RedisTemplate<String, Top> redisTemplate;
	private RedisTemplate<String, Top> redisTemplate;

	@Test
	public void test() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

		// 保存对象
		Top user = new Top("超人","20");
		redisTemplate.opsForValue().set(user.getTv(), user);

		user = new Top("蝙蝠侠", "30");
		redisTemplate.opsForValue().set(user.getTv(), user);

		user = new Top("蜘蛛侠", "40");
		redisTemplate.opsForValue().set(user.getTv(), user);

		Assert.assertEquals("20", ( redisTemplate.opsForValue().get("超人")).getHostname());
		Assert.assertEquals("30", ( redisTemplate.opsForValue().get("蝙蝠侠")).getHostname());
		Assert.assertEquals("40", ( redisTemplate.opsForValue().get("蜘蛛侠")).getHostname());
		System.out.println("ist done!!");

	}


}
