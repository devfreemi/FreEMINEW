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

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextPath}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextPath}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextPath}/resources/js/pace.min.js" />"></script>

<script src="<c:url value="${contextPath}/resources/js/investment.js" />"></script>
<style>
/* #sig-canvas {
	border: 1px solid #969696;
    cursor: crosshair;
    box-shadow: 0 0 6px 0px #b7b3b3;
} */
</style>
</head>

<script type="text/javascript">
	var currentTab = 0; // Current tab is set to be the first tab (0)
	//showTab(currentTab); // Display the current tab

	

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

<script src="<c:url value="${contextPath}/resources/js/bseinvest.js" />" async="async"></script>

</html>