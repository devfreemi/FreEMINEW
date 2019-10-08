<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forgot Password</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<!-- <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" /> -->
<meta http-equiv="pragma" content="no-cache" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container" style="">

		<div class="freemi-logo">
			<img src="<c:url value="${contextcdn}/resources/images/f.png"/>"
				class="img-fluid" style="height: 50px;">
		</div>


		<!-- MAIN FORM -->
		<div class="topic_forgot">
			<span> Forgot password?</span>
			<div style="font-size: 18px;">You can reset your password here
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-lg-6 form_div" style="margin: auto;">
				<div class="row">
					<div class="col-12">

						<c:choose>
							<c:when test="${not empty success }">
								<div class="alert alert-primary" role="alert">
									<span>${success}</span>
								</div>
							</c:when>
							<c:when test="${not empty error }">
								<div class="alert alert-danger" role="alert" >
									<span>${error}</span>
								</div>
							</c:when>
							<c:otherwise>
								<span></span>
							</c:otherwise>
						</c:choose>
					</div>

					<!--  -->

				</div>
				<div style="padding: 5px; font-size: 12px; color: red;">
					<span id="jsmsg"></span>
					<form:form method="POST"
						action="${pageContext.request.contextPath}/forgotPassword.do"
						commandName="forgotPasswordForm"
						onsubmit="return validateForm(event)">

						<div class="md-form">
							<i class="fas fa-mobile-alt prefix"></i>
							<form:input path="usermobile" type="text" id="form1"
								pattern="[0-9]{10}" maxlength="10"
								class="form-control form-control-sm"
								placeholder="10 digit mobile number"></form:input>
						</div>
						<div style="margin-bottom: 20px;">
							<div class="g-recaptcha"
								data-sitekey="6LdvUoQUAAAAADk77XVS_YlkPTluN9EYCawk1xo6"></div>
						</div>

						<button type="submit" class="btn btn-block btn-sm peach-gradient">
							<!-- <i *ngIf="spinner" class="fa fa-spinner login_spin" aria-hidden="true"></i> -->
							Reset Password
						</button>
						<a href="${pageContext.request.contextPath}/login"
							style="text-decoration: none; margin-top: 10px;"> <form:button
								class="btn btn-block btn-sm purple-gradient white-text">
								<span> <i class="fas fa-backward"></i> Back
								</span>
							</form:button>
						</a>
					</form:form>
				</div>
				<!-- <div class="col-md-6 col-lg-6"></div> -->
			</div>

		</div>
	</div>
	<jsp:include page="include/footer.jsp"></jsp:include>

	<script type="text/javascript">
		function validateForm(e) {
			//e.preventDefault();
			$("#jsmsg").text("");
			var response = grecaptcha.getResponse();
			//recaptcha failed validation
			var data = document.forms["forgotPasswordForm"]["usermobile"].value;
			if (data.length != 10) {
				$("#jsmsg").text("Valid mobile no required to be submitted");
				return false;
			}

			if (response.length == 0) {
				$("#jsmsg").text("Check the Google secutiry box!");
				return false;
			}

			return true;
		}
	</script>
	<script src='https://www.google.com/recaptcha/api.js'></script>
</body>
</html>