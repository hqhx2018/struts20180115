package com.hqhx.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hqhx.dao.DeptDao;
import com.hqhx.model.Dept;
import com.hqhx.model.Pager;
import com.hqhx.util.DBHelper;
import com.hqhx.util.HibernateUtil;

public class DeptDaoImpl implements DeptDao{	
	
	private DBHelper db=new DBHelper();
	private Transaction ts=null;
	//获取session
	public Session getSession(){
		//sessionFactory:
		/**
		 * getCurrentSession():获取当前线程的session,如果没有则开启一个新的session
		 * openSession():开启一个新的session
		 */
		SessionFactory sf=HibernateUtil.getSessionFactory();
		Session session=sf.openSession();
		ts=session.beginTransaction();
		return session;
	}
	
	@Override
	public void addDept(Dept dept) {
		Session session=getSession();
		session.save(dept);
		ts.commit();
		session.close();
	}

	@Override
	public void deleteDeptById(Dept dept) {
		Session session=getSession();
		session.delete(dept);
		ts.commit();
		session.close();
	}

	@Override
	public void updateDept(Dept dept) {
		Session session=getSession();
		session.update(dept);
		ts.commit();
		session.close();
	}

	@Override
	public List<Dept> listDept() {
		Session session=getSession();
		//标准化查询
		Criteria c=session.createCriteria(Dept.class);
		List<Dept> depts=c.list();
		session.close();
		return depts;
	}

	@Override
	public Dept findDeptById(Integer deptno) {
		Session session=getSession();
		Query q=session.createQuery("from Dept where deptno=?");
		q.setParameter(0, deptno);
		List<Dept> depts=q.list();
		if(depts.size()>0){
			return depts.get(0);
		}
		session.close();
		return null;
	}

	@Override
	public void listDeptByPager(Pager<Dept> pager) {
		Session session=getSession();
		Query query=session.createQuery("from Dept");
		//分页查询
		//设置偏移量
		query.setFirstResult(pager.getOffSet());
		//设置每页显示条数
		query.setMaxResults(pager.getPageSize());
		
		List<Dept> depts=query.list();
		//把当前页数据设置到pager对象
		pager.setDatas(depts);
		session.close();
	}

	@Override
	public Long getTotalCount() {
		Session session=getSession();
		Query q=session.createQuery("select count(deptno) from Dept");
		//如果当获取的数据是一个唯一的值
		Long totalCount=(Long) q.uniqueResult();
		session.close();
		return totalCount;
	}
}
