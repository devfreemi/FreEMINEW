<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="<c:url value="${contextPath}/resources/js/registryFunds.js" />"></script>

<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextPath}/resources/css/registry.form1.funds.component.css"/>"
	rel="stylesheet">
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set"
	>
	<div class="container freemi_container">
		<div class="page_topic">
			<h2>
				<span style="color: #FDF906;">${type}</span> Registry
			</h2>
		</div>

		<div class="funds_list">
			<%-- <form:form commandName="fundSelect" action="/freemi/registryFunds.do"
				method="POST"> --%>
				<table class="funds_table">
					<thead class="thead_style">
						<tr>
							<th>MF Scheme name</th>
							<th>Rating</th>
							<th>Since launch Return %</th>
							<th>1 month</th>
							<th>3 months</th>
							<th>6 months</th>
							<th>1 year</th>
							<th>*Monthly savings</th>
							<th>Manage Savings</th>
						</tr>
					</thead>
					<c:forEach var="listValue" items="${fundsList}">
					<form:form action="${pageContext.request.contextPath}/registryFunds.do"
						commandName="fundSelect" method="POST">
						<form:hidden id="fundSelected"  path="fundName" value="${listValue.fundName}" />
						<form:hidden  path="fundRating" value="${listValue.fundRating}" />
						<form:hidden  path="schemeOption" value="${listValue.schemeOption}" />
						<form:hidden  path="schemeCode" value="${listValue.schemeCode}" />
						<form:hidden  path="fundReturn" value="${listValue.fundReturn}" />
						<form:hidden  path="returnOneMonth" value="${listValue.returnOneMonth}" />
						<form:hidden  path="returnThreeMonths" value="${listValue.returnThreeMonths}" />
						<form:hidden  path="returnSixMonths" value="${listValue.returnSixMonths}" />
						<form:hidden  path="returnTwelveMonths" value="${listValue.returnTwelveMonths}" />
						<form:hidden  path="monthlySavings" value="${listValue.monthlySavings}" />
						<%-- <form:hidden path="registryFund" value="${listValue}"/> --%>
						
						<tr>
							<td>${listValue.fundName}</td>
							<td>${listValue.fundRating }</td>
							<td>${listValue.fundReturn }</td>
							<td>${listValue.returnOneMonth }</td>
							<td>${listValue.returnThreeMonths }</td>
							<td>${listValue.returnSixMonths }</td>
							<td>${listValue.returnTwelveMonths }</td>
							<td>${listValue.monthlySavings}</td>
							<td>
								<%-- <a href="/freemi/registryFunds.do/${listValue.fundName}" style="text-decoration: none;"><button type="button" class="btn btn-sm btn-info btn-custom">Select</button></a> --%>
								<button type="submit" class="btn btn-sm btn-info btn-custom" onclick="">Select</button>
							</td>
						</tr>
						</form:form>
					</c:forEach>

				</table>
			<%-- </form:form> --%>

			<div>
				<span style="font-size: 9px; color: #4B4BF5">*Mutual Funds
					performance as on date 06/04/2018</span>
			</div>
		</div>
		<div class="row invdetails">
			<div class="col-md-8 col-lg-8 inv_risk">
				<span>Mutual Fund Investments are subject to market risks,
					read all scheme related documents carefully.</span>
			</div>
		</div>

	</div>



</body>
</html>