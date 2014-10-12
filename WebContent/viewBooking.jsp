<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Booking</title>
</head>
<body>

<%@ include file="header.html"%>


<h2>My Booking:</h2>


<c:choose>
	<c:when test="${not empty myBooking}">
		<table>
		<tr><th>Cinema<th>Movie<th>Date<th>Time<th>No of Tickets
		<c:forEach var="data" items="${myBooking}">
			<tr><td>${data.cinemaName}</td><td>${data.movieTitle}</td><td>${data.bookingDate}</td><td>${data.time}</td><td>${data.noOfTicket}</td></tr>				
		</c:forEach>		
		</table>

	</c:when>
	<c:otherwise>
		You have not made any booking yet.<br>
	</c:otherwise>
</c:choose>

<a href='index.jsp'>HOME</a>


</body>
</html>