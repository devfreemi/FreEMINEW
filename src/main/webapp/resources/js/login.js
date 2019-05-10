var otpsubmit=false;

//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
//var distance = 15000;
var distance = 300000;
var tokenexpired= false;

function formOnLoad(){
	/*var digitregexp = /^[6-9]{1}[0-9]{9}$/;
	var mobile = document.forms["login"]["usermobile"].value;
	var flag1=false, flag2=false;


	if(digitregexp.test(mobile)){
		flag1=true;
	}
	if(flag1){
		document.getElementById("loginsubmit").disabled = false;
	}else{
		document.getElementById("loginsubmit").disabled = true;
	}*/
	validateForm();

}

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
			/*$("#validationCustomUsername").css({"border-bottom":"2px solid #3eb755;"});*/
			/*$("#mobico").css({"color":"#2aa72f;"});
		$("#msg1").text("");*/
//			console.log("valid no");
			mobdes.style.color="rgb(60, 177, 59)";

		}else{
//			console.log("Invalid mobile");
			/*$("#validationCustomUsername").css({"border-bottom":"2px solid #f7445e;"});*/
//			$("#mobico").css({"color":"#f7445e;"});


			/*$("#msg1").text("Invalid number format!");*/
			/*$("#ht").css("color","#f7445e;");*/
			mobdes.style.color="#e83a3a";

		}
	}else{
		mobdes.style.color="black";
	}


	if(!otplogin){

		if(pass.length>0){
			if(pass.length >=1 && pass.length < 8){


				passdes.style.color="#e83a3a";
//				console.log("more")
			}else{
//				console.log("less")
				passdes.style.color="rgb(60, 177, 59)";
				flag2=true;
			}
		}else{
			passdes.style.color="black";
		}
	}else{
		flag2=true;
	}

	/*if(otpsubmit ){
		var otp = document.forms["login"]["otpVal"].value;
		if(otp.lenth==6){
		flag2=true;
		}
	}else{
		flag2=false;
	}*/

	if(flag1 && flag2){
		document.getElementById("loginsubmit").disabled = false;
	}else{
		document.getElementById("loginsubmit").disabled = true;
	}
}


function submitLogin(e){
	e.preventDefault();
	var digitregexp = /^[6-9]{1}[0-9]{9}$/;
	var mobile = document.forms["login"]["usermobile"].value;
	var pass = document.forms["login"]["userpassword"].value;
	var returnUrl = document.forms["login"]["returnUrl"].value;
	var otploginChosen = $("#otplogin").is(":checked");
	var token =  $('input[name="_csrf"]').attr('value'); 
//	console.log("OTP login- "+ otploginChosen);
//	console.log(token);
	$("#loginmsg").text("");
	
	
	if(otploginChosen){
		token.pass="OTP_LOGIN";
	}
	
	if(!otpsubmit){
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});

		var request;
		var str = $("#login").serialize();
//		console.log(str);
		
		request = $.ajax({
			url: "/products/login2.do",
			method: "POST",
			data:str,
			async: true,
			datatype: "json",
			beforeSend: function() {
				disableButon();
		    }
		});

		request.done(function(msg) {
//			console.log(msg);
			if(msg=="OTP_SENT"){
				$("#loginmsg").text("OTP sent to your registered email.");
				document.getElementById("passbox").style.display = 'none';
				document.getElementById("otpChoice").style.display = 'none';
				document.getElementById("otpbox").style.display = 'block';
				
				$("#otpsubmitstat").val(true);
				otpsubmit = true;
				countDownTimer();
				
			}else if(msg=="SUCCESS"){
				console.log("Redirect to: " +returnUrl);
				window.location.href = returnUrl;
			}else{
//				Error
				$("#loginmsg").text(msg);
			}

		});

		request.fail(function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
			
//			location.reload();
		});
		
		request.always(function(msg){
//			console.log("first step request done- "+msg);
			$("#loginspin").hide();
			$("#loginbasic").show();
			$("#loginsubmit").prop("disabled", false);
		});
		
		

	}else{
		$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
		});

		var request;
		var str = $("#login").serialize();
//		console.log(str);
		
		request = $.ajax({
			url: "/products/login2.do",
			method: "POST",
			data:str,
			async: true,
			datatype: "json",
			beforeSend: function() {
				disableButon();
		    }
		});

		request.done(function(msg) {
			console.log(msg);
			if(msg=="OTP_SENT"){

				document.getElementById("passbox").style.display = 'none';
				document.getElementById("otpChoice").style.display = 'none';
				document.getElementById("otpbox").style.display = 'block';
				
			}else if(msg=="SUCCESS"){
				console.log("Redirect to: " +returnUrl);
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

		});

		request.fail(function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
//			location.reload();
		});
		
		request.always(function(msg){
//			console.log("AJAX requests process complete- "+ msg);
			
			if(msg!='SUCCESS'){
				$("#loginsubmit").prop("disabled", false);
				$("#loginspin").hide();
				$("#loginbasic").show();
			}
		});


		
	}
	
	return false;

}

function countDownTimer(){
	//var countDownDate = new Date("Jun 13, 2018 15:37:25").getTime();
	console.log("Timer started")
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

function disableButon(){
	$("#loginbasic").hide();
	$("#loginspin").show();
	$("#loginsubmit").prop("disabled", true);
	
}
