<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>Mutual fund investment form for new customer</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Fill up the form to complete your investment if you are an e-KYC verified customer" />
<meta name="robots" content="index,nofollow" />

<!-- <link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet"> -->
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>"
	type="text/css" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextcdn}/resources/css/investmentform.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="include/bootstrap.jsp"></jsp:include>

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

<style>
h5 {
	margin-bottom: 0px;
}
</style>

<body class="back_set" onload="nextPrev(0);">

	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">

		<!-- <h1>Register:</h1> -->
		<div class="row">

			<div class="col-md-10 col-lg-10 formstyle">

				<form:form id="regForm"
					action="${pageContext.request.contextPath}/registry-mutual-funds/mfInvestment.do"
					method="POST" commandName="mfInvestForm">

					<!-- Circles which indicates the steps of the form: -->
					<div style="text-align: center; margin-top: 40px;">
						<span class="step"></span> <span class="step"></span> <span
							class="step"></span>
						<!-- <span class="step"></span> -->
					</div>

					<!-- One "tab" for each step in the form: -->
					<div class="tab">
						<div class="animated fadeIn">

							<c:if test="${error != null }">
								<div>
									<span style="color: red; font-size: 11px;">${error }</span>
								</div>
							</c:if>
							<div>
								<span id="mandateField" style="color: red;"></span>
							</div>

							<div>
								<span>${mfInvestForm.investmentType } </span>
							</div>

							<div class="sectionheader">
								<h5>Unit Holder Information</h5>
							</div>

							<div style="text-align: center; margin-bottom: 15px;">
								<c:if test="${panStatus.isKYCVerified == 'Y'}">
									<span
										style="color: white; background-color: #47997d; padding: 2px 15px; border-radius: 5px; font-style: oblique;">KYC
										Status- OK </span>
								</c:if>
								<c:if test="${panStatus.isKYCVerified == 'N'}">
									<span
										style="color: white; background-color: #47997d; padding: 2px 15px; border-radius: 5px; font-style: oblique;">KYC
										verified- Complete the process </span>
								</c:if>

							</div>

							<div class="form-row">
								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon3">Mode
												of holding</span>
										</div>
										<form:select class="custom-select" id="holdingMode"
											path="holdingMode">
											<form:option value="Single" selected="true">Single</form:option>
										</form:select>
									</div>
								</div>
								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon3">PAN</span>
										</div>
										<form:input type="text" class="form-control" id="pan"
											style="text-transform: uppercase;"
											aria-describedby="basic-addon3" path="PAN" readonly="true" />
									</div>

								</div>
							</div>

							<div class="form-row">
								<div class="form-group col-md-4">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon3">Tax
												Status</span>
										</div>
										<form:select class="custom-select" id="taxStatus"
											path="taxStatus">
											<form:options items="${taxStatus}" />
										</form:select>
									</div>
								</div>

								<div class="form-group col-md-8 fatcaCheck"
									style="border: 1px solid #a5a29e; border-radius: 4px;">
									<div>
										<span style="color: #ad5da8; text-decoration: underline;"><strong>FATCA
												Information - For Individuals</strong></span>
									</div>

									<div class="row" style="text-align: justify;">
										<div class="col-md-9">
											<span
												style="font-size: 12px; color: #575353; font-style: italic;">
												Do you have any non-Indian Country [ies] of Birth /
												Citizenship / Nationality and Tax Residency - Yes / No. If
												Yes, you must specify all non-Indian countries of birth,
												citizenship, nationality and/or tax residency
												below[mandatory] * </span>

										</div>
										<div class="col-md-3" style="margin: -1px; margin-right: 1px;">
											<div class="form-check form-check-inline">
												<label class="form-check-label"> <form:radiobutton
														class="form-check-input" path="fatcaStatus"
														id="fatcaStatus" value="Y" disabled="true" /> <span
													style="">Yes</span>
												</label>
											</div>
											<div class="form-check form-check-inline">
												<label class="form-check-label"> <form:radiobutton
														class="form-check-input" path="fatcaStatus"
														id="fatcaStatus" value="N" /><span style="color: #27941b">No</span>
												</label>
											</div>

										</div>

									</div>
									<%-- <div class="form-group">
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <form:radiobutton
													class="form-check-input" path="fatcaStatus"
													id="fatcaStatus" value="Y" /> Yes
											</label>
										</div>
										<div class="form-check form-check-inline">
											<label class="form-check-label"> <form:radiobutton
													class="form-check-input" path="fatcaStatus"
													id="fatcaStatus" value="N" /> No
											</label>
										</div>
									</div> --%>



								</div>

							</div>


							<div>
								<div class="sectionheader">
									<h5>
										<i class="fas fa-user"></i> Personal Details
									</h5>
								</div>

								<div class="form-row">
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Name</span>
											</div>
											<form:input type="text" class="form-control" id="invName"
												path="invName" aria-describedby="basic-addon3" />
										</div>
									</div>
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Date
													of Birth</span>
											</div>
											<form:input type="date" path="invDOB" class="form-control"
												id="investorDOB" />
										</div>
									</div>

								</div>

								<div class="form-row">
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Email
													ID</span>
											</div>
											<form:input type="text" class="form-control" id="email"
												path="email" aria-describedby="basic-addon3" />
										</div>
									</div>
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Mobile
													no.</span>
											</div>
											<form:input type="text" class="form-control" id="mobile"
												path="mobile" aria-describedby="basic-addon3" />
										</div>
									</div>

								</div>

								<div class="form-row">
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Gross
													Annual Income</span>
											</div>
											<form:select class="custom-select" id="annualIncome"
												path="annualIncome">
												<form:options items="${annualIncome}" />
											</form:select>
										</div>
									</div>
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Occupation</span>
											</div>
											<form:select class="custom-select" id="occupation"
												path="occupation">
												<form:options items="${occupation}" />
											</form:select>
										</div>
									</div>

								</div>

								<div class="form-row">
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Place
													of Birth</span>
											</div>
											<form:input type="text" class="form-control" id="birthPlace"
												path="placeOfBirth" aria-describedby="basic-addon3" />
										</div>
									</div>


									<div class="form-group col-md-6">

										<div class="row" style="text-align: center;">
											<div class="col-6">
												<div class="form-check form-check-inline">
													<label class="form-check-label"> <form:radiobutton
															class="form-check-input" path="gender" id="gender"
															value="M" /> Male
													</label>
												</div>
												<div class="form-check form-check-inline">
													<label class="form-check-label"> <form:radiobutton
															class="form-check-input" path="gender" id="gender"
															value="F" /> Female
													</label>
												</div>

											</div>
											<div class="col-6"
												style="margin-left: -1px; padding-left: 1px;">
												<div class="form-check form-check-inline">
													<label class="form-check-label"> <form:radiobutton
															class="form-check-input" path="maritalStatus"
															id="marital" value="M" /> Married
													</label>
												</div>
												<div class="form-check form-check-inline">
													<label class="form-check-label"> <form:radiobutton
															class="form-check-input" path="maritalStatus"
															id="marital" value="S" /> Single
													</label>
												</div>

											</div>
										</div>


									</div>

								</div>


							</div>

							<div>

								<div class="sectionheader">
									<h5>
										<i class="fas fa-users"></i> Nominee Details
									</h5>
								</div>
								<div class="form-row">
									<div class="form-group col-md-6">

										<%-- <label> <form:checkbox type="checkbox" path="nominee.isNominate"
												id="nominate" value="Y" checked="checked" /> <span
												class="checkmark"></span> <span
												style="font-size: 12px; color: #21a35e;"> I wish to nominate
											</span>
											</label> --%>
										<div>
											<span
												style="color: green; position: absolute; margin-left: 20px;">I
												wish to nominate</span>
											<div style="width: 10px; padding-top: 7px;">
												<form:radiobutton path="nominee.isNominate" value="Y"
													id="isNominate" onclick="setDefaultvalues();" />
											</div>
										</div>
										<div>
											<span
												style="color: red; position: absolute; margin-left: 20px;">I
												do not wish to nominate</span>
											<div style="width: 10px; padding-top: 7px;">
												<form:radiobutton path="nominee.isNominate" value="N"
													id="isNominate" onclick="setDefaultvalues();" />
											</div>
										</div>

									</div>
								</div>

								<div class="showNomineeForm">
									<div class="form-row">
										<div class="form-group col-md-6">
											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Name</span>
												</div>
												<form:input type="text" class="form-control"
													id="nomineeName" path="nominee.nomineeName"
													aria-describedby="basic-addon3" />
											</div>
										</div>
										<div class="form-group col-md-6">

											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Birth
														date</span>
												</div>
												<form:input type="date" path="nominee.nomineeDOB"
													class="form-control" id="nomineeDOB" />
											</div>
											<span style="color: navy; font-size: 11px;">Age 18 -
												65 yrs.</span>
										</div>

									</div>
									<div class="form-row">
										<div class="form-group col-md-6">
											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Address
													</span>
												</div>
												<form:input type="text" class="form-control"
													id="nomineeAddress1" path="nominee.nomineeAddress1"
													aria-describedby="basic-addon3" />
											</div>
										</div>
										<div class="form-group col-md-6">
											<%-- <div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Address
														2</span>
												</div>
												<form:input type="text" class="form-control"
													id="nomineeAddress2" path="nominee.nomineeAddress2"
													aria-describedby="basic-addon3" />
											</div> --%>

											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Relation</span>
												</div>
												<form:select class="custom-select" id="relation"
													path="nominee.nomineeRelation">
													<option value="" selected="selected">Choose
														relation</option>
													<option value="SON">Son</option>
													<option value="DAUGHTER">Daughter</option>
													<option value="Spouse">Spouse</option>
													<option value="Parents">Parents</option>
												</form:select>
											</div>

										</div>

									</div>

									<%-- <div class="form-row">
										<div class="form-group col-md-6">
											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">City</span>
												</div>
												<form:input type="text" class="form-control"
													id="nomineeCity" path="nominee.nomineeCity"
													aria-describedby="basic-addon3" />
											</div>
										</div>
										<div class="form-group col-md-6">
											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">State</span>
												</div>
												<form:select class="custom-select" id="nomineeState"
													path="nominee.nomineeState">
													<form:option value="" selected="true">Select State</form:option>
													<form:options items="${stateList}" />
												</form:select>
											</div>
										</div>

									</div> --%>


									<%-- <div class="form-row">
										<div class="form-group col-md-6">
											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Percentage</span>
												</div>
												<form:input type="text" class="form-control"
													id="nomineePercent" path="nominee.nomineePercentage"
													aria-describedby="basic-addon3" />
											</div>
										</div>
										<div class="form-group col-md-6">
											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Relation</span>
												</div>
												<form:select class="custom-select" id="relation"
													path="nominee.nomineeRelation">
													<option value="" selected="selected">Choose
														relation</option>
													<option value="SON">Son</option>
													<option value="DAUGHTER">Daughter</option>
													<option value="WIFE">Wife</option>
												</form:select>
											</div>
										</div>

									</div> --%>


									<div class="form-row">
										<div class="form-group col-md-6">
											<%-- <div class="form-check">
												<form:checkbox class="form-check-input"
													path="nominee.isNomineeMinor" value="Y" id="gridCheck2" />
												<label class="form-check-label" for="gridCheck2">
													Nominee is a minor </label>
											</div> --%>

											<div>
												<span
													style="color: black; position: absolute; margin-left: 20px;">Not
													minor</span>
												<div style="width: 10px; padding-top: 7px;">
													<form:radiobutton path="nominee.isNomineeMinor" value="N"
														id="isMinor" />
												</div>
											</div>
											<div>
												<span
													style="color: black; position: absolute; margin-left: 20px;">Minor
												</span>
												<div style="width: 10px; padding-top: 7px;">
													<form:radiobutton path="nominee.isNomineeMinor" value="Y"
														id="isMinor" />
												</div>
											</div>

										</div>
										<div class="form-group col-md-6">
											<div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Guardian
														Name (If minor) </span>
												</div>
												<form:input type="text" class="form-control" id="guardian"
													path="nominee.nomineeGuardian"
													aria-describedby="basic-addon3" />
											</div>
										</div>
									</div>

								</div>

								<div class="sectionheader">
									<h5>
										<i class="fas fa-clipboard-check"></i> Declarations
									</h5>
								</div>

								<div class="form-row" style="margin-bottom: 5px;">
									<div class="form-group col-md-1 col-1"
										style="padding-top: 7px;">
										<form:checkbox path="ubo" value="Y" />
									</div>
									<div class="form-group col-md-11 col-11 agree_Check"
										style="height: 75px;">
										<span style="font-size: 12px; color: #575353;"> I have
											read the <a
											href="https://mf.adityabirlacapital.com/Pages/Individual/Forms-Downloads/Forms.aspx"
											target="_blank">Key Information Memorandum, Scheme
												Information Document / Statement of Additional Information </a>
											and <a
											href="https://mf.adityabirlacapital.com/_layouts/ABFSG/MF/downloads/Disclaimer.pdf"
											target="_blank">Disclaimer</a> for the scheme in which I am
											investing. <br> In line with regulation under Prevention
											of Money Laundering Act, 2002 (PMLA), I/We hereby confirm
											that the funds for this purchase are being debited from my
											bank account.

										</span>
									</div>

								</div>



								<div class="form-row">
									<div class="form-group col-md-1 col-1"
										style="padding-top: 7px;">
										<form:checkbox path="declaration" value="Y" />
									</div>
									<div class="form-group col-md-11 col-11 agree_Check">
										<span> I/We acknowledge and confirm that the
											information provided above is/are true and correct to the
											best of my/our knowledge and belief. In case any of the above
											specified information is found to be false or untrue or
											misleading or misrepresenting, I/We am/are aware that I/We
											may liable for it. I/We hereby authorize you to disclose,
											share, remit in any form, mode or manner, all / any of the
											information provided by me/ us, including all changes,
											updates to such information as and when provided by me/ us to
											Mutual Fund, its Sponsor, Asset Management Company, trustees,
											their employees / RTAs ('the Authorized Parties') or any
											Indian or foreign governmental or statutory or judicial
											authorities / agencies including but not limited to the
											Financial Intelligence Unit-India (FIU-IND), the tax /
											revenue authorities in India or outside India wherever it is
											legally required and other investigation agencies without any
											obligation of advising me/us of the same. Further, I/We,
											authorize to share the given information to other SEBI
											Registered Intermediaries to facilitate single submission /
											updation &amp; for other relevant purposes. I/We also
											undertake to keep you informed in writing about any changes /
											modification to the above information in future and also
											undertake to provide any other additional information as may
											be required at your end </span>
									</div>

								</div>

							</div>

						</div>
					</div>


					<!-- --------------------------------- BAnk Details ------------------------------------------  -->
					<div class="tab">
						<div class="animated fadeIn">
							<div class="sectionheader">
								<h5>
									<i class="fas fa-university"></i> Bank Account Details
								</h5>
							</div>
							<fieldset class="form-group">
								<div class="row">
									<!-- <legend class="col-form-label label_design col-md-6 pt-0">Mode of Payment</legend> -->
									<label for="accountnum"
										class="col-md-6 col-form-label label_design">Mode of
										Payment</label>
									<div class="col-md-6">
										<div class="form-check">
											<input class="form-check-input" type="radio"
												name="gridRadios" id="gridRadios1"
												value="Register As Biller" checked> <label
												class="form-check-label" for="gridRadios1"> Register
												As Biller </label>
											<div style="font-size: 11px; color: #f72e53;">SIP will
												begin after 15 days</div>
										</div>
										<!-- <div class="form-check">
											<input class="form-check-input" type="radio"
												name="gridRadios" id="gridRadios2" value="Internet Banking">
											<label class="form-check-label" for="gridRadios2">
												Internet Banking </label>
										</div>
										<div class="form-check disabled">
											<input class="form-check-input" type="radio"
												name="gridRadios" id="gridRadios3" value="UPI" disabled>
											<label class="form-check-label" for="gridRadios3">
												UPI </label>
										</div> -->
									</div>
								</div>
							</fieldset>

							<div class="form-row">
								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">Account no.</span>
										</div>
										<form:input type="text" class="form-control" id="accountno"
											path="bankDetails.accountNumber"
											aria-describedby="basic-addon3" />
									</div>
									<span style="font-size: 10px;"><sup>*</sup>Account must
										belong to investor.</span>
								</div>

								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon3">Account
												Type</span>
										</div>
										<form:select class="custom-select" id="accountType"
											path="bankDetails.accountType">
											<form:option value="" selected="true">Select Account Type</form:option>
											<form:options items="${accountTypes}" />
										</form:select>
									</div>
								</div>



							</div>

							<div class="form-row">
								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">IFSC Code</span>
										</div>
										<form:input type="text" class="form-control" id="ifsc"
											path="bankDetails.ifscCode" aria-describedby="basic-addon3"
											maxlength="11" />
									</div>
									<span id="invalidifsc" style="color: red; font-size: 11px;"></span>
								</div>
								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon3">Bank
												Name</span>
										</div>
										<form:select class="custom-select" id="bankName"
											path="bankDetails.bankName">
											<form:option value="" selected="true">Select your bank</form:option>
											<%-- <form:option value="ICICI">ICICI Bank</form:option> --%>
											<form:options items="${banNames}" />

										</form:select>
									</div>


								</div>
							</div>

							<div class="form-row">
								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon3">Branch</span>
										</div>
										<form:input type="text" class="form-control" id="branch"
											path="bankDetails.bankBranch" aria-describedby="basic-addon3"
											readonly="true" />
									</div>
								</div>
								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon3">Address</span>
										</div>
										<form:input type="text" class="form-control" id="bankAddress"
											path="bankDetails.bankAddress"
											aria-describedby="basic-addon3" readonly="true" />
									</div>
								</div>
							</div>

							<div class="form-row">
								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon3">City</span>
										</div>
										<form:input type="text" class="form-control" id="bankCity"
											path="bankDetails.bankCity" aria-describedby="basic-addon3"
											readonly="true" />
									</div>
								</div>

								<div class="form-group col-md-6">
									<div class="input-group mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">State</span>
										</div>
										<form:input type="text" class="form-control" id="bankState"
											path="bankDetails.branchState"
											aria-describedby="basic-addon3" readonly="true" />
									</div>
								</div>
							</div>

							<div style="border-top: 1px solid black; margin-bottom: 20px;">
								<span></span>
							</div>

							<c:if test="${mfInvestForm.investmentType != 'TARGET_PLAN' }">
								<div class="form-row">
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Investment
													Frequency </span>
											</div>
											<form:select class="custom-select" id="investFrequency"
												path="mfInvestDates.invFrequency">
												<form:option value="Monthly" selected="true">Monthly</form:option>
												<%-- <form:options items="${invFrequency}" /> --%>

											</form:select>
										</div>
									</div>

									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">Invest
													date </span>
											</div>
											<form:select class="custom-select" id="monthlyInvDate"
												path="mfInvestDates.monthlyInvestTriggerDate">
												<form:options items="${monthDates}" />

											</form:select>
										</div>
									</div>
								</div>

								<div class="form-row">
									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">From
													Period </span>
											</div>
											<form:select class="custom-select" id="investStartMonth"
												path="mfInvestDates.sipPeriodFromMonth">
												<form:options items="${calendarMonths}" />

											</form:select>
											<form:select class="custom-select" id="investStartYear"
												path="mfInvestDates.sipPeriodFromYear">
												<form:option value="2018" selected="true">2018</form:option>
												<form:option value="2019">2019</form:option>
											</form:select>
										</div>
									</div>

									<div class="form-group col-md-6">
										<div class="input-group mb-1">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon3">To
													period </span>
											</div>
											<form:select class="custom-select" id="investEndMonth"
												path="mfInvestDates.sipPeriodToMonth">
												<form:options items="${calendarMonths}" />

											</form:select>
											<form:select class="custom-select" id="investEndYear"
												path="mfInvestDates.sipPeriodToYear">
												<form:options items="${investYears}" />

											</form:select>
										</div>
									</div>

								</div>

							</c:if>

						</div>
					</div>

					<!-- ------------ SUMARY TAB ---------------------------------------------------------------------------------------------  -->
					<div class="tab">
						<div class="animated fadeIn">
							<div class="sectionheader">
								<h5>
									<i class="fas fa-user"></i> Investor Details
								</h5>
							</div>
							<!-- ---------------------------------------------------------------------------------------------------------  -->
							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Investor
										name</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="nameDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">PAN
										No</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
										id="PANDisplay" style="text-transform: uppercase;"></span></label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Date
										of birth (DOB)</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="DOBDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Gender</label>
									<label class="col-6 col-md-6 col-form-label label_design1"><span
										id="genderDisplay" style="text-transform: uppercase;"></span></label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Tax
										Status</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="taxStatusDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Mode
										of holding</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="holdingModeDisplay"></span></label>
								</div>
							</div>


							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Email
										ID</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
										id="emailDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Mobile
										No.</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
										id="mobileDisplay"></span></label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Income
										slab </label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="incomeslabDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Occupation
									</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
										id="occupationDisplay"></span></label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Place
										of Birth </label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="birthPlaceDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Marital
										status </label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="maritalDisplay"></span></label>
								</div>

							</div>

							<!-- ---------------------------------------------------------------------------------------------------------  -->
							<div class="sectionheader">
								<h5>
									<i class="fas fa-users"></i> Nominee Details
								</h5>
							</div>

							<div id="isNomineeSelected">
								<div class="row gap_custom">
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Nominee
										</label> <label class="col-6 col-md-6 col-form-label label_design1">
											<span id="nomineeSelected"></span>
										</label>
									</div>
								</div>
							</div>

							<div class="NomineeDetailsDisplay">

								<div class="row gap_custom">
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Name
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeNameDisplay"></span></label>
									</div>
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Date
											of Birth </label> <label
											class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeDOBDisplay"></span></label>
									</div>
								</div>


								<div class="row gap_custom">
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Address
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeaddress1Display"></span></label>
									</div>
									<!-- <div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Address
											2 </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeaddress2Display"></span></label>
									</div> -->

									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Relation
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeRelationDisplay"></span></label>
									</div>
								</div>

								<!-- <div class="row gap_custom">
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">City
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeCityDisplay"></span></label>
									</div>
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">State
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeStateDisplay"></span></label>
									</div>
								</div> -->

								<!-- <div class="row gap_custom">
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Relation
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeRelationDisplay"></span></label>
									</div>
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Pecentage
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineePercentageDisplay"></span></label>
									</div>
								</div> -->



							</div>

							<!-- End of nominee selecttion -->

							<!-- ---------------------------------------------------------------------------------------------------------  -->
							<div class="sectionheader">
								<h5>
									<i class="fas fa-chart-line"></i> Investment Details
								</h5>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Mutual
										Fund Name </label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="fundNameDisplay">${mfInvestForm.selectedFund.fundName }</span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Scheme
										Type </label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="schemeTypeDisplay">${mfInvestForm.selectedFund.schemeOption }</span></label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Scheme
										Name </label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="schemeNameDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Amount
									</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
										id="investAmountDisplay">${mfInvestForm.selectedFund.monthlySavings }</span></label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Invest
										Frequency </label> <label
										class="col-6 col-md-6 col-form-label label_design1"> <c:if
											test="S{mfInvestForm.mfInvestDates.invFrequency == '12'}">
											<span id="investmentFrequency">Monthly</span>
										</c:if> <c:if
											test="S{mfInvestForm.mfInvestDates.invFrequency == '13'}">
											<span id="investmentFrequency">Lumpsum</span>
										</c:if>
									</label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Investment
										date </label> <label
										class="col-6 col-md-6 col-form-label label_design1"> <c:if
											test="${mfInvestForm.investmentType != 'TARGET_PLAN' }">
											<span id="monthlyInvestDate"></span>
										</c:if> <c:if test="${mfInvestForm.investmentType == 'TARGET_PLAN' }">
											<span id="monthlyInvestDate"></span>
										</c:if>
									</label>
								</div>
							</div>

							<c:if test="${mfInvestForm.investmentType != 'TARGET_PLAN' }">
								<div class="row gap_custom">
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Start
											from </label> <label
											class="col-6 col-md-6 col-form-label label_design1"><span
											id="startFrom"></span></label>
									</div>
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Till
											date </label> <label
											class="col-6 col-md-6 col-form-label label_design1"><span
											id="investTillDate"></span></label>
									</div>
								</div>
							</c:if>
							
							
							
							<!-- ---------------------------------------------------------------------------------------------------------  -->
							<div class="sectionheader">
								<h5>
									<i class="fas fa-university"></i> Bank Details
								</h5>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Account
										Holder</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="accHolderDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Mode
										of payment</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="paymentModeDisplay"></span> </label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Account
										Type</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="accountTypeDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Account
										No.</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
										id="accNumberDisplay"></span></label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Bank
										Name </label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="bankNameDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Branch</label>
									<label class="col-6 col-md-6 col-form-label label_design1"><span
										id="branchDisplay"></span></label>
								</div>
							</div>

							<div class="row gap_custom">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">Branch
										Address</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="branchAddressDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">IFSC
										Code</label> <label
										class="col-6 col-md-6 col-form-label label_design1"><span
										id="ifscDisplay"></span></label>
								</div>
							</div>

							<div class="form-group row">
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">City
									</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
										id="bankCityDisplay"></span></label>
								</div>
								<div class="col-md-6">
									<label class="col-5 col-md-5 col-form-label label_design">State</label>
									<label class="col-6 col-md-6 col-form-label label_design1"><span
										id="branchStateDisplay"></span></label>
								</div>
							</div>


						</div>

					</div>

					<!-- <div class="tab">
					<div>
					<span style="color: black;">Please wait while details are processed...</span>
					</div>
					</div> -->

					<!-- End of investor details tab  -->



					<div style="overflow: auto;">
						<div id="display_progress" class="progress_tag"
							style="display: none;">
							<h5>Please wait while we process your request...</h5>
							<div style="text-align: center;">
								<div class="lds-ellipsis">
									<div></div>
									<div></div>
									<div></div>
									<div></div>
								</div>
							</div>
							<h6>Do not Refresh the page</h6>
						</div>

						<div style="float: right;">
							<button type="button" class="btn btn-secondary btn-sm"
								id="prevBtn" onclick="nextPrev(-1)">Previous</button>
							<button type="button" class="btn btn-primary btn-sm" id="nextBtn"
								onclick="nextPrev(1)">Next</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

</body>



</html>