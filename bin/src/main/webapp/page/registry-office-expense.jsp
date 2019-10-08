<!DOCTYPE html>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="keywords" content="" />


<meta name="title" content="Registry - Office Expense" />
<meta name="description" content="" />
<meta name="robots" content="index,follow" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registry Product</title>
<link href="<c:url value="${contextPath}/resources/css/registry.form8.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextPath}/resources/js/registry-registry-expense.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>

<body class="back_set"
	onload="formOnLoad();">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
	<div>
    <div class="page_topic">
        <h2>
            <span style="color: rgb(255, 252, 88)">Daily Office Expense</span>
        </h2>
        <h4 style="color: aliceblue;">Build your Registry</h4>
    </div>

    <div class="row">
        <div class="col-12 imaginary">
            <img src="<c:url value="${contextPath}/resources/images/boy.png"/>" class="img-fluid" style="    float: right;
        margin-top: -100px;
        height: 250px;">
            <h2>Ohh… is it, can I plan for my daily office expenses?</h2>
            <h3>It's amazing right?</h3>
        </div>
    </div>

    <div class="row form_entry" style="margin-top: 20px;">
        <div class="col-md-6 col-lg-6 form-border" style="margin: auto;">
            <form:form commandName="officeExpenseForm" action="${pageContext.request.contextPath}/registry-mutual-funds/office-expense.do" method="POST">

                <div class="row">
                    <div class="col-md-4 col-lg-4 label_box">
                        <label class="label_style">Company Name</label>
                    </div>
                    <div class="col-md-8 col-lg-8">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <i class="fas fa-building" aria-hidden="true"></i>
                                </span>
                            </div>
                            <form:input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" pattern="[0-9a-zA-Z .]*" maxlength="128"
                                path="officeName" />
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-4 col-lg-4 label_box">
                        <label class="label_style">Designation/Role</label>
                    </div>
                    <div class="col-md-8 col-lg-8">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">
                                    <i class="fas fa-users" aria-hidden="true"></i>
                                </label>
                            </div>
                            <form:select class="custom-select" id="inputGroupSelect01"
									path="designation">
									<!-- <option *ngFor="let options of loanTypes" [value]="options">{{options}}</option> -->
									<form:options items="${officeRole}" />
								</form:select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 col-lg-4 label_box">
                        <label class="label_style">Conveyance type</label>
                    </div>
                    <div class="col-md-8 col-lg-8">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect02">
                                    <i class="fas fa-bus" aria-hidden="true"></i>
                                </label>
                            </div>
                            <form:select class="custom-select" id="inputGroupSelect02"
									path="travelMode">
									<!-- <option *ngFor="let options of loanTypes" [value]="options">{{options}}</option> -->
									<form:options items="${transportType}" />
								</form:select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 col-lg-4 label_box">
                        <label class="label_style">Weekly Expenses</label>
                    </div>
                    <div class="col-md-8 col-lg-8">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <i class="fas fa-rupee-sign" aria-hidden="true"></i>
                                </span>
                            </div>
                            <form:input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" pattern="[0-9]*" maxlength="5"
                                placeholder="Enter an amount" path="weeklyExpense" />
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-12 col-lg-12" style="text-align: center">
                        <button type="submit" class="btn btn-sm btn-primary" >CREATE EXPENSE REGISTRY</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
	</div>
</head>
<body>

</body>
</html>