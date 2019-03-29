package com.zgl.spring.environment.controller;

import com.zgl.spring.environment.domain.User;
import com.zgl.spring.environment.service.HelloService;
import com.zgl.spring.environment.service.MysqlService;
import com.zgl.spring.environment.service.RedisService;
import com.zgl.spring.environment.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zgl
 * @date 2019/3/27 下午6:02
 */
@RestController
@RequestMapping("/spring")
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private HelloService helloService;

	@Resource
	private RedisService redisService;

	@Resource
	private MysqlService mysqlService;

	@GetMapping("/hello")
	public String helloSpring(@RequestParam String str) {
		return helloService.helloSpring(str);
	}

	@GetMapping("/redis")
	public void operateRedis(@RequestParam String key,@RequestParam String value){
		redisService.operate(key, value);
	}

	@GetMapping("/mysql")
	public User queryUser(@RequestParam String name){
		return mysqlService.queryUserByName(name);
	}

	@GetMapping("/rabbit")
	public String queryRabbitInfo(){
		logger.info("connectionFactory信息:{}",SpringContextUtil.getBean("connectionFactory").toString());
		return "yeah!";
	}
}