<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="logic.model.Cocktail" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DrinkHub - Profile</title>
<style>
h2 {
	text-align: center;
}
h3 {
	text-align: center;
}
h5 {
	text-align: right;
}
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
#post {
	width: 550px;
    margin: 0 auto;
}
</style>
</head>
<body>
<jsp:include page="/topBan.jsp"/>
<h4>->Profile</h4><hr>
<img src=<%=request.getSession().getAttribute("imageBean")%> height="100" width="100" class="center">
<h2>@<%=request.getSession().getAttribute("usernameBean")%></h2>
<h2><%=request.getSession().getAttribute("nameBean")%> <%=request.getSession().getAttribute("surnameBean")%></h2>
<hr>

<%
	if(request.getSession().getAttribute("profileMessage") != null) {
		%><h3>NO POSTS</h3><%
	}
	else {
		int l;
		if(request.getSession().getAttribute("length") == null) {
			l = 0;
		}
		else {
			l = (int)request.getSession().getAttribute("length");
		}
		
		ArrayList<Cocktail> list = (ArrayList<Cocktail>)request.getSession().getAttribute("list");
		for(int i=0; i<l; i++) {
			%>
			<div id=post>
			<h3><img src=<%=list.get(i).getImage()%> height="80" width="80" align="left"><%=list.get(i).getName()%></h3><br>
			<h5><%=list.get(i).getDate().toString()%></h5><br>
			<form action="" name="myform" method="GET"><input type="submit" value="See detail"></form><hr>
			</div>
			<%
		} 
	}

%>

</body>
</html>