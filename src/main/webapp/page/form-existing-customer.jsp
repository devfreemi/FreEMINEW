<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Investment form</title>

<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextcdn}/resources/css/investmentform.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="include/bootstrap.jsp"></jsp:include>

</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container-fluid freemi_container existing_look">
		<div class="container">

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8 col-lg-8"
					style="margin: auto; border: 1px solid #c7c7c7; border-radius: 4px;">
					<h5>Wish based on lumpsum</h5>
					<div class="row purchase_info">
						<div class="col-md-4 col-lg-4">
							<div class="row">
								<div class="col-6">
									<label style="font-weight: bold;">Folio Number</label>
								</div>
								<div class="col-6">
									<span id="foliono">423423423</span>
								</div>
							</div>
						</div>
						<div class="col-md-5 col-lg-5">
							<div class="row">
								<div class="col-3">
									<label style="font-weight: bold;">Investor</label>
								</div>
								<div class="col-9">
									<span id="investor">DEBASISH SARKAR</span>
								</div>
							</div>
						</div>

						<div class="col-md-3 col-lg-3" style="margin-left: -1px;">
							<div class="row">
								<div class="col-6">
									<label style="font-weight: bold;">Date</label>
								</div>
								<div class="col-6">
									<span id="trnsactdate">01/09/2018</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8 col-lg-8"
					style="margin: auto; border: 1px solid #c7c7c7;">
					<div>
						<!-- Investment Details -->
						<div class="row">
							<div class="col-4">
								<label>Fund category</label>
							</div>
							<div class="col-6">
								<label id="category"></label>
							</div>
						</div>

						<div class="row">
							<div class="col-4">
								<label>Fund sub-category</label>
							</div>
							<div class="col-6">
								<label id="sub_category"></label>
							</div>
						</div>

						<div class="row">
							<div class="col-4">
								<label>Scheme name</label>
							</div>
							<div class="col-6">
								<label id="scheme_name"></label>
							</div>
						</div>

						<div class="row">
							<div class="col-4">
								<label>Scheme option</label>
							</div>
							<div class="col-6">
								<label id="scheme_option"></label>
							</div>
						</div>

					</div>

					<div class="payment">
						<div class="row">
							<div class="col-md-6 col-lg-6" style="margin: auto;">
								<button class="btn btn-sm btn-primary">Pay</button>
							</div>

						</div>
					</div>


				</div>
			</div>


		</div>
	</div>

</body>
</html>