package com.zgl.spring.environment.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zgl
 * @date 2019/3/29 下午4:54
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContextParam) throws BeansException {
		applicationContext = applicationContextParam;
	}

	public static Object getObject(String id) {
		Object object = null;
		object = applicationContext.getBean(id);
		return object;
	}

	public static <T> T getObject(Class<T> tClass) {
		return applicationContext.getBean(tClass);
	}

	public static Object getBean(String tClass) {
		return applicationContext.getBean(tClass);
	}

	public static <T> T getBean(Class<T> tClass) {
		return applicationContext.getBean(tClass);
	}

}