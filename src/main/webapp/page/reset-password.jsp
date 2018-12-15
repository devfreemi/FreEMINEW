<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset password</title>
<link href="<c:url value="${contextcdn}/resources/css/reset-password.component.css"/>" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>" rel="stylesheet">
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set" >
<div class="container freemi_container">

    <div class="row">
        <div class="col-md-6 col-lg-6" style="margin: auto; padding: 30px;">
            
            <h3 style="text-align: center">Reset your Password</h3>
            <!-- <a routerLink="Home">Go back to Home</a> -->
            <span>You have requested to reset password for user ID:
                <strong> ${userid} </strong>
            </span>
            <!-- <p>Token- {{token}}</p> -->
            <!-- <reset-password-form></reset-password-form> -->

            <div>
                    <c:if test="${STATUS == 'N' }">
                    <span>${error }</span>
                    </c:if>
                    <div class="reset-form-box">
                        <form:form action="${pageContext.request.contextPath}/reset-password.do" method="POST" commandName="resetPassword">
                            <div class="form-group">
                                <span class="span_style">
                                    <form:input type="password" class="form-control input-sm " path="newpassword" placeholder="New Password" maxlength="24"
                                        />
                                </span>
                            </div>
                            <div class="form-group">
                                <span class="span_style">
                                   
                                    <form:input type="password" class="form-control input-sm " path="confirmpassword" placeholder="Confirm New Password"
                                        maxlength="24" />
                                        <!-- <i *ngIf="newpassword == confirmpassword" class="fas fa-check"></i> -->   
                                </span>
                            </div>
                            <div>
                            	
                            </div>
                            <button type="submit" class="btn btn-sm btn-block btn-info button_block">
                                <span> Reset Password</span>
                            </button>
                        </form:form>
                    </div>
                </div>

        </div>
    </div>
</div>
</body>
</html>