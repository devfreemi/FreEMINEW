<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<jsp:include page="include/GoogleHeadTag.jsp"></jsp:include>
<meta name="keywords" content="Car Insurance, Motor Insurance, health Insurance, insurance renewal, online insurance, insurance policy" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="title" content="Compare & Get Best Insurance Policy online in India" />
<meta name="description" content="Get best motor and health insurance policy comparing top insurance companies in India online in India" />
<meta name="robots" content="follow,index" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>F-Secure</title>
<link
	href="<c:url value="${contextcdn}/resources/css/fsecure.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
	
	<script src="<c:url value="${contextcdn}/resources/js/fsecure.js" />"></script>
</head>
<body class="back_set"
	 onload="formOnLoad();">
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
	
		<div class="topic_image">
			<img
				src="<c:url value="${contextcdn}/resources/images/fsecure-img.jpg"/>"
				class="img-fluid" alt="F-Secure Image">
		</div>

		<div class="plan_style">
			<span>BUY INSURANCE PLAN</span>
		</div>

		<!-- <div class="fsecure_form_border"> -->
		<div class="row fsecure-form">
			<div class="col-md-6 col-lg-6 fsecure-form-style"
				style="margin: auto;">
				<form:form commandName="fsecureForm" method="POST"
					action="#">
					<div class="row">
						<div class="col-md-4 col-lg-4 label_class">
							<label> <b>Your Name <span class="require_style">*</span>
							</b>
							</label>
						</div>
						<div class="col-md-8 col-lg-8" style="margin-right: -1px;">
							<div class="form-group">
								<div class="input-group mb-3">

									<form:input type="text" class="form-control input-sm"
										style="margin-right:2px;"
										path="fname" id="fname" placeholder="First Name" maxlength="32" onkeyup="validateForm();" />
									<form:input type="text" class="form-control input-sm"
										 path="lname" id="lname"
										placeholder="Last Name" maxlength="96" onkeyup="validateForm();" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-4 col-lg-4 label_class">
							<label> <b>Mobile number <span class="require_style">*</span>
							</b>
							</label>
						</div>
						<div class="col-md-8 col-lg-8" style="margin-right: -1px;">
							<div class="form-group">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon2"> <strong>+91</strong>
										</span>
									</div>
									<form:input type="text" class="form-control input-sm"
										aria-describedby="basic-addon2" path="mobile" id="mobile"
										placeholder="10-digit mobile number" pattern="[0-9]*"
										minlength="10" maxlength="10" value="${sessionScope.userid }" onkeyup="validateForm();" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-4 col-lg-4 label_class">
							<label> <b>Email ID <span class="require_style">*</span>
							</b>
							</label>
						</div>
						<div class="col-md-8 col-lg-8" style="margin-right: -1px;">
							<div class="form-group">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon3"> <i
											class="fa fa-at"></i>
										</span>
									</div>
									<form:input  type="email" class="form-control input-sm"
										path="email" aria-describedby="basic-addon3"
										placeholder="Email ID" maxlength="128" onkeyup="validateForm();" />

								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-4 col-md-4 col-lg-4 label_class">
							<label> <b>You are <span class="require_style">*</span>
							</b>
							</label>
						</div>
						<div class="col-8 col-md-8 col-lg-8"
							style="margin-right: -1px; padding-top: 7px;">
							<label class="control control-radio"
								style="padding-top: 0px; padding-right: 15px;"> Male <form:radiobutton
									value="M" checked="checked" path="gender" />
								<!-- <div class="control_indicator"></div> -->
							</label> <label class="control control-radio"
								style="padding-top: 0px; padding-right: 15px;"> Female <form:radiobutton
									value="F" path="gender" />
								<!-- <div class="control_indicator"></div> -->
							</label>
						</div>
					</div>

					<div class="row">
						<div class="col-4 col-md-4 col-lg-4 label_class">
							<label> <b>DOB <span class="require_style">*</span>
							</b>
							</label>
						</div>
						<div class="col-8 col-md-8 col-lg-8" style="margin-right: -1px;">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<button class="btn btn-outline-info" type="button">
										<i class="fas fa-calendar-alt"
											style="width: 1.2rem; height: 1rem; cursor: pointer;"></i>
									</button>
								</div>
								<%-- <form:input class="form-control" placeholder="Age 18 - 65 yrs (yyyy-mm-dd)" name="dob" [(ngModel)]="model" ngbDatepicker #d="ngbDatepicker"
                                    path="dob" /> --%>
								<form:input type="date" path="dob" class="form-control"
									onkeyup="validateForm();" onchange="validateForm();" />
								<small id="dobHelp" class="form-text text-muted">Age 18 - 65 yrs (yyyy-mm-dd)</small>
							</div>
							<span id="doberror"></span>
						</div>
					</div>

					<div class="row">
						<div class="col-4 col-md-4 col-lg-4 label_class">
							<label> <b>Smoker? <span class="require_style">*</span>
							</b>
							</label>
						</div>
						<div class="col-8 col-md-8 col-lg-8"
							style="margin-right: -1px; padding-top: 7px;">
							<label class="control control-radio"
								style="padding-top: 0px; padding-right: 15px;"> Yes <form:radiobutton
									value="1" checked="checked" path="isSmoker" />
								<!-- <div class="control_indicator"></div> -->
							</label> <label class="control control-radio"
								style="padding-top: 0px; padding-right: 15px;"> No <form:radiobutton
									value="0" path="isSmoker" />
								<!-- <div class="control_indicator"></div> -->
							</label>
						</div>
					</div>

					<div>
						<%-- <form:checkbox type="checkbox" path="allowcalls" /> --%>
						<!-- <span style="line-height: 1em; font-size: 12px;">I
							authorize HDFC Life to contact me overriding my registry on NDNC.</span> -->
						<label class="container1"> <span
							style="font-size: 12px; color: #21a35e;"> <strong>I
									accept <span style="color: blue; text-decoration: underline;"
									data-toggle="modal" data-target="#exampleModal">disclaimer</span> and authorize
									FreEMI.in &amp; associate company to contact me overriding my
									registry on NDNC.
							</strong>
						</span> <form:checkbox value="Y" path="allowcalls" checked="checked"/> <span
							class="checkmark" ></span>
						</label>
					</div>
					<div style="text-align: center;">
                    <button type="submit" id="fsecureSubmit" class="btn btn-primary">
                        Get Quotes
                    </button>

                </div>
				</form:form>
			</div>
		</div>

		<%-- <div class="row">
			<div class="col-md-8 col-lg-8 banner_style" style="margin: auto;">
				<div id="carouselExampleControls" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<img class="d-block img-fluid"
								src="<c:url value="${contextPath}/resources/images/Banner-01.jpg"/>"
								alt="">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid"
								src="<c:url value="${contextPath}/resources/images/Banner-02.jpg"/>"
								alt="">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid"
								src="<c:url value="${contextPath}/resources/images/Banner-03.jpg"/>"
								alt="">
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid"
								src="<c:url value="${contextPath}/resources/images/Banner-04.jpg"/>"
								alt="">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleControls"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleControls"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div> --%>

		<div class="fsecure_details1">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item"><a class="nav-link active" id="home-tab"
					data-toggle="tab" href="#home" role="tab" aria-controls="home"
					aria-selected="true"> <span> <strong>TAX
								BENEFIT</strong>
					</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" id="profile-tab"
					data-toggle="tab" href="#profile" role="tab"
					aria-controls="profile" aria-selected="false"> <span> <strong>CLAIM
								PROCESS</strong>
					</span>
				</a></li>
				<!--   <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Contact</a>
  </li> -->
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="home" role="tabpanel"
					aria-labelledby="home-tab">
					<div class="tax_benefit">
						<img src="<c:url value="${contextcdn}/resources/images/tax_benefit.png"/>" alt="Tax benefit"
							style="float: left; margin-left: 5px; opacity: 0.5; margin-right: 20px;">
						<p style="text-align: justify;">
							Within a financial year, Insurance premium paid are exempted
							under section <strong>80C of Income Tax Act</strong> upto <strong>
								&#x20B9;1,50,000</strong>
						</p>

					</div>
				</div>
				<div class="tab-pane fade" id="profile" role="tabpanel"
					aria-labelledby="profile-tab">
					<div class="tax_benefit">
						<img src="<c:url value="${contextcdn}/resources/images/claim-process-icon.png"/>"
							style="float: left; margin-left: 5px; opacity: 0.5; margin-right: 20px;" alt="Claim Process">
						<h4 style="text-align: justify;">There are two scenarios in
							claim:</h4>
						<strong style="font-size: 20px; color: coral;">1. </strong> In
						case of death of policy holder the sum assured can be claimed by
						the nominee of the policy. <br> Process to be followed: <br>
						<strong>A. Report claim:</strong> Inform the death claim to the
						Insurance company where the policy holder bought the policy. <br>
						<strong>B. Documentation:</strong> <span class="circleNumber">1</span>
						Fill the claim form. <span class="circleNumber">2</span> Submit
						document like death certificate, attach policy bond &amp; nominee
						document eg. ID proof, address proof along with documents
						mentioned by Insurer. <br> <strong>C.</strong> Coordinate
						with Insurance Company. <br> <strong>D.</strong> Verify claim
						settlement details and get the clearance for settlement amount. <br>
						<br> <strong style="font-size: 20px; color: coral;">2.
						</strong> Second Scenario would be to claim the maturity amount when the
						time of policy get matured at the end of the policy term. <br>
						Follow Process: <br> Submit policy documents along with a
						revenue stamp and wait for sum assured to be credited. Some
						specific requirements from the insurer to be followed as well.

					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Redirect Disclaimer</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="font-size: 12px;">
        <p>Please note that you are now entering a website directly or indirectly maintained by a third party (the &ldquo;External
            Site&rdquo;) and that you do so at your own risk. Ntactus Financial services Pvt Ltd. (&ldquo;FreEMI.in&rdquo;)
            has no control over the External Site, any data or other content contained therein or any additional linked websites.
            The link to the External Site is provided for convenience purposes only.</p>
        <p>By clicking &ldquo;Get Quotes&rdquo; you acknowledge and agree that neither Ntactus Financial services Pvt Ltd. (&ldquo;FreEMI.in&rdquo;)
            nor third party provider is responsible, or accepts or assumes any responsibility or liability whatsoever for,
            the content, the data or the technical operation of the Linked Site. Further, by entering the External Site,
            you also acknowledge and agree that you completely and irrevocably waive any and all rights and claims against
            Ntactus Financial services Pvt Ltd. (&ldquo;FreEMI.in&rdquo;) and third party provider and further acknowledge
            and agree that in no event shall Ntactus Financial services Pvt Ltd. (&ldquo;FreEMI.in&rdquo;) or third party
            provider, its officers, employees, directors and agents be liable for any</p>
        <ol style="list-style-type: lower-roman;">
            <li>indirect, consequential, incidental, special, compensatory or punitive damages,&nbsp;</li>
            <li>damages for loss of income, loss of business profits, business interruption, loss of data or business information,
                loss of or damage to property,</li>
            <li>claims of third parties, or</li>
            <li>other pecuniary loss, arising out of or related to the Legal Notice, this disclaimer or the External Site.</li>
        </ol>
        <p>By entering the External Site, you further acknowledge and agree that the disclaimer of warranties and limitations
            of liability set out in this disclaimer shall apply regardless of the causes, circumstances or form of action
            giving rise to the loss, damage, claim or liability, even if such loss, damage, claim or liability is based upon
            breach of contract (including, without limitation, a claim of fundamental breach or breach of a fundamental term),
            tort (including, without limitation, negligence), strict liability or any other legal or equitable theory, and
            even if Ntactus Financial services Pvt Ltd. (&ldquo;FreEMI.in&rdquo;) and third party provider are advised of
            the possibility of the loss, damage, claim or liability.</p>
        <p>The waiver and release specifically includes, without limitation, any and all rights and claims pertaining to the
            processing of personal data, including but not limited to any rights under any applicable data protection statute(s).
            If in any jurisdiction, any part of this disclaimer is held to be unenforceable by a court of competent jurisdiction,
            such part of this disclaimer shall be restricted or eliminated to the minimum extent and the remaining disclaimer
            shall otherwise remain in full force and effect.</p>
        <p>Please note the information presented is deemed representative at the time of its original release. Changes in historical
            information may occur due to adjustments in accounting and reporting standards &amp; procedures.</p>
      </div>
    </div>
  </div>
</div>
	
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>