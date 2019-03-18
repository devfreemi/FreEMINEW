<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset password</title>
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<div style="height: 100vh;">
		<jsp:include page="include/header.jsp"></jsp:include>
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-6" style="margin: auto; padding: 30px;">

					<h3 style="text-align: center">Reset your Password</h3>
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
							<div class="alert alert-success" role="alert">${success}</div>
						</c:if>

						<c:if test="${PWDCHANGE == 'N' }">
							<div class="reset-form-box">
								<form:form
									action="${pageContext.request.contextPath}/resetPassword.do"
									method="POST" commandName="resetPassword">
									<div class="form-group">
										<span class="span_style"> <form:input type="password"
												class="form-control input-sm " path="newpassword"
												placeholder="New Password" maxlength="24" />
										</span>
									</div>
									<div class="form-group">
										<span class="span_style"> <form:input type="password"
												class="form-control input-sm " path="confirmpassword"
												placeholder="Confirm New Password" maxlength="24" /> <!-- <i *ngIf="newpassword == confirmpassword" class="fas fa-check"></i> -->
										</span>
									</div>
									<div style="margin-bottom: 20px;">
										<div class="g-recaptcha"
											data-sitekey="6LdvUoQUAAAAADk77XVS_YlkPTluN9EYCawk1xo6"></div>
									</div>
									<button type="submit"
										class="btn btn-sm btn-block btn-info button_block">
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
	
	</div>
	<jsp:include page="include/footer.jsp"></jsp:include>
	<script src='https://www.google.com/recaptcha/api.js'></script>
</body>
</html>