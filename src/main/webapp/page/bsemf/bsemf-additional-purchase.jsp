<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FreEMI MF</title>
<meta name="robots" content="index,nofollow" />
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/multistep.css"/>" rel="stylesheet">
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
				<h2 class="redeem-h2">Additional Investment</h2>
				<section class="redeem-form-section">
					<div style="background: #e4e3e1;">
						<div class="row">
							<div class="col-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-6">
										<label>Folio No:</label>
									</div>
									<label class="col-6" style="font-weight: 600;">${purchaseForm.portfolio}</label>
								</div>
							</div>
							<div class="col-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-6">
										<label>Account Holder</label>
									</div>
									<label class="col-6" style="font-weight: 600;">${purchaseForm.unitHolderName}</label>
								</div>
							</div>
							<div class="col-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-6">
										<label>Date</label>
									</div>
									<label class="col-6" style="font-weight: 600;"><%=(new SimpleDateFormat("dd-MMM-yyyy")).format(new Date())%></label>
								</div>
							</div>
						</div>
					</div>

					<form:form class="form cf" id="regForm"
						action="${pageContext.request.contextPath}/mutual-funds/mfInvestAdditionalPurchase.do"
						method="POST" commandName="purchaseForm">

						<!-- One "tab" for each step in the form: -->
						<div class="tab">
							<h4 class="text-md-center" style="color: #f16927; font-weight: 500;border-bottom: 1px solid;">1. Scheme Details</h4>
							<div class="row">
								<div class="col-md-12 col-lg-12" style="text-align: left;">
									<div>
										<span id="mandateField" style="color: red;"></span>
									</div>
									<c:if test="${error!= null }">
										<span style="color: red;">${error }</span>
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
										<label for="schemeName"
											class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Scheme
											Code</label>
										<div class="col-6 col-md-8 col-lg-8">
											<%-- <input type="text" readonly class="form-control-plaintext"
									id="schemeName" value="${selectedFund.schemeName }"> --%>
											<span id="growthcode"> <form:input
													path="growthSchemeCode" readonly="readonly"
													class="form-control-plaintext" />
											</span> <span id="reinvcode" style="display: none;"> <form:input
													path="reinvSchemeCode" readonly="readonly"
													class="form-control-plaintext" />
											</span>
										</div>
									</div>

									<div class="form-group row">
										<label for="investtype"
											class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Investment
											Type:</label>
										<div class="col-6 col-md-8 col-lg-8">

											<div
												class="custom-control custom-radio custom-control-inline">
												<form:radiobutton path="investType" value="LUMPSUM"
													id="investtype1" name="investType"
													class="custom-control-input" />
												<label class="custom-control-label" for="investtype1">Lumpsum</label>
											</div>
											<%-- <div
												class="custom-control custom-radio custom-control-inline">
												<form:radiobutton path="investType" value="SIP"
													id="investtype2" name="investType"
													class="custom-control-input" />
												<label class="custom-control-label" for="investtype2">SIP</label>
											</div> --%>

										</div>
									</div>


									<div class="form-group row">
										<label for="redeemamount"
											class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Purchase
											Amount:</label>
										<div class="col-6 col-md-8 col-lg-8">
											<form:input path="purchaseAmounts" id="purchaseamount"
												class="form-control form-control-sm" />
										</div>
									</div>

								</div>
							</div>
						</div>

						<div class="tab">
							<div class="row">
								<div class="col-md-12 col-lg-12 purchase-confirm-tab">
									<h4 class="text-md-center" style="color: #f16927; font-weight: 500;border-bottom: 1px solid;">2. Confirm Details</h4>
									<!-- ------------------------------  Confirm tab ------------------------------------------------------>

									<div class="form-group row mb-1">
										<label for="folioconf"
											class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Folio
											No:</label>
										<div class="col-6 col-md-8 col-lg-8">
											<label class="confirm-label" id="folioconf">${purchaseForm.portfolio }</label>
										</div>
									</div>

									<div class="form-group row mb-1">
										<label for="fundnameconf"
											class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Scheme
											Name:</label>
										<div class="col-6 col-md-8 col-lg-8">
											<label class="confirm-label" id="fundnameconf">${purchaseForm.fundName }</label>
										</div>
									</div>

									<div class="form-group row mb-1">
										<label for="investtypeconf"
											class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Investment
											Type:</label>
										<div class="col-6 col-md-8 col-lg-8">
											<label class="confirm-label" id="investtypeconf">${purchaseForm.investType }</label>

										</div>
									</div>

									<div class="form-group row mb-1">
										<label for="redeemamountconf"
											class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Purchase
											Amount:</label>
										<div class="col-6 col-md-8 col-lg-8">
											<label class="confirm-label" id="purchaseamountconf"></label>
										</div>
									</div>

									<hr>
									<div class="form-group row mb-1">
										<div class="col-md-12 col-lg-12">

											<div class="custom-control custom-checkbox">
												<span data-toggle="modal" data-target="#mfdisclaimer"
													style="text-decoration: underline; cursor: pointer; color: blue; font-size: 11px;">Disclaimer</span>
												<form:checkbox path="agreePolicy"
													class="custom-control-input" id="agreepolicyconf" />
												<label class="custom-control-label" for="agreepolicyconf">
													<span style="font-size: 11px; text-align: justify;">
														1. Performance history may or may not be in sync with the
														future performance and should not be considered as a base
														for investments. <br> 2. The size of the Assets Under
														Management (AuM) are based on the last published Monthly
														AUM by the corresponding fund house. <br> 3. If the
														investors are confused about whether the product is
														suitable for them or not, then he should consult their
														financial advisers for a better guidance. <br>

												</span>
												</label>
											</div>

										</div>
									</div>

									<form:hidden path="purchaseTransid" />
									<!-- End of confirm tab -->

									<%-- <div class="form-group row">
												<div class="col-sm-3" style="margin: auto;">
													<form:button type="submit"
														class="btn btn-outline-secondary btn-block btn-sm">Submit</form:button>
												</div>
											</div> --%>
								</div>
							</div>
						</div>
						
						<jsp:include page="transaction-in-progress-icon.jsp"></jsp:include>

						<div style="overflow: auto;">
							<div style="float: right;">
								<form:button type="button" class="btn btn-sm btn-info"
									id="prevBtn" onclick="nextPrevAdditional(-1)">
									<i class="fas fa-arrow-left"></i> Previous</form:button>
								<form:button type="button" class="btn btn-sm btn-success"
									id="nextBtn" onclick="nextPrevAdditional(1)">Next <i
										class="fas fa-angle-right"></i>
								</form:button>
							</div>
						</div>

						<!-- Circles which indicates the steps of the form: -->
						<div style="text-align: center; margin-top: 40px;">
							<span class="step"></span> <span class="step"></span>
						</div>
					</form:form>
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

