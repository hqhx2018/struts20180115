package com.hqhx.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LogInterceptor implements Interceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("LogInterceptor：destroy");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("LogInterceptor：init");
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("LogInterceptor：intercept start");
		String r=arg0.invoke();
		System.out.println("LogInterceptor：intercept end");
		return r;
	}

}
