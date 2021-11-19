<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form id="regForm"
	action="${pageContext.request.contextPath}/mutual-funds/mfInvestRegister.do"
	method="POST" commandName="mfInvestForm" >

	<!-- Circles which indicates the steps of the form: -->
	<div class="text-center mt-3 mb-3">
		<span class="step"></span> <span class="step"></span> <span
			class="step"></span> <span class="step"></span>
	</div>

	<c:if test="${error != null }">
		<div class="alert alert-danger" style="padding: 5px; font-size: 12px;"
			role="alert">${error }</div>
	</c:if>


	<!-- One "tab" for each step in the form: -->
	<div class="tab">
		<div class="animated fadeIn">

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
			<form:hidden path="invName" id="invName" />
			<form:hidden path="dividendPayMode" val="02" />
			<form:hidden path="customerSignature1" id="signature1" />
			<form:hidden path="customerSignature2" id="signature2" />


			<div>
				<div class="sectionheader">
					<h5 class="topic-style">
						<i class="fas fa-user"></i> Personal Details
					</h5>
				</div>

				<div class="form-row">
					<div class="col-md-6 col-lg-6">
						<div class="md-form form-group mt-0">
							<form:input type="text" class="form-control" maxlength="48"
								id="fname" path="fname" required="required" />
							<label for="fname">First Name</label>

						</div>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="md-form mt-0">
							<form:input type="text" class="form-control" maxlength="48"
								id="lname" path="lname" required="required" placeholder="" />
							<label for="lname">Last name</label>
						</div>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 col-lg-6">
						<div class="md-form form-group mt-0">
							<form:input type="text" path="customerdob" required="required"
								data-provide="datepicker" data-date-start-date="-65y"
								data-date-end-date="-6750d" maxlength="10"
								class="form-control datepicker mb-0" id="investorDOB" />
							<label for="investorDOB">Date of Birth</label> <small
								class="text-muted">Age limit: 18-65 years</small>
						</div>
					</div>

					<div class="col-md-6 col-lg-6 mb-4">
						<label class="mr-4 mb-0"> <form:radiobutton path="gender"
								value="M" id="category1"
								class="option-input radio purchasecategory" /> Male
						</label> <label class="mb-0"> <form:radiobutton path="gender"
								value="F" id="category2"
								class="option-input radio purchasecategory" /> Female
						</label>
					</div>
				</div>


				<div class="form-row mt-3">
					<div class="col-md-6 col-lg-6">
						<div class="md-form form-group mt-0">
							<form:input type="email" class="form-control" maxlength="128"
								id="email" path="email" required="required"
								readonly="${LOGGED =='Y' ? 'true' : 'false' }" />
							<label for="email">Email ID</label>
						</div>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="md-form mt-0">
							<form:input type="text" class="form-control" minlength="10"
								maxlength="10" pattern="[0-9]*" id="mobile" path="mobile"
								required="required"
								readonly="${LOGGED =='Y' ? 'true' : 'false' }" />
							<label for="mobile">Mobile no.</label>
						</div>
					</div>
				</div>


				<div class="form-row">
					<div class="col-md-6 col-lg-6">
						<div class="md-form form-group mt-0">
							<small class="text-primary">Occupation</small>
							<form:select
								class="custom-select custom-select-sm drop-down-custom"
								id="occupation" path="occupation">
								<form:option value="">-- Select --</form:option>
								<form:options items="${occupation}" />
							</form:select>
						</div>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="md-form form-group mt-0 pt-1">
							<form:input type="text" class="form-control" id="pan1"
								style="text-transform: uppercase; margin-top: 13px;"
								maxlength="10" required="required" path="pan1" />
							<label for="pan1">PAN 1</label>
						</div>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 col-lg-6">
						<div class="md-form form-group mt-0">
							<small class="text-primary">Select holding nature</small>
							<form:select
								class="custom-select custom-select-sm drop-down-custom"
								id="holdingMode" path="holdingMode">
								<form:options items="${holingNature}" />
							</form:select>
						</div>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="md-form mt-0">
							<small class="text-primary">Select tax status</small>
							<form:select
								class="custom-select custom-select-sm drop-down-custom"
								id="taxStatus" path="taxStatus">
								<form:options items="${taxStatus}" />
							</form:select>
						</div>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="md-form form-group mt-0">
							<small class="text-primary">Are you already KYC verified?</small>
							<form:select
								class="custom-select custom-select-sm drop-down-custom"
								id="pan1kycverified" path="pan1KycVerified">
								<form:option value="N">No</form:option>
								<form:option value="Y">Yes</form:option>
							</form:select>
							<p class="text-muted" style="font-size: 11px;">
								<sup>*</sup> You must be KYC verified to be able to invest. You
								can check KYC status at <a
									href="https://www.cvlkra.com/kycpaninquiry.aspx"
									target="_blank">CVL</a>, <a href="https://camskra.com/"
									target="_blank">CAMS</a>, etc.
							</p>
						</div>
					</div>
				</div>

				
				<div class="form-row ${mfInvestForm.holdingMode == 'SI' ? 'd-none' : '' }" id="applicant2-box">
					<div class="col-md-6 col-lg-6">
						<div class="md-form form-group mt-0">
							<form:input type="text" class="form-control" id="applicant2Val" maxlength="48"
								path="applicant2" />
							<label for="applicant2Val">Applicant 2</label>
						</div>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="md-form mt-0">
							<form:input type="text" class="form-control" id="pan2"
								style="text-transform: uppercase;" maxlength="10" path="pan2" />
							<label for="pan2">PAN 2</label>
						</div>
					</div>
				</div>


			</div>

			<div>
				<div class="sectionheader">
					<h5 class="topic-style">
						<i class="fas fa-users"></i> Nominee Details
					</h5>
				</div>
				<div class="form-row mb-3">
					<div class="col-md-12 col-lg-12">
						<div class="custom-control custom-radio custom-control-inline">
							<form:radiobutton path="nominee.isNominate" value="N"
								id="customRadioInline1" name="customRadioInline1"
								class="custom-control-input" onclick="setDefaultvalues();" />
							<label class="custom-control-label text-danger"
								for="customRadioInline1">I do not wish to nominate</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<form:radiobutton path="nominee.isNominate" value="Y"
								id="customRadioInline2" name="customRadioInline1"
								class="custom-control-input" onclick="setDefaultvalues();" />
							<label class="custom-control-label text-success"
								for="customRadioInline2"> I wish to nominate</label>
						</div>

					</div>

				</div>

				<div class="showNomineeForm">
					<div class="form-row">
						<div class="col-md-6 col-lg-6">
							<div class="md-form form-group mt-0 pt-1">
								<form:input type="text"
									class="form-control input-select-uniform" id="nomineeName"
									path="nominee.nomineeName" />
								<label for="nomineeName">Nominee Name</label>
							</div>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="md-form mt-0">
								<small class="text-primary">Relation with nominee</small>
								<form:select
									class="custom-select custom-select-sm drop-down-custom"
									id="relation" path="nominee.nomineeRelation">
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
				<h5 class="topic-style">
					<i class="fas fa-university"></i> Bank Account Details
				</h5>
			</div>


			<div class="form-row">
				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">

						<form:input type="tel"
							class="form-control input-select-uniform mb-0" id="accountno"
							path="bankDetails.accountNumber" maxlength="18" />
						<label for="accountno">Account No.</label> <small
							class="text-muted"><sup>*</sup>Account must belong to
							primary account holder.</small>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form mt-0">
						<small class="text-primary">Account Type</small>
						<form:select
							class="custom-select custom-select-sm drop-down-custom"
							id="accountType" required="required"
							path="bankDetails.accountType">
							<form:option value="">Select Account Type</form:option>
							<form:options items="${accountTypes}" />
						</form:select>
					</div>
				</div>
			</div>


			<div class="form-row">
				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">

						<form:input type="text" class="form-control input-select-uniform"
							id="ifsc" path="bankDetails.ifscCode" required="required"
							maxlength="11" />
						<label for="ifsc">IFSC Code</label> <small id="invalidifsc"
							class="text-danger"></small>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form mt-0">
						<small class="text-primary">Bank Name</small>
						<form:select
							class="custom-select custom-select-sm drop-down-custom"
							id="bankName" path="bankDetails.bankName">
							<form:option value="">Select your bank</form:option>
							<form:options items="${bankNames}" />
						</form:select>
					</div>
				</div>
			</div>

			<div class="form-row">
				<form:hidden path="bankDetails.bankBranch" id="branch" />
				<form:hidden path="bankDetails.bankAddress" id="bankAddress" />
				<form:hidden path="bankDetails.bankCity" id="bankCity" />
				<form:hidden path="bankDetails.branchState" id="bankState" />
			</div>


			<!-- ------------------------------------------------- ADDRESS ------------------------------------------------------------ -->
			<div class="sectionheader">
				<h5 class="topic-style">
					<i class="fas fa-address-card"></i> Address Details
				</h5>
			</div>


			<div class="form-row">
				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<small class="text-primary">Address Type</small>
						<form:select
							class="custom-select custom-select-sm drop-down-custom"
							id="addressCategory" path="fatcaDetails.addressType">
							<form:option value="">Select your Address Type</form:option>
							<form:options items="${addressType}" />
						</form:select>
						<small class="text-muted"><sup>*</sup>Address must be KYC
							verified</small>
					</div>
				</div>

			</div>


			<div class="form-row">
				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<form:input type="text" class="form-control" required="required"
							id="address1" path="addressDetails.address1" />
						<label for="address1">Address 1</label>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<form:input type="text" class="form-control" id="address2"
							path="addressDetails.address2" />
						<label for="address2">Address 2</label>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<form:input type="text" class="form-control input-select-uniform"
							id="address3" path="addressDetails.address3" />
						<label for="address3">Address 3</label>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<small class="text-primary">State</small>
						<form:select
							class="custom-select custom-select-sm drop-down-custom"
							id="addState" required="required" path="addressDetails.state">
							<form:option value="" selected="true">Select your State</form:option>
							<form:options items="${states}" />
						</form:select>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<form:input type="text" class="form-control" required="required"
							id="address_city" path="addressDetails.city" />
						<label for="address_city">City</label>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<form:input type="tel" class="form-control" id="pinCode"
							path="addressDetails.pinCode" maxlength="6" pattern="[0-9]*"
							required="required" />
						<label for="pinCode">Pincode</label>
					</div>
				</div>

			</div>

		</div>
	</div>

	<!-- -------------- FATCA CAPTURE ------------------------------------------------------------------------------------------------------------------  -->

	<div class="tab">
		<div class="animated fadeIn">
			<div class="sectionheader">
				<h5 class="topic-style">
					<i class="fas fa-file-signature"></i> FATCA declaration for
					individual
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
				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<form:input type="text" class="form-control input-select-uniform"
							maxlength="32" pattern="[a-zA-Z ]*" id="birthplace"
							path="fatcaDetails.placeOfBirth" />
						<label for="birthplace">Place of Birth</label>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<small class="text-primary">Country of birth</small>
						<form:select
							class="custom-select custom-select-sm drop-down-custom"
							id="countryOfBirth" required="required"
							path="fatcaDetails.countryOfBirth">
							<form:option value="IN" selected="selected">India</form:option>
							<form:options items="${ bsecountryofbirth }" />
						</form:select>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<form:input type="text" class="form-control" id="fathername"
							path="fatcaDetails.fatherName" maxlength="64" />
						<label for="fathername">Father's name</label>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<form:input type="text" class="form-control" id="spousename"
							path="fatcaDetails.spouseName" maxlength="64" />
						<label for="spousename">Spouse Name</label>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<small class="text-primary">Income slab</small>
						<form:select
							class="custom-select custom-select-sm drop-down-custom"
							id="incomeslab" required="required"
							path="fatcaDetails.incomeSlab">
							<form:option value="">--Select--</form:option>
							<form:options items="${incomeSlab}" />
						</form:select>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<small class="text-primary">Wealth source</small>
						<form:select
							class="custom-select custom-select-sm drop-down-custom"
							id="wealthsource" required="required"
							path="fatcaDetails.wealthSource">
							<form:option value="">--Select--</form:option>
							<form:options items="${wealthSource}" />
						</form:select>
					</div>
				</div>

				<div class="col-md-6 col-lg-6">
					<div class="md-form form-group mt-0">
						<small class="text-primary">Political view</small>
						<form:select
							class="custom-select custom-select-sm drop-down-custom"
							id="politicalview" required="required"
							path="fatcaDetails.politicalExposedPerson">
							<form:option value="">--Select--</form:option>
							<form:options items="${politicalView}" />
						</form:select>
					</div>
				</div>
			</div>

			<div class="form-row mb-3"
				style="padding-left: 10px; font-weight: 400; background: #fd6d6d;">
				<div class="custom-control custom-checkbox">
					<form:checkbox class="custom-control-input"
						path="fatcaDetails.usCitizenshipCheck" id="uscitizencheck"
						checked="checked" />
					<label class="custom-control-label" for="uscitizencheck"
						style="color: white;">I am not a citizen of US/Canada</label>
				</div>
			</div>
			
			<h5 class="topic-style">
				Signing form
			</h5>
			
			<div class="form-row">
				<div class="col-md-6 col-lg-6">
				<canvas id="sig-canvas" style="border: 1px solid #969696; cursor: crosshair;box-shadow: 0 0 6px 0px #b7b3b3;">
		 			Browser not supported!
		 		</canvas>
		 		<p class="small text-muted"> 1<sup>st</sup> Applicant signature</p>
						<button type="button" class="btn btn-sm btn-secondary" id="sig-clearBtn">Reset</button>
					
				</div>
				<div class="col-md-6 col-lg-6 ${mfInvestForm.holdingMode == 'SI' ? 'd-none' : '' }" id="sign2-box">
				<canvas id="sig-canvas2" style="border: 1px solid #969696; cursor: crosshair;box-shadow: 0 0 6px 0px #b7b3b3;">
		 			Browser not supported!
		 		</canvas>
		 		<p class="small text-muted"> 2<sup>nd</sup> Applicant signature</p>
						<button type="button" class="btn btn-sm btn-secondary" id="sig-clearBtn2">Reset</button>
				</div>
				
			</div>


		</div>
	</div>
	<!-- ------------ END OF FATCA CAPTURE ---------------------------------------------------------------------------------------------  -->



	<!-- ------------ SUMARY TAB ---------------------------------------------------------------------------------------------  -->
	<div class="tab" style="font-size: 13px;">
		<div class="animated fadeIn">
			<div class="sectionheader1">
				<h6 class="topic-style">
					<i class="fas fa-user"></i> Investor Details
				</h6>
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
					<label class="col-5 col-md-5 col-form-label label_design">KYC
						Verified? </label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
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
					<label class="col-5 col-md-5 col-form-label label_design">DOB
					</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
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

			<div class="row gap_custom ${mfInvestForm.holdingMode == 'SI' ? 'd-none' : '' }" id="secApplicant">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Second
						Applicant </label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="secondapplicantName"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">2<sup>nd</sup> Applicant
						PAN </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="secondapplicantPan"></span></label>
				</div>
			</div>


			<!-- ---------------------------------------------------------------------------------------------------------  -->
			<div class="sectionheader1">
				<hr>
				<h6 class="topic-style">
					<i class="fas fa-users"></i> Nominee Details
				</h6>
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
			<hr>
				<h6 class="topic-style">
					<i class="far fa-address-card"></i> Address Details
				</h6>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Address
						1 </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="add1Display"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Address
						2 </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="add2Display"></span></label>
				</div>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Address
						3 </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
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
					<label class="col-5 col-md-5 col-form-label label_design">Pin
						Code </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
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
			<hr>
				<h6 class="topic-style">
					<i class="fas fa-university"></i> Bank Details
				</h6>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Account
						Holder</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="accHolderDisplay"></span></label>
				</div>
				<!-- <div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Mode
						of payment</label> <label
						class="col-6 col-md-6 col-form-label label_design1"><span
						id="paymentModeDisplay"></span> </label>
				</div> -->
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
			<hr>
				<h6 class="topic-style">
					<i class="fas fa-file-signature"></i> FATCA Declaration
				</h6>
			</div>

			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design pr-0">US/Canada
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
						Name</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
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
						Type</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="occupationtypedisplay"></span></label>
				</div>
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">Political
						View </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
						id="politicalviewdisplay"></span></label>
				</div>
			</div>
			
			<div class="row gap_custom">
				<div class="col-md-6">
					<label class="col-5 col-md-5 col-form-label label_design">1<sup>st</sup> Applicant sign
						</label> <div class="col-6 col-md-6 col-form-label label_design1"><!-- <span
						id="sign1display"></span> -->
						<figure>
							<img
								src=""
								class="img-fluid" id="sign1display" alt="Signature Image">
							<figcaption class="text-muted">
								1<sup>st</sup> Applicant
							</figcaption>
						</figure>
						</div>
				</div>
				<div class="col-md-6 ${mfInvestForm.holdingMode == 'SI' ? 'd-none' : '' }" id="sign2-display">
					<label class="col-5 col-md-5 col-form-label label_design">2<sup>nd</sup> Applicant sign
						</label> <div class="col-6 col-md-6 col-form-label label_design1"><!-- <span
						id="sign2display"></span> -->
						<figure>
							<img src="" class="img-fluid" id="sign2display" alt="Signature Image">
							<figcaption class="text-muted">
								2<sup>nd</sup> Applicant
							</figcaption>
						</figure>
						</div>
				</div>
			</div>

			<!-- FATCA Declaration  -->


			<!-- End of FATCA declaration  -->

			<div class="sectionheader1">
			<hr>
				<h6 class="topic-style">
					<img
						src="<c:url value="${contextcdn}/resources/images/invest/declaration.svg"/>"
						class="img-fluid" style="height: 15px;"> Declarations
				</h6>
			</div>

			<div class="form-row" style="margin-bottom: 5px; padding-left: 5px;">

				<div class="custom-control custom-checkbox">
					<form:checkbox path="ubo" id="customCheck1"
						class="custom-control-input" onchange="validConfirmForm();" />
					<label class="custom-control-label text-muted pt-1" for="customCheck1"
						style="color: black;">By clicking Apply, you confirm that you have read Mutual Fund Investment documents and FreEMI <a href="https://www.freemi.in/terms-conditions/" target="_blank">Terms &amp;
						Conditions</a> and agree to the same</label>
				</div>

			</div>

		</div>

	</div>


	<div class="mb-3 mt-3" style="overflow: auto;">
		<div id="mandateField" class="text-danger mb-3 mt-3"></div>
		<hr>
		<div style="float: right;">
			<button type="button" class="btn btn-secondary" id="prevBtn"
				onclick="nextPrev(-1)">Previous</button>
			<button type="button" class="btn btn-primary" id="nextBtn"
				onclick="nextPrev(1)">Next</button>

		</div>
	</div>
</form:form>