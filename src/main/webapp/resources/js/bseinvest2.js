//console.log = function() {}
var minsip=0;
var minlumpsum=0;

var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
	// This function will display the specified tab of the form...
	console.log("showTab currenttab- " + currentTab + " : n: " + n);

	var x = document.getElementsByClassName("tab");
	x[n].style.display = "block";
	//... and fix the Previous/Next buttons:
	if (n == 0) {
		document.getElementById("prevBtn").style.display = "none";
	} else {
		document.getElementById("prevBtn").style.display = "inline";
	}
	if (n == (x.length - 1)) {
		document.getElementById("nextBtn").innerHTML = "Apply <i class='fas fa-angle-double-right'></i></i>";
	} else {
		document.getElementById("nextBtn").innerHTML = "Next";
		$("#nextBtn").prop("disabled", false);
	}
	//... and run a function that will display the correct step indicator:
	fixStepIndicator(n)
}

function nextPrev(n) {
	console.log("nextprev- " + currentTab + " : increment step by n: " + n);
	// This function will figure out which tab to display

	var x = document.getElementsByClassName("tab");
	// Exit the function if any field in the current tab is invalid:
	/*
	if (n == 1 && !validateForm())
		return false;
	 */

	if (n == 1 && currentTab == 0) {
		console.log("1st form")
		if (!validBasicForm())
			return false;
	}

	if (n == 1 && currentTab == 1) {
		console.log("2nd form");

		if (!validBankForm())
			return false;
	}

	if (n == 1 && currentTab == 2) {
		console.log("3rd form validate")
		if (!validFatcaForm())
			return false;
	}
	document.getElementsByClassName("step")[currentTab].className += " finish";
	// Hide the current tab:
	x[currentTab].style.display = "none";
	// Increase or decrease the current tab by 1:
	currentTab = currentTab + n;
	// if you have reached the end of the form...

	/* if(currentTab == 1 && n ==1){
			displayfdpurchasesummary();
		} */

	if (currentTab == (x.length - 1)) {
//		console.log("Show confirm page..." + currentTab);
		populateConfirmPage();
	}

	if (currentTab >= x.length) {
		// ... the form gets submitted:
		currentTab = currentTab - n;
		x[currentTab].style.display = "inline";

		$("#nextBtn").html("<span>Processing <i class=\"fas fa-spinner fa-spin\"></i></span>");
		$("#nextBtn").prop("disabled", true);
		document.getElementById("regForm").submit();
//		showprogress();
		return false;
	}


	// Otherwise, display the correct tab:
	showTab(currentTab);
}

function validateForm() {
	return true;
	console.log("Validate form...")
	var x, y, i, valid = true;
	x = document.getElementsByClassName("tab");
	y = x[currentTab].getElementsByTagName("input");
	// A loop that checks every input field in the current tab:
	for (i = 0; i < y.length; i++) {
		// If a field is empty...
		if (y[i].value == "" && y[i].hasAttribute('required')) {
			// add an "invalid" class to the field:
			y[i].className += " invalid";
			// and set the current valid status to false
			valid = false;
			/* valid = true; */// Test field
		} else {
			if (y[i].classList.contains('invalid')) {
				//console.log("Remove class");
				y[i].classList.remove('invalid');
			}
		}
	}
	// If the valid status is true, mark the step as finished and valid:
	if (valid) {
		$("#tabvalidationerrormsg").text("")
		document.getElementsByClassName("step")[currentTab].className += " finish";
	} else {
		$("#tabvalidationerrormsg").text(
		"Please fill in the mandatory fields.")
	}
	consoloe.log("Return tab validation status- "+ valid)
	return valid; // return the valid status
//	return true;

}

function fixStepIndicator(n) {

	// This function removes the "active" class of all steps...
	var i, x = document.getElementsByClassName("step");
	for (i = 0; i < x.length; i++) {
		x[i].className = x[i].className.replace(" active", "");
	}
	//... and adds the "active" class on the current step:
	x[n].className += " active";

	console.log("showtab cals - fixStepIndicator n- " + n);
}

