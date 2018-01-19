package com.hqhx.service.impl;

import java.util.List;

import com.hqhx.dao.EmpDao;
import com.hqhx.dao.impl.EmpDaoImpl;
import com.hqhx.model.Emp;
import com.hqhx.model.Pager;
import com.hqhx.service.EmpService;

public class EmpServiceImpl implements EmpService{

	private EmpDao empDao=new EmpDaoImpl();
	
	@Override
	public int addEmp(Emp emp) {
		// TODO Auto-generated method stub
		int i=empDao.addEmp(emp);
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
		// TODO Auto-generated method stub
		return empDao.listEmp();
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
