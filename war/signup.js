
$(document).ready(function(){
	var form = $(".sign_form");
	var firstName = $(".firstname");
	var  fdetails= $(".firstDetails");
	var secondName = $(".secondname");
	var  sdetails = $(".secondDetails");
	var  username = $(".username");
	var  udetails = $(".userDetails");
	var  id = $(".id");
	var  ndetails = $(".numberDetails");
	var  email = $(".email");
	var  edetails = $(".emailDetails");
	var phone = $(".phone");
	var  pdetails= $(".phoneDetails");
	var  password = $(".password");
	var  pdetails = $(".passDetails");
	var  cpassword = $(".confirmPass");
	var  cdetails = $(".confirmDetails");

	firstName.blur(validateFName);
	secondName.blur(validateSName);
	email.blur(validateMail);

	$("p").click(function(){
		$(".header").hide();
		$(".header").addClass("ggg");
	});
	$(".firstname").focusout(function(){
		validateFName();
	});

	form.submit(function(){
		if(validateFName() & validateSName() $ validateMail()){
			return true;
		}else{
			return false;
		}
	});
	
	function validateFName(){
		if(firstName.val().length < 4){
			firstName.addClass("error");
			fdetails.text("Your first name is too short");
			fdetails.addClass("error_div");
			return false;
		}else{
			firstName.removeClass("error");
			fdetails.text("What's your first name");
			fdetails.addClass("fine_div");
			return true;
		}
	}

	function validateSName(){
		if(firstName.val().length < 4){
			secondName.addClass("error");
			sdetails.text("Your surname name is too short");
			sdetails.addClass("error_div");
			return false;
		}else{
			secondName.removeClass("error");
			sdetails.text("What's your surname");
			sdetails.addClass("fine_div");
			return true;
		}
	}

	function validateMail(){
		var a = email.val();
		var regexp = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;

		if(regexp.text(a)){
			email.removeClass("error");
			edetails.text("So i can rearch you");
			edetails.addClass("fine_div");
			return true;
		}else{
			email.addClass("error");
			edetails.text("Enter a valid e-mail address");
			edetails.addClass("error_div");
			return false;
		}
	}

});