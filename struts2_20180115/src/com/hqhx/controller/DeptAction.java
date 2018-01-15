package com.hqhx.controller;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport{

	public String addDept(){
		System.out.println("--添加部门----");
		int i=0;
		ServletActionContext.getRequest().setAttribute("msg", "你好");
		if(i>0){
			return SUCCESS;
		}else{
			return "failed";
		}
	}

	
	public String deleteDept(){
		System.out.println("--删除部门----");
		return SUCCESS;
	}
	
	public String updateDept(){
		System.out.println("--修改部门----");
		return SUCCESS;
	}
	
	public String findDeptByDeptno(){
		System.out.println("--根据部门编号查询部门----");
		return SUCCESS;
	}
	
	public String listDept(){
		System.out.println("--查询所有部门----");
		return SUCCESS;
	}
}
