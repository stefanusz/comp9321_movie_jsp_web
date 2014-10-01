<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
<%@ include file="header.html"%>
<h1>Registration Page</h1>

<form action="control" method="POST">
<c:if test="${not empty registered}">
	FAIL
</c:if>
<table>
<tr><td>Username: <td><input type='text' name='username'> 
<tr><td>First name: <td><input type='text' name='firstName'>
<tr><td>Last name: <td><input type='text' name='lastName'>
<tr><td>Nickname: <td><input type='text' name='nickname'>
<tr><td>E-mail: <td><input type='text' name='email'>
<tr><td>Password: <td><input type='text' name='password'>
<tr><td>Re-type password: <td><input type='text' name='password2'>
</table>
<input type='submit' value='Register' name='register'>

</form>

</body>
</html>