<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form id="regForm"
	action="${pageContext.request.contextPath}/mutual-funds/mfInvestRegister.do"
	method="POST" commandName="mfInvestForm">

	<!-- Circles which indicates the steps of the form: -->
	<div style="text-align: center;">
		<span class="step"></span> <span class="step"></span> <span
			class="step"></span> <span class="step"></span>
	</div>

	<c:if test="${error != null }">
		<div class="alert alert-danger" style="padding: 5px; font-size: 12px;"
			role="alert">${error }</div>
	</c:if>
	<div>
		<span id="mandateField" style="color: red;"></span>
	</div>

	<!-- One "tab" for each step in the form: -->
	<div class="tab">
		<div class="animated fadeIn">

			<div class="sectionheader">
				<h5>
					<i class="fas fa-info-circle"></i> Unit Holder Information
				</h5>
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

			<form:hidden path="profileRegRequired" />
			<form:hidden path="customerRegistered" />

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Mode of
								holding</span>
						</div>
						<form:select class="custom-select" id="holdingMode"
							path="holdingMode">
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
							<span class="input-group-text" id="basic-addon3">Are you
								already KYC verified?<sup style="color: red">*</sup>
							</span>
						</div>
						<form:select class="custom-select" id="pan1kycverified"
							path="pan1verified">
							<form:option value="N">No</form:option>
							<form:option value="Y">Yes</form:option>
						</form:select>
					</div>


				</div>

				<div class="form-group col-md-6">
					<div
						style="color: black; font-size: 11px; background: #feffc1; padding: 5px; border-radius: 3px;">
						<span>You must be KYC verified to be able to invest. You
							can check KYC status at <a
							href="https://www.cvlkra.com/kycpaninquiry.aspx" target="_blank">CVL</a>,
							<a href="https://camskra.com/" target="_blank">CAMS</a>, etc.
						</span> <br>For Non-KYC, offline process will be generated to help
						complete your KYC.
					</div>

				</div>


			</div>

			<div class="form-row">

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">PAN 1<sup
								style="color: red">*</sup></span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="pan1" style="text-transform: uppercase;" maxlength="10"
							required="required" aria-describedby="basic-addon3" path="pan1" />
					</div>

				</div>
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">PAN 2</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="pan2" style="text-transform: uppercase;" maxlength="10"
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
								<span class="input-group-text" id="basic-addon3">Name<sup
									style="color: red">*</sup></span>
							</div>
							<form:input type="text" class="form-control form-control-custom"
								maxlength="128" id="invName" path="invName" required="required"
								aria-describedby="basic-addon3" />
						</div>
					</div>
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Date of
									Birth<sup style="color: red">*</sup>
								</span>
							</div>
							<form:input type="text" path="invDOB" required="required" data-provide="datepicker"  data-date-start-date="-65y" data-date-end-date="-6750d"
								maxlength="10" class="form-control form-control-custom datepicker"
								id="investorDOB" />
						</div>
						<span style="font-size: 10px; color: #ff1111;"><sup>*</sup>Age
							limit: 18-65 years</span>
					</div>

				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Email ID<sup
									style="color: red">*</sup></span>
							</div>
							<form:input type="email" class="form-control form-control-custom"
								maxlength="128" id="email" path="email" required="required"
								aria-describedby="basic-addon3" />
						</div>
					</div>

					<c:choose>
						<c:when test="${LOGGED == 'Y' }">
							<div class="form-group col-md-6">
								<div class="input-group mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon3">Mobile
											no.<sup style="color: red">*</sup>
										</span>
									</div>
									<form:input type="text"
										class="form-control form-control-custom" maxlength="10"
										pattern="[0-9]*" id="mobile" path="mobile" required="required"
										aria-describedby="basic-addon3" readonly="true" />
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group col-md-6">
								<div class="input-group mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon3">Mobile
											no.<sup style="color: red">*</sup>
										</span>
									</div>
									<form:input type="text"
										class="form-control form-control-custom" maxlength="10"
										pattern="[0-9]*" id="mobile" path="mobile" required="required"
										aria-describedby="basic-addon3" />
								</div>
							</div>
						</c:otherwise>

					</c:choose>


				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<div class="input-group mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Dividend
									pay mode<sup style="color: red">*</sup>
								</span>
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
								<span class="input-group-text" id="basic-addon3">Occupation<sup
									style="color: red">*</sup></span>
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
						<div class="custom-control custom-radio custom-control-inline">
							<form:radiobutton path="gender" value="M" id="customRadioInline3"
								name="customRadioInline3" class="custom-control-input" />
							<label class="custom-control-label" for="customRadioInline3">Male</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<form:radiobutton path="gender" value="F" id="customRadioInline4"
								name="customRadioInline4" class="custom-control-input" />
							<label class="custom-control-label" for="customRadioInline4">Female</label>
						</div>

					</div>
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

				</div>


			</div>

			<div>

				<div class="sectionheader">
					<h5>
						<i class="fas fa-users"></i> Nominee Details
					</h5>
				</div>
				<div class="form-row">

					<div class="col-md-12 col-lg-12">
						<div class="custom-control custom-radio custom-control-inline">
							<form:radiobutton path="nominee.isNominate" value="N"
								id="customRadioInline1" name="customRadioInline1"
								class="custom-control-input" onclick="setDefaultvalues();" />
							<label class="custom-control-label" for="customRadioInline1"
								style="color: red">I do not wish to nominate</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<form:radiobutton path="nominee.isNominate" value="Y"
								id="customRadioInline2" name="customRadioInline1"
								class="custom-control-input" onclick="setDefaultvalues();" />
							<label class="custom-control-label" for="customRadioInline2"
								style="color: green;"> I wish to nominate</label>
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
									<span class="input-group-text" id="basic-addon3">Relation</span>
								</div>
								<form:select class="custom-select" id="relation"
									path="nominee.nomineeRelation">
									<option value="">Choose relation</option>
									<form:options items="${nomineeRelation}" />
								</form:select>
							</div>
						</div>

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
		

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text">Account no.<sup
								style="color: red">*</sup></span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="accountno" path="bankDetails.accountNumber" maxlength="18"
							aria-describedby="basic-addon3" />
					</div>
					<span style="font-size: 10px; color: #d05c41;"><sup>*</sup>Account
						must belong to primary account holder.</span>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Account
								Type</span>
						</div>
						<form:select class="custom-select" id="accountType"
							required="required" path="bankDetails.accountType">
							<form:option value="">Select Account Type</form:option>
							<form:options items="${accountTypes}" />
						</form:select>
					</div>
				</div>



			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text">IFSC Code<sup
								style="color: red">*</sup></span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="ifsc" path="bankDetails.ifscCode" required="required"
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
							<form:option value="">Select your bank</form:option>
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
		
		
			<!-- ------------------------------------------------- ADDRESS ------------------------------------------------------------ -->
			<div class="sectionheader">
				<h5>
					<i class="fas fa-address-card"></i> Address Details
				</h5>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Address
								Type</span>
						</div>
						<form:select class="custom-select" id="addressCategory"
							path="fatcaDetails.addressType">
							<form:option value="">Select your Address Type</form:option>
							<form:options items="${addressType}" />

						</form:select>

					</div>
					<span style="color: #e40000; font-size: 11px; font-weight: 400;"><sup>*</sup>Address
						must be KYC verified</span>

				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Address 1<sup
								style="color: red">*</sup></span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							required="required" id="address1" path="addressDetails.address1"
							aria-describedby="basic-addon3" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Address 2</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="address2" path="addressDetails.address2"
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
							<span class="input-group-text" id="basic-addon3">City<sup
								style="color: red">*</sup></span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							required="required" id="address_city" path="addressDetails.city"
							aria-describedby="basic-addon3" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Pin Code<sup
								style="color: red">*</sup></span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="pinCode" path="addressDetails.pinCode" maxlength="6"
							pattern="[0-9]*" required="required"
							aria-describedby="basic-addon3" />
					</div>
				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">State<sup
								style="color: red">*</sup></span>
						</div>
						<form:select class="custom-select" id="addState"
							required="required" path="addressDetails.state">
							<form:option value="" selected="true">Select your State</form:option>
							<form:options items="${states}" />

						</form:select>
					</div>
				</div>

			</div>


		</div>
	</div>

	<!-- -------------- FATCA CAPTURE ------------------------------------------------------------------------------------------------------------------  -->

	<div class="tab">
		<div class="animated fadeIn">
			<div class="sectionheader">
				<h5>
					<i class="fas fa-file-signature"></i> FATCA DECLARATION FOR
					INDIVIDUAL
				</h5>
			</div>

			<div style="color: #353332; font-size: 12px; font-weight: 400;">
				<details>
					<summary>
						<strong style="font-weight: 400; color: #ff682c;">What is
							FATCA Declaration?</strong>
					</summary>
					<p>Foreign Account Tax Compliance Act (FATCA) is a law enacted
						by the US congress in 2010. It was introduced to deter US evasion
						of taxes by US citizens by hiding money in foreign countries. The
						Indian Inter-Governmental Agreement (IGA) with the USA for
						implementation of FATCA came into effect from August 31, 2015</p>
				</details>

				<hr>
			</div>

			

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text">Place of Birth<sup
								style="color: red">*</sup></span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							maxlength="32" pattern="[a-zA-Z ]*" id="birthplace"
							path="fatcaDetails.placeOfBirth" aria-describedby="basic-addon3" />
					</div>

				</div>

				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Wealth Source<sup
								style="color: red">*</sup></span>
						</div>
						<form:select class="custom-select" id="wealthsource"
							required="required" path="fatcaDetails.wealthSource">
							<form:option value="">--Select--</form:option>
							<form:options items="${wealthSource}" />
						</form:select>
					</div>
				</div>



			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text">Father's Name</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="fathername" path="fatcaDetails.fatherName"
							aria-describedby="basic-addon3" maxlength="64" />
					</div>
				</div>
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text">Spouse Name</span>
						</div>
						<form:input type="text" class="form-control form-control-custom"
							id="spousename" path="fatcaDetails.spouseName"
							aria-describedby="basic-addon3" maxlength="64" />
					</div>


				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Income Slab<sup
								style="color: red">*</sup></span>
						</div>
						<form:select class="custom-select" id="incomeslab"
							required="required" path="fatcaDetails.incomeSlab">
							<form:option value="">--Select--</form:option>
							<form:options items="${incomeSlab}" />
						</form:select>
					</div>
				</div>
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Occupation Type<sup
								style="color: red">*</sup></span>
						</div>
						<form:select class="custom-select" id="occupationType"
							required="required" path="fatcaDetails.occupationType">
							<form:option value="">--Select--</form:option>
							<form:options items="${occupationType}" />
						</form:select>
					</div>
				</div>
			</div>


			<div class="form-row">
				<div class="form-group col-md-6">
					<div class="input-group mb-1">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon3">Political View Type<sup
								style="color: red">*</sup> </span>
						</div>
						<form:select class="custom-select" id="politicalview"
							required="required" path="fatcaDetails.politicalExposedPerson">
							<form:option value="">--Select--</form:option>
							<form:options items="${politicalView}" />
						</form:select>
					</div>
				</div>

				
			</div>
			
			
			<div class="form-row mb-3"
				style="padding-left: 10px; font-weight: 400;background: #fd6d6d;">
				<div class="custom-control custom-checkbox">
					<form:checkbox class="custom-control-input"
						path="fatcaDetails.usCitizenshipCheck" id="uscitizencheck" />
					<label class="custom-control-label" for="uscitizencheck" style="color: white;">I
						am not a citizen of US/Canada</label>
				</div>
			</div>

		</div>
	</div>
	<!-- ------------ END OF FATCA CAPTURE ---------------------------------------------------------------------------------------------  -->



	<!-- ------------ SUMARY TAB ---------------------------------------------------------------------------------------------  -->
	<div class="tab">
		<div class="animated fadeIn">
			<div class="sectionheader1">
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
					<label class="col-5 col-md-5 col-form-label label_design">KYC Verified? </label> 
					<label class="col-6 col-md-6 col-form-label label_design1"><span
						id="pan1kycverifyDisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Occupation
					</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="occupationDisplay"></span></label>
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
						id="genderDisplay"></span></label>
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


			<div class="row gap_custom" id="secApplicant">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Second
						Applicant </label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="secondapplicantName"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Applicant
						PAN </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="secondapplicantPan"></span></label>
				</div>
			</div>


			<!-- ---------------------------------------------------------------------------------------------------------  -->
			<div class="sectionheader1">
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
						<label class="col-5 col-md-5 col-form-label label_design">Relation
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="nomineeRelationDisplay"></span></label>
					</div>
				</div>

			</div>

			<!-- End of nominee selecttion -->
			
			
			<!-- ---------------------------------------------------------------------------------------------------------  -->
			<div class="sectionheader1">
				<h5>
					<i class="far fa-address-card"></i> Address Details
				</h5>
			</div>

				<div class="row gap_custom">
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Address 1
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="add1Display"></span></label>
					</div>
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Address 2
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="add2Display"></span></label>
					</div>
				</div>
				
				<div class="row gap_custom">
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Address 3
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="add3Display"></span></label>
					</div>
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">City
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="addCityDisplay"></span></label>
					</div>
				</div>
				
				<div class="row gap_custom">
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">Pin Code
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="addpincodeDisplay"></span></label>
					</div>
					<div class="col-md-6">
						<label class="col-5 col-md-5 col-form-label label_design">State
						</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
							id="addSateDisplay"></span></label>
					</div>
				</div>


			<!-- End of Address Details -->
			

			<!-- Bank info summary  -->
			<div class="sectionheader1">
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

			<!-- End of Bank info summary  -->
			<div class="sectionheader1">
				<h5>
					<i class="fas fa-file-signature"></i> FATCA Declaration
				</h5>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">US/Canada
						Citizen </label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="uscitizenshipcheckdisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Place
						of Birth</label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="birthplacedisplay"></span> </label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Father's
						Name</label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="fathernamedisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Spouse
						Name </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="spousenamedisplay"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Wealth
						Source </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="wealthsourcedisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Income
						Slab</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="incomeslabdisplay"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Occupation
						Type</label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="occupationtypedisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Political
						View </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="politicalviewdisplay"></span></label>
				</div>
			</div>

			<!-- FATCA Declaration  -->


			<!-- End of FATCA declaration  -->

			<div class="sectionheader1">
				<h5>
					<img
						src="<c:url value="${contextPath}/resources/images/invest/declaration.svg"/>"
						class="img-fluid" style="height: 15px;"> Declarations
				</h5>
			</div>

			<div class="form-row" style="margin-bottom: 5px; padding-left: 5px;">

				<div class="custom-control custom-checkbox">
					<form:checkbox path="ubo" id="customCheck1"
						class="custom-control-input" onchange="validConfirmForm();" />
					<label class="custom-control-label" for="customCheck1" style="color: black;">I
						agree to the terms &amp; conditions </label>
				</div>

			</div>

		</div>

	</div>


	<div style="overflow: auto;">
		<div id="display_progress" class="progress_tag" style="display: none;">
			<h5>Please wait while we process your request...</h5>
			<div style="text-align: center;">
				<img src="<c:url value="${contextPath}/resources/images/invest/transacting.svg"/>"class="img-fluid" style=" height: 8rem;">
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