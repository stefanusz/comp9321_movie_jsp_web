<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking Page</title>
</head>
<body>


<%@ include file="header.html"%>

<h2>Booking for <b>${movieTitle}</b></h2>

<form action='control' method='post'>
<table>
<tr><td><b>Cinema:</b></td><td>${cinemaName}</td></tr>
<tr><td><b>Date:</b></td><td>${bookingDate}</td></tr>
<tr><td><b>Time:</b></td><td>${time}</td></tr>
<tr><td><b>No of tickets:</b></td><td>
								<select name='nooftickets'>
								<c:forEach var='i' begin='1' end='${availableTickets}'><option value="${i}">${i}</option></c:forEach> 
								</select></td></tr>
</table>

<input type='hidden' name='bookingdate' value='${bookingDate}'>
<input type='hidden' name='showtimeid' value='${showTimeID}'>
<input type='submit' name='addBooking' value='Book'>
</form>

<br>
<br>


</body>
</html>