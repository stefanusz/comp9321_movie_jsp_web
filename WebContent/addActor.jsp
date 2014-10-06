<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Actor</title>
</head>
<body>
<%@ include file="header.html"%>
<h1>Add New Actor</h1>

<%@ include file="headerForm.jsp"%>


<form action="control" method="POST">
<table>
<tr><td>Actor Name: <td><input type='text' name='actorName'>
<tr><td>Gender: <td>
	<select name='gender'>
	  <option value='male'>Male</option>
	  <option value='female'>Female</option>
	</select>
<tr><td>DOB: <td>

	<select name='dob_day'>
		<c:forEach var="i" begin="1" end="31">
		   <option value='${i}'>${i}</option>
		</c:forEach>
	</select>
	<select name='dob_month'>
		<c:forEach var="i" begin="1" end="12">
		   <option value='${i}'>${i}</option>
		</c:forEach>
	</select>
	<select name='dob_year'>
		<c:forEach var="i" begin="1900" end="2014">
		   <option value='${i}'>${i}</option>
		</c:forEach>
	</select>


</table>
<input type='submit' value='Add' name='addActor'>

</form>

</body>
</html>