<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<link href="signup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="jquery-2.1.4.min.js"></script>
<script type="text/javascript" language="javascript" src="signup.js"></script>
<script>
$(document).ready(function(){

    $(".firstname").keyup(function(){
        check_FName();
    });
    $(".lastname").keyup(function(){
        check_SName();
    });
    $(".username").keyup(function(){
        check_username();
    });
    $(".email").keyup(function(){
        check_email();
    });
    $(".password").keyup(function(){
        check_pass();
    });
    $(".confirmPass").keyup(function(){
        check_retype_pass();
    });
    $(".phone").keyup(function(){
        check_phone();
    });
    

    function check_FName(){
        var name_length = $(".firstname").val().length;

        if(name_length < 4){
            $(".firstname").addClass("error");
            $(".firstDetails").text("your name is not that short");
            $(".firstDetails").addClass("error_div");
            return false;
        }else if(name_length > 20){
            $(".firstname").addClass("error");
            $(".firstDetails").text("your name is too long");
            $(".firstDetails").addClass("error_div");
            return false;
        }else{
            $(".firstname").removeClass("error");
            $(".firstDetails").text("");
            $(".firstDetails").removeClass("error_div");
            return true;
        }
    }

    function check_SName(){
        var lname_length = $(".lastname").val().length;

        if(lname_length < 4){
            $(".lastname").addClass("error");
            $(".lastDetails").text("your name is not that short").show();
            $(".lastDetails").addClass("error_div");
            return false;
        }else if(lname_length > 20){
            $(".lastname").addClass("error");
            $(".lastDetails").text("your name is too long");
            $(".lastDetails").addClass("lerror_div");
            return false;
        }else{
            $(".lastname").removeClass("error");
            $(".lastDetails").text("");
            $(".lastDetails").removeClass("error_div");
            return true;
        }
    }

    function check_username(){
        var user = $(".username").val().length;

         if(user < 4){
            $(".username").addClass("error");
            $(".userDetails").text("Should be between 5-8 characters");
            $(".userDetails").addClass("error_div");
            return false;
        }else if(user > 20){
            $(".username").addClass("error");
            $(".userDetails").text("Should be between 5-8 characters");
            $(".userDetails").addClass("error_div");
            return false;
        }else{
            $(".username").removeClass("error");
            $(".userDetails").text("");
            $(".userDetails").removeClass("error_div");
            return true;
        }
    }

    function check_pass(){
        var pass = $(".password").val().length;

        if(pass < 8){
            $(".password").addClass("error");
            $(".passDetails").text("Atleast 8 characters");
            $(".passDetails").addClass("error_div");
            return false;
        }else{
            $(".password").removeClass("error");
            $(".passDetails").text("");
            $(".passDetails").removeClass("error_div");
            return true;
        }
    }

    function check_retype_pass(){
        var password = $(".password").val().length;
        var retype_pass = $(".confirmPass").val().length;

        if(password != retype_pass){
            $(".confirmPass").addClass("error");
            $(".confirmDetails").text("Passwords do not match");
            $(".confirmDetails").addClass("error_div");
            return false;
        }else{
            $(".confirmPass").removeClass("error");
            $(".confirmDetails").text("");
            $(".confirmDetails").removeClass("error_div");
            return true;
        }
    }

    function check_email(){
        var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);

        if(pattern.test(".email").val()){
            $(".emailDetails").removeClass("error_div");
            $(".emailDetails").text("");
            $(".email").removeClass("error");
            return true;
        }else{
            $(".emailDetails").addClass("error_div");
            $(".emailDetails").text("Invalid E-mail Address");
            $(".email").addClass("error");
            return false;
        }
    }

    function check_phone(){
        var tel = $(".phone").val();
        var tel_length = $(".phone").val().length;

        //validate length
        if(tel_length < 10 || tel_length > 10){
            $(".phone").addClass("error");
            $(".phoneDetails").text("Invalid number, check the length of your digits");
            $(".phoneDetails").addClass("error_div");
            return false;
        }else{
            $(".phone").removeClass("error");
            $(".phoneDetails").text("");
            $(".phoneDetails").removeClass("error_div");
            return false;   
        }

        //validate initial digits
        //validate for strings
    }


});

</script>
<title>Sign Up</title>
</head>

<body>

<div class="wrapper">
<div class="header">
    Employee Time Tracking System
</div>
<div class="maincontent">
    <p class="tittle">Join ETTS for happier working days</p>
<form class="sign_form" action="UserSignupServlet" method="post">
    
    <p>Enter your details </p>


<div class="form-group">    
    <label>Name</label><br>
    <input type="search" name="firstname" placeholder="first" required autofocus class="firstname">  <input type="search" name="lastname" placeholder="last" required class="lastname">
    <span class="firstDetails"></span><span class="lastDetails"></span>
</div>
<div class="form-group">
    <label>Username</label><br> 
    <input type="search" name="username" required placeholder="Enter username" class="username">
    <span class="userDetails"></span>
</div>
<div class="form-group">
    <label>Data Of Birth</label><br>
    <label>Day: </label><input type="number" name="date" required placeholder="day" min="1" max="31"><label>Month: </label><input type="number" name="month" required placeholder="month" min="1" max="12"><label>Year: </label><input type="number" name="year" required placeholder="year" min="1950" max="2016">
</div>
<div class="form-group">
    <label>Position in Company</label><br>
    <select name="pos_company">
        <option value="Partner">Partner</option>
        <option value="Manager">Manager</option>
        <option value="SeniorAuditor">Senior Auditor</option>
        <option value="AssistantAuditor">Assistant Auditor</option>
        <option value="Administrator">Administrator</option>
    </select>
</div>
<div class="form-group">
    <label>Employee Number</label><br>
    <input type="number" name="emplo_number" placeholder="Eg. 089" required class="id">
    <span class="numberDetails"></span>
</div>
<div class="form-group">
    <label>Phone</label><br>
    <input type="tel" name="phone" placeholder=" Eg. 0700 000 000" required class="phone">
    <span class="phoneDetails"></span>
</div>
<div class="form-group">
    <label>Email Address</label><br>
    <input type="email" name="email" placeholder="example@domain.com" required class="email">
    <span class="emailDetails"></span>
</div>
<div class="form-group">
    <label>Password</label><br>
    <input type="password" name="password" required class="password">
    <span class="passDetails"></span>
</div>
<div class="form-group">
    <label>Confirm password</label><br>
    <input type="password" name="confirmpassword" required class="confirmPass">
    <span class="confirmDetails"></span>
</div>
<div class="form-group">
    <label>Location</label><br>
    <select name="location">
        <option value="uganda">Uganda</option>
        <option value="rwanda">Rwanda</option>
    </select>
</div>
<div class="form-group">
    <input type="checkbox" required> I agree to the Garry White Knight <a href="">terms and conditions</a>
</div>
<div class="error_div2">
    <%=request.getAttribute("") %>
</div>
<div class="form-group">
<input type="submit" class="register" value="Sign Up"><br><br>
</div>
</form>
</div>
<div class="sidebar">

    <p>A single account grants you unlimited access to digitized ETTS and leave management subsystems just to make your working year a really accountable working year.<br>
	<img src="time_tracking-clock.png">
	<br><br>
    <a href="login.jsp">Log in</a> with Employee Time Tracking System if you already have an account with us</p>
</div>


</div>
</body>
</html>
