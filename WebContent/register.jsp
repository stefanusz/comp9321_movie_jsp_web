<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<table>
<tr><td>Username: <td><input type='text' name='username'> 
<tr><td>First name: <td><input type='text' name='first_name'>
<tr><td>Last name: <td><input type='text' name='last_name'>
<tr><td>Nickname: <td><input type='text' name='nickname'>
<tr><td>E-mail: <td><input type='text' name='email'>
<tr><td>Password: <td><input type='text' name='password'>
<tr><td>Repeat password: <td><input type='text' name='password2'>
</table>
<input type='submit' value='Register'>

</form>

</body>
</html>