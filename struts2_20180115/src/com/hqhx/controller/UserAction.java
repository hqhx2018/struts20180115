package com.hqhx.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.hqhx.model.User;
import com.hqhx.service.UserService;
import com.hqhx.service.impl.UserServiceImpl;
import com.hqhx.util.CreateImage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user;
	private UserService userService=new UserServiceImpl();
	private String code;
	private String isM;
	private String msg;

//	@Override
//	public void validate() {
//		System.out.println("-----UserAction validate---------");
//	}
	
	
//	//校验reg方法
//	public void validateReg(){
//		System.out.println("-----UserAction validateReg-----");
//		if(user!=null){
//			if(user.getUsername()==null||user.getUsername().equals("")){
//				//校验不通过
//				super.addFieldError("username", "用户名不能为空");
//			}else{
//				//用户名必须由字母数字_组成，必须6-8位
//				String reg="^[a-zA-Z0-9_]{6,8}$";
//				boolean b=user.getUsername().matches(reg);
//				if(!b){
//					//校验不通过
//					super.addFieldError("username", "用户名必须由字母数字_组成长度为6-8位");
//				}
//			}
//		}
//	}

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
	//跳转到注册页面
	public String regInput(){
		return "regInput";
	}
	
	
	//注册
	public String reg(){
		//获取客户端传递过来的信息
		System.out.println(user);
		//1.把图片保存到服务器上
		//动态获取当前项目在服务器的真实路径
		String relpath=ServletActionContext.getRequest().getServletContext().getRealPath("../");
		//创建一个目录用于保存图片
		File upload=new File(relpath,"upload");
		System.out.println(upload);
		//判断目录是否存在，如果存在则直接把图片保存到该目录下，不存在则创建在保存
		if(!upload.exists()){
			upload.mkdir();
		}
		//为了防止重名文件被覆盖，要给文件动态生成一个唯一的名字
		String newFileName=UUID.randomUUID().toString()+user.getImgFileName().substring(user.getImgFileName().lastIndexOf("."));
		//构建一个新的文件，并把源文件的数据复制到一个新文件中
		File newFile=new File(upload,newFileName);
		//把文件上传到upload目录中
		try {
			FileUtils.copyFile(user.getImg(), newFile);
			//用新文件的名字改掉旧的名字
			user.setImgFileName(newFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i=userService.reg(user);
		if(i>0){
			//2.把图片的名字保存到数据库中
			return "loginInput";
		}else{
			msg="注册失败，请重新注册";
			return "regInput";
		}
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
