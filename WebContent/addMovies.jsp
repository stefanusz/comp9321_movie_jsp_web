<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	$( "#checker" ).click(function(){
	   if($.trim($("#movieTitle").val()) == ""){
	      alert('Input can not be left blank');
	   }
	});
	</script>
	
<title>Add New Movies</title>
</head>
<body>
<%@ include file="header.html"%>

<h1>Add New Movies</h1>

<%@ include file="headerForm.jsp"%>


<%@ page import="java.sql.*" %>
<%@ page import="ass2.*" %>

<%
	Connection conn = DBConnectionFactory.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet genreSet = stmt.executeQuery("SELECT * FROM genre");

%>

<form action="control" method="POST" enctype="multipart/form-data">

<table>
<tr><td>Movie Title: <td><input id="movieTitle" type='text' name='movieTitle' > 
<tr><td>Poster: <td><input type="file" name="poster" size="50"/>
<tr><td>Actors(use ',' for multiple actors): <td><input type='text' name='actor'>
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
	
<tr><td>Release Date: <td> <input type="text" name='releaseDate' id="datepicker">

</table>
<input type='hidden' value='notNull' name='viewAllMovies'>
<input id="checker" type='submit' value='Add' name='addMovies'  >

</form>



</body>
</html>