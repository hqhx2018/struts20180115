package com.hqhx.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.hqhx.dao.DeptDao;
import com.hqhx.dao.impl.DeptDaoImpl;
import com.hqhx.model.Dept;
import com.hqhx.model.Pager;
import com.hqhx.service.DeptService;

public class DeptServiceImpl implements DeptService{

	private DeptDao deptDao=new DeptDaoImpl();
	
	@Override
	public void addDept(Dept dept) {
		if(dept!=null){
			deptDao.addDept(dept);
		}
		
	}

	@Override
	public void deleteDeptById(Dept dept) {
		if(dept!=null){
			deptDao.deleteDeptById(dept);
		}
	
	}

	@Override
	public void updateDept(Dept dept) {
		if(dept!=null){
			deptDao.updateDept(dept);
		}
		
	}

	@Override
	public List<Dept> listDept() {
		return deptDao.listDept();
	}
	
	
	

	@Override
	public Dept findDeptById(Integer deptno) {
		System.out.println("service---------->"+deptno);
		return deptDao.findDeptById(deptno);
	}

	@Override
	public void listDeptByPager(Pager<Dept> pager) {
		deptDao.listDeptByPager(pager);
		long totalCount=deptDao.getTotalCount();
		pager.setTotalCount(totalCount);
	}

	//导出excel
	@Override
	public File exportExcel() {
		List<Dept> depts=deptDao.listDept();
		
		String realPath=ServletActionContext.getRequest().getServletContext().getRealPath("/download");
		File f=new File(realPath,"dept.xls");
		WritableWorkbook workBook=null;
		try {
			//内存中构建一个excel文件
			workBook=Workbook.createWorkbook(f);
			WritableSheet[] sheets=workBook.getSheets();
			
//			for (WritableSheet sheet : sheets) {
//				System.out.println("-----***-----"+sheet);
//			}
			//获取excel文件中指定的一个工作表sheet
			WritableSheet sheet=workBook.createSheet("部门信息", 0);
			//给sheet表中写数据
			Label thead1=new Label(0, 0, "部门编号");
			Label thead2=new Label(1, 0, "部门名称");
			Label thead3=new Label(2, 0, "部门所在地");
			sheet.addCell(thead1);
			sheet.addCell(thead2);
			sheet.addCell(thead3);
			//写部门信息到excel表格中
			for(int i=0;i<depts.size();i++){
				Label tbody1=new Label(0, i+1, depts.get(i).getDeptno().toString());
				Label tbody2=new Label(1, i+1, depts.get(i).getDname());
				Label tbody3=new Label(2, i+1, depts.get(i).getLoc());
				sheet.addCell(tbody1);
				sheet.addCell(tbody2);
				sheet.addCell(tbody3);
			}
			workBook.write();
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				workBook.close();
			} catch (WriteException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}

}
