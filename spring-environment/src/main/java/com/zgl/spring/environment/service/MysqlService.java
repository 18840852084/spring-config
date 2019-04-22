package com.zgl.spring.environment.service;

import com.zgl.spring.environment.domain.User;

/**
 * @author zgl
 * @date 2019/3/28 下午6:12
 */
public interface MysqlService {
	User queryUserByName(String name);

	void batchInsert();
}