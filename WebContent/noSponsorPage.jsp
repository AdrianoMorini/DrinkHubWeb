<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DrinkHub - Sponsor</title>
<style>
h2 {
	text-align: center;
}
</style>
</head>
<body>
<jsp:include page="/topBan.jsp"/>
<h4>->Sponsor</h4><hr>
<h2>Your sponsor is still in activity. Will end in <%=request.getSession().getAttribute("timeline")%></h2>
</body>
</html>