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

<form action="control" method="POST">


<table>
<tr><td>Actor Name: <td><input type='text' name='actorName'>
<tr><td>Gender: <td>
	<select name='gender'>
	  <option value='male'>Male</option>
	  <option value='female'>Female</option>
	</select>
<tr><td>DOB: <td> <input type="date" name="dob">	  
	  
	
 

</table>
<input type='submit' value='Add' name='addAmenities'>

</form>

</body>
</html>