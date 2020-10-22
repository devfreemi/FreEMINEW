<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<html>
<head>
<title>Card Request: FreEMI</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<jsp:include page="../include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set">
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="container mx-auto">

<h1>Credit Card Request History</h1>

<div>
<c:forEach items="${REQHISTORY}" var="element"> 
<p>asdas</p>
    <p>${element}</p>
   
</c:forEach>
</div>

</div>

<jsp:include page="../include/sub-footer.jsp"></jsp:include>
<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>