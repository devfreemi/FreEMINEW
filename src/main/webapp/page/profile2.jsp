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
<title>My Profile</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.27/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.27/dist/sweetalert2.min.css
" rel="stylesheet">

	<style type="text/css">
		.prof-bank-details {
			margin-left: -15px;
			margin-right: -15px;
			border-bottom: 1px solid #e4e2e2;
			padding-left: 15px;
			padding-right: 15px;
		}
	
		.side label {
			color: #f09c23;
		}
	</style>
</head>
<body class="back_set" onload="formOnLoad();">

<jsp:include page="include/header.jsp"></jsp:include>

<div class="container pt-3 mt-md-2">
	<div class="main-body">

		<div class="row gutters-sm">
			<div class="col-md-4 mb-3">
				<div class="card border-0 shadow">
					<div class="card-body">
						<div class="d-flex flex-column align-items-center text-center">
							<img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="User"
								class="rounded-circle" width="150">
							<div class="mt-3">
								<h4>Hi! ${profilefreemi.fname }
								${profilefreemi.lname } </h4>
								<!-- <p class="text-secondary mb-1">Full Stack Developer</p> -->
								<p class="text-secondary font-size-sm">Email Id: <span
										class="font-weight-bold">${profilefreemi.mail }</span></p>
								<a href="${pageContext.request.contextPath}/my-dashboard"
									class="btn btn-outline-success font-weight-bold">Dashboard</a>
								<!-- <button class="btn btn-outline-primary">Message</button> -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="card mb-3  border-0 shadow">
					<div class="card-body">
						<div class="row">
							<div class="col-sm-3">
								<h6 class="mb-0 fw-bold">Full Name</h6>
							</div>
							<div class="col-sm-9 text-default">
								${profilefreemi.fname }
								${profilefreemi.lname } </div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<h6 class="mb-0 fw-bold">Email</h6>
							</div>
							<div class="col-sm-9 text-default">
								${profilefreemi.mail } </div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<h6 class="mb-0 fw-bold">Mobile</h6>
							</div>
							<div class="col-sm-9 text-default">
								${profileBasic.mobile } </div>
						</div>
						<hr>
						<div class="row">
							<div class="col-sm-3">
								<h6 class="mb-0 fw-bold">Address</h6>
							</div>
							<div class="col-sm-9 text-default">
								${profileBasic.address1 }</div>
						</div>
						<hr>
						<div class="row mx-auto">
							<div class="col-sm-12 mx-auto text-center text-md-left pb-md-3 pb-0">
								<a class="btn btn-chng-pass rounded waves-effect waves-light" data-toggle="modal"
									data-target="#profile">Change Password &nbsp; <i class="fas fa-edit"></i></a>
							</div>
						</div>
						<div class="modal fade pt-5 mt-4 px-0" id="profile" tabindex="-1" role="dialog"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title text-dark font-weight-bold" id="exampleModalLabel">Change
											Password</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<!-- <div class="row"> -->
										<form:form commandName="profilePasswordChangeForm" method="POST"
											onsubmit="return changepass(event);">
											<div class="col-sm-12 mx-auto text-center mx-auto">
												<span id="passmsg" style="color: red;"></span>
											</div>
											<div class="row mb-3">
												<div class="col-sm-6">
													<p class="mb-0 text-dash">Old Password</p>
												</div>
												<div class="col-sm-6 text-secondary">
													<form:input type="password" class="form-control rounded"
														placeholder="Old Password" id="oldpass" path="oldPassword"
														maxlength="24" />
													<!-- <input type="email" name="email" required class="form-control rounded" placeholder="john@example.com"> -->
												</div>
											</div>

											<div class="row mb-3">
												<div class="col-sm-6">
													<p class="mb-0 text-dash">New Password</p>
												</div>
												<div class="col-sm-6 text-secondary">
													<form:input type="password" class="form-control rounded"
														placeholder="New Password" id="newpass" path="newPassword"
														maxlength="24" />
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-6">
													<p class="mb-0 text-dash">Confirm New Password</p>
												</div>
												<div class="col-sm-6 text-secondary">
													<form:input type="password" class="form-control rounded"
														placeholder="Confirm New Password" id="newpassconf"
														path="confirmNewPassword" maxlength="24" />
												</div>
											</div>
											<form:hidden path="mobile" />
											<div class="row justify-content-center">
												<div class="col-sm-12 text-center text-md-left">
													<button type="submit" class="btn btn-sm btn-chng-pass"
														id="resetbutton">
														<span id="passchangebasic" style="display: block;"> Reset
															Password <i class="fas fa-lock"></i></span><span
															id="passchangespin" style="display: none;">Please wait <i
																class="fas fa-spinner fa-spin"></i></span>
													</button>
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</div>
							<!-- Model -->
						</div>
					</div>





				</div>
			</div>
			<jsp:include page="include/profile-mf-account.jsp"></jsp:include>
			<jsp:include page="include/profile-mahindra-fd-account.jsp"></jsp:include>
		</div>
	</div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>

<script src="<c:url value="${contextPath}/resources/js/profile.js" />" async="true"></script>
</html>