<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>FreEMI Mutual Fund Payment Status</title>

<meta name="description" content="" />
<meta name="robots" content="noindex,follow" />

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<jsp:include page="../include/GoogleHeadTag.jsp"></jsp:include>

</head>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<jsp:include page="../include/GoogleBodyTag.jsp"></jsp:include>
	<div class="container">
		<div class=row>
			<div class="col-md-6 col-lg-6 col-sm-12 mx-auto text-center mt-5 mb-5">
				<c:if test="${bsepay.payvia == 'IB' }">
				<div class="border">
				${data }
				</div>
			</c:if>
				<c:if test="${bsepay.payvia == 'UPI' }">
				<div class="border">
					<h5 class="text-success mt-2"><Strong> Payment request has been sent </Strong></h5>
					<hr>
					<div class="row">
						<div class="col-4">
							<label>UPI ID</label>
						</div>
						<div class="col-8">
							<small>${bsepay.upiid}</small>
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label>Amount</label>
						</div>
						<div class="col-8">
							<small>Rs.${bsepay.amount}</small>
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label>Order no</label>
						</div>
						<div class="col-8">
							<small>${bsepay.orderno}</small>
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label>Response</label>
						</div>
						<div class="col-8">
							<small>${msg}</small>
						</div>
					</div>
					
					<div class="row mt-3 mb-3">
						<div class="col-12 text-center">
							<a href="${pageContext.request.contextPath}/my-dashboard"><button class="btn btn-primary btn-sm">Dashboard</button></a>
							
							<a href="/mutual-funds/mutual-fund-explorer"><button class="btn btn-primary btn-sm">Explore funds</button></a>
						</div>
						
					</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>

</html>