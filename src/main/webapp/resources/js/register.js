var digitregexp = /^([6-9]{1})[0-9]{9}$/;
var otpregexp = /^([0-9]{6})$/;
var letterregexp = /^[a-z A-Z]+$/;
var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

var otpVerified = false;
var otpsendingdisabled = false;
var otpverifydisabled = false;
var timervar;
var getUrl = window.location;
var baseurl = getUrl.origin; //or
var baseurl =  getUrl.pathname.split('/')[1]; 

$( document ).ready(function() {
	$("#verify-otp-btn").prop("disabled", true);
	validateForm();
	var otpverified = document.forms["registerForm"]["otpverified"].value;
	console.log("Data- "+ otpverified)
	if(otpverified=='Y'){
		$("#send-otp-btn").prop("disabled",true);
		$("#send-otp-btn").html("<i class=\"fas fa-check\"></i>");
		$("#send-otp-btn").removeClass("btn-deep-orange");
		$("#send-otp-btn").addClass("btn-success");
		otpVerified = true;
		otpsendingdisabled = true;
		otpverifydisabled = true;
	}
	
});

$("#fnameid,#lnameid,#registermobile,#useremail,#registerpassword").keyup(function(){
	console.log("Validate form")
	validateForm();
});


function validateForm(){
//	var username = document.forms["registerForm"]["username"].value;
	var fname = document.forms["registerForm"]["fname"].value;
	var lname = document.forms["registerForm"]["lname"].value;
	var registermobile = document.forms["registerForm"]["registermobile"].value;
	var useremail = document.forms["registerForm"]["useremail"].value;
	var password = document.forms["registerForm"]["registerpassword"].value;
	var otpverified = document.forms["registerForm"]["otpverified"].value;
	
	var mobdes = document.getElementById("mobico");
	var namedes = document.getElementById("userico");
	var emaildes = document.getElementById("mailico");
	var passdes = document.getElementById("passico");
	
	var formInvalid= true;
	
	var cond1 = false, cond2 = false,cond3 = false,cond4 = false, cond5=false;
	
 	if((fname == "" || !letterregexp.test(fname)) || (lname == "" || !letterregexp.test(lname))){
 		namedes.style.color="#e83a3a";
	}else{
		cond1 = true;
		namedes.style.color="rgb(60, 177, 59)";
	}
 	
 	if( password == undefined || password == "" || password.length < 8){
 		passdes.style.color="#e83a3a";
	}else{
		cond2= true;
		passdes.style.color="rgb(60, 177, 59)";
	}
 	
 	if(useremail == "" || !emailregexp.test(useremail)){
 		emaildes.style.color="#e83a3a";
	}else{
		cond3= true;
		emaildes.style.color="rgb(60, 177, 59)";
	}
 	
	if(registermobile == "" || !digitregexp.test(registermobile)){
		mobdes.style.color="#e83a3a";
	}else{
		cond4= true;
		mobdes.style.color="rgb(60, 177, 59)";
	}
	
	if(otpverified =='Y'){
		cond5 = true;
	}
	
	if(cond1 && cond2 && cond3 && cond4 && cond5){
		document.getElementById("registerSubmit").disabled = false;
	}else{
		document.getElementById("registerSubmit").disabled = true;
	}
	
}

var input = document.getElementById("registerpassword");
input.addEventListener("keyup", function(event) {

	  // If "caps lock" is pressed, display the warning text
	  if (event.getModifierState("CapsLock")) {
	    $("#msg2").html("Caps lock ON!");
	  } else {
		  $("#msg2").html("");
	  }
	});


$("form").submit(function(){
	$("#registerSubmit").prop("disabled",true);
	$("#registerSubmit").html("Processing <i class='fas fa-spinner fa-spin'>");
	return true;
});

