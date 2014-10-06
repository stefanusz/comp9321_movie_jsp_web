<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Movies</title>
</head>
<body>
<%@ include file="header.html"%>
<h1>Add New Movies</h1>


<%@ include file="headerForm.jsp"%>



<c:out value=" ${message} " />
<% 
	request.getSession().setAttribute("message", "");
%>



<%@ page import="java.sql.*" %>
<%@ page import="ass2.*" %>


<%
	Connection conn = DBConnectionFactory.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet genreSet = stmt.executeQuery("SELECT * FROM genre");

%>

<form action="control" method="POST" enctype="multipart/form-data">

<table>
<tr><td>Movie Title: <td><input type='text' name='movieTitle'> 
<tr><td>Poster: <td><input type="file" name="poster" size="50"/>
<tr><td>Actors: <td>
<tr><td>Genre:<td>
<% while(genreSet.next()){
		String genre = genreSet.getString("name");
		String genreid = genreSet.getString("genreid");
%>
<input type="checkbox" name="genreid" value="<%=genreid%>"><%= genre%>
<br>
<% } %>



<tr><td>Director: <td><input type='text' name='director'>
<tr><td>Short Sypnosis<br>(100 words): <td><textarea name="sypnosis" cols="50" rows="10"></textarea>
<tr><td>Age Rating: <td>
	<select name='ageRating'>
	  <option value='G'>G</option>
	  <option value='PG'>PG</option>
	  <option value='M'>M</option>
	  <option value='MA15+'>MA15+</option>
	  <option value='R18+'>R18+</option>
	</select>
	
<tr><td>Release Date: <td>
	<select name='release_day'>
		<c:forEach var="i" begin="1" end="31">
		   <option value='${i}'>${i}</option>
		</c:forEach>
	</select>
	<select name='release_month'>
		<c:forEach var="i" begin="1" end="12">
		   <option value='${i}'>${i}</option>
		</c:forEach>
	</select>
	<select name='release_year'>
		<c:forEach var="i" begin="1900" end="2014">
		   <option value='${i}'>${i}</option>
		</c:forEach>
	</select>


</table>
<input type='submit' value='Add' name='addMovies'>

</form>

</body>
</html>