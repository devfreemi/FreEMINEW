var otpsubmit=false; 
$(document).ready(function(){
	var distance = 300000;
	var tokenexpired= false;
	var current_fs, next_fs, previous_fs; //fieldsets
	var opacity;

	$(".next").click(function(e){
		e.preventDefault();
		var key = document.forms["msform"]["usermobile"].value;
		var type = $("input[name='loginmethod']:checked").val();
		var digitregexp = /^[6-9]{1}[0-9]{9}$/;
		
		if(!digitregexp.test(key)){
			$("#loginmsg").text("Invalid mobile no.");
			return false; 
		}
		
		$("#nextbtn").prop('disabled',true);
		current_fs = $(this).parent();
		next_fs = $(this).parent().next();
		console.log("current_fs- "+ current_fs + " ;next_fs- "+ next_fs);
		//Add Class Active
		
//		r();
//		return false;
		if(type == 'OTP'){
			var otpsent = requestotp();
			if(otpsent){
				var pwdlement =  document.getElementById('pwdbox');
				if (typeof(pwdlement) != 'undefined' && pwdlement != null)
				{
					pwdlement.remove();
				}
				var element =  document.getElementById('otpbox');
				if (typeof(element) != 'undefined' && element != null)
				{
				}else{

					distOption = document.getElementById("logincred");
					var x = document.createElement("INPUT");
					x.setAttribute("type", "tel");
					x.setAttribute("id","otpbox");
					x.setAttribute("class", "form-control form-control-sm mr-sm-3");
					x.setAttribute("name", "otpVal");
					x.setAttribute("pattern", "[0-9]*");
					x.setAttribute("placeholder", "OTP");
					x.setAttribute("aria-describedby", "validationOTP");
					x.setAttribute("autocomplete", "off");
					x.setAttribute("maxlength", "6");
					distOption.appendChild(x);
				}
			}else{
				$("#nextbtn").prop('disabled',false);
				return false;

			}
		}else if(type == 'PWD'){
			var otpelement =  document.getElementById('otpbox');
			if (typeof(otpelement) != 'undefined' && otpelement != null)
			{
				otpelement.remove();
			}
			var element =  document.getElementById('pwdbox');
			if (typeof(element) != 'undefined' && element != null)
			{
			}else{

				distOption = document.getElementById("logincred");
				var x = document.createElement("INPUT");
				x.setAttribute("type", "password");
				x.setAttribute("id","pwdbox");
				x.setAttribute("class", "form-control form-control-sm mr-sm-3");
				x.setAttribute("name", "userpassword");
				x.setAttribute("pattern", "[0-9]*");
				x.setAttribute("placeholder", "Your password");
				x.setAttribute("maxlength", "24");
				x.setAttribute("autocomplete", "off");
				distOption.appendChild(x);
			}
		}

		$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

		$("#key").val(key);

		//show the next fieldset
		next_fs.show(); 
		//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
			step: function(now) {
				// for making fielset appear animation
				opacity = 1 - now;

				current_fs.css({
					'display': 'none',
					'position': 'relative'
				});
				next_fs.css({'opacity': opacity});
			}, 
			duration: 600
		});

		$("#nextbtn").prop('disabled',false);
	});

	$(".login").click(function(){
		validatesubmit();
	});

	$(".previous").click(function(){

		current_fs = $(this).parent();
		previous_fs = $(this).parent().prev();

		//Remove class active
		$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

		//show the previous fieldset
		previous_fs.show();

		//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
			step: function(now) {
				// for making fielset appear animation
				opacity = 1 - now;

				current_fs.css({
					'display': 'none',
					'position': 'relative'
				});
				previous_fs.css({'opacity': opacity});
			}, 
			duration: 600
		});
	});

	$('.radio-group .radio').click(function(){
		$(this).parent().find('.radio').removeClass('selected');
		$(this).addClass('selected');
	});

	$(".submit").click(function(){
		console.log("submit test")
		return false;
	})

});


