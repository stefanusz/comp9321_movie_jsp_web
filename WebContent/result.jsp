<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>


<%@ include file="header.html"%>
<h2>Search Results:</h2>
<c:choose>
	<c:when test="${not empty searchResult}">
		<table>
		<tr><th>Poster<th>Title<th>Genre<th>Actors<th>Rating<th>Link
		
		<c:forEach var="result" items="${searchResult}">
			<tr><td><img src="${result.poster}" alt="${result.poster}" height="42" width="42"><td><a href='control?viewDetail=1&movieid=${result.movieID}'>${result.title}</a><td>
				<c:forEach var="genre" items="${result.genre}">
					${genre} <br>
				</c:forEach>	
			<td>
				<c:forEach var="actor" items="${result.actor}">
					${actor} <br> 
				</c:forEach>
			<td>${result.ageRating}
		</c:forEach>
		
		</table>
	</c:when>
	<c:otherwise>
		0 results found.<br>
	</c:otherwise>
</c:choose>


<a href='index.jsp'>HOME</a>
</body>
</html>