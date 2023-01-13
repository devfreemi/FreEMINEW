<!DOCTYPE html">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login to FreEMI portal and get access to your dash-board
	and transactions</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="keywords"
	content="freemi login, Sign up for free Online Mutual Fund account, Get best health insurance by signup, get Instant credit by signup" />
<meta name="description"
	content="Login to your FreEMI account and view your transactions, wishes completed" />

<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow" />
<meta name="bingbot" content="index, follow" />

<link rel="canonical" href=" https://www.freemi.in/products/login" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<link href="<c:url value="${contextcdn}/resources/css/login23.css"/>"
	type="text/css" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>"
	type="text/css" rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/login23.js" />"
	type="text/javascript" defer="defer"></script>

<style>
.timer_style {
	text-align: center;
	margin-bottom: 20px;
	font-weight: 400;
	color: #e46a39;
	/* font-family: monospace; */
	font-size: 16px;
}

@media screen and (max-width: 320px) {
	.signupbtnclass, .loginbtnclass {
		padding: .5rem 1.6rem;
		font-size: .84rem;
	}
}
</style>
</head>
<body>
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="include/header.jsp"></jsp:include>

	<!-- MultiStep Form -->
	<div class="container-fluid" id="grad1">
		<div class="row justify-content-center mt-0">
			<div
				class="col-11 col-sm-9 col-md-7 col-lg-4 text-center p-0 mt-3 mb-2">
				<div class="card px-0 pt-4 pb-0 mt-3 mb-3">
					<h5>Login to your account</h5>
					<div class="row">
						<div class="col-md-12 mx-0">
							<form:form id="msform" modelAttribute="login">
								<!-- progressbar -->
								<ul id="progressbar">
									<li class="active" id="account"><strong>Account</strong></li>
									<li id="personal"><strong>Verification</strong></li>

								</ul>
								<!-- fieldsets -->
								<div>
									<div>
										<span id="infomsg"
											style="color: green; font-weight: 400; font-size: 13px;">${info }</span>
									</div>

									<div>
										<span id="loginmsg"
											style="color: #ff7623; font-weight: 400; font-size: 13px;"></span>
									</div>
								</div>
								<fieldset>
									<div class="form-card">
										<h6>Account Information</h6>
										<form:input type="text" style="padding-left: 5px;"
											id="validationCustomUsername"
											class="form-control form-control-sm" path="usermobile"
											pattern="[0-9]{10}" maxlength="10" autocomplete="off"
											placeholder="Mobile number"></form:input>

										<div class="form-check">
											<input class="form-check-input" type="radio"
												name="loginmethod" value="OTP" id="loginmethod1"> <label
												class="form-check-label" for="loginmethod1"> OTP </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="radio"
												name="loginmethod" id="loginmethod2" value="PWD" checked>
											<label class="form-check-label" for="loginmethod2">
												Password </label>
										</div>
									</div>
									<input type="button" name="next" class="next action-button"
										value="Proceed" id="nextbtn" />
								</fieldset>
								<fieldset>
									<div class="form-card">
										<h2 class="fs-title">Verification Details</h2>
										<input type="text" id="key" placeholder="Login Id" readonly="readonly" />
	<%-- 
										<form:input type="tel" style="padding-left: 5px;"
											pattern="[0-9]*" class="form-control form-control-sm mr-sm-3"
											path="otpVal" id="validationOTP" maxlength="6"
											autocomplete="off" placeholder="OTP"
											aria-describedby="validationOTP"></form:input>

										<form:input type="password" style="padding-left: 5px;"
											class="form-control form-control-sm" path="userpassword"
											id="validationPassword" maxlength="24" autocomplete="off"
											placeholder="Your password"></form:input> --%>
										<div id="logincred"></div>

									</div>
									<!-- <input type="button" name="previous" class="previous action-button-previous" value="Previous"/> -->
									<input type="button" name="resendotp"
										class="previous action-button-previous" value="Previous" /> <input
										type="button" name="login_account" class="login action-button"
										value="Confirm" />
									<form:hidden path="returnUrl" />
									<form:hidden path="otpSubmit" id="otpsubmitstat" />
								</fieldset>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <script src='https://www.google.com/recaptcha/api.js'></script> -->
	<%-- <script src="<c:url value="${contextcdn}/resources/js/login.js" />"
	type="text/javascript" defer="defer"></script>
	
	 --%>
</body>
</html>