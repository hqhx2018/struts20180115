package com.hqhx.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hqhx.model.User;
import com.hqhx.service.UserService;
import com.hqhx.service.impl.UserServiceImpl;
import com.hqhx.util.CreateImage;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user;
	private UserService userService=new UserServiceImpl();
	private String code;
	private String isM;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsM() {
		return isM;
	}

	public void setIsM(String isM) {
		this.isM = isM;
	}

	public String loginInput(){
		System.out.println("----------------------------");
		return "loginInput";
	}
	
	
	//获取当前在线人数
		public String getCount() {
			HttpServletRequest req=ServletActionContext.getRequest();
			HttpServletResponse resp=ServletActionContext.getResponse();
			Integer c=(Integer) req.getServletContext().getAttribute("count");
			PrintWriter out=null;
			try {
				out=resp.getWriter();
				if(c==null){
					out.write(0);
				}else{
					out.write(c.toString());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
					out.close();
			}
			return null;
		}
	
	//获取验证码
	public String createImage() {
		System.out.println("****************");
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse resp=ServletActionContext.getResponse();
		//设置响应的数据类型
		resp.setContentType("image/jpg");
		CreateImage c=new CreateImage();
		//获取图片
		BufferedImage img=c.getImage();
		String trueCode=c.getCode();
		req.getSession().setAttribute("trueCode", trueCode);
		//把code存储到session中
		OutputStream os=null;
		//获取输出流
		try {
			os=resp.getOutputStream();
			ImageIO.write(img, "JPEG", os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	
	
	//登录
	public String login() throws UnsupportedEncodingException{
		User u=userService.login(user.getUsername());
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse resp=ServletActionContext.getResponse();
		//获取真实的验证码
				String trueCode=(String)req.getSession().getAttribute("trueCode");	
				if(u==null){
					//登录失败，提示用户用户名错误，请重新登录
					msg="用户用户名错误，请重新登录";
					//请求转发到login.jsp
					return "loginInput";
				}else{
					//用户名正确，验证密码是否正确
					if(user.getPassword().equals(u.getPassword())){
						//判断验证码是否正确
						if(code.equalsIgnoreCase(trueCode)){					
							//密码正确，登录成功
							//获取session
							HttpSession session=req.getSession();
							//把当前用户存储到session中
							session.setAttribute("user", u);
							//把用户名和密码响应给客户端，让客户端把用户名和密码存储到客户端的Cookie文件中
							Cookie uname=new Cookie("username",URLEncoder.encode(user.getUsername(),"UTF-8"));
							Cookie psw=new Cookie("password",user.getPassword());
							if("1".equals(isM)){
								//设置用户名和密码在cookie文件中的保存时间
								uname.setMaxAge(7*24*60*60*1000);
								psw.setMaxAge(7*24*60*60*1000);
							}else{
								uname.setMaxAge(0);
								psw.setMaxAge(0);
							}
							resp.addCookie(uname);
							resp.addCookie(psw);
							return "index";
						}else{
							//登录失败，提示验证码错误，请重新登录
							msg="验证码错误，请重新登录";
							//请求转发到login.jsp
							return "loginInput";
						}
					}else{
						//登录失败，提示用户密码错误，请重新登录
						msg="密码错误，请重新登录";
						//请求转发到login.jsp
						return "loginInput";
					}
				}
	}
	
	
	
	public String add(){
		//获取action上下文对象
		ActionContext actionContext=ActionContext.getContext();
		//给Action上下文设置值
		actionContext.put("actionContext", "123456");
		//获取session
		Map<String,Object> session=actionContext.getSession();
		session.put("session", "session456789");
		//获取application
		Map<String,Object> application=actionContext.getApplication();
		application.put("application", "application456789");
		
		Map<String,Object> param=actionContext.getParameters();
		Set<String> keys=param.keySet();
		for (String key : keys) {
			System.out.println("key="+key+" value="+param.get(key));
			Object[] os=(Object[]) param.get(key);
			for(Object o:os){
				System.out.println(o);
			}
		}
		System.out.println("------添加用户-----");
		return "success";
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	@Override
	public User getModel() {
		if(user==null){
			user=new User();
		}
		return user;
	}

	
}
