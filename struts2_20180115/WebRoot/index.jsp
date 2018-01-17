<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${sessionScope.basePath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<title>Insert title here</title>

</head>
<body>
当前在线人数：<span id="c"></span>
<br/>
<a href="dept/Dept_addDeptInput">添加部门</a>
<a href="dept/Dept_delete">删除</a>
<a href="dept/Dept_update">修改</a>
<a href="dept/Dept_findDeptById">按编号查询</a>
<a href="dept/Dept_listDept">查询所有部门</a>
<a href="user/User_add">添加用户</a>



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