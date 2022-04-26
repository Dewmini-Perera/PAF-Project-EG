<%@page import="model.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
//Insert item---------------------------------
if (request.getParameter("Pcode") != null) {
	Users itemObj = new Users();
	String stsMsg = itemObj.insertItem(request.getParameter("Pcode"), request.getParameter("PName"), request.getParameter("PNIC"),
	request.getParameter("PhoneNo"), request.getParameter("Email"), request.getParameter("Address"),
	request.getParameter("Password"));
	session.setAttribute("statusMsg", stsMsg);
}
//Delete item----------------------------------
if (request.getParameter("PID") != null) {
	Users itemObj = new Users();
	String stsMsg = itemObj.deleteItem(request.getParameter("PID"));
	session.setAttribute("statusMsg", stsMsg);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<h1>User Management</h1>
	<form id="formItem" name="formItem" method="post"
		action="powerGrids.jsp">
		User Code: <input id="Pcode" name="Pcode" type="text"
			class="form-control form-control-sm"> <br> 
			Name: <input
			id="PName" name="PName" type="text"
			class="form-control form-control-sm"> <br> 
			NIC: <input
			id="PNIC" name="PNIC" type="text"
			class="form-control form-control-sm"> <br> 
			Contact: <input
			id="PhoneNo" name="PhoneNo" type="text"
			class="form-control form-control-sm"> <br> 
			Email: <input
			id="Email" name="Email" type="text"
			class="form-control form-control-sm"> <br> 
			Address: <input
			id="Address" name="Address" type="text"
			class="form-control form-control-sm"> <br> 
			Password: <input
			id="Password" name="Password" type="text"
			class="form-control form-control-sm"> <br> <input
			id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
			id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	<%
	out.print(session.getAttribute("statusMsg"));
	%>
	4
	<br>
	<%
	Users itemObj = new Users();
	out.print(itemObj.readItems());
	%>
</body>
</html>