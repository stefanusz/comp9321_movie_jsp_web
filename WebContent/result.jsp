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
	<script type="text/javascript">
	$(document).ready(function() {
		
		$( "#checker" ).click(function(event){
			   if($.trim($("#search").val()) == ""){
			      alert('Please fill in what you want to search');
			      event.preventDefault();
			   }
			});
		
	});
	</script>
<title>Result</title>
</head>
<body>


<%@ include file="header.html"%>
<h2>Search Results:</h2>

<table>
<form action ="control" method = "post">
<tr><td>Search: <td><input id="search" type='text' name='search'></td></tr>
<tr><td><input id="checker" type='submit' value='Search' name='doSearch'></td></tr>
</form>
</table>

<c:choose>
	<c:when test="${not empty searchResult}">
		<table>
		<tr><th>Poster<th>Title<th>Genre<th>Actors<th>Rating
		
		<c:forEach var="result" items="${searchResult}">
			<tr><td><img src="${result.poster}" alt="${result.poster}" height="100" width="67"><td><a href='control?viewDetail=1&movieid=${result.movieID}'>${result.title}</a><td>
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

</body>
</html>