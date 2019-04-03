package com.zgl.spring.environment.interceptor;


import com.zgl.spring.environment.aop.Action;
import com.zgl.spring.environment.util.LogUtil;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zgl
 * @date 2019/3/27 下午4:46
 */

public class AccessLogHandler implements HandlerInterceptor {

	@Action
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long currentTime = System.currentTimeMillis();
		request.setAttribute("Current-Time", currentTime);
		LogUtil.logger.error("{} {}", request.getMethod(), request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
		long currentTime = (long)request.getAttribute("Current-Time");
		LogUtil.logger.error("{} has finished in {}ms", request.getRequestURI(), System.currentTimeMillis() - currentTime);
	}
}