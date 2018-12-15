
//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
var distance = 600000;


function countDownTimer(){
	//var countDownDate = new Date("Jun 13, 2018 15:37:25").getTime();
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
			clearInterval(x);
			document.getElementById("timer").innerHTML = "EXPIRED!";
			$('#exampleModalCenter').modal('hide');
		}
	}, 1000);

}

function closePopUp(){
	$('#exampleModalCenter').modal('hide');
}


$(".next").click(function(){
	if(animating) return false;
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
			scale = 0.8 + (1 - now) * 0.2;
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
});

$(document).ready(function(){
	$("#panbox").keyup(function(){
		validatePAN();
	});
});

function validatePAN(){
	//console.log("PAN validation");
	var regex= new RegExp ('^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}$');
	var pan = $("#panbox").val();
	if(!regex.test(pan)){
		$("#panbox").css('border-left','2px solid red');
		$("#kycdetails").attr("disabled", true);
	}else{
		$("#panbox").css('border-left','2px solid green');
		$("#kycdetails").attr("disabled", false);
	}
}


$(document).ready(function(){
	$("#otpbox").keyup(function(){
		validateOTP();
	});
});

function validateOTP(){
	//console.log("PAN validation");
	var regex= new RegExp ('^[0-9]+$');
	var pan = $("#otpbox").val();
	if(!regex.test(pan)){
		$("#otpbox").css('border','2px solid #ff6a6a');
		$("#otpsubmit").attr("disabled", true);
	}else{
		$("#otpbox").css('border','2px solid #43c253');
		$("#otpsubmit").attr("disabled", false);
	}
}




