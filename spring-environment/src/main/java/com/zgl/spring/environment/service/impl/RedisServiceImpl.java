package com.zgl.spring.environment.service.impl;

import com.zgl.spring.environment.service.RedisService;
import com.zgl.spring.environment.util.LogUtil;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zgl
 * @date 2019/3/28 下午3:35
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Resource
	private RedisTemplate redisTemplate;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void operate(String key, String value) {
		redisTemplate.opsForValue().set(key,value,60L,TimeUnit.MINUTES);
	}

	/**
	 * String 数据结构
	 */


	public void setString(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value, 60, TimeUnit.MINUTES);
	}

	public String getString(String key) {
		LogUtil.logger.error("获取到String数据结构的值是:{}", stringRedisTemplate.opsForValue().get(key));
		return stringRedisTemplate.opsForValue().get(key);
	}


	/**
	 * List 数据结构
	 */

	public void setList(String key, List<String> value) {
		stringRedisTemplate.opsForList().leftPushAll(key, value);
		LogUtil.logger.error("List的大小为:{}", stringRedisTemplate.opsForList().size(key));
		LogUtil.logger.error("遍历List:{}", stringRedisTemplate.opsForList().range(key, 0, -1));
	}

	public List<String> getList(String key) {
		return stringRedisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * Hash 数据结构
	 */

	@SuppressWarnings("unchecked")
	public void setHash(String key, Map<String, Integer> hash) {
		redisTemplate.opsForHash().putAll(key, hash);
		LogUtil.logger.error("hash的值为:{}", redisTemplate.opsForHash().entries(key).toString());
	}

	@SuppressWarnings("unchecked")
	public Object getHash(String key, String hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	/**
	 * Set 数据结构
	 */
	@SuppressWarnings("unchecked")
	public void setSet(String key, String[] value) {
		redisTemplate.opsForSet().add(key, value);
		LogUtil.logger.error("set大小为:{}", redisTemplate.opsForSet().size(key));
	}

	@SuppressWarnings("unchecked")
	public Object getSet(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * ZSet 数据结构
	 */
	@SuppressWarnings("unchecked")
	public void setZSet(String key, Map<String,Double> value) {
		Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
		Set<String>keySet=value.keySet();
		for(String k:keySet){
			ZSetOperations.TypedTuple<Object> objectTypedTuple = new DefaultTypedTuple<>(k, value.get(k));
			tuples.add(objectTypedTuple);
		}
		redisTemplate.opsForZSet().add(key, tuples);
		LogUtil.logger.error("zset排序:{}", redisTemplate.opsForZSet().range(key, 0, -1));
	}

	@SuppressWarnings("unchecked")
	public Object getZSet(String key, String item) {
		return redisTemplate.opsForZSet().rank(key, item);
	}

	/**
	 * 对象 object
	 */
	@SuppressWarnings("unchecked")
	public void setObject(String key ,Object value){
		redisTemplate.opsForValue().set(key, value);
	}
}