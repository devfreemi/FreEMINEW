var minsip=0;
var minlumpsum=0;

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
	$("input[type='radio'][name='options']:checked").css("background","red");
});

function customamount() {
	if ($('input:radio[name="options"]:checked')) {
//		console.log("Check active")
		$('#radioamount label').removeClass('active');
		

	}

}

$(document).on("click", "#transactionType1", function() {
	$("#sipbox").show();
	$("#minvalreq").text(minsip);
	
});

$(document).on("click", "#transactionType2", function() {
	$("#sipbox").hide();
	$("#minvalreq").text(minlumpsum);
});

$(document).on("click", "#growthCategory", function() {
//	$("#sipbox").show();
//	$("#minvalreq").text(minsip);
	console.log("Growth selected");
//	$("#minvalreq").val($(""));
	$("#reinvcode").hide();
	$("#growthcode").show();
	
	
});

$(document).on("click", "#reinvestcategory", function() {
//	$("#sipbox").hide();
//	$("#minvalreq").text(minlumpsum);
	console.log("Re-invest selected");
	$("#growthcode").hide();
	$("#reinvcode").show();
	
});



/*------------------------------------------------------------------------------------------------------------------------*/

//BSE Invest form register
function showTab(n) {
	// This function will display the specified tab of the form ...
	var x = document.getElementsByClassName("tab");
//	console.log("Showtab- "+n);
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
//	 console.log("nextPrev- " + n);
//	 console.log("Current tab- " + currentTab);

	if (n == 1 && currentTab == 3) {
//		console.log("Display progress");
		$("#display_progress").css({
			"display" : "block"
		});
		$("#prevBtn").attr("disabled", "disabled");
		$("#nextBtn").attr("disabled", "disabled");
	}
	var x = document.getElementsByClassName("tab");
	// Exit the function if any field in the current tab is invalid:
	var z =currentTab + n;
//	console.log("z- "+ z);
	if (z == 1) {
//		console.log("1st form")
		if (!validBasicForm())
			return false;
	}

	if (z == 2) {
//		console.log("2nd form");

		if (!validBankForm())
			return false;
	}
	
	if (z == 3) {
		if (!validFatcaForm())
			return false;
	}

	// Hide the current tab:
	x[currentTab].style.display = "none";
	// Increase or decrease the current tab by 1:
	currentTab = currentTab + n;
	// if you have reached the end of the form... :
	if (currentTab == (x.length - 1)) {
//		console.log("Show confirm page..." + currentTab);
		populateConfirmPage();
	}

	if (currentTab >= x.length) {
		//...the form gets submitted:
//		console.log("Submit the form ... "+ z);
		document.getElementById("regForm").submit();

		return false;
	}
	// Otherwise, display the correct tab:
//	console.log("Calling showtab- "+n);
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
	$("#nextBtn").removeAttr("disabled");
	$("#mandateField").text("");
	var dt= $("#investorDOB").val();
	
	var splitdt = dt.split("/");
	dob = new Date(splitdt[2]+"-"+splitdt[1]+"-"+splitdt[0]);
	var today = new Date();
	var diff = Math.floor((today - dob)/(1000*60*60*24*365));
	
	var gender = $("input[name='gender']:checked").val();
	
	if(gender == undefined){
		$("#mandateField").text("Please select your gender");
		return false;
	}
	
	if(dob>today || diff <18 || diff > 65){
		$("#mandateField").text("Investor age must be between 18-65 years as per document.");
		return false;
	}
	
	if ($("#investorDOB").val() == "" || $("#invName").val() == "" || $("#email").val() == "" ||$("#mobile").val() == "") {
		$("#mandateField").text("Please provide mandatory fields data.");
		return false;
	}

	return true;
}

function validBankForm() {
	$("#nextBtn").removeAttr("disabled");
	$("#mandateField").text("");
	if ($("#accountno").val() == "" || $("#ifsc").val() == "" || $("#address1").val() == "" || $("#address_city").val() == "" || $("#pinCode").val() == "" || $("#addState :selected").val() == "" ) {
		$("#mandateField").text("Please provide mandatory fields data.");
		return false;
	}

	return true;
}

function validFatcaForm() {
	$("#nextBtn").removeAttr("disabled");
	$("#mandateField").text("");
	
	if ($("#birthplace").val() == "" || $("#occupationType :selected").val() == "" || $("#incomeslab :selected").val() == "" || $("#wealthsource :selected").val() == "" || $("#politicalview :selected").val() == "" ) {
		$("#mandateField").text("Please provide mandatory fields data.");
		return false;
	}

	return true;
}

