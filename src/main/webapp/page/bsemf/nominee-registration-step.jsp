
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
				<h5 class="mb-3"><strong> Nominee registration successful</strong></h5>
				<form:form class="form cf" id="nomineeverification"
					action="${pageContext.request.contextPath}/nominee-registration/mutual-funds-authenticate"
					method="POST" commandName="response">
					<div>
					<small class="text-danger text-success mb-3">${errormsg}</small>
					</div>
					<div>
					<small class="text-danger text-success mb-3">Nominee submit status - ${response.remarks}</small>
					</div>
					<div style="text-align: center; margin-top: 20px;">
						<form:button type="submit"
							class="btn #00796b teal darken-2 white-text" id="confirmbtn">Authenticate
							</form:button>
					</div>
				</form:form>
			</div>
		</div>
	</div>


	<jsp:include page="./disclaimer.jsp"></jsp:include>
	<!-- END BSE MF  -->
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>