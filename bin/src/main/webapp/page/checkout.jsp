<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Payment</title>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body>
<%-- <form action="/freemi/purchase.do" method="POST"> --%>
<form:form method="POST" action="${pageContext.request.contextPath}/homeLoan.do" >
<!-- Note that the amount is in paise = 50 INR -->
<script
    src="https://checkout.razorpay.com/v1/checkout.js"
    data-key="rzp_test_hlQUGp4Z6Grw9v"
    data-amount="5000"
    data-buttontext="Pay with Razorpay"
    data-name="Merchant Name"
    data-description="Purchase Description"
    data-image="http://localhost:8080/products/resources/images/freemi.png"
    data-prefill.name="Harshil Mathur"
    data-prefill.email="support@razorpay.com"
    data-theme.color="#F37254"
></script>
<input type="hidden" value="Hidden Element" name="hidden">
</form:form>
</body>
</html>