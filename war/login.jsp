<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
<link href="login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="jquery-2.1.4.min.js"></script>
</head>

<body>
<div class="wrapper">
<div class="header">
	<p class="title">Employee Time Tracking System</p>
</div>

<div class="maincontent">
<div class="form">
	<p><h3>Sign in</h3>GarryWhiteKnite Time tracking system <a href="employeeNumber.jsp">create a new account</a> 
	</p>
<form action="UserLoginServlet" method="post">
<br>
<div class="form-group1">
<input type="search" class="input" placeholder="Username" name="username" required>
</div>
<div class="form-group2">
<input type="password" class="input" placeholder="Password" name="password" required>
</div>
<div class="form-group4">
	<select name="country" class="country">
        <option value="uganda">Uganda</option>
        <option value="rwanda">Rwanda</option>
    </select>
</div>
<div>
	<input type="checkbox" class="keepSign"> Keep me signed in
</div>
<div class="errorMessage">
	<%=request.getAttribute("validationMessage") %>
</div>
<div class="form-group3">
<input type="submit" value="Sign in" class="login">
</div>
</form><br><br>
<P><a href="" class="accessAccount">can't access your account</a></p>
<p class="signup">Don't have an account with ETTS, <a href="employeeNumber.jsp">Sign up Now</a></p>
</div>

<div class="sliders"></div>
</div>

<div class="footer">
	<a href=""><img src="icons/facebook3.png"></a><a href=""><img src="icons/video-call.png"></a><a href=""><img src="icons/logo22.png"></a><br><br>&#169 garry white Knight. All rights reserved
</div>
</div>
</body>
</html>
