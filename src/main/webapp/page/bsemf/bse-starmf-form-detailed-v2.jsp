<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="fddetailed">

	<div>
		<form:form id="regForm"
			action="${pageContext.request.contextPath}/mutual-funds/register"
			modelAttribute="mfInvestForm" method="POST">

			<div style="text-align: center; margin-bottom: 1rem;">
				<span class="step"></span> <span class="step"></span> <span
					class="step"></span><span class="step"></span>
			</div>

			<div>
				<c:if test="${not empty error }">
					<!-- <span class="text-danger">{error}</span> -->
					<div class="alert alert-danger" role="alert" style="padding: 4px;"
						id="errormsgbox">${ error}</div>
				</c:if>
			</div>
			<div class="tab animated fadeIn">
				<form:hidden path="profileRegRequired" />
				<form:hidden path="customerRegistered" />
				<form:hidden path="invName" id="invName" />
				<form:hidden path="dividendPayMode" val="02" />
				<form:hidden path="customerSignature1" id="signature1" />
				<form:hidden path="customerSignature2" id="signature2" />
				<form:hidden path="holdingMode" id="holdingMode" value="SI" />
				<form:hidden path="mobileverified" id="mobileverified" />
				<form:hidden path="emailverified" id="emailverified" />
				<form:hidden path="nomineeregcomplete" id="nomineeverifyregcomplete"/>

				<div class="sectionheader mb-4">
					<h5 class="topic-style">
						<i class="fas fa-user"></i> Personal Details
					</h5>
				</div>

				<div class="form-row mb-3">
					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class=" form-control invest" maxlength="48"
								id="fname" path="fname" required="required" />
							<label class="label-invest" for="fname">First Name <sup class="text-danger">*</sup>
							</label>
						</div>
					</div>

					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" maxlength="48"
								id="mname" path="mname" required="required" />
							<label class="label-invest" for="mname">Middle Name</label>
						</div>
					</div>

					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" maxlength="48"
								id="lname" path="lname" required="required" placeholder="" />
							<label class="label-invest" for="lname">Last name <sup class="text-danger">*</sup></label>
						</div>
					</div>

				</div>

				<div class="form-row mb-3">
					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="buttonInside form-control invest"
								minlength="10" maxlength="10" pattern="[0-9]*" id="mobile"
								path="mobile" required="required"
								readonly="${LOGGED =='Y' ? 'true' : 'false' }" />
							<label class="label-invest" for="mobile">Mobile no. <sup class="text-danger">*</sup></label>
							<span class="border-0 verifybtn" id="verifymobile"
								data-mdb-toggle="tooltip" data-mdb-placement="top"
								title="Click to send OTP"> <i id="mobileverifyimg"
								class="fas ${mfInvestForm.mobileverified =='Y' ? 'fa-user-check text-success' : 'fa-exclamation-circle text-danger' }"
								style="margin-top: 10px;"></i>

							</span>
						</div>
					</div>

					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="email" class="buttonInside form-control invest"
								maxlength="128" id="email" path="email" required="required"
								readonly="${LOGGED =='Y' ? 'true' : 'false' }" />
							<label class="label-invest"  for="email">Email ID <sup class="text-danger">*</sup></label>
							<span class="border-0 verifybtn" id="verifyemail"
								data-mdb-toggle="tooltip" data-mdb-placement="top"
								title="Click to send OTP"><i id="emailverifyimg"
								class="fas ${mfInvestForm.emailverified =='Y' ? 'fa-user-check text-success' : 'fa-exclamation-circle text-danger' }"
								style="margin-top: 10px;"></i></span>
						</div>
					</div>

					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="pan1"
								style="text-transform: uppercase;" maxlength="10"
								required="required" path="pan1" />
							<label class="label-invest" for="pan1">Primary holder PAN <sup
								class="text-danger">*</sup></label>
						</div>
					</div>
				</div>

				<div class="form-row mb-3">

					<div class="col-md-4 col-lg-4 my-4 pl-3">
						<div class="row mx-auto mw-100 pl-md-3">
							<div class="col-6 col-md-6 form-check mx-auto mt-n4 mt-md-0">
								<label class="label-invest form-check-label" for="genderm">
									<form:radiobutton class="form-check-input form-radio" path="gender" id="genderm" value="M" />
									<strong>Male</strong>
								</label>
							</div>
							<div class="col-6 col-md-6 form-check mt-n4 mt-md-0">
								<label class="label-invest form-check-label" for="genderf">
									<form:radiobutton class="form-check-input form-radio" path="gender" id="genderf" value="F" />
									<strong>Female</strong>
								</label>
							</div>
					</div>
				   </div>

					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" path="customerdob" required="required"
								data-provide="datepicker" data-date-start-date="-65y"
								data-date-end-date="-6750d" maxlength="10"
								class="form-control datepicker invest" id="investorDOB" />
							<label class="label-invest" for="investorDOB">Date of Birth <sup
								class="text-danger">*</sup></label> <small class="text-muted"
								style="font-size: 10px;">Age limit: 18-65 years</small>
						</div>
					</div>
					<div class="col-md-4 col-lg-4">
						<div class="field select-mod mt-0">
							<form:select class="custom-select form-control invest"
								id="occupation" path="occupation">
								<form:option value="">--Select Occupation--</form:option>
								<form:options items="${occupation}" />
							</form:select>
							<!-- <small class="text-primary">Occupation</small> -->
						</div>
					</div>

				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<div class="field select-mod">
							<form:select class="form-control invest" id="mobiledec"
								path="mobiledecflag">
								<form:option value="" disabled="true" selected="true">--Mobile no. owner declaration--</form:option>
								<form:options items="${idbelongsto}" />
							</form:select>
						</div>
						
					</div>
					<div class="form-group col-md-6">
						<!-- <small class="text-primary">Email ID owner declaration</small> -->
						<div class="field select-mod">
							<form:select class="form-control invest" id="emaildeclare"
								path="emaildeclareflag">
								<form:option value="" disabled="true" selected="true">--Email Id. owner declaration--</form:option>
								<form:options items="${idbelongsto}" />
							</form:select>
							
						</div>
					</div>
				</div>


				<div class="form-row">
					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" required="required"
								id="address1" path="addressDetails.address1" />
							<label class="label-invest" for="address1">Address 1 <sup class="text-danger">*</sup></label>
						</div>
					</div>

					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="address2"
								path="addressDetails.address2" />
							<label class="label-invest" for="address2">Address 2 <sup class="text-danger">*</sup></label>
						</div>
					</div>

					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="address3"
								path="addressDetails.address3" />
							<label class="label-invest" for="address3">Address 3</label>
						</div>
					</div>
					<div class="col-md-4 col-lg-4 mb-3 field select-mod">
						<form:select class="custom-select select2size" id="addState"
							required="required" path="addressDetails.state">
							<form:options items="${states}" />
						</form:select>
					</div>

					<div class="col-md-4 col-lg-4 mb-3 field select-mod">
						<form:select class="custom-select select2size"
							path="addressDetails.city" id="address_city">
							<form:options items="${cities}" />
						</form:select>

					</div>

					<div class="col-md-4 col-lg-4 mb-3 field select-mod">
						<form:select class="custom-select select2size mb-3"
							path="addressDetails.pinCode" id="pinCode">
							<form:options items="${pincode}" />
						</form:select>
					</div>
				</div>

				<div class="animated fadeIn"></div>


			</div>
			<!--End of 1st TAB  -->

			<div class="tab">
				<div class="sectionheader mb-5">
					<h5 class="topic-style">
						<i class="fas fa-university"></i> Bank Account Details
					</h5>
					<hr>
				</div>

				<div class="form-row mb-3">
					<div class="col-md-6 col-lg-6">
						<div class="field mt-0">
							<form:input type="tel" class="form-control invest mb-0" id="accountno"
								path="bankDetails.accountNumber" maxlength="18" />
							<label class="label-invest" for="accountno">Account No. <sup
								class="text-danger">*</sup></label> <small class="text-muted"
								style="font-size: 10px;"><sup>*</sup>Account must belong
								to primary account holder.</small>
						</div>
					</div>
					<div class="col-md-6 col-lg-6">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="ifsc"
								path="bankDetails.ifscCode" required="required" maxlength="11" />
							<label class="label-invest" for="ifsc">IFSC Code <sup class="text-danger">*</sup></label>
							<small id="invalidifsc" class="text-danger"></small>
						</div>
					</div>

				</div>

				<div class="form-row mb-3">
					<div class="col-md-6 col-lg-6">
						<p class="mb-3" style="font-size: .9rem;">
							Account type <sup class="text-danger">*</sup>
						</p>
						<div class="row mx-auto mw-100 pl-md-3">
							<div class="col-6 col-md-6 form-check mx-auto my-3 mt-md-0">
								<label class="label-invest form-check-label" for="accountTypesb">
									<form:radiobutton class="form-check-input form-radio" path="bankDetails.accountType" id="accountTypesb" value="SB" />
									<strong>Savings</strong>
								</label>
							</div>
							
							<div class="col-6 col-md-6 form-check mx-auto my-3 mt-md-0">
								<label class="form-check-label" for="accountTypeac">
									<form:radiobutton class="form-check-input form-radio" path="bankDetails.accountType" id="accountTypeac" value="CB" />
									<strong>Savings</strong>
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-6">
						<form:select class="custom-select" id="bankName"
							path="bankDetails.bankName" style="width: 100%;">
							<form:options items="${bankNames}" />
						</form:select>
					</div>

				</div>
				<div class="form-row">
					<form:hidden path="bankDetails.bankBranch" id="branch" />
					<form:hidden path="bankDetails.bankAddress" id="bankAddress" />
					<form:hidden path="bankDetails.bankCity" id="bankCity" />
					<form:hidden path="bankDetails.branchState" id="bankState" />
				</div>

				<div class="form-row">
					<div class="col-md-6 col-lg-6">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="nomineeName"
								path="nominee.nomineeName" />
							<label class="label-invest" for="nomineeName">Nominee Name <sup
								class="text-danger">*</sup></label>
						</div>
					</div>

					<div class="col-md-6 col-lg-6">
						<div class="field select-mod mt-0">
							<form:select class="custom-select form-control invest" id="relation"
								path="nominee.nomineeRelation">
								<option value="">Choose relation</option>
								<form:options items="${nomineeRelation}" />
							</form:select>
						</div>
					</div>
				</div>


				<div class="form-row mb-3">

					<div class="col-md-4 col-lg-4 mb-4">
						<p class="mb-3" style="font-size: .9rem;">
							Nominee Minor? <sup class="text-danger">*</sup>
						</p>
						<div class="row mx-auto mw-100 pl-md-3">
							<div class="col-6 col-md-6 form-check mx-auto my-3 mt-md-0">
								<label class="label-invest form-check-label" for=isNomineeMinorn>
									<form:radiobutton class="form-check-input form-radio" path="nominee.isNomineeMinor" id="isNomineeMinorn" value="N" />
									<strong>No</strong>
								</label>
							</div>
							
							<div class="col-6 col-md-6 form-check mx-auto my-3 mt-md-0">
								<label class="label-invest form-check-label" for="isNomineeMinory">
									<form:radiobutton class="form-check-input form-radio" path="nominee.isNomineeMinor" id="isNomineeMinory" value="Y" />
									<strong>Yes</strong>
								</label>
							</div>
						</div>
					</div>

					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" path="nominee.nomineeDOB"
								required="required" data-provide="datepicker"
								data-date-start-date="-18y" data-date-end-date="-1d"
								maxlength="10" class="form-control invest datepicker mb-0"
								id="nomineedob" />
							<label class="label-invest" for="nomineedob">Minor's DOB </label>
						</div>
					</div>


					<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="nomineeguardian"
								maxlength="64" path="nominee.nomineeGuardian" />
							<label class="label-invest" for="nomineeName">Nominee Guardian</label>
						</div>
					</div>
				</div>
				
				
				
				<div class="form-row mb-3">
				
				<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="nominee1pan"
								maxlength="10" path="nominee.nominee1pan" value="OTP" />
							<label class="label-invest" for="nominee1pan">Nominee PAN</label>
						</div>
				</div>
				
				<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="nominee1guardpan"
								maxlength="10" path="nominee.nominee1guardianpan" value="OTP" />
							<label class="label-invest" for="nominee1guardpan">Nominee Guardian PAN</label>
						</div>
				</div>
				
				<div class="col-md-4 col-lg-4">
						<div class="field mt-0">
							<form:input type="text" class="form-control invest" id="authmode"
								maxlength="64" path="nomineeverifymethod" readonly="readonly" value="OTP" />
							<label class="label-invest" for="authmode">Nominee authentication</label>
						</div>
				</div>
				
				</div>



			</div>
			<!-- End of tab  -->

			<div class="tab">

				<div class="animated fadeIn">
					<div class="sectionheader mb-5">
						<h5 class="topic-style">
							<i class="fas fa-file-signature"></i> FATCA declaration for
							individual
						</h5>
						<div style="color: #353332; font-size: 12px; font-weight: 400;">
							<details>
								<summary>
									<strong style="font-weight: 400; color: #ff682c;">What
										is FATCA Declaration?</strong>
								</summary>
								<small class="text-muted;">Foreign Account Tax
									Compliance Act (FATCA) is a law enacted by the US congress in
									2010. It was introduced to deter US evasion of taxes by US
									citizens by hiding money in foreign countries. The Indian
									Inter-Governmental Agreement (IGA) with the USA for
									implementation of FATCA came into effect from August 31, 2015</small>
							</details>
						</div>
						<hr>
					</div>


					<div class="form-row mb-3">
						<div class="col-md-6 col-lg-6">
							<div class="field mt-0">
								<form:input type="text" class="form-control invest" maxlength="32"
									pattern="[a-zA-Z ]*" id="birthplace"
									path="fatcaDetails.placeOfBirth" />
								<label class="label-invest" for="birthplace">Place of Birth<sup
									class="text-danger">*</sup></label>
							</div>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="md-form mt-0">
								<form:select class="custom-select" id="countryOfBirth"
									required="required" path="fatcaDetails.countryOfBirth"
									style="width: 100%;">
									<%-- <form:option value="IN" selected="selected">India</form:option> --%>
									<form:options items="${ bsecountryofbirth }" />
								</form:select>
							</div>
						</div>
					</div>
					<div class="form-row mb-3">

						<div class="col-md-6 col-lg-6">
							<div class="field mt-0">
								<form:input type="text" class="form-control invest" id="fathername"
									path="fatcaDetails.fatherName" maxlength="64" />
								<label class="label-invest" for="fathername">Father's name</label>
							</div>
						</div>

						<div class="col-md-6 col-lg-6">
							<div class="field mt-0">
								<form:input type="text" class="form-control invest" id="spousename"
									path="fatcaDetails.spouseName" maxlength="64" />
								<label class="label-invest" for="spousename">Spouse Name</label>
							</div>
						</div>
					</div>
					<div class="form-row mb-3">
						<div class="col-md-4 col-lg-4">
							<div class="field select-mod mt-0">
								<small class="text-primary">Income slab <sup
									class="text-danger">*</sup></small>
								<form:select class="custom-select form-control invest" id="incomeslab"
									required="required" path="fatcaDetails.incomeSlab">
									<form:option value="">--Select--</form:option>
									<form:options items="${incomeSlab}" />
								</form:select>
							</div>
						</div>

						<div class="col-md-4 col-lg-4">
							<div class="field select-mod mt-0">
								<small class="text-primary">Wealth source <sup
									class="text-danger">*</sup></small>
								<form:select class="custom-select form-control invest" id="wealthsource"
									required="required" path="fatcaDetails.wealthSource">
									<form:option value="">--Select--</form:option>
									<form:options items="${wealthSource}" />
								</form:select>
							</div>
						</div>

						<div class="col-md-4 col-lg-4">
							<div class="field select-mod">
								<small class="text-primary">Political view <sup
									class="text-danger">*</sup></small>
								<form:select class="custom-select form-control invest" id="politicalview"
									required="required" path="fatcaDetails.politicalExposedPerson">
									<form:option value="">--Select--</form:option>
									<form:options items="${politicalView}" />
								</form:select>
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col-md-6 col-lg-6">
							<canvas id="sig-canvas"
								style="border: 1px solid #969696; cursor: crosshair; box-shadow: 0 0 6px 0px #b7b3b3;">
		 			Browser not supported!
		 		</canvas>
							<p class="small text-muted">
								1<sup>st</sup> Applicant signature
							</p>
							<button type="button" class="btn btn-sm btn-secondary"
								id="sig-clearBtn">Reset</button>

						</div>
						<div
							class="col-md-6 col-lg-6 ${mfInvestForm.holdingMode == 'SI' ? 'd-none' : '' }"
							id="sign2-box">
							<canvas id="sig-canvas2"
								style="border: 1px solid #969696; cursor: crosshair; box-shadow: 0 0 6px 0px #b7b3b3;">
		 			Browser not supported!
		 		</canvas>
							<p class="small text-muted">
								2<sup>nd</sup> Applicant signature
							</p>
							<button type="button" class="btn btn-sm btn-secondary"
								id="sig-clearBtn2">Reset</button>
						</div>
					</div>

					<div class="form-row mb-3">
						<div class="col-md-6 col-lg-6 mb-3">
							<!-- Default switch -->
							<div class="custom-control custom-switch">
								<form:checkbox path="fatcaDetails.usCitizenshipCheck"
									id="uscitizencheck" class="custom-control-input" />
								<label class="custom-control-label" for="uscitizencheck">I
									am a citizen of US/Canada</label>
							</div>

						</div>

					</div>

					<!-- <div class="row">

						<div class="col-md-6 col-lg-6 label-tandc-size px-0">
							<%-- <form:checkbox path="ubo" id="customCheck1"
								class="custom-control-input" onchange="validConfirmForm();" />
							<label class="custom-control-label text-muted pt-1"
								for="customCheck1" style="color: black;">By clicking
								Apply, you confirm that you have read Mutual Fund Investment
								documents and FreEMI <a
								href="https://www.freemi.in/terms-conditions/" target="_blank">Terms
									&amp; Conditions</a> and agree to the same
							</label>  --%>

							<form:checkbox
									path="ubo" class="option-input checkbox" checked="checked"
									id="customCheck1" />
							
							<div class="col-12 pl-small-text text-muted px-0 mb-1" style="font-size: 10px;">
								<i class="fas fa-star-of-life my-note text-danger"></i>
								By clicking Apply, you confirm that you
								have read Mutual Fund Investment documents and FreEMI.
							</div>
							<a href="https://www.freemi.in/terms-conditions/" target="_blank">Terms
								&amp; Conditions</a>
							and agree to the same
						</div>

					</div> -->
					<div class="row">
					
						<div class="col-md-6 col-lg-6 label-tandc-size px-0">
							<input id="customCheck1" name="ubo" checked="checked" class="agreeTerms mb-0" type="checkbox" value="true" />
							<a href="https://www.freemi.in/terms-conditions/" target="_blank" class="label-tandc-size">
								Terms &amp; Conditions</a>
							<input type="hidden" name="_ubo" value="on" />
							<label for="TandC">
								and agree to the same.
							</label>
							<div class="col-12 pl-small-text text-muted px-0 mb-1" style="font-size: 10px;">
								<i class="fas fa-star-of-life my-note text-danger"></i>
								By clicking Apply, you confirm that you
								have read Mutual Fund Investment documents and FreEMI.
							</div>
						</div>
					
					</div>

				</div>
			</div>
			<!--End of tab  -->

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
						<!-- <div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">KYC
								Verified? </label> <label
								class="col-6 col-md-6 col-form-label label_design1"><span
								id="pan1kycverifyDisplay"></span></label>
						</div> -->
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Occupation
							</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
								id="occupationDisplay"></span></label>
						</div>
					</div>

					<div class="row gap_custom">
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Date
								of birth </label> <label
								class="col-6 col-md-6 col-form-label label_design1"><span
								id="DOBDisplay"></span></label>
						</div>
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Gender</label>
							<label class="col-6 col-md-6 col-form-label label_design1"><span
								id="genderDisplay"></span></label>
						</div>
					</div>

					<!-- <div class="row gap_custom">
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Tax
								Status</label> <label
								class="col-6 col-md-6 col-form-label label_design1"><span
								id="taxStatusDisplay"></span></label>
						</div>
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Mode
								of holding</label> <label
								class="col-6 col-md-6 col-form-label label_design1"><span
								id="holdingModeDisplay"></span></label>
						</div>
					</div> -->


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

					<%-- 	<div
						class="row gap_custom ${mfInvestForm.holdingMode == 'SI' ? 'd-none' : '' }"
						id="secApplicant">
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Second
								Applicant </label> <label
								class="col-6 col-md-6 col-form-label label_design1"><span
								id="secondapplicantName"></span></label>
						</div>
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">2<sup>nd</sup>
								Applicant PAN
							</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
								id="secondapplicantPan"></span></label>
						</div>
					</div> --%>


					<!-- ---------------------------------------------------------------------------------------------------------  -->
					<!-- <div class="sectionheader1">
						<hr>
						<h6 class="topic-style">
							<i class="fas fa-users"></i> Nominee Details
						</h6>
					</div> -->

					<!-- <div id="isNomineeSelected">
						<div class="row gap_custom">
							<div class="col-md-6">
								<label class="col-5 col-md-5 col-form-label label_design">Nominee
								</label> <label class="col-6 col-md-6 col-form-label label_design1">
									<span id="nomineeSelected"></span>
								</label>
							</div>
						</div>
					</div> -->

					<div class="NomineeDetailsDisplay">
						<div class="row gap_custom">
							<div class="col-md-6">
								<label class="col-5 col-md-5 col-form-label label_design">Nominee
									Name </label> <label
									class="col-6 col-md-6 col-form-label label_design1"><span
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
						<!-- <div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Account
								Holder</label> <label
								class="col-6 col-md-6 col-form-label label_design1"><span
								id="accHolderDisplay"></span></label>
						</div> -->
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
						<!-- <div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Branch
								Address</label> <label
								class="col-6 col-md-6 col-form-label label_design1"><span
								id="branchAddressDisplay"></span></label>
						</div> -->
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
								Source </label> <label
								class="col-6 col-md-6 col-form-label label_design1"><span
								id="wealthsourcedisplay"></span></label>
						</div>
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Income
								Slab</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
								id="incomeslabdisplay"></span></label>
						</div>
					</div>

					<div class="row gap_custom">
						<!-- <div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Occupation
								Type</label> <label class="col-6 col-md-6 col-form-label label_design1"><span
								id="occupationtypedisplay"></span></label>
						</div> -->
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">Political
								View </label> <label class="col-6 col-md-6 col-form-label label_design1"><span
								id="politicalviewdisplay"></span></label>
						</div>
					</div>

					<div class="row gap_custom">
						<div class="col-md-6">
							<label class="col-5 col-md-5 col-form-label label_design">1<sup>st</sup>
								Applicant
							</label>
							<div class="col-6 col-md-6 col-form-label label_design1">
								<!-- <span
						id="sign1display"></span> -->
								<figure>
									<img src="" class="img-fluid" id="sign1display"
										alt="Signature Image">
									<figcaption class="text-muted">Applicant Signature
									</figcaption>
								</figure>
							</div>
						</div>

						<%-- 
						<div
							class="col-md-6 ${mfInvestForm.holdingMode == 'SI' ? 'd-none' : '' }"
							id="sign2-display">
							<label class="col-5 col-md-5 col-form-label label_design">2<sup>nd</sup>
								Applicant sign
							</label>
							<div class="col-6 col-md-6 col-form-label label_design1">
								<figure>
									<img src="" class="img-fluid" id="sign2display"
										alt="Signature Image">
									<figcaption class="text-muted">
										2<sup>nd</sup> Applicant
									</figcaption>
								</figure>
							</div>
						</div> --%>

					</div>

					<!-- FATCA Declaration  -->


					<!-- End of FATCA declaration  -->

					<%-- 					<div class="sectionheader1">
						<hr>
						<h6 class="topic-style">
							<img
								src="<c:url value="${contextcdn}/resources/images/invest/declaration.svg"/>"
								class="img-fluid" style="height: 15px;"> Declarations
						</h6>
					</div>

					<div class="form-row"
						style="margin-bottom: 5px; padding-left: 5px;">

						<div class="custom-control custom-checkbox">
							<form:checkbox path="ubo" id="customCheck1"
								class="custom-control-input" onchange="validConfirmForm();" />
							<label class="custom-control-label text-muted pt-1"
								for="customCheck1" style="color: black;">By clicking
								Apply, you confirm that you have read Mutual Fund Investment
								documents and FreEMI <a
								href="https://www.freemi.in/terms-conditions/" target="_blank">Terms
									&amp; Conditions</a> and agree to the same
							</label>
						</div>

					</div>
 --%>
				</div>

			</div>


			<div id="progressdisplay"></div>


			<div>
				<p id="tabvalidationerrormsg" class="text-danger"></p>
			</div>

			<hr>
			<div style="overflow: auto;">
				<small id="mandateField" class="text-danger mb-3 mt-3"></small>
				<div style="float: right;">
					<button class="btn btn-sm btn-primary" type="button" id="prevBtn"
						onclick="nextPrev(-1)">Previous</button>
					<button class="btn btn-sm btn-success" type="button" id="nextBtn"
						onclick="nextPrev(1)">Next</button>
				</div>
			</div>
		</form:form>

	</div>
</div>
