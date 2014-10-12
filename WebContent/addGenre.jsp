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
	<script type="text/javascript">
	$(document).ready(function() {
		
		$( "#checker" ).click(function(event){
			   if($.trim($("#genre").val()) == ""){
			      alert('Please fill in the genre field!');
			      event.preventDefault();
			   }
			});
		
	});
	</script>
<title>Add New Genre</title>
</head>
<body>
<%@ include file="header.html"%>
<h1>Add New Genre</h1>

<%@ include file="headerForm.jsp"%>



<form action="control" method="POST">
<table>
<tr><td>Genre Name: <td><input id="genre" type='text' name='genreName'>

</table>
<input id="checker" type='submit' value='Add' name='addGenre'>

</form>

</body>
</html>