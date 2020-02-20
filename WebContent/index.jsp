<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
/*
    if (request.getParameter("login") != null) {
        
             session.setAttribute("username", request.getParameter("username")); 
             session.setAttribute("password", request.getParameter("password")); 
             System.out.println("forwarding...\n");
             RequestDispatcher rd = request.getRequestDispatcher("/LoginServlet");
     		rd.forward(request, response); 
            
     } */
    if(request.getSession().getAttribute("message") == null) {
    	request.getSession().setAttribute("message", "Enter in DrinkHub");
    }
    
    
%>     

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DrinkHub - Login page</title>
<style>
#login {
	width: 190px;
    margin: 0 auto;
}
h1 {
	text-align: center;
}
</style>
</head>
<body>
<h1><%=request.getSession().getAttribute("message")%></h1>
<div class="container">
    <form action="LoginServlet" name="myform" method="POST">
    
    	<div id="login">
    		<fieldset>
			<h3>Username <input type="text" name="username" id="username"/></h3>
			
			<h3>Password <input type="password" name="password" id="password"/></h3>
			<br>
			<input type="submit" value="Login">
			</fieldset>
		</div>
	
	</form>
</div>
</body>
</html>