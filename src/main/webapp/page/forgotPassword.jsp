<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forgot Password</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<!-- <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" /> -->
<meta http-equiv="pragma" content="no-cache" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container mt-5 mb-5" style="">

		<!-- MAIN FORM -->
		<div class="row mx-auto">
			<div class="col-md-6 col-lg-6 mx-auto text-center mb-5 mt-3">
				<h1 class="font-weight-bold" style="font-size: 2rem; font-weight: 500;">Forgot
					password</h1>
				<h6>You can reset your password here</h6>
			</div>
		</div>

		<div class="row mx-auto">
			<div class="col-md-6 col-lg-6 mx-auto">
				<div class="row mb-2">
					<div class="col-12">
						<c:choose>
							<c:when test="${not empty success }">
								<div class="alert-primary" role="alert">
									<span>${success}</span>
								</div>
							</c:when>
							<c:when test="${not empty error }">
								<div class="alert-danger" role="alert">
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
				<div>
					<span class="text-danger" id="jsmsg"></span>
					<form:form method="POST"
						action="${pageContext.request.contextPath}/forgotPassword"
						commandName="forgotPasswordForm"
						onsubmit="return validateForm(event)">

						<div class="form-row">
							<div class="col-md-12 col-lg-12">
								<div class="form-group">
									<label for="form1" class="font-weight-bold">Mobile no.
										<sup class="text-danger">*</sup>
									</label>
									<form:input type="tel" class="form-control" id="form1"
										pattern="[0-9]{10}" style="text-transform: uppercase;"
										maxlength="10" aria-describedby="forgothelp"
										required="required" path="usermobile" />
									<small id="forgothelp" class="form-text text-muted">Enter
										registered mobile no for verification</small>
								</div>
							</div>
						</div>

						<div style="margin-bottom: 20px;">
							<div class="g-recaptcha"
								data-sitekey="6LdvUoQUAAAAADk77XVS_YlkPTluN9EYCawk1xo6"></div>
						</div>

						<button type="submit" class="btn btn-block btn-primary mt-3">
							<!-- <i *ngIf="spinner" class="fa fa-spinner login_spin" aria-hidden="true"></i> -->
							Reset Password
						</button>

						<div class="row mb-6 mt-3">
							<div class="col-md-12 text-center text-muted" style="font-size: 16px;">
								Back to <a href="${pageContext.request.contextPath}/login">
									Login </a>
							</div>
						</div>

					</form:form>
				</div>
			</div>

		</div>
	</div>
	<jsp:include page="include/sub-footer.jsp"></jsp:include>
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