<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreEMI MF Transaction</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<div class="container" style="min-height: 100vh;">
		<div class="row" style="margin: auto;">
			<div class="col-md-10 col-lg-10"
				style="margin: auto; margin-top: 30px;">
				<h1 style="font-size: 2rem; color: #797d6f; font-family: monospace;">TRACK
					YOUR TRANSCTIONS</h1>
				<c:choose>
					<c:when test="${PURCHASE_LIST =='ID_NOT_FOUND' }">
						<div>
							<h5>You do not seem registered for investment to view this
								page.</h5>
							<h5>Get Registered here</h5>
							<span>*If you think you have already invested, kindly
								contact admin</span>
						</div>
					</c:when>

					<c:when test="${PURCHASE_LIST =='ERROR' }">
						<h5>failed to fetch your purchase history. Please try after
							sometime</h5>
					</c:when>

					<c:when test="${PURCHASE_LIST =='NONE' }">
						<h5>No purchase history found</h5>
					</c:when>

					<c:when test="${PURCHASE_LIST =='SUCCESS' }">
						<div class="purchase-history-table"
							style="margin-top: 30px; overflow-x: auto;">
							<table class="table table-sm table-bordered"
								style="box-shadow: 1px 3px 5px 1px #d4cfcf;">
								<caption>Registry Purchase History</caption>
								<thead class="purchase-records"
									style="background: #3db4d0; color: #fff29e; font-size: 15px;">
									<tr>
										<th scope="col">TRANSACTION REFERENCE</th>
										<th scope="col">INVEST DATE</th>
										<th scope="col">ORDER NO</th>
										<th scope="col">STATUS</th>
								</thead>
								<tbody
									style="font-size: 13px; font-family: sans-serif; font-weight: 400;">

									<c:forEach var="listVar" items="${PURCHASE_ORDERS}">
										<tr>
											<td>${listVar.uniqueReferenceNo }</td>
											<td>${listVar.createdOn }</td>
											<td>${listVar.orderNoOrSipRegNo }</td>
											<td style="text-align: center;">
												<button class="btn btn-sm btn-secondary"
													onclick="getbseOrderPaymentStatus('${listVar.clientCode}','${listVar.orderNoOrSipRegNo }' )">View
													Transaction status</button>

											</td>
										</tr>
									</c:forEach>

								</tbody>

							</table>
						</div>

					</c:when>
				</c:choose>

			</div>
		</div>
		
	</div>
	<jsp:include page="../include/footer.jsp"></jsp:include>
	
</body>

<script type="text/javascript" defer="defer">
	function getbseOrderPaymentStatus(clientId, orderNo) {
		console.log("Order staus for id- " + clientId + " : " + orderNo);
		$.get("/products/mutual-funds/orderpaymentStatus", {
			client : clientId,
			order : orderNo
		}, function(data, status) {

			console.log(data);
			$('#exampleModal1').modal('hide');
			if (data == 'NO_SESSIION') {

				alert("Invalid request");

			} else if (data == 'REQUEST_DENIED') {
				alert("Session not found!")
			} else {
				alert(data);
			}

		}).fail(function(response) {
			/* $('#exampleModal1').modal('hide');
			$("#signuploadstatus")
					.text(
							"Failed to submit your signature. Please try again."); */
			alert(response);
		});

	}
</script>
</html>