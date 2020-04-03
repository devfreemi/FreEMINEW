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
<title>FreEMI Mutual Fund Transaction</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Mutual Fund Transaction page" />
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
	<div class="container-fluid"
		style="min-height: 100vh; margin-bottom: 5rem;">
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
							<a href="/products/mutual-funds/funds-explorer"><button
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
										<!-- <th scope="col">SCHEME CODE</th> -->
										<th scope="col">SCHEME DETAILS</th>
										<th scope="col">CATEGORY</th>
										<th scope="col">INVESTMENT TYPE</th>
										<th scope="col">SIP START DATE</th>
										<th scope="col">SIP END DATE</th>
										<th scope="col">INVEST AMOUNT</th>
										<th scope="col">TRANSACTION DATE</th>
										<th scope="col">ORDER NO</th>
										<th scope="col">ACTION</th>
								</thead>
								<tbody
									style="font-size: 13px; font-family: sans-serif; font-weight: 400;">

									<c:forEach var="listVar" items="${PURCHASE_ORDERS}">
										<tr>
											<td>${listVar.transactionId }</td>
											<%-- <td>${listVar.schemeCode }</td> --%>
											<td>${listVar.schemeName }<strong>/</strong>${listVar.schemeCode }</td>
											<td>${listVar.transctionType }</td>
											<td>${listVar.investType }</td>
											<td>${listVar.sipStartDate }</td>
											<td>${listVar.sipEndDate }</td>
											<td>${listVar.investAmount }</td>
											<td>${listVar.orderTime }</td>
											<td>${listVar.orderNo }</td>
											<td style="text-align: center;">

												<div class="btn-group">
													<button type="button"
														class="btn btn-secondary dropdown-toggle btn-sm"
														data-toggle="dropdown" aria-haspopup="true"
														aria-expanded="false"
														style="font-size: 11px; padding: 10px; width: 5rem;">ACTION</button>
													<div class="dropdown-menu dropdown-menu-right">

														<c:choose>
															<c:when
																test="${listVar.transctionType =='PURCHASE' && listVar.investType == 'SIP'  }">
																<button class="dropdown-item" type="button"
																	style="font-size: 12px; color: #238019; font-weight: 600;"
																	onclick="getbseOrderPaymentStatus('${listVar.clienId}','${listVar.orderNo }' )">
																	Order Status</button>
																<button class="dropdown-item" type="button"
																	data-toggle="modal" data-target="#sipcancelmodal"
																	data-orderno="${listVar.orderNo }" data-amount="${listVar.investAmount }" data-clientid="${listVar.clienId}" data-transactionid="${listVar.transactionId}"
																	style="font-size: 12px; color: red; font-weight: 600;">
																	Cancel SIP</button>
															</c:when>

															<c:when
																test="${listVar.transctionType =='PURCHASE' && listVar.investType == 'LUMPSUM'  }">
																<button class="dropdown-item" type="button"
																	style="font-size: 12px; color: #238019; font-weight: 600;"
																	onclick="getbseOrderPaymentStatus('${listVar.clienId}','${listVar.orderNo }' )">
																	Payment Status</button>

																<button class="dropdown-item" type="button"
																	style="font-size: 12px; color: #d82f2f; font-weight: 600;"
																	onclick="cancelOrder('${listVar.clienId}','${listVar.orderNo}','${listVar.investType }','${listVar.transctionType }','${listVar.transactionId }' )">
																	Cancel Order</button>
															</c:when>

															<c:otherwise>
																<button class="dropdown-item" type="button"
																	style="font-size: 12px; color: #238019; font-weight: 600;"
																	onclick="getbseOrderPaymentStatus('${listVar.clienId}','${listVar.orderNo }' )">
																	Payment Status</button>
															</c:otherwise>
														</c:choose>

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


	<!-- Modal -->
	<div class="modal fade" id="sipcancelmodal" tabindex="-1" role="dialog"
		aria-labelledby="sipcancellabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header" style="background: #44ca9e;">
					<h5 class="modal-title white-text" id="sipcancellabel">Cancel your SIP</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="white-text" aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div>
					<span id="cancelresponse" class="text-primary"></span>
					</div>
					<form action="" id="sipcancelform">
						<div class="row mt-2">
							<div class="col-4">
								<label class="text-muted">SIP Order No</label>
							</div>
							<div class="col-8">
								<input type="text" id="orderno" name="orderno" class="form-control form-control-plaintext" readonly="readonly">
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<label class="text-muted">Invest Amount</label>
							</div>
							<div class="col-8">
							<input type="text" id="sipamnt" name="sipamnt" class="form-control form-control-plaintext" readonly="readonly">
							</div>
						</div>
						<input type="hidden" value="" id="formclientid">
						<input type="hidden" value="" id="siptransactionid">
						
						<div class="mt-2 text-center">
						<button type="submit" class="btn btn-sm btn-deep-orange" id="cancelsubmitbtn">Cancel
							SIP</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>





	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>

</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('#dtBasicExample').DataTable({
			"columns" : [ {
				"orderable" : false
			},
			/*   { "orderable": false }, */
			null, null, null, null, null, null, null, {
				"orderable" : false
			}, {
				"orderable" : false
			} ],
			"order" : [ [ 7, "desc" ] ]
		});
		$('.dataTables_length').addClass('bs-select');

	});

	$("#sipcancelmodal").on('show.bs.modal', function(event) {
		//console.log("Modal called")
		if ($(event.relatedTarget).attr("data-orderno") == undefined) {
			$("#orderno").val("NA");
		} else {
			$("#orderno").val($(event.relatedTarget).attr("data-orderno"));
		}

		if ($(event.relatedTarget).attr("data-amount") == undefined) {
			$("#sipamnt").val("NA");
		} else {
			$("#sipamnt").val($(event.relatedTarget).attr("data-amount"));
		}

		$("#formclientid").val($(event.relatedTarget).attr("data-clientid"));
		$("#siptransactionid").val($(event.relatedTarget).attr("data-transactionid"));

	});
</script>

<script src="<c:url value="${contextcdn}/resources/js/investment.js" />"></script>
</html>