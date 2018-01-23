package com.hqhx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.hqhx.model.Dept;
import com.hqhx.model.Pager;
import com.hqhx.service.DeptService;
import com.hqhx.service.impl.DeptServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DeptAction extends BaseAction implements ModelDriven<Dept>{

	
	private Dept dept;
	private Pager<Dept> pager;
	private DeptService deptService=new DeptServiceImpl();
	private String downloadFile;
	private InputStream in=null;
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}
	public Pager<Dept> getPager() {
		return pager;
	}
	public void setPager(Pager<Dept> pager) {
		this.pager = pager;
	}
	
	
	
	public String addDeptInput(){
		
		return "addDeptInput";
	}
	
	public String add(){
		System.out.println("--添加部门----");
		try{
			deptService.addDept(dept);
			return SUCCESS;
		}catch(Exception e){
			return "failed";
		}
		
	}

	//转跳到addDept.jsp页面
	public String addInput(){
		return "addDept";
	}
	
	public String delete(){
		try{
		deptService.deleteDeptById(dept);
		return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}

	}
	
	public String update(){
		System.out.println("--修改部门----");
		try{
			deptService.updateDept(dept);
			return SUCCESS;
		}catch(Exception e){
			return "failed";
		}
		
	}
	
	public String findDeptById(){
		System.out.println("--根据部门编号查询部门----");
		dept=deptService.findDeptById(dept.getDeptno());
		return SUCCESS;
	}
	
	public String listDept(){
		System.out.println("--查询所有部门----");
		List<Dept> depts=deptService.listDept();
		return SUCCESS;
	}
	
	
	
	//分页查询所有部门
		public String listDeptByPager() {
			if(pager==null){
				pager=new Pager<Dept>();
			}
			deptService.listDeptByPager(pager);
			return "listDeptByPager";
		}
	
	//导出excel
	public String exportExcel(){
		File f=deptService.exportExcel();
		
		try {
			in = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public InputStream getExcel(){
		
		return in;
	}
	/**
	 * <interceptor-ref name="modelDriven"/>功能，是把
	 * getModel()方法返回的对象，放在值栈的栈顶
	 */
	@Override
	public Dept getModel() {
		if(dept==null){
			dept=new Dept();
		}
		return dept;
	}
	








}
