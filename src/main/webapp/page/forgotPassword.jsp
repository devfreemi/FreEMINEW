<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link href="<c:url value="${contextcdn}/resources/css/login.component.css"/>" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<!-- <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" /> -->
<meta http-equiv="pragma" content="no-cache" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set" >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container freemi_container" style="background-color: rgb(3, 86, 155);">
	
    <div class="freemi-logo">
        <img src="<c:url value="${contextcdn}/resources/images/f.png"/>" class="img-fluid" style="height: 50px;">
    </div>


    <!-- MAIN FORM -->
        <div class="topic_forgot">
            <span> Forgot password?</span>
            <div style="font-size: 18px;">
                   You can reset your password here
                </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-lg-6 form_div" style="margin: auto;">
				<div class="row">
			<c:choose>
			<c:when test="${not empty success }">
			<div class="col-md-12 col-lg-12 alert alert-primary" role="alert" style="text-align: center;">
				<span>${success}</span>
			</div>
			</c:when>
			<c:when test="${not empty error }">
			<div class="col-md-12 col-lg-12 alert alert-danger" role="alert" style="text-align: center;">
				<span>${error}</span>
			</div>
			</c:when>
			<c:otherwise>
			<span></span>
			</c:otherwise>
			</c:choose>
			<!--  -->
	
		</div>
	                <form:form method="POST" action="${pageContext.request.contextPath}/forgotPassword.do" commandName="forgotPasswordForm">

                    <div class="form-group" style="margin-bottom: 50px;">
                        <span class="span_style">
                            <i class="fas fa-mobile-alt icon_style" style="font-size: 30px;margin-top: 5px;"></i>
                            <form:input type="text" style="padding-left: 35px;" class="form-control input-sm " path="usermobile" placeholder="Your 10-digit registered mobile number"
                                maxlength="10" />
                        </span>
                    </div>

                        <button type="submit" class="btn btn-block btn-sm login_button">
                        <!-- <i *ngIf="spinner" class="fa fa-spinner login_spin" aria-hidden="true"></i> -->
                        Reset Password
                        </button>
                     <a href="${pageContext.request.contextPath}/login" style="text-decoration: none">
                    <button type="button" class="btn btn-block btn-sm login_button" >
                        <span>
                            <i class="fas fa-backward"></i> Back</span>
                    </button>
                    </a>
                </form:form>
            </div>
            <!-- <div class="col-md-6 col-lg-6"></div> -->
        </div>

</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>