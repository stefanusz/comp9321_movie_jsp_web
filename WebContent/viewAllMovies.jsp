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

<c:out value=" ${message} " />
<% 
	request.getSession().setAttribute("message", "");
%>

<h2>All Movies:</h2>

<table>
<tr><th>Poster<th>Title<th>Genre<th>Actors<th>Age Rating

<c:forEach var="data" items="${allMovies}">
	<tr><td><img src="${data.poster}" alt="capture_test" height="42" width="42"><td><a href='control?viewDetail=1&movieid=${data.movieID}'>${data.title}</a><td>
		<c:forEach var="genre" items="${data.genre}">
			${genre} <br>
		</c:forEach>		
	<td>
		<c:forEach var="actor" items="${data.actor}">
			${actor} <br> 
		</c:forEach>
	<td>${data.ageRating}
</c:forEach>




</table>

<a href='index.jsp'>HOME</a>


</body>
</html>