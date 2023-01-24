<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-mandates</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"><"/script>
<script type="text/javascript"
	src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<script src="<c:url value="${contextcdn}/resources/js/mandates.js" />"
	type="text/javascript" defer="defer"></script>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container">
		
		<div class="row mx-auto mt-5">
			<div class="col-12 mx-auto table-responsive">
				<h1 style="font-size: 2rem; color: #f16d2d; font-weight: 400;">E-Mandates</h1>
				<table class="table table-striped table-bordered"
					style="box-shadow: 1px 3px 5px 1px #d4cfcf;" id="dtBasicExample">
					<caption>E-mandate generation history</caption>
					<thead class="purchase-records"
						style="background: #3db4d0; color: #fff29e; font-size: 10px;">
						<tr>
							<th scope="col">Mandate Type</th>
							<th scope="col">Mandate ID</th>
							<th scope="col">Start Date</th>
							<th scope="col">End Date</th>
							<th scope="col">Investment Limit</th>
							<th scope="col">Action</th>
					</thead>
					<tbody
						style="font-size: 13px; font-family: sans-serif; font-weight: 400;">

						<c:forEach var="listVar" items="${mandates}">
							<tr>
								<td><c:if test="${ listVar.mandateType == 'N' }">
								E-Nach
								</c:if> <c:if test="${ listVar.mandateType == 'X' }">
								Biller
								</c:if></td>
								<td>${listVar.mandateId }</td>
								<td><fmt:formatDate type="date"
										value="${listVar.sipStartDate }" /></td>
								<td><fmt:formatDate type="date"
										value="${listVar.sipEndDate }" /></td>
								<td>&#8377;<fmt:formatNumber type="number"
										maxFractionDigits="1" value="${listVar.amount}" /></td>
								<td><button class="btn btn-sm btn-primary mt-0"
										class="mandateaction"
										onclick="mandatestatus('${listVar.clientCode }','${listVar.mandateId }')">Status</button>
								</td>
							</tr>
						</c:forEach>

					</tbody>

				</table>

			</div>
		</div>
	</div>
	<form:form action="">
		<input type="hidden" id="m">
		<input type="hidden" id="c">
	</form:form>
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
	<jsp:include page="mandate-modal.jsp"></jsp:include>
</body>
</html>