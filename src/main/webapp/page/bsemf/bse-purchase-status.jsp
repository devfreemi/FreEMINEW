<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>FreEMI Mutual Fund Purchase Status</title>

<meta name="description" content="" />
<meta name="robots" content="noindex,follow" />

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<jsp:include page="../include/GoogleHeadTag.jsp"></jsp:include>
<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<link href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>" rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
</head>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

<body >
	<jsp:include page="../include/header.jsp"></jsp:include>
	<jsp:include page="../include/GoogleBodyTag.jsp"></jsp:include>
	<div class="container">

		<section class="purchase_status" style="margin-bottom: 5rem;">
			<div class="row" style="margin: auto;">
				<div class="col-md-8 col-lg-8 p-4"
					style="margin: auto;box-shadow: 0 0 6px 1px #b7b7b7; border-radius: 5px;">
					
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
							<h5 class="text-center #00897b teal darken-1 white-text p-1 mb-4">Your purchase is complete!</h5>
							
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
										<a
											style="text-decoration: none;"
											href="${pageContext.request.contextPath}/mutual-funds/funds-explorer"><button class="btn btn-sm mdb-color darken-1 white-text">Place another Order</button> </a>
									</div>
								</div>
							</section>
						</c:when>

						<c:when test="${TRANS_STATUS == 'Y' }">
							<h5 class="text-center #00897b teal darken-1 white-text p-1 mb-4">Your order is placed successfully</h5>
								<div class="dataTables_wrapper dt-bootstrap4 animated fadeIn"
										style="margin-top: 30px; overflow: auto;">
									<table class="table">
										<tr>
											<th class="text-dark">Fund Name</th>
											<td>${TRANSACTION_REPORT.fundName }</td>
										</tr>
										<tr>
											<th class="text-dark">Transaction type</th>
											<td>${TRANS_TYPE }</td>
										</tr>
										<tr>
											<th class="text-dark">Transaction Reference no</th>
											<td>${TRANS_ID }</td>
										</tr>
										<tr>
											<th class="text-dark">Order No</th>
											<td>${TRANSACTION_REPORT.bseOrderNoFromResponse}</td>
										</tr>
									</table>
									</div>
									<hr>
									<div class="text-center">
									<small class="text-muted">${TRANSACTION_REPORT.statusMsg }</small>
									</div>
								
							<c:if test="${not empty EMANDATE}">
								<c:choose>
									<c:when test="${EMANDATE == 'S' }">
										<div>
											<small class="text-muted">E-mandate registered successfully. Kindly ensure to register the mandate ID in your internet banking account.</small>
										</div>
									</c:when>
									<c:when test="${EMANDATE == 'F' }">
										<div class="mb-2">
											<small class="text-muted">Failed to generate mandate ID. Please
												contact admin if the issue persist.</small >
										</div>
									</c:when>
								</c:choose>
								<%-- <p></p> --%>
							</c:if>
							
							<c:if test="${FIRST_PAY == 'Y' }">
								<c:if test="${orderUrl.statusCode == '100' }">
									<div class="text-center">
									<a href="${orderUrl.payUrl }">
										<button class="btn #ff7043 deep-orange lighten-1 text-white">Complete your
											payment <i class="fab fa-opencart"></i></button>
									</a>
									</div>
								</c:if>
							</c:if>
							

						</c:when>
						<c:when test="${TRANS_STATUS == 'N' }">
							<div class="alert alert-danger" role="alert">Failed to
								process your request currently. Kindly try after sometime</div>

							<div>
								<span style="font-size: 11px; font-weight: 600;">${MSG }</span>
							</div>

							<section style="margin-top: 30px;">
								<div class="row" style="margin: auto;">
									<div class="col-md-6 col-lg-6"
										style="margin: auto; text-align: center;">
										<a
											style="text-decoration: none;"
											href="${pageContext.request.contextPath}/mutual-funds/funds-explorer"><button class="btn btn-sm mdb-color darken-1 white-text">Place another order</button> </a>
									</div>
								</div>
							</section>
						</c:when>
						<c:when test="${TRANS_STATUS == 'SF' }">
							<h5>Your transaction is successful, but failed to save
								request at FreEMI. Admin will help you fix the problem.</h5>
						</c:when>
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
</html>