<script type="text/javascript">
	var currentTab = 0; // Current tab is set to be the first tab (0)
	showTabAdditional(currentTab); // Display the current tab

	function showTabAdditional(n) {
		//console.log("Show current tab.")
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
			//console.log("Additional purhase- " + $("#purchaseamount").val())
			$("#purchaseamountconf").text($("#purchaseamount").val());
			/* $("#paymodeconf").text($("#input[name='pay']:checked").val()); */
		} else {
			document.getElementById("nextBtn").innerHTML = "Next";
		}
		// ... and run a function that displays the correct step indicator:
		fixStepIndicatorAdditional(n)
	}

	function nextPrevAdditional(n) {
		// This function will figure out which tab to display
		//console.log("Next page...");
		var x = document.getElementsByClassName("tab");
		// Exit the function if any field in the current tab is invalid:
		//console.log("n- " + n)
		
		if (n == 1 && currentTab == 1) {
//		console.log("Display progress");
		$("#display_progress").css({
			"display" : "block"
		});
		
		$("#prevBtn").attr("disabled", "disabled");
		$("#nextBtn").attr("disabled", "disabled");
		
	}
		
		if (n == 2) {
			//console.log("Return false");
			return false;
		}
		// Hide the current tab:
		x[currentTab].style.display = "none";
		// Increase or decrease the current tab by 1:
		currentTab = currentTab + n;
		// if you have reached the end of the form... :
		if (currentTab >= x.length) {
			//...the form gets submitted:
			document.getElementById("regForm").submit();
			return false;
		}
		// Otherwise, display the correct tab:
		//console.log("SHow tab- " + currentTab);
		showTabAdditional(currentTab);
	}

	function validateForm() {
		// This function deals with validation of the form fields
		//console.log("Validate additional purchase");
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
			}
		}
		// If the valid status is true, mark the step as finished and valid:
		if (valid) {
			document.getElementsByClassName("step")[currentTab].className += " finish";
		}
		return valid; // return the valid status
	}

	function fixStepIndicatorAdditional(n) {
		// This function removes the "active" class of all steps...
		var i, x = document.getElementsByClassName("step");
		for (i = 0; i < x.length; i++) {
			x[i].className = x[i].className.replace(" active", "");
		}
		//... and adds the "active" class to the current step:
		x[n].className += " active";
	}
</script>
<script src="<c:url value="${contextcdn}/resources/js/bseinvest.js" />" async="true"></script>
</html>

