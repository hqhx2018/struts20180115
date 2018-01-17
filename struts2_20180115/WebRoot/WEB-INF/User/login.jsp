<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${sessionScope.basePath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
</head>
<body>
<s:debug></s:debug>
<h1 align="center">登录页面</h1>
<form action="login/login" method="post">
<s:token></s:token>
<table align="center">
<tr>
<td colspan="2"><font color='red'>${requestScope.msg}</font></td>
</tr>
<tr>
<td>用户名：</td>
<td><input id="input1" type="text" name="username"/></td>
</tr>
<tr>
<td>密码：</td>
<td><input id="input2"  type="password" name="password"/></td>
</tr>
<tr>
<td>验证码：</td>
<td><input type="text" name="code"/><img onclick="this.src='login/createImage?a='+Math.random()" src="login/createImage" /></td>
</tr>
<tr>
<td><input type="checkbox" value="1" name="isM"/></td>
<td>记住密码</td>
</tr>
<tr>
<td><input type="submit"  value="登录"/></td>
<td><input type="reset"  value="重置"/></td>
</tr>
</table>
</form>
<script type="text/javascript">
document.getElementById("input1").value=decodeURIComponent('${cookie.username.value}');
document.getElementById("input2").value=decodeURIComponent('${cookie.password.value}');
</script>
</body>
</html>