<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Detail</title>
</head>
<body>


<%@ include file="header.html"%>

<h2>${movieDetail.title}</h2>
<img src="${movieDetail.poster}">
<table>
<tr><td><b>Actors:</b><td>
<tr><td><b>Genre:</b><td><c:forEach var="genre" items="${movieDetail.genre}">
							${genre} 
						</c:forEach>	

<tr><td><b>Director:</b><td>${movieDetail.director}
<tr><td><b>Sypnosis:</b><td>${movieDetail.sypnosis}
<tr><td><b>Age Rating:</b><td>${movieDetail.ageRating}
<tr><td><b>Release Date:</b><td>${movieDetail.releaseDate}

</table>


<br>
<br>
[booking?]
<br>
[input comment]
<br>
[list of comments/ratings]

</body>
</html>