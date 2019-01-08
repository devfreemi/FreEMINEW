<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<script src="<c:url value="${contextPath}/resources/js/profile.js" />"></script>
</head>
<body class="back_set" onload="formOnLoad();">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container"
		style="background-color: rgba(255, 255, 255, 1); padding-bottom: 30px; padding-left: 20px; padding-top: 10px;">

		<div class="row" style="margin: auto;">
			<div class="col-md-12 col-lg-12">
				<c:choose>
					<c:when test="${not empty success }">
						<div class="col-md-12 col-lg-12 alert alert-success" role="alert"
							style="text-align: center;">
							<span> ${success}</span>
						</div>
					</c:when>
					<c:when test="${not empty error }">
						<div class="col-md-12 col-lg-12 alert alert-danger" role="alert"
							style="text-align: center;">
							<span>${error}</span>
						</div>
					</c:when>
					<c:otherwise>
						<span></span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="row" style="margin: auto;">
			<div class="col-md-12 col-lg-12">
				<div id="accordion">
					<div class="card">
						<div class="card-header" id="headingOne">
							<h5 class="mb-0">
								<button class="btn btn-link" data-toggle="collapse"
									data-target="#collapseOne" aria-expanded="true"
									aria-controls="collapseOne">
									<span class="profile"> <i class="fas fa-user"
										style="font-size: 22px; margin-right: 10px;"></i> BASIC
										PROFILE DETAILS
									</span>
								</button>
							</h5>
						</div>

						<div id="collapseOne" class="collapse show"
							aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body">

								<div class="row">
									<div class="col-md-6 col-lg-6">
										<!-- Basic user details form -->
										<form:form commandName="profileBasic"
											action="${pageContext.request.contextPath}/profileBasic.do"
											method="POST">
											<!-- <div>Data - {{profileData | json }}</div> -->
											<!-- <div>Data - {{profileData.fname }}</div> -->
											<form:hidden path="uid" />
											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>AADHAAR number</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<div class="input-group input-group-sm mb-3"
															style="margin-bottom: 0;">
															<form:input type="text"
																class="form-control form-control-sm" path="aadhaar" />
															<div class="input-group-append">
																<span class="input-group-text" id="basic-addon2"
																	style="padding: 1px 7px;"> <img
																	src="<c:url value="${contextPath}/resources/images/aadhaar_icon.png"/>"
																	class="img-fluid" style="height: 30px;" alt="A">
																</span>
															</div>
														</div>
														<!-- <span style="font-size: 12px;">*Click the icon to sync data</span> -->
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>PAN number</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<div class="input-group input-group-sm mb-3"
															style="margin-bottom: 0;">
															<form:input type="text"
																class="form-control form-control-sm" path="pan"
																readonly="true" />
															<div class="input-group-append">
																<span class="input-group-text" id="basic-addon2">
																	<i class="far fa-id-card" aria-hidden="true"
																	style="color: rgb(77, 152, 238); font-size: 24px;"></i>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>Your name</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<div class="input-group input-group-sm mb-3"
															style="margin-bottom: 0;">
															<form:input type="text"
																class="form-control form-control-sm" path="fname"
																readonly="true" />
															<div class="input-group-append">
																<span class="input-group-text" id="basic-addon2">
																	<i class="fa fa-user" aria-hidden="true"></i>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>Mobile Number</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<div class="input-group input-group-sm mb-3">
															<div class="input-group-prepend">
																<span class="input-group-text" id="basic-addon1">
																	<b>+91</b>
																</span>
															</div>
															<form:input type="text"
																class="form-control form-control-sm" path="mobile"
																aria-label="Username" aria-describedby="basic-addon1"
																readonly="true" />
														</div>
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>Your Gender</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<div
															class="custom-control custom-radio custom-control-inline">
															<form:radiobutton path="gender" value="M"
																id="customRadioInline3" name="customRadioInline3"
																class="custom-control-input" />
															<label class="custom-control-label"
																for="customRadioInline3">Male</label>
														</div>
														<div
															class="custom-control custom-radio custom-control-inline">
															<form:radiobutton path="gender" value="F"
																id="customRadioInline4" name="customRadioInline4"
																class="custom-control-input" />
															<label class="custom-control-label"
																for="customRadioInline4">Female</label>
														</div>
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>Email Address</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<div class="input-group input-group-sm mb-3">
															<div class="input-group-prepend">
																<span class="input-group-text" id="basic-addon1">@</span>
															</div>
															<form:input type="text"
																class="form-control form-control-sm" path="mail" />
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4"></div>
													<div class="col-md-8 col-lg-8">
														<button type="submit" class="btn btn-sm btn-primary">Save
															Details</button>
														<img
															src="<c:url value="${contextPath}/resources/images/edit.png"/>"
															class="img-fluid" style="height: 30px; float: right;"
															onclick="editBasicDetails=!editBasicDetails" alt="Edit">
													</div>
												</div>
											</div>

										</form:form>

									</div>

									<div class="col-md-6 col-lg-6">
										<div id="changebutton">
											<span>
												<button type="button" class="btn btn-sm btn-block btn-info"
													onclick="showpassCHangeForm();">Change Password</button>
											</span>
										</div>
										<div id="passwordchangeform" class="animated bounceInUp">
											<h4>Change Password</h4>
											<form:form commandName="profilePasswordChangeForm"
												action="${pageContext.request.contextPath}/changePassword.do"
												method="POST">
												<div class="form-group">
													<span class="span_style"> <form:input
															type="password" class="form-control input-sm "
															placeholder="Old Password" path="oldPassword"
															maxlength="24" />
													</span>
												</div>

												<div class="form-group">
													<span class="span_style"> <form:input
															type="password" class="form-control input-sm "
															placeholder="New Password" path="newPassword"
															maxlength="24" />
													</span>
												</div>
												<div class="form-group">
													<span class="span_style"> <form:input
															type="password" class="form-control input-sm "
															placeholder="Confirm New Password"
															path="confirmNewPassword" maxlength="24" /> <!-- <i *ngIf="newpassword == confirmpassword" class="fas fa-check"></i> -->
													</span>
												</div>

												<button type="submit"
													class="btn btn-sm btn-block btn-info button_block">
													<span> Reset Password</span>
												</button>
												<button type="button"
													class="btn btn-sm btn-block btn-info button_block"
													onclick="hidepassChangeForm();">
													<span>Cancel</span>
												</button>
											</form:form>
										</div>


									</div>

								</div>



							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="headingTwo">
							<h5 class="mb-0">
								<button class="btn btn-link collapsed" data-toggle="collapse"
									data-target="#collapseTwo" aria-expanded="false"
									aria-controls="collapseTwo">
									<span class="profile"> <i class="fas fa-university"
										style="font-size: 22px; margin-right: 10px;"
										aria-hidden="true"></i> BANK ACCOUNT DETAILS
									</span>
								</button>
							</h5>
						</div>
						<div id="collapseTwo" class="collapse"
							aria-labelledby="headingTwo" data-parent="#accordion">
							<div class="card-body">

								<div class="row">
									<div class="col-md-6 col-lg-6">

										<div>
											<form:form commandName="profileAccount"
												action="${pageContext.request.contextPath}/profileAccount.do"
												method="POST">
												<form:hidden path="uid" />
												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4">
															<label>Account Holder Name <span
																style="color: red">*</span>
															</label>
														</div>
														<div class="col-md-8 col-lg-8">
															<form:input type="text"
																class="form-control form-control-sm"
																path="accountHolder" />
														</div>
													</div>
												</div>

												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4">
															<label>IFSC Code <span style="color: red">*</span>
															</label>
														</div>
														<div class="col-md-8 col-lg-8">
															<form:input type="text" id="ifsc"
																class="form-control form-control-sm" path="ifscCode"
																maxlength="11" />
														</div>
														<span id="invalidifsc"
															style="color: red; font-size: 11px;"></span>
													</div>
												</div>

												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4">
															<label>Bank Name <span style="color: red">*</span>
															</label>
														</div>
														<div class="col-md-8 col-lg-8">
															<%-- <form:input type="text"
														class="form-control form-control-sm" path="bankName" /> --%>
															<form:select class="form-control form-control-sm"
																path="bankName" id="bankName">
																<form:options items="${bankNames }" />
															</form:select>
														</div>
													</div>
												</div>

												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4">
															<label>Account Number <span style="color: red">*</span>
															</label>
														</div>
														<div class="col-md-8 col-lg-8">
															<form:input type="text"
																class="form-control form-control-sm"
																path="accountNumber" />
														</div>
													</div>
												</div>

												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4">
															<label>Branch <span style="color: red">*</span>
															</label>
														</div>
														<div class="col-md-8 col-lg-8">
															<form:input type="text" id="branch"
																class="form-control form-control-sm" path="branch"
																readonly="true" />
														</div>
													</div>
												</div>

												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4">
															<label>Branch City <span style="color: red">*</span>
															</label>
														</div>
														<div class="col-md-8 col-lg-8">
															<form:input type="text" id="bankCity"
																class="form-control form-control-sm" path="branchCity"
																readonly="true" />
														</div>
													</div>
												</div>

												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4">
															<label>Branch State <span style="color: red">*</span>
															</label>
														</div>
														<div class="col-md-8 col-lg-8">
															<form:input type="text"
																class="form-control form-control-sm" path="accountState"
																id="bankState" readonly="true" />
														</div>
													</div>
												</div>

												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4">
															<label>Account Type <span style="color: red">*</span>
															</label>
														</div>
														<div class="col-md-8 col-lg-8">
															<%-- <form:input type="text"
														class="form-control form-control-sm" path="accountType" /> --%>
															<form:select class="form-control form-control-sm"
																path="accountType">
																<form:options items="${accountTypes }" />
															</form:select>
														</div>
													</div>
												</div>

												<div class="form-group">
													<div class="row">
														<div class="col-md-4 col-lg-4"></div>
														<div class="col-md-8 col-lg-8">
															<button type="submit" class="btn btn-sm btn-primary">Update
																Bank Details</button>
															<!-- <button type="button" (click)="editBankDetails=!editBankDetails" class="btn btn-primary">Edit Bank Details</button> -->

															<!-- <img src="assets/images/edit.png" class="img-fluid" style="height: 30px; float: right;" (click)="editBankDetails=!editBankDetails"
                          alt="Edit"> -->
														</div>
													</div>
												</div>

											</form:form>
										</div>
									</div>
									<div class="col-md-6 col-lg-6" style="margin: -1px;">
										<div class="row">
											<!-- <div class="col-md-4 col-lg-4">
                  <label>Fetch accounts linked to your mobile number</label>
                </div> -->
											<div class="col-md-12 col-lg-12"
												style="text-align: center; margin: auto;">
												<!-- <button type="button" class="btn btn-outline-secondary" style="margin-top: 15px;">
                    Fetch accounts linked to your mobile number
                  </button> -->
											</div>
										</div>
									</div>
								</div>



							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="headingThree">
							<h5 class="mb-0">
								<button class="btn btn-link collapsed" data-toggle="collapse"
									data-target="#collapseThree" aria-expanded="false"
									aria-controls="collapseThree">
									<span class="profile"> <i class="fas fa-address-card"
										style="font-size: 22px; margin-right: 10px;"
										aria-hidden="true"></i> INVESTOR ADDRESS
									</span>
								</button>
							</h5>
						</div>
						<div id="collapseThree" class="collapse"
							aria-labelledby="headingThree" data-parent="#accordion">
							<div class="card-body">

								<div class="row">
									<div class="col-md-6 col-lg-6">
										<form:form commandName="profileAddress"
											action="${pageContext.request.contextPath}/profileAddress.do"
											method="POST">
											<form:hidden path="uid" />
											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>Building Name/Number</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<form:input type="text"
															class="form-control form-control-sm" path="houseNumber" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>Address 1</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<form:input type="text"
															class="form-control form-control-sm" path="address1" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>Address 2 (Optional)</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<form:input type="text"
															class="form-control form-control-sm" path="address2" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>City/District/Town</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<form:input type="text"
															class="form-control form-control-sm" path="city" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>State</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<%-- <form:input type="text" class="form-control form-control-sm"
													path="state" /> --%>
														<form:select class="form-control form-control-sm"
															path="state">
															<form:option value="">Select</form:option>
															<form:options items="${states }" />
														</form:select>
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4">
														<label>Pincode</label>
													</div>
													<div class="col-md-8 col-lg-8">
														<form:input type="text"
															class="form-control form-control-sm" path="pincode" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="row">
													<div class="col-md-4 col-lg-4"></div>
													<div class="col-md-8 col-lg-8">
														<button type="submit" class="btn btn-sm btn-primary">Update
															Details</button>
													</div>
												</div>
											</div>

										</form:form>
									</div>
									<div class="col-md-6 col-lg-6" style="margin: -1px;">
										<h4>FAQ</h4>
										<ul>
											<li>
												<p>
													<b>Why address cannot be modified?</b> <br> <span
														class="font-size: 14px;"> Address obtained from
														UIDAI is considered KYC verified and hence not allowed for
														changes here. </span>
												</p>
											</li>
										</ul>
									</div>
								</div>



							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>