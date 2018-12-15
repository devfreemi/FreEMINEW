<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="keywords" content="Credit Line, Instant Credit,Personal Loan,Home Loan, Business Loan, Instant Credit, Credit Crad, No Cost EMI" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="title" content="FreEMI Credit" />
<meta name="description" content="" />
<meta name="robots" content="index,follow" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Freemi Product</title>
<link href="<c:url value="${contextPath}/resources/css/freeEmi.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextPath}/resources/js/freemi.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>

<body class="back_set"
	
	onload="formOnLoad();">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
		<div class="topic_spacing">
			<div style="padding-left: 2px;">
				<img
					src="<c:url value="${contextPath}/resources/images/freemi_icon1.png"/>"
					class="img-fluid" style="width: 50px;">
			</div>
			<div style="margin-left: 15px;">
				<span class="heading_name">FreEMI</span>
				<h5 style="color: rgb(253, 253, 253);">A lending like never
					before..</h5>
			</div>
		</div>

		<div class="row details_style">
			<div class="col-md-4 col-lg-4">
				<div class="topic_option">
					<h2>Fixed Credit</h2>
				</div>
				<p class="contect_style1">
					A solution based credit facility where the customer can have
					availed to meet deferent daily lifestyle expenses, like rent
					deposit, relocation cost, etc. Credit facility starts from <i
						class="fas fa-rupee-sign"></i>10,000 to <i
						class="fas fa-rupee-sign"></i>96,000, borrowers have the
					flexibility of re-paying within specific time period opted during
					creation of Fixed Credit account in FreEMI.in
				</p>
			</div>
			<div class="col-md-4 col-lg-4">
				<div class="topic_option">
					<h2>Flexible Credit</h2>
				</div>
				<p class="contect_style1">
					A kind of cash credit which offered by FreEMI limited to your very
					crucial requirement. These are cash credit facilities can be
					availed on amount starting from <i class="fas fa-rupee-sign"></i>10,000
					to <i class="fas fa-rupee-sign"></i>96,000 We offer flexible
					payment periods that varied from a few months up to a year, even
					you can pay in weeks.
				</p>
			</div>
			<div class="col-md-4 col-lg-4" style="margin-left: -1px;">
				<div class="topic_option">
					<h2>No Cost EMI</h2>
				</div>
				<p class="contect_style1">
					With 'No Cost EMI' we enabling these credit facilities to the
					people who really need it. We are ready to hear from these people
					and serve with logical inputs. Credit facilities start from <i
						class="fas fa-rupee-sign"></i>10,000 to <i
						class="fas fa-rupee-sign"></i>36,000, repay in months by linking
					bank accounts with us generally we chose the best possible time by
					checking bank account ledger balance using our algorithm and debit
					the amount.
				</p>
			</div>
		</div>

		<div style="text-align: center;">
			<a href="${contextPath}/register" style="text-decoration: none"><img
				src="<c:url value="${contextPath}/resources/images/signup.png"/>"
				class="img-fluid" style="height: 60px;"></a>
		</div>

		<div class="row form_box">
			<div class="col-md-5 col-lg-5 form_box_left">
				<div class="contents">
					<h2 id="desc"></h2>

				</div>

				<div class="background">
					<img id="change_img"
						src="<c:url value="${contextPath}/resources/images/loan1.png"/>"
						class="img-fluid">
				</div>
			</div>
			<div class="col-md-7 col-lg-7 form_box_right" style="margin: -1px;">


				<form:form method="POST" action="${pageContext.request.contextPath}/freemi.do"
					commandName="freemiLoanForm">
					<div id="activeForm1">
						<div class="btn-group-horizontal btn_group_style">
							<div>
								<span class="product_heading">Choose your Product</span>
							</div>
							<button type="button"
								class="btn btn-secondary btn-sm loan_button"
								onclick="fixedCredit()">Fixed Credit</button>
							<button type="button"
								class="btn btn-secondary btn-sm loan_button"
								onclick="flexibleCredit()">Flexible Credit</button>
							<button type="button" class="btn btn-primary btn-sm loan_button"
								onclick="noCostEmi()">No cost EMI</button>
						</div>
						
						<%-- <div class="form-group" style="margin-top: 20px;">
							<label class="center-block"> <b>Loan category: </b>
							</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<label class="input-group-text" for="inputGroupSelect01">
										<i class="fa fa-puzzle-piece"></i>
									</label>
								</div>

								<form:select class="custom-select" id="inputGroupSelect01"
									path="loan_category">
									<!-- <option *ngFor="let options of loanTypes" [value]="options">{{options}}</option> -->
									<form:options items="${freemiLoanCategory}" />
								</form:select>
							</div>
						</div> --%>

						<div class="form-group" style="margin-top: 20px;">
							<label class="center-block"> <b>Credit Type: <c:out value="${loan_category }"></c:out> </b>
							</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<label class="input-group-text" for="inputGroupSelect01">
										<i class="fa fa-puzzle-piece"></i>
									</label>
								</div>

								<form:select class="custom-select" id="inputGroupSelect01"
									path="loanType">
									<!-- <option *ngFor="let options of loanTypes" [value]="options">{{options}}</option> -->
									
									<form:options items="${creditType}" />
								</form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="center-block"> <b>Credit Amount: ( Upto
									&#8377; <span id="allowedloan" style="color: crimson;">
										</span> )
							</b> <span id="amount_error" class="amount_error"></span>
							</label>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1"> <i
										class="fas fa-rupee-sign"></i>
									</span>
								</div>
								<form:input id="loanbox" type="text"
									class="form-control input-sm" onkeyup="loanamountcheck();"
									aria-label="Username" aria-describedby="basic-addon1"
									path="loanAmount" pattern="[0-9]*" maxlength="5" />

							</div>
						</div>
						<div class="form-group">
							<label class="center-block"> <b>Tenure (in months) </b>
							</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<label class="input-group-text" for="inputGroupSelect01">
										<i class="fas fa-calendar-alt"></i>
									</label>
								</div>
								<form:select class="custom-select" id="inputGroupSelect01"
									path="loanTenure">
									<form:options items="${loanTenureList}" />
								</form:select>
							</div>
						</div>
						<div class="form-group" style="margin: auto; text-align: center;">
							<button id="proceed" class="button2" type="button"
								style="vertical-align: middle" onclick="changeForm1to2();">
								<span>Proceed</span>
							</button>
							
						</div>
					</div>



					<div id="activeForm2">
						<%-- <form:form action="POST" method="/freemi/freemi.do" commandName="freemiLoanForm"> --%>
						<div>
							<span class="form_header">Fill in these below details to
								evaluate your estimate: </span>
						</div>
						<div class="form-group">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1"> <i
										class="fa fa-globe"></i>
									</span>
								</div>
								<form:select class="custom-select" id="inputGroupSelect01"
									path="city">
									<option value="" selected="selected">Select your city</option>
									<form:options items="${cityList}" />
								</form:select>
							</div>
						</div>

						<div class="form-group">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1"> <i
										class="fas fa-rupee-sign"></i>
									</span>
								</div>
								<form:input type="text" class="form-control input-sm"
									aria-label="Username" aria-describedby="basic-addon1"
									path="netIncome" pattern="[0-9]*" maxlength="8"
									placeholder="Monthly Net Income" />
							</div>
						</div>

						<div class="form-group">
							<div class="row" style="line-height: 1em;">
								<div class="col-md-2 col-lg-2">
									<span> <strong>Employment tenure</strong>
									</span>
								</div>

								<div class="col-md-2 col-lg-2">
									<form:radiobutton value="1" path="employement" />
									1 Year
								</div>
								<div class="col-sm-4 col-md-4 col-lg-4"
									style="margin-left: -1px;">
									<form:radiobutton value="2" path="employement" />
									Less than 2 year
								</div>
								<div class="col-sm-4 col-md-4 col-lg-4">
									<form:radiobutton value="5" path="employement" />
									Less than 5 year
								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1"> <i
												class="fas fa-rupee-sign"></i>
											</span>
										</div>
										<form:input type="text" class="form-control input-sm"
											aria-label="Username" aria-describedby="basic-addon1"
											path="emiOutflow" pattern="[0-9]*" maxlength="8"
											placeholder="Existing EMI amount" />
									</div>
								</div>
							</div>
							<div class="col-md-6 col-lg-6" style="margin-left: -1px;">
								<div class="form-group">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1"> <i
												class="fas fa-rupee-sign"></i>
											</span>
										</div>
										<form:input type="text" class="form-control input-sm"
											aria-label="Username" aria-describedby="basic-addon1"
											path="rentOutflow" pattern="[0-9]*" maxlength="8"
											placeholder="Monthly Gross Expense" />
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1"> <i
										class="fa fa-at"></i>
									</span>
								</div>
								<form:input type="text" class="form-control input-sm"
									aria-label="Email ID" aria-describedby="basic-addon1"
									path="emailId" maxlength="128" placeholder="Your email-id" />
							</div>
						</div>
						<div class="form-group">
							<span style="line-height: 1em; font-size: 12px;"> <form:checkbox
									path="citizenship" value="1" /> Allow FreEMI to send email
								communications &amp; make calls.
							</span>
						</div>

						<div class="form-group" style=" text-align: center;">
							<button class="button1" type="button"
								style="vertical-align: middle" onclick="changeForm2to1()">
								<span>Go back </span>
							</button>

							<button class="button2" type="submit"
								style="vertical-align: middle" onclick="freemiCalculate()">
								<span>Submit Request </span>
							</button>
						</div>
					</div>
				</form:form>

			</div>

			<%-- </form:form> --%>

		</div>


		<div class="row">
			<div class="col-sm-4 col-md-4 col-lg-4" style="margin: -1px;">
				<div class="topic_option">
					<h3>Choice</h3>
				</div>
				<div>
					<p class="contect_style1">
						It's your right to choose a credit with affordability, it should
						not be, <strong>Credit by Chance”</strong>. FreEMI is a product
						which deliver the suitability which fits into your requirement.
					</p>
				</div>

			</div>
			<div class="col-sm-4 col-md-4 col-lg-4">
				<div class="topic_option">
					<h3>Reaching Out</h3>
				</div>
				<div>
					<p class="contect_style1">
						Our cutting edge technology makes possibilities into reality. Time
						has come to say <strong>"NO"</strong> to unsafe Funds, when FreEMI
						is available easily.
					</p>
				</div>
			</div>
			<div class="col-sm-4 col-md-4 col-lg-4">
				<div class="topic_option">
					<h3>Credibility</h3>
				</div>
				<div>
					<p class="contect_style1">
						Are you seeking creditworthiness? 1/5 <sup>th</sup> of India has a
						credit score below 750 as you are and we understand you better....
						Lets start with FreEMI: "Believe it Or Not" an interest free
						credit can be available in FreEMI.
					</p>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>

