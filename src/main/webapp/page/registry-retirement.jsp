<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="keywords" content="" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="title" content="Registry - Retirement planning" />
<meta name="description" content="" />
<meta name="robots" content="index,follow" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registry Product</title>
<link
	href="<c:url value="${contextPath}/resources/css/registry.form2.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<script
	src="<c:url value="${contextPath}/resources/js/registry-retirement.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>

<body class="back_set"
	
	onload="formOnLoad();">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
			<div class="page_topic">
				<h2>Build your Registry</h2>
				<h4>Retirement plans</h4>
			</div>
			<div class="row">
				<div class="col-md-6 col-lg-6"
					style="margin: auto; border: 1px groove; padding: 10px;">
					<form:form commandName="retirementForm" action="${pageContext.request.contextPath}/registry-mutual-funds/registry-retirement.do" method="POST">
						<div class="row">
							<div class="col-md-6 col-lg-6">
								<label class="label_style">Your retirement (in yrs.)</label>
							</div>
							<div class="col-md-6 col-lg-6">
								<label class="container_radio">5 yrs <form:radiobutton
										checked="checked" value="5" path="tenure" /> <span
									class="checkmark"></span>
								</label> <label class="container_radio">10 yrs <form:radiobutton
										value="10" path="tenure" /> <span class="checkmark"></span>
								</label> <label class="container_radio">15 yrs <form:radiobutton
										value="15" path="tenure" /> <span class="checkmark"></span>
								</label> <label class="container_radio">20 yrs <form:radiobutton
										value="20" path="tenure" /> <span class="checkmark"></span>
								</label> <label class="container_radio">25 yrs <form:radiobutton
										value="25" path="tenure" /> <span class="checkmark"></span>
								</label>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 col-lg-6">
								<label class="label_style">Current expenses</label>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="fas fa-rupee-sign" aria-hidden="true"></i>
										</span>
									</div>
									<form:input type="text" class="form-control"
										aria-label="Username" aria-describedby="basic-addon1"
										pattern="[0-9]*" maxlength="8" placeholder="Enter amount"
										path="currentExpense" />
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-md-6 col-lg-6">
								<label class="label_style">Inflation</label>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="fa fa-percent" aria-hidden="true"></i>
										</span>
									</div>
									<form:input type="text" class="form-control"
										aria-label="Username" aria-describedby="basic-addon1"
										pattern="[0-9]*" maxlength="8" placeholder="Current inflation"
										path="inflation" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 col-lg-6">
								<label class="label_style">Current Investment in Equity
									/ Equity MF</label>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="fas fa-rupee-sign" aria-hidden="true"></i>
										</span>
									</div>
									<form:input type="text" class="form-control"
										aria-label="Username" aria-describedby="basic-addon1"
										pattern="[0-9]*" maxlength="8"
										placeholder="Existing investment amount"
										path="currentEquityInvestment" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 col-lg-6">
								<label class="label_style">Current Investment in FD /
									Debt MF</label>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="fas fa-rupee-sign" aria-hidden="true"></i>
										</span>
									</div>
									<form:input type="text" class="form-control"
										aria-label="Username" aria-describedby="basic-addon1"
										pattern="[0-9]*" maxlength="8" placeholder="Enter an amount"
										path="currentFDDebtInvestment" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 col-lg-6">
								<label class="label_style">Expected Return</label>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="fas fa-rupee-sign" aria-hidden="true"></i>
										</span>
									</div>
									<form:input type="text" class="form-control"
										aria-label="Username" aria-describedby="basic-addon1"
										pattern="[0-9]*" maxlength="8" placeholder="Enter an amount"
										path="expectedReturn" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 col-lg-6">
								<label class="label_style">Retirement Corpus required</label>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="fas fa-rupee-sign" aria-hidden="true"></i>
										</span>
									</div>
									<form:input type="text" class="form-control"
										aria-label="Username" aria-describedby="basic-addon1"
										pattern="[0-9]*" maxlength="8"
										placeholder="Existing EMI amount"
										path="retirementCorpusRequired" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12 col-lg-12" style="text-align: center">
								<button type="submit" class="btn btn-outline-info">CREATE
									REGISTRY</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>

	</div>

</body>
</html>