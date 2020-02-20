<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    if(request.getSession().getAttribute("pricePreview") == null) {
    	request.getSession().setAttribute("pricePreview", "");
    }
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DrinkHub - Sponsor</title>

<style>
form { 
	display: inline;
}
p {
	display: inline;	
}
radio {
	display: inline;
}
h4 {
	display: inline;
}
</style>

</head>
<body>
<jsp:include page="/topBan.jsp"/><br>
<h4>->Sponsor</h4><hr><br><br>
<h4>Here you can sponsorize your posts or your profile</h4><br><br>

<form action="SponsorRequestServlet" method="GET">
<h4>1.Choose duration of sponsorship</h4>
  <input type="radio" id="typeP" name="type" value="Profile">
  <label for="typeP">Profile</label>
  <input type="radio" id="typeC" name="type" value="Post">
  <label for="typeC">Post</label>
  <input type="radio" id="typeB" name="type" value="Both">
  <label for="tybeB">Both</label>
  
  <br>
  <br>
  
  <h4>2. Choose type of sponsorship</h4>
  <input type="radio" id="timeO" name="time" value="1 month">
  <label for="timeO">1 month</label>
  <input type="radio" id="typeT" name="time" value="2 months (-10%)">
  <label for="timeT">2 months (-10%)</label>
  <input type="radio" id="timeTh" name="time" value="3 months (-15%)">
  <label for="tybeTh">3 months (-15%)</label>
  <input type="radio" id="timeS" name="time" value="6 months (-20%)">
  <label for="tybeS">6 months (-20%)</label>
  <input type="radio" id="timeY" name="time" value="1 year (-30%)">
  <label for="tybeY">1 year (-30%)</label>
  <br>
  <br>
  
  <h4>3. Click on 'Price preview' to see a payment preview.</h4><br>
  <h4>4. Confirm and pay</h4>
  
  <h1><%=request.getSession().getAttribute("pricePreview")%></h1>
  <input type="submit" value="Price preview">
</form>

<br>
<br>

<form action="SponsorConfirmServlet" method="GET">

	<input type="submit" id="confirmB" value="CONFIRM">
</form>

</body>
</html>