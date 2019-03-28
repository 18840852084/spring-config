package com.zgl.spring.environment.service.impl;

import com.zgl.spring.environment.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zgl
 * @date 2019/3/28 下午3:35
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Resource
	private RedisTemplate redisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void operate(String key, String value) {
		redisTemplate.opsForValue().set(key,value,60L,TimeUnit.MINUTES);
	}
}