<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.fddetailed .desc {
	font-weight: 500;
	color: #ff9235;
}

* {
	box-sizing: border-box;
}

body {
	background-color: #f2fff9;
}

#regForm {
	background-color: #f2fff9;
	/* margin: 100px auto; */
	/*  font-family: Raleway;
  padding: 40px; */
	/* width: 100%; */
	min-width: 300px;
}

h1 {
	text-align: center;
}

/* input {
  padding: 10px;
  width: 100%;
  font-size: 17px;
  font-family: Raleway;
  border: 1px solid #aaaaaa;
} */

/* Mark input boxes that gets an error on validation: */
input.invalid {
	background-color: #ffdddd;
}

/* Hide all steps by default: */
.tab {
	display: none;
}

/* button {
  background-color: #4CAF50;
  color: #ffffff;
  border: none;
  padding: 10px 20px;
  font-size: 17px;
  font-family: Raleway;
  cursor: pointer;
}

button:hover {
  opacity: 0.8;
}
 */
#prevBtn {
	background-color: #bbbbbb;
}

/* Make circles that indicate the steps of the form: */
.step {
	height: 15px;
	width: 15px;
	margin: 0 2px;
	background-color: #bbbbbb;
	border: none;
	border-radius: 50%;
	display: inline-block;
	opacity: 0.5;
}

.step.active {
	opacity: 1;
}

/* Mark the steps that are finished and valid: */
.step.finish {
	background-color: #4CAF50;
}
</style>

