<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>FreEMI Mutual Fund Purchase Status</title>

<meta name="description" content="" />
<meta name="robots" content="noindex,follow" />

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<jsp:include page="../include/GoogleHeadTag.jsp"></jsp:include>
<link
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
</head>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<jsp:include page="../include/GoogleBodyTag.jsp"></jsp:include>
	<div class="container">
		<%
			
		%>
		<section class="purchase_status" style="margin-bottom: 5rem;">
			<div class="row" style="margin: auto;">
				<div class="col-md-8 col-lg-8 p-4"
					style="margin: auto; box-shadow: 0 0 6px 1px #b7b7b7; border-radius: 5px;">

					<div class="row mb-4">
						<div class="col-md-4 col-lg-4" style="margin: auto;">
							<img class="img-fluid d-none d-md-block"
								src="<c:url value="${contextcdn}/resources/images/invest/transaction-receipt.svg"/>"
								alt="Transact">
						</div>
						<div class="col-md-8 col-lg-8">
							<div class="text-center d-md-none mb-3">
								<img class="img-fluid" style="height: 2rem; margin: auto;"
									src="<c:url value="${contextcdn}/resources/images/invest/transact_bse.svg"/>"
									alt="Transact">
							</div>

							<c:choose>
								<c:when test="${TRANS_STATUS == 'COMPLETE' }">
									<h5
										class="text-center #00897b teal darken-1 white-text p-1 mb-4">Your
										purchase is complete!</h5>

									<div class="dataTables_wrapper dt-bootstrap4 animated fadeIn"
										style="margin-top: 30px; overflow: auto;">
										<table class="table">
											<tr>
												<th class="text-dark">Order No</th>
												<td>${orderno}</td>
											</tr>
											<tr>
												<th class="text-dark">Payment Status</th>
												<td>${ORDER_STATUS}</td>
											</tr>


										</table>
									</div>

									<section style="margin-top: 30px;">
										<div class="row" style="margin: auto;">
											<div class="col-md-6 col-lg-6 text-center"
												style="margin: auto;">
												<a style="text-decoration: none;"
													href="${pageContext.request.contextPath}/mutual-funds/funds-explorer"><button
														class="btn btn-sm mdb-color darken-1 white-text">Place
														another Order</button> </a>
											</div>
										</div>
									</section>
								</c:when>

								<c:when test="${TRANS_STATUS == 'UPI_REQUEST' }">
									<div class="border">
										<h5 class="text-success mt-2">
											<Strong> Payment request has been sent </Strong>
										</h5>
										<hr>
										<div class="row">
											<div class="col-4">
												<label>UPI ID</label>
											</div>
											<div class="col-8">
												<small>${bsepay.upiid}</small>
											</div>
										</div>
										<div class="row">
											<div class="col-4">
												<label>Amount</label>
											</div>
											<div class="col-8">
												<small>Rs.${bsepay.amount}</small>
											</div>
										</div>
										<div class="row">
											<div class="col-4">
												<label>Order no</label>
											</div>
											<div class="col-8">
												<small>${bsepay.orderno}</small>
											</div>
										</div>
										<div class="row">
											<div class="col-4">
												<label>Response</label>
											</div>
											<div class="col-8">
												<small>${data}</small>
											</div>
										</div>

										<div class="row mt-3 mb-3">
											<div class="col-12 text-center">
												<a href="${pageContext.request.contextPath}/my-dashboard"><button
														class="btn btn-primary btn-sm">Dashboard</button></a> <a
													href="/mutual-funds/mutual-fund-explorer"><button
														class="btn btn-primary btn-sm">Explore funds</button></a>
											</div>

										</div>
									</div>


								</c:when>

								<c:when
									test="${TRANS_STATUS == 'Y' or TRANS_STATUS == 'RETRY' }">
									<h5
										class="text-center #00897b teal darken-1 white-text p-1 mb-4">Your
										order is placed successfully</h5>
										
									<h6 class="text-danger">${error}</h6>
									<div class="dataTables_wrapper dt-bootstrap4 animated fadeIn"
										style="margin-top: 30px; overflow: auto;">
										<table class="table">
											<tr>
												<th class="text-dark">Fund Name</th>
												<td>${bsepay.transstatus.fundName }</td>
											</tr>
											<tr>
												<th class="text-dark">Investment Type</th>
												<td>${bsepay.transstatus.investmentType }</td>
											</tr>
											<tr>
												<th class="text-dark">Transaction type</th>
												<td>${bsepay.transstatus.transactiontype }</td>
											</tr>
											<tr>
												<th class="text-dark">Reference no.</th>
												<td>${bsepay.transstatus.transactionReference }</td>
											</tr>
											<tr>
												<th class="text-dark">Order No</th>
												<td>${bsepay.transstatus.bseOrderNoFromResponse}</td>
											</tr>

											<c:if test="${bsepay.transstatus.investmentType == 'SIP'}">

												<%-- <c:if test="${not empty TRANSACTION_REPORT.emandateStatusCode}"> --%>
												<tr>
													<th class="text-dark">Mandate ID</th>
													<td>${bsepay.transstatus.mandateid }</td>
												</tr>
												<c:choose>
													<c:when
														test="${bsepay.transstatus.emandateStatusCode == 'S' }">
														<div>
															<small class="text-muted">E-mandate registered
																successfully. Authenticate mandate</small>
														</div>
														<tr>
															<th class="text-dark">Mandate generation</th>
															<td>Success</td>
														</tr>
														<tr>
															<th class="text-dark">Action</th>
															<td><c:if
																	test="${bsepay.transstatus.other1 == 'S' }">
																	<a href="${bsepay.transstatus.other2}" target="_blank">
																		<button class="btn btn-sm btn-primary">Authenticate
																			E-mandate</button>
																	</a>

																	<button onclick="mandatestatus()">Test</button>
																</c:if></td>
														</tr>

													</c:when>
													<c:when
														test="${bsepay.transstatus.emandateStatusCode == 'SELECTED' }">
														<tr>
															<th class="text-dark">Action</th>
															<td><button class="btn btn-sm btn-primary">Check
																	E-mandate</button></td>
														</tr>
													</c:when>
													<c:when
														test="${bsepay.transstatus.emandateStatusCode == 'F' }">
														<div class="mb-2">
															<small class="text-danger">Failed to generate
																mandate ID. Please contact admin if the issue persist.</small>
														</div>
													</c:when>

												</c:choose>
												<%-- <p></p> --%>
											</c:if>
											<%-- </c:if> --%>

										</table>
									</div>
									<hr>
									<div class="text-center">
										<small class="text-muted">${bsepay.transstatus.statusMsg }</small>
									</div>



									<c:if test="${bsepay.transstatus.firstpay == 'Y' }">
										<div class="row">
											<div class="col-12 mx-auto">
												<div id="h">
													<form:form modelAttribute="bsepay" method="POST"
														action="${pageContext.request.contextPath}/mutual-funds/bse-transaction-status">
														<label for="paymode" class="form-label small text-primary">Payment
															mode </label>
														<div class="md-form md-outline form-sm mt-0">
															<form:select class="form-select form-select-sm"
																path="payvia" id="payvia" aria-label="Payment mode"
																data-width="100%">
																<option selected value="IB">Internet Banking</option>
																<option value="UPI">UPI</option>
															</form:select>


														</div>
														<div id="bank" class="mb-3 d-all">
															<label for="bank" class="form-label small text-primary">Account
															</label>
															<form:input path="bankacc"
																class="form-control
															 form-control-sm"
																id="bankaccno" readonly="true" placeholder=""></form:input>
														</div>
														<div id="bank" class="mb-3 d-all">
															<label for="bank" class="form-label small text-primary">Bank
																name</label>
															<form:input path="bankname"
																class="form-control
															 form-control-sm"
																id="bank" readonly="true" placeholder=""></form:input>
														</div>

														<div id="upifield" class="d-none mb-3">
															<label for="upi" class="form-label small text-primary">UPI
																ID</label>
															<form:input path="upiid" maxlength="65"
																class="form-control form-control-sm" id="upi"
																placeholder=""></form:input>
														</div>

														<form:hidden path="chosenbankid" />
														<form:hidden path="transstatus.clientcode" />
														<form:hidden path="transstatus.bseOrderNoFromResponse" />
														<form:hidden path="transstatus.mandateid" />
														<form:hidden path="transstatus.mobile" />
														<form:hidden path="transstatus.successFlag" />
														<form:hidden path="transstatus.investamount" />
														<form:hidden path="transstatus.transactionReference" />
														<form:hidden path="transstatus.fundName" />
														<form:hidden path="transstatus.investmentType" />
														<form:hidden path="transstatus.transactiontype" />
														<form:hidden path="transstatus.emandateStatusCode" />
														<form:hidden path="transstatus.other1" />
														<form:hidden path="transstatus.other2" />
														<form:hidden path="transstatus.statusMsg" />
														<form:hidden path="transstatus.firstpay" />
														
														
														<div class="mb-3 text-center">
															<form:button class="btn btn-primary">Proceed to pay</form:button>
															<p><small class="text-info">*SMS/Email sent to complete order authentication. Kindly complete before proceeding to pay</small></p>
														</div>
														
													</form:form>
												</div>

											</div>
										</div>

										<c:if test="${orderUrl.statusCode == '100' }">
											<div class="text-center">
												<a href="${orderUrl.payUrl }">
													<button
														class="btn #ff7043 deep-orange lighten-1 text-white">
														Complete your payment <i class="fab fa-opencart"></i>
													</button>
												</a>
											</div>
										</c:if>
									</c:if>


								</c:when>

								<c:when test="${TRANS_STATUS == 'N' }">
									<div class="alert alert-danger" role="alert">Failed to
										process your request currently. Kindly try after sometime</div>

									<div>
										<span style="font-size: 11px; font-weight: 600;">${bsepay.transstatus.transactionmsg }</span>
									</div>

									<section style="margin-top: 30px;">
										<div class="row" style="margin: auto;">
											<div class="col-md-6 col-lg-6"
												style="margin: auto; text-align: center;">
												<a style="text-decoration: none;"
													href="${pageContext.request.contextPath}/mutual-funds/funds-explorer"><button
														class="btn btn-sm mdb-color darken-1 white-text">Place
														another order</button> </a>
											</div>
										</div>
									</section>
								</c:when>

								<c:when test="${TRANS_STATUS == 'SF' }">
									<h5>Your transaction is successful, but failed to save
										request at FreEMI. Admin will help you fix the problem.</h5>
								</c:when>
								<c:otherwise>
									<h5
										class="text-center #00897b teal darken-1 white-text p-1 mb-4">Transaction
										status missing!</h5>
									<h6>Your details could not be shown due to missing
										information. Kindly go to profile and check the status.</h6>
								</c:otherwise>
							</c:choose>
						</div>

					</div>
				</div>
			</div>
		</section>

	</div>
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#payvia').select2({
							minimumResultsForSearch : -1
						});

						$("#payvia").change(function() {
							if ($("#payvia").val() == 'IB') {
								$("#upifield").removeClass("d-all");
								$("#upifield").addClass("d-none");
								$("#bank").addClass("d-all");
								$("#bank").removeClass("d-none");
							} else if ($("#payvia").val() == 'UPI') {
								$("#upifield").removeClass("d-none");
								$("#upifield").addClass("d-all");
								$("#bank").addClass("d-none");
								$("#bank").removeClass("d-all");
							}/* else{
																									alert("Invalid option!- "+ $("#payvia").text());
																								} */
						});

						$('form')
								.on(
										'submit',
										function(e) {
											e.preventDefault();
											let regex = new RegExp(
													/^[a-zA-Z0-9.-]{2,48}@[a-zA-Z][a-zA-Z]{2,16}$/);
											if ($("#payvia").val() == 'UPI') {
												if ($("#upi").val() == undefined
														|| $("#upi").val() == null
														|| $("#upi").val() == "") {
													alert("Please provide UPI Id.");
													return false;
												}
												if ($("#upi").val().length > 65) {
													alert("UPI ID is lengthy.");
													return false;
												}
												if (!regex
														.test($("#upi").val())) {
													alert("Invalid UPI ID format.");
													return false;
												}
											}
											$(this).off("submit");

											this.submit();
											return true;

										});

					});
</script>

</html>