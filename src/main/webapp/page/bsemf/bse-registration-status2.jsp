<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>BSE registration status</title>
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
					<small class="text-muted"><i class="fas fa-check" style="color: #3fc53f;"></i> Your mutual account registration complete. Your account is registered at FreEMI and temporary password sent over mail for your login.</small>
					<hr>
					<div class="mt-3">
						<jsp:include page="bse-purchase-form-new-register.jsp"></jsp:include>
					</div>

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