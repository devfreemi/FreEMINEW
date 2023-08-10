
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nominee registration</title>
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>



	<div class="container">
		<div class="row" style="margin-bottom: 3rem; margin-top: 3rem;">
			<div class="col-md-6 mx-auto">
				<h5 class="mb-3"><strong> Nominee verification Status - ${status }</strong></h5>
			</div>
		</div>
	</div>


	<jsp:include page="./disclaimer.jsp"></jsp:include>
	<!-- END BSE MF  -->
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>