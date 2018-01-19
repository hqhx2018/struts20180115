package com.hqhx.service;

import java.util.List;

import com.hqhx.model.Emp;
import com.hqhx.model.Pager;

public interface EmpService {

	
		/**
		 * 添加员工信息
		 * @param emp：员工对象
		 * @return：返回整数 >0表示添加成功，否则表示添加失败
		 */
		public int addEmp(Emp emp);

		/**
		 * 删除员工
		 * @param empno：要删除的员工编号
		 * @return：返回整数 >0表示删除成功，否则表示删除失败
		 */
		public int deleteEmpById(Integer empno);
		/**
		 * 修改员工信息
		 * @param emp：新的员工信息
		 * @return：返回整数 >0表示修改成功，否则表示修改失败
		 */
		public int updateEmp(Emp emp);
		
		
		/**
		 * 查询所有员工信息
		 * @return：返回所有员工信息
		 */
		public List<Emp> listEmp();
		
		
		/**
		 * 分页查询所有员工信息
		 * @return：
		 */
		public void listEmpByPager(Pager<Emp> pager);
		
		/**
		 * 根据编号查询员工对象
		 * @param empno：要查询的员工的编号
		 * @return:返回查询到的员工对象
		 */
		public Emp findEmpById(Integer empno);
		
		/**
		 * 获取总员工个数
		 * @return
		 */
		public Long getTotalCount();
		

}
