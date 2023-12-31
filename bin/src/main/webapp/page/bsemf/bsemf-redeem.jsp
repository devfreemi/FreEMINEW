<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FreEMI MF</title>
<meta name="robots" content="index,nofollow" />
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/multistep.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container">
		<div class="row" style="margin-bottom: 3rem;">
			<div class="col-md-7 col-lg-7">
				<h2 class="redeem-h2">Redemption Request</h2>
				<section class="redeem-form-section">
					<div style="background: #e4e3e1;">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-6">
										<label>Folio No:</label>
									</div>
									<label class="col-6" style="font-weight: 600;">${mfRedeemForm.portfolio}</label>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-6">
										<label>Account Holder</label>
									</div>
									<label class="col-6" style="font-weight: 600;">${mfRedeemForm.unitHolderName}</label>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-6">
										<label>Date</label>
									</div>
									<label class="col-6" style="font-weight: 600;"><%=(new SimpleDateFormat("dd-MMM-yyyy")).format(new Date())%></label>
								</div>
							</div>
						</div>
					</div>

					<c:choose>
						<c:when test="${FUNDAVAILABLE == 'Y' }">
							<form:form class="form cf" id="regForm"
								action="${pageContext.request.contextPath}/mutual-funds/mfInvestRedeem.do"
								method="POST" commandName="mfRedeemForm">

								<div class="tab">
									<h4 class="text-md-center"
										style="color: #f16927; font-weight: 500; border-bottom: 1px solid;">1.
										Scheme Details</h4>
									<div class="row">
										<div class="col-md-12 col-lg-12" style="text-align: left;">
											<div>
												<span id="mandateField" style="color: red; font-size: 12px;"></span>
											</div>
											<c:if test="${error!= null }">
												<span
													style="color: red; font-size: 12px; margin-bottom: 15px;">${error }</span>
											</c:if>

											<div class="form-group row">
												<label for="folio"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Folio
													No:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<form:input readonly="true" path="portfolio" id="folio"
														class="form-control form-control-sm form-control-plaintext" />
												</div>
											</div>

											<div class="form-group row">
												<label for="fundname"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Scheme
													Name:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<form:input readonly="true" path="fundName" id="fundname"
														class="form-control form-control-sm form-control-plaintext" />
												</div>
											</div>

											<div class="form-group row">
												<label for="fundname"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">BSE
													Scheme Code:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<form:input readonly="true" path="schemeCode"
														id="bseschemeCode"
														class="form-control form-control-sm form-control-plaintext" />
												</div>
											</div>

											<div class="form-group row">
												<label for="availableFund"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Invested
													Amount:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<%-- <form:input path="totalValue" id="availableFund"
														readonly="true"
														class="form-control form-control-sm form-control-plaintext" /> --%>

													<form:hidden path="totalValue" id="availableFund" />
													<%-- <form:input type="number" path="marketValue" id="marketVal"  readonly="true" class="form-control form-control-sm form-control-plaintext" /> --%>
													<label
														class="form-control form-control-sm form-control-plaintext"><fmt:formatNumber
															type="number" pattern="##,###.00" minFractionDigits="1"
															minIntegerDigits="1" maxFractionDigits="2"
															groupingUsed="true" value="${mfRedeemForm.totalValue }" /></label>
												</div>
											</div>

											<div class="form-group row">
												<label for="availableFund"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Current
													value:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<form:hidden path="marketValue" id="marketVal" />
													<%-- <form:input type="number" path="marketValue" id="marketVal"  readonly="true" class="form-control form-control-sm form-control-plaintext" /> --%>
													<label
														class="form-control form-control-sm form-control-plaintext"><fmt:formatNumber
															type="number" pattern="##,###.00" minFractionDigits="1"
															minIntegerDigits="1" maxFractionDigits="2"
															groupingUsed="true" value="${mfRedeemForm.marketValue }" /></label>
												</div>
											</div>

											<div class="form-group row mb-1">
												<label for="redeemAllCheckBox"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Redeem
													All ?</label>
												<div class="col-6 col-md-8 col-lg-8">
													<div class="custom-control custom-checkbox">
														<form:checkbox path="redeemAll"
															class="custom-control-input" id="redeemAllCheckBox" />
														<label class="custom-control-label"
															for="redeemAllCheckBox">Yes</label>
													</div>
												</div>
											</div>

											<div class="form-group row" id="redeemamntbox">
												<label for="redeemamount"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Redeem
													Amount:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<form:input path="redeemAmounts" id="redeemamount" maxlength="10"
														class="form-control form-control-sm" />
													<span id="invalidamnt" style="font-size: 11px; color: red;"></span>
												</div>
											</div>

											


										</div>

									</div>
								</div>
								<div class="tab">
									<div class="row">
										<div class="col-md-12 col-lg-12">
											<h4 class="text-md-center"
												style="color: #f16927; font-weight: 500; border-bottom: 1px solid;">2.
												Confirm Details</h4>
											<div class="form-group row mb-1">
												<label for="folioconf"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Folio
													No:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<label class="confirm-label" id="folioconf">${mfRedeemForm.portfolio}</label>
												</div>
											</div>

											<div class="form-group row mb-1">
												<label for="fundnameconf"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Scheme
													Name:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<label class="confirm-label" id="fundnameconf">${mfRedeemForm.fundName}</label>
												</div>
											</div>

											<div class="form-group row mb-1">
												<label for="investtypeconf"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Investment
													Type:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<label class="confirm-label" id="investtypeconf">${mfRedeemForm.investType}</label>
												</div>
											</div>


											<div class="form-group row mb-1">
												<label for="availablefundconf"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Available
													Amount:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<label class="confirm-label" id="availablefundconf">${mfRedeemForm.totalValue}</label>
												</div>
											</div>



											<div class="form-group row mb-1">
												<label for="redeemamountconf"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Redeem
													Amount:</label>
												<div class="col-6 col-md-8 col-lg-8">
													<label class="confirm-label" id="redeemamountconf"></label>
												</div>
											</div>
											<hr>
											<div class="form-group row mb-1">
												<!-- <label for="agreepolicyconf"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm"></label> -->
												<div class="col-md-12 col-lg-12">

													<div class="custom-control custom-checkbox">
														<span data-toggle="modal" data-target="#mfdisclaimer"
															style="text-decoration: underline; cursor: pointer; color: blue; font-size: 11px;">Disclaimer</span>
														<form:checkbox path="agreePolicy"
															class="custom-control-input" id="agreepolicyconf" />
														<label class="custom-control-label" for="agreepolicyconf">
															<span style="font-size: 11px; text-align: justify;">
																1. Performance history may or may not be in sync with
																the future performance and should not be considered as a
																base for investments. <br> 2. The size of the
																Assets Under Management (AuM) are based on the last
																published Monthly AUM by the corresponding fund house. <br>
																3. If the investors are confused about whether the
																product is suitable for them or not, then he should
																consult their financial advisers for a better guidance.
																<br>

														</span>
														</label>
													</div>

												</div>
											</div>
											<div>
												<form:hidden path="redeemTransId" />
												<%-- <form:hidden path="schemeCode" /> --%>
											</div>

										</div>
									</div>
								</div>

								<jsp:include page="transaction-in-progress-icon.jsp"></jsp:include>


								<div style="overflow: auto;">
									<div style="float: right;">
										<form:button type="button" class="btn btn-sm btn-info"
											id="prevBtn" onclick="nextPrev(-1)">
											<i class="fas fa-arrow-left"></i> Previous</form:button>
										<form:button type="button" class="btn btn-sm btn-success"
											id="nextBtn" onclick="nextPrev(1)">Next <i
												class="fas fa-angle-right"></i>
										</form:button>
									</div>
								</div>

								<!-- Circles which indicates the steps of the form: -->
								<div style="text-align: center; margin-top: 40px;">
									<span class="step"></span> <span class="step"></span>
								</div>


							</form:form>
						</c:when>
						<c:when test="${FUNDAVAILABLE == 'N' }">
							<c:if test="${error!= null }">
								<span style="color: red;">${error }</span>
							</c:if>
							<div>
								<a href="${pageContext.request.contextPath}/my-dashboard">GO
									BACK TO DASHBOARD</a>
							</div>
						</c:when>
					</c:choose>
				</section>



			</div>
			<div class="col-md-5 col-lg-5"></div>
		</div>

	</div>


	<!-- BSE MF  -->
	<jsp:include page="./bsestarmfpowered.jsp"></jsp:include>
	<!-- END BSE MF  -->

	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>


	<jsp:include page="./disclaimer.jsp"></jsp:include>
