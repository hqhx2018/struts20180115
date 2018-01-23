package com.hqhx.service;

import java.io.File;
import java.util.List;

import com.hqhx.model.Dept;
import com.hqhx.model.Pager;

public interface DeptService{
	/**
	 * ��Ӳ�����Ϣ
	 * @param dept�����Ŷ���
	 * @return���������� >0��ʾ��ӳɹ��������ʾ���ʧ��
	 */
	public void addDept(Dept dept);

	/**
	 * ɾ����
	 * @param deptno��Ҫɾ��Ĳ��ű��
	 * @return���������� >0��ʾɾ��ɹ��������ʾɾ��ʧ��
	 */
	public void deleteDeptById(Dept dept);
	/**
	 * �޸Ĳ�����Ϣ
	 * @param dept���µĲ�����Ϣ
	 * @return���������� >0��ʾ�޸ĳɹ��������ʾ�޸�ʧ��
	 */
	public void updateDept(Dept dept);
	
	
	/**
	 * ��ѯ���в�����Ϣ
	 * @return���������в�����Ϣ
	 */
	public List<Dept> listDept();
	
	//导出excel
	public File exportExcel();
	
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
}
