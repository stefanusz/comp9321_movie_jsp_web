<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>


<%@ include file="header.html"%>
<h2>Search:</h2>
<form action='control' method='post'>
<input type='text' name='search_terms'>
<input type='submit' value='Search'>
</form>

</body>
</html>