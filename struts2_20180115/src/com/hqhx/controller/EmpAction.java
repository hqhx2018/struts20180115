package com.hqhx.controller;


import java.util.List;

import com.hqhx.model.Dept;
import com.hqhx.model.Emp;
import com.hqhx.service.DeptService;
import com.hqhx.service.EmpService;
import com.hqhx.service.impl.DeptServiceImpl;
import com.hqhx.service.impl.EmpServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EmpAction extends BaseAction implements ModelDriven<Emp>{

	
	private EmpService empService=new EmpServiceImpl();
	private DeptService deptService=new DeptServiceImpl();
	private List<Dept> depts;
	private List<Emp> emps;
	private Emp emp;
	
	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public List<Emp> getEmps() {
		return emps;
	}

	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}

	//转跳到addEmp.jsp
	public String addEmpInput(){
		depts=deptService.listDept();
		return SUCCESS;
	}

	
	//添加员工
	public String addEmp(){
		System.out.println(emp);
		try{
			empService.addEmp(emp);
			return SUCCESS;
		}catch(Exception e){
			return INPUT;
		}
	}
	
	//查询所有员工
	public String listEmp(){
		emps=empService.listEmp();
		return SUCCESS;
	}

	@Override
	public Emp getModel() {
		if(emp==null){
			emp=new Emp();
		}
		return emp;
	}
	
	
	//静态方法
	public static String aa(String a){
		System.out.println("------静态方法aa---------");
		return "你好"+a;
	}
}
