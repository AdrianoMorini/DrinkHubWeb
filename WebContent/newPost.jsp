<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="logic.bean.CheckIngredientBean" %>
<%@page import="logic.bean.NewCocktailBean" %>
<%@page import="logic.controller.NewCocktailController" %>
<%@page import="logic.exception.StringIsEmptyException" %>
    
<%
	final NewCocktailBean newCBean;
	final NewCocktailController contr;

	if(request.getSession().getAttribute("newPostMessage") == null) {
		request.getSession().setAttribute("newPostMessage", "");
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New post page</title>
<style>
h3 {
	display: inline;
}
#formPost {
	width: 900px;
    margin: 0 auto;
}
</style>
</head>
<body>
<jsp:include page="/topBan.jsp"/>
<h4>->New post</h4><hr>
<h4><%=request.getSession().getAttribute("newPostMessage")%></h4>
	<div id="formPost">
		
		<form action="AddNewIngredientServlet" name="addIng" method="GET">
			<h3>Ingredient name: <input type="text" name="ingredientN" id="ingredientN"/>
				Ingredient quantity: <input type="text" name="ingredientQ" id="ingredientQ"/></h3>
				<input type="radio" id="typeM" name="cap" value="g">
 				<label for="typeM">g</label>
  				<input type="radio" id="typeL" name="cap" value="ml">
  				<label for="typeL">ml</label>
  				<input type="radio" id="typeN" name="cap" value="none">
  				<label for="tybeN">(none)</label>
			<br><br>
			<h3>Tag: <input type="text" name="tag" id="tag"/></h3>
			<input type="submit" value="Add ingredient and tag"><br><br>
		</form>
		
	<form action="AddNewIngredientServlet" name="addPost" method="POST">
		<h3>Cocktail name: <input type="text" name="cocktailName" id="cocktailName"/></h3><br><br>
		<h3>Procedure: <input type="text" name="procedure" id="procedure"/></h3><br><br>
		<input type="submit" value="Done">
	</form>
	</div>
</body>
</html>