<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<!--
   This form is presented as is and should not be used in production as it is
   vulnerable to every sort of injection attack. Please make sure you fix this 
   before using it in your assignment. 
 -->
<title>Mail Sender Demo</title>
</head>
<body>
<center><h1>Welcome to Joke Mail!</h1></center>
<form action="mail" method="post">
<p><b>From:</b><input type="text" name="from" maxlength="40"/></p>
<p><b>To:</b><input type="text" name="to" maxlength="40"/></p>
<p><b>Subject</b><input type="text" name="subject" maxlength="40"/></p>
<p><textarea rows="4" cols="50" name="body">
Write your email body here!
</textarea></p>
<p><input type="submit" name="Send!" title="Send!"/></p>
</form>
</body>
</html>