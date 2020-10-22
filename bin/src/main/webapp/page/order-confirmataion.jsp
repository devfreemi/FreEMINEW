<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body>
<c:if test="${paymentsucess == 'Y'}">
	<h1>Payment success.</h1>
</c:if>
<c:if test="${paymentsucess == 'N'}">
	<h1>Unable to receive payment</h1>
</c:if>
</body>
</html>