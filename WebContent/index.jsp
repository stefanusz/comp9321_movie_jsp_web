<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

<%@ include file="header.html"%>
<table>
<form action="control" method="POST">
<tr><td>Email: <td><input type='text' name='email'></td></tr>
<tr><td>Password: <td><input type='password' name='password'></td></tr>
<tr><td><input type='submit' value='Login' name='login'></td></tr>
</form>
</table>


<br>
Not a member yet? <a href='register.jsp'>Register!</a>
<br>
<br>


<c:out value ="${username}" />




Now Showing:(order by rating)
<table>
<tr><th>No.<th>Poster/Title<th>Rating
</table>

<br>

Coming Soon: (order by release date)
<table>
<tr><th>No.<th>Poster/Title<th>Rating<th>Release date
</table>

</body>
</html>


