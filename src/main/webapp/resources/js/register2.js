var digitregexp = /^([6-9]{1})[0-9]{9}$/;
var otpregexp = /^([0-9]{6})$/;
var letterregexp = /^[a-z A-Z]+$/;
var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

var otpVerified = false;
var otpsendingdisabled = false;
var otpverifydisabled = false;
var getUrl = window.location;
var baseurl = getUrl.origin; //or
var baseurl =  getUrl.pathname.split('/')[1]; 
var otpverified = false;
//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches

$(".next").click(function(){
	console.log("next clicked");
	if(animating) return false;
	
	if(!verifyotp()){
		return false;
	}	
	
	animating = true;
	
	current_fs = $(this).parent();
	next_fs = $(this).parent().next();
	
	//activate next step on progressbar using the index of next_fs
	$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
	
	//show the next fieldset
	next_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({
      'transform': 'scale('+scale+')',
      'position': 'absolute'
    });
			next_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$(".previous").click(function(){
	if(animating) return false;
	animating = true;
	

	
	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();
	
	//de-activate current step on progressbar
	$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
	
	//show the previous fieldset
	previous_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.7 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$(".submit").click(function(){
	return false;
})



$( document ).ready(function() {
	
	$("#otpblock").hide();
//	$("#next-btn").prop("disabled", true);
	$("#verify-otp-btn").prop("disabled", true);
//	validateForm();
});

$("#fnameid,#lnameid,#registermobile,#useremail,#registerpassword").keyup(function(){
	console.log("Validate form")
//	validateForm();
});


function validateForm(){
//	var username = document.forms["registerForm"]["username"].value;
	var fname = document.forms["registerForm"]["fname"].value;
	var lname = document.forms["registerForm"]["lname"].value;
	var registermobile = document.forms["registerForm"]["registermobile"].value;
	var useremail = document.forms["registerForm"]["useremail"].value;
	var password = document.forms["registerForm"]["registerpassword"].value;
	
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
	
	if(cond1 && cond2 && cond3 && cond4){
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


$("#send-otp-btn").click(function(){
	var registermobile = $("#registermobile").val();
	var emailid = $("#useremail").val();
	
	if(registermobile == undefined || registermobile == "" || !digitregexp.test(registermobile)){
		$("#displaymsg").text("Please enter a valid Indian mobile no.");
		return false;
	}if(emailid == undefined || emailid == "" || !emailregexp.test(emailid)){
		$("#displaymsg").text("Please enter a valid email id");
		return false;
	}
		console.log("Mobile no valid. Trigger SMS");
		$("#displaymsg").text("");
		var jsonObjects = {"mobile":registermobile, "module": window.location.pathname};
		
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

			request.done(function(data, textStatus, xhr) {
				if (data == 'SUCCESS'){
//					const options= { positionClass:'toast-center' };
					toastr.success("OTP has been sent to "+ registermobile, '' );
					
					$("#send-otp-btn").val("Sent");
					$("#send-otp-btn").prop("disabled", true);
					otpsendingdisabled = true;
					
					$("#otpblock").show();
					$("#otpblock").addClass("animated fadeIn")
					
					setTimeout(function() {
						console.log("Re-enable OTP")
						otpsendingdisabled = false;
						$("#send-otp-btn").val("Get OTP");
						$("#send-otp-btn").prop("disabled", false);
					}, 1*30*1000);

				} else {
					console.log("Not sent")
//					const options= { positionClass:'toast-center' };
					toastr.warning("OTP could not be sent to "+ registermobile, '');
					otpsendingdisabled = false;
					$("#send-otp-btn").val("Get OTP");
					$("#send-otp-btn").prop("disabled", false);
				}
				console.log('data- ' + data + " - "+ textStatus + " -" + xhr );


			});

			request.fail(function(xhr, textStatus) {
//				alert("Request failed: "+textStatus+" "+ xhr.responseText);
				toastr.error("OTP sending error! Please try again.", '' );
				otpsendingdisabled = false;
				$("#send-otp-btn").val("Get OTP");
//				location.reload();
				$("#send-otp-btn").prop("disabled", false);
			});

		}else{
			console.log("OTP disabled..");
		}
		
		console.log("OTP send complete")
	
});


$("#otpbox").keyup(function(){
	var otpinfo = $("#otpbox").val();
	var registermobile = $("#registermobile").val();
	
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
	verifyotp();
});

function verifyotp(){
	
	var registermobile = $("#registermobile").val();
	var otpinfo = $("#otpbox").val();
	
	if(registermobile == "" || !digitregexp.test(registermobile) ){
		$("#displaymsg").text("Please enter a valid Indian mobile no.")
	}else if(otpinfo == "" || !otpregexp.test(otpinfo) ){
		$("#displaymsg").text("Please enter OTP coorectly")
	}else{
		console.log("Mobile no valid. Trigger SMS");
		
		var jsonObjects = {"mobile":registermobile,"otpinfo":otpinfo, "module": "REGISTRATION"};
		
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
				console.log("Valdidation status- "+ data);
				if (data == 'SUCCESS'){
//					const options= { positionClass:'toast-center' };
//					toastr.success("OTP has been sent to "+ registermobile, '' );
					otpVerified=true;
					$("#otpverify").text("Verified");
					$("#verify-otp-btn").prop("disabled", true);
					otpverifydisabled = true;

				} else {
					console.log("Not verified")
//					const options= { positionClass:'toast-center' };
					toastr.error("OTP validaiton failed for "+registermobile , '');
					otpverifydisabled = false;
					$("#otpverify").text("Get OTP");
					$("#send-otp-btn").prop("disabled", false);
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
	
	return otpVerified;
	
}
