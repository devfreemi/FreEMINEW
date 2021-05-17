<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
					<form:form
						action="${pageContext.request.contextPath}/mutual-funds/mfPurchaseConfirm.do"
						method="POST" commandName="selectedFund">
						<h5 class="mb-4 border-bottom border-warning text-secondary" style="font-weight: 500">
							<img
								src="<c:url value="${contextcdn}/resources/images/invest/investor-2.svg"/>"
								alt="Investor icon" style="height: 1.5rem;"> Investor
							Details
						</h5>
						<form:hidden path="clientID" />
						<form:hidden path="transactionID" />
						<form:hidden path="schemeOptionType" id="schemetypeid" />

						
						<div class="form-group row">
							<label for="custName" class="col-sm-5 col-form-label text-default">Investor
								Name</label>
							<div class="col-sm-7">
								<div class="md-form mt-0 mb-0">
									<form:input path="investorName" class="form-control-plaintext form-control-sm"
										id="custName" readonly="true" />
								</div>
							</div>
						</div>

						<div class="form-group row">
							<label for="mobileid" class="col-sm-5 col-form-label text-default">Mobile</label>
							<div class="col-sm-7">
								<div class="md-form mt-0 mb-0">
									<form:input path="mobile" class="form-control-plaintext form-control-sm"
										id="mobileid" readonly="true" />
								</div>
							</div>
						</div>

						
								<h5 class="mb-4 border-bottom border-warning text-secondary" style="font-weight: 500">
								<img
								src="<c:url value="${contextcdn}/resources/images/invest/cart-2.svg"/>" class="img-fluid prepend"
								alt="Cart icon" style="height: 1.5rem;">
							 Purchase Details
						</h5>
						
					
						<div class="form-group row">
							<label for="folioid" class="col-sm-5 col-form-label text-default">Portfolio
								no.</label>
							<div class="col-sm-7">
								<div class="md-form mt-0 mb-0">
									<form:select path="portfolio" id="folioid"
										class="form-control form-control-sm">
										<form:option value="NEW" selected="selected">NEW</form:option>
										<form:options items="${amcPortFolio }" />
									</form:select>
								</div>
							</div>
						</div>

						<div class="form-group row">
							<label for="schemeName" class="col-sm-5 col-form-label text-default">Scheme
								Name</label>
							<div class="col-sm-7">
								<div class="md-form mt-0 mb-0">
									<form:input path="schemeName" readonly="true"
										class="form-control-plaintext form-control-sm" id="schemeName" />
								</div>
							</div>
						</div>


						
						<form:hidden path="schemeCode" />
						<form:hidden path="reinvSchemeCode" />

					

						<div class="form-group row mb-4">
							<label for="schemeName" class="col-sm-5 col-form-label text-default">Fund
								category</label>
							<div class="col-sm-7">
								<div
									class="custom-control form-control-sm custom-checkbox custom-control-inline">
									<form:radiobutton path="invCategory" value="Z"
										id="growthCategory" name="catval" class="custom-control-input" />
									<label class="custom-control-label" for="growthCategory">Growth</label>
								</div>

								<c:if test="${not empty selectedFund.reinvSchemeCode}">
									<div
										class="custom-control form-control-sm custom-checkbox custom-control-inline">
										<form:radiobutton path="invCategory" value="Y"
											id="reinvestcategory" name="catval"
											class="custom-control-input" />
										<label class="custom-control-label" for="reinvestcategory">
											Dividend Re-investment</label>
									</div>
								</c:if>
							</div>
						</div>

				
				<div class="form-group row">
							<label for="investype" class="col-5 col-form-label text-default">Investment
								Type</label>
							<div class="col-7">
								<div class="md-form mt-0 mb-0">
									<form:input path="investype" readonly="true"
										class="form-control-plaintext form-control-sm" id="invtype" />
								</div>
							</div>
						</div>

						<div class="form-group row">
							<label for="invAmount" class="col-5 col-form-label text-default">Investment
								Amount</label>
							<div class="col-7">
								<div class="md-form mt-0 mb-0">
									<form:input path="investAmount"
										class="form-control form-control-sm" id="invAmount" />
								</div>
							</div>
						</div>

					
						


						<c:if test="${selectedFund.investype == 'SIP' }">
							
							<div class="form-group row">
								<label for="schemeDate" class="col-5 col-form-label text-default">Monthly
									SIP date</label>
								<div class="col-7">
									<div class="md-form mt-0 mb-0">
										<form:input path="sipDate" readonly="true"
											class="form-control-plaintext" id="schemeDate" />
									</div>
								</div>
							</div>


							<div class="form-group row">
								<label for="schemeDate" class="col-sm-5 col-form-label text-default">SIP start from</label>
								<div class="col-sm-7">
									<div class="md-form mt-0 mb-0">
										<div class="row">
											<!-- Grid column -->
											<div class="col">
												<div class="md-form mt-0 mb-0">
													<form:select class="custom-select form-control-sm"
										id="sipmonth" path="sipStartMonth">
										<form:options items="${calendarmonths}" />
									</form:select>
												</div>
											</div>
											<div class="col">
												<!-- Material input -->
												<div class="md-form mt-0 mb-0">
													<form:select class="custom-select form-control-sm" id="sipyear"
										path="sipStartYear">
										<form:options items="${sipyear}" />
									</form:select>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>



							<div class="form-group row">
								<label for="noOfInstallments" class="col-5 col-form-label text-default">No. of installments (Monthly)</label>
								<div class="col-7">
									<div class="md-form mt-0 mb-0">
										<form:input path="noOfInstallments" mandatory="true"
										class="form-control form-control-sm" id="noOfInstallments" />
									</div>
								</div>
							</div>
							
						
							<div class="form-group row">
								<label for="bankName" class="col-sm-5 col-form-label text-default">Registered Bank for SIP</label>
								<div class="col-sm-7">
									<div class="md-form mt-0 mb-0">
									<input type="text" class="form-control-plaintext" readonly="readonly" id="bankName" value="${bankname }" />
									
									</div>
								</div>
							</div>
							
						
							
							<div class="form-group row">
								<label for="accno" class="col-sm-5 col-form-label text-default">Registered Bank Account </label>
								<div class="col-sm-7">
									<div class="md-form mt-0 mb-0">
									<input type="text"class="form-control-plaintext" readonly="readonly" id="accno" value="${bankacc}"/>
									</div>
								</div>
							</div>

							<form:hidden path="eMandateRegRequired" />
							<c:if test="${selectedFund.eMandateRegRequired}">
								
								
								<div class="form-group row mb-0">
								<label for="eMandate1" class="col-6 col-md-4 col-lg-4 col-form-label text-muted">Generate mandate ID </label>
								<div class="col-6 col-md-8 col-lg-8 custom-control custom-checkbox mb-2">
									<form:checkbox class="custom-control-input"
										path="eMandateRegRequired" disabled="true" id="eMandate1"></form:checkbox>
									<label class="custom-control-label" for="eMandate1" >
										Yes </label>
									<div>
										
										<form:hidden path="mandateType"/>
									</div>
								</div>
							</div>
							<div class="row d-flex justify-content-end mb-3">
								<div class="col-12 col-md-8 col-lg-8">
								<small class="text-muted">No paperwork. You have to add BSE through your Internet banking portal. Your Internet banking must support biller.</small>
								</div>							
							</div>
							

							</c:if>
							<c:if test="${not selectedFund.eMandateRegRequired}">
																
								<div class="form-group row">
								<label for="mandateId" class="col-5 col-form-label text-default">Mandate ID </label>
								<div class="col-7">
									<div class="md-form mt-0 mb-0">
									<form:select class="custom-select form-control-sm"
										id="mandateId" path="mandateId">
										<form:options items="${allmandates}" />
										</form:select>
									</div>
								</div>
							</div>

								<form:hidden path="mandateType" />

							</c:if>
							
							<div class="form-group row">
								<label for="payFirst1" class="col-6 col-md-5 col-lg-5 col-form-label text-default">Pay First Installment?</label>
								<div class="col-6 col-md-7 col-lg-7">
									<div class="custom-control custom-checkbox">
										<form:checkbox path="payFirstInstallment"
									class="custom-control-input" id="payFirst1" checked="checked" />
								<label class="custom-control-label" for="payFirst1" > Yes </label>
									</div>
								</div>
							</div>
							
							

						</c:if>


						<div class="custom-control custom-checkbox">
							<hr>
							<span data-toggle="modal" data-target="#mfdisclaimer"
								style="text-decoration: underline; cursor: pointer; color: blue;">Disclaimer
							</span> <input type="checkbox" class="custom-control-input"
								id="agreecheck1" checked="checked"> <label
								class="custom-control-label" for="agreecheck1"
								style="font-size: 11px; text-align: justify;"> <span>
									1. Performance history may or may not be in sync with the
									future performance and should not be considered as a base for
									investments. <br> 2. The size of the Assets Under
									Management (AuM) are based on the last published Monthly AUM by
									the corresponding fund house. <br> 3. If the investors are
									confused about whether the product is suitable for them or not,
									then he should consult their financial advisers for a better
									guidance. <br>

							</span>
							</label>
						</div>

						<div style="text-align: center; margin-top: 20px;">
							<form:button type="submit" class="btn #00796b teal darken-2 white-text" id="orderconfirmbtn">Confirm Order <i class="fab fa-opencart"></i>
							</form:button>
						</div>
					</form:form>