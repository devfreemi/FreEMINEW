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

/* .dataTables_wrapper .dataTables_paginate .paginate_button.current, .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
    color: white !important;
    border: 1px solid #979797;
    background-color: white;
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #fff), color-stop(100%, #dcdcdc));
    background: -webkit-linear-gradient(top, #007bff 0%, #007bff 100%);
    background: -moz-linear-gradient(top, #007bff 0%, #007bff 100%);
    background: -ms-linear-gradient(top, #007bff 0%, #007bff 100%);
    background: -o-linear-gradient(top, #007bff 0%, #007bff 100%);
   background: linear-gradient(to bottom, #007bff 0%, #007bff 100%);
} */

table.dataTable thead th, table.dataTable thead td {
    padding: 2px 18px;
    border-bottom: 1px solid #6f6d6d;
}

.dataTables_wrapper .dataTables_filter input {
	margin-left: 0;
}
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container" style="min-height: 100vh;">
		<div class="row" style="margin: auto;overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">
				
				
				<div class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
							style="margin-top: 30px;">
							<table class="table table-striped table-sm" id="dtBasicExample">
								<caption>Funds Purchase History</caption>
								<thead>
									<tr>
										<th scope="col">Fund Name</th>
										<th scope="col">SchemeType</th>
										<th scope="col">NAV</th>
										<th scope="col">Minimum Investment</th>
										<th scope="col">Action</th>
								</thead>
								<tbody
									style="font-size: 13px; font-family: sans-serif; font-weight: 400;">

									<c:forEach var="listVar" items="${fundsexplorer}">
										<tr>
											<td><strong>${listVar.fundName }</strong></td>
											<td>${listVar.schemeType }</td>
											<td>${listVar.nav }</td>
											<td>${listVar.minInv }</td>
											<td style="text-align: center;">
												<button type="button" class="btn btn-primary btn-sm"
									onclick="bseinvest('${listVar.schemeCode }','${listVar.fundName }', '${listVar.minInv }','${listVar.amcCode }','${listVar.minInv }');">
									INVEST</button>

											</td>
										</tr>
									</c:forEach>

								</tbody>

							</table>
						</div>

			</div>
		</div>

	</div>
	<jsp:include page="../include/footer.jsp"></jsp:include>
	<jsp:include page="../include/selectedfund.jsp"></jsp:include>
</body>

<script type="text/javascript">	
	$(document).ready(function() {
		$('#dtBasicExample').DataTable({
			"columns": [
			    { "orderable": true },
			    { "orderable": false },
			  /*   null,
			    null,
			    null,
			    null,
			    null, */
			    { "orderable": false },
			    { "orderable": false }
			  ],
			 "pagingType": "numbers",
		});
		$('.dataTables_length').addClass('bs-select');
		
	});

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
				alert("Status of order no: "+orderNo+"\n"+ data);
			}

		}).fail(function(response) {
			/* $('#exampleModal1').modal('hide');
			$("#signuploadstatus")
					.text(
							"Failed to submit your signature. Please try again."); */
			/* alert(response); */
			alert("Failed to get status for order- "+ orderNo);
		});

	}
</script>
<script src="<c:url value="${contextPath}/resources/js/bseinvest.js" />"></script>
</html>