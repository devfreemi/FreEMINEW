<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<section>

	<!-- Material form login -->
	<div class="card">
		<h5
			class="card-header #d84315 deep-orange darken-3 white-text text-center">
			<strong>Redeem your balance </strong> <img alt="Withdraw"
				src="<c:url value="${contextPath}/resources/images/invest/withdraw-2.svg" />"
				class="img-fluid" style="height: 2rem;">
		</h5>

		<!--Card content-->
		<div class="card-body px-lg-6 pt-0">
			<div>
				<div class="row" style="background: #e4e3e1;">
					<div class="col-6">
						<div class="form-group row mb-0" style="font-size: 12px;">
							<div class="col-6">
								<label>Account Holder</label>
							</div>
							<label class="col-6" style="font-weight: 600;">${mfRedeemForm.unitHolderName}</label>
						</div>
					</div>
					<div class="col-6">
						<div class="form-group row mb-0" style="font-size: 12px;">
							<div class="col-6">
								<label>Date</label>
							</div>
							<label class="col-6" style="font-weight: 600;"><%=(new SimpleDateFormat("dd-MMM-yyyy")).format(new Date())%></label>
						</div>
					</div>
				</div>
			</div>

			<c:choose>
				<c:when test="${FUNDAVAILABLE == 'Y' }">
					<form:form class="form cf" id="regForm"
						action="${pageContext.request.contextPath}/mutual-funds/mfInvestRedeem.do"
						method="POST" commandName="mfRedeemForm">

						<div class="tab animated fadeIn">
							<h4 class="text-md-center"
								style="color: #f16927; font-weight: 500; border-bottom: 1px solid;">1.
								Scheme Details</h4>
							<div class="row">
								<div class="col-md-12 col-lg-12" style="text-align: left;">
									<div>
										<span id="mandateField" style="color: red; font-size: 12px;"></span>
									</div>
									<c:if test="${error!= null }">
										<span
											style="color: red; font-size: 12px; margin-bottom: 15px;">${error }</span>
									</c:if>

									<div class="form-group row form-sm mb-0">
										<label for="folio"
											class="col-sm-4 col-form-label mdb-color white-text">Folio
											No</label>
										<div class="col-sm-8">
											<div class="md-form mt-0">
												<form:input path="portfolio" id="folio"
													class="form-control form-control-sm" readonly="true" />
											</div>
										</div>
									</div>
								
									<div class="form-group row form-sm mb-0">
										<label for="fundname"
											class="col-sm-4 col-form-label mdb-color white-text">Scheme
											Name</label>
										<div class="col-sm-8">
											<div class="md-form mt-0">
												<form:input path="fundName" id="fundname"
													class="form-control form-control-sm" readonly="true" />
											</div>
										</div>
									</div>

									<div class="form-group row form-sm mb-0">
										<label for="bseschemeCode"
											class="col-sm-4 col-form-label mdb-color white-text">BSE
											Scheme Code</label>
										<div class="col-sm-8">
											<div class="md-form mt-0">
												<form:input path="schemeCode" id="bseschemeCode"
													class="form-control form-control-sm" readonly="true" />
											</div>
										</div>
									</div>

									<div class="form-group row form-sm mb-0">
										<label for="availableFund"
											class="col-sm-4 col-form-label mdb-color white-text">Invested
											Amount</label>
										<div class="col-sm-8">
											<div class="md-form mt-0">
												<form:input path="totalValue" id="availableFund"
													class="form-control form-control-sm" readonly="true" />
											</div>
										</div>
									</div>

									<div class="form-group row form-sm mb-0">
										<label for="currentNav"
											class="col-sm-4 col-form-label mdb-color white-text">NAV</label>
										<div class="col-sm-8">
											<div class="md-form mt-0">
												<form:input path="currentnav" id="currentNav"
													class="form-control form-control-sm" readonly="true" />
												<small class="grey-text">As on ${mfRedeemForm.navDate}</small>
											</div>
										</div>
									</div>

									<div class="form-group row form-sm mb-0">
										<label for="marketVal"
											class="col-sm-4 col-form-label mdb-color white-text">Current
											Value</label>
										<div class="col-sm-8">
											<div class="md-form mt-0">
												<form:input path="marketValue" id="marketVal"
													class="form-control form-control-sm" readonly="true" />
											</div>
										</div>
									</div>

									<div class="form-group row form-sm mb-0">
										<label for="redeemAllCheckBox"
											class="col-sm-4 col-form-label mdb-color white-text">Redeem
											All ?</label>
										<div class="col-sm-8">
											<div class="custom-control custom-checkbox mb-3">
												<form:checkbox path="redeemAll" class="custom-control-input"
													id="redeemAllCheckBox" />
												<label class="custom-control-label" for="redeemAllCheckBox">Yes</label>
											</div>
										</div>
									</div>


									<div class="form-group row form-sm mb-0" id="redeemamntbox">
										<label for="redeemamount"
											class="col-sm-4 col-form-label mdb-color white-text">Redeem
											Amount</label>
										<div class="col-sm-8">
											<div class="md-form mt-0">
												<form:input path="redeemAmounts" id="redeemamount"
													class="form-control form-control-sm" />

												<small id="invalidamnt" style="font-size: 11px; color: red;"
													class="form-text"></small>
											</div>
										</div>
									</div>

								</div>

							</div>
						</div>

						<!--  Confirm TAB -->
						<div class="tab animated fadeIn">
							<h4 class="text-md-center"
								style="color: #f16927; font-weight: 500; border-bottom: 1px solid;">2.
								Confirm Details</h4>

							<div>
								<span id="mandateField2" style="color: red; font-size: 12px;"></span>
							</div>
							<div class="row">
								<div class="col-md-12 col-lg-12">

									<div class="form-group row mb-0">
										<label for="folioconf"
											class="col-md-4 col-lg-4 col-form-label col-form-label-sm mdb-color white-text">Folio
											No:</label>
										<div class="col-md-8 col-lg-8 mb-2">
											<label class="confirm-label" id="folioconf">${mfRedeemForm.portfolio}</label>
										</div>
									</div>


									<div class="form-group row mb-0">
										<label for="fundnameconf"
											class="col-md-4 col-lg-4 col-form-label col-form-label-sm mdb-color white-text">Scheme
											Name:</label>
										<div class="col-md-8 col-lg-8 mb-2">
											<label class="confirm-label" id="fundnameconf">${mfRedeemForm.fundName}</label>
										</div>
									</div>


									<div class="form-group row mb-0">
										<label for="investtypeconf"
											class="col-md-4 col-lg-4 col-form-label col-form-label-sm mdb-color white-text">Scheme
											Code:</label>
										<div class="col-md-8 col-lg-8 mb-2">
											<label class="confirm-label" id="investtypeconf">${mfRedeemForm.schemeCode}</label>
										</div>
									</div>



									<div class="form-group row mb-0">
										<label for="availablefundconf"
											class="col-md-4 col-lg-4 col-form-label col-form-label-sm mdb-color white-text">Available
											Amount:</label>
										<div class="col-md-8 col-lg-8 mb-2">
											<label class="confirm-label" id="availablefundconf">${mfRedeemForm.totalValue}</label>
										</div>
									</div>


									<div class="form-group row mb-0">
										<label for="redeemamountconf"
											class="col-md-4 col-lg-4 col-form-label col-form-label-sm mdb-color white-text">Redeem
											Amount:</label>
										<div class="col-md-8 col-lg-8 mb-2">
											<label class="confirm-label" id="redeemamountconf"></label>
										</div>
									</div>


									<hr>

									<div class="form-group row mb-0">
										<div class="col-md-12 col-lg-12">

											<div class="custom-control custom-checkbox">
												<span data-toggle="modal" data-target="#mfdisclaimer"
													style="text-decoration: underline; cursor: pointer; color: blue; font-size: 11px;">Disclaimer</span>
												<form:checkbox path="agreePolicy"
													class="custom-control-input" id="agreepolicyconf" />
												<label class="custom-control-label" for="agreepolicyconf">
													<span style="font-size: 11px; text-align: justify;">
														1. Performance history may or may not be in sync with the
														future performance and should not be considered as a base
														for investments. <br> 2. The size of the Assets Under
														Management (AuM) are based on the last published Monthly
														AUM by the corresponding fund house. <br> 3. If the
														investors are confused about whether the product is
														suitable for them or not, then he should consult their
														financial advisers for a better guidance. <br>

												</span>
												</label>
											</div>

										</div>
									</div>
									<div>
										<form:hidden path="redeemTransId" />
										<form:hidden path="unitHolderName"/>
										<form:hidden path="navDate"/>
									</div>

								</div>
							</div>
						</div>

						<jsp:include page="transaction-in-progress-icon.jsp"></jsp:include>


						<div style="overflow: auto;">
							<div style="float: right;">
								<form:button type="button" class="btn btn-sm btn-info"
									id="prevBtn" onclick="nextPrev(-1)">
									<i class="fas fa-arrow-left"></i> Previous</form:button>
								<form:button type="button" class="btn btn-sm btn-success"
									id="nextBtn" onclick="nextPrev(1)">Next <i
										class="fas fa-angle-right"></i>
								</form:button>
							</div>
						</div>

						<!-- Circles which indicates the steps of the form: -->
						<div style="text-align: center; margin-top: 40px;">
							<span class="step"></span> <span class="step"></span>
						</div>


					</form:form>
				</c:when>
				<c:when test="${FUNDAVAILABLE == 'N' }">
					<c:if test="${error!= null }">
						<span style="color: red;">${error }</span>
					</c:if>
					<div>
						<a href="${pageContext.request.contextPath}/my-dashboard">GO
							BACK TO DASHBOARD</a>
					</div>
				</c:when>
			</c:choose>


		</div>

	</div>
	<!-- Material form login -->


</section>