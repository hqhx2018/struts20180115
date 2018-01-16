package com.hqhx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hqhx.model.User;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		StringBuffer url=req.getRequestURL();
		System.out.println(url);
		//http://localhost:8080/EL_JSTL20180105/login.jsp
		
		String uri=url.substring(url.lastIndexOf("/")+1);
		System.out.println("----------------------");
		System.out.println(uri);
		String m=req.getParameter("m");
		if(uri.equals("jquery-1.9.1.js")||uri.equals("untitled.bmp")||uri.equals("")||uri.equals("login.jsp")||(uri.equals("user")&&m.equals("login"))||(uri.equals("user")&&m.equals("createImage"))||(uri.equals("user")&&m.equals("getCount"))){
			//�����ʵ�¼ҳ����ߵ�¼ʱ��������
			chain.doFilter(req, response);
		}else{
			//�ж��û��Ƿ��¼�ˣ�
			HttpSession session=req.getSession();
			User user=(User) session.getAttribute("user");
			if(user!=null){
				//�����¼�˾ͼ�������Ŀ����Դ
				chain.doFilter(req, response);
			}else{
				//���û�е�¼����Ӧ����¼ҳ��
				//��Ӧ����¼ҳ�棬������ʾ�û��ȵ�¼
				String msg="���ȵ�¼";
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("login.jsp").forward(req, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}
