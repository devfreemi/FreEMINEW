<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mutual fund investment form for new customer</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="Fill up the form to complete your investment if you are an e-KYC verified customer" />
<meta name="robots" content="noindex,follow" />

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform2.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>" rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script> -->
	<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
	
 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css"> 


</head>

<body>
<jsp:include page="/page/include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container" style="margin-bottom: 4rem;">
		
		<div class="row mx-auto mt-3 mb-3">
			<div class="col-md-8 col-lg-8 mx-auto">
			<h6 class="text-muted" style="font-weight: 400;">Complete your mutual fund profile registration</h6>
			</div>

		</div>
		<div class="row mx-auto">
			<div class="col-md-8 col-lg-8 mx-auto box-style">
			
				<jsp:include page="bseform2.jsp"></jsp:include>
			</div>

		</div>
	</div>
	
	
	<!-- BSE MF  -->
		<jsp:include page="./bsestarmfpowered.jsp"></jsp:include>
	<!-- END BSE MF  -->
	<jsp:include page="../include/footer.jsp"></jsp:include>

</body>
<script>
	//Material Select Initialization
	/* $(document).ready(function() {
		$('.mdb-select').materialSelect();
		
	}); */
</script>
<script src="<c:url value="${contextcdn}/resources/js/bseinvest2.js" />"
	defer="defer"></script>
<script src="<c:url value="${contextcdn}/resources/js/investment.js" />" defer="defer"></script>
<script src="<c:url value="${contextcdn}/resources/js/signaturepanel2.js" />" async="true"></script>

</html>