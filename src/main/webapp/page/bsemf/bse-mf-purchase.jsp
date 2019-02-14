<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreEMI MF Transaction</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description" content="" />
<meta name="robots" content="index,nofollow" />
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container">
		<div class="row" style="margin: auto;">
			<div class="col-md-8 col-lg-8 purchase-form" style="margin: auto;">
				<form:form
					action="${pageContext.request.contextPath}/mutual-funds/mfPurchaseConfirm.do"
					method="POST" commandName="selectedFund">
					<h5>
						<img
							src="<c:url value="${contextcdn}/resources/images/invest/investor-2.svg"/>"
							alt="Investor icon" style="height: 1.5rem;"> Investor
						Details
					</h5>
					<c:if test="${GETDATA == 'S' }">
						<div class="form-group row">
							<label for="custName" class="col-sm-3 col-form-label">Investor
								name</label>
							<div class="col-sm-9">
								<input type="text" readonly class="form-control-plaintext"
									id="custName" value="${customerData.invName }">
							</div>
						</div>
						<div class="form-group row">
							<label for="custName" class="col-sm-3 col-form-label">Mobile
							</label>
							<div class="col-sm-9">
								<input type="text" readonly class="form-control-plaintext"
									id="custName" value="${customerData.mobile	 }">
							</div>
						</div>
					</c:if>
					<c:if test="${GETDATA == 'F' }">
						<div>
							<p>Unable to fetch investor data!</p>
						</div>
					</c:if>
					<h5>
						<img
							src="<c:url value="${contextcdn}/resources/images/invest/cart-2.svg"/>"
							alt="Cart icon" style="height: 1.5rem;"> Purchase Details
					</h5>
					<div class="form-group row">
						<label for="folio" class="col-sm-3 col-form-label">Folio
							No.</label>
						<div class="col-sm-9">
							<form:select path="portfolio"
								class="form-control form-control-sm">
								<form:options items="${amcPortFolio }" />
							</form:select>
						</div>
					</div>
					<div class="form-group row">
						<label for="schemeName" class="col-sm-3 col-form-label">Scheme
							Name</label>
						<div class="col-sm-9">
							<input type="text" readonly class="form-control-plaintext"
								id="schemeName" value="${selectedFund.schemeName }">
						</div>
					</div>
					<div class="form-group row">
						<label for="schemeName" class="col-sm-3 col-form-label">Investment
							Amount</label>
						<div class="col-sm-9">
							<input type="text" readonly class="form-control-plaintext"
								id="schemeName" value="${selectedFund.investAmount }">
						</div>
					</div>
					<div class="form-group row">
						<label for="schemeName" class="col-sm-3 col-form-label">Investment
							Type</label>
						<div class="col-sm-9">
							<input type="text" readonly class="form-control-plaintext"
								id="schemeName" value="${selectedFund.investype }">
						</div>
					</div>
					<c:if test="${selectedFund.investype == 'SIP' }">
						<div class="form-group row">
							<label for="schemeName" class="col-sm-3 col-form-label">Monthly
								SIP Date</label>
							<div class="col-sm-9">
								<input type="text" readonly class="form-control-plaintext"
									id="schemeDate" value="${selectedFund.sipDate }">
							</div>
						</div>

						<div class="form-group row">
							<label for="schemeName" class="col-sm-3 col-form-label">Start
								From</label>
							<div class="col-6 col-md-5"
								style="padding-right: 2px; margin: auto;">
								<%-- <input type="text" readonly class="form-control-plaintext"
									id="schemeStartFrom" value="${selectedFund.sipStartMonth}-${selectedFund.sipStartYear}"> --%>
								<form:select class="custom-select form-control-sm" id="sipmonth"
									path="sipStartMonth">
									<form:options items="${calendarmonths}" />
								</form:select>
							</div>
							<div class="col-6 col-md-4"
								style="padding-left: 0px; margin: auto;">
								<form:select class="custom-select form-control-sm" id="sipyear"
									path="sipStartYear">
									<form:options items="${sipyear}" />
								</form:select>
							</div>
						</div>


					</c:if>

					<!-- <h5>Payment Option</h5> -->
					<%-- <div class="form-group row">
						<label for="schemeName" class="col-sm-3 col-form-label">Payment
							Option</label>
						<div class="col-sm-9" style="margin: auto;">
							<div class="custom-control custom-radio custom-control-inline">
								<form:radiobutton path="paymentMethod" value="NETBANK"
									id="paymentRadio1" name="paymentRadio1"
									class="custom-control-input" />
								<label class="custom-control-label" for="paymentRadio1">NET_BANKING</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<form:radiobutton path="paymentMethod" value="CARD"
									id="paymentRadio2" name="paymentRadio2"
									class="custom-control-input" />
								<label class="custom-control-label" for="paymentRadio2">CARD</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<form:radiobutton path="paymentMethod" value="UPI"
									id="paymentRadio3" name="paymentRadio3"
									class="custom-control-input" />
								<label class="custom-control-label" for="paymentRadio3">UPI</label>
							</div>
						</div>
					</div> --%>
					<div class="custom-control custom-checkbox"
						style="height: 150px; overflow-y: auto;">
						<input type="checkbox" class="custom-control-input"
							id="agreecheck1"> <label class="custom-control-label"
							for="agreecheck1" style="font-size: 11px; text-align: justify;">
							I/We have read and understood the contents of the Scheme
							Information Document(s), Key Information Memorandum and Addenda
							issued for the respective scheme(s). I/We hereby apply to the
							Trustee of Mutual Fund and agree to abide by terms and
							conditions, rules and regulation of the relevant scheme(s) /
							Mutual Fund. I/We have neither received nor been induced by any
							rebate or gifts, directly or indirectly in making this
							investment. The ARN holder has disclosed to me/us all the
							commissions (in the form of trail commission or any other mode),
							payable to him for the different competing Schemes of various
							Mutual Funds from amongst which the Scheme is being recommended
							to me/us. I/We hereby confirm that I/we have not been offered /
							communicated any indicative portfolio and/or any indicative yield
							by the respective Mutual Fund / its distributor for this
							investment. I/We am/are authorized to undertake this transaction.
							<br> <br> Any other advisory charges shall be paid
							directly by the investor to the ARN Holder (AMFI registered
							Distributor) based on the investors' assessment of various
							factors including the service rendered by the ARN Holder. In case
							of existing investor where investment amount is Rs.10,000/- or
							more and your Distributor has opted to receive Transaction
							Charges, Rs.100/- will be deducted from the purchase amount and
							paid to the Distributor. Units will be issued against the balance
							amount invested. I/We confirm that payment for this transaction
							has been done through my/own bank account number which is
							registered in the folio and no third party account is used for
							such payments. In case of any non-compliance, I/We authorize you
							to refund the said amount to the account where it is debited and
							will liable for any consequences arising thereof.
						</label>
					</div>
					<div style="text-align: center; margin-top: 20px;">
						<form:button type="submit" class="btn btn-outline-danger btn-sm">Confirm Order</form:button>
					</div>
				</form:form>
			</div>

		</div>
	</div>

	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>