<div class="fddetailed">

	<div style="padding-top: 2rem;">
		<form:form id="fdpurchaseform"
			action="${pageContext.request.contextPath}/fixed-deposit/mahindra-fd-purchase"
			enctype="multipart/form-data" modelAttribute="fdform">

			<div style="text-align: center; margin-bottom: 40px;">
				<span class="step"></span> <span class="step"></span> <span
					class="step"></span>
			</div>
			<div class="text-primary">Provide the required details to
				process your deposit request</div>

			<div>
				<c:if test="${not empty error }">
					<!-- <span class="text-danger">{error}</span> -->
					<div class="alert alert-danger" role="alert" style="padding: 4px;"
						id="errormsgbox">${ error}</div>
				</c:if>
			</div>

			<div class="tab animated fadeIn">
				<div id="accordion">
					<div class="card">
						<div class="card-header" id="headingOne">
							<h5 class="mb-0">
								<button class="btn btn-link" style="padding: 0; margin: auto;"
									data-target="#collapseOne" data-toggle="collapse"
									aria-expanded="true" aria-controls="collapseOne">
									<img
										src="<c:url value="${contextcdn}/resources/images/invest/save21.svg"/>"
										class="img-fluid"
										style="height: 2rem; float: left; margin-right: .5rem;"><span>Saving
										Details</span>
								</button>
							</h5>
						</div>

						<div id="collapseOne" class="collapse show"
							aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body">
								<div class="row">
									<div class="col-6">
										<label class="desc">Investor's PAN</label>
									</div>
									<div class="col-6">
										<label>${fdform.pan }</label>
										<form:hidden path="pan" />
									</div>
									<div class="col-6">
										<label class="desc">Mobile number</label>
									</div>
									<div class="col-6">
										<label> ${fdform.mobile }</label>

									</div>
									<div class="col-6">
										<label class="desc">Deposit Amount</label>
									</div>
									<div class="col-6">
										<label><fmt:formatNumber value="${fdform.saveAmount }"
												type="currency" /> </label>
										<%-- <form:label path="saveAmount"><fmt:formatNumber value="${fdform.saveAmount }" type="currency" /></form:label> --%>

									</div>
									<div class="col-6">
										<label class="desc">Category</label>
									</div>
									<div class="col-6">
										<c:if test="${fdform.category=='PU' }">
											<label>GENERAL</label>
										</c:if>
										<c:if test="${fdform.category=='SR' }">
											<label>SENIOR CITIZEN</label>
										</c:if>

									</div>
									<div class="col-6">
										<label class="desc">Scheme Type</label>
									</div>
									<div class="col-6">
										<c:choose>
											<c:when test="${fdform.scheme == 'C'}">
												<label>Cumulative</label>
											</c:when>
											<c:when test="${fdform.scheme == 'NC'}">
												<label>Non-Cumulative</label>
											</c:when>
											<c:otherwise>
												<label class="text-danger">Unsupported Category!</label>
											</c:otherwise>
										</c:choose>

									</div>

								</div>
							</div>
						</div>
					</div>
				</div>

				<div id="accordion2" class="mt-4">
					<div class="card">
						<div class="card-header" id="headingTwo">
							<h5 class="mb-0">
								<button class="btn btn-link"
									style="padding: 0; margin: auto; margin: auto;"
									data-target="#collapseTwo" aria-expanded="true"
									aria-controls="collapseTwo">
									<img
										src="<c:url value="${contextcdn}/resources/images/invest/user21.svg"/>"
										class="img-fluid"
										style="height: 2rem; float: left; margin-right: .5rem;"><span>Customer
										Details</span>
								</button>
							</h5>
						</div>

						<div id="collapseTwo" class="collapse show"
							aria-labelledby="headingTwo" data-parent="#accordion2">
							<div class="card-body">

								<div class="md-form input-group mb-0">
									<div class="input-group-prepend">
										<!-- <span class="input-group-text md-addon"><i
											class="fas fa-user-alt "></i></span> -->
										<form:select path="primaryHolderTitle"
											class="browser-default custom-select ">
											<form:option value="MR" selected="selected">Mr</form:option>
											<form:option value="MS">Ms</form:option>
											<form:option value="MRS">Mrs</form:option>
										</form:select>


									</div>
									<form:input type="text" path="firstName" class="form-control"
										style="text-transform:uppercase" minlenth="8" maxlength="64"
										pattern="[a-zA-Z. ]*" id="firstNameid" required="required"
										aria-label="First name" placeholder=" First Name" />

									<form:input type="text" path="middleName" class="form-control"
										style="text-transform:uppercase" minlenth="8" maxlength="64"
										pattern="[a-zA-Z. ]*" id="middlenameid"
										aria-label="Middle name" placeholder=" Middle Name" />
									<form:input type="text" path="lastName" class="form-control"
										style="text-transform:uppercase" minlenth="8" maxlength="64"
										pattern="[a-zA-Z. ]*" id="lastNameid" required="required"
										aria-label="Last name" placeholder=" last Name" />
								</div>


								<div class="form-row">
									<div class="col-md-6">
										<div class="md-form form-sm">
											<i class="fas fa-envelope prefix"></i>
											<form:input type="email" path="email" class="form-control"
												minlenth="5" maxlength="64" id="emailid" required="required" />
											<label for="emailid">Email ID for communication</label>
										</div>

									</div>

									<div class="col-md-6">
										<div class="md-form form-sm">
											<i class="fas fa-calendar-day prefix"></i>
											<form:input type="text" path="dob" required="required"
												data-provide="datepicker" data-date-format="mm/dd/yyyy"
												data-date-start-date="-65y" data-date-end-date="-21d"
												maxlength="10"
												class="form-control form-control-custom datepicker"
												id="investorDOB" />
											<label for="dobid">Date of birth</label>
										</div>

									</div>

								</div>

								<div class="form-row mt-3">
									<div class="col-md-6">
										<div class="form-group row">
											<label for="tenure" class="col-md-4 col-form-label desc">Int.
												Frequency</label>
											<div class="col-md-8 text-md-right" id="frequency">
												<div class="btn-group btn-group-sm btn-group-toggle"
													data-toggle="buttons" id="frequencyid">
													<c:choose>
														<c:when test="${fdform.scheme == 'C'}">
															<label class="btn btn-info active"> <form:radiobutton
																	path="intFreq" value="Y" autocomplete="off"
																	checked="checked" /> YEARLY
															</label>
														</c:when>
														<c:when test="${fdform.scheme == 'NC'}">

															<c:forEach var="listVar" items="${interestFrequency}">
																<c:choose>
																	<c:when test="${listVar=='Q' }">
																		<label class="btn btn-info"> <form:radiobutton
																				path="intFreq" value="${listVar }"
																				autocomplete="off" /> Quarterly
																		</label>
																	</c:when>
																	<c:when test="${listVar=='H' }">
																		<label class="btn btn-info active"> <form:radiobutton
																				path="intFreq" value="${listVar }"
																				autocomplete="off" checked="checked" /> Half-Yearly
																		</label>
																	</c:when>
																	<c:when test="${listVar=='Y' }">
																		<label class="btn btn-info"> <form:radiobutton
																				path="intFreq" value="${listVar }"
																				autocomplete="off" /> Yearly
																		</label>
																	</c:when>
																</c:choose>
															</c:forEach>

														</c:when>
														<c:otherwise>
															<label class="btn btn-info active"> <form:radiobutton
																	path="intFreq" value="Y" autocomplete="off"
																	checked="checked" /> YEARLY
															</label>
														</c:otherwise>
													</c:choose>


												</div>

											</div>
										</div>
									</div>


									<div class="col-md-6">
										<div class="form-group row">
											<!-- Material input -->
											<label for="tenure" class="col-md-4 col-form-label desc">Tenure
												(in Months)</label>
											<div class="col-md-8  text-md-right" id="tenure">
												<div class="btn-group btn-group-sm btn-group-toggle"
													data-toggle="buttons" id="radioamount">

													<c:forEach var="listVar" items="${tenurelist}">
														<label
															class="btn btn-info <c:if test="${fdform.saveTenure eq listVar}">active</c:if>">
															<form:radiobutton path="saveTenure" value="${listVar }" />
															${listVar }
														</label>
													</c:forEach>

												</div>
												<div class="btn-group btn-group-sm btn-group-toggle">
													<label class="btn btn-secondary">Int. Rate </label> <label
														class="btn btn-default" id="interestrateview">${fdform.interestRate}%
														p.a </label>
												</div>

											</div>
										</div>
									</div>
								</div>

								<div class="form-row">

									<div class="col-md-6">
										<div class="form-group row">
											<label for="tenure" class="col-6 col-form-label desc">Holding
												Pattern </label>
											<div class="col-6 text-md-right" id="holdingpattern">
												<div class="btn-group btn-group-sm btn-group-toggle"
													data-toggle="buttons" id="holdingoption">
													<label class="btn btn-info active"> <form:radiobutton
															path="holdingOptions" value="01" autocomplete="off"
															checked="checked" /> SINGLE
													</label>
													<%-- <label class="btn btn-info"> <form:radiobutton
															path="holdingOptions" value="MULTIPLE" autocomplete="off" />
														MULTIPLE
													</label> --%>
												</div>
												<div class="custom-control custom-checkbox mt-3">
													<form:checkbox path="nomineechosen"
														class="custom-control-input" id="nomineeid" />
													<label class="custom-control-label" for="nomineeid">Nominee</label>
												</div>

											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group row">
											<!-- Material input -->
											<label for="tenure" class="col-8 col-form-label desc">Tax
												Deduction at Source? </label>
											<div class="col-4 text-md-right" id="scheme">
												<div
													class="btn-group btn-group-sm btn-group-toggle float-right"
													data-toggle="buttons" id="taxDeduct">
													<label class="btn btn-info active"> <form:radiobutton
															path="taxDeductAtSource" value="YES" autocomplete="off"
															checked="checked" /> YES
													</label>
													<%-- <label class="btn btn-info"> <form:radiobutton
															path="taxDeductAtSource" value="NO" autocomplete="off" />
														NO
													</label> --%>
												</div>
											</div>
										</div>
									</div>

								</div>

								<div class="form-row">
									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:select class="custom-select" path="gender"
												required="required" id="genderid"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value="" selected="selected"> Select Gender</form:option>
												<form:option value="M"> Male</form:option>
												<form:option value="F"> Female</form:option>
											</form:select>
										</div>

									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:select class="custom-select" path="maritalStatus"
												required="required" id="maritalstatusid"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value="00" selected="selected"> Marital Status</form:option>
												<form:option value="01">Married</form:option>
												<form:option value="02">Unmarried</form:option>
												<form:option value="03">Others</form:option>
											</form:select>
										</div>
									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:select class="custom-select" path="occupation"
												required="required" id="occupationid"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value="" selected="selected">Occupation</form:option>
												<form:options items="${mmoccupation }" />
											</form:select>
										</div>
									</div>

								</div>


								<div class="form-group row mt-3">
									<!-- Material input -->
									<label class="col-sm-2 col-form-label desc">Father's
										Name</label>
									<div class="col-sm-10">
										<div class="md-form input-group mb-0" style="margin: auto;">
											<div class="input-group-prepend">
												<form:select path="kycFatherPrefix"
													class="browser-default custom-select ">
													<form:option value="MR" selected="selected">Mr</form:option>
												</form:select>
											</div>

											<form:input type="text" path="kycFatherFirstName"
												class="form-control" style="text-transform:uppercase"
												minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
												id="kycFatherFirstNameid" aria-label="First name"
												placeholder=" First Name" />

											<form:input type="text" path="kycFatherMiddlename"
												class="form-control" style="text-transform:uppercase"
												minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
												id="kycFatherMiddlenameid" aria-label="Middle name"
												placeholder=" Middle Name" />
											<form:input type="text" path="kycFatherLastName"
												class="form-control" style="text-transform:uppercase"
												minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
												id="kycFatherLastNameid" aria-label="Last name"
												placeholder="Last Name" />
										</div>
									</div>
								</div>

								<div class="form-group row mt-3">
									<!-- Material input -->
									<label class="col-sm-2 col-form-label desc">Mother's
										Name</label>
									<div class="col-sm-10">
										<div class="md-form input-group mb-0" style="margin: auto;">
											<div class="input-group-prepend">
												<form:select path="kycMotherPrefix"
													class="browser-default custom-select ">
													<form:option value="MS">Ms</form:option>
													<form:option value="MRS" selected="selected">Mrs</form:option>
												</form:select>
											</div>

											<form:input type="text" path="kycMotherFirstName"
												class="form-control" style="text-transform:uppercase"
												minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
												id="kycMotherFirstNameid" aria-label="First name"
												placeholder=" First Name" />

											<form:input type="text" path="kycMotherMiddlename"
												class="form-control" style="text-transform:uppercase"
												minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
												id="kycMotherMiddlenameid" aria-label="Middle name"
												placeholder=" Middle Name" />
											<form:input type="text" path="kycMotherLastName"
												class="form-control" style="text-transform:uppercase"
												minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
												id="kycMotherLastNameid" aria-label="Last name"
												placeholder="last Name" />
										</div>
									</div>
								</div>





								<!-- 	<div class="mx-auto text-center mt-2">
								<button type="submit"
									class="btn btn-primary mt-md-4 px-3 py-2 custom_btn label-size">
									<strong>Process Now <i
										class="fas fa-angle-double-right"></i></strong>
								</button>
							</div> -->

							</div>
						</div>
					</div>

				</div>
				<!-- End of accordion2  -->

				<!-- accordion3  -->
				<div id="accordion3" class="mt-4">
					<div class="card">
						<div class="card-header" id="headingThree">
							<h5 class="mb-0">
								<button class="btn btn-link"
									style="padding: 0; margin: auto; margin: auto;"
									data-target="#collapseThree" aria-expanded="true"
									aria-controls="collapseThree">
									<img
										src="<c:url value="${contextcdn}/resources/images/invest/house21.svg"/>"
										class="img-fluid"
										style="height: 2rem; float: left; margin-right: .5rem;"><span>
										KYC Verified Address Details</span>
								</button>
							</h5>
						</div>

						<div id="collapseThree" class="collapse show"
							aria-labelledby="headingThree" data-parent="#accordion3">
							<div class="card-body">
								<h6 class="text-secondary">*Same Address will be used for
									communication</h6>
								<div class="form-row">
									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="address1" class="form-control"
												minlenth="1" maxlength="64" id="address1id"
												required="required" />
											<label for="address1id">Address 1</label>
										</div>

									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="address2_1"
												class="form-control" minlenth="1" maxlength="64"
												id="address2id" required="required" />
											<label for="address2id">Address 2</label>
										</div>

									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="address3_1"
												class="form-control" minlenth="1" maxlength="64"
												id="address3id" />
											<label for="address3id">Address 3</label>
										</div>

									</div>

								</div>

								<div class="form-row">
									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<%-- <form:input type="text" path="addCountry1"
												class="form-control" minlenth="1" maxlength="64"
												id="addCountryid" required="required" />
											<label for="addCountryid">Country</label> --%>
											<form:select class="custom-select" path="addCountry1"
												required="required" id="addCountryid"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value="IN" selected="selected"> India</form:option>
											</form:select>
										</div>

									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:select class="custom-select stateselect"
												path="addressstate1" required="required" id="addressstateid"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value="" selected="selected"> Select State</form:option>
												<form:options items="${states}" />
											</form:select>
											<small id="statevalidity" class="form-text text-danger"></small>
										</div>
									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<%-- <form:input type="text" path="addressDistrict1"
												class="form-control" minlenth="1" maxlength="16"
												id="addressDistrictid" required="required" />
											<label for="addressDistrictid">District</label> --%>

											<form:select class="custom-select" path="addressDistrict1"
												required="required" id="addressDistrict1id"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value="${fdform.addressDistrict1 }"
													selected="selected">${fdform.addressDistrict1 }</form:option>
												<%-- 	<form:options items="${availdistrict }"/> --%>
											</form:select>
											<small id="districtvalidity" class="form-text text-danger"></small>
										</div>
									</div>

								</div>


								<div class="form-row">
									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="addressCity1"
												class="form-control" minlenth="1" maxlength="64"
												id="addressCityid" required="required" />
											<label for="addressCityid">City</label>
										</div>

									</div>
									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="addresspincode1"
												class="form-control pincodeid" pattern="[0-9]{6}"
												minlength="6" maxlength="6" id="addresspincode1id"
												required="required" />
											<label for="addresspincode1id">Pincode</label> <small
												id="pancodevalidity" class="form-text text-danger"></small>

											<%-- <form:select class="custom-select" path="addresspincode1"
												required="required" id="addresspincode1id"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
											</form:select> --%>
										</div>
									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="ckyc" class="form-control"
												minlenth="1" maxlength="16" id="ckycid" />
											<label for="ckycid">CKYC Number</label>
										</div>
									</div>

								</div>

							</div>
						</div>
					</div>
				</div>
				<!-- End of accordion3  -->


				<div id="accordion8" class="mt-4">
					<div class="card">
						<div class="card-header" id="headingEight">
							<h5 class="mb-0">
								<button class="btn btn-link" style="padding: 0; margin: auto;"
									data-target="#collapseEight" data-toggle="collapse"
									aria-expanded="true" aria-controls="collapseSeven">
									<span>Nominee</span>
								</button>
							</h5>
						</div>

						<div id="collapseEight" class="collapse show"
							aria-labelledby="headingEight" data-parent="#accordion8">
							<div class="card-body">
								<!-- ----------------------------- -->

								<!--Nominee Details Block  -->

								<div id="nomineeDetailsBlock"
									<c:if test="${fdform.nomineechosen == false }">style="display: none;"</c:if>>

									<div class="md-form input-group mb-0">
										<div class="input-group-prepend">
											<!-- <span class="input-group-text md-addon"><i
											class="fas fa-user-alt "></i></span> -->
											<form:select path="nomineeprefix"
												class="browser-default custom-select ">
												<form:option value="MR" selected="selected">Mr</form:option>
												<form:option value="MS">Ms</form:option>
												<form:option value="MRS">Mrs</form:option>
											</form:select>


										</div>
										<form:input type="text" path="nomineefirstname"
											class="form-control" style="text-transform:uppercase"
											minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
											id="firstName" aria-label="First name"
											placeholder=" First Name" />

										<form:input type="text" path="nomineemiddlename"
											class="form-control" style="text-transform:uppercase"
											minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
											id="middleName" aria-label="Middle name"
											placeholder=" Middle Name" />
										<form:input type="text" path="nomineelastname"
											class="form-control" style="text-transform:uppercase"
											minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
											id="nomineelastnameid" aria-label="Last name"
											placeholder="Last Name" />
									</div>

									<div class="form-row">
										<div class="col-md-4">
											<div class="md-form form-sm mb-0">
												<form:select class="custom-select" path="nomineerelation"
													required="required" id="nomineerelationid"
													style="border-top: transparent;border-left: transparent;border-right: transparent;">
													<form:option value="" selected="selected">Select Relation</form:option>
													<form:options items="${relationMaster }" />
												</form:select>
											</div>

										</div>

										<div class="col-md-4">
											<div class="md-form form-sm">
												<i class="fas fa-calendar-day prefix"></i>
												<form:input type="text" path="nomineedob"
													data-provide="datepicker" data-date-format="mm/dd/yyyy"
													data-date-start-date="-65y" data-date-end-date="-21d"
													maxlength="10"
													class="form-control form-control-custom datepicker"
													id="nomineedobid" />
												<label for="nomineedobid">Date of birth</label>
											</div>
										</div>

										<div class="col-md-4">
											<div class="md-form input-group mb-3">
												<div class="input-group-prepend">
													<form:select path="nomineeminor"
														class="browser-default custom-select ">
														<form:option value="N" selected="selected">Not Minor</form:option>
														<form:option value="Y">Minor</form:option>
													</form:select>


												</div>

												<form:input type="text" path="nomineeguardian"
													class="form-control" style="text-transform:uppercase"
													minlenth="8" maxlength="64" pattern="[a-zA-Z. ]*"
													id="nomineeguardianid" aria-label="Last name"
													placeholder="Guardian Name" />

											</div>
										</div>

									</div>

									<div class="form-row">
										<div class="col-md-6">
											<div class="md-form form-sm mb-0">
												<form:input type="text" path="nomineemobile"
													class="form-control" minlenth="10" maxlength="10"
													pattern="[0-9]*" id="nomineemobileid"
													aria-label="Mobile no." placeholder="Nominee's mobile no" />
											</div>
										</div>

										<div class="col-md-6">
											<div class="md-form form-sm mb-0">
												<form:input type="email" path="nomineeemail"
													class="form-control" minlenth="10" maxlength="128"
													id="nomineeemailid" aria-label="Email"
													placeholder="Email ID" />
											</div>
										</div>

									</div>

									<div class="form-row">
										<div class="col-md-4">
											<div class="md-form form-sm mb-0">
												<form:input type="text" path="nomineeaddress1"
													class="form-control" style="text-transform:uppercase"
													maxlength="32" pattern="[0-9]*" id="nomineeadd1id"
													aria-label="Address 1" placeholder="Address 1" />
											</div>
										</div>

										<div class="col-md-4">
											<div class="md-form form-sm mb-0">
												<form:input type="email" path="nomineeaddress2"
													style="text-transform:uppercase" class="form-control"
													maxlength="32" id="nomineeadd2id" aria-label="Address 2"
													placeholder="Address 2" />
											</div>
										</div>

										<div class="col-md-4">
											<div class="md-form form-sm mb-0">
												<form:input type="email" path="nomineeaddress3"
													style="text-transform:uppercase" class="form-control"
													maxlength="32" id="nomineeadd3id" aria-label="Address 3"
													placeholder="Address 3" />
											</div>
										</div>
									</div>

									<div class="form-row">
										<div class="col-md-6">
											<div class="md-form form-sm mb-0">
												<form:select class="custom-select stateselect"
													path="nomineestatecode" id="nomineestatecodeid"
													style="border-top: transparent;border-left: transparent;border-right: transparent;">
													<form:option value="" selected="selected"> Select State</form:option>
													<form:options items="${states}" />
												</form:select>
												<small id="nomineestatevalidity"
													class="form-text text-danger"></small>
											</div>

										</div>

										<%-- <div class="col-md-6">
											<div class="md-form form-sm mb-0">
												<form:input type="text" path="nomineedistrict"
													class="form-control" maxlength="32" id="nomineedistrictid"
													aria-label="Email" placeholder="District" />
											</div>
										</div> --%>
										<div class="col-md-6">
											<div class="md-form form-sm mb-0">
												<form:select class="custom-select" path="nomineedistrict"
													required="required" id="nomineedistrictid"
													style="border-top: transparent;border-left: transparent;border-right: transparent;">
													<form:option value="${fdform.nomineedistrict }"
														selected="selected">${fdform.nomineedistrict }</form:option>
												</form:select>
												<small id="nomineedistrictvalidity"
													class="form-text text-danger"></small>
											</div>
										</div>


									</div>

									<div class="form-row">
										<div class="col-md-6">
											<div class="md-form form-sm mb-0">
												<form:input type="text" path="nomineecity"
													class="form-control" maxlength="32" id="nomineecityid"
													aria-label="Email" placeholder="City" />
											</div>
										</div>

										<div class="col-md-6">
											<div class="md-form form-sm mb-0">
												<form:input type="text" path="nomineecitypincode"
													class="form-control pincodeid" minlength="6" maxlength="6"
													pattern="[0-9]*" id="nomineecitypincodeid"
													aria-label="Email" placeholder="Pincode" />
												<small id="nomineepancodevalidity"
													class="form-text text-danger"></small>
											</div>
										</div>

									</div>



									<!--End of Nominee Details Block  -->


								</div>
							</div>
						</div>
					</div>
					<!--  -->

				</div>

			</div>
			<!--End of 1st TAB  -->

			<div class="tab">

				<!-- accordion4  -->
				<div id="accordion4" class="mt-4">
					<div class="card">
						<div class="card-header" id="headingFour">
							<h5 class="mb-0">
								<button class="btn btn-link" style="padding: 0; margin: auto;"
									data-target="#collapseFour" aria-expanded="true"
									aria-controls="collapseFour">
									<img
										src="<c:url value="${contextcdn}/resources/images/invest/bank21.png"/>"
										class="img-fluid"
										style="height: 2rem; float: left; margin-right: .5rem;"><span>Bank
										Details</span>
								</button>
							</h5>
						</div>

						<div id="collapseFour" class="collapse show"
							aria-labelledby="headingFour" data-parent="#accordion4">
							<div class="card-body">
								<h6 class="text-secondary">Bank mandate of First/Sole
									Applicant (account in which you wish to receive maturity
									proceeds)</h6>
								<div class="form-row">
									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="ifscCode" class="form-control"
												minlenth="1" maxlength="16" id="ifscid" required="required" />
											<label for="ifscid">IFSC Code</label>
										</div>

									</div>
									<div class="col-md-8">
										<div class="row">
											<div class="col-4">
												<label class="desc">Bank Name</label>
											</div>
											<div class="col-8">
												<label id="banknameid">${fdform.bankname }</label>
											</div>

											<div class="col-4">
												<label class="desc">Branch</label>
											</div>
											<div class="col-8">
												<label id="bankbranchid">${fdform.bankbranch }</label>
											</div>
											
											<div class="col-4">
												<label class="desc">MICR Code</label>
											</div>
											<div class="col-8">
												<label id="micrcodeid">${fdform.micrCode }</label>
											</div>

										</div>
										<form:hidden path="bankname" />
										<form:hidden path="bankbranch" />
										<form:hidden path="micrCode" />

									</div>

								</div>

								<%-- <div class="form-row">
									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="bankname" class="form-control"
												minlenth="1" maxlength="128" id="bankNameid"
												required="required"  disabled="true"/>
											<label for="bankNameid">Bank Name</label>
										</div>

									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="bankbranch" class="form-control"
												minlenth="1" maxlength="16" id="bankranchid"
												required="required"  disabled="true" />
											<label for="bankbranchid">Branch Name</label>
										</div>
									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="micrCode" class="form-control"
												minlenth="1" maxlength="16" id="micrCodeid"
												required="required" disabled="true" />
											<label for="micrCodeid">MICR Code</label>
										</div>
									</div>

								</div> --%>

								<div class="form-row">

									<div class="col-md-6">
										<div class="md-form form-sm mb-0">
											<form:input type="password" path="accountNumber"
												class="form-control" minlenth="1" maxlength="24"
												id="accountNumberid" required="required" />
											<label for="accountNumberid">Account No.</label>
										</div>
									</div>

									<div class="col-md-6">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="confirmaccountNumber"
												class="form-control" minlenth="1" maxlength="24"
												id="confirmaccountNumberid" required="required" />
											<label for="confirmaccountNumberid">Confirm Account
												No.</label>
											<small id="accountvalidationmsg"></small>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<!-- End of accordion4  -->

				<!-- accordin5 -->
				<div id="accordion5" class="mt-4">
					<div class="card">
						<div class="card-header" id="headingFive">
							<h5 class="mb-0">
								<button class="btn btn-link" style="padding: 0; margin: auto;"
									data-target="#collapseFive" aria-expanded="true"
									aria-controls="collapseFive">
									<img
										src="<c:url value="${contextcdn}/resources/images/invest/declaration21.svg"/>"
										class="img-fluid"
										style="height: 2rem; float: left; margin-right: .5rem;"><span>Declared
										for</span>
								</button>
							</h5>
						</div>

						<div id="collapseFive" class="collapse show"
							aria-labelledby="headingFive" data-parent="#accordion5">
							<div class="card-body">


								<div class="form-row">
									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<%-- <form:input type="text" path="nationality"
												class="form-control" minlenth="1" maxlength="24"
												id="nationalityid" required="required" />
											<label for="nationalityid">Nationality</label> --%>
											<form:select class="custom-select" path="nationality"
												required="required" id="nationalityid"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value="">Select Nationality</form:option>
												<form:option value="IN" selected="selected"> India</form:option>
											</form:select>
										</div>

									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<%-- <form:input type="text" path="countryOfBirth"
												class="form-control" minlenth="1" maxlength="24"
												id="countryOfBirthid" required="required" />
											<label for="countryOfBirthid">Country of Birth</label> --%>
											<form:select class="custom-select" path="countryOfBirth"
												required="required" id="countryOfBirthid"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value=""> Country of Birth</form:option>
												<form:option value="IN" selected="selected"> India</form:option>
											</form:select>

										</div>
									</div>

									<div class="col-md-4">
										<div class="md-form form-sm mb-0">
											<form:input type="text" path="cityOfBirth"
												class="form-control" minlenth="1" maxlength="24"
												id="cityOfBirthid" required="required" />
											<label for="cityOfBirthid">City of Birth</label>
										</div>
									</div>

								</div>

								<div class="form-row">
									<div class="col-md-6">
										<div class="form-group row">
											<!-- Material input -->
											<label for="taxresidentOtherIndia"
												class="col-md-8 col-form-label desc">Are you/joint
												holder a tax resident of a country other than India? </label>
											<div class="col-md-4 text-md-right"
												id="taxresidentOtherIndia">
												<div class="btn-group btn-group-sm btn-group-toggle"
													data-toggle="buttons" id="taxResOtherThanIndiaid">
													<label class="btn btn-secondary ${fdform.taxResidentOtherCountry == 'YES' ? 'active' : ''}"> <form:radiobutton
															path="taxResidentOtherCountry" value="YES"
															autocomplete="off" checked="${fdform.taxResidentOtherCountry == 'YES' ? 'checked' : ''}" /> YES
													</label> <label class="btn btn-secondary ${fdform.taxResidentOtherCountry == 'NO' ? 'active' : ''}"> <form:radiobutton
															path="taxResidentOtherCountry" value="NO"
															autocomplete="off" checked="${fdform.taxResidentOtherCountry == 'NO' ? 'checked' : ''}" /> NO
													</label>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group row">
											<!-- Material input -->
											<label for="greencardholderid"
												class="col-md-8 col-form-label desc">Are you/joint
												holder a green card holder? </label>
											<div class="col-md-4 text-md-right" id="greencardholderid">
												<div class="btn-group btn-group-sm btn-group-toggle"
													data-toggle="buttons" id="greenCardHolderbuttonid">
													<label class="btn btn-secondary ${fdform.greenCardHolder == 'YES' ? 'active' : ''}"> <form:radiobutton
															path="greenCardHolder" value="YES" checked="${fdform.greenCardHolder == 'YES' ? 'checked' : ''}" />
														YES
													</label> <label class="btn btn-secondary ${fdform.greenCardHolder == 'NO' ? 'active' : ''}"> <form:radiobutton
															path="greenCardHolder" value="NO" checked="${fdform.greenCardHolder == 'NO' ? 'checked' : ''}" /> NO
													</label>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="taxResidentOutsideIndia" style="display: ${fdform.taxResidentOtherCountry == 'NO' ? 'none' : 'block'}">
									<div class="form-row"
										style="border: 1px solid #d4d8dc; padding: 5px; overflow-x: scroll;">
										<p style="color: red; font-size: 11px;">
											Note: Please indicate ALL the countries in which you are a
											resident for tax purposes and associated details as required
											below <br>Note: To also include USA where the individual
											is a citizen/green card holder of USA
										</p>
										<table class="table table-sm table-bordered table-responsive"
											id="mfprofiledata2">
											<thead class="#3949ab indigo darken-1 white-text">
												<tr>
													<th scope="col" valign="middle">Country of Tax</th>
													<th scope="col" valign="middle">Tax Identification
														Type</th>
													<th scope="col" valign="middle">Tax Identification No.</th>
													<th scope="col" valign="middle">TRC Expiry Date</th>
													<th scope="col" valign="middle">Address Type</th>
													<th scope="col" valign="middle">Address1</th>
													<th scope="col" valign="middle">Address2</th>
													<th scope="col" valign="middle">Landmark</th>
													<th scope="col" valign="middle">State</th>
													<th scope="col" valign="middle">City</th>
													<th scope="col" valign="middle">Postal Code</th>
													<th scope="col" valign="middle">STD-Code Primary</th>
													<th scope="col" valign="middle">Tel. No. Primary</th>
													<th scope="col" valign="middle">Mobile No. Primary</th>
													<th scope="col" valign="middle">STD Code Other</th>
													<th scope="col" valign="middle">Action</th>
												</tr>
											</thead>

											<tbody id="fdforeigntaxdetailsbody">
												<c:forEach var="listVar" items="${fdform.foreignTaxDetails}" varStatus="loop">
													<tr>
														<%-- <td><form:input class='form-control form-control-sm' id="foreignTaxDetails[${loop.index}].taxid" type="text" path="foreignTaxDetails[${loop.index}].taxid" value="${listVar.taxCountry}" /></td> --%>
														<td><form:input class="form-control form-control-sm" readonly="true" id="foreignTaxDetails[${loop.index}].taxCountry" type="text" path="foreignTaxDetails[${loop.index}].taxCountry" value="${listVar.taxCountry}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].taxidentificationtype" type="text" path="foreignTaxDetails[${loop.index}].taxidentificationtype" value="${listVar.taxidentificationtype}" /></td>
														
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].taxidentificationno" type="text" path="foreignTaxDetails[${loop.index}].taxidentificationno" value="${listVar.taxidentificationno}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].trcexpirydate" type="text" path="foreignTaxDetails[${loop.index}].trcexpirydate" value="${listVar.trcexpirydate}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].taxaddresstype" type="text" path="foreignTaxDetails[${loop.index}].taxaddresstype" value="${listVar.taxaddresstype}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].ftaxaddress1" type="text" path="foreignTaxDetails[${loop.index}].ftaxaddress1" value="${listVar.ftaxaddress1}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].ftaxaddress2" type="text" path="foreignTaxDetails[${loop.index}].ftaxaddress2" value="${listVar.ftaxaddress2}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].ftaxcity" type="text" path="foreignTaxDetails[${loop.index}].ftaxcity" value="${listVar.ftaxcity}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].ftaxstate" type="text" path="foreignTaxDetails[${loop.index}].ftaxstate" value="${listVar.ftaxstate}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].ftaxpostalcode" type="text" path="foreignTaxDetails[${loop.index}].ftaxpostalcode" value="${listVar.ftaxpostalcode}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].ftaxlandmark" type="text" path="foreignTaxDetails[${loop.index}].ftaxlandmark" value="${listVar.ftaxlandmark}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].stdcodeprimary" type="text" path="foreignTaxDetails[${loop.index}].stdcodeprimary" value="${listVar.stdcodeprimary}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].primarytelno" type="text" path="foreignTaxDetails[${loop.index}].primarytelno" value="${listVar.primarytelno}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].ftaxmobileno" type="text" path="foreignTaxDetails[${loop.index}].ftaxmobileno" value="${listVar.ftaxmobileno}" /></td>
														<td><form:input class="form-control form-control-sm" id="foreignTaxDetails[${loop.index}].ftaxstdother" type="text" path="foreignTaxDetails[${loop.index}].ftaxstdother" value="${listVar.ftaxstdother}" /></td>
														<td style="text-align: center;">
															<i class='fas fa-minus-circle' style='color: red;cursor: pointer;' onclick="deleteRow(this)"></i>
														</td>
													</tr>
												</c:forEach>
											</tbody>

										</table>
										<button class="btn btn-sm btn-default" data-toggle="modal"
											data-target="#taxResidentModal"
											onclick="openTaxResidentBox();">Add Detail</button>
									</div>

									<div class="form-group row">
										<!-- Material input -->
										<label for="citizenshipid" class="col-sm-4 col-form-label">Citizenship</label>
										<div class="col-sm-8">
											<div class="md-form mt-8">
												<form:select path="citizenship" class="custom-select"
													required="required" multiple="multiple">
													<option value="" selected="selected">Select</option>
													<!-- <option value="1">One</option>
													<option value="2">Two</option>
													<option value="3">Three</option> -->
													<form:options items="${ckyccountrymaster }"/>
												</form:select>
											</div>
										</div>
									</div>

								</div>


							</div>
						</div>
					</div>
				</div>
				<!-- End of accordin5 -->

				<!-- accordion6  -->
				<div id="accordion6" class="mt-4">
					<div class="card">
						<div class="card-header" id="headingSix">
							<h5 class="mb-0">
								<button class="btn btn-link" style="padding: 0; margin: auto;"
									data-target="#collapseSix" aria-expanded="true"
									aria-controls="collapseSix">
									<img
										src="<c:url value="${contextcdn}/resources/images/invest/document21.svg"/>"
										class="img-fluid"
										style="height: 2rem; float: left; margin-right: .5rem;"><span>Upload
										KYC Documents</span>
								</button>
							</h5>
						</div>

						<div id="collapseSix" class="collapse show"
							aria-labelledby="headingSix" data-parent="#accordion6">
							<div class="card-body">
								<div class="form-row">

									<div class="col-md-3">
										<div class="input-group input-group-sm mb-3">
											<div class="input-group-prepend" style="margin: auto;">
												<img
													src="<c:url value="${contextcdn}/resources/images/invest/upload22.svg"/>"
													class="img-fluid"
													style="height: 1.5rem; float: left; margin-right: .5rem;">
											</div>
											<div class="custom-file">
												<form:input type="file" class="custom-file-input"
													path="canecelledcheque" id="canecelledchequeid"
													accept="image/jpg,image/jpeg,application/pdf" />
												<label class="custom-file-label" for="canecelledchequeid">Cancelled
													Cheque(1MB)</label>
											</div>
											<!-- <div>
											<img id="photospace" src="#" alt="your image" />
											</div> -->

										</div>
										<small id="photomsg" class="form-text text-danger"></small>
									</div>

									<div class="col-md-3">
										<div class="input-group input-group-sm mb-3">
											<div class="input-group-prepend" style="margin: auto;">
												<img
													src="<c:url value="${contextcdn}/resources/images/invest/upload22.svg"/>"
													class="img-fluid"
													style="height: 1.5rem; float: left; margin-right: .5rem;">
											</div>
											<div class="custom-file">
												<%-- <form:input type="file" path="kycphotoproof" id="photoid" onchange="readURL(this,'photospace');"  accept="image/jpg,image/jpeg,application/pdf" /> --%>
												<form:input type="file" class="custom-file-input"
													path="kycphotoproof" id="photoid"
													accept="image/jpg,image/jpeg,application/pdf" />
												<label class="custom-file-label" for="photoid">Upload
													Photo (1MB)</label>
											</div>
											<!-- <div>
											<img id="photospace" src="#" alt="your image" />
											</div> -->

										</div>
										<small id="photomsg" class="form-text text-danger"></small>
									</div>

									<div class="col-md-3">
										<div class="input-group input-group-sm mb-3">
											<div class="input-group-prepend" style="margin: auto;">
												<img
													src="<c:url value="${contextcdn}/resources/images/invest/upload22.svg"/>"
													class="img-fluid"
													style="height: 1.5rem; float: left; margin-right: .5rem;">
											</div>
											<div class="custom-file">
												<!-- <input type="file" class="custom-file-input" id="panproofid"> -->
												<%-- <form:input type="file" path="kycpanproof" id="panproofid" onchange="readURL(this,'panspace');"  accept="image/jpg,image/jpeg,application/pdf" /> --%>
												<form:input type="file" path="kycpanproof" id="panproofid"
													accept="image/jpg,image/jpeg,application/pdf" />
												<label class="custom-file-label" for="panproofid">PAN
													Proof (1MB)</label>
											</div>

											<!-- <img id="panspace" src="#" alt="Your PAN" /> -->
										</div>
										<small id="panmsg" class="form-text text-danger"></small>
									</div>

									<div class="col-md-3">
										<div class="input-group input-group-sm mb-3">
											<div class="input-group-prepend" style="margin: auto;">
												<img
													src="<c:url value="${contextcdn}/resources/images/invest/upload22.svg"/>"
													class="img-fluid"
													style="height: 1.5rem; float: left; margin-right: .5rem;">
											</div>
											<div class="custom-file">
												<!-- <input type="file" class="custom-file-input" id="addproofid"> -->
												<%-- <form:input type="file" path="kycaddressproof" id="addproofid" onchange="readURL(this,'addressspace');"  accept="image/jpg,image/jpeg,application/pdf" /> --%>
												<form:input type="file" path="kycaddressproof"
													id="addproofid"
													accept="image/jpg,image/jpeg,application/pdf" />
												<label class="custom-file-label" for="addproofid">Address
													Proof (1MB)</label> <small></small>
											</div>
											<!-- <img id="addressspace" src="#" alt="Your Address Proof" /> -->
										</div>
										<small id="addmsg" class="form-text text-danger"></small>

										<div class="md-form form-sm mb-0">
											<form:select class="custom-select" path="addressproofType"
												required="required" id="addressproofTypeid"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<form:option value="NA" selected="selected"> Address Proof Type</form:option>
												<form:option value="A">Passport</form:option>
												<form:option value="B">Voter ID</form:option>
												<form:option value="D">Driving License</form:option>
												<form:option value="E">UID</form:option>
											</form:select>
										</div>

										<div class="md-form form-sm mb-0">
											<form:input type="text" path="addressproofrefno"
												class="form-control" minlenth="1" maxlength="24"
												id="addressproofrefnoid" />
											<label for="addressproofrefnoid">Reference No.</label>
										</div>

										<div class="md-form form-sm" style="display: <c:if test="${fdform.addressproofpxpirydate != 'D' || fdform.addressproofpxpirydate != 'E'}">none</c:if>" id="expirydatebox">
											<i class="fas fa-calendar-day prefix"></i>
											<form:input type="text" path="addressproofpxpirydate"
												data-provide="datepicker" data-date-format="dd/mm/yyyy"
												data-date-start-date="+1d" data-date-end-date="+20y"
												maxlength="10"
												class="form-control form-control-custom datepicker"
												id="addressproofpxpirydateid" />
											<label for="dobid">Expiry Date</label>
										</div>


									</div>

								</div>

								<form:hidden path="mobile" />
								<form:hidden path="category" />
								<form:hidden path="scheme" />
								<form:hidden path="schemeCode" />
								<form:hidden path="saveAmount" />
								<form:hidden path="interestRate" id="intratetoggle" />
								<form:hidden path="cpTrnasRefNo" />

							</div>
						</div>
					</div>
				</div>
				<!--End of accordion6  -->



			</div>
			<!-- End of tab  -->

			<div class="tab">



				<div id="accordion7">
					<div class="card">
						<div class="card-header" id="headingSeven">
							<h5 class="mb-0">
								<button class="btn btn-link" style="padding: 0; margin: auto;"
									data-target="#collapseSeven" data-toggle="collapse"
									aria-expanded="true" aria-controls="collapseSeven">
									<span>Basic Details Summary</span>
								</button>
							</h5>
						</div>

						<div id="collapseSeven" class="collapse show"
							aria-labelledby="headingSeven" data-parent="#accordion7">
							<div class="card-body">
								<!-- ----------------------------- -->
								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Name</label>
											</div>
											<div class="col-8">
												<label class="disp" id="fullnamedisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Mobile</label>
											</div>
											<div class="col-8">
												<label class="disp" id="mobiledisplay"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->

								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">PAN</label>
											</div>
											<div class="col-8">
												<label class="disp" id="pandisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Email</label>
											</div>
											<div class="col-8">
												<label class="disp" id="emaildisplay"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->

								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Gender</label>
											</div>
											<div class="col-8">
												<label class="disp" id="genderdisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Occupation</label>
											</div>
											<div class="col-8">
												<label class="disp" id="occupationdisplay"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->

								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">City of Birth</label>
											</div>
											<div class="col-8">
												<label class="disp" id="birthplacedisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">CKYC</label>
											</div>
											<div class="col-8">
												<label class="disp" id="ckycdisplay"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->

								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Saving</label>
											</div>
											<div class="col-8">
												<label class="disp" id="amountdisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Tenure (Months)</label>
											</div>
											<div class="col-8">
												<label class="disp" id="tenuredisplay"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->

								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Category</label>
											</div>
											<div class="col-8">
												<label class="disp" id="categorydisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Interest frequency</label>
											</div>
											<div class="col-8">
												<label class="disp" id="frequencydisplay"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->

								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Scheme Code</label>
											</div>
											<div class="col-8">
												<label class="disp" id="schemecodedisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Interest Rate %</label>
											</div>
											<div class="col-8">
												<label class="disp" id="intratedisplay"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->

								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">KYC Address</label>
											</div>
											<div class="col-8">
												<label class="disp" id="addressdisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Bank Details</label>
											</div>
											<div class="col-8">
												<label class="disp" id="bankdetailsdisplay"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->
								
								<div class="row">
									<!-- New row  -->
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Nominee Chosen</label>
											</div>
											<div class="col-8">
												<label class="disp" id="nomineechosendisplay"></label>
											</div>
										</div>
									</div>
									<div class="col-md-6 col-lg-6">
										<div class="row">
											<div class="col-4">
												<label class="desc">Tax Resident outside India?</label>
											</div>
											<div class="col-8">
												<label class="disp" id="taxresidentoutsideIndia"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- End of row-  -->
								
								
								
								<!-- ----------------------------- -->
							</div>
						</div>
					</div>
				</div>
				<!--End of accordion7  -->



			</div>
			<!--End of tab  -->

			<div id="progressdisplay"></div>


			<div>
				<p id="tabvalidationerrormsg" class="text-danger"></p>
			</div>

			<hr>
			<div style="overflow: auto;">
				<div style="float: right;">
					<button class="btn btn-sm btn-primary" type="button" id="prevBtn"
						onclick="nextPrev(-1)">Previous</button>
					<button class="btn btn-sm btn-success" type="button" id="nextBtn"
						onclick="nextPrev(1)">Next</button>
				</div>
			</div>
		</form:form>


		<%-- <form:form method="POST"
			action="${pageContext.request.contextPath}/products/capture-frxed-deposit.do"
			modelAttribute="fdform" onsubmit="return valivateFDForm();">




		</form:form> --%>
	</div>
</div>







