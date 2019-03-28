package com.zgl.spring.environment.service;

/**
 * @author zgl
 * @date 2019/3/28 下午3:35
 */
public interface RedisService {

	void operate(String key,String value);
}
