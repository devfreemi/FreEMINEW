<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<meta http-equiv="pragma" content="no-cache" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container" style="">

		<div class="row" style="margin-bottom: 5rem; margin-top: 5rem;">
				<div class="col-md-6 col-lg-6"
					style="margin: auto; padding: 30px; border: 1px solid #7b7676;">

					<h3 style="text-align: center; color: #486cd4; font-weight: 500;">Reset
						your Password</h3>
					<hr
						style="border: 0; height: 1px; background-image: -webkit-linear-gradient(left, #f0f0f0, #f97d4d, #f0f0f0); background-image: -moz-linear-gradient(left, #f0f0f0, #f97d4d, #f0f0f0); background-image: -ms-linear-gradient(left, #f0f0f0, #f97d4d, #f0f0f0); background-image: -o-linear-gradient(left, #f0f0f0, #f97d4d, #f0f0f0);">
					<!-- <a routerLink="Home">Go back to Home</a> -->
					<span>You have requested to reset password for user ID: <strong>
							${userid} </strong>
					</span>
					<!-- <p>Token- {{token}}</p> -->
					<!-- <reset-password-form></reset-password-form> -->

					<div>
						<c:if test="${STATUS == 'N' }">
							<div class="alert alert-danger" role="alert">${error}</div>
						</c:if>
						<c:if test="${STATUS == 'Y' }">
							<div class="alert alert-success" role="alert"
								style="padding: 5px; font-size: 12px;">${success}</div>
						</c:if>

						<c:if test="${PWDCHANGE == 'N' }">
							<div class="reset-form-box">
								<form:form
									action="${pageContext.request.contextPath}/resetPassword.do"
									method="POST" commandName="resetPassword">
									
									<div class="md-form">
										<i class="fas fa-unlock-alt prefix" style="color: #778282;"></i>
										<form:input type="password" id="box1" class="form-control form-control-sm"
											path="newpassword" placeholder="New Password" maxlength="24" />
									</div>
									
									<div class="md-form">
										<i class="fas fa-unlock-alt prefix" style="color: #778282;"></i>
										<form:input type="password" id="box2"
												class="form-control form-control-sm" path="confirmpassword"
												placeholder="Confirm New Password" maxlength="24" />
									</div>
									
									<div style="margin-bottom: 20px;">
										<div class="g-recaptcha"
											data-sitekey="6LdvUoQUAAAAADk77XVS_YlkPTluN9EYCawk1xo6"></div>
									</div>
									<button type="submit"
										class="btn btn-sm btn-block blue-gradient white-text">
										<span> Reset Password</span>
									</button>
								</form:form>
							</div>
						</c:if>

						<c:if test="${PWDCHANGE == 'S' }">
							<span>Click <a href="/products/login">here</a> to login to
								your account
							</span>
						</c:if>
					</div>

				</div>
			</div>
	</div>
	<jsp:include page="include/footer.jsp"></jsp:include>
	<script src='https://www.google.com/recaptcha/api.js'></script>
</body>
</html>