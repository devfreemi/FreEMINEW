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
<title>FD Purchase History</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Mutual Fund Transaction page" />
<meta name="robots" content="index,nofollow" />
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>" rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>

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
	<div class="container-fluid" style="min-height: 100vh;margin-bottom: 5rem;">
		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">
				<h1 style="font-size: 2rem; color: #f16d2d; font-weight: 400;">TRACK
					YOUR TRANSCTIONS</h1>
				<c:choose>
					<c:when test="${PURCHASE_LIST =='ID_NOT_FOUND' }">
						<div>
							<h5 style="color: #e24c67; font-family: serif;">No Savings details found</h5>
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
							<a href="/fixed-deposit"><button
									class="btn btn-sm btn-info">
									Make your 1<sup>st</sup>Savings
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
										<th scope="col">TRANSACTION ID</th>
										<th scope="col">APPLICATION NO</th>
										<th scope="col">CATEGORY</th>
										<th scope="col">FREQUENCY</th>
										<th scope="col">TENURE (in months)</th>
										<th scope="col">INT. RATE</th>
										<th scope="col">SAVING AMOUNT</th>
										<th scope="col">TRANSACTION DATE</th>
										<th scope="col">MATURITY AMOUNT</th>
										<th scope="col">MATURITY DATE</th>
										<th scope="col">Payment Complete?</th>
										<!-- <th scope="col">ACTION</th> -->
									</tr>
								</thead>
								<tbody
									style="font-size: 13px; font-family: sans-serif; font-weight: 400;">

									<c:forEach var="listVar" items="${PURCHASE_ORDERS}">
										<tr>
											<td>${listVar.transactionrefid }</td>
											<td>${listVar.applicationno }</td>
											<td>${listVar.transactiontype }</td>
											<td>${listVar.interestfrequency }</td>
											<td>${listVar.tenure }</td>
											<td>${listVar.interestrate }</td>
											<td>${listVar.purchaseamount }</td>
											<td>${listVar.transactiondate }</td>
											<td></td>
											<td></td>
											<td>${listVar.ispaymentcomplete }</td>
											
											<%-- <td style="text-align: center;">
												
												<div class="btn-group">
													<button type="button"
														class="btn btn-secondary dropdown-toggle btn-sm"
														data-toggle="dropdown" aria-haspopup="true"
														aria-expanded="false"
														style="font-size: 11px; padding: 10px; width: 5rem;">ACTION</button>
													<div class="dropdown-menu dropdown-menu-right">
														<c:if test="${listVar.ispaymentcomplete =='N'}">
														<button class="dropdown-item" type="button"
															style="font-size: 12px; color: #238019; font-weight: 600;"
															onclick="getmahindrafdpaymentstatus('${listVar.applicationno}','${listVar.mobile }' )">
															Payment Status</button>
															
															<button class="dropdown-item" type="button"
															style="font-size: 12px; color: #238019; font-weight: 600;"
															onclick="completefdpayment('${listVar.applicationno}','${listVar.mobile }','${listVar.transactionrefid }','${listVar.mfsysrefid }' )">
															Complete payment</button>
														</c:if>
														<c:if test="${listVar.transactiontype =='NEW' }">
														<button class="dropdown-item" type="button"
															style="font-size: 12px; color: #d82f2f; font-weight: 600;">
															Renew</button>
														</c:if>
														<button class="dropdown-item" type="button"
															style="font-size: 12px; color: #238019; font-weight: 600;"
															onclick="reuploadkycdoc('${listVar.applicationno}','${listVar.mobile }' )">
															Retry KYC upload</button>
													</div>
												</div>
											</td> --%>
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
	
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>

</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('#dtBasicExample').DataTable({
			"columns": [
			    { "orderable": false },
			  /*   { "orderable": false }, */
			    null,
			    null,
			    null,
			    null,
			    null,
			    null,
			    null,
			    null,
			    { "orderable": false },
			    { "orderable": false }
			  ],
			  "order": [[ 7, "desc" ]]
		});
		$('.dataTables_length').addClass('bs-select');
		
	});

	
</script>
<script
	src="<c:url value="${contextcdn}/resources/js/fd-profile.js" />"></script>
</html>