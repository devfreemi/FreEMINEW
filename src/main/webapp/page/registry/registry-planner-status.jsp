<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>FreEMI Registry</title>

<meta name="description" content="" />
<meta name="robots" content="index,nofollow" />
<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>

<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container">

		<section class="">
			<div class="row" style="padding: 0.5rem;">
				<div class="col-md-8 col-lg-8 mt-4 mb-4 p-2"
					style="margin: auto; box-shadow: 0 0 11px 1px #dcd4d4;">
					<div class="row animated fadeIn">
						<div class="col-md-4 col-lg-4" style="margin: auto;">
							<img class="d-none d-sm-block img-fluid"
								src="<c:url value="${contextcdn}/resources/images/registry/registry-order-status.svg"/>"
								alt="Transact Status">
						</div>
						<div class="col-md-8 col-lg-8">
							<div class="mb-3">
								<img class="d-block img-fluid"
									style="height: 3rem; margin: auto;"
									src="<c:url value="${contextcdn}/resources/images/registry/registry-order-success.svg"/>"
									alt="Transaction Successful">
							</div>

							<h5 class="text-primary text-center mb-4">Your Registry
								created successfully</h5>
							<div class="animated fadeIn mt-2">
								<table class="table">
									<tbody>
										<tr>
											<th class="text-dark">Registry Name</th>
											<td>NA</td>
										</tr>
										<tr>
											<th class="text-dark">Registry ID</th>
											<td>NA</td>
										</tr>
										<tr>
											<th class="text-dark">Registry status</th>
											<td>Active</td>
										</tr>
										<tr>
											<th class="text-dark">Payment status</th>
											<td>${TRANS_STATUS }</td>
										</tr>
									</tbody>
								</table>

							</div>

							<c:if test="${TRANS_STATUS == 'Payment Pending' }">
								<div class="mt-4 text-center">
									<a href="${orderUrl.payUrl }">
										<button class="btn btn-success">
											Complete payment   <i class="fas fa-credit-card ml-1"></i>
										</button>
									</a>
								</div>
							</c:if>
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