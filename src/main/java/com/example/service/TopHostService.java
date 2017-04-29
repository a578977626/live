package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.example.util.ReturnObject;

@Service
public class TopHostService {

	@Autowired
    private RedisOperateService redisService;
	
	public String getTopHostList (String key){
		Map<Object,Object> hostMap = redisService.getMapObjcetOnHashRedis(key);
		ReturnObject rObj = new ReturnObject(hostMap);
		String res = JSON.toJSONString(rObj, true);
		return res;
	}
}