$("#send-otp-btn").click(function(){
	var registermobile = document.forms["registerForm"]["registermobile"].value;
	var sessionid = document.forms["registerForm"]["sessionid"].value;
	if(registermobile == "" || !digitregexp.test(registermobile)){
		$("#displaymsg").text("Please enter a valid Indian mobile no.")
	}else{
		console.log("Mobile no valid. Trigger SMS");
		
		var jsonObjects = {"mobile":registermobile, "module": window.location.pathname, "submodule": "REGISTRATION", "sessionid": sessionid};
		
		if(!otpsendingdisabled){

//			$(".otp-val").show();

			//alert(state);
			console.log("Trigger otp call")
			var request;
			request = $.ajax({
				url: basepathdata+"/api/send-otp",
				method: "POST",
				contentType: "application/json",
				data: JSON.stringify(jsonObjects),
				async: true,
				datatype: "json",
				beforeSend: function() {
					otpsendingdisabled = true;
					$("#otptext").text("Sending..");
					$("#send-otp-btn").prop("disabled", true);
					
				}
			});

			request.done(function(msg, textStatus, xhr) {
				var data = JSON.parse(msg);
				console.log("Data- "+ data + "-> "+ data.statuscode)
				if (data.statuscode == '0'){
//					const options= { positionClass:'toast-center' };
					toastr.success(data.msg);
					
					$("#otptext").text("Sent");
					$("#send-otp-btn").prop("disabled", true);
					$("#otpblock").removeClass("d-none");
					$("#otpblock").addClass("d-block");
					
					otpsendingdisabled = true;
					timervar = setTimeout(function() {
						console.log("Re-enable OTP")
						otpsendingdisabled = false;
						$("#otptext").text("Resend");
						$("#send-otp-btn").prop("disabled", false);
					}, 1*30*1000);

				} else {
					console.log("Not sent")
//					const options= { positionClass:'toast-center' };
					toastr.warning("OTP could not be sent to "+ registermobile, '');
					otpsendingdisabled = false;
					$("#otptext").text("Get OTP");
					$("#send-otp-btn").prop("disabled", false);
				}
				console.log('data- ' + data + " - "+ textStatus + " -" + xhr );


			});

			request.fail(function(xhr, textStatus) {
//				alert("Request failed: "+textStatus+" "+ xhr.responseText);
				toastr.error("OTP sending error! Please try again.", '' );
				otpsendingdisabled = false;
				$("#otptext").text("Get OTP");
//				location.reload();
				$("#send-otp-btn").prop("disabled", false);
			});

		}else{
			console.log("OTP disabled..");
			$("#send-otp-btn").prop("disabled",true);
			if(otpVerified){
				alert("Mobile no verified. Proceed with submission.");
			}
		}
		
	}
	
	
});


$("#otpbox").keyup(function(){
	var otpinfo = document.forms["registerForm"]["otpbox"].value;
	var registermobile = document.forms["registerForm"]["registermobile"].value;
	
	if(otpinfo != "" && otpregexp.test(otpinfo)){
		
		if(registermobile != "" && digitregexp.test(registermobile)){
			$("#verify-otp-btn").prop("disabled", false);
		}else{
			$("#displaymsg").text("Mobile no is required first.");
		}
	}else{
		$("#verify-otp-btn").prop("disabled", true);
	}
	
});

$("#verify-otp-btn").click(function(){
	var registermobile = document.forms["registerForm"]["registermobile"].value;
	var otpinfo = document.forms["registerForm"]["otpbox"].value;
	var sessionid = document.forms["registerForm"]["sessionid"].value;
	
	if(registermobile == "" || !digitregexp.test(registermobile) ){
		$("#displaymsg").text("Please enter a valid Indian mobile no.")
	}else if(otpinfo == "" || !otpregexp.test(otpinfo) ){
		$("#displaymsg").text("Please enter OTP coorectly")
	}else{
		console.log("Mobile no valid. Trigger SMS");
		
		var jsonObjects = {"mobile":registermobile,"otpinfo":otpinfo, "module": window.location.pathname, "submodule": "REGISTRATION", "sessionid": sessionid};
		
		if(!otpverifydisabled){

//			$(".otp-val").show();

			//alert(state);
			console.log("Trigger otp verify")
			var request;
			request = $.ajax({
				url: basepathdata+"/api/verify-otp",
				method: "POST",
				contentType: "application/json",
				data: JSON.stringify(jsonObjects),
				async: true,
				datatype: "json",
				beforeSend: function() {
					otpverifydisabled = true;
					$("#otpverify").text("Checking..");
					$("#verify-otp-btn").prop("disabled", true);
					
				}
			});

			request.done(function(data, textStatus, xhr) {
				if (data == 'SUCCESS'){
//					const options= { positionClass:'toast-center' };
//					toastr.success("OTP has been sent to "+ registermobile, '' );
					
					$("#otpverify").text("Verified");
					$("#verify-otp-btn").prop("disabled", true);
					otpverifydisabled = true;
					otpsendingdisabled = true;
					clearTimeout(timervar);
					$("#otpblock").removeClass("d-block");
					$("#otpblock").addClass("d-none");
					$("#send-otp-btn").prop("disabled",true);
					$("#send-otp-btn").html("<i class=\"fas fa-check\"></i>");
					$("#send-otp-btn").removeClass("btn-deep-orange");
					$("#send-otp-btn").addClass("btn-success");
					document.forms["registerForm"]["otpverified"].value ='Y';
					validateForm();
				} else {
					console.log("Not verified")
//					const options= { positionClass:'toast-center' };
					toastr.error(data);
					otpverifydisabled = false;
					$("#otpverify").text("Verify");
					$("#verify-otp-btn").prop("disabled", false);
					$("#send-otp-btn").prop("disabled", true);
				}
				console.log('data- ' + data + " - "+ textStatus + " -" + xhr );


			});

			request.fail(function(xhr, textStatus) {
				toastr.error("OTP sending error! Please try again.", '' );
				otpverifydisabled = false;
				$("#otptext").text("Verify");
				$("#verify-otp-btn").prop("disabled", false);
			});

		}else{
			console.log("OTP verification disabled..");
		}
		
		
	}
	
	
});
