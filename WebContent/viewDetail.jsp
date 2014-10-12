<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script>
	$(function() {
	  $( "#datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  
	});
	</script>
<title>Movie Detail</title>
</head>
<body>


<%@ include file="header.html"%>

<h2>${movieDetail.title}</h2>
<img src="${movieDetail.poster}" height="284px" width="192px" >
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


<c:if test = "${movieDetail.releaseDate < currentDate}">
	<c:if test = "${not empty username && role != 'admin'}">
		
	Book your ticket:
		<form action="control" method="GET">
			<input type="text" name='bookingDate' id="datepicker">
			
			<input type='hidden' name='movieid' value='${movieDetail.movieID}'>
			<input type='hidden' name='viewDetail' value='notNull'>
			<input type="submit" name='getShowTimes' value='Go'>
		</form>
		<br>
		<c:if test = "${not empty bookingTimes}">
			<table>
				<c:forEach var="bean" items="${bookingTimes}">
					<tr><td>${bean.name} :</td>
					<c:forEach var="time" items="${bean.showTimes}">
						<td><a href='control?doBooking=1&movieid=${movieDetail.movieID}&movietitle=${movieDetail.title}&cinemaid=${bean.cinemaID}&cinemaname=${bean.name}&bookingdate=${bookingDate}&time=${time}'>${time}.00</a></td>
					</c:forEach>
					</tr>
				</c:forEach>
			</table>
		
			<%request.getSession().removeAttribute("bookingTimes");%>
		</c:if>
	</c:if>	
		
		<br>
		<br>
	
		<c:if test = "${not empty username && role == 'admin'}">
			Add Show Times (<b>${movieDetail.title}</b>):
			<form action="control" method="POST">
			<table>
				<c:forEach var="bean" items="${movieEmptyTimes}">
					<tr><td>${bean.name} :</td>
					<c:forEach var="time" items="${bean.showTimes}">
						<td><input type='checkbox' name='${bean.cinemaID}' value='${time}'>${time}.00</td>
					</c:forEach>
					</tr>
				</c:forEach>
			
			</table>
			<input type='hidden' name='viewDetail' value='notNull'>
			<input type='hidden' name='movieid' value='${movieDetail.movieID}'>
			<input type='submit' name='addShowTimes' value='Process'>
			</form>
		</c:if>
	<br>
	<br>
	<br>
		
		
	
	
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
	
	
	
	<c:choose>
		<c:when test='${empty movieComment}'>
			No comment on this movies yet.
		</c:when>
		
		<c:otherwise>
			
			<c:forEach var="data" items="${movieComment}">
				<table>
					<tr><td>${data.user} -> <td>${data.rating}/5.0
					<tr><td colspan='2'>${data.comment}
				</table>
			</c:forEach>
		</c:otherwise>
	</c:choose>

</c:if>

</body>
</html>