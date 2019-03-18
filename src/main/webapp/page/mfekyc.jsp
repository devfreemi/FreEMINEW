<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="keywords" content="" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description" content="" />
<meta name="robots" content="noindex,nofollow" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FreEMI Product</title>
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/kyc.css"/>"
	rel="stylesheet">

<jsp:include page="include/bootstrap.jsp"></jsp:include>
<script
	src="<c:url value="${contextcdn}/resources/js/kyc-verify.js" />"></script>
<link
	href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
</head>

<script type="text/javascript">
	function checkModal() {
		validatePAN();
		//	console.log("Called");
		var otpBox = "${otpbox}";
		//	console.log(otpBox);
		if (otpBox == 'true') {
			console.log("True");
			$('#exampleModalCenter').modal('show');
			//setTimeout(closePopUp, 15000);
			countDownTimer();

		}
	}
</script>

<body class="back_set"
	onload="checkModal();">
	<jsp:include page="include/header.jsp"></jsp:include>

	<div class="container-fluid freemi_container">
		<div class="info-border">
			<h3 class="heading1">e-KYC verification using Aadhaar</h3>
			<div class="line-through"></div>
			<div class="freemi_choice">
				<p>Thank you for choosing FreEMI as your investment partner. You
					can now complete your eKYC formalities online in a seamless and
					convenient manner using your Aadhaar number.</p>
			</div>
			<div class="notes">
				<div class="kyc_info">
					<div class="row" style="max-width: 95rem;">
						<div class="col-md-9 col-lg-9">

							<h4 class="heading2">Instructions for Aadhaar based e-KYC</h4>
							<ul class="points">
								<li><strong>Provide you PAN number</strong> - System will
									verify if e-KYC has been completed earlier.</li>
								<li><strong>Provide your Aadhaar number</strong> - System
									will validate your Aadhaar with Unique Identification Authority
									of India (UIDAI). An OTP will be sent by UIDAI upon successful
									validation to your registered mobile number. <br> Enter
									the OTP number on the provided screen. Upon successful
									validation, information from UIDAI will be fetched.</li>
								<li><strong>Additional KYC information</strong> - Required
									by AMC to complete your KYC and invest in mutual fund.</li>
								<li><strong>Happy Investing!</strong> <br> Data
									provided by you will be sent to Central KYC Registry (CKYCR)
									for generation of your KYC Identification Number (KIN). <br>
									Investors completing KYC through OTP based eKYC are permitted
									to invest &#x20B9;50,000 per Mutual Fund per year.</li>
							</ul>
						</div>
						<div class="col-md-3 col-lg-3" style="padding-top: 30px;">
							<div id="carouselExampleSlidesOnly" class="carousel slide"
								data-ride="carousel">
								<div class="carousel-inner">
									<div class="carousel-item active">
										<img class="d-block w-100" src="<c:url value="${contextcdn}/resources/images/ekyc1.png"/>" alt="First slide">
									</div>
									<div class="carousel-item">
										<img class="d-block w-100" src="<c:url value="${contextcdn}/resources/images/ekyc2.png"/>" alt="Second slide">
									</div>
									<div class="carousel-item">
										<img class="d-block w-100" src="<c:url value="${contextcdn}/resources/images/ekyc3.png"/>" alt="Third slide">
									</div>
									<div class="carousel-item">
										<img class="d-block w-100" src="<c:url value="${contextcdn}/resources/images/ekyc4.png"/>" alt="Fourth slide">
									</div>
								</div>
							</div>
						</div>
					</div>



				</div>
				<form:form commandName="mfInvestForm"
					action="${pageContext.request.contextPath}/validatePAN.do" method="POST">
					<div class="row" style="margin-top: 20px; max-width: 100rem;">
						<div class="col-md-4 col-lg-4" style="margin: auto;">

							<div class="form-group row">
								<label for="panbox" class="col-sm-4 col-form-label">PAN
									Number :</label>
								<div class="col-sm-8" style="margin-left: -1px;">
									<c:if test="${error!=null }">
										<div>
											<span style="font-size: 11px; color: red;"> ${error} </span>
										</div>
									</c:if>
									<form:input type="text" class="form-control" path="PAN"
										id="panbox" style="text-transform: uppercase;" maxlength="10"
										readonly="${panread }" />
								</div>
							</div>
						</div>
					</div>

					<c:if test="${showAadhaar == true }">
						<div class="row" style="margin-top: 20px; max-width: 100rem;">
							<div class="col-md-4 col-lg-4" style="margin: auto;">

								<c:if test="${panStatus.isKYCVerified == 'N'}">
									<span class="non_kyc">You are not KYC-verified</span>
									<br>
									<span>Please complete your KYC process with your AADHAAR
										number</span>
								</c:if>
							</div>
						</div>

						<div class="row" style="margin-top: 20px; max-width: 100rem;">

							<div class="col-md-4 col-lg-4" style="margin: auto;">
								<div class="form-group row">
									<label for="panbox" class="col-sm-4 col-form-label">Aadhaar
										Number :</label>
									<div class="col-sm-8" style="margin-left: -1px;">
										<form:input type="text" class="form-control" path="aadhaar"
											id="aadhaarbox" style="text-transform: uppercase;"
											maxlength="12" />
									</div>
								</div>
							</div>
						</div>
					</c:if>

					<div class="row"
						style="margin-top: 20px; max-width: 100rem; margin-bottom: 20px;">
						<div class="col-md-3 col-lg-3"
							style="margin: auto; text-align: center;">
							<button type="submit" id="kycdetails"
								class="btn btn-primary btn-sm">
								Validate and proceed <i class="fas fa-chevron-right"></i>
							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>

	</div>
	<jsp:include page="include/aadhaarotp.jsp"></jsp:include>
</body>
</html>