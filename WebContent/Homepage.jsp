<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<%@page import="logic.model.Cocktail" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<style>
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
#sponsor {
	width: 550px;
    margin: 0 auto;
}
</style>
</head>
<body>
<jsp:include page="/topBan.jsp"/>
<h4>->Homepage</h4><hr>

<h4>Our sponsored posts:</h4>
<%
	if(request.getSession().getAttribute("postMessage") != null) {
		%><h3>NO POSTS</h3><%
	}
	else {
		int len = (int)request.getSession().getAttribute("len");
		ArrayList<Cocktail> list = (ArrayList<Cocktail>)request.getSession().getAttribute("listSponsor");
		for(int i=0; i<len; i++) {
			%>
			<div id=sponsor>
			<h3><img src=<%=list.get(i).getImage()%> height="80" width="80" align="left"><%=list.get(i).getName()%></h3><br>
			<h5><%=list.get(i).getDate().toString()%></h5><br>
			<form action="CocktailPostServlet" name="myform" method="GET"><input type="submit" name="id" value="<%=list.get(i).getName()%>"></form><hr>
			</div>
			<%
		} 
	}

%>

</body>
</html>