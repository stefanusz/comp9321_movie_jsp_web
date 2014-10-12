<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script type="text/javascript">
	$(document).ready(function() {
		
		$( "#checker" ).click(function(event){
			   if($.trim($("#search").val()) == ""){
			      alert('Please fill in what you want to search');
			      event.preventDefault();
			   }
			   
			});
		
		$( "#login" ).click(function(event){
			 
			   if($.trim($("#username").val()) == ""){
				      alert('Please type in your username');
				      event.preventDefault();
				   }
			   else if($.trim($("#password").val()) == ""){
				      alert('Please type in your password');
				      event.preventDefault();
				   }
			   
			});
		
	});
	</script>
<title>Home</title>
</head>
<body>

<%@ include file="header.html"%>

<c:out value="${message}" />
<% 
	request.getSession().setAttribute("message", "");
%>

<br>
<br>

<c:choose>
	<c:when test='${empty username}'>
		<form action="control" method="POST">
		<table>
			<tr><td>Username: <td><input id="username" type='text' name='username'></td></tr>
			<tr><td>Password: <td><input id="password" type='password' name='password'></td></tr>
			<tr><td><input id="login" type='submit' value='Login' name='login'></td></tr>
		</table>
		Not a member yet? <a href='register.jsp'>Register!</a>
		<br>
		</form>
	</c:when>
	
	<c:otherwise>
		Welcome back, ${username}! <a href='editProfile.jsp'>(edit your profile)</a><br>
		<a href='control?viewBooking=1'>My Booking</a><br>
		<form action="control" method="POST" ><input type='submit' value='Logout' name='logout'></td></form>
	</c:otherwise>
</c:choose>


<br>
<br>

<c:if test="${role == 'admin'}">
	<a href='addMovies.jsp'>Add Movies</a><br>
		<a href='addActor.jsp'>Add Actor</a><br>
	<a href='addGenre.jsp'>Add Genre</a><br>
		<a href='addCinema.jsp'>Add Cinema</a><br>
		<a href='addAmenities.jsp'>Add Amenities</a><br>	
</c:if>

<br>

<table>
<form action ="control" method = "post">
<tr><td>Search: <td><input id="search" type='text' name='search'></td></tr>
<tr><td><input id="checker" type='submit' value='Search' name='doSearch'></td></tr>
</form>
</table>

<br>

<h2>Now Showing:</h2>
<c:choose>
	<c:when test='${not empty nowShowing}'>
		<table>
		<tr><th>Poster<th>Title<th>Rating
		<c:forEach var="data" items="${nowShowing}">
			<tr><td><img src="${data.poster}" alt="capture_test" height="100" width="67"><td><a href='control?viewDetail=1&movieid=${data.movieID}'>${data.title}</a><td>${data.ratingString}
		</c:forEach>
		</table>
	</c:when>
	
	<c:otherwise>
		No movies available at the moment.<br> 
	</c:otherwise>
</c:choose>


<a href='control?viewAllMovies=1'>View All</a>
<br>
<br>
<br>

<h2>Coming Soon:</h2>
<c:choose>
	<c:when test='${not empty nowShowing}'>
		<table>
		<tr><th>Poster<th>Title<th>Rating<th>Release date
		<c:forEach var="data" items="${comingSoon}">
			<tr><td><img src="${data.poster}" alt="capture_test" height="100" width="67"><td><a href='control?viewDetail=1&movieid=${data.movieID}'>${data.title}</a><td>${data.ratingString}<td>${data.releaseDate}
		</c:forEach>
		</table>
	</c:when>
	
	<c:otherwise>
		No movies available at the moment.<br> 
	</c:otherwise>
</c:choose>


</body>
</html>


