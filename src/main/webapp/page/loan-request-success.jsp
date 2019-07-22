<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan Request Received</title>
<meta name="keywords"
	content="Credit Line, Instant Credit,Personal Loan,Home Loan, Business Loan, Instant Credit, Credit Crad, No Cost EMI,personal loan, personal loan rate" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="title" content="A Comprehensive Approach for Personal Loan" />
<meta name="description"
	content="Details of Personal Loan. Personal Loan bridges the gap between what we have and what we need. Personal Loan is an unsecured loan." />
<meta name="robots" content="noindex, nofollow">

<title>FreEMI Loans</title>

<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="include/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row" style="margin: auto;">
			<%-- <div class="col-md-4 col-lg-4">
				<img
					src="<c:url value="${contextPath}/resources/images/lrp.png"/>"
					class="img-fluid"
					>
			</div> --%>

			<div class="col-md-7 col-lg-8" style="margin: auto;padding-top: 30px;">
				
				<c:if test="${REQUESTSUCCESS=='Y' }">
					<div class="alert alert-success" role="alert">
						<img
							src="<c:url value="${contextcdn}/resources/images/agent-support.png"/>"
							class="img-fluid rounded-circle" alt="Agent icon"
							style="height: 50px; border: 1px solid #ec8439; padding: 5px;">
						<span>${requestmessage }</span>
					</div>
				</c:if>
				
			</div>
		</div>

	</div>
</body>
</html>