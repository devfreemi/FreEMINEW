$(document).ready(function() {
	$("#ifsc").blur(function() {
		var regex = new RegExp('^[a-zA-Z]{4}[0][0-9a-zA-Z]{6}$');
//		console.log("IFSC search");
		var ifsc = $("#ifsc").val();

		if (regex.test(ifsc)) {
			$("#ifsc").css('border-left','2px solid #43c253');
			$.get("https://ifsc.razorpay.com/"+ ifsc,function(data,status) {
//				console.log(data.BANK);
				//console.log(data.BRANCH);
				//console.log(data.ADDRESS);

				$("#bankCity").val(data.CITY);
				$("#branch").val(data.BRANCH);
				$("#bankAddress").val(data.ADDRESS);
				$("#bankName").val(data.BANK);
				$("#bankState").val(data.STATE);
				$("#invalidifsc").text("");
			})
			.fail(function(data,status) {
				//console.log("Error retrieving data");
				//console.log(data);
//				console.log(status);
				$("#invalidifsc").text("Invalid IFSC code");
				$("#bankCity").val("");					
				$("#branch").val("");
				$("#bankAddress").val("");
				$("#bankName").val("");
				$("#bankState").val("");
			});
		} else {
			$("#ifsc").css('border-left','2px solid #ff6a6a');
		}

	});
});


//selectfund

$(document).on("click", "#radioamount", function() {
	var x = $("input[type='radio'][name='options']:checked").val();
	/* console.log("Test" + $("input[type='radio'][name='options']:checked").val()); */
	$("#amount").val(x);
});

function customamount() {
	if ($('input:radio[name="options"]:checked')) {
		console.log("Check active")
		$('label').removeClass('active');
	}

}

$(document).on("click", "#transactionType1", function() {
	$("#sipbox").show();
});

$(document).on("click", "#transactionType2", function() {
	$("#sipbox").hide();
});



/*------------------------------------------------------------------------------------------------------------------------*/

//BSE Invest form register
function showTab(n) {
	// This function will display the specified tab of the form ...
	var x = document.getElementsByClassName("tab");
	//	console.log(n);
	x[n].style.display = "block";

	// ... and fix the Previous/Next buttons:
	if (n == 0) {
		document.getElementById("prevBtn").style.display = "none";
	} else {
		document.getElementById("prevBtn").style.display = "inline";
	}
	if (n == (x.length - 1)) {
		document.getElementById("nextBtn").innerHTML = "Submit";
	} else {
		document.getElementById("nextBtn").innerHTML = "Next";
	}

	// ... and run a function that displays the correct step indicator:
	fixStepIndicator(n)
}

function nextPrev(n) {
	// This function will figure out which tab to display
	// console.log("n- " + n);
	// console.log("Current tab- " + currentTab);

	if (n == 1 && currentTab == 2) {
		console.log("Display progress");
		$("#display_progress").css({
			"display" : "block"
		});
		$("#prevBtn").attr("disabled", "disabled");
		$("#nextBtn").attr("disabled", "disabled");
	}
	var x = document.getElementsByClassName("tab");
	// Exit the function if any field in the current tab is invalid:
	if (n == 1) {
		//console.log("1st form")
		if (!validBasicForm())
			return false;
	}

	if (n == 2) {
		//console.log("2nd form");

		if (!validBankForm())
			return false;
	}

	// Hide the current tab:
	x[currentTab].style.display = "none";
	// Increase or decrease the current tab by 1:
	currentTab = currentTab + n;
	// if you have reached the end of the form... :
	if (currentTab == (x.length - 1)) {
		populateConfirmPage();
	}

	if (currentTab >= x.length) {
		//...the form gets submitted:
		document.getElementById("regForm").submit();

		return false;
	}
	// Otherwise, display the correct tab:
	showTab(currentTab);
	setDefaultvalues();
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
			// add an "invalid" class to the field:
			y[i].className += " invalid";
			// and set the current valid status to false:

			valid = false;
		} else {
			y[i].className += " valid";
		}
	}
	// If the valid status is true, mark the step as finished and valid:
	if (valid) {
		document.getElementsByClassName("step")[currentTab].className += " finish";
	}
	return valid; // return the valid status
}

