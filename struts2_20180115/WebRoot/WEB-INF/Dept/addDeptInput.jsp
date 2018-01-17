<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${sessionScope.basePath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">添加部门</h1>
<s:debug></s:debug>
<center>
<%-- <%
String msg=(String)request.getAttribute("msg"); 
if(msg!=null){
out.print(msg);
}
%> --%>
${requestScope.msg}
</center>
<form action="dept/Dept_add" method="post">
<s:token></s:token>
<table border="1" cellpadding="0" align="center" cellspacing="0">
<tr>
<td>部门编号:</td>
<td><input type="text" name="deptno"/></td>
</tr>
<tr>
<td>部门名称:</td>
<td><input type="text" name="dname"/></td>
</tr>
<tr>
<td>部门地址:</td>
<td><input type="text" name="loc"/></td>
</tr>
<tr>
<td><input type="submit" value="提交"/></td>
<td><input type="reset" value="重置"/></td>
</tr>
</table>
</form>
</body>
</html>