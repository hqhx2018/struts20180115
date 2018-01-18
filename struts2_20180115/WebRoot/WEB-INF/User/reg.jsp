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
<h1>用户注册</h1>
${msg}
<form action="login/reg" method="post" enctype="multipart/form-data">
<s:token></s:token>
<table>
<tr>
<td>用户名:</td>
<td><input type="text" name="username"/></td>
</tr>
<tr>
<td>密码:</td>
<td><input type="password" name="password"/></td>
</tr>
<tr>
<td>头像:</td>
<td>
<input type="file" name="img"/></td>
</tr>
<tr>
<td><input type="submit" value="注册"/></td>
<td><input type="reset" value="重置"/></td>
</tr>
</table>
</form>
</body>
</html>