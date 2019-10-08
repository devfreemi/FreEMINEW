<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<html>
<head>
<title>Contact Us</title>

</head>
<body class="back_set">
<!--  Include Header -->
   <jsp:include page="include/header.jsp"></jsp:include>
    <jsp:include page="include/banner_contactus.jsp"></jsp:include>  
    <main role="main" class="main" id="div1" onload="formOnLoad();">

      <section class="about_section wow slideInLeft" data-wow-duration="2s">
    
<div class="container">
          <div class="row padd_60">

		<div class="col-md-12 col-lg-12" style="text-align: center">
			<h3 class="contact_heading">Feedback / Enquiry </h3>
			<div>
				<img src="<c:url value="${contextcdn}/resources/images/contact_us.png"/>" alt="Contact Us" class="img-fluid" style="width: 160px;">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 col-lg-12" style="text-align: center; margin-bottom: 10px;">
			<span class="description">
				Our purpose is to serve you better. Hence, your feedback is important to us. Let us know your concern or contact us for any
				kind of query related to our product,website or the services.
			</span>
		</div>
	</div>
	<div class="form-details form_back">
		<div class="row">
			<c:choose>
			<c:when test="${not empty success }">
			<div class="col-md-12 col-lg-12 alert alert-primary" role="alert" style="text-align: center;">
				<span>Please mention the request ID for future reference-  ${success}</span>
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
		<div class="row">
			<div class="col-md-8 col-lg-8">
				<form:form method="POST" action="${pageContext.request.contextPath}/contact.do" commandName="contactForm">
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
										<span class="input-group-text" id="basic-addon1">
											<i class="fas fa-user" ></i>
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
										<span class="input-group-text" id="basic-addon1">
											<i class="fas fa-at" ></i>
										</span>
									</div>
									<form:input type="email" class="form-control" id="email" path="email" />
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
								<form:select class="custom-select" id="inputGroupSelect01" path="requesttopic" >
                                	<form:options items = "${contactType}" />
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
								<form:textarea class="form-control" id="feedbackdata" path="feedback" style="max-height: 200px;"></form:textarea>
							</div>
						</div>
					</div>
					<div class="row" style="margin-bottom: 20px;">
						<div class="col-md-4"></div>
						<div class="col-md-6" style="text-align: center;">
							<button type="submit" class="btn btn-primary btn-sm">
								<span class="button_work">submit
									<i class="fa fa-paper-plane" ></i>
								</span>
							</button>
						</div>
					</div>
					
				</form:form>
			</div>
			<div class="col-md-4 col-lg-4">
				<div style="text-align: center;">
					<span class="map_heading">
						Our Office location
					</span>
				</div>
				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d471220.5630325241!2d88.04953748841308!3d22.675752095704546!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39f882db4908f667%3A0x43e330e68f6c2cbc!2sKolkata%2C+West+Bengal!5e0!3m2!1sen!2sin!4v1508302075291" width="100%" height="300" frameborder="0" style="border:0" ></iframe>
			</div>
		</div>

	</div>
	</div>
	
	 </section>

    </main>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>