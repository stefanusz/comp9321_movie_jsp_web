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

<form action="control" method="POST">


<table>
<tr><td>Movie Title: <td><input type='text' name='movieTitle'> 
<tr><td>Poster: <td><input type="file" name="poster" size="50"/>
<tr><td>Actors: <td>
<tr><td>Genre:<td>
	<input type='checkbox' name='genre' value='romance'>Romance<br>
	<input type='checkbox' name='genre' value='horror'>Horror<br>
	<input type='checkbox' name='genre' value='thriller'>Thriller<br>
	<input type='checkbox' name='genre' value='comedy'>Comedy<br>
	<input type='checkbox' name='genre' value='drama'>Drama<br>
	<input type='checkbox' name='genre' value='biopix'>Biopix<br>
	<input type='checkbox' name='genre' value='action'>Action<br>
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

</table>
<input type='submit' value='Add' name='addMovies'>

</form>

</body>
</html>