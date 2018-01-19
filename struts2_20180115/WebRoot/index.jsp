<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${sessionScope.basePath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<title>Insert title here</title>

</head>
<body>
欢迎${sessionScope.user.username} <img width="50" height="50" src="../upload/${sessionScope.user.imgFileName}"/>


当前在线人数：<span id="c"></span>
<br/>
<table>
<tr>
<td><a href="dept/Dept_addDeptInput">添加部门</a></td>
</tr>
<tr>
<td><a href="dept/Dept_delete">删除</a></td>
</tr>
<tr>
<td><a href="dept/Dept_update">修改</a></td>
</tr>
<tr>
<td><a href="dept/Dept_findDeptById">按编号查询</a></td>
</tr>
<tr>
<td><a href="dept/Dept_listDept">查询所有部门</a></td>
</tr>
<tr>
<td><a href="dept/Dept_listDeptByPager">分页查询所有部门</a></td>
</tr>
<tr>
<td><a href="user/User_add">添加用户</a></td>
</tr>
<tr>
<td><a href="download/2017.tar.gz">下载tar.gz</a></td>
</tr>
<tr>
<td><a href="download/0115.png">下载图片</a></td>
</tr>
<tr>
<td><a href="download/20180118.txt">下载txt</a></td>
</tr>
<tr>
<td><a href="emp/Emp_addEmpInput">添加员工</a></td>
</tr>
<tr>
<td><a href="emp/Emp_listEmp">员工管理</a></td>
</tr>
</table>
<script type="text/javascript">
window.setInterval(function(){
	$.ajax({
		type:"get",
		url:"login/getCount",
		success:function(msg){
			$("#c").text(msg);
		}});
}, 1000)
</script>
</body>
</html>