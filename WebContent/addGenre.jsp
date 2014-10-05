<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Genre</title>
</head>
<body>
<%@ include file="header.html"%>
<h1>Add New Genre</h1>

<c:out value=" ${message} " />
<% 
String message = "";
request.getSession().setAttribute("message", message);
%>


<form action="control" method="POST">
<table>
<tr><td>Genre Name: <td><input type='text' name='genreName'>

</table>
<input type='submit' value='Add' name='addGenre'>

</form>

</body>
</html>