package com.zgl.spring.environment.aop;

import com.zgl.spring.environment.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author zgl
 * @date 2019/3/28 上午10:13
 */
public class LogAspect {

	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		LogUtil.logger.info("前置通知：The method {}, begins with:{},类名:{}",methodName,args,joinpoint.getClass().getName());
	}

	public void afterMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object>args = Arrays.asList(joinpoint.getArgs());
		LogUtil.logger.info("后置通知：The method {}, ends",methodName);
	}

	public void afterReturning(JoinPoint joinpoint, Object result) {
		String methodName = joinpoint.getSignature().getName();
		LogUtil.logger.info("返回通知：The method:{}, ends with{}",methodName,result);
	}

	public void afterThrowing(JoinPoint joinpoint, Exception e) {
		String methodName = joinpoint.getSignature().getName();
		LogUtil.logger.info("异常通知：The method:{},occurs exception:{}",methodName,e);
	}

	public Object aroundMethod(ProceedingJoinPoint point) {
		Object result = null;
		String methodName = point.getSignature().getName();
		try {
			//执行目标方法
			result = point.proceed();
		} catch (Throwable e) {
			//异常通知
			System.out.println("The method " + methodName + " occurs exception " + e);
			throw new RuntimeException(e);
		}
		//后置通知
		LogUtil.logger.info("环绕通知");
		return result;
	}
}