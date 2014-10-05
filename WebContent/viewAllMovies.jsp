<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Movies</title>
</head>
<body>


<%@ include file="header.html"%>
<h2>All Movies:</h2>


<table>
<tr><th>Poster<th>Title<th>Genre<th>Actors<th>Rating<th>Link

<c:forEach var="data" items="${allMovies}">
	<tr><td><img src="${data.poster}"><td>${data.title}<td>genre<td>actors<td>${data.ageRating}<td>detail
</c:forEach>




</table>


</body>
</html>