package com.hqhx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class BasePathFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("--------BasePathFilter destroy-------");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("-------BasePathFilter-doFilter----start----------");
		HttpServletRequest request=(HttpServletRequest) req;
		String basePath=req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+request.getContextPath()+"/";
		request.getSession().setAttribute("basePath", basePath);
		chain.doFilter(req, resp);
		System.out.println("-------BasePathFilter---doFilter--end-----");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("--------LogFilter��ʼ��-------");
	}

}
