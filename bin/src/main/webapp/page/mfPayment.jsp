<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextPath}/resources/css/investmentform.css"/>"
	rel="stylesheet">
<title>Payment</title>
</head>
<body class="back_set">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">

		<div>
			<div class="row" style="padding: 5px;">
				<div class="col-md-8 col-lg-8 transaction_status">
					<div class="payment_header">
						<h5>
							<i class="fas fa-shopping-cart"></i> Transaction Status
						</h5>
					</div>

					<div class="row">
						<div class="col-md-12 col-lg-12" style="text-align: center;">
							<span
								style="font-size: 16px; font-weight: 600; font-family: monospace;">Congratulations!!</span>
							<div>We have successfully created a folio for your wish(s)</div>
						</div>
					</div>

					<div style="border-top: 1px solid #b5b1b1; margin: 10px 0;">
						<span></span>
					</div>

					<div class="row">
						<div class="col-6">
							<label>Folio number</label>

						</div>
						<div class="col-6" style="margin-left: -1px;">
							<span class="data_style"> ${folioStatus.folioNumber } </span>
						</div>
					</div>

					<c:if test="${folioStatus.savePreSIPSuccessful== true }">

						<div class="row">
							<div class="col-6">
								<label>AMC transaction no.</label>

							</div>
							<div class="col-6" style="margin-left: -1px;">
								<span class="data_style"> ${folioStatus.transNo } </span>
							</div>
						</div>

						<div class="row">
							<div class="col-6">
								<span><label>URN number </label> </span>
							</div>
							<div class="col-6" style="margin-left: -1px;">
								<span class="data_style"> ${folioStatus.urnNumber } </span>
							</div>
						</div>

						<div class="row">
							<div class="col-6">
								<span><label>Monthly installment </label> </span>
							</div>
							<div class="col-6" style="margin-left: -1px;">
								<span class="data_style">&#x20B9; <fmt:formatNumber
										value="${mfInvestForm.selectedFund.monthlySavings }"
										type="number" maxFractionDigits="2" />
								</span>
							</div>
						</div>


						<c:if test="${investType!='TARGET_PLAN' }">
							<div class="row">
								<div class="col-md-12 col-lg-12">
									<span class="urn_topic">Please register Aditya Birla as
										your biller with your bank account (net-banking) using the URN
										number within 3 days.</span>
								</div>
							</div>

						</c:if>

						<c:if test="${paymentsucess=='N' }">
							
							<c:if test="${investType!='TARGET_PLAN' }">
							<div style="text-align: center;">
								<span class="pay_topic"> Complete your transaction by
									paying the first installment</span>
							</div>
							</c:if>
							<c:if test="${investType=='TARGET_PLAN' }">
							<div style="text-align: center;">
								<span class="pay_topic"> Proceed to pay your Target Plan</span>
							</div>
							</c:if>
							
							<div class="row">

								<div class="col-md-16 col-lg-6" style="margin: auto;">
									<form:form method="POST"
										action="${pageContext.request.contextPath}/registry-mutual-funds/processPay.do"
										commandName="mfInvestForm">
										<script src="https://checkout.razorpay.com/v1/checkout.js"
											data-key="rzp_test_EoKq4RCiyXYAgT"
											data-amount="${mfInvestForm.selectedFund.monthlySavings*100}"
											data-buttontext="Pay your First Installment"
											data-name="Aditya Birla"
											data-description="Payment for mutual fund"
											data-image="http://localhost:8080/products/resources/images/freemi.png"
											data-prefill.name="${mfInvestForm.invName}"
											data-prefill.contact="${mfInvestForm.mobile}"
											data-prefill.email="${mfInvestForm.email}"
											data-theme.color="#F37254"></script>

										<%-- <form:hidden path="invDOB" id="investorDOB" /> --%>
									</form:form>
								</div>
							</div>
						</c:if>
					</c:if>
					<c:if test="${folioStatus.savePreSIPSuccessful== false }">
						<div class="row">
							<div class="col-md-12 col-lg-12"></div>
							Failed to save your scheme data. Reason-
							${folioStatus.savePreSIPMessage }
						</div>
						<div>You can re-try your transaction with generated folio
							number.</div>

					</c:if>
					<c:if test="${postsipstarted=='Y' }">


						<c:if test="${folioStatus.savePostSIPSuccessfull== false }">
							<div class="row">
								<div class="col-md-12 col-lg-12"></div>
								Failed process post sip to CAMS . Reason-
								${folioStatus.savePostSIPMessage }
							</div>
							<div>You can re-try your transaction with generated folio
								number.</div>
						</c:if>
						<c:if test="${folioStatus.savePostSIPSuccessfull== true }">
							<c:if test="${folioStatus.camsEntrySuccessful== true }">

								<div class="row cams_success">
									<div class="col-md-8 col-lg-8" style="margin: auto;">
										<span class="data_style">Your profile successfully
											registered with CAMs</span>
									</div>

								</div>

								<div class="row">
									<div class="col-md-6 col-lg-6">
										<span><label>CAMS Entry Date </label> </span>
									</div>
									<div class="col-md-6 col-lg-6">
										<span class="data_style"> ${folioStatus.camsEntryDate }
										</span>
									</div>
								</div>
							</c:if>

							<c:if test="${folioStatus.camsEntrySuccessful== false }">

								<div class="row cams_failure">
									<div class="col-md-8 col-lg-8" style="margin: auto;">
										<span class="data_style">CAMS entry unsuccessful</span>
									</div>

								</div>

								<div class="row">
									<div class="col-md-6 col-lg-6">
										<span><label>CAMS error failure reason</label> </span>
									</div>
									<div class="col-md-6 col-lg-6">
										<span class="data_style">
											${folioStatus.camsReturnMessage } </span>
									</div>
								</div>

							</c:if>

						</c:if>

					</c:if>

				</div>
			</div>

		</div>

		<div class="mfInfo">
			<div class="row">
				<div class="col-md-8 col-lg-8">
					<jsp:include page="include/mfinfo.jsp"></jsp:include>
				</div>
			</div>


		</div>


	</div>

	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>