</body>

<%-- 

	<script
		src="<c:url value="${contextPath}/resources/js/multistep.js" />"></script> --%>
<script type="text/javascript">
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
		//console.log("Current tab- "+currentTab  + " n-"+ n);
		// This function will figure out which tab to display
		var x = document.getElementsByClassName("tab");
		// Exit the function if any field in the current tab is invalid:

		if (n == 1 && currentTab == 1) {
			//		console.log("Display progress");
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

				var availamount = Number($("#marketVal").val());
				var redeemamount = Number($("#redeemamount").val());
				//console.log("Remeem amount- "+ redeemamount);
				if (!isNaN(redeemamount)) {
					$("#invalidamnt").text("");
					//$("#nextBtn").removeAttr("disabled");
					if (redeemamount > availamount) {
						//  console.log("Invalid")
						$("#invalidamnt").text(
								"Entered amount above available amount!");
						$("#nextBtn").attr("disabled", "disabled");
					} else if (redeemamount <= 0) {
						$("#invalidamnt").text("Invalid withdrwal amount");
						$("#nextBtn").attr("disabled", "disabled");
					} else {

						//console.log("valid")
						$("#invalidamnt").text("");
						$("#nextBtn").removeAttr("disabled");
					}
				} else {
					$("#invalidamnt").text("Invalid number");
					$("#nextBtn").attr("disabled", "disabled");
				}

			});

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
		}

	});
</script>

</html>