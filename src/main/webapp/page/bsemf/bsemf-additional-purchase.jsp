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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
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
		<div class="row">
			<div class="col-md-7 col-lg-7">
				<h2 class="redeem-h2">Additional Investment</h2>
				<section class="redeem-form-section">
					<div style="background: #e4e3e1;">
						<div class="row">
							<div class="col-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-sm-6">
										<label>Folio No:</label>
									</div>
									<label class="col-sm-6" style="font-weight: 600;">${purchaseForm.portfolio}</label>
								</div>
							</div>
							<div class="col-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-sm-6">
										<label>Account Holder</label>
									</div>
									<label class="col-sm-6" style="font-weight: 600;">${purchaseForm.unitHolderName}</label>
								</div>
							</div>
							<div class="col-4">
								<div class="form-group row" style="font-size: 12px;">
									<div class="col-sm-6">
										<label>Date</label>
									</div>
									<label class="col-sm-6" style="font-weight: 600;"><%=(new SimpleDateFormat("dd-MMM-yyyy")).format(new Date())%></label>
								</div>
							</div>
						</div>
					</div>

					<form:form class="form cf" id="regForm"
						action="${pageContext.request.contextPath}/mutual-funds/mfInvestAdditionalPurchase.do"
						method="POST" commandName="purchaseForm">

						<!-- One "tab" for each step in the form: -->
						<div class="tab">
							<h4 class="text-md-center">1. Scheme Details</h4>
									<div class="row">
										<%-- <jsp:include page="bseform-redeem.jsp"></jsp:include> --%>
										<div class="col-md-12 col-lg-12" style="text-align: left;">
											<div>
												<span id="mandateField" style="color: red;"></span>
											</div>
											<c:if test="${error!= null }">
												<span style="color: red;">${error }</span>
											</c:if>

											<div class="form-group row">
												<label for="folio"
													class="col-sm-4 col-form-label col-form-label-sm">Folio
													No:</label>
												<div class="col-sm-8">
													<form:input readonly="true" path="portfolio" id="folio"
														class="form-control form-control-sm form-control-plaintext" />
												</div>
											</div>

											<div class="form-group row">
												<label for="fundname"
													class="col-sm-4 col-form-label col-form-label-sm">Scheme
													Name:</label>
												<div class="col-sm-8">
													<form:input readonly="true" path="fundName" id="fundname"
														class="form-control form-control-sm form-control-plaintext" />
												</div>
											</div>

											<div class="form-group row">
												<label for="investtype"
													class="col-sm-4 col-form-label col-form-label-sm">Investment
													Type:</label>
												<div class="col-sm-8">

													<div
														class="custom-control custom-radio custom-control-inline">
														<form:radiobutton path="investType" id="investtype"
															value="${purchaseForm.investType }"
															class="custom-control-input" />
														<label class="custom-control-label" for="invType">${purchaseForm.investType }</label>
													</div>

												</div>
											</div>

											<div class="form-group row">
												<label for="redeemamount"
													class="col-sm-4 col-form-label col-form-label-sm">Purchase
													Amount:</label>
												<div class="col-sm-8">
													<form:input path="purchaseAmounts" id="purchaseamount"
														class="form-control form-control-sm" />
												</div>
											</div>

											<div class="form-group row">
												<label for="paymodet"
													class="col-sm-4 col-form-label col-form-label-sm">Payment
													Mode:</label>
												<div class="col-sm-8">

													<c:choose>
														<c:when test="${purchaseForm.investType == 'SIP' }">
															<div
																class="custom-control custom-radio custom-control-inline">
																<form:radiobutton path="paymentMode" id="paymode3"
																	value="BANKDEBIT" class="custom-control-input" />
																<label class="custom-control-label" for="paymode3">BANK
																	DEBIT</label>
															</div>

														</c:when>

														<c:when test="${purchaseForm.investType == 'LUMPSUM' }">
															<div
																class="custom-control custom-radio custom-control-inline">
																<form:radiobutton path="paymentMode" name="pay" id="paymode1"
																	value="NETBANKING" class="custom-control-input" />
																<label class="custom-control-label" for="paymode1">NETBANKING</label>
															</div>
															<div
																class="custom-control custom-radio custom-control-inline">
																<form:radiobutton path="paymentMode" name="pay" id="paymode2"
																	value="CARD" class="custom-control-input" />
																<label class="custom-control-label" for="paymode2">CARD</label>
															</div>

														</c:when>

													</c:choose>

												</div>
											</div>

											<%-- <div class="form-group row">
												<div class="col-sm-3" style="margin: auto;">
													<form:button type="button"
														class="btn btn-outline-secondary btn-block btn-sm next-step next-button">NEXT</form:button>
												</div>
											</div> --%>

										</div>

										<%-- <ul class="list-inline text-md-center">
										<li><!-- <button type="button"
												class="btn btn-lg btn-common next-step next-button">next</button> -->
											<div class="form-group row">
											<div class="col-sm-3" style="margin: auto;">
												<form:button type="button"
													class="btn btn-outline-secondary btn-block btn-sm next-step next-button">Submit</form:button>
											</div>
										</div>
												</li>
									</ul> --%>
									</div>
						</div>

						<div class="tab">
							<div class="row">
										<div class="col-md-12 col-lg-12 purchase-confirm-tab">
											<h4 class="text-md-center">2. Confirm Details</h4>
											<!-- ------------------------------  Confirm tab ------------------------------------------------------>

											<div class="form-group row mb-1">
												<label for="folioconf"
													class="col-sm-4 col-form-label col-form-label-sm">Folio
													No:</label>
												<div class="col-sm-8">
													<label class="confirm-label" id="folioconf">${purchaseForm.portfolio }</label>
												</div>
											</div>

											<div class="form-group row mb-1">
												<label for="fundnameconf"
													class="col-sm-4 col-form-label col-form-label-sm">Scheme
													Name:</label>
												<div class="col-sm-8">
													<label class="confirm-label" id="fundnameconf">${purchaseForm.fundName }</label>
												</div>
											</div>

											<div class="form-group row mb-1">
												<label for="investtypeconf"
													class="col-sm-4 col-form-label col-form-label-sm">Investment
													Type:</label>
												<div class="col-sm-8">
													<label class="confirm-label" id="investtypeconf">${purchaseForm.investType }</label>

												</div>
											</div>

											<div class="form-group row mb-1">
												<label for="redeemamountconf"
													class="col-sm-4 col-form-label col-form-label-sm">Purchase
													Amount:</label>
												<div class="col-sm-8">
													<label class="confirm-label" id="redeemamountconf"></label>
												</div>
											</div>

											<div class="form-group row mb-1">
												<label for="paymodeconf"
													class="col-sm-4 col-form-label col-form-label-sm">Payment
													Mode:</label>
												<div class="col-sm-8">

													<label class="confirm-label" id="paymodeconf"></label>

												</div>
											</div>

											<div class="form-group row mb-1">
												<label for="agreepolicyconf"
													class="col-sm-4 col-form-label col-form-label-sm"></label>
												<div class="col-sm-8">

													<div class="custom-control custom-checkbox">
														<form:checkbox path="agreePolicy"
															class="custom-control-input" id="agreepolicyconf" />
														<label class="custom-control-label" for="agreepolicyconf">
															<span style="font-size: 11px;"> Liquid Schemes
																Subscriptions: <strong>01.00 pm</strong> <br>
																Liquid Schemes Redemptions &amp; Switch Out: <strong>02:00
																	pm</strong> <br> All other Schemes Purchases, Redemptions
																&amp; Switch Out: <strong>02.15:00 pm</strong> <br>
														</span>
														</label>
													</div>

												</div>
											</div>


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

						<div style="overflow: auto;">
							<div style="float: right;">
								<form:button type="button" class="btn btn-sm btn-info"  id="prevBtn" onclick="nextPrev(-1)"><i class="fas fa-arrow-left"></i> Previous</form:button>
								<form:button type="button" class="btn btn-sm btn-success" id="nextBtn" onclick="nextPrev(1)">Next <i class="fas fa-angle-right"></i></form:button>
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
			
			$("#redeemamountconf").text($("#purchaseamount").val());
			$("#paymodeconf").text($("#input[name='pay']:checked").val());
		} else {
			document.getElementById("nextBtn").innerHTML = "Next";
		}
		// ... and run a function that displays the correct step indicator:
		fixStepIndicator(n)
	}

	function nextPrev(n) {
		// This function will figure out which tab to display
		var x = document.getElementsByClassName("tab");
		// Exit the function if any field in the current tab is invalid:
		if (n == 1 && !validateForm())
			return false;
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
	

</script>
	
</body>
</html>