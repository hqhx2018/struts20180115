package com.hqhx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hqhx.dao.DeptDao;
import com.hqhx.dao.EmpDao;
import com.hqhx.model.Dept;
import com.hqhx.model.Emp;
import com.hqhx.model.Pager;
import com.hqhx.util.DBHelper;

public class EmpDaoImpl implements EmpDao{
	private DBHelper db=new DBHelper();
	private DeptDao deptDao=new DeptDaoImpl();
	
	
	@Override
	public int addEmp(Emp emp) {
		String sql="insert into emp (empno,ename,sex,job,mgr,salary,hiredate,deptno) values (?,?,?,?,?,?,?,?)";
		int i=db.CUD(sql, emp.getEmpno(),emp.getEname(),emp.getSex(),emp.getJob(),emp.getMgr(),emp.getSalary(),emp.getHiredate(),emp.getDept().getDeptno());
		return i;
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
		List<Emp> emps=new ArrayList<Emp>();
		String sql="select empno,ename,sex,job,mgr,salary,hiredate,deptno from emp";
		Connection conn=db.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				int empno=rs.getInt(1);
				String ename=rs.getString(2);
				String sex=rs.getString(3);
				String job=rs.getString(4);
				int mgr=rs.getInt(5);
				Double salary=rs.getDouble(6);
				Date hiredate=rs.getDate(7);
				int deptno=rs.getInt(8);
				//根据部门编号查询部门对象
				Dept dept=deptDao.findDeptById(deptno);
				//构建员工对象
				Emp emp=new Emp(empno,ename,sex,job,mgr,salary,dept,hiredate);
				emps.add(emp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close(rs, ps, conn);
		}
		return emps;
	}

	@Override
	public void listEmpByPager(Pager<Emp> pager) {
		// TODO Auto-generated method stub
		
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
