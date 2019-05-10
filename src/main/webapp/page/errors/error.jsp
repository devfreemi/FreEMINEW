<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<jsp:include page="../include/bootstrap.jsp"></jsp:include>
</head>
<body class="login_design" >
	<jsp:include page="../include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container" style="min-height: 100vh; padding-top: 30px;text-align: center;">
		<img
			src="<c:url value="${contextcdn}/resources/images/error500.png"/>"
			class="img-fluid" alt="Error!" style="box-shadow: 2px 4px 10px 3px #a7a7a7;">
	</div>
</body>
</html>