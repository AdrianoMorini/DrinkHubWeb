<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="LoginBean" scope="request" class="logic.view.LoginServlet"/>
<jsp:setProperty name="LoginBean" property="*"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
form { 
	display: inline;
}
p {
	display: inline;
}
#container {
	width: 610px;
    margin: 0 auto;
}
</style>
</head>
<body>
<div id="container">
	<p>DrinkHub</p>
	<form action="HomepageServlet" name="myform" method="GET">
		<input type="submit" value="Homepage">
	</form>
	
	<form action="ProfileServlet" name="myform" method="GET">
		<input type="submit" value="My Profile">
	</form>
	
	<form action="NewPostServlet" name="myform" method="GET">
		<input type="submit" value="New post">
	</form>
	
	<form action="SearchServlet" name="myform" method="GET">
		<input type="submit" value="Search">
	</form>
	
	<a href="https://www.google.com/maps">
		<input type="button" value="Search Bar">
	</a>

	<form action="SponsorServlet" name="myform" method="GET">
		<input type="submit" value="Sponsor">
	</form>
	
	<form action="ExitServlet" name="myform" method="GET">
		<input type="submit" value="Exit">
	</form>
	<hr>
</div>
</body>
</html>