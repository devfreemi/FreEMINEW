<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset password link sent</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<div class="container mt-5 mb-5" style="">
		<div class="row mx-auto mt-5">
			<div class="col-md-6 col-lg-6 mx-auto text-center mb-5 mt-3">
				<div class="mb-4">
					<h1 style="font-size: 2rem; font-weight: 500;">Forgot
						password</h1>
					<h6>A password link is sent to linked email ID if mobile no.
						is registered</h6>
				</div>
				<hr>
				<div class="mb-4">
					<p>
						For any query, you can write to <a href="mailto:support@freemi.in">support@freemi.in</a>
					</p>
				</div>

				<div class="text-center text-muted" style="font-size: 16px;">
					Back to <a href="${pageContext.request.contextPath}/login">
						Login </a>
				</div>
			</div>

		</div>


	</div>
	<jsp:include page="include/sub-footer.jsp"></jsp:include>
	<jsp:include page="include/footer.jsp"></jsp:include>

</body>
</html>