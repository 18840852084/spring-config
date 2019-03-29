package com.zgl.spring.environment.service.impl;

import com.zgl.spring.environment.domain.User;
import com.zgl.spring.environment.mapper.UserMapper;
import com.zgl.spring.environment.service.MysqlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zgl
 * @date 2019/3/28 下午6:14
 */
@Service
//@Transactional
public class MysqlServiceImpl implements MysqlService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User queryUserByName(String name) {
		User user = new User();
		user.setName(name);
		return userMapper.selectOne(user);
	}
}