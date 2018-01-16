package com.hqhx.model;

import java.util.List;

public class Pager<T> {

	private Integer currentPage=1;//��ǰҳ
	private Integer offSet;//ƫ����
	private Integer pageSize=3;//ÿҳ��ʾ����
	private List<T> datas;//��ǰҳ������
	private Long totalCount;//������
	private Integer totalPage;//��ҳ��
	private Integer previousPage;//��һҳ
	private Integer nextPage;//��һҳ
	private Integer startPage;//��ʼҳ��
	private Integer endPage;//����ҳ��
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	//���㲢����ƫ����
	public Integer getOffSet() {
		offSet=(getCurrentPage()-1)*getPageSize();
		return offSet;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	//��ȡ������
	public Long getTotalCount() {
		return totalCount;
	}
	//����������
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	//��ȡ��ҳ��
	public Integer getTotalPage() {
		totalPage=(int) Math.ceil(getTotalCount()*1.0/getPageSize());
		return totalPage;
	}
	
	
	
	
	
	
	//��ȡ��һҳ
	public Integer getPreviousPage() {
		//�Ƿ�����һҳ
		if(hasPreviousPage()){
			previousPage=getCurrentPage()-1;
		}else{
			previousPage=1;
		}
		return previousPage;
	}
	
	
	//��ȡ��һҳ
	public Integer getNextPage() {
		//�Ƿ�����һҳ
		if(hasNextPage()){
			nextPage=getCurrentPage()+1;
		}else{
			nextPage=getTotalPage();
		}
		return nextPage;
	}
	
	//�ж��Ƿ�����һҳ
	public boolean hasPreviousPage(){
		if(getCurrentPage()-1>0){
			return true;
		}else{
			return false;
		}
	}
	

	//�ж��Ƿ�����һҳ
	public boolean hasNextPage(){
		if(getCurrentPage()+1>getTotalPage()){
			return false;
		}else{
			return true;
		}
	}
	//���㿪ʼҳ��
	public Integer getStartPage() {
		if(getCurrentPage()>=1&&getCurrentPage()<=5){
			startPage=1;
		}else if(getCurrentPage()>=getTotalPage()-4&&getCurrentPage()<=getTotalPage()){
			startPage=getTotalPage()-4;
		}else{
			startPage=getCurrentPage()-2;
		}
		return startPage;
	}
	//�������ҳ��
	public Integer getEndPage() {
		if(getCurrentPage()>=1&&getCurrentPage()<=5){
			if(getTotalPage()>5){
				endPage=5;
			}else{
				endPage=getTotalPage();
			}
		}else if(getCurrentPage()>=getTotalPage()-4&&getCurrentPage()<=getTotalPage()){
			endPage=getTotalPage();
		}else{
			endPage=getCurrentPage()+2;
		}
		return endPage;
	}	
}
