<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">


</style>
</head>
<body>

<s:debug></s:debug>




<h1 align="center">员工添加</h1>
<table align="center">
<tr>
<td>
<s:form action="emp/Emp_addEmp" method="post">
<s:textfield label="员工编号" labelposition="left" name="empno" ></s:textfield>
<s:textfield label="员工姓名" labelposition="left" name="ename" ></s:textfield>
<!-- {'男','女'}是使用OGNL表达式定义的list -->
<s:radio list="{'男','女'}" label="性别" name="sex"></s:radio>
<s:textfield label="工作" labelposition="left" name="job" ></s:textfield>
<s:textfield label="上司" labelposition="left" name="mgr" ></s:textfield>
<s:textfield label="薪资" labelposition="left" name="salary" ></s:textfield>
<s:textfield label="入职时间" labelposition="left" name="hiredate" ></s:textfield>
<s:select list="depts" listKey="deptno" listValue="dname" name="dept.deptno" label="所在部门" labelposition="left"></s:select>
<s:submit value="提交"></s:submit>
<s:reset value="重置"></s:reset>
</s:form>
</td>
</tr>
</table>
</body>
</html>