function validBasicForm() {
	if ($("#investorDOB").val() == "") {
		console.log("DOB missing")
		$("#mandateField").text("Fields are mandatory");
		return false;
	}

	return true;
}

function validBankForm() {
	if ($("#ifsc").val() == "") {
		console.log("IFSC missing")
		$("#mandateField").text("IFSC code mandatory");
		return false;
	}

	return true;
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

function setDefaultvalues() {

	var x = $("input[name='nominee.isNominate']:checked").val();

	if (x == "N") {

		$(".showNomineeForm").hide();
	} else {
		$(".showNomineeForm").show();
	}

}

function populateConfirmPage() {
	//	console.log("last page called");
	//	console.log(document.getElementById("holdingMode").value);

	//Personal Details
	$("#nameDisplay").text($("#invName").val());
	$("#PANDisplay").text($("#pan1").val());

	$("#mobileDisplay").text($("#mobile").val());
	$("#emailDisplay").text($("#email").val());
	$("#DOBDisplay").text($("#investorDOB").val());
	$("#incomeslabDisplay").text($("#annualIncome").val());
	$("#occupationDisplay").text($("#occupation :selected").text());
	$("#birthPlaceDisplay").text($("#birthPlace").val());
	$("#genderDisplay").text($("input[name='gender']:checked").parent().text());
	$("#maritalDisplay").text(
			$("input[name='maritalStatus']:checked").val());

	//	$("#").text($("#genderDisplay").val());

	$("#taxStatusDisplay").text($("#taxStatus :selected").text());
	$("#holdingModeDisplay").text("Individual");
	/* $("#taxStatusDisplay").text("taxStatus"); */
	
	
	$("#secondapplicantName").text($("#applicant2Val").val());
	$("#secondapplicantPan").text($("#pan2").val());

	//$("#").text($("#").val());
	/* 		var x = "Y";
	 if (document.getElementById("nominate").checked) {
	 x = "N";
	 }

	 //Nominee Details
	 $("#nomineeSelected").text(x);
	 $("#isNominate").val(x); */

	var x = $("input[name='nominee.isNominate']:checked").val();

	if (x == "N") {
		$(".NomineeDetailsDisplay").hide();
		$("#nomineeSelected").text("No nominee chosen");
	} else {
		$(".NomineeDetailsDisplay").show();
		$("#nomineeSelected").text(x);
	}

	$("#nomineeNameDisplay").text($("#nomineeName").val());
	$("#nomineeDOBDisplay").text($("#nomineeDOB").val());
	$("#nomineeaddress1Display").text($("#nomineeAddress1").val());
	$("#nomineeaddress2Display").text($("#nomineeAddress2").val());
	$("#nomineeCityDisplay").text($("#nomineeCity").val());
	$("#nomineeStateDisplay").text($("#nomineeState").val());
	$("#nomineePercentageDisplay").text($("#nomineePercent").val());
	$("#nomineeRelationDisplay").text($("#relation").val());
	//	$("#").text($("#").val());
	//	$("#").text($("#").val());

	//Investment Details
	//$("#investmentFrequency").text($("#investFrequency").val());
	$("#monthlyInvestDate").text($("#monthlyInvDate").val());
	$("#startFrom").text(
			($("#investStartMonth").val()) + "-"
					+ ($("#investStartYear").val()));
	$("#investTillDate").text(
			($("#investEndMonth").val()) + "-"
					+ ($("#investEndYear").val()));

	//Account Details
	$("#accHolderDisplay").text($("#invName").val());
	/*$("#paymentModeDisplay").text(
			$("input[name='gridRadios']:checked").val());*/
	$("#paymentModeDisplay").text(
			$("#dividendPayMode :selected").text());
	$("#accountTypeDisplay").text($("#accountType :selected").text());
	$("#accNumberDisplay").text($("#accountno").val());

	$("#ifscDisplay").text($("#ifsc").val());
	$("#bankNameDisplay").text($("#bankName").val());
	$("#branchDisplay").text($("#branch").val());
	$("#branchAddressDisplay").text($("#bankAddress").val());
	$("#bankCityDisplay").text($("#bankCity").val());
	$("#branchStateDisplay").text($("#bankState").val());
	//	$("#").text($("#").val());

}

//----------------------------------------------------------------------------------------------------------------------





