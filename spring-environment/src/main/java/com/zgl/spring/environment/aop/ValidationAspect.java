package com.zgl.spring.environment.aop;

import com.zgl.spring.environment.util.LogUtil;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * @author zgl
 * @date 2019/3/28 上午10:28
 */
public class ValidationAspect {

	public void validateArgs(JoinPoint joinPoint) {
		LogUtil.logger.info("validate :{}",Arrays.asList(joinPoint.getArgs()));
	}
}