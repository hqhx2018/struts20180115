package com.hqhx.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener,HttpSessionAttributeListener{

	//Ԥ������򣺵�session������ʱ���ø÷���
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session��������:"+se.getSession().getId());
		//��ȡapplication
		ServletContext application=se.getSession().getServletContext();
		//��application��Χ��ȡ�����ı���
		Integer c=(Integer) application.getAttribute("count");
		if(c==null){
			//���cΪnull˵����ǰ�û��ǵ�һ�����ʸ���վ����
			application.setAttribute("count", 1);
		}else{
			application.setAttribute("count", c+1);
		}
	}
	//Ԥ������򣺵�session������ʱ���ø÷���
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session��������:"+se.getSession().getId());
		//��ȡapplication
		ServletContext application=se.getSession().getServletContext();
		//��application��Χ��ȡ�����ı���
		Integer c=(Integer) application.getAttribute("count");
		if(c!=null){
			application.setAttribute("count", c-1);
		}
	}
	//����session��������Ժ�͵��ø÷���
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}
	//����session���Ƴ����Ժ�͵��ø÷���
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}
	//����session���޸����Ժ�͵��ø÷���
	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

}
