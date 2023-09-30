<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

					<form:form
						action="${pageContext.request.contextPath}/mutual-funds/mfPurchaseConfirm.do"
						method="POST" commandName="selectedFund">

						<form:hidden path="clientID" />
						<form:hidden path="transactionID" />
						<form:hidden path="schemeOptionType" id="schemetypeid" />

						<div class="first-step">
							<h5 class="mb-4 text-purchase-head text-center">
								Invest Details
							</h5>
					
							<div class="form-group row mb-0">
								<div class="col-sm-5 col-5 col-form-label text-purchase">
									<p class="mb-0">Investor Name</p>
								</div>
								<div class="col-sm-7 col-7 md-form mb-0">
									<form:input path="investorName" class="form-control border-0 text-right pt-2 text-input-purchase"
										id="custName" value="" readonly="true" />
								</div>
							</div>
					
							<div class="form-group row mb-0">
								<div class="col-sm-5 col-5 col-form-label text-purchase">
									<p class="mb-0">Mobile</p>
								</div>
								<div class="col-sm-7 col-7 md-form mb-0">
									<form:input path="mobile" class="form-control border-0 text-right pt-2 text-input-purchase" value=""
										id="mobileid" readonly="true" />
								</div>
							</div>
					
					
							<div class="form-group row mb-0">
								<div class="col-sm-6 col-6 col-form-label text-purchase">
									<p class="mb-0">Portfolio Number</p>
								</div>
								<div class="col-sm-6 col-6 md-form mb-0">
									<form:select path="portfolio" id="folioid" class="form-control border-0 text-right pt-2 text-input-purchase">
										<form:option value="NEW" selected="selected">NEW &#9660;</form:option>
										<form:options items="${amcPortFolio }" />
									</form:select>
								</div>
							</div>
					
							<div class="form-group row mb-0">
								<div class="col-sm-5 col-md-4 col-5 col-form-label text-purchase">
									<p class="mb-0">Scheme Name</p>
								</div>
								<div class="col-sm-7 col-md-8 col-7 md-form mb-0">
									<form:input path="schemeName" readonly="true" value=""
										class="form-control border-0 text-right pt-2 text-input-purchase" id="schemeName" />
								</div>
							</div>
					
					
					
							<form:hidden path="schemeCode" />
							<form:hidden path="reinvSchemeCode" />
					
							<div class="form-group row mb-0">
								<div class="col-sm-4 col-5 col-form-label text-purchase">
									<p class="mb-0">Fund category</p>
								</div>
								<div class="col-sm-7 col-md-8 col-7 md-form mb-0">
									<form:select path="invCategory" id="growthCategory"
										class="form-control border-0 text-right pt-2 text-input-purchase">
										<form:option value="Z" selected="selected">Growth &#9660;</form:option>
										<c:if test="${not empty selectedFund.reinvSchemeCode}">
											<form:option value="Y">Dividend Re-investment</form:option>
										</c:if>
									</form:select>
								</div>
							</div>
					
					
							<div class="form-group row mb-0">
								<div class="col-7 col-form-label text-purchase">
									<p class="mb-0">Investment Type</p>
								</div>
								<div class="col-5 md-form mt-0 mb-0">
									<form:input path="investype" value="" readonly="true"
										class="form-control border-0 text-right py-0 text-input-purchase" id="invtype" />
								</div>
							</div>
					
							<div class="form-group row mb-0">
								<div class="col-7 col-form-label text-purchase">
									<p class="mb-0"> Investment Amount </p>
								</div>
								<div class="col-5 md-form mt-0 mb-0">
									<form:input path="investAmount" value=""
										class="form-control border-0 text-right py-0 text-input-purchase" id="invAmount" />
								</div>
							</div>
						</div>
						<div class="mt-4 d-md-none d-block text-right">
							<button class="purchase-next btn btn-success" type="button">Next</button>
						</div>
						<div class="d-md-block d-none" id="next-step">

							<h5 class="mb-4 mt-0 mt-md-4 text-purchase-head text-center">
								<span class="text-left float-left back d-block d-md-none" style="height: 1.5rem;">
									<i class="fas fa-angle-left"></i>
								</span>
								Purchase Details
							</h5>
							<c:if test="${selectedFund.investype == 'SIP' }">
								<div class="form-group row mb-0">
									<div class="col-6 col-form-label text-purchase">
										<p class="mb-0"> Monthly SIP date</p>
									</div>
									<div class="col-6 md-form mt-0 mb-0">
										<form:input path="sipDate" readonly="true"
											class="form-control border-0 text-right pt-2 text-input-purchase" id="schemeDate" />
									</div>
								</div>
								<div class="form-group row mb-0">
									<div class="col-sm-6 col-6 col-form-label text-purchase">
										<p class="mb-0">SIP start from</p>
									</div>
									<div class="col-sm-6 col-6 pl-0">
										<div class="md-form mt-0 mb-0">
											<div class="row mx-auto">
												<!-- Grid column -->
												<div class="col-6 md-form mt-0 mb-0 px-0">
													<form:select class="form-control border-0 pt-2 text-input-purchase" id="sipmonth"
														path="sipStartMonth">
														<form:options items="${calendarmonths}" />
													</form:select>
												</div>
												<div class="col-6 md-form mt-0 mb-0 px-0">
													<!-- Material input -->
													<form:select class="form-control border-0 text-right pt-2 text-input-purchase" id="sipyear"
														path="sipStartYear">
														<form:options items="${sipyear}" />
													</form:select>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group row mb-0">
									<div class="col-6 col-form-label text-purchase">
										<p class="mb-0">Tenure (Month)</p>
									</div>
									<div class="col-6 md-form mt-0 mb-0">
										<form:input path="noOfInstallments" mandatory="true"
											class="form-control border-0 text-right pt-2 text-input-purchase"
											id="noOfInstallments" />
									</div>
								</div>
								<div class="form-group row mb-0">
									<div class="col-6 col-form-label text-purchase">
										<p class="mb-0">Registered Bank</p>
									</div>
									<div class="col-6 md-form mt-0 mb-0">
										<form:input path="other1" type="text" class="form-control border-0 text-right pt-2 text-input-purchase"
											readonly="true" id="bankName" />
									</div>
								</div>
								<div class="form-group row mb-0">
									<div class="col-6 col-form-label text-purchase">
										<p class="mb-0">Bank Account No.</p>
									</div>
									<div class="col-6 md-form mt-0 mb-0">
											<form:input path="banbkaccount" type="text" class="form-control border-0 text-right pt-2 text-input-purchase" readonly="true" id="accno"/>
									</div>
								</div>
								<form:hidden path="eMandateRegRequired" />
								<c:if test="${selectedFund.eMandateRegRequired}">


									<div class="form-group row mb-0 mb-0">
										<div class="col-8 col-form-label text-purchase">
											<p class="mb-0">Generate mandate ID</p>
										</div>
										<div class="col-4 pt-2 text-right pr-0">
											<div class="form-check form-check-inline">
													<form:checkbox class="form-check-input" path="eMandateRegRequired" id="eMandate1"
													value="Y" />
												<label class="form-check-label text-input-purchase" for="eMandate1">Yes</label>
											</div>
											<form:hidden path="mandateType"/>
										</div>
									</div>
									<div class="col-12 col-md-12 col-lg-12 py-3 px-0 px-md-3 text-center">
										<small class="text-primary f-11">E-Nach mandate registration for automated payment service.</small>
										<br>
										<small class="text-muted f-11">Note: Email will be sent for authenticating the generated mandate
											ID.</small>
									</div>


								</c:if>
								<c:if test="${not selectedFund.eMandateRegRequired}">

									<div class="form-group row mb-0">
										<div class="col-5 col-form-label text-purchase">
											<p class="mb-0">Mandate ID</p>
										</div>
										<div class="col-7 md-form mt-0 mb-0">
											<form:select class="form-control border-0 text-right pt-2 text-input-purchase" id="mandateId"
												path="mandateId">
												<form:option value="" selected="selected" disabled="true">Select Mandate ID &#9660;</form:option>
												<form:options items="${allmandates}" />
											</form:select>
										</div>
									</div>

									<form:hidden path="mandateType" />

								</c:if>
								<div class="form-group row mb-0">
									<div class="col-8 col-form-label text-purchase">
										<p class="mb-0">Pay First Installment?</p>
									</div>
									<div class="col-4 pt-2 text-right pr-0">
										<!-- <checkbox path="payFirstInstallment" class="custom-control-input" id="payFirst1" checked="checked" /> -->
										<!-- <label class="custom-control-label" for="payFirst1"> Yes </label> -->
										<div class="form-check form-check-inline">
												<form:checkbox path="payFirstInstallment"
									class="form-check-input" id="payFirst1" checked="checked" />
											<label class="form-check-label text-input-purchase" for="payFirst1">Yes</label>
										</div>
									</div>
								</div>
							</c:if>
							<div class="custom-control custom-checkbox">
								<hr>
								<span class="disclaimer" data-toggle="modal" data-target="#mfdisclaimer">Disclaimer
								</span>
								<br>
								 <input type="checkbox" class="custom-control-input"
									id="agreecheck1" checked="checked" required="true"> <label
									class="custom-control-label" for="agreecheck1"
									style="font-size: 11px; text-align: justify;"> 
									<span class="text-tc">
										1. Performance history may or may not be in sync with the
										future performance and should not be considered as a base for
										investments. <span class="text-danger read-more">...</span>
										<span class="more">
											<br>
											2. The size of the Assets Under
											Management (AuM) are based on the last published Monthly AUM by
											the corresponding fund house.
											<br>
											3. If the investors are
											confused about whether the product is suitable for them or not,
											then he should consult their financial advisers for a better
											guidance.
											<br>
										</span>
									</span>
								</label>
							</div>
					
							<div class="text-center mt-3">
								<form:button type="submit" class="btn btn-order-create py-2" id="orderconfirmbtn">Confirm Order <i
										class="fab fa-opencart"></i>
								</form:button>
							</div>
						</div>
						
					</form:form>