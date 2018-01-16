package com.hqhx.dao;

import java.util.List;

import com.hqhx.model.Dept;
import com.hqhx.model.Pager;

public interface DeptDao {

	/**
	 * ��Ӳ�����Ϣ
	 * @param dept�����Ŷ���
	 * @return���������� >0��ʾ��ӳɹ��������ʾ���ʧ��
	 */
	public int addDept(Dept dept);

	/**
	 * ɾ����
	 * @param deptno��Ҫɾ��Ĳ��ű��
	 * @return���������� >0��ʾɾ��ɹ��������ʾɾ��ʧ��
	 */
	public int deleteDeptById(Integer deptno);
	/**
	 * �޸Ĳ�����Ϣ
	 * @param dept���µĲ�����Ϣ
	 * @return���������� >0��ʾ�޸ĳɹ��������ʾ�޸�ʧ��
	 */
	public int updateDept(Dept dept);
	
	
	/**
	 * ��ѯ���в�����Ϣ
	 * @return���������в�����Ϣ
	 */
	public List<Dept> listDept();
	
	
	/**
	 * ��ҳ��ѯ���в�����Ϣ
	 * @return��
	 */
	public void listDeptByPager(Pager<Dept> pager);
	
	/**
	 * ��ݱ�Ų�ѯ���Ŷ���
	 * @param deptno��Ҫ��ѯ�Ĳ��ŵı��
	 * @return:���ز�ѯ���Ĳ��Ŷ���
	 */
	public Dept findDeptById(Integer deptno);
	
	/**
	 * ��ȡ�ܲ��Ÿ���
	 * @return
	 */
	public Long getTotalCount();
}
