var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
	// This function will display the specified tab of the form ...
	var x = document.getElementsByClassName("tab");
	x[n].style.display = "block";
	// ... and fix the Previous/Next buttons:
	if (n == 0) {
		document.getElementById("prevBtn").style.display = "none";
	} else {
		document.getElementById("prevBtn").style.display = "inline";
	}
	if (n == (x.length - 1)) {
		document.getElementById("nextBtn").innerHTML = "Submit";

		$("#paymodeconf").text($("#input[name='pay']:checked").val());

		var redeemallflag = $("input[name='redeemAll']:checked").val();

		if (redeemallflag) {
			$("#redeemamountconf").text("Redeem All");
		} else {
			$("#redeemamountconf").text(
					Number($("#redeemamount").val()).toFixed(2));

		}

	} else {
		document.getElementById("nextBtn").innerHTML = "Next";
	}
	// ... and run a function that displays the correct step indicator:
	fixStepIndicator(n)
}

function nextPrev(n) {
//	console.log("Current tab- "+currentTab  + " n-"+ n);
	$("#mandateField2").text("");
	// This function will figure out which tab to display
	var x = document.getElementsByClassName("tab");
	// Exit the function if any field in the current tab is invalid:


	if (n == 1 && currentTab == 0) {
		//...the form gets submitted:
//		console.log("Not valid");
		//document.getElementById("regForm").submit();
		var flag= validateRedeemAmount();
		if(!flag){
			return flag;
		}
	}

	if (n == 1 && currentTab == 1) {
		//		console.log("Display progress");
		var policyagree = $("input[name='agreePolicy']:checked").val();
		
		if(policyagree == undefined){
			$("#mandateField2").text("Please agree to the policy for transaction.");
			return false;
		}
		
		$("#display_progress").css({
			"display" : "block"
		});

		$("#prevBtn").attr("disabled", "disabled");
		$("#nextBtn").attr("disabled", "disabled");

	}

	if (n == 1 && !validateForm()) {
		//console.log("Validation failed.");
		return false;
	}
	// Hide the current tab:
	x[currentTab].style.display = "none";
	// Increase or decrease the current tab by 1:
	currentTab = currentTab + n;
	// if you have reached the end of the form... :
	if (currentTab >= x.length) {
		//...the form gets submitted:
		//console.log("Submit form");
		document.getElementById("regForm").submit();
		return false;
	}
	// Otherwise, display the correct tab:
	showTab(currentTab);
}

function validateForm() {
	// This function deals with validation of the form fields
	var x, y, i, valid = true;
	x = document.getElementsByClassName("tab");
	y = x[currentTab].getElementsByTagName("input");
	// A loop that checks every input field in the current tab:

	for (i = 0; i < y.length; i++) {
		// If a field is empty...
		if (y[i].value == "") {
			console.log(y[i]);
			// add an "invalid" class to the field:
			y[i].className += " invalid";
			// and set the current valid status to false:
			valid = false;
		}
	}
	// If the valid status is true, mark the step as finished and valid:
	if (valid) {
		document.getElementsByClassName("step")[currentTab].className += " finish";
	}
	return valid; // return the valid status
}

function fixStepIndicator(n) {
	// This function removes the "active" class of all steps...
	var i, x = document.getElementsByClassName("step");
	for (i = 0; i < x.length; i++) {
		x[i].className = x[i].className.replace(" active", "");
	}
	//... and adds the "active" class to the current step:
	x[n].className += " active";
}

$('#redeemamount').on(
		'keyup',
		function() {
			//Verify amount is less than available amount

			return validateRedeemAmount();

		});


function validateRedeemAmount(){
	var availamount = Number($("#marketVal").val());
	var redeemamount = Number($("#redeemamount").val());
	var redeemall = $("input[name='redeemAll']:checked").val();
	//console.log("The text has been changed - "+  f);
	var flag=true;
	if (redeemall == undefined) {
		if (!isNaN(redeemamount)) {
			$("#invalidamnt").text("");
			//$("#nextBtn").removeAttr("disabled");
			if (redeemamount > availamount) {
				//  console.log("Invalid")
				$("#invalidamnt").text("Entered amount above available amount!");
				$("#nextBtn").attr("disabled", "disabled");
				flag=false;
			} else if (redeemamount <= 0) {
				$("#invalidamnt").text("Invalid withdrwal amount");
				$("#nextBtn").attr("disabled", "disabled");
				flag=false;
			} else {

				$("#invalidamnt").text("");
				$("#nextBtn").removeAttr("disabled");
			}
		} else {
			$("#invalidamnt").text("Invalid number");
			$("#nextBtn").attr("disabled", "disabled");
			flag=false;
		}

	}else{
//		console.log("Redeem all checked.. NO need to check data");
		$("#invalidamnt").text("");
		$("#nextBtn").removeAttr("disabled");
	}
//	console.log("Return - "+ flag);
	return flag;
}


$(document).ready(function() {
	var f = $("input[name='redeemAll']:checked").val();
	//console.log("On load- "+  f);
	if (f == undefined) {
		$("#redeemamntbox").show();
	}
	if (f) {
		$("#redeemamntbox").hide();
	}
});

$("#redeemAllCheckBox").change(function() {
	var f = $("input[name='redeemAll']:checked").val();
	//console.log("The text has been changed - "+  f);
	if (f == undefined) {
		$("#redeemamntbox").show();
	}
	if (f) {
		$("#redeemamntbox").hide();
		$("#invalidamnt").text("");
		$("#nextBtn").removeAttr("disabled");
	}

});