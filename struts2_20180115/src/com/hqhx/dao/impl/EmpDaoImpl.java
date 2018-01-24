package com.hqhx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hqhx.dao.DeptDao;
import com.hqhx.dao.EmpDao;
import com.hqhx.model.Dept;
import com.hqhx.model.Emp;
import com.hqhx.model.Pager;
import com.hqhx.util.DBHelper;
import com.hqhx.util.HibernateUtil;

public class EmpDaoImpl implements EmpDao{
	private DBHelper db=new DBHelper();
	private DeptDao deptDao=new DeptDaoImpl();
	
	
	public Session getSession(){
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	
	
	@Override
	public void addEmp(Emp emp) {
		Session session=getSession();
		session.save(emp);
		session.beginTransaction().commit();
	}

	@Override
	public int deleteEmpById(Integer empno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmp(Emp emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Emp> listEmp() {
		Session session=getSession();
		Query query=session.createQuery("from Emp");
		List<Emp> emps=query.list();
		session.close();
		return emps;
	}

	@Override
	public void listEmpByPager(Pager<Emp> pager) {
		
	}

	@Override
	public Emp findEmpById(Integer empno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
