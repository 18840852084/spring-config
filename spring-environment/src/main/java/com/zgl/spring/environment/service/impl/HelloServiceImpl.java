package com.zgl.spring.environment.service.impl;

import com.zgl.spring.environment.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author zgl
 * @date 2019/3/28 上午11:49
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String helloSpring(String str) {
		return str;
	}
}