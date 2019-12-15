<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan Request Received</title>
<meta name="keywords"
	content="Credit Line, Instant Credit,Personal Loan,Home Loan, Business Loan, Instant Credit, Credit Crad, No Cost EMI,personal loan, personal loan rate" />


<meta name="title" content="A Comprehensive Approach for Personal Loan" />
<meta name="description"
	content="Details of Personal Loan. Personal Loan bridges the gap between what we have and what we need. Personal Loan is an unsecured loan." />
<meta name="robots" content="index, nofollow">

<title>FreEMI Loans</title>

<link href="<c:url value="${contextcdn }/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="${contextcdn }/resources/css/pace-theme.css"/>" rel="stylesheet">
<script src="<c:url value="${contextcdn }/resources/js/pace.min.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>

<style type="text/css">
.icon-box {
	width: 105px;
	height: 105px;
	border-radius: 8px;
	box-shadow: 4px 5px 20px 0 rgba(16, 16, 16, 0.18);
	background-color: #3ad4b0;
	display: block;
	margin: 0px 15px 40px;
	position: relative;
	transition: all 0.1s ease-in-out;
	text-decoration: none;
	padding: 15px 5px;
	cursor: pointer;
}

.icon-box:hover {
	transform: scale(1.1);
	box-shadow: 0 0 8px 2px #b3aaaa;
}

.icon-txt {
	text-align: center;
	font-size: 11px;
	color: white;
}

hr.style-one {
	border: 0;
	height: 1px;
	background: #333;
	background-image: -webkit-linear-gradient(left, #ccc, #f79c60, #ccc);
	background-image: -moz-linear-gradient(left, #ccc, #f79c60, #ccc);
	background-image: -ms-linear-gradient(left, #ccc, #f79c60, #ccc);
	background-image: -o-linear-gradient(left, #ccc, #f79c60, #ccc);
}
</style>

</head>
<body>
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="include/header.jsp"></jsp:include>

	<div class="container" style="margin-bottom: 5rem;">
			<section>
		<div class="row" style="margin-bottom: 3rem;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; padding-top: 30px;">
				<div class="alert #f4511e deep-orange darken-1 white-text" role="alert" style="text-align: center;">
						<img
							src="https://resources.freemi.in/loans/resources/images/agent-support-2.png"
							alt="Agent support icon" class="img-fluid float-left">
					<span>Thank you for submitting your request. Our personal advisor will contact you to process your requirements within 24hrs.</span>
				</div>

			</div>
		</div>

		<hr class="style-one">

		<div class="other-products-title mb-3"> Our other services for you </div>

		<div class="row py-3"
			style="box-shadow: 0 0 8px 2px #d6d5d5;margin: auto;">
			<div class="col-md-12 col-lg-12 animated zoomIn">
				<div class="d-flex flex-wrap align-content-center">
					<a href="https://www.freemi.in/mutual-funds/" class="icon-box">
					 <img alt="Mutual Fund Investment"
							src="https://resources.freemi.in/loans/resources/images/invest/saving52.png"
							class="img-fluid"
							style="display: block; margin: auto;">
						<p class="icon-txt">Mutual Funds</p>
					</a>
					<a href="https://www.freemi.in/business-loan/?utm_source=succ1" class="icon-box">
						<img alt="Business Loan"
							src="<c:url value="https://resources.freemi.in/loans/resources/images/invest/business-loan51.png"/>"
							class="img-fluid"
							style="display: block; margin: auto;">
						<p class="icon-txt">Business Loan</p>
					</a>
					<a href="https://www.freemi.in/personal-loan/?utm_source=succ1" class="icon-box">
					<img alt="Personal Loan"
							src="https://resources.freemi.in/loans/resources/images/invest/personal-loan51.png"
							class="img-fluid"
							style="display: block; margin: auto;">
						<p class="icon-txt">Personal Loan</p>
				    </a>
					<a href="https://www.freemi.in/home-loan/?utm_source=succ1" class="icon-box">
					<img alt="Home Loan"
							src="https://resources.freemi.in/loans/resources/images/invest/home-loan51.png"
							class="img-fluid"
							style="display: block; margin: auto;">
						<p class="icon-txt">Home Loan</p>
					</a>
					<a href="https://www.freemi.in/health-insurance/?utm_source=succ1" class="icon-box">
				    <img alt="Health Insurance"
							src="https://resources.freemi.in/loans/resources/images/invest/health-insurance51.png"
							class="img-fluid"
							style="display: block; margin: auto;">
						<p class="icon-txt">Health Insurance</p>
					</a>
					<a href="https://www.freemi.in/car-insurance/?utm_source=succ1" class="icon-box">
					 <img alt="Car Insurance"
							src="https://resources.freemi.in/loans/resources/images/invest/carinsurance51.png"
							class="img-fluid"
							style="display: block; margin: auto;">
						<p class="icon-txt">Car Insurance</p>
					</a>
					<a href="https://www.freemi.in/two-wheeler-insurance/?utm_source=succ1" class="icon-box">
					<img alt="2 wheeler Insurance"
							src="https://resources.freemi.in/loans/resources/images/invest/motorcycle51.png"
							class="img-fluid"
							style="display: block; margin: auto;">
						<p class="icon-txt">Bike Insurance</p>
					</a>
					<a href="https://www.freemi.in/credit-card/?utm_source=succ1" class="icon-box">
					<img alt="Credit Card"
							src="https://resources.freemi.in/loans/resources/images/invest/creditcard51.png"
							class="img-fluid"
							style="display: block; margin: auto;">
						<p class="icon-txt">Credit Card</p>
					</a>
				</div>

			</div>
		</div>
		
		</section>

	</div>
	
	<jsp:include page="include/sub-footer.jsp"></jsp:include>
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>