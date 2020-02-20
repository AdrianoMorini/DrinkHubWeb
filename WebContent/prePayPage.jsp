<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DrinkHub - Pay here</title>
<style>

</style>
</head>
<body>
<jsp:include page="/topBan.jsp"/>
<h4>->Sponsor->Payment</h4>
<h2>Pay here: <%=request.getSession().getAttribute("pricePreview")%> euros</h2>
<br>
	<script src="https://www.paypal.com/sdk/js?client-id=AT4Gwd4JBYAWRZMRbDnBzZkxFcNojz9K4HZzdIQWolQM0HfNmV-akOpEHUDA7L-pESI_VlDo_Np5nUuJ"></script>
	<script> paypal.Buttons({
			onApprove: function(data, actions) {
			  // Capture the funds from the transaction
			  return actions.order.capture().then(function(details) {
				// Show a success message to your buyer
				//alert('Transaction completed!');
				window.location.href = "SavePayServlet";
			  });
			}
		  }).render('body');
	</script> 
	<!-- <script>paypal.Buttons().render('body');</script> -->
	
	
</body>
</html>