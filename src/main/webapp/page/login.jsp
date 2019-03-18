<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="<c:url value="${contextcdn}/resources/css/login.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>"
	type="text/css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FreEMI online login</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<meta name="keywords"
	content="freemi login, Sign up for free Online Mutual Fund account, Get best health insurance by signup, get Instant credit by signup" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description"
	content="Login to your FreEMI account and view your transactions, wishes completed" />

<!-- <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" /> -->
<meta http-equiv="pragma" content="no-cache" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextcdn}/resources/css/login.component.css"/>"
	rel="stylesheet">

<script src="<c:url value="${contextcdn}/resources/js/login.js" />"></script>
<style>
.timer_style {
	text-align: center;
	margin-bottom: 20px;
	font-weight: 400;
	color: #e46a39;
	/* font-family: monospace; */
	font-size: 16px;
}
</style>
</head>
<body class="login_design" onload="formOnLoad();">
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container" style="min-height: 100vh; padding-top: 30px;">

		<div class="freemi-logo">
			<img src="<c:url value="${contextcdn}/resources/images/f.png"/>"
				class="img-fluid" style="height: 50px;">
		</div>

		<!-- login form -->
		<div class="login_header" id="ht">Login to your Account</div>

		<div class="row">
			<div class="col-md-6 col-lg-6 form_div animated fadeInLeft"
				style="margin: auto; background: #ffffffe3;">
					
					
					<div>
						<span id="infomsg" style="color: green; font-weight: 400; font-size: 13px;">${info }</span>
					</div>
					
					<div>
						<span id="loginmsg" style="color: #ff7623; font-weight: 400; font-size: 13px;"></span>
					</div>
				
				<div id="loginFormBox">
				<form:form method="POST" commandName="login"
					onsubmit="return submitLogin(event);">
					<%-- action="${pageContext.request.contextPath}/login.do" --%>

					<div class="md-form mb-1">
						<i class="fas fa-mobile-alt prefix" style="padding-left: 5px;"
							id="mobico"></i>
						<form:input type="text" style="padding-left: 5px;"
							id="validationCustomUsername"
							class="form-control form-control-sm" path="usermobile"
							onkeyup="validateForm();" pattern="[0-9]{10}" maxlength="10"
							autocomplete="off" placeholder="Mobile number"></form:input>
						<!-- <label for="validationCustomUsername">Mobile number</label> -->
					</div>
					<span id="msg1" style="font-size: 11px;"></span>


					<div class="md-form" id="passbox">
						<i class="fas fa-lock prefix" id="passico"></i>
						<form:input type="password" style="padding-left: 5px;"
							class="form-control form-control-sm" path="userpassword"
							id="validationPassword" maxlength="24" oninput="validateForm();"
							autocomplete="off" placeholder="Your password"></form:input>
						<!-- <label for="validationPassword">Your password</label> -->
					</div>

					<div class="md-form form-group mt-0 animated fadeIn " id="otpbox"
						style="display: none;">
						<img
							src="<c:url value="${contextcdn}/resources/images/otp-service.svg"/>"
							class="img-fluid prefix" style="height: 2rem;" alt="OTP">
						<form:input type="text" style="padding-left: 5px;" pattern="[0-9]*"
							class="form-control form-control-sm mr-sm-3" path="otpVal"
							id="validationOTP" maxlength="6" oninput="validateForm();"
							autocomplete="off" placeholder="OTP"
							aria-describedby="validationOTP"></form:input>
						<!-- <small id="validationPassword" class="text-muted">Resend </small> -->
						<!-- <label for="validationPassword">Your password</label> -->
						<div class="timer_style">
							<span id="timer"></span>
						</div>

					</div>

					<div class="form-group row" id="otpChoice">
						<label for="otplogin"
							class="col-2 col-md-1 col-lg-1 col-form-label"><i
							class="fas fa-sms" style="font-size: 24px;"></i></label>
						<div
							class="col-8 col-md-8 col-lg-8 custom-control custom-checkbox"
							style="margin-top: 7px; font-weight: 500; color: #ea6f25;">
							<form:checkbox path="otpLogin" class="custom-control-input"
								id="otplogin" onchange="validateForm();"></form:checkbox>
							<label class="custom-control-label" for="otplogin">Login
								by OTP</label>
						</div>
					</div>
					
					<form:hidden path="returnUrl"/>
					<form:hidden path="otpSubmit" id="otpsubmitstat"/>

					<%-- <div class="custom-control custom-checkbox">
						<!-- <i class="fas fa-sms prefix"></i> -->
						<form:checkbox path="otpLogin" class="custom-control-input"
							id="otplogin"></form:checkbox>
						<label class="custom-control-label" for="otplogin">Login
							by OTP</label>

					</div> --%>

					<div class="row">
						<div class="col-md-12 col-lg-12">
							<a class="password_reset"
								href="${pageContext.request.contextPath}/forgotPassword">Forgot
								Password? </a>
						</div>
					</div>

					<!-- <div style="margin-bottom: 20px;">
						<div class="g-recaptcha" data-sitekey="6LdvUoQUAAAAADk77XVS_YlkPTluN9EYCawk1xo6"></div>
					</div> -->
					<div class="login_buttons">
						<button type="submit" id="loginsubmit"
							class="btn btn-sm btn-block blue-gradient">
							<i class="fas fa-lock"></i> Login
						</button>

						<a href="${pageContext.request.contextPath}/register"
							style="text-decoration: none; margin-top: 10px">
							<button type="button"
								class="btn btn-sm btn-block purple-gradient"
								style="text-decoration: none;">
								<i class="fas fa-user-plus"></i> Sign Up
							</button>
						</a>
					</div>
				</form:form>
				</div>
				
				<div id="otpSubmitForm" style="display: none;">
				
				<form:form method="POST" action="#" commandName="otpForm"
					onsubmit="return submitOtp(event);">
					<%-- action="${pageContext.request.contextPath}/login.do" --%>

					<div class="md-form mb-1">
						<i class="fas fa-mobile-alt prefix" style="padding-left: 5px;"
							id="mobico"></i>
						<form:input type="text" style="padding-left: 5px;"
							id="validationCustomUsername2"
							class="form-control form-control-sm" path="usermobile"
							onkeyup="validateForm();" pattern="[0-9]{10}" maxlength="10"
							autocomplete="off" placeholder="Mobile number"></form:input>
						<!-- <label for="validationCustomUsername">Mobile number</label> -->
					</div>
					<span id="msg1" style="font-size: 11px;"></span>

					<div class="md-form form-group mt-0 animated fadeIn " id="otpbox">
						<img
							src="<c:url value="${contextcdn}/resources/images/otp-service.svg"/>"
							class="img-fluid prefix" style="height: 2rem;" alt="OTP">
						<form:input type="password" style="padding-left: 5px;"
							class="form-control form-control-sm mr-sm-3" path="otpVal"
							id="validationOTP" maxlength="6" oninput="validateForm();"
							autocomplete="off" placeholder="OTP"
							aria-describedby="validationOTP"></form:input>
						<!-- <small id="validationPassword" class="text-muted">Resend </small> -->
						<!-- <label for="validationPassword">Your password</label> -->
						<div class="timer_style">
							<span id="timer"></span>
						</div>

					</div>

					<form:hidden path="returnUrl"/>

					<div class="row">
						<div class="col-md-12 col-lg-12">
							<a class="password_reset"
								href="${pageContext.request.contextPath}/forgotPassword">Forgot
								Password? </a>
						</div>
					</div>

					<!-- <div style="margin-bottom: 20px;">
						<div class="g-recaptcha" data-sitekey="6LdvUoQUAAAAADk77XVS_YlkPTluN9EYCawk1xo6"></div>
					</div> -->
					<div class="login_buttons">
						<button type="submit" id="otpsubmit"
							class="btn btn-sm btn-block blue-gradient">
							<i class="fas fa-lock"></i> VERIFY OTP
						</button>

						<a href="${pageContext.request.contextPath}/register"
							style="text-decoration: none; margin-top: 10px">
							<button type="button"
								class="btn btn-sm btn-block purple-gradient"
								style="text-decoration: none;">
								<i class="fas fa-user-plus"></i> Sign Up
							</button>
						</a>
					</div>
				</form:form>
				</div>
				
				
			</div>
		</div>

	</div>
	<!-- <script src='https://www.google.com/recaptcha/api.js'></script> -->
</body>
</html>