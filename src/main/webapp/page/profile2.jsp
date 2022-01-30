<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link
	href="<c:url value="${contextPath}/resources/css/user-profile.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>

<style type="text/css">
.borderstyle-1 {
	border: 1px solid #eceae6;
}

.leftside .row {
	margin: 0px;
}

.leftside label {
	color: #254f82;
}

.rightside .row {
	margin: 0px;
}

.rightside label {
	color: #254f82;
}

.prof-bank-details {
	margin-left: -15px;
	margin-right: -15px;
	border-bottom: 1px solid #e4e2e2;
	padding-left: 15px;
	padding-right: 15px;
}
</style>
</head>
<body class="back_set" onload="formOnLoad();"
	style="background: #f7f7f7;">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container"
		style="background-color: rgba(255, 255, 255, 1); padding-bottom: 30px; padding-left: 20px; font-size: 13px; padding-top: 1rem; margin-bottom: 3rem; min-height: 100vh;">


		<section class="basc_details" style="margin-top: 2rem;">
			<div class="row" style="margin: auto;">
				<div class="col-md-6 col-lg-6"
					style="padding: 10px; margin-bottom: 1.5rem;">
					<div class="leftside" style="border: 1px solid #e4e4e4;">
						<div style="text-align: center;">
							<img
								src="<c:url value="${contextPath}/resources/images/invest/profile-2.png"/>"
								class="img-fluid rounded-circle border"
								style="height: 3rem; margin-top: -25px; box-shadow: 0 0 2px 1px #cac0c0;"
								alt="Edit">
						</div>
						<h4
							style="font-weight: 500; text-align: center; margin: 1rem 5px;">Account
							Details</h4>

						<c:if test="${not empty error }">
							<div class="col-md-12 col-lg-12 alert alert-danger" role="alert"
								style="text-align: center;">
								<span>${error}</span>
							</div>
						</c:if>
						<div class="row">
							<div class="col-6 borderstyle-1">
								<label>Name</label>
							</div>
							<div class="col-6 borderstyle-1 text-muted" style="overflow: auto;">${profilefreemi.fname } ${profilefreemi.lname }</div>
						</div>
						<div class="row">
							<div class="col-6 borderstyle-1">
								<label>Email</label>
							</div>
							<div class="col-6 borderstyle-1 text-muted" style="overflow: auto;">${profilefreemi.mail }</div>
						</div>
					</div>

				</div>

				<div class="col-md-6 col-lg-6"
					style="padding: 10px; margin-bottom: 2rem;">

					<div id="changebutton">
						<span>
							<button type="button"
								class="btn btn-sm btn-block red lighten-1 white-text"
								onclick="showpassCHangeForm();">Change Password</button>
						</span>
					</div>
					<div id="passwordchangeform" class="animated fadeIn"
						style="padding: 1rem; border: 1px solid #c2c5c8;">

						<div style="text-align: center;">
							<img
								src="<c:url value="${contextPath}/resources/images/invest/pass-change-2.png"/>"
								class="img-fluid rounded-circle border"
								style="height: 3rem; margin-top: -55px; box-shadow: 0 0 2px 1px #cac0c0;"
								alt="Edit">
						</div>

						<h4>Change Password</h4>

						<div>
							<span id="passmsg" style="color: red;"></span>
						</div>
						<form:form commandName="profilePasswordChangeForm" method="POST"
							onsubmit="return changepass(event);">
							<%-- action="${pageContext.request.contextPath}/changePassword.do" --%>
							<div class="md-form">
								<form:input type="password" class="form-control form-control-sm"
									placeholder="Old Password" id="oldpass" path="oldPassword"
									maxlength="24" />
							</div>
							<div class="md-form">
								<form:input type="password" class="form-control form-control-sm"
									placeholder="New Password" id="newpass" path="newPassword"
									maxlength="24" />
							</div>
							<div class="md-form">
								<form:input type="password" class="form-control form-control-sm"
									placeholder="Confirm New Password" path="confirmNewPassword"
									id="newpassconf" maxlength="24" />
							</div>
							<form:hidden path="mobile" />

							<button type="submit" class="btn btn-sm teal darken-3 white-text"
								id="resetbutton">
								<span id="passchangebasic" style="display: block;"><i
									class="fas fa-lock"></i> Reset Password</span><span
									id="passchangespin" style="display: none;">Please wait <i
									class="fas fa-spinner fa-spin"></i></span>
							</button>
							<button type="button"
								class="btn btn-sm stylish-color-dark white-text"
								onclick="hidepassChangeForm();">
								<span>Cancel</span>
							</button>
						</form:form>
					</div>

				</div>

			</div>

		</section>

		<hr style="border: 0; height: 1px; background-image: -webkit-linear-gradient(left, #f0f0f0, #f97d4d, #f0f0f0);">

		<jsp:include page="include/profile-mf-account.jsp"></jsp:include>
		
		<hr style="border: 0; height: 1px; background-image: -webkit-linear-gradient(left, #f0f0f0, #f97d4d, #f0f0f0);">
		
		<jsp:include page="include/profile-mahindra-fd-account.jsp"></jsp:include>
		
	</div>

	<jsp:include page="include/footer.jsp"></jsp:include>
</body>

<script src="<c:url value="${contextPath}/resources/js/profile.js" />" async="true"></script>
</html>