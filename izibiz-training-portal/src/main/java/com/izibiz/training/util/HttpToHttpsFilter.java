package com.izibiz.training.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpToHttpsFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		servletRequest.setCharacterEncoding("UTF-8");
		filterChain.doFilter(servletRequest, new HttpHeaderRedirect((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse));

		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}



}
