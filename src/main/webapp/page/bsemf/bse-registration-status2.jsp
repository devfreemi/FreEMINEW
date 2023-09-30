<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mutual fund registration status</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="robots" content="noindex,nofollow" />

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform2.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
</head>
<body>
	<jsp:include page="/page/include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container-fluid" style="margin-bottom: 10rem;">

		<section style="padding: 20px 0px;">
			<div class="row mx-auto">
				<div class="col-md-6 col-lg-6 mx-auto pt-3"
					style="border: 1px solid #c1bdb8; border-radius: 5px;">
					<h1 style="font-size: 1.5rem; color: #5885e9; font-weight: 400;">Congratulation!</h1>
					<small class="text-muted"><i class="fas fa-check" style="color: #3fc53f;"></i> Your mutual fund account registration complete.</small>
					<div class="text-center">
					 <a  target="_blank" href="${n2faurl}"><button class="btn btn-info" id="nomiee2fa">Authenticate Nominee</button></a>
					</div>
					<br>
					<c:if test="${PURCHASE_TYPE =='NEW_CUSTOMER' }">
						<small>Your account is registered at FreEMI and temporary password sent over mail for your login.</small>
					</c:if>
					<c:if test="${PURCHASE_TYPE =='LOGGED_NEW_CUSTOMER' }">
						<small>Proceed to complete your fund order</small>
					</c:if>
					
					<hr>
					<c:if test="${FUND_DATA == 'PROVIDED' }">
						<div class="mt-3">
						<jsp:include page="bse-purchase-form-new-register.jsp"></jsp:include>
					</div>
					</c:if>
					<c:if test="${FUND_DATA == 'EMPTY' }">
						<div class="row mx-auto">
							<div class="col-md-12 col-lg-12 text-center mb-3">
								<h6>Start your first investment</h6>
							<a href="https://www.freemi.in/mutual-funds/mutual-fund-explorer/">
								<button class="btn btn-info">Select fund</button>
							 </a>
							</div>
							<div class="col-md-12 col-lg-12 text-center mb-3">
								<h6>Check your dashboard</h6>
							<a href="/products/my-dashboard">
								<button class="btn btn-info">Visit dashboard</button>
							 </a>
							</div>
							
						</div>
						
					</c:if>
					

				</div>
			</div>
		</section>


	</div>
	<jsp:include page="./disclaimer.jsp"></jsp:include>
	<!-- BSE MF  -->
	<jsp:include page="./bsestarmfpowered.jsp"></jsp:include>
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>


</body>

<script type="text/javascript">
$( "#newuserpurhcase" ).submit(function( event ) {
	  $("#orderconfirmbtn").attr("disabled", true);
	  $("#submiticon").html("<i class=\"fas fa-spinner fa-spin\"></i>")
	});
</script>



</html>