<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
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