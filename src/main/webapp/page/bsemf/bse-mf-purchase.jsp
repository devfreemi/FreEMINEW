<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreEMI MF Transaction</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<meta name="description" content="" />
<meta name="robots" content="noindex,follow" />
<%-- <link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet"> --%>
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">

<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
</head>
<body>
<jsp:include page="/page/include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="../include/header.jsp"></jsp:include>

	<div class="container">
		<section style="margin-bottom: 1rem;">
			<c:if test="${REG_COMPLETE == 'N' }">
				<div class="row" style="margin: auto;">
					<div class="col-md-8 col-lg-8"
						style="margin: auto; border: 1px solid #a4a5a7; padding: 5px;">

						<div>
							<h6 style="font-weight: 500;" class="animated shake">
								<img
									src="<c:url value="${contextcdn}/resources/images/invest/warning1.jpg"/>"
									alt="Investor icon" style="height: 1.5rem;"> Profile
								registration is Incomplete. Kindly complete the registration! <br>
							</h6>
							<a href="/products/my-dashboard"><button
									class="btn btn-sm #ef5350 red lighten-1 white-text">Check
									your registration status here</button> </a>
						</div>
					</div>
				</div>
			</c:if>
		</section>

		<section style="margin-bottom: 3rem;">
			<div class="row" style="margin: auto;">
				<div class="col-md-8 col-lg-8 p-3" style="  box-shadow: 0 0 5px 0px #a8a3a3; border-radius: 5px;margin: auto;">

					<c:if test="${errormsg != null}">
						<div class="alert alert-danger" role="alert">${errormsg}</div>
					</c:if>
					
					<jsp:include page="bse-purchase-form.jsp"></jsp:include>
				</div>

			</div>
		</section>


	</div>
	
	<!-- BSE MF  -->
	<jsp:include page="./bsestarmfpowered.jsp"></jsp:include>

	<jsp:include page="./disclaimer.jsp"></jsp:include>


	<!-- END BSE MF  -->
	<jsp:include page="../include/footer.jsp"></jsp:include>
	<script src="<c:url value="${contextcdn}/resources/js/bseinvest.js" />"
		async="true"></script>

</body>
</html>