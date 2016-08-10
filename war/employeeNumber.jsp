<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="signup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="jquery-2.1.4.min.js"></script>
<script type="text/javascript" language="javascript" src="signup.js"></script>
<title>Employee Number</title>
</head>
<body>

<div class="overlay">
	<div class="employeeNumber_div">
		<div class="small_header">Enter Your Employee Number For Access</div>
		<br>
		<form action="EmployeeNumberServlet" method="post">
		<label>Employee Number</label><br>
		<input type="password" name="emp_number" class="emp_number"><br>
		<button type="submit" class="emp_button1">Submit</button> <a href="login.jsp"class="emp_button">cancel</a>
	</form>
		<div class="errorMessage1">
			<%= request.getParameter("errorMessage")%>
		</div>
	</div>
</div>

</body>
</html>