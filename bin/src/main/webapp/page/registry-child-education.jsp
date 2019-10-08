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


<meta name="title" content="Registry - Child Education" />
<meta name="description" content="" />
<meta name="robots" content="index,follow" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registry Product</title>
<link href="<c:url value="${contextPath}/resources/css/registry.form3.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextPath}/resources/js/registry-child-education.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>

<body class="back_set"
	onload="formOnLoad();">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
	<!-- This form is a subchild of registry. So parent class has the container_radio defined -->
<div style="margin-bottom: 60px;">
    <div class="page_topic">
        <h2>
            <span style="color:#FDF906"> Child Education</span> Registry</h2>
        <h4></h4>
    </div>
    <div class="topic_content">
        <p>Right education a duty for all parents, who believe on kids ability to grow and become successful in life, but without
            planing for cost of higher education, it might be tough in future… So lets plan for “Child Education” by creating
            a Registry account could be successful career for your Kid. We evaluate the cost and take you through a right
            investment product to achieved the goal.
        </p>
    </div>
    <div class="row form_entry form_border">
        <div class="col-md-8 col-lg-8" style="margin: auto; border: 1px groove; padding: 15px;">
            <form:form commandName="childEducationForm" action="${pageContext.request.contextPath}/registry-mutual-funds/registry-child-education.do" method="POST">
                <div class="row">
                    <div class="col-md-6 col-lg-6 label_box label_box">
                        <label class="label_style">Education duration (in yrs.)</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <label class="container_radio">5 yrs
                            <form:radiobutton checked="checked" path="tenure" value="5" />
                            <span class="checkmark"></span>
                        </label>
                        <label class="container_radio">10 yrs
                            <form:radiobutton path="tenure" value="10" />
                            <span class="checkmark"></span>
                        </label>
                        <label class="container_radio">15 yrs
                            <form:radiobutton path="tenure" value="15" />
                            <span class="checkmark"></span>
                        </label>
                        <label class="container_radio">20 yrs
                            <form:radiobutton path="tenure" value="20" />
                            <span class="checkmark"></span>
                        </label>
                        <label class="container_radio">25 yrs
                            <form:radiobutton path="tenure" value="25" />
                            <span class="checkmark"></span>
                        </label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6 label_box">
                        <label class="label_style">Current Educational cost</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <i class="fas fa-rupee-sign" aria-hidden="true"></i>
                                </span>
                            </div>
                            <form:input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" pattern="[0-9]*" maxlength="8"
                                placeholder="Enter amount" path="currentExpense" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6 label_box">
                        <label class="label_style">Inflation</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <i class="fa fa-percent" aria-hidden="true"></i>
                                </span>
                            </div>
                            <form:input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" pattern="[0-9]*" maxlength="8"
                                placeholder="Current inflation" path="inflation" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6 label_box">
                        <label class="label_style">Current Investment in Equity / Equity MF</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <i class="fas fa-rupee-sign" aria-hidden="true"></i>
                                </span>
                            </div>
                            <form:input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" pattern="[0-9]*" maxlength="8"
                                placeholder="Existing investment amount" path="currentEquityInvestment" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6 label_box">
                        <label class="label_style">Current Investment in FD / Debt MF</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <i class="fas fa-rupee-sign" aria-hidden="true"></i>
                                </span>
                            </div>
                            <form:input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" pattern="[0-9]*" maxlength="8"
                                placeholder="Enter an amount" path="currentFDDebtInvestment" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6 label_box">
                        <label class="label_style">Expected Return</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <i class="fas fa-rupee-sign" aria-hidden="true"></i>
                                </span>
                            </div>
                            <form:input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" pattern="[0-9]*" maxlength="8"
                                placeholder="Enter an amount" path="expectedReturn" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 col-lg-6 label_box">
                        <label class="label_style">Corpus required for child education</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">
                                    <i class="fas fa-rupee-sign" aria-hidden="true"></i>
                                </span>
                            </div>
                            <form:input type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" pattern="[0-9]*" maxlength="8"
                                placeholder="Enter an amount" path="retirementCorpusRequired" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 col-lg-12" style="text-align: center">
                        <button type="submit" class="btn btn-sm btn-outline-info">CREATE REGISTRY</button>
                    </div>
                </div>

            </form:form>
        </div>
    </div>

</div>
	</div>

</body>
</html>