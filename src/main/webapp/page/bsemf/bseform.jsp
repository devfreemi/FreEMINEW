<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form id="regForm"
	action="${pageContext.request.contextPath}/mutual-funds/mfInvestRegister.do"
	method="POST" commandName="mfInvestForm">

	<!-- Circles which indicates the steps of the form: -->
	<div style="text-align: center;">
		<span class="step"></span> <span class="step"></span> <span
			class="step"></span>
		<!-- <span class="step"></span> -->
	</div>

	<!-- One "tab" for each step in the form: -->
	<div class="tab">
		<div class="animated fadeIn">

			<c:if test="${error != null }">
				<div>
					<span style="color: red; font-size: 11px;">${error }</span>
				</div>
			</c:if>
			<div>
				<span id="mandateField" style="color: red;"></span>
			</div>

			<div class="sectionheader">
				<h5>Unit Holder Information</h5>
			</div>

			<div style="text-align: center; margin-bottom: 15px;">
				<c:if test="${panStatus.isKYCVerified == 'Y'}">
					<span
						style="color: white; background-color: #47997d; padding: 2px 15px; border-radius: 5px; font-style: oblique;">KYC
						Status- OK </span>
				</c:if>
				<c:if test="${panStatus.isKYCVerified == 'N'}">
					<span
						style="color: white; background-color: #47997d; padding: 2px 15px; border-radius: 5px; font-style: oblique;">KYC
						verified- Complete the process </span>
				</c:if>

			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Mode of
								holding</span>
						</div>
						<form:select class="custom-select" id="holdingMode"
							path="holdingMode">
							<%-- <form:option value="Single" selected="true">Single</form:option> --%>
							<form:options items="${holingNature}" />
						</form:select>
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Tax
								Status</span>
						</div>
						<form:select class="custom-select" id="taxStatus" path="taxStatus">
							<form:options items="${taxStatus}" />
						</form:select>
					</div>
				</div>
			</div>

			<div class="form-row">

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">PAN 1</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="pan" style="text-transform: uppercase;" maxlength="10"
							aria-describedby="basic-addon3" path="pan1" />
					</div>

				</div>
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">PAN 2</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="pan" style="text-transform: uppercase;" maxlength="10"
							aria-describedby="basic-addon3" path="pan2" />
					</div>

				</div>

			</div>


			<div>
				<div class="sectionheader">
					<h5>
						<i class="fas fa-user"></i> Personal Details
					</h5>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Name</span>
							</div>
							<form:input type="text" class="form-control form-control-custom" maxlength="128"
								id="invName" path="invName" aria-describedby="basic-addon3" />
						</div>
					</div>
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Date of
									Birth</span>
							</div>
							<form:input type="date" path="invDOB"
								class="form-control form-control-custom" id="investorDOB" />
						</div>
					</div>

				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Email ID</span>
							</div>
							<form:input type="text" class="form-control form-control-custom" maxlength="128"
								id="email" path="email" aria-describedby="basic-addon3" />
						</div>
					</div>
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Mobile
									no.</span>
							</div>
							<form:input type="text" class="form-control form-control-custom" maxlength="10" pattern="[0-9]*"
								id="mobile" path="mobile" aria-describedby="basic-addon3" />
						</div>
					</div>

				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Dividend
									pay mode </span>
							</div>
							<form:select class="custom-select" id="dividendPayMode"
								path="dividendPayMode">
								<form:options items="${dividendPayMode}" />
							</form:select>
						</div>
					</div>
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Occupation</span>
							</div>
							<form:select class="custom-select" id="occupation"
								path="occupation">
								<form:options items="${occupation}" />
							</form:select>
						</div>
					</div>

				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Applicant
									2</span>
							</div>
							<form:input type="text" class="form-control form-control-custom"
								id="applicant2Val" path="applicant2"
								aria-describedby="basic-addon3" />
						</div>
					</div>

					<div class="form-group col-md-6">
						<div class="form-check">
							<label class="form-check-label"> <form:radiobutton
									class="form-check-input" path="gender" id="gender" value="M" />
								Male
							</label>
						</div>
						<div class="form-check">
							<label class="form-check-label"> <form:radiobutton
									class="form-check-input" path="gender" id="gender" value="F" />
								Female
							</label>
						</div>


					</div>

				</div>


			</div>

			<div>

				<div class="sectionheader">
					<h5>
						<i class="fas fa-users"></i> Nominee Details
					</h5>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">

						<%-- <label> <form:checkbox type="checkbox" path="nominee.isNominate"
												id="nominate" value="Y" checked="checked" /> <span
												class="checkmark"></span> <span
												style="font-size: 12px; color: #21a35e;"> I wish to nominate
											</span>
											</label> --%>
						<div>
							<!-- <span
								style="color: green; position: absolute; margin-left: 20px;">I
								wish to nominate</span> -->
							<div style="width: 10px; padding-top: 7px;">
								<form:radiobutton path="nominee.isNominate" value="Y"
									id="isNominate" onclick="setDefaultvalues();" label="I wish to nominate" />
							</div>
						</div>
						<div>
							<span style="color: red; position: absolute; margin-left: 20px;">I
								do not wish to nominate</span>
							<div style="width: 10px; padding-top: 7px;">
								<form:radiobutton path="nominee.isNominate" value="N"
									id="isNominate" onclick="setDefaultvalues();" />
							</div>
						</div>

					</div>
				</div>

				<div class="showNomineeForm">
					<div class="form-row">
						<div class="form-group col-md-6">
							<div class="input-group mb-1">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon3">Name</span>
								</div>
								<form:input type="text" class="form-control form-control-custom"
									id="nomineeName" path="nominee.nomineeName"
									aria-describedby="basic-addon3" />
							</div>
						</div>
						<div class="form-group col-md-6">

							<div class="input-group mb-1">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon3">Birth
										date</span>
								</div>
								<form:input type="date" path="nominee.nomineeDOB"
									class="form-control form-control-custom" id="nomineeDOB" />
							</div>
							<span style="color: navy; font-size: 11px;">Age 18 - 65
								yrs.</span>
						</div>

					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<div class="input-group mb-1">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon3">Address
									</span>
								</div>
								<form:input type="text" class="form-control form-control-custom"
									id="nomineeAddress1" path="nominee.nomineeAddress1"
									aria-describedby="basic-addon3" />
							</div>
						</div>
						<div class="form-group col-md-6">
							<%-- <div class="input-group mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon3">Address
														2</span>
												</div>
												<form:input type="text" class="form-control form-control-custom"
													id="nomineeAddress2" path="nominee.nomineeAddress2"
													aria-describedby="basic-addon3" />
											</div> --%>

							<div class="input-group mb-1">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon3">Relation</span>
								</div>
								<form:select class="custom-select" id="relation"
									path="nominee.nomineeRelation">
									<option value="" selected="selected">Choose relation</option>
									<option value="SON">Son</option>
									<option value="DAUGHTER">Daughter</option>
									<option value="Spouse">Spouse</option>
									<option value="Parents">Parents</option>
								</form:select>
							</div>

						</div>

					</div>


					<div class="form-row">
						<div class="form-group col-md-6">
							<%-- <div class="form-check">
												<form:checkbox class="form-check-input"
													path="nominee.isNomineeMinor" value="Y" id="gridCheck2" />
												<label class="form-check-label" for="gridCheck2">
													Nominee is a minor </label>
											</div> --%>

							<div>
								<span
									style="color: black; position: absolute; margin-left: 20px;">Not
									minor</span>
								<div style="width: 10px; padding-top: 7px;">
									<form:radiobutton path="nominee.isNomineeMinor" value="N"
										id="isMinor" />
								</div>
							</div>
							<div>
								<span
									style="color: black; position: absolute; margin-left: 20px;">Minor
								</span>
								<div style="width: 10px; padding-top: 7px;">
									<form:radiobutton path="nominee.isNomineeMinor" value="Y"
										id="isMinor" />
								</div>
							</div>

						</div>
						<div class="form-group col-md-6">
							<div class="input-group mb-1">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon3">Guardian
										Name (If minor) </span>
								</div>
								<form:input type="text" class="form-control form-control-custom"
									id="guardian" path="nominee.nomineeGuardian"
									aria-describedby="basic-addon3" />
							</div>
						</div>
					</div>

				</div>

				<div class="sectionheader">
					<h5>
						<i class="fas fa-clipboard-check"></i> Declarations
					</h5>
				</div>

				<div class="form-row" style="margin-bottom: 5px;">
					<div class="form-group col-md-1 col-1" style="padding-top: 7px;">
						<form:checkbox path="ubo" value="Y" />
					</div>
					<div class="form-group col-md-11 col-11 agree_Check"
						style="height: 75px;">
						<span style="font-size: 12px; color: #575353;"> I have read
							the <a
							href="https://mf.adityabirlacapital.com/Pages/Individual/Forms-Downloads/Forms.aspx"
							target="_blank">Key Information Memorandum, Scheme
								Information Document / Statement of Additional Information </a> and
							<a
							href="https://mf.adityabirlacapital.com/_layouts/ABFSG/MF/downloads/Disclaimer.pdf"
							target="_blank">Disclaimer</a> for the scheme in which I am
							investing. <br> In line with regulation under Prevention of
							Money Laundering Act, 2002 (PMLA), I/We hereby confirm that the
							funds for this purchase are being debited from my bank account.

						</span>
					</div>

				</div>

			</div>

		</div>
	</div>


	<!-- --------------------------------- BAnk Details ------------------------------------------  -->
	<div class="tab">
		<div class="animated fadeIn">
			<div class="sectionheader">
				<h5>
					<i class="fas fa-university"></i> Bank Account Details
				</h5>
			</div>
			<!-- 	<fieldset class="form-group">
								<div class="row">
									<legend class="col-form-label label_design col-md-6 pt-0">Mode of Payment</legend>
									<label for="accountnum"
										class="col-md-6 col-form-label label_design">Mode of
										Payment</label>
									<div class="col-md-6">
										<div class="form-check">
											<input class="form-check-input" type="radio"
												name="gridRadios" id="gridRadios1"
												value="Register As Biller" checked> <label
												class="form-check-label" for="gridRadios1"> Register
												As Biller </label>
											<div style="font-size: 11px; color: #f72e53;">SIP will
												begin after 15 days</div>
										</div>
									</div>
								</div>
							</fieldset> -->

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text">Account no.</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="accountno" path="bankDetails.accountNumber"
							aria-describedby="basic-addon3" />
					</div>
					<span style="font-size: 10px; color: #2f79c7;"><sup>*</sup>Account
						must belong to investor.</span>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Account
								Type</span>
						</div>
						<form:select class="custom-select" id="accountType"
							path="bankDetails.accountType">
							<form:option value="" selected="true">Select Account Type</form:option>
							<form:options items="${accountTypes}" />
						</form:select>
					</div>
				</div>



			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text">IFSC Code</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="ifsc" path="bankDetails.ifscCode"
							aria-describedby="basic-addon3" maxlength="11" />
					</div>
					<span id="invalidifsc" style="color: red; font-size: 11px;"></span>
				</div>
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Bank Name</span>
						</div>
						<form:select class="custom-select" id="bankName"
							path="bankDetails.bankName">
							<form:option value="" selected="true">Select your bank</form:option>
							<%-- <form:option value="ICICI">ICICI Bank</form:option> --%>
							<form:options items="${bankNames}" />

						</form:select>
					</div>


				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Branch</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="branch" path="bankDetails.bankBranch"
							aria-describedby="basic-addon3" readonly="true" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Address</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="bankAddress" path="bankDetails.bankAddress"
							aria-describedby="basic-addon3" readonly="true" />
					</div>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">City</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="bankCity" path="bankDetails.bankCity"
							aria-describedby="basic-addon3" readonly="true" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text">State</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="bankState" path="bankDetails.branchState"
							aria-describedby="basic-addon3" readonly="true" />
					</div>
				</div>
			</div>

			<div class="sectionheader">
				<h5>Address Details</h5>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Address 1</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="address1" path="addressDetails.address1"
							aria-describedby="basic-addon3" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Address 2</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="address1" path="addressDetails.address2"
							aria-describedby="basic-addon3" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Address 3</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="address3" path="addressDetails.address3"
							aria-describedby="basic-addon3" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">City</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="address city" path="addressDetails.city"
							aria-describedby="basic-addon3" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Pin Code</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="pinCode" path="addressDetails.pinCode"
							aria-describedby="basic-addon3" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">State</span>
						</div>
						<form:select class="custom-select" id="bankName"
							path="addressDetails.state">
							<form:option value="" selected="true">Select your State</form:option>
							<form:options items="${states}" />

						</form:select>
					</div>
				</div>

			</div>



		</div>
	</div>

	<!-- ------------ SUMARY TAB ---------------------------------------------------------------------------------------------  -->
	<div class="tab">
		<div class="animated fadeIn">
			<div class="sectionheader">
				<h5>
					<i class="fas fa-user"></i> Investor Details
				</h5>
			</div>
			<!-- ---------------------------------------------------------------------------------------------------------  -->
			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Investor
						name</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="nameDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">PAN
						No</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="PANDisplay" style="text-transform: uppercase;"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Date
						of birth (DOB)</label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="DOBDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Gender</label>
					<label class="col-6 col-md-6 col-form-label label_design1"><span
						id="genderDisplay" style="text-transform: uppercase;"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Tax
						Status</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="taxStatusDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Mode
						of holding</label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="holdingModeDisplay"></span></label>
				</div>
			</div>


			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Email
						ID</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="emailDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Mobile
						No.</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="mobileDisplay"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Income
						slab </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="incomeslabDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Occupation
					</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="occupationDisplay"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Place
						of Birth </label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="birthPlaceDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Marital
						status </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="maritalDisplay"></span></label>
				</div>

			</div>

			<!-- ---------------------------------------------------------------------------------------------------------  -->
			<div class="sectionheader">
				<h5>
					<i class="fas fa-users"></i> Nominee Details
				</h5>
			</div>

			<div id="isNomineeSelected">
				<div class="row gap_custom">
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Nominee
						</label> <label class="col-6 col-md-6 col-form-label label_design1">
							<span id="nomineeSelected"></span>
						</label>
					</div>
				</div>
			</div>

			<div class="NomineeDetailsDisplay">

				<div class="row gap_custom">
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Name
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="nomineeNameDisplay"></span></label>
					</div>
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Date
							of Birth </label> <label
							class="col-6 col-md-6 col-form-label label_design1"><span
							id="nomineeDOBDisplay"></span></label>
					</div>
				</div>


				<div class="row gap_custom">
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Address
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="nomineeaddress1Display"></span></label>
					</div>
					<!-- <div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Address
											2 </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeaddress2Display"></span></label>
									</div> -->

					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Relation
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="nomineeRelationDisplay"></span></label>
					</div>
				</div>

				<!-- <div class="row gap_custom">
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">City
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeCityDisplay"></span></label>
									</div>
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">State
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeStateDisplay"></span></label>
									</div>
								</div> -->

				<!-- <div class="row gap_custom">
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Relation
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineeRelationDisplay"></span></label>
									</div>
									<div class="col-md-6">
										<label class="col-5 col-md-5 col-form-label label_design">Pecentage
										</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
											id="nomineePercentageDisplay"></span></label>
									</div>
								</div> -->



			</div>

			<!-- End of nominee selecttion -->

			<!-- ---------------------------------------------------------------------------------------------------------  -->
			<div class="sectionheader">
				<h5>
					<i class="fas fa-chart-line"></i> Investment Details
				</h5>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Mutual
						Fund Name </label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="fundNameDisplay">${mfInvestForm.selectedFund.fundName }</span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Scheme
						Type </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="schemeTypeDisplay">${mfInvestForm.selectedFund.schemeOption }</span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Scheme
						Name </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="schemeNameDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Amount
					</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="investAmountDisplay">${mfInvestForm.selectedFund.monthlySavings }</span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Invest
						Frequency </label> <label
						class="col-6 col-md-6 col-form-label label_design1"> <c:if
							test="S{mfInvestForm.mfInvestDates.invFrequency == '12'}">
							<span id="investmentFrequency">Monthly</span>
						</c:if> <c:if test="S{mfInvestForm.mfInvestDates.invFrequency == '13'}">
							<span id="investmentFrequency">Lumpsum</span>
						</c:if>
					</label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Investment
						date </label> <label class="col-6 col-md-6 col-form-label label_design1">
						<c:if test="${mfInvestForm.investmentType != 'TARGET_PLAN' }">
							<span id="monthlyInvestDate"></span>
						</c:if> <c:if test="${mfInvestForm.investmentType == 'TARGET_PLAN' }">
							<span id="monthlyInvestDate"></span>
						</c:if>
					</label>
				</div>
			</div>

			<c:if test="${mfInvestForm.investmentType != 'TARGET_PLAN' }">
				<div class="row gap_custom">
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Start
							from </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="startFrom"></span></label>
					</div>
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Till
							date </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="investTillDate"></span></label>
					</div>
				</div>
			</c:if>



			<!-- ---------------------------------------------------------------------------------------------------------  -->
			<div class="sectionheader">
				<h5>
					<i class="fas fa-university"></i> Bank Details
				</h5>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Account
						Holder</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="accHolderDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Mode
						of payment</label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="paymentModeDisplay"></span> </label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Account
						Type</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="accountTypeDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Account
						No.</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="accNumberDisplay"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Bank
						Name </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="bankNameDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Branch</label>
					<label class="col-6 col-md-6 col-form-label label_design1"><span
						id="branchDisplay"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Branch
						Address</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="branchAddressDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">IFSC
						Code</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="ifscDisplay"></span></label>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">City
					</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="bankCityDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">State</label>
					<label class="col-6 col-md-6 col-form-label label_design1"><span
						id="branchStateDisplay"></span></label>
				</div>
			</div>


		</div>

	</div>

	<!-- <div class="tab">
					<div>
					<span style="color: black;">Please wait while details are processed...</span>
					</div>
					</div> -->

	<!-- End of investor details tab  -->



	<div style="overflow: auto;">
		<div id="display_progress" class="progress_tag" style="display: none;">
			<h5>Please wait while we process your request...</h5>
			<div style="text-align: center;">
				<div class="lds-ellipsis">
					<div></div>
					<div></div>
					<div></div>
					<div></div>
				</div>
			</div>
			<h6>Do not Refresh the page</h6>
		</div>

		<div style="float: right;">
			<button type="button" class="btn btn-secondary btn-sm" id="prevBtn"
				onclick="nextPrev(-1)">Previous</button>
			<button type="button" class="btn btn-primary btn-sm" id="nextBtn"
				onclick="nextPrev(1)">Next</button>
		</div>
	</div>
</form:form>