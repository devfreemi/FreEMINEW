<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link
	href="<c:url value="${contextcdn}/resources/css/login.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
 <link href="<c:url value="${contextcdn}/resources/css/animate.css"/>" type="text/css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreEMI online login</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<meta name="keywords" content="freemi login, Sign up for free Online Mutual Fund account, Get best health insurance by signup, get Instant credit by signup" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description"
	content="Login to your FreEMI account and view your transactions, wishes completed" />

<!-- <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" /> -->
<meta http-equiv="pragma" content="no-cache" />
<link
	href="<c:url value="${contextcdn}/resources/css/login.component.css"/>"
	rel="stylesheet">
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<script src="<c:url value="${contextcdn}/resources/js/login.js" />"></script>
</head>
<body class="login_design" onload="formOnLoad();">
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container" style="min-height: 400px; padding-top: 30px;">

		<div class="freemi-logo">
			<img src="<c:url value="${contextcdn}/resources/images/f.png"/>"
				class="img-fluid" style="height: 50px;">
		</div>


		<!-- MAIN FORM -->

		<!-- login form -->
		<div class="login_header">Login to your Account</div>

		<div class="row">
			<div class="col-md-6 col-lg-6 form_div animated fadeInLeft"
				style="margin: auto; background: #d8d8d8e3;">

				<div>
					<form:errors path="usermobile" cssClass="error" />
				</div>
				<div>
					<form:errors path="userpassword" cssClass="error" />
				</div>
				
				<c:choose>
						<c:when test="${not empty success }">
							<div class="col-md-12 col-lg-12 alert alert-primary" 
								style="text-align: center;">
								<span> ${success}</span>
							</div>
						</c:when>
						<c:when test="${not empty error }">
							<div class="col-md-12 col-lg-12 alert alert-danger" 
								style="text-align: center;">
								<span>${error}</span>
							</div>
						</c:when>
						<c:otherwise>
							<span></span>
						</c:otherwise>
					</c:choose>
				<form:form method="POST" action="${pageContext.request.contextPath}/login.do"
					commandName="login">
					<%-- <div class="form-group"
						style="padding-left: 10px; padding-right: 10px;">
						<div class="row">
							<span class="span_style"> <i
								class="fas fa-mobile-alt icon_style"
								style="font-size: 30px; margin-top: 5px;"></i> <form:input
									type="text" class="form-control input-sm boxstyle"
									style="padding-left: 40px;"
									placeholder=" 10-digit mobile number" path="usermobile"
									onkeyup="validateForm();" pattern="[0-9]{10}" maxlength="10"
									autocomplete="off" />
							</span>
						</div>
					</div> --%>

					<div class="form-row">
					<div class="col-md-12 mb-1">
						<label for="validationCustomUsername">Mobile Number</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend" style="padding: 1px 10px;">
								<img src="<c:url value="${contextcdn}/resources/images/mobile.png"/>"
								class="img-fluid" style="height: 35px;">
								</span>
							</div>
							<form:input type="text" class="form-control"
								id="validationCustomUsername" placeholder="10-digit mobile number"
								aria-describedby="inputGroupPrepend" path="usermobile" 
								oninput="validateForm();" pattern="[0-9]{10}" maxlength="10"
									autocomplete="off"
								/>
							<!-- <div class="invalid-feedback">Please choose a username.</div> -->
						</div>
					</div>
					</div>


					<%-- <div class="form-group"
						style="padding-left: 10px; padding-right: 10px;">
						<div class="row">
							<span class="span_style"> <i class="fa fa-key icon_style"
								style="font-size: 27px; margin-top: 5px;"></i> <form:input
									type="password" class="form-control input-sm"
									style="padding-left: 40px;" path="userpassword"
									placeholder="Password" maxlength="24" autocomplete="off" />
							</span>
						</div>
					</div> --%>
					
					<div class="form-row">
					<div class="col-md-12 mb-1">
						<label for="validationPassword">Password</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend" style="padding: 1px 15px;">
								<img src="<c:url value="${contextcdn}/resources/images/pass.png"/>"
								class="img-fluid" style="height: 34px;">
								</span>
							</div>
							<form:input type="password" class="form-control"
								id="validationPassword" placeholder="Password"
								aria-describedby="inputGroupPrepend" path="userpassword"
								  maxlength="24" oninput="validateForm();"
									autocomplete="off"
								/>
							<!-- <div class="invalid-feedback">Please choose a username.</div> -->
						</div>
					</div>
					</div>

					<div class="row">
						<div class="col-md-12 col-lg-12">
						<a class="password_reset" href="${pageContext.request.contextPath}/forgotPassword">Forgot
							Password? </a>
						</div>
					</div>
					
					<!-- <div style="margin-bottom: 20px;">
						<div class="g-recaptcha" data-sitekey="6LdvUoQUAAAAADk77XVS_YlkPTluN9EYCawk1xo6"></div>
					</div> -->
					<div class="login_buttons">
						<button type="submit" id="loginsubmit"
							class="btn btn-sm btn-block login_button">
							<i class="fas fa-lock"></i>
							<!-- <i *ngIf="LoginRequestsubmitted" class="fa fa-spinner login_spin" aria-hidden="true"></i> -->
							Login
						</button>

						<a href="${pageContext.request.contextPath}/register" style="text-decoration: none;">
							<button type="button" class="btn btn-sm btn-block login_button"
								style="text-decoration: none;">
								<i class="fas fa-user-plus"></i> Sign Up
							</button>
						</a>
					</div>
				</form:form>
			</div>
			<!-- <div class="col-md-6 col-lg-6"></div> -->
		</div>

	</div>
	<script src='https://www.google.com/recaptcha/api.js'></script>
</body>
</html>