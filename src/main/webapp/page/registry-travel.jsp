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


<meta name="title" content="Registry - Travel plan" />
<meta name="description" content="" />
<meta name="robots" content="index,follow" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registry Product</title>
<link href="<c:url value="${contextPath}/resources/css/registry.form5.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextPath}/resources/js/registry-travel.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>

<body class="back_set"
	onload="formOnLoad();">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
	<div>
    <div class="page_topic">
        <img src="<c:url value="${contextPath}/resources/images/registry_travel.jpg"/>" >
        <div class="slide-down">
            <h2>
                    <span class="travel_design">Travel</span> Registry
            </h2>
        </div>
    </div>
    <div class="row">
        <div class="col-md-7 col-lg-7">
            <div class="form-box">
                <form:form commandName="travelForm" action="${pageContext.request.contextPath}/registry-mutual-funds/registry-travel.do" method="POST">
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Flying</label>
                        <div class="col-sm-8">
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <form:radiobutton class="form-check-input" path="flying" value="DOM" /> Domestic
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <form:radiobutton class="form-check-input"  path="flying" value="INT" /> International
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-4 col-form-label">Travel date</label>
                        <div class="col-sm-8">
                            <div class="input-group mb-3">
                               <form:input type="date" path="travelDate" class="form-control"
									onkeyup="validateForm();" onchange="validateForm();" />
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-4 col-form-label">Who are travelling with you?</label>
                        <div class="col-sm-8">
                            <div class="row">
                                <div class="col-6">
                                    <div class="input-group mb-3 mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-prepend">
                                            <button class="btn btn-secondary" type="button" onclick="deductAdults();">
                                                <i class="fas fa-minus" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                        <form:input type="text" class="form-control form-control-sm input_design" path="adults" placeholder="Adults" maxlength="1"
                                            max="9" />
                                        <div class="input-group-append">
                                            <button class="btn btn-info" type="button" onclick="addAdults();">
                                                <i class="fas fa-plus" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="input-group mb-3 mb-2 mr-sm-2 mb-sm-0">
                                        <div class="input-group-prepend">
                                            <button class="btn btn-secondary" type="button" onclick="deductKids();">
                                                <i class="fas fa-minus" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                        <form:input type="text" class="form-control form-control-sm input_design" path="kids" maxlength="1" max="9" placeholder="Kids"/>
                                        <div class="input-group-append" >
                                            <button class="btn btn-info" type="button" onclick="addKids();">
                                                <i class="fas fa-plus" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">How many days and night</label>
                        <div class="col-sm-8">
                            <div class="input-group mb-3 mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-prepend">
                                    <button class="btn btn-secondary" type="button" onclick="deductTravelDays();">
                                        <i class="fas fa-minus" aria-hidden="true"></i>
                                    </button>
                                </div>
                                <form:input type="text" class="form-control form-control-sm input_design" path="travelDays" maxlength="2" max="99" />
                                <div class="input-group-append">
                                    <button class="btn btn-info" type="button" onclick="addTravelDays();">
                                        <i class="fas fa-plus" aria-hidden="true"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-4 col-form-label"></label>
                        <div class="col-sm-8">
                            <button class="btn btn-sm btn-block btn-danger">Checkout</button>
                        </div>
                    </div>


                </form:form>
            </div>
        </div>
        <div class="col-md-5 col-lg-5" style="padding-top: 40px;">
            <!-- chart -->
            <!-- <canvas baseChart
            [data]="doughnutChartData"
            [labels]="doughnutChartLabels"
            [chartType]="doughnutChartType"
            (chartHover)="chartHovered($event)"
            (chartClick)="chartClicked($event)"></canvas> -->
        </div>
    </div>

</div>
	</div>

</body>
</html>