function validConfirmForm() {
	$("#mandateField").text("");
	if($("input[name='ubo']:checked").val()){
		$("#nextBtn").removeAttr("disabled");
	}
	if($("input[name='ubo']:checked").val() == undefined){
		$("#nextBtn").attr("disabled", "disabled");
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
	
	if($("input[name='ubo']:checked").val()){
		$("#nextBtn").removeAttr("disabled");
	}
	if($("input[name='ubo']:checked").val() == undefined){
		$("#nextBtn").attr("disabled", "disabled");
	}

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
	$("#holdingModeDisplay").text($("#holdingMode :selected").text());
	/* $("#taxStatusDisplay").text("taxStatus"); */
	
	
	$("#secondapplicantName").text($("#applicant2Val").val());
	$("#secondapplicantPan").text($("#pan2").val());
	$("#pan1kycverifyDisplay").text($("#pan1kycverified :selected").text());
	if($("#pan1kycverified :selected").val() == 'N'){
		$("#pan1kycverifyDisplay").css({"color":"white","background":"#ef2e2eed","padding":"2px 10px","border-radius":"2px"});
	}else{
		$("#pan1kycverifyDisplay").css({"color":"white","background":"#1cad1ced","padding":"2px 10px","border-radius":"2px"});
	}


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
	
//	ADdress Details
	$("#add1Display").text($("#address1").val());
	$("#add2Display").text($("#address2").val());
	$("#add3Display").text($("#address3").val());
	$("#addCityDisplay").text($("#address_city").val());
	$("#addpincodeDisplay").text($("#pinCode").val());
	$("#addSateDisplay").text($("#addState :selected").text());
	

	//Investment Details
	/*
	$("#monthlyInvestDate").text($("#monthlyInvDate").val());
	$("#startFrom").text(($("#investStartMonth").val()) + "-"+ ($("#investStartYear").val()));
	$("#investTillDate").text(($("#investEndMonth").val()) + "-"+ ($("#investEndYear").val()));
	*/
	
	//Account Details
	$("#accHolderDisplay").text($("#invName").val());
	
	$("#paymentModeDisplay").text($("#dividendPayMode :selected").text());
	$("#accountTypeDisplay").text($("#accountType :selected").text());
	$("#accNumberDisplay").text($("#accountno").val());

	$("#ifscDisplay").text($("#ifsc").val());
	$("#bankNameDisplay").text($("#bankName").val());
	$("#branchDisplay").text($("#branch").val());
	$("#branchAddressDisplay").text($("#bankAddress").val());
	$("#bankCityDisplay").text($("#bankCity").val());
	$("#branchStateDisplay").text($("#bankState").val());
		
	$("#branchStateDisplay").text($("#bankState").val());
	
	
//	FATCA
	var f = $("input[name='fatcaDetails.usCitizenshipCheck']:checked").val();
//	console.log("US citizen> "+ f);
	
	if(f){
		$("#uscitizenshipcheckdisplay").text("Not a citizen of US/Canada");
		$("#uscitizenshipcheckdisplay").css({"color":"white","background":"rgba(26, 115, 53, 0.93)","padding":"2px 10px","border-radius":"2px"});
		
	}else{
		$("#uscitizenshipcheckdisplay").text("Citizen of US/Canada");
		$("#uscitizenshipcheckdisplay").css({"color":"white","background":"#ef2e2eed","padding":"2px 10px","border-radius":"2px"});
	}
	
	$("#birthplacedisplay").text($("#birthplace").val());
	$("#fathernamedisplay").text($("#fathername").val());
	$("#spousenamedisplay").text($("#spousename").val());
	$("#wealthsourcedisplay").text($("#wealthsource :selected").text());
	$("#incomeslabdisplay").text($("#incomeslab :selected").text());
	$("#occupationtypedisplay").text($("#occupationType :selected").text());
	$("#politicalviewdisplay").text($("#politicalview :selected").text());
	
	

}


function validateFundForm(){
	var panregex = new RegExp('^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}$');
	var radioValue = $("input[name='investype']:checked").val();
	var pandata = $("#panval").val();
	if(!(panregex.test(pandata))){
		$("#selectmsg").text("Invalid PAN number format!");
		return false;
	}else{
		$("#selectmsg").text("");
	}
	
//	console.log(radioValue);
	if(radioValue == 'SIP'){
		/*var sipdate = $("input[name='sipDate']:checked").val();
		console.log("SIp Date- "+ sipdate)
		if(typeof sipdate === 'undefined'){
			$("#selectmsg").text("Please select your SIP date");
			return false;
		}else{
			$("#selectmsg").text("");
		}*/
		
		var minval = $("#minvalreq").text();
		if(Number(minval) == 0){
			$("#selectmsg").text("Investment not available for current category. Please select another.");
			return false;
		}
		
		
		var sipdate = $("#sipOtherDates :selected").text();
//		console.log("SIp Date- "+ sipdate)
		if(typeof sipdate === 'undefined'){
			$("#selectmsg").text("Please select your SIP date");
			return false;
		}else{
			$("#selectmsg").text("");
		}
		
	}
	
//	validate amount
	var minimumPurchase = 0;
	 var purchaseamount = $("#amount").val();
	 
	 minimumPurchase=$("#minvalreq").text();
	 
//	 console.log(parseFloat(purchaseamount).toFixed(2) +  " - "+ minimumPurchase);
	  if(!isNaN(purchaseamount)){
		  console.log("NOT NAN");
//		  $("#invalidamnt").text("");
		  //$("#nextBtn").removeAttr("disabled");
	  if(parseFloat(purchaseamount) < parseFloat(minimumPurchase) ){
		  console.log("Amount is less");
		  $("#selectmsg").text("Minimum purchase amount criteria not met");
//		  $("#nextBtn").attr("disabled", "disabled");
		  return false;
	  }else{
		  console.log("valid");
		  $("#selectmsg").text("");
//		  $("#nextBtn").removeAttr("disabled");
	  }
	  }else{
		  /*$("#invalidamnt").text("Invalid number");
		  $("#nextBtn").attr("disabled", "disabled");*/
		  conosle.log("NAN")
		  $("#selectmsg").text("Invalid number");
		  return false;
	  }
	  
	  if(purchaseamount < 0){
		  $("#selectmsg").text("Invalid entry!");
		  return false;
	  }
	
	
	return true;
}



function bseinvest(mfCode,reinvCode, mfName, lumpsumMinimum, amcCode, sipMin,sipDates,rtaAgent,amcicon) {
//	console.log("Reached- " + mfCode + " " + mfName);
	var radioValue = $("input[name='investype']:checked").val();
//	console.log("Selected- " + radioValue);
//	console.log("SIP dates- "+ sipDates);
	minsip=sipMin;
	minlumpsum=lumpsumMinimum;
	$("#myModal").modal();
	$("#schemeNameTitle").text(mfName);
	$("#schemeName").val(mfName);
	$("#rtaAgent").val(rtaAgent);
	var dtarray = sipDates.split(",");
	
	if(amcicon != ''){
		document.getElementById("amcicondisplay").src = "https://resources.freemi.in/products/resources/images/partnerlogo/mf/"+amcicon;
		$("amcicondisplay").css("background","white");
	}else{
		document.getElementById("amcicondisplay").src = "http://localhost:8080/products/resources/images/partnerlogo/mf/default-amc.png";
		$("amcicondisplay").css("background","white");
	}
	
	var x = document.getElementById("sipOtherDates");
	$("#sipOtherDates").empty();
	/*
	for (i = 0; i < x.length; i++) {
		  x.remove(i);
		}*/
	
	for(i=0;i<dtarray.length ; i++){
//		console.log(dtarray[i]);
		var option = document.createElement("option");
		option.text = dtarray[i];
		option.value = dtarray[i];
		x.add(option);
	}
	x.selectedIndex = 1; 
	
	if (radioValue == 'SIP') {
		$("#sipbox").show();
		
		
		
		$("#minvalreq").text(minsip);
	} else if (radioValue == 'LUMPSUM') {
		$("#sipbox").hide();
		$("#minvalreq").text(minlumpsum);
	} else {
		$("#sipbox").hide();
		$("#minvalreq").text("0");
	}
	$("#minValls").hide();
	$("#schemecode").val(mfCode);
	console.log("Reonv code- "+ reinvCode);
	$("#reinvSchemeCode").val(reinvCode);
	$("#amcCode").val(amcCode);
	return true;
}



//Purchase
function mandateTypeChosen(){
//	console.log("Selected type-" + $("#mandateType :selected").val());
	console.log($("input[name='mandateType']:checked").val());
	var mandateType = $("input[name='mandateType']:checked").val();
	if(mandateType == 'I'){
		$("#xsip").hide();
		$("#isip").show();
	}
	if(mandateType == 'X'){
		$("#isip").hide();
		$("#xsip").show();
	}
//	$("#occupationtypedisplay").text($("#mandateType :selected").text());
}

