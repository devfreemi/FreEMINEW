<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreEMI MF</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description" content="" />
<meta name="robots" content="index,nofollow" />
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
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

		<section class="purchase_status">
			<div class="row" style="margin: auto;">
				<div class="col-md-6 col-lg-6"
					style="margin: auto; text-align: center; padding: 20px; background: aliceblue;">
					<c:choose>
						<c:when test="${TRANS_STATUS == 'COMPLETE' }">
							<h5>Your purchase is complete!</h5>

							<section style="margin-top: 30px;">
								<div class="row" style="margin: auto;">
									<div class="col-md-6 col-lg-6"
										style="margin: auto; text-align: center;">
										<a class="transaction-redirect1"
											style="text-decoration: none;"
											href="${pageContext.request.contextPath}/mutual-funds/top-performing">Place
											Another Order</a>
									</div>
								</div>
							</section>
						</c:when>

						<c:when test="${TRANS_STATUS == 'Y' }">
							<h5>Order placed successfully</h5>
							<h6>Transaction Reference No - ${TRANS_ID }</h6>

							<c:if test="${orderUrl.statusCode == '100' }">
								<a href="${orderUrl.payUrl }">
									<button>Complete your payment</button>
								</a>
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
										<a class="transaction-redirect1"
											style="text-decoration: none;"
											href="${pageContext.request.contextPath}/mutual-funds/top-performing">Place
											Another Order</a>
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
</body>
</html>