$(document).ready(function() {
	$( function() {
		$( "#investorDOB" ).datepicker({ format: 'dd/mm/yyyy' });
	} );
	setDefaultvalues();
});

$(document).ready(function() {
	$("#ifsc").blur(function() {
		var regex = new RegExp('^[a-zA-Z]{4}[0][0-9a-zA-Z]{6}$');
//		console.log("IFSC search");
		var ifsc = $("#ifsc").val();

		if (regex.test(ifsc)) {
			$("#ifsc").css('border-bottom','2px solid #43c253');
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
			$("#ifsc").css('border-bottom','2px solid #ff6a6a');
		}

	});
});


//selectfund

$(document).on("click change", "#radioamount", function() {
	var x = $("input[type='radio'][name='options']:checked").val();
//	console.log("Test " + x); 
	$("#amount").val(x);
	$("input[type='radio'][name='options']:checked").css("background","red");
});

function customamount() {
	if ($('input:radio[name="options"]:checked')) {
		console.log("Check active")
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

$(document).ready(function(){
	$("#holdingMode").change(function(){
		viewapplicant2sign();
	});
});


function viewapplicant2sign(){
	var holdingmode = document.forms["regForm"]["holdingMode"].value;
	if( holdingmode == 'AS' || holdingmode == 'JO') {
		$("#applicant2-box").removeClass("d-none");
	$("#secApplicant").removeClass("d-none");
	$("#sign2-box").removeClass("d-none");
	$("#sign2-display").removeClass("d-none");
}else{
	$("#applicant2-box").addClass("d-none");
	$("#secApplicant").addClass("d-none");
	$("#sign2-box").addClass("d-none");
	$("#sign2-display").addClass("d-none");
	
}
}

/*------------------------------------------------------------------------------------------------------------------------*/


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
	console.log("Validate 1st form");
	$("#nextBtn").removeAttr("disabled");
	$("#mandateField").text("");
	var dt= $("#investorDOB").val();
	var panregex1 = /^[A-Z]{3}[P]{1}[A-Z]{1}[0-9]{4}[A-Z]{1}$/;
	var panregex2 = /^([A-Z]{3}[H]{1}[A-Z]{1}[0-9]{4}[A-Z]{1})$/;
	var panregex3 = /^([A-Z]{3}[AT]{1}[A-Z]{1}[0-9]{4}[A-Z]{1})$/;
	var panregex4 = /^([A-Z]{3}[F]{1}[A-Z]{1}[0-9]{4}[A-Z]{1})$/;
	var panregex5 = /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/;

	var pan1 = document.forms["regForm"]["pan1"].value.toUpperCase();
	var holdingmode = document.forms["regForm"]["holdingMode"].value;
	var taxstatus = document.forms["regForm"]["taxStatus"].value;
	var applicant2 = document.forms["regForm"]["applicant2"].value;
	var pan2 = document.forms["regForm"]["pan2"].value.toUpperCase();
	var occupation = document.forms["regForm"]["occupation"].value;

	var splitdt = dt.split("/");
	dob = new Date(splitdt[2]+"-"+splitdt[1]+"-"+splitdt[0]);
	var today = new Date();
	var diff = Math.floor((today - dob)/(1000*60*60*24*365));

	var gender = $("input[name='gender']:checked").val();

	if(pan1==''){
		showerrormsg('mandateField','PAN details are mandatory');
		return false;
	}
	
	if ($("#investorDOB").val() == "" || $("#fname").val() == "" || $("#lname").val() == "" || $("#email").val() == "" ||$("#mobile").val() == "") {
		$("#mandateField").text("Please provide mandatory fields data.");
		return false;
	}

	if(taxstatus == '01' || taxstatus == '02'){
		if(!panregex1.test(pan1)){
			showerrormsg('mandateField','Invalid PAN for individual');
			return false;
		}
	}
	if(taxstatus == '03'){
		if(!panregex2.test(pan1)){
			showerrormsg('mandateField','Invalid PAN for HUF');
			return false;
		}
	}

	if((taxstatus == '01' || taxstatus == '02' || taxstatus == '03') && gender == undefined){
		showerrormsg('mandateField','Please select your gender');
		return false;
	}

	if(dob>today || diff <18 || diff > 65){
		showerrormsg('mandateField','Investor age must be between 18-65 years as per document.');
		return false;
	}
	
	if(occupation == ''){
		showerrormsg('mandateField','Please select your occupation');
		return false;
	}
	
	if(holdingmode == 'AS' || holdingmode == 'JO'){
		if(applicant2 == undefined || applicant2 == ''){
			showerrormsg('mandateField','2nd Applicant Name is required');
			return false;
		}
		if(pan2 == undefined || pan2 == ''){
			showerrormsg('mandateField','2nd applicant PAN is required');
			return false;
		}
		
		if(pan2 !='' && !panregex5.test(pan2)){
			showerrormsg('mandateField','Invalid 2nd applicant PAN format');
			return false;
		}
		
	}
	

	return true;
}

function validBankForm() {
	$("#nextBtn").removeAttr("disabled");
	$("#mandateField").text("");
	if ($("#accountno").val() == "" || $("#ifsc").val() == "") {
		$("#mandateField").text("Please provide mandatory bank details");
		return false;
	}
	var pincoderegex = /^[0-9]{6}$/;
	
	if($("#address1").val()==""){
		$("#mandateField").text("Address 1 is mandatory");
		return false;
	}
	
	if($("#address2").val()==""){
		$("#mandateField").text("Address 2 is mandatory");
		return false;
	}
	
	if($("#addState :selected").val()==""){
		$("#mandateField").text("Select your state");
		return false;
	}
	
	if($("#address_city").val()==""){
		$("#mandateField").text("Select your city");
		return false;
	}
	
	let pincode = $("#pinCode").val();
	
	if($("#pinCode").val()=="" || !pincoderegex.test(pincode) ){
		$("#mandateField").text("Ente valid 6-digit pincode");
		return false;
	}
	
	viewapplicant2sign();

	return true;
}

function validFatcaForm() {
	console.log("Fatca validation called");
	$("#nextBtn").removeAttr("disabled");
	$("#mandateField").text("");

	if ($("#birthplace").val() == "" || $("#occupationType :selected").val() == "" || $("#incomeslab :selected").val() == "" || $("#wealthsource :selected").val() == "" || $("#politicalview :selected").val() == "" ) {
		$("#mandateField").text("Please provide mandatory fields data.");
		return false;
	}
	var canvas = document.getElementById("sig-canvas");
	var dataUrl = canvas.toDataURL();
	
	let signeddata = $("#signature1").val();
	let finaldata;
	
	if(dataUrl=='' ){
		$("#mandateField").text("Applicant 1 signature required");
		return false;
	}
	
	if(dataUrl.length <= 3600 ){
		$("#mandateField").text("New signature no prominent.");
		return false;
	}
	
	if(dataUrl.length >=24000 ){
		$("#mandateField").text("Signature looks too hazzy!");
		return false;
	}
	
	$("#signature1").val(dataUrl);
	var holdingmode = document.forms["regForm"]["holdingMode"].value;
	
	if(holdingmode == 'AS' || holdingmode == 'JO'){
		var canvas2 = document.getElementById("sig-canvas2");
		var dataUrl2 = canvas2.toDataURL();
		console.log("Image 2- "+ dataUrl2.length + " ->" + dataUrl2);
		let signeddata2 = $("#signature1").val();
		let finaldata2;
		
		if(dataUrl == dataUrl2){
			$("#mandateField").text("Applicant 2 same signature not allowed!");
			return false;
		}
		
		if(dataUrl2=='' ){
			$("#mandateField").text("Applicant 2 signature required");
			return false;
		}
		
		if(dataUrl2.length <= 3600 ){
			$("#mandateField").text("New signature no prominent.");
			return false;
		}
		
		if(dataUrl2.length >=24000 ){
			$("#mandateField").text("Signature looks too hazzy!");
			return false;
		}
		
		$("#signature2").val(dataUrl2);
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
	$("#invName").val($("#fname").val().trim() + " "+ $("#lname").val().trim());

	//Personal Details
	$("#nameDisplay").text($("#invName").val());
//	$("#nameDisplay").text($("#fname").val().trim() + " "+ $("#lname").val().trim());

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
	
	if($("#holdingMode :selected").val() == 'SI'){
		$("#secondapplicantName").text("NA");
		$("#secondapplicantPan").text("NA");
		$("#applicant2Val").val("")
		$("#pan2").val("")
	}else{
		$("#secondapplicantName").text($("#applicant2Val").val());
		$("#secondapplicantPan").text($("#pan2").val());
	}
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

	/*$("#paymentModeDisplay").text($("#dividendPayMode :selected").text());*/
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
		$("#uscitizenshipcheckdisplay").text("No");
		$("#uscitizenshipcheckdisplay").css({"color":"white","background":"rgba(26, 115, 53, 0.93)","padding":"2px 10px","border-radius":"2px"});

	}else{
		$("#uscitizenshipcheckdisplay").text("Yes");
		$("#uscitizenshipcheckdisplay").css({"color":"white","background":"#ef2e2eed","padding":"2px 10px","border-radius":"2px"});
	}

	$("#birthplacedisplay").text($("#birthplace").val());
	$("#fathernamedisplay").text($("#fathername").val());
	$("#spousenamedisplay").text($("#spousename").val());
	$("#wealthsourcedisplay").text($("#wealthsource :selected").text());
	$("#incomeslabdisplay").text($("#incomeslab :selected").text());
	$("#occupationtypedisplay").text($("#occupationType :selected").text());
	$("#politicalviewdisplay").text($("#politicalview :selected").text());
//	$("#sign1display").text($("#signature1").val());
	let sigImage = document.getElementById("sign1display");
	
	console.log("Signature- "+ $("#signature1").val())
	sigImage.setAttribute("src", $("#signature1").val());
	
	if($("#holdingMode :selected").val() == 'AS' || $("#holdingMode :selected").val() == 'JO'){
		let sigImage2 = document.getElementById("sign2display");
		sigImage2.setAttribute("src", $("#signature2").val());
		
	}/*else{
		sigImage2.setAttribute("src","");
	}*/

};


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

//	console.log(parseFloat(purchaseamount).toFixed(2) +  " - "+ minimumPurchase);
	if(!isNaN(purchaseamount)){
		console.log("NOT NAN");
//		$("#invalidamnt").text("");
		//$("#nextBtn").removeAttr("disabled");
		if(parseFloat(purchaseamount) < parseFloat(minimumPurchase) ){
			console.log("Amount is less");
			$("#selectmsg").text("Minimum purchase amount criteria not met");
//			$("#nextBtn").attr("disabled", "disabled");
			return false;
		}else{
			console.log("valid");
			$("#selectmsg").text("");
//			$("#nextBtn").removeAttr("disabled");
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

	displayprocessing("#selectfundbtn");
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

$('#selectedFund').on('submit', function() {
	displayprocessing("#orderconfirmbtn");
	return true;
});

function displayprocessing(elementid){
	$(elementid).html("Processing.. <i class='fas fa-spinner fa-spin'>");
	$(elementid).attr("disabled", "disabled");
}

function showerrormsg(field,msg){
	$("#"+field).html("<p class=\"alert alert-danger small p-1 m-0\">" +msg +" </p>");

};
