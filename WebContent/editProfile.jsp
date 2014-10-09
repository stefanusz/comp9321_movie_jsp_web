<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
<%@ include file="header.html"%>
<h1>Edit Profile</h1>
<a href='control'>HOME</a>

<c:out value=" ${message} " />
<% 
String message = "";
request.getSession().setAttribute("message", message);
%>

<form action="control" method="POST">
<table>
<tr><td>Username: <td><input type='hidden' name='username' value='${username}'>${username}
<tr><td>First name: <td><input type='text' name='firstName' value='${first_name}'>
<tr><td>Last name: <td><input type='text' name='lastName' value='${last_name}'>
<tr><td>Nickname: <td><input type='text' name='nickname' value='${nickname}'>
<tr><td>E-mail: <td><input type='text' name='email' value='${email}'>
<tr><td><td>
<tr><td>Old Password: <td><input type='password' name='oldPassword'>
<tr><td>New Password: <td><input type='password' name='newPassword'>
<tr><td>Re-type password: <td><input type='password' name='newPassword2'>
</table>
<input type='submit' value='Edit' name='editProfile'>

</form>

</body>
</html>