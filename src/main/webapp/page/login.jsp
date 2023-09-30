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
<%-- <link href="<c:url value="${contextcdn}/resources/css/login23.css"/>"
	type="text/css" rel="stylesheet"> --%>

<link href="<c:url value=" ../products/resources/css/new-login.css" />"
	type="text/css" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>"
	type="text/css" rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/login23mod.js" />"
	type="text/javascript" defer="defer"></script>
<script>
	$(document).ready(function () {
		$(".mobile").hide();
		$("#loginPWD").hide();
		$("#sendOTPbtn").hide();
		$(".phone-btn").click(function () {
			$(".mobile").show();
			$(".google").hide();
			$(".demo").hide();
			$(".phone-btn").hide();
		});
		$(".otp").hide();
		$("#loginOTP").click(function () {
			$(".otp").show();
			$(".pwd").hide();
			$("#loginPWD").show();
			$("#loginOTP").hide();
			$("#sendOTPbtn").show();
			$("#nextbtn").hide();
			$("#loginmethod1").prop("checked", true);
		});
		$("#loginPWD").click(function () {
			$(".pwd").show();
			$(".otp").hide();
			$("#loginOTP").show();
			$("#loginPWD").hide();
			$("#sendOTPbtn").hide();
			$("#nextbtn").show();
			$("#loginmethod2").prop("checked", true);
		});
	});
</script>
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
	<div class="d-none d-md-none"><jsp:include page="include/header.jsp"></jsp:include></div>

<div class="container-login mb-3 mt-5 pt-5 mx-auto">
	<div class="header">
		<div class="header-image">
			<div class="header-image-particle header-image-particle-1"></div>
			<div class="header-image-particle header-image-particle-2"></div>
			<div class="header-image-particle header-image-particle-3"></div>

			<img src="<c:url value="../products/resources/images/f.png" />" alt="FreEMI" width="65" height="65">
		</div>

		<h1 class="header-title text-center">
			Welcome to <span class="brand-color">FreEMI!</span>
		</h1>

		<p class="text-sub text-center">
			Live Life Digital
		</p>
	</div>



		<!--<div class="google">
			<div class="p-2 rounded col-12 mx-auto text-center font-weight-bold google-btn shadow-sm" style="pointer-events: none;">
				<a href="#" class="google-button-a">
					<img src="../products/resources/images/google.png" alt="google"> &nbsp; CONTINUE WITH GOOGLE
				</a>

			</div>
		</div> -->

		<!-- <div class="demo">
			<div class="or or--x" aria-role="presentation">Or</div>
		</div> -->

		<div class="phone mt-5">
			<div class="p-2 rounded col-12 mx-auto text-center font-weight-bold phone-btn">
				<img src="../products/resources/images/phone.png" alt="phone"> &nbsp;
				CONTINUE WITH MOBILE NUMBER
			</div>
		</div>
		<form:form id="msform" modelAttribute="login">
		<div class="mobile">

			<div class="pb-2">
				<div class="text-center">
					<span id="infomsg" style="color: green; font-weight: 400; font-size: 13px;">${info }</span>
				</div>
			
				<div class="text-center">
					<span id="loginmsg" class="font-weight-bold text-danger" style="font-size: 13px;"></span>
				</div>
			</div>
					
			<div class="form-floating mb-3">
				<form:input type="text" id="validationCustomUsername" class="form-control log-mod"
					path="usermobile" name="usermobile" pattern="[0-9]{10}" maxlength="10" autocomplete="off" placeholder="Mobile Number"></form:input>
			<label for="floatingInput" class="log-lable">Mobile Number</label>
			</div>
			<div class="form-floating mb-3 otp">
				<form:input type="tel" id="otpbox" class="form-control log-mod" aria-describedby="validationOTP"
					path="otpVal" name="otpVal" pattern="[0-9]*" maxlength="6" autocomplete="off" placeholder="Valid OTP"></form:input>
				<label for="floatingInput" class="log-lable">Valid OTP</label>
			</div>
			<div class="form-floating mb-3 pwd">
				<form:input type="password" id="pwdbox" class="form-control log-mod" path="userpassword" pattern="[0-9]{10}"
					maxlength="24" name="userpassword" autocomplete="off" placeholder="Password"></form:input>
				<label for="floatingInput" class="log-lable">Password</label>
			</div>
			<%-- Add By Subhajit --%>
			<div class="check-valu d-none">
			<div class="form-check">
				<input class="form-check-input" type="radio" name="loginmethod" value="OTP" id="loginmethod1"> <label
					class="form-check-label" for="loginmethod1"> OTP </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="loginmethod" id="loginmethod2" value="PWD" checked>
				<label class="form-check-label" for="loginmethod2">
					Password </label>
			</div>
			</div>
				<form:hidden path="returnUrl" />
				<form:hidden path="otpSubmit" id="otpsubmitstat" />
				<input id="nextbtn" type="button" name="login_account" class="login button app-login-button" value="Confirm" />
				<input id="sendOTPbtn" type="button" class="sendOTP button app-login-button" value="Send OTP" />
				<div class="text-center">
					<p class="text text-center" id="timeCount">Resend OTP after <span class="text text-danger font-weight-bold" id="timeShow">00:00</span></p>
				</div>
		</div>
	</form:form>

	<div class="mobile">
		<div class="text-center">
			<p class="text text-center" id="loginOTP">Login with <span class="text link otp-class">OTP</span> </p>
			<p class="text text-center" id="loginPWD">Login with <span class="text link otp-class">Password</span> </p>
		</div>
		<!--<div class="text-center">
			<a href="#" class="text link">Forgot password?</a>
		</div>-->

		<div class="footer my-3">
			<p class="text text-center">
				Don't have an account?
				<a href="/products/register" class="text link">Register!</a>
			</p>
		</div>
	</div>
</div>
	<!-- <script src='https://www.google.com/recaptcha/api.js'></script> -->
	<%-- <script src="<c:url value="${contextcdn}/resources/js/login.js" />"
	type="text/javascript" defer="defer"></script>
	
	 --%>
	 
</body>
</html>