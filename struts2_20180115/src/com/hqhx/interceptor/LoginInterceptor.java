package com.hqhx.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hqhx.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		//判断session中是否有用户信息
		HttpSession session=ServletActionContext.getRequest().getSession();
		User user=(User) session.getAttribute("user");
		if(user==null){
			ServletActionContext.getRequest().setAttribute("msg", "请先登录");
			return "loginInput";
		}else{
			return arg0.invoke();
		}
	}
}
