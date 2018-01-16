package com.hqhx.controller;

import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	
	public String add(){
		//获取action上下文对象
		ActionContext actionContext=ActionContext.getContext();
		//给Action上下文设置值
		actionContext.put("actionContext", "123456");
		//获取session
		Map<String,Object> session=actionContext.getSession();
		session.put("session", "session456789");
		//获取application
		Map<String,Object> application=actionContext.getApplication();
		application.put("application", "application456789");
		
		Map<String,Object> param=actionContext.getParameters();
		Set<String> keys=param.keySet();
		for (String key : keys) {
			System.out.println("key="+key+" value="+param.get(key));
			Object[] os=(Object[]) param.get(key);
			for(Object o:os){
				System.out.println(o);
			}
		}
		System.out.println("------添加用户-----");
		return "success";
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	
}
