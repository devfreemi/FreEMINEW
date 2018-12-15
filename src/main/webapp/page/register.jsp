<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta name="keywords" content="freemi signup, freemi register, investment portal" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="title" content="Sign up" />
<meta name="description" content="Get registered to FreEMI and invest to fill your goals" />
<meta name="robots" content="follow,index" />

<link href="<c:url value="${contextcdn}/resources/css/register.component.css"/>" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>" type="text/css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<script src="<c:url value="${contextcdn}/resources/js/register.js" />"></script>
</head>
<body class="login_design" onload="formOnLoad();">
<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" style="min-height: 400px; padding-top: 30px;">

    <!-- MAIN FORM -->

        <div class="freemi-logo">
            <img src="<c:url value="${contextcdn}/resources/images/f.png"/>" class="img-fluid" style="height: 50px;">
        </div>
        <div class="login_header">
            <span>New to FreEMI?</span>
            <div style="font-size: 20px;">
                Create your <span style="color: rgb(255, 252, 94)"> Free </span> account today
            </div>
        </div>

        <!-- Registration form -->
        <div class="row">
            <div class="col-md-6 col-lg-6 form_div animated fadeInRight" style="margin: auto;background: #d8d8d8e3;">
                <div>
                <form:errors path="*" cssClass="error" element="div" />
                </div>
                <div class="row">
			<c:choose>
			<c:when test="${not empty success }">
			<div class="col-md-12 col-lg-12 alert alert-primary" role="alert" style="text-align: center;">
				<span> ${success}</span>
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
                <form:form method="POST" action="${pageContext.request.contextPath}/register.do" commandName="registerForm">
                    <!-- <span *ngIf="isRegistratrionSuccess">Registration successful</span> -->
                    <div class="form-group">
                        <span class="span_style">
                            <i class="far fa-user icon_style" style="font-size: 27px;margin-top: 5px;"></i>
                            <form:input style="padding-left: 40px;" type="text" class="form-control input-sm" id="username" path="fullName" placeholder="Name" maxlength="128" onkeyup="validateForm();" />
                        </span>
                    </div>

                    <div class="form-group">
                        <span class="span_style">
                            <i class="fas fa-mobile-alt icon_style" style="font-size: 30px;margin-top: 5px;"></i>
                            <form:input style="padding-left: 40px;" type="text" class="form-control input-sm" id="registermobile" path="mobile" placeholder="10-digit mobile number" maxlength="10" onkeyup="validateForm();"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <span class="span_style">
                            <i class="fas fa-at icon_style" style="font-size: 27px;margin-top: 5px;"></i>
                            <form:input style="padding-left: 40px;" type="text" class="form-control input-sm " id="useremail" path="email" placeholder="Email ID" maxlength="128" onkeyup="validateForm();" />
                        </span>
                        <!-- <div *ngIf="useremail.invalid && useremail.dirty" class="alert-box alert-danger">{{EMAILREQUIRED}}</div> -->
                    </div>

                    <div class="form-group">
                        <span class="span_style">
                            <i class="fas fa-key icon_style" style="font-size: 27px;margin-top: 5px;"></i>
                            <form:input style="padding-left: 40px;" type="password" class="form-control input-sm" id="registerpassword" path="password" placeholder="Password" maxlength="24" onkeyup="validateForm();" />
                        </span>
                        <!-- <div *ngIf="registerpassword.invalid && registerpassword.dirty" class="alert-box alert-danger">{{PASSWORDREQUIRED}}</div> -->
                    </div>

                    <div>
                        <button type="submit" id="registerSubmit" class="btn btn-sm btn-block login_button" >
                            <span>Register</span>
                        </button>
                        <a href="${pageContext.request.contextPath}/login" style="text-decoration: none;">
                        <button type="button" class="btn btn-sm btn-block login_button">
                            <!-- <i class="fa fa-backward" aria-hidden="true"></i> -->
                            <span>
                                Already registered? Login
                            </span>
                        </button>
                        </a>
                    </div>
                </form:form>

            </div>

            <!-- <div class="col-md-6 col-lg-6"></div> -->
        </div>
        <div class="row">
            <div class="col-md-6 col-lg-6" style="margin: auto;padding-top: 10px; font-size: 14px; color: aliceblue;">
                <span> By clicking "Register", I agree to FreEMI <a href="${pageContext.request.contextPath}/terms-conditions" style="color: rgb(253, 244, 115)">Terms of Service</a>.</span>
            </div>
        </div>

</div>
</body>
</html>