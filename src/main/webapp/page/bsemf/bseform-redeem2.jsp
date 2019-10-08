<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<section class="redeem-form-section">
	<div style="background: #e4e3e1;">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group row" style="font-size: 12px;">
					<div class="col-6">
						<label>Folio No:</label>
					</div>
					<label class="col-6" style="font-weight: 600;">${mfRedeemForm.portfolio}</label>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group row" style="font-size: 12px;">
					<div class="col-6">
						<label>Account Holder</label>
					</div>
					<label class="col-6" style="font-weight: 600;">${mfRedeemForm.unitHolderName}</label>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group row" style="font-size: 12px;">
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

				<div class="tab">
					<h4 class="text-md-center"
						style="color: #f16927; font-weight: 500; border-bottom: 1px solid;">1.
						Scheme Details</h4>
					<div class="row">
						<div class="col-md-12 col-lg-12" style="text-align: left;">
							<div>
								<span id="mandateField" style="color: red; font-size: 12px;"></span>
							</div>
							<c:if test="${error!= null }">
								<span style="color: red; font-size: 12px; margin-bottom: 15px;">${error }</span>
							</c:if>

							<div class="form-group row">
								<label for="folio"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Folio
									No:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<form:input readonly="true" path="portfolio" id="folio"
										class="form-control form-control-sm form-control-plaintext" />
								</div>
							</div>

							<div class="form-group row">
								<label for="fundname"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Scheme
									Name:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<form:input readonly="true" path="fundName" id="fundname"
										class="form-control form-control-sm form-control-plaintext" />
								</div>
							</div>

							<div class="form-group row">
								<label for="fundname"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">BSE
									Scheme Code:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<form:input readonly="true" path="schemeCode"
										id="bseschemeCode"
										class="form-control form-control-sm form-control-plaintext" />
								</div>
							</div>

							<div class="form-group row">
								<label for="availableFund"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Invested
									Amount:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<%-- <form:input path="totalValue" id="availableFund"
														readonly="true"
														class="form-control form-control-sm form-control-plaintext" /> --%>

									<form:hidden path="totalValue" id="availableFund" />
									<%-- <form:input type="number" path="marketValue" id="marketVal"  readonly="true" class="form-control form-control-sm form-control-plaintext" /> --%>
									<label
										class="form-control form-control-sm form-control-plaintext"><fmt:formatNumber
											type="number" pattern="##,###.00" minFractionDigits="1"
											minIntegerDigits="1" maxFractionDigits="2"
											groupingUsed="true" value="${mfRedeemForm.totalValue }" /></label>
								</div>
							</div>

							<div class="form-group row">
								<label for="availableFund"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">NAV
								</label>
								<div class="col-6 col-md-8 col-lg-8">
									<form:hidden path="currentnav" id="currentNav" />
									<label
										class="form-control form-control-sm form-control-plaintext">${mfRedeemForm.currentnav}</label>
								</div>
							</div>

							<div class="form-group row">
								<label for="availableFund"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Current
									value:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<form:hidden path="marketValue" id="marketVal" />
									<%-- <form:input type="number" path="marketValue" id="marketVal"  readonly="true" class="form-control form-control-sm form-control-plaintext" /> --%>
									<label
										class="form-control form-control-sm form-control-plaintext"><fmt:formatNumber
											type="number" pattern="##,###.00" minFractionDigits="1"
											minIntegerDigits="1" maxFractionDigits="2"
											groupingUsed="true" value="${mfRedeemForm.marketValue }" /></label>
								</div>
							</div>

							<div class="form-group row mb-1">
								<label for="redeemAllCheckBox"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Redeem
									All ?</label>
								<div class="col-6 col-md-8 col-lg-8">
									<div class="custom-control custom-checkbox">
										<form:checkbox path="redeemAll" class="custom-control-input"
											id="redeemAllCheckBox" />
										<label class="custom-control-label" for="redeemAllCheckBox">Yes</label>
									</div>
								</div>
							</div>

							<div class="form-group row" id="redeemamntbox">
								<label for="redeemamount"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Redeem
									Amount:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<form:input path="redeemAmounts" id="redeemamount"
										maxlength="10" class="form-control form-control-sm" />
									<span id="invalidamnt" style="font-size: 11px; color: red;"></span>
								</div>
							</div>

						</div>

					</div>
				</div>
				<div class="tab">
					<div class="row">
						<div class="col-md-12 col-lg-12">
							<h4 class="text-md-center"
								style="color: #f16927; font-weight: 500; border-bottom: 1px solid;">2.
								Confirm Details</h4>
							<div class="form-group row mb-1">
								<label for="folioconf"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Folio
									No:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<label class="confirm-label" id="folioconf">${mfRedeemForm.portfolio}</label>
								</div>
							</div>

							<div class="form-group row mb-1">
								<label for="fundnameconf"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Scheme
									Name:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<label class="confirm-label" id="fundnameconf">${mfRedeemForm.fundName}</label>
								</div>
							</div>

							<div class="form-group row mb-1">
								<label for="investtypeconf"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Investment
									Type:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<label class="confirm-label" id="investtypeconf">${mfRedeemForm.investType}</label>
								</div>
							</div>


							<div class="form-group row mb-1">
								<label for="availablefundconf"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Available
									Amount:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<label class="confirm-label" id="availablefundconf">${mfRedeemForm.totalValue}</label>
								</div>
							</div>



							<div class="form-group row mb-1">
								<label for="redeemamountconf"
									class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm">Redeem
									Amount:</label>
								<div class="col-6 col-md-8 col-lg-8">
									<label class="confirm-label" id="redeemamountconf"></label>
								</div>
							</div>
							<hr>
							<div class="form-group row mb-1">
								<!-- <label for="agreepolicyconf"
													class="col-6 col-md-4 col-lg-4 col-form-label col-form-label-sm"></label> -->
								<div class="col-md-12 col-lg-12">

									<div class="custom-control custom-checkbox">
										<span data-toggle="modal" data-target="#mfdisclaimer"
											style="text-decoration: underline; cursor: pointer; color: blue; font-size: 11px;">Disclaimer</span>
										<form:checkbox path="agreePolicy" class="custom-control-input"
											id="agreepolicyconf" />
										<label class="custom-control-label" for="agreepolicyconf">
											<span style="font-size: 11px; text-align: justify;">
												1. Performance history may or may not be in sync with the
												future performance and should not be considered as a base
												for investments. <br> 2. The size of the Assets Under
												Management (AuM) are based on the last published Monthly AUM
												by the corresponding fund house. <br> 3. If the
												investors are confused about whether the product is suitable
												for them or not, then he should consult their financial
												advisers for a better guidance. <br>

										</span>
										</label>
									</div>

								</div>
							</div>
							<div>
								<form:hidden path="redeemTransId" />
								<%-- <form:hidden path="schemeCode" /> --%>
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
</section>