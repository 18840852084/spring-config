package com.zgl.spring.environment.service.impl;

import com.zgl.spring.environment.domain.User;
import com.zgl.spring.environment.mapper.UserMapper;
import com.zgl.spring.environment.service.MysqlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author zgl
 * @date 2019/3/28 下午6:14
 */
@Service
public class MysqlServiceImpl implements MysqlService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User queryUserByName(String name) {
		User user = new User();
		user.setName(name);
		return userMapper.selectOne(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void batchInsert(){
		User user1 = new User("123","zyy","111","sadad",24,new Date(),"female");
		User user2 = new User("1234","zgl","111","sadad",25,new Date(),"male");
		User user3 = new User("12345","zyy2","111","sadad",26,new Date(),"female");
		userMapper.insert(user1);
		userMapper.insert(user2);
		//int num = 1/0;
		userMapper.insert(user3);
	}
}