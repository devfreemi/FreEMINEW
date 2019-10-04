<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Force change Password</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<jsp:include page="include/bootstrap.jsp"></jsp:include>

</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<div class="container" style="margin-bottom: 5rem;">

		<div class="row">
			<div class="col-md-6 col-lg-6" style="margin: auto;">


				<!-- Material form login -->
				<div class="card" style="margin-top: 5rem;">

					<h5 class="card-header info-color white-text text-center py-4">
						<strong>Change Password</strong>
					</h5>

					<!--Card content-->
					<div class="card-body px-lg-5 pt-0">
						<strong style="color: #696b75;">Change your temporary password</strong>
						<!-- Form -->

						<form:form
							action="${pageContext.request.contextPath}/forceChangePassword.do"
							commandName="changePasswordForm"
							onsubmit="return validateForm(event)">

							<div class="md-form">
								<i class="fas fa-mobile-alt prefix"></i>
								<form:input path="userid" type="text" id="userid"
									class="form-control" />
								<label for="userid">login ID</label>
							</div>

							<div class="md-form">
								<i class="fas fa-key prefix"></i>
								<form:input path="temporaryPassword" type="password"
									id="temppass" class="form-control" />
								<label for="temppass">Temporary Password</label>
							</div>

							<div class="md-form">
								<i class="fas fa-lock prefix"></i>
								<form:input path="newpassword" type="password" id="newpassword"
									class="form-control" />
								<label for="newpassword">New Password</label>
							</div>

							<div class="md-form">
								<i class="fas fa-lock prefix"></i>
								<form:input path="confirmnewpassword" type="password"
									id="confirmnewpass" class="form-control" />
								<label for="confirmnewpass">New Password</label>
							</div>
							
							<div class="md-form" style="text-align: center;">
							<button type="submit" class="btn btn-sm white-text #e64a19 deep-orange darken-2">
							Change Password <i class="fas fa-angle-double-right"></i>
						</button>
						</div>

						</form:form>
					</div>

				</div>
				<!-- Material form login -->
			</div>

		</div>

	</div>

	<jsp:include page="include/sub-footer.jsp"></jsp:include>
	<jsp:include page="include/footer.jsp"></jsp:include>

</body>
</html>