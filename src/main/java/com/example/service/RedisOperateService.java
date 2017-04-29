package com.example.service;

import java.util.Map;

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
	 * 
	 * @param host
	 */
	public void handleTopHostRedis(TopHost host) {
		redisTemplate.opsForHash().put("top1", host.getPlatform(), host);
		TopHost host1 = (TopHost) redisTemplate.opsForHash().get("top1", host.getPlatform());
		System.out.println(host1.getHostName() + "------" + host1.getAddress());

	}
	/**
	 * 返回redis里面缓存的主播们-_-
	 * @param key
	 * @return
	 */
	public Map<Object, Object> getMapObjcetOnHashRedis(String key){
		Map<Object, Object> hashCache = redisTemplate.opsForHash().entries(key);
//				            for (Map.Entry<Object, Object> entry : hashCache.entrySet()) {
//				                 System.out.println(entry.getKey() + " - " + entry.getValue());
//				            }
		return hashCache;
	}

}
