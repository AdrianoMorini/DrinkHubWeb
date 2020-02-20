<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="logic.model.Cocktail" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DrinkHub - Cocktail</title>
</head>
<body>
<h2><%=request.getSession().getAttribute("name")%></h2>
<h2><%=request.getSession().getAttribute("proc")%></h2>
<h2><%=request.getSession().getAttribute("ingr")%></h2>
</body>
</html>