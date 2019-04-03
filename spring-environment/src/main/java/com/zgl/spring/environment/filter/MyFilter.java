package com.zgl.spring.environment.filter;

import com.zgl.spring.environment.util.LogUtil;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zgl
 * @date 2019/3/28 下午12:07
 */
public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		LogUtil.logger.info("过滤器执行+++++++++++++++++++,{}",this.getClass().getName());
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}