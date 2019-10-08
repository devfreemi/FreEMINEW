<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>FreEMI MF</title>


<meta name="description" content="" />
<meta name="robots" content="index,nofollow" />
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link href='https://fonts.googleapis.com/css?family=Arsenal' rel='stylesheet'>
<style type="text/css">
td{
border: 1px solid #8b8e90;
}
</style>
</head>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

<body onLoad="noBack();" onpageshow="if (event.persisted) noBack();"
	onUnload="">
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container">

		<section class="purchase_status" style="margin-bottom: 5rem;">
			<div class="row" style="margin: auto;">
				<div class="col-md-6 col-lg-6"
					style="margin: auto; text-align: center; padding: 20px; background: aliceblue;">

					<div class="" style="margin-bottom: 2rem;">
						<img class="d-block img-fluid" style="height: 2rem; margin: auto;"
							src="<c:url value="${contextcdn}/resources/images/invest/transact_bse.svg"/>"
							alt="Transact">
					</div>
					
					<c:choose>
						<c:when test="${TRANS_STATUS == 'COMPLETE' }">
							<h5>Your purchase is complete!</h5>
							<h6>${ORDER_STATUS }</h6>
							<section style="margin-top: 30px;">
								<div class="row" style="margin: auto;">
									<div class="col-md-6 col-lg-6"
										style="margin: auto; text-align: center;">
										<a
											style="text-decoration: none;"
											href="${pageContext.request.contextPath}/mutual-funds/funds-explorer"><button class="btn btn-sm mdb-color darken-1 white-text">Place another Order</button> </a>
									</div>
								</div>
							</section>
						</c:when>

						<c:when test="${TRANS_STATUS == 'Y' }">
							<h5>Your order placed successfully</h5>
							<div class="row">
								<div class="col-md-12 col-lg-12" style="margin-bottom: 2rem;">
									<table style="box-shadow: 1px 3px 4px 0px #b1b1b1;margin: auto;">
										<tr>
											<td><label>Fund Name</label></td>
											<td>${TRANSACTION_REPORT.fundName }</td>
										</tr>
										<tr>
											<td><label>Transaction Reference no</label></td>
											<td>${TRANS_ID }</td>
										</tr>
										<tr>
											<td><label>Order No</label></td>
											<td>${TRANSACTION_REPORT.bseOrderNoFromResponse }</td>
										</tr>
									</table>
									
									<div>
									<span>${TRANSACTION_REPORT.statusMsg }</span>
									</div>
								</div>
							</div>

							<c:if test="${FIRST_PAY == 'Y' }">
								<c:if test="${orderUrl.statusCode == '100' }">
									<a href="${orderUrl.payUrl }">
										<button class="btn btn-sm btn-success">Complete your
											payment</button>
									</a>
								</c:if>
							</c:if>
							<c:if test="${not empty EMANDATE}">
								<c:choose>
									<c:when test="${EMANDATE == 'S' }">
										<div>
											<span style="font-weight: 500;">E-mandate registered successfully</span>
										</div>
									</c:when>
									<c:when test="${EMANDATE == 'F' }">
										<div>
											<span>Failed to register bank for E-mandate. Please
												contact admin.</span>
										</div>
									</c:when>
								</c:choose>
								<p>${MANDATE_REMARKS }</p>
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
											href="${pageContext.request.contextPath}/mutual-funds/funds-explorer"><button class="btn btn-sm mdb-color darken-1 white-text">Place another Order</button> </a>
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
		</section>

	</div>
	
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>