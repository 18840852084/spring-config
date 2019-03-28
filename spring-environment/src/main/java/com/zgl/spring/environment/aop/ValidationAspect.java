package com.zgl.spring.environment.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author zgl
 * @date 2019/3/28 上午10:28
 */
public class ValidationAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	public void validateArgs(JoinPoint joinPoint) {
		logger.info("validate :{}",Arrays.asList(joinPoint.getArgs()));
	}
}