function requestotp(){
	var requestpass=false;
	$("#loginmsg").text("");
	var digitregexp = /^[6-9]{1}[0-9]{9}$/;
	var mobile = document.forms["msform"]["usermobile"].value;
//	var pass = document.forms["msform"]["userpassword"].value;
	var returnUrl = document.forms["msform"]["returnUrl"].value;
//	var otploginChosen = $("#otplogin").is(":checked");
//	var otpVal = document.forms["msform"]["otpVal"].value;
	var token =  $('input[name="_csrf"]').attr('value'); 
	var type = $("input[name='loginmethod']:checked").val();
	var pass ="OTP_LOGIN";

	var otploginChosen=false;
	if(type == 'OTP'){
		console.log("Tryt OTP");
		otploginChosen=true;
		otpsubmit = false;
		pass="OTP_LOGIN";
	}else if(type == 'PWD'){
		otploginChosen=false;
	}

	$.ajaxSetup({
		headers:
		{ 'X-CSRF-TOKEN': token }
	});
	var jsonObjects={
			"usermobile" : mobile,
			"userpassword" : pass,
			"otpVal" : "NONE",
			"otpLogin" : otploginChosen,
			"returnUrl" : encodeURIComponent(returnUrl),
			"otpSubmit" : otpsubmit,
			"_csrf" : token
	}
	console.log(JSON.stringify(jsonObjects));
	var request;
	request = $.ajax({
		url: "/products/login-otp",
		method: "POST",
		contentType: "application/json",
		data:JSON.stringify(jsonObjects),
		async: false,
		datatype: "json",
		beforeSend: function() {
			console.log("Test disable");

			$("#nextbtn").html("Processing <i class=\"fas fa-spinner fa-spin\"></i>");
			//$("#nextbtn").prop('disabled',true);
			otpsending = true;
		}
	});

	request.done(function(data, textStatus, xhr) {
		console.log("Response received- "+ JSON.stringify(data));
		if (data.statuscode == 0){

			$("#loginmsg").text(data.msg);
			timer= setTimeout(function() {
				console.log("Re-enable OTP")
				otpsending = false;
//				$("#"+sendbutton).prop('disabled',false);
//				$("#"+sendbutton).html("Re-send OTP");
			}, 1*30*1000);
			requestpass = true;
		} else {
			console.log("failed..");
			$("#loginmsg").text(data.errormsg);
			requestpass=false;
		}

	});

	request.fail(function(xhr, textStatus) {
//		alert("Request failed: "+textStatus+" "+ xhr.responseText);
		otpsending = false;
		$("#loginmsg").text("Request failed. Please retry!");
//		location.reload();
		valid=false;
//		$("#").html("Send OTP");
		requestpass=false;
	});
	request.always(function(xhr){
//		$("#").html("Send OTP");
	});
	console.log("Returning- "+ requestpass);
	return requestpass; 
};


function validatesubmit(){
	var requestpass=false, otpval,pass;
	$("#loginmsg").text("");
	var digitregexp = /^[6-9]{1}[0-9]{9}$/;
	var mobile = document.forms["msform"]["usermobile"].value;
	var returnUrl = document.forms["msform"]["returnUrl"].value;
//	var otploginChosen = $("#otplogin").is(":checked");
	var token =  $('input[name="_csrf"]').attr('value'); 
	var type = $("input[name='loginmethod']:checked").val();

	var otploginChosen=false;
	if(type == 'OTP'){
		otploginChosen=true;
		pass="OTP_LOGIN";
		otpsubmit = true;
		otpval = document.forms["msform"]["otpVal"].value;
	}else if(type == 'PWD'){
		var pass = document.forms["msform"]["userpassword"].value;
		var otpval="NONE";
		otploginChosen=false;
		otpsubmit=false;
	}



	$.ajaxSetup({
		headers:
		{ 'X-CSRF-TOKEN': token }
	});
	var jsonObjects={
			"usermobile" : mobile,
			"userpassword" : pass,
			"otpVal" : otpval,
			"otpLogin" : otploginChosen,
			"returnUrl" : encodeURIComponent(returnUrl),
			"otpSubmit" : otpsubmit,
			"_csrf" : token
	}
	console.log(JSON.stringify(jsonObjects));
	var request;
	request = $.ajax({
		url: "/products/login-verify",
		method: "POST",
		contentType: "application/json",
		data:JSON.stringify(jsonObjects),
		async: false,
		datatype: "json",
		beforeSend: function() {
			console.log("Test disable");
//			$("#nextBtn").html("Processing <i class=\"fas fa-spinner fa-spin\"></i>");

			//document.getElementById("sendotpbutton").innerHTML = "Processing <i class=\"fas fa-spinner fa-spin\"></i>";
			$("#login_account").html("Processing <i class=\"fas fa-spinner fa-spin\"></i>");
			$("#login_account").prop('disabled',true);
			otpsending = true;
		}
	});

	request.done(function(data, textStatus, xhr) {
		console.log("Response received- "+ JSON.stringify(data));
		if (data.statuscode == 0){
			if(data.msg=="SUCCESS"){
				window.location.href = returnUrl;
				return true;
			}else{
				$("#loginmsg").text(data.errormsg);
				requestpass=false;
			}
		} else {
			console.log("failed..");
			$("#loginmsg").text(data.errormsg);
			requestpass=false;
		}

	});

	request.fail(function(xhr, textStatus) {
		otpsending = false;
		$("#loginmsg").text("Verification request failed. Please retry!");
//		location.reload();
		valid=false;
//		$("#").html("Send OTP");
		requestpass=false;
	});
	request.always(function(xhr){
//		$("#").html("Send OTP");
	});
	console.log("Returning- "+ requestpass);
	return requestpass; 
};

