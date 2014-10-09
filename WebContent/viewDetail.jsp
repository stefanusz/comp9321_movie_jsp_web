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
<tr><td><b>Actors:</b><td><c:forEach var="actor" items="${movieDetail.actor}">
							${actor}  
						</c:forEach>
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
<br>
<br>


<c:if test = "${movieDetail.releaseDate < currentDate}">
	
	<c:choose>
		<c:when test='${empty username}'>
			You need to login first before comment.<br>
		</c:when>
		
		<c:otherwise>
			
			<form action="control" method="POST" > 
				Comment & Rate:    
				<input type="radio" name="rating" value="1">1
				<input type="radio" name="rating" value="2">2
				<input type="radio" name="rating" value="3">3
				<input type="radio" name="rating" value="4">4
				<input type="radio" name="rating" value="5">5
				<br>
				<textarea name="comment" cols="30" rows="7"></textarea>
				<br>
				<input type='hidden' name ='movieid' value='${movieDetail.movieID}'>
				<input type='hidden' name ='viewDetail' value='notNull'>
				<input type='submit' value='Comment' name='addComment'>
			</form>
		</c:otherwise>
	</c:choose>
	




<br>
Comments:
<c:forEach var="data" items="${movieComment}">
	<table>
		<tr><td>${data.user} -> <td>${data.rating}/5.0
		<tr><td colspan='2'>${data.comment}
	</table>
</c:forEach>

</c:if>
</body>
</html>