//console.log = function() {}
var otpsubmit=false;

//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
//var distance = 15000;
var distance = 300000;
var tokenexpired= false;

function formOnLoad(){
	$('input:checkbox').prop('checked', false);
	validateForm();

}


$("#validationCustomUsername,#validationPassword,#validationOTP,#otplogin").keyup(function(){
	validateForm();
});

$("#otplogin").change(function(){
	validateForm();
});


function validateForm(){
//	console.log("validate");
	var digitregexp = /^[6-9]{1}[0-9]{9}$/;
	var mobile = document.forms["login"]["usermobile"].value;
	var pass = document.forms["login"]["userpassword"].value;
	var otplogin = $("#otplogin").is(":checked");
	var flag1=false, flag2=false;

//	console.log(otplogin);

	var mobdes = document.getElementById("mobico");
	var passdes = document.getElementById("passico");

	if(mobile.length>0){
		if(digitregexp.test(mobile)){
			flag1=true;
			mobdes.style.color="rgb(60, 177, 59)";

		}else{
			mobdes.style.color="#e83a3a";
		}
	}else{
		mobdes.style.color="black";
	}


	if(!otplogin){

		if(pass.length>0){
			if(pass.length >=1 && pass.length < 8){
				passdes.style.color="#e83a3a";
			}else{
				passdes.style.color="rgb(60, 177, 59)";
				flag2=true;
			}
		}else{
			passdes.style.color="black";
		}
	}else{
		flag2=true;
	}

	if(flag1 && flag2){
		document.getElementById("loginsubmit").disabled = false;
	}else{
		document.getElementById("loginsubmit").disabled = true;
	}
}


function submitLogin(e){
	e.preventDefault();
	$("#loginmsg").text("");
	var digitregexp = /^[6-9]{1}[0-9]{9}$/;
	var mobile = document.forms["login"]["usermobile"].value;
	var pass = document.forms["login"]["userpassword"].value;
	var returnUrl = document.forms["login"]["returnUrl"].value;
	var otploginChosen = $("#otplogin").is(":checked");
	var otpVal = document.forms["login"]["otpVal"].value;
	var token =  $('input[name="_csrf"]').attr('value'); 

	if(otploginChosen){
		pass="OTP_LOGIN";
	}

	if(!otpsubmit){
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});

		var request;
		request = $.ajax({
			url: "/products/login2.do",
			method: "POST",
			data: {
				"usermobile" : mobile,
				"userpassword" : pass,
				"otpVal" : otpVal,
				"otpLogin" : otploginChosen,
				"returnUrl" : encodeURIComponent(returnUrl),
				"otpSubmit" : otpsubmit,
				"_csrf" : token				
			},
			async: true,
			datatype: "json",
			beforeSend: function() {
				displayprogress();
			}
		});

		request.done(function(msg) {
//			console.log(msg);
			/*
			if(msg=="OTP_SENT"){
				$("#loginmsg").text("OTP sent to your registered email.");
				document.getElementById("passbox").style.display = 'none';
				document.getElementById("otpChoice").style.display = 'none';
				document.getElementById("otpbox").style.display = 'block';

				$("#otpsubmitstat").val(true);
				otpsubmit = true;
				countDownTimer();

			}else if(msg=="SUCCESS"){
//				console.log("Redirect to: " +returnUrl);
				window.location.href = returnUrl;
			}else{
//				Error
				$("#loginmsg").text(msg);
			}
			 */			
			if (data.statuscode == 0){
				$("#loginmsg").text(data.msg);
				document.getElementById("passbox").style.display = 'none';
				document.getElementById("otpChoice").style.display = 'none';
				document.getElementById("otpbox").style.display = 'block';

				$("#otpsubmitstat").val(true);
				otpsubmit = true;
				countDownTimer();
			}else if(data.statuscode == 99){
//				console.log("Redirect to: " +returnUrl);
				window.location.href = returnUrl;
			} else {
				$("#loginmsg").text(data.msg);
			}



		});

		request.fail(function(jqXHR, textStatus) {
			$("#loginmsg").text("Request failed..");

//			location.reload();
		});

		request.always(function(msg){
//			console.log("first step request done- "+msg);
			/*$("#loginspin").hide();
			$("#loginbasic").show();*/
//			$("#loginbasic").html("<i class='fas fa-lock'></i> Login</span>");
			$("#loginsubmit").prop("disabled", false);
			/*if ($("#loginbasic").is(':checked')) {
				  $("#loginbasic").html("<i class='fas fa-lock'></i> Get OTP</span>");
			  }else{
				  $("#loginbasic").html("<i class='fas fa-lock'></i> Login</span>");
			  }*/
			loginfieldvalue("otplogin");
		});

	}else{
		var otpv = document.forms["login"]["otpVal"].value;
		if(otpv=="" || otpv.length!=6){
			$("#loginmsg").text("Invalid OTP!");
			return false;
		}

		$.ajaxSetup({
			headers: { 'X-CSRF-TOKEN': token }
		});

		var request;
		request = $.ajax({
			url: "/products/login2.do",
			method: "POST",
			data: {
				"usermobile" : mobile,
				"userpassword" : pass,
				"otpVal" : otpVal,
				"otpLogin" : otploginChosen,
				"returnUrl" : encodeURIComponent(returnUrl),
				"otpSubmit" : otpsubmit,
				"_csrf" : token				
			},
			async: true,
			datatype: "json",
			beforeSend: function() {
				displayprogress();
			}
		});

		request.done(function(msg) {
//			console.log(msg);
			/*
			if(msg=="OTP_SENT"){

				document.getElementById("passbox").style.display = 'none';
				document.getElementById("otpChoice").style.display = 'none';
				document.getElementById("otpbox").style.display = 'block';

			}else if(msg=="SUCCESS"){
//				console.log("Redirect to: " +returnUrl);
				window.location.href = returnUrl;
			}else if(msg=="OTP_INVALID"){
				$("#loginmsg").text("Invalid OTP");
			}else if(msg=="OTP_INVALIDATED"){
				$("#loginmsg").text("OTP invalidated. Get new token.");
			}else if(msg=="OTP_LOGIN_FAIL" || msg=="OTP_VALID_FAIL" ){
				$("#loginmsg").text("Internal error. Kindly contact admin.");
			}
			else{
//				Error
				$("#loginmsg").text(msg);
			}
			 */			
			if (data.statuscode == 0){
				if(data.msg=="OTP_SENT"){

					document.getElementById("passbox").style.display = 'none';
					document.getElementById("otpChoice").style.display = 'none';
					document.getElementById("otpbox").style.display = 'block';

				}else if(data.msg=="SUCCESS"){
//					console.log("Redirect to: " +returnUrl);
					window.location.href = returnUrl;
				}else if(data.msg=="OTP_INVALID"){
					$("#loginmsg").text("Invalid OTP");
				}else if(data.msg=="OTP_INVALIDATED"){
					$("#loginmsg").text("OTP invalidated. Get new token.");
				}else if(data.msg=="OTP_LOGIN_FAIL" || data.msg=="OTP_VALID_FAIL" ){
					$("#loginmsg").text("Internal error. Kindly contact admin.");
				}
				else{
//					Error
					$("#loginmsg").text(data.msg);
				}

			} else {
				$("#loginmsg").text(data.msg);
			}


		});

		request.fail(function(jqXHR, textStatus) {
			$("#loginmsg").text("Request failed..");
//			location.reload();
		});

		request.always(function(msg){
//			console.log("AJAX requests process complete- "+ msg);

			if(msg!='SUCCESS'){
				$("#loginsubmit").prop("disabled", false);
				/*$("#loginspin").hide();
				$("#loginbasic").show();*/
				loginfieldvalue("otplogin");
			}
		});
	}

	return false;

}

