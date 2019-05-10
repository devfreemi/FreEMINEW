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

<jsp:include page="../include/mdbstyle.jsp"></jsp:include>

<link
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<style type="text/css">
table td {
	font-size: 12px;
	font-weight: 300;
}

table.dataTable tbody th, table.dataTable tbody td {
	padding: 2px 2px;
}

.dataTables_wrapper .dataTables_paginate .paginate_button.current,
	.dataTables_wrapper .dataTables_paginate .paginate_button.current:hover
	{
	color: white !important;
	border: 1px solid #979797;
	background-color: white;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #fff),
		color-stop(100%, #dcdcdc));
	background: -webkit-linear-gradient(top, #007bff 0%, #007bff 100%);
	background: -moz-linear-gradient(top, #007bff 0%, #007bff 100%);
	background: -ms-linear-gradient(top, #007bff 0%, #007bff 100%);
	background: -o-linear-gradient(top, #007bff 0%, #007bff 100%);
	background: linear-gradient(to bottom, #007bff 0%, #007bff 100%);
}

table.dataTable thead th, table.dataTable thead td {
	padding: 2px 18px;
	border-bottom: 1px solid #6f6d6d;
}
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container" style="min-height: 100vh;">
		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">
				<h1 style="font-size: 2rem; color: #f16d2d; font-weight: 400;">TRACK
					YOUR TRANSCTIONS</h1>
				<c:choose>
					<c:when test="${PURCHASE_LIST =='ID_NOT_FOUND' }">
						<div>
							<h5 style="color: #e24c67; font-family: serif;">You do not
								seem registered for investment to view this page.</h5>
							<h5>Get Registered here</h5>
							<span>*If you think you have already invested, kindly
								contact admin</span>
						</div>
					</c:when>

					<c:when test="${PURCHASE_LIST =='ERROR' }">
						<h5 style="color: #e24c67; font-family: serif;">Failed to
							fetch your purchase history. Please try after sometime</h5>
					</c:when>

					<c:when test="${PURCHASE_LIST =='NONE' }">
						<h5 style="color: #2793e2; font-family: serif;">No purchase
							history found</h5>
						<div style="text-align: center;">
							<a href="/products/mutual-funds/top-performing"><button
									class="btn btn-sm btn-info">
									Make your 1<sup>st</sup>Investment
								</button></a>
						</div>
					</c:when>

					<c:when test="${PURCHASE_LIST =='SUCCESS' }">
						<div class="dataTables_wrapper dt-bootstrap4 animated fadeIn"
							style="margin-top: 30px;">
							<table class="table table-striped table-bordered"
								style="box-shadow: 1px 3px 5px 1px #d4cfcf;" id="dtBasicExample">
								<caption>Funds Purchase History</caption>
								<thead class="purchase-records"
									style="background: #3db4d0; color: #fff29e; font-size: 10px;">
									<tr>
										<th scope="col">TRANSACTION REFERENCE</th>
										<th scope="col">SCHEME CODE</th>
										<th scope="col">CATEGORY</th>
										<th scope="col">INVESTMENT TYPE</th>
										<th scope="col">SIP START DATE</th>
										<th scope="col">SIP END DATE</th>
										<th scope="col">INVEST AMOUNT</th>
										<th scope="col">INVEST DATE</th>
										<th scope="col">ORDER NO</th>
										<th scope="col">ACTION</th>
								</thead>
								<tbody
									style="font-size: 13px; font-family: sans-serif; font-weight: 400;">

									<c:forEach var="listVar" items="${PURCHASE_ORDERS}">
										<tr>
											<td>${listVar.transactionId }</td>
											<td>${listVar.schemeCode }</td>
											<td>${listVar.transctionType }</td>
											<td>${listVar.investType }</td>
											<td>${listVar.sipStartDate }</td>
											<td>${listVar.sipEndDate }</td>
											<td>${listVar.investAmount }</td>
											<td>${listVar.orderTime }</td>
											<td>${listVar.orderNo }</td>
											<td style="text-align: center;">
												<%-- <button class="btn btn-sm btn-secondary" style="padding: .1rem .5rem;"
													onclick="getbseOrderPaymentStatus('${listVar.clienId}','${listVar.orderNo }' )">
													Payment Status</button> --%> <%-- <span style="color: blue;cursor: pointer;" onclick="getbseOrderPaymentStatus('${listVar.clienId}','${listVar.orderNo }' )">Order Status</span>
												<span style="color: red;cursor: pointer;">Cancel Order</span> --%>



												<div class="btn-group">
													<button type="button"
														class="btn btn-secondary dropdown-toggle btn-sm"
														data-toggle="dropdown" aria-haspopup="true"
														aria-expanded="false"
														style="font-size: 11px; padding: 10px; width: 5rem;">ACTION</button>
													<div class="dropdown-menu dropdown-menu-right">
														<button class="dropdown-item" type="button"
															style="font-size: 12px; color: #238019; font-weight: 600;"
															onclick="getbseOrderPaymentStatus('${listVar.clienId}','${listVar.orderNo }' )">
															Payment Status</button>
														
														<c:if test="${listVar.transctionType =='PURCHASE' }">
														<button class="dropdown-item" type="button"
															style="font-size: 12px; color: #d82f2f; font-weight: 600;"
															onclick="cancelOrder('${listVar.clienId}','${listVar.orderNo}','${listVar.investType }','${listVar.transctionType }','${listVar.transactionId }' )">
															<%-- onclick="cancelOrder('${listVar.schemeCode}','${listVar.orderNo },'${listVar.investType }')"> --%>
															Cancel Order</button>
														</c:if>

													</div>
												</div>



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

<script type="text/javascript">
	$(document).ready(function() {
		$('#dtBasicExample').DataTable({
			"columns": [
			    { "orderable": false },
			    { "orderable": false },
			    null,
			    null,
			    null,
			    null,
			    null,
			    null,
			    { "orderable": false },
			    { "orderable": false }
			  ]
		});
		$('.dataTables_length').addClass('bs-select');
		
	});

	
</script>

<script
	src="<c:url value="${contextPath}/resources/js/investment.js" />"></script>
</html>