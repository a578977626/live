package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.model.TopHost;

@Service
public class RedisOperateService {

	@Autowired
	private RedisTemplate<String, TopHost> redisTemplate;
	/**
	 * 用redis来处理数据
	 * @param host
	 */
	public void handleTopHostRedis(TopHost host){
		redisTemplate.opsForHash().put("top1", host.getPlatform(), host);
		TopHost host1 = (TopHost) redisTemplate.opsForHash().get("top1", host.getPlatform());
		System.out.println(host1.getHostName()+"------"+host1.getAddress());

	}
}