function countDownTimer(){
	var x = setInterval(function() {

		// Get todays date and time
		var now = new Date().getTime();

		// Find the distance between now an the count down date
		distance  -= 1000;
		//console.log(distance);

		// Time calculations for days, hours, minutes and seconds
		var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));

		var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		// Display the result in the element with id="demo"
		//document.getElementById("timer").innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
		document.getElementById("timer").innerHTML = "Valid for - " +  minutes + "min : " + seconds + "sec ";
		// If the count down is finished, write some text 
		if (distance < 0) {
			tokenexpired =  true;
			clearInterval(x);
			document.getElementById("timer").innerHTML = "TOKEN EXPIRED!";
		}
	}, 1000);

}

function displayprogress(){
	/*$("#loginbasic").hide();
	$("#loginspin").show();*/
	$("#loginbasic").html("Please wait <i class='fas fa-spinner fa-spin'>");
	$("#loginsubmit").prop("disabled", true);

}

var input = document.getElementById("validationPassword");
input.addEventListener("keyup", function(event) {

	// If "caps lock" is pressed, display the warning text
	if (event.getModifierState("CapsLock")) {
		$("#msg2").html("Caps lock ON!");
	} else {
		$("#msg2").html("");
	}
});

$('#otplogin').click(function() {
//	console.log($(this).attr('id'));
	loginfieldvalue($(this).attr('id'));
});

function loginfieldvalue(elementname){
//	console.log(elementname);
	if(otpsubmit){
		$("#loginbasic").html("<i class='fas fa-lock'></i> Login</span>");
	}else{
		if ($("#"+elementname).is(':checked')) {
			$("#loginbasic").html("<i class='fas fa-lock'></i> Get OTP</span>");
		}else{
			$("#loginbasic").html("<i class='fas fa-lock'></i> Login</span>");
		}
	}
}
