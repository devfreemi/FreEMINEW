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
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container">

		<section class="purchase_status">
			<div class="row" style="margin: auto;">
				<div class="col-md-6 col-lg-6"
					style="margin: auto; text-align: center; padding: 20px; background: aliceblue;">
					<c:if test="${TRANS_STATUS == 'Y' }">
						<h5>Order placed successfully</h5>
						<h6>Transaction Reference No - ${TRANS_ID }</h6>
					</c:if>
					<c:if test="${TRANS_STATUS == 'N' }">
						<h5>Failed to process your request currently. Kindly try
							after sometime</h5>
					</c:if>
				</div>
			</div>
		</section>

		<section style="margin-top: 30px;">
			<div class="row" style="margin: auto;">
				<div class="col-md-6 col-lg-6"
					style="margin: auto; text-align: center;">
					<a class="transaction-redirect1" style="text-decoration: none;"
						href="${pageContext.request.contextPath}/mutual-funds/top-performing">Place
						Another Order</a>
				</div>
			</div>
		</section>

	</div>
</body>
</html>