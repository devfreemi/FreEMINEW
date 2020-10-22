<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Contact Us: FreEMI</title>
<meta name="keywords"
	content="contact FreEMI,call FreEMI, mail FreEMI, FreEMI address" />
<meta name="description"
	content="Reach out to FreEMI for any suggestion, improvement, issue with website" />
<meta name="author" content="FreEMI" />
<link href="<c:url value="${contextcdn}/resources/css/contact.component.css"/>" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
		<div class="row">
			<div class="col-md-12 col-lg-12" style="text-align: center">
				<h3 class="contact_heading">Feedback / Enquiry</h3>
				<div>
					<img
						src="<c:url value="${contextcdn}/resources/images/contact_us.png"/>"
						class="img-fluid" style="width: 160px;">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-lg-12"
				style="text-align: center; margin-bottom: 10px;">
				<span class="description"> Our purpose is to serve you
					better. Hence, your feedback is important to us. Let us know your
					concern or contact us for any kind of query related to our
					product,website or the services. </span>
			</div>
		</div>
		<div class="form-details form_back">
			<div class="row">
				<c:choose>
					<c:when test="${not empty success }">
						<div class="col-md-12 col-lg-12 alert alert-primary" role="alert"
							style="text-align: center;">
							<span>Please mention the request ID for future reference-
								${success}</span>
						</div>
					</c:when>
					<c:when test="${not empty error }">
						<div class="col-md-12 col-lg-12 alert alert-danger" role="alert"
							style="text-align: center;">
							<span>${error}</span>
						</div>
					</c:when>
					<c:otherwise>
						<span></span>
					</c:otherwise>
				</c:choose>
				<!--  -->

			</div>
			<div class="row">
				<div class="col-md-8 col-lg-8">
					<form:form method="POST"
						action="${pageContext.request.contextPath}/contact.do"
						commandName="contactForm">
						<div>
							<form:errors path="*" cssClass="error" element="div" />
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<label>Your name or mobile no.</label>
								</div>
								<div class="col-md-6">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1"> <i
												class="fas fa-user"></i>
											</span>
										</div>
										<form:input type="text" class="form-control" path="contactid" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<label>Email Address</label>
								</div>
								<div class="col-md-6">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1"> <i
												class="fas fa-at"></i>
											</span>
										</div>
										<form:input type="email" class="form-control" id="email"
											path="email" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<label>Mention a topic</label>
								</div>
								<div class="col-md-6">
									<form:select class="custom-select" id="inputGroupSelect01"
										path="requesttopic">
										<form:options items="${contactType}" />
									</form:select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<label>Your feedback/enquiry</label>
								</div>
								<div class="col-md-6">
									<form:textarea class="form-control" id="feedbackdata"
										path="feedback" style="max-height: 200px;"></form:textarea>
								</div>
							</div>
						</div>
						<div class="row" style="margin-bottom: 20px;">
							<div class="col-md-4"></div>
							<div class="col-md-6" style="text-align: center;">
								<button type="submit" class="btn btn-primary btn-sm">
									<span class="button_work">submit <i
										class="fa fa-paper-plane"></i>
									</span>
								</button>
							</div>
						</div>

					</form:form>
				</div>
				<div class="col-md-4 col-lg-4">
					<div style="text-align: center;">
						<span class="map_heading"> Our Office location </span>
					</div>
					<iframe
						src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d471220.5630325241!2d88.04953748841308!3d22.675752095704546!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39f882db4908f667%3A0x43e330e68f6c2cbc!2sKolkata%2C+West+Bengal!5e0!3m2!1sen!2sin!4v1508302075291"
						width="100%" height="300" frameborder="0" style="border: 0"></iframe>
				</div>
			</div>

		</div>
	</div>

	<jsp:include page="include/sub-footer.jsp"></jsp:include>
	<jsp:include page="include/footer.jsp"></jsp:include>

</body>
</html>