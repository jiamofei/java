<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>欢迎您：${user.userName}，以下是您的信息，您可以对其进行修改：</p>
	<form action="UserUpdateServlet" method="post">
		<table>
			<tr>
				<td><input type="hidden" name="userId" value="${user.userId}"/></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" value="${user.userName}"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="text" name="password" value="${user.password}"/></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="email" value="${user.email}"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="修改"></td>
			</tr>
			<tr>
			<td colspan="2">
				<font color="red">
				${errorMsg}</font>
			</td>
		</tr>
		</table>
	</form>
</body>
</html>