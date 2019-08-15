<!DOCTYPE html>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tax Calculator</title>
<link
	href="<c:url value="${contextcdn}/resources/css/registry.tax-calculator.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<script
	src="<c:url value="${contextcdn}/resources/js/taxCalculator.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>

<body class="back_set"
	>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
		<div style="margin-bottom: 60px;">
			<div class="page_topic">
				<h2>
					<span style="color: #fdff3f;">Tax Saving </span> Registry
				</h2>
				<!-- <h4>
            <span style="color: #fdff3f;">Tax Saving </span> plans
        </h4> -->
			</div>
			<div>
				<p>
					Planing for Tax Savings, is always last minute task. It's really a
					stress full and boring. Lets make it a fun while planing a " <strong>Tax
						Savings Registry</strong>". No need to consult with a tax expert or tax
					consultant while creating a "Tax Savings Registry" it's fully
					loaded with inbuilt intelligence to calculate tax on income and
					actually how much you need to invest in which tax saving product as
					well. Sound interesting ???
				</p>
			</div>

			<div class="row" style="margin: 0px auto;">
				<div class="col-md-8" style="background: rgb(18, 158, 171);">
					<form:form commandName="taxCalculatorForm">
						<div class="row" style="margin-top: 10px;">
							<div class="col-md-6 col-lg-6">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<label class="input-group-text" for="earnerTypeid">
											<i class="fa fa-users" aria-hidden="true"></i>
										</label>
									</div>
									<form:select class="custom-select" id="earnerTypeid" path="earnerType" onchange="calculateTax();">
										<%-- <form:options items="${taxpayerType}" /> --%>
										<form:option value="MF">Male/Female (Below 60 years)</form:option>
										<form:option value="SC">Senior Citizen (60 - 80 years)</form:option>
										<form:option value="SSC">Super Senior Citizen (Above 80 years)</form:option>
									</form:select>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1"> <i
											class="fas fa-rupee-sign" aria-hidden="true"></i>
										</span>
									</div>
									<form:input type="text" class="form-control"
										aria-label="Income" aria-describedby="basic-addon1"
										pattern="[0-9]*" maxlength="7"
										placeholder="Gross Taxable Income" path="annualIncome" id="annualIncomeid"
										onkeyup="calculateTax()" />
								</div>
							</div>
						</div>


						<div class="section_header" style="text-align: center;">
							<h5 class="heading1">Tax benefit u/s 80c</h5>
							<span class="sub_heading">Diversify investment to reduce
								taxable income by <span class="amount"> &#8377;1,50,000</span>
							</span>
						</div>


						<div style="padding: 5px 5px; border: 1px groove yellow;">
							<div class="row">
								<div class="col-md-6 col-lg-6">
									<div class="row">
										<div class="col-md-5 col-lg-5 label_box">
											<label class="label_style">ELSS (3yrs lock-in)</label>
										</div>
										<div class="col-md-7 col-lg-7">
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon1"> <i
														class="fas fa-rupee-sign" aria-hidden="true"></i>
													</span>
												</div>
												<form:input type="text" class="form-control input_custom"
													aria-label="elss" aria-describedby="basic-addon1"
													pattern="[0-9]*" maxlength="6" placeholder="0" path="elss" id="elssid"
													onkeyup="calculateTax()" />
											</div>
											<!-- <div>{{spy.className}}</div> -->
										</div>
									</div>

									<div class="row">
										<div class="col-md-5 col-lg-5 label_box">
											<label class="label_style">PPF (15yrs lock-in)</label>
										</div>
										<div class="col-md-7 col-lg-7">
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon1"> <i
														class="fas fa-rupee-sign" aria-hidden="true"></i>
													</span>
												</div>
												<form:input type="text" class="form-control input_custom"
													aria-label="Username" aria-describedby="basic-addon1"
													pattern="[0-9]*" maxlength="6" placeholder="0" path="ppf" id="ppfid"
													onkeyup="calculateTax()" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-5 col-lg-5 label_box">
											<label class="label_style">NSC (5yrs lock-in)</label>
										</div>
										<div class="col-md-7 col-lg-7">
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon1"> <i
														class="fas fa-rupee-sign" aria-hidden="true"></i>
													</span>
												</div>
												<form:input type="text" class="form-control input_custom"
													aria-label="Username" aria-describedby="basic-addon1"
													pattern="[0-9]*" maxlength="6" placeholder="0" path="nsc" id="nscid"
													onkeyup="calculateTax()" />
											</div>
										</div>
									</div>
								</div>

								<!-- 80C Payments section decuctions -->
								<div class="col-md-6 col-lg-6">

									<div class="row">
										<div class="col-md-5 col-lg-5 label_box">
											<label class="label_style">FD (5yrs lock-in)</label>
											<!-- <button  ngbPopover="Invest for 5 years to avail the benefit" popoverTitle="">
                                        <i class="fa fa-info-circle" aria-hidden="true"></i>
                                    </button > -->
										</div>
										<div class="col-md-7 col-lg-7">
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon1"> <i
														class="fas fa-rupee-sign" aria-hidden="true"></i>
													</span>
												</div>
												<form:input type="text" class="form-control input_custom"
													aria-label="Username" aria-describedby="basic-addon1"
													pattern="[0-9]*" maxlength="6" placeholder="0"
													path="fixedDeposit" id="fixedDepositid" onkeyup="calculateTax()" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-5 col-lg-5 label_box">
											<label class="label_style">ULIP (5yrs lock-in)</label>
										</div>
										<div class="col-md-7 col-lg-7">
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon1"> <i
														class="fas fa-rupee-sign" aria-hidden="true"></i>
													</span>
												</div>
												<input type="text" class="form-control input_custom"
													aria-label="Username" aria-describedby="basic-addon1"
													pattern="[0-9]*" maxlength="6" placeholder="0" path="ulip" id="ulipid"
													onkeyup="calculateTax()" />
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-5 col-lg-5 label_box">
											<label class="label_style">LIC (Policy term 5-30 yrs)</label>
										</div>
										<div class="col-md-7 col-lg-7">
											<div class="input-group mb-3">
												<div class="input-group-prepend">
													<span class="input-group-text" id="basic-addon1"> <i
														class="fas fa-rupee-sign" aria-hidden="true"></i>
													</span>
												</div>
												<form:input type="text" class="form-control input_custom"
													aria-label="Username" aria-describedby="basic-addon1"
													pattern="[0-9]*" maxlength="6" placeholder="0" id="lifeInsuranceid"
													path="lifeInsurance" onkeyup="calculateTax()" />
											</div>
										</div>
									</div>

								</div>

							</div>
							<div class="row">
								<div class="col-md-12 col-lg-12"
									style="text-align: center; color: white;">
									<h6>Total Investment: &#8377; <span id="total80c"></span></h6>
									<div style="height: 20px;">
										<h6 id="excessinvestment" style="color: #ff0303b8;"></h6>
									</div>
								</div>
							</div>
						</div>



						<div class="row" style="margin-bottom: 15px;">
							<div class="col-md-6 col-lg-6">
								<div class="section_header">
									<h5 class="heading1">Tax benefit u/s 80D</h5>
									<span class="sub_heading">Additional tax benefit upto <span
										class="amount"> &#8377;60,000</span>
									</span>
								</div>
								<div class="row">
									<div class="col-md-5 col-lg-5 label_box">
										<label class="label_style">Health Insurance</label>
									</div>
									<div class="col-md-7 col-lg-7">
										<div class="input-group mb-3">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon1"> <i
													class="fas fa-rupee-sign" aria-hidden="true"></i>
												</span>
											</div>
											<form:input type="text" class="form-control input_custom"
												aria-label="Username" aria-describedby="basic-addon1"
												pattern="[0-9]*" maxlength="6" placeholder="0"
												path="healthInsurance" id="healthInsuranceid" onkeyup="calculateTax()" />
										</div>
									</div>
								</div>

							</div>

							<div class="col-md-6 col-lg-6">
								<div class="section_header">
									<h5 class="heading1">Tax benefit u/s 80CCD (1B)</h5>
									<span class="sub_heading">Additional tax benefit upto <span
										class="amount"> &#8377;50,000</span>
									</span>
								</div>

								<div class="row">
									<div class="col-md-5 col-lg-5 label_box">
										<label class="label_style">NPS (Till retirement)</label>
									</div>
									<div class="col-md-7 col-lg-7">
										<div class="input-group mb-3">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon1"> <i
													class="fas fa-rupee-sign" aria-hidden="true"></i>
												</span>
											</div>
											<form:input type="text" class="form-control input_custom"
												aria-label="Username" aria-describedby="basic-addon1"
												pattern="[0-9]*" maxlength="6" placeholder="0" path="nps" id="npsid"
												onkeyup="calculateTax()" />
										</div>
									</div>
								</div>
							</div>
						</div>

					</form:form>
				</div>

				<!-- Right section  -->
				<div class="col-md-4 col-lg-4"
					style="background: #6ddc9e; padding-bottom: 15px;">
					<div
						style="text-align: center; margin: 10px auto; font-family: serif;">
						<h3>Tax break-up</h3>
					</div>
					<div>
						<table style="width: 100%">
							<tr>
								<td>Taxable Income</td>
								<td id="taxableIncome">&#8377; 0</td>
							</tr>
							<tr>
								<td>Calculated Tax</td>
								<td id="tax">&#8377; 0</td>
							</tr>
							<tr>
								<td>Tax Relief u/s 87A</td>
								<td id="rebate87a">&#8377; 0</td>
							</tr>
							<tr>
								<td>Cess</td>
								<td id="cess">&#8377; 0</td>
							</tr>
							<tr>
								<td>Surcharge</td>
								<td id="surcharge">&#8377; 0</td>
							</tr>
							<tr>
								<th>Total tax payable</th>
								<th id="totalTax">&#8377; 0</th>
							</tr>
						</table>
					</div>

					<div style="height: 80px;">
						<div id="taxdiff" style="text-align: center;">
							<div>
								<b>You can bring down tax to</b>
							</div>
							<div>
								<span class="full_inv" id="totalReducedTax">&#8377;
									0</span>
							</div>
						</div>
					</div>
					<div class="invest">
						<!-- <div class="invest_box">
                        <span>Invest</span>
                    </div> -->
						<button class="btn btn-sm btn-primary btn_invest">Click
							to Save TAX</button>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>