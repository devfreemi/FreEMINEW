<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="keywords"
	content="freemi signup, freemi register, investment portal" />
<link rel="canonical" href=" https://www.freemi.in/products/register" />

<meta name="title" content="Sign up" />
<meta name="description" content="Get registered to FreEMI and invest to fill your goals. Apply for personal loans, business loan, credit cards, health insurance, motor insurance, etc. Purchase mutual funds, invest through SIP." />
<meta name="robots" content="follow,index" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextcdn}/resources/css/register.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>"
	type="text/css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New to FreEMI? Get registered today.</title>
<style type="text/css">
@media screen and (max-width: 320px) {
	.custombtnclass {
		padding: .5rem 1.6rem;
		font-size: .84rem;
	}
}
</style>

</head>
<body class="login_design" onload="formOnLoad();">
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container" style="min-height: 400px; padding-top: 30px;">

		<div class="login_header">
			<h1 style="font-size: 2rem;">New to FreEMI?</h1>
			<div style="font-size: 20px;">
				Create your <span style="color: rgb(255, 252, 94)"> Free </span>
				account today
			</div>
		</div>

		<!-- Registration form -->
		<div class="row p-1">
			<div class="col-md-6 col-lg-6 form_div animated fadeInRight"
				style="margin: auto; background: #ffffffe3;">
				<div>
					<form:errors path="*" cssClass="error" element="div" />
				</div>
				<div class="row">
					<c:choose>

						<c:when test="${not empty success }">
							<div class="col-md-12 col-lg-12">
								<div class="alert alert-primary" role="alert">
									<span> ${success}</span>
								</div>
							</div>
						</c:when>
						<c:when test="${not empty error }">
							<div class="col-md-12 col-lg-12">
								<div class="alert alert-danger" role="alert">
									<span>${error}</span>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<span></span>
						</c:otherwise>
					</c:choose>
					<!--  -->

				</div>
				<form:form method="POST"
					action="${pageContext.request.contextPath}/register.do"
					commandName="registerForm">
					<div class="row">

						<div class="col">
							<!-- Material input -->
							<div class="md-form mt-0">
								<i class="fas fa-user prefix" id="userico"></i>
								<form:input type="text" style="padding-left: 5px;" id="fnameid"
									class="form-control form-control-sm" path="fname"
									aria-label="First name" pattern="[a-zA-Z0-9 .]*" maxlength="64"
									autocomplete="off" placeholder="First Name"></form:input>
							</div>
						</div>

						<div class="col">
							<div class="md-form mt-0">
								<form:input type="text" style="padding-left: 5px;" id="lnameid"
									class="form-control form-control-sm" path="lname"
									aria-label="Last name" pattern="[a-zA-Z0-9 .]*" maxlength="64"
									autocomplete="off" placeholder="Last Name"></form:input>
							</div>
						</div>
					</div>



					<div class="md-form mt-1">
						<i class="fas fa-mobile-alt prefix" id="mobico"></i>
						<form:input type="text" style="padding-left: 5px;"
							id="registermobile" class="form-control form-control-sm"
							path="mobile" pattern="[0-9]*" maxlength="10" autocomplete="off"
							placeholder="10-digit mobile number"></form:input>
					</div>


					<div class="md-form">
						<i class="fas fa-envelope prefix" id="mailico"></i>
						<form:input type="text" style="padding-left: 5px;" id="useremail"
							class="form-control form-control-sm" path="email" maxlength="128"
							autocomplete="off" placeholder="Valid Email ID"></form:input>
					</div>

					<div class="md-form">
						<i class="fas fa-lock prefix" id="passico"></i>
						<form:input type="password" style="padding-left: 5px;"
							id="registerpassword" class="form-control form-control-sm"
							path="password" minlength="8" maxlength="24" autocomplete="off"
							placeholder="Password for login"></form:input>
						<small id="msg2"
							style="font-size: 10px; padding-left: 2.5rem; color: #f33c3c;"></small>
					</div>

					<div class="text-center mb-2">
						<button type="submit" id="registerSubmit"
							class="btn blue-gradient white-text custombtnclass">
							<span>Register</span>
						</button>
					</div>
					<div class="text-center">
					<a href="${pageContext.request.contextPath}/login"
							style="text-decoration: none; margin-top: 10px;">
							 Already registered? Login 
						</a>
					</div>
					
				</form:form>

			</div>

		</div>
		<div class="row">
			<div class="col-md-6 col-lg-6"
				style="margin: auto; padding-top: 10px; font-size: 14px; color: aliceblue;">
				<span> By clicking "Register", I agree to FreEMI <a
					href="https://www.freemi.in/terms-conditions"
					style="color: rgb(253, 244, 115)">Terms of Service</a>.
				</span>
			</div>
		</div>

	</div>
</body>
<script src="<c:url value="${contextcdn}/resources/js/register.js" />"></script>
</html>