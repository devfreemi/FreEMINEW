<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FATCA (Foreign Account Tax Compliance Act) Declaration </title>

<meta name="description" content="" />
<meta name="robots" content="index,nofollow" />
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/mdbstyle.jsp"></jsp:include>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>
<body>
	<jsp:include page="../include/headermdb.jsp"></jsp:include>
	<div class="container" style="min-height: 100vh;">
		<h1 style="font-size: 26px;">FATCA DECLARATION</h1>

		<div class="row">
			<div class="col-md-12 col-lg-12">
				<jsp:include page="fatca-form.jsp"></jsp:include>

			</div>
		</div>

	</div>

</body>

</html>