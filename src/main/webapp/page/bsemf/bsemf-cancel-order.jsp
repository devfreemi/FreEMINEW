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
<script
	src="<c:url value="${contextPath}/resources/js/investment.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-7 col-lg-7">
				<h2 class="redeem-h2">Cancel Order</h2>
				<section class="redeem-form-section">
					<div style="background: #e4e3e1;">
						<%-- <div class="row">
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
						</div> --%>
					</div>

					<form:form class="form cf" id="regForm"
						action="${pageContext.request.contextPath}/mutual-funds/cancelOrder.do"
						method="POST" commandName="mfCencelForm">

						<!-- One "tab" for each step in the form: -->
						<!-- <div class="tab"> -->
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
										class="col-sm-4 col-form-label col-form-label-sm">Order
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
									<label for="fundname"
										class="col-sm-4 col-form-label col-form-label-sm">Scheme
										Code:</label>
									<div class="col-sm-8">
										<form:input readonly="true" path="schemeCode" id="fundCode"
											class="form-control form-control-sm form-control-plaintext" />
									</div>
								</div>

								<div class="form-group row">
									<label for="investtype"
										class="col-sm-4 col-form-label col-form-label-sm">Investment
										Type:</label>
									<div class="col-sm-8">
										<form:input readonly="true" path="investType" id="invtype"
											class="form-control form-control-sm form-control-plaintext" />

									</div>
								</div>

								<div class="form-group row">
									<label for="investtype"
										class="col-sm-4 col-form-label col-form-label-sm">Investment
										Amount:</label>
									<div class="col-sm-8">
										<form:input readonly="true" path="totalValue" id="invAmount"
											class="form-control form-control-sm form-control-plaintext" />

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
												<span style="font-size: 11px;"> I agree to the terms &amp; conditions <br>
											</span>
											</label>
										</div>

									</div>
								</div>


							</div>

						</div>
						<form:hidden path="redeemTransId"/>

						<div style="text-align: center;">
							<form:button type="submit" class="btn btn-sm btn-info"
								id="prevBtn"> Cancel Order</form:button>
						</div>
					</form:form>
				</section>
			</div>
			<div class="col-md-5 col-lg-5"></div>
		</div>

	</div>

</body>
</html>