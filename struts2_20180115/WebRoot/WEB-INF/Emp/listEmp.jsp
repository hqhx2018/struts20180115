<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
</head>
<body>
<h1 align="center">员工管理</h1>
<s:debug></s:debug>
<!-- 通过ognl表达式调用静态方法 -->
<s:property value="@com.hqhx.controller.EmpAction@aa('wwww')" default="ssssss"/>
<!-- 通过ognl表达式获取session中用户对象的用户名 -->
<s:property value="#session.user.username"/>
<!-- 使用ognl表达式结合struts标签创建一个list设置到session范围 -->
<s:set name="a" value="{'a','b','c'}" scope="session"></s:set>
<!-- 使用ognl表达式结合struts标签创建一个list设置到application范围 -->
<s:set name="b" value="#{1:'s',2:'a',3:'w'}" scope="application"></s:set>

<s:property value="#session.a"/>
<s:property value="#application.b"/>
<table border="1" cellpadding="0" cellspacing="0" align="center" width="80%">
<tr>
<td>员工编号</td>
<td>员工姓名</td>
<td>员工性别</td>
<td>职位</td>
<td>上司编号</td>
<td>薪水</td>
<td>入职日期</td>
<td>所在部门</td>
<td>所在部门的地址</td>
</tr>

<s:iterator var="emp" value="emps.{$#this.salary>=5000}">
<tr>
<td><s:property value="empno"/></td>
<td><s:property value="ename"/></td>
<td><s:property value="sex"/></td>
<td><s:property value="job"/></td>
<td><s:property value="mgr"/></td>
<td><s:property value="salary"/></td>
<td><s:property value="hiredate"/></td>
<td><s:property value="dept.dname"/></td>
<td><s:property value="dept.loc"/></td>
</tr>
</s:iterator>

</table>
</body>
</html>