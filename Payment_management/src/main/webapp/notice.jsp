<%@ page import="com.Payment"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notice Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Componets/jquery-3.2.1.min.js"></script>
<script src="Componets/notice.js"></script>

</head>
<body class="container">
	<div class="row my-3">
	 	<div class="col-md-12">
	
		<h1>Payment Management</h1>
		   <form id="formNotice" name="formNotice" method="post" action="notice.jsp">
				
			 	payment ID:
				<input id="paymentID" name="paymentID" type="text"
				 class="form-control form-control-sm" readonly>
				<br>
				  Acc no:
				<input id="accNo" name="accNo" type="text"
				 class="form-control form-control-sm">
				<br>
				 User name:
				<input id="userName" name="userName" type="text"
				 class="form-control form-control-sm">
				<br> 
				amount:
				<input id="amount" name="amount" type="number"
				 class="form-control form-control-sm">
				<br>
				 Description:
				<input id="description" name="description" type="text"
				 class="form-control form-control-sm">
				<br>
				
				
				
				<input id="btnSave" name="btnSave" type="button" value="Save"
				 class="btn btn-primary">
				<input type="hidden" id="hideNoticeIDSave" name="hideNoticeIDSave" value="">
				
			  </form>	
		
			<div id="alertSuccess" class="alert alert-success"></div>
		    <div id="alertError" class="alert alert-danger"></div>
	
		</div>
	 
	 </div>


<div class="row my-3">
 
		<div class="col-md-12">
				<div id="divItemsGrid">
				 <%
				 Payment itemObj = new Payment();
				 		 		 		 		 out.print(itemObj.readPayment());
				 %>
				 </div>
		</div>
 
</div>

	
		<br> 
	

</body>
</html>