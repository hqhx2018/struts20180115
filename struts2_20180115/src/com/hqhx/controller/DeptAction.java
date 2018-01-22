package com.hqhx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

public class DeptAction extends ActionSupport implements ModelDriven<Dept>, ServletRequestAware,SessionAware,ServletContextAware,ServletResponseAware{

	private HttpServletRequest request;
	private Map<String,Object> session;
	private ServletContext application;
	private HttpServletResponse response;
	private Dept dept;
	private Pager<Dept> pager;
	private DeptService deptService=new DeptServiceImpl();
	private String downloadFile;
	private InputStream in=null;
	
	
	
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
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.application=arg0;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response=arg0;
	}
	
	
	
	public String addDeptInput(){
		
		return "addDeptInput";
	}
	
	public String add(){
		System.out.println("--添加部门----");
		System.out.println("要添加的部门信息"+dept.getDeptno()+" "+dept.getDname()+" "+dept.getLoc());
		int i=0;
		ServletActionContext.getRequest().setAttribute("msg", "你好");
		
		if(i>0){
			return SUCCESS;
		}else{
			return "failed";
		}
	}

	//转跳到addDept.jsp页面
	public String addInput(){
		return "addDept";
	}
	public String delete(){
		System.out.println("--删除部门----");
		String deptno=request.getParameter("deptno");
		System.out.println("要删除的部门编号为："+deptno);
		return SUCCESS;
	}
	
	public String update(){
		System.out.println("--修改部门----");
		return SUCCESS;
	}
	
	public String findDeptById(){
		System.out.println("--根据部门编号查询部门----");
		return SUCCESS;
	}
	
	public String listDept(){
		System.out.println("--查询所有部门----");
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
