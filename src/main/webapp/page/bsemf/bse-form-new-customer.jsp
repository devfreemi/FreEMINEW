<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mutual fund investment form for new customer</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description"
	content="Fill up the form to complete your investment if you are an e-KYC verified customer" />
<meta name="robots" content="index,nofollow" />


<link
	href="<c:url value="${contextPath}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextPath}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextPath}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>

</head>

<script type="text/javascript">
	var currentTab = 0; // Current tab is set to be the first tab (0)
	//showTab(currentTab); // Display the current tab

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
		$("#PANDisplay").text($("#pan").val());

		$("#mobileDisplay").text($("#mobile").val());
		$("#emailDisplay").text($("#email").val());
		$("#DOBDisplay").text($("#investorDOB").val());
		$("#incomeslabDisplay").text($("#annualIncome").val());
		$("#occupationDisplay").text($("#occupation").val());
		$("#birthPlaceDisplay").text($("#birthPlace").val());
		$("#genderDisplay").text($("input[name='gender']:checked").val());
		$("#maritalDisplay").text(
				$("input[name='maritalStatus']:checked").val());

		//	$("#").text($("#genderDisplay").val());

		$("#taxStatusDisplay").text($("#taxStatus").val());
		$("#holdingModeDisplay").text("Individual");
		/* $("#taxStatusDisplay").text("taxStatus"); */

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
		$("#paymentModeDisplay").text(
				$("input[name='gridRadios']:checked").val());
		$("#accountTypeDisplay").text($("#accountType").val());
		$("#accNumberDisplay").text($("#accountno").val());

		$("#ifscDisplay").text($("#ifsc").val());
		$("#bankNameDisplay").text($("#bankName").val());
		$("#branchDisplay").text($("#branch").val());
		$("#branchAddressDisplay").text($("#bankAddress").val());
		$("#bankCityDisplay").text($("#bankCity").val());
		$("#branchStateDisplay").text($("#bankState").val());
		//	$("#").text($("#").val());

	}

	$(document)
			.ready(
					function() {
						$("#ifsc")
								.blur(
										function() {
											var regex = new RegExp(
													'^[a-zA-Z]{4}[0][0-9a-zA-Z]{6}$');
											//console.log("IFSC search");
											var ifsc = $("#ifsc").val();

											if (regex.test(ifsc)) {
												$("#ifsc").css('border-left',
														'2px solid #43c253');
												$
														.get(
																"https://ifsc.razorpay.com/"
																		+ ifsc,
																function(data,
																		status) {
																	//console.log(data.BANK);
																	//console.log(data.BRANCH);
																	//console.log(data.ADDRESS);

																	$(
																			"#bankCity")
																			.val(
																					data.CITY);
																	$("#branch")
																			.val(
																					data.BRANCH);
																	$(
																			"#bankAddress")
																			.val(
																					data.ADDRESS);
																	$(
																			"#bankName")
																			.val(
																					data.BANK);
																	$(
																			"#bankState")
																			.val(
																					data.STATE);

																	$(
																			"#invalidifsc")
																			.text(
																					"");
																})
														.fail(
																function(data,
																		status) {
																	//console.log("Error retrieving data");
																	//console.log(data);
																	//console.log(status);
																	$(
																			"#invalidifsc")
																			.text(
																					"Invalid IFSC code");
																	$(
																			"#bankCity")
																			.val(
																					"");
																	$("#branch")
																			.val(
																					"");
																	$(
																			"#bankAddress")
																			.val(
																					"");
																	$(
																			"#bankName")
																			.val(
																					"");
																	$(
																			"#bankState")
																			.val(
																					"");

																});
											} else {
												$("#ifsc").css('border-left',
														'2px solid #ff6a6a');
											}

										});
					});
</script>

<body onload="nextPrev(0);">

	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container-fluid">

		<nav aria-label="breadcrumb">
			<ol class="breadcrumb" style="margin-left: 0px;">

				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/"><i
						class="fas fa-home"></i></a></li>
				<li class="breadcrumb-item active" aria-current="page">
					Register for Mutual Funds</li>
			</ol>
		</nav>
		<!-- <h1>Register:</h1> -->
		<div class="row" style=" margin: auto;">

			<div class="col-md-7 col-lg-7 formstyle">

				<jsp:include page="bseform.jsp"></jsp:include>
			</div>

			<div class="col-md-5 col-lg-5">
				<div class="notice-mf">
					<h5 style="color: #de1313;">Important Notice</h5>

					<p>
						Please note that given the recent Supreme Court judgement
						regarding Adhaar, the eKYC throgh website stands withdrawn from
						Oct 19 2018.<br />
						<br />As an effect, the following will not be available to our
						investors and distributors:
					</p>
					<ul>
						<li>eKYC process</li>
						<li>OTP and Biometric eKYC support</li>
					</ul>
					<p>We are currently registering all customers via physical
						verifications. A KYC-compliant need to submit their details,
						download the form, sign and upload it for system based
						verification.</p>
					<p>For non-kyc customers, after the signed form is uploaded,
						our customer representative will help complete the verification by
						offiline process.</p>
					<p>
						<br />For instant assistance, please call our office number.
					</p>
				</div>

			</div>
		</div>
	</div>

	<jsp:include page="../include/footer.jsp"></jsp:include>

</body>



</html>