<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>FreEMI Dashboard</title>

<link
	href="<c:url value="${contextcdn}/resources/css/my-dashboard.component.css"/>"
	rel="stylesheet">

<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<script src="<c:url value="${contextPath}/resources/js/investment.js" />"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>


</head>

<body class="back_set">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container-fluid freemi_container" style="padding: 0px;">
		<div class="dashboard_header">
			<span>DASHBOARD</span>
		</div>

		<div class="home-view">
			<span>HOME VIEW</span>
		</div>

		<section class="top-section">
			<div class="row dashboard_content">
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style1">
						<div class="header header-back1">
							<h5>FreEMI Credit</h5>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-6">Loan Status</div>
								<div class="col-6">Active</div>
							</div>
							<div class="row">
								<div class="col-6">Total loan(s)</div>
								<div class="col-6">2</div>
							</div>
						</div>
						<div class="footer_link">
							<span>VIEW DETAILS</span>
						</div>
					</div>

				</div>
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style2">
						<div class="header header-back2">
							<h5>Registry</h5>
						</div>
						<div class="box-body">
							<!-- <div class="row">
						<div class="col-6">Registry</div>
						<div class="col-6">Active</div>
					</div> -->
							<div class="row">
								<div class="col-6">
									<h6>TOTAL ASSETS</h6>
								</div>
								<div class="col-6">
									<h4>
										<i class="fas fa-rupee-sign"> </i>
										<fmt:formatNumber value="${totalasset }" type="number" />
									</h4>
								</div>
							</div>
						</div>
						<div class="footer_link">
							<span>VIEW DETAILS</span>
						</div>
					</div>

				</div>
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style3">
						<div class="header header-back3">
							<h5>F-Secure</h5>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-6">Life Secured</div>
								<div class="col-6">No</div>
							</div>
							<div class="row">
								<div class="col-6">Plans Activated</div>
								<div class="col-6">0</div>
							</div>
						</div>
						<div class="footer_link">
							<span>VIEW DETAILS</span>

						</div>
					</div>

				</div>
			</div>

		</section>

		<section class="desctiopn-section">
			<div class="row" style="margin: auto;">
				<div class="col-md-12 col-lg-12">


					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active" id="home-tab"
							data-toggle="tab" href="#freemi" role="tab"
							aria-controls="freemi" aria-selected="true">FreEMI</a></li>
						<li class="nav-item"><a class="nav-link" id="profile-tab"
							data-toggle="tab" href="#fsecure" role="tab"
							aria-controls="fsecure" aria-selected="false">F-Secure</a></li>
						<li class="nav-item"><a class="nav-link" id="contact-tab"
							data-toggle="tab" href="#registry" role="tab"
							aria-controls="registry" aria-selected="false">Registry</a></li>
					</ul>

					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active custom-tab" id="freemi"
							role="tabpanel" aria-labelledby="home-tab">

							<div class="fremi-outer-shell">
								<div class="freemi-profiles">
									<i class="fas fa-list" style="color: aliceblue;"></i>
								</div>

								<div class="freemi-outer">
									<div style="overflow-x: auto;">
										<table class="table table-sm table-bordered">
											<caption>FreEMI Purchse History</caption>
											<thead class="freemi-records">
												<tr>
													<th scope="col">Loan Category</th>
													<th scope="col">Loan Type</th>
													<th scope="col">Loan Amount</th>
													<th scope="col">Loan Tenure</th>
													<th scope="col">Monthly EMI</th>
													<th scope="col">Premium Amount</th>
												</tr>
											</thead>
											<tbody>
												<!-- <tr *ngFor="let data of freemiData">
            <td>{{data.instype}}</td>
            <td>{{data.insname}}</td>
            <td>{{data.nominee}}</td>
            <td>{{data.term}}</td>
            <td>{{data.payingterm}}</td>
            <td>&#8377; {{data.amount | number: '.2'}}</td>
            <td>{{data.statement }}</td>
          </tr> -->
											</tbody>

										</table>
									</div>
								</div>

							</div>

						</div>
						<div class="tab-pane fade custom-tab" id="fsecure" role="tabpanel"
							aria-labelledby="profile-tab">

							<div class="fremi-outer-shell">
								<div class="fsecure-profiles">
									<i class="fas fa-list" style="color: aliceblue;"></i>
								</div>

								<div class="fsecure-outer">
									<div style="overflow-x: auto;">
										<table class="table table-sm table-bordered">
											<caption>FSecure Purchse History</caption>
											<thead class="fsecure-records">
												<tr>
													<th scope="col">Insrunce Type</th>
													<th scope="col">Insurer Name</th>
													<th scope="col">Nominee Name</th>
													<th scope="col">Term</th>
													<th scope="col">Premium Paying Term</th>
													<th scope="col">Premium Amount</th>
													<th scope="col">Premium Statement</th>
												</tr>
											</thead>
											<tbody>
												<!-- <tr *ngFor="let data of fsecureData">
          <td>{{data.instype}}</td>
          <td>{{data.insname}}</td>
          <td>{{data.nominee}}</td>
          <td>{{data.term}}</td>
          <td>{{data.payingterm}}</td>
          <td>&#8377; {{data.amount | number: '.2'}}</td>
          <td>{{data.statement }}</td>
        </tr> -->
											</tbody>

										</table>
									</div>
								</div>


							</div>

						</div>
						<div class="tab-pane fade custom-tab" id="registry"
							role="tabpanel" aria-labelledby="contact-tab">

							<div class="fremi-outer-shell">
								<div class="registry-profiles">
									<i class="fas fa-list" style="color: aliceblue;"></i>
								</div>

								<div class="registry-outer">
									<c:choose>
										<c:when test="${ORDERHISTORY == 'SUCCESS' }">
											<table class="table table-sm table-bordered registry-table">
												<caption>Registry Purchase History</caption>
												<thead class="registry-records">
													<tr>
														<th scope="col">PORTFOLIO</th>
														<th scope="col">INVEST TYPE</th>
														<th scope="col">SCHEME NAME</th>
														<th scope="col">NAV DATE</th>
														<th scope="col">NAV VALUE (Rs.)</th>
														<th scope="col">INVESTMENT (Rs.)</th>
														<th scope="col">ACTION</th>
														<!-- <th scope="col">XIRR</th>
													<th scope="col">Accumulated Amount</th>
													<th scope="col">Topup SIP</th>
													<th scope="col">Action</th> -->
													</tr>
												</thead>
												<tbody>

													<c:forEach var="listVar" items="${mforderhistory}">
														<tr>
															<td>${listVar.portfoilio }</td>
															<td>${listVar.investType }</td>
															<td>${listVar.schemeName }</td>
															<td></td>
															<td></td>
															<td style="font-weight: 600;"><fmt:formatNumber value="${listVar.schemeInvestment }" type="number" maxFractionDigits = "3" /></td>
															<td style="text-align: center;">
																<div class="btn-group">
																	<button type="button"
																		class="btn btn-secondary dropdown-toggle btn-sm"
																		data-toggle="dropdown" aria-haspopup="true"
																		aria-expanded="false" style="font-size: 11px;">ACTION</button>
																	<div class="dropdown-menu dropdown-menu-right">
																		<button class="dropdown-item" type="button" style="font-size: 12px;color: #238019;font-weight: 600;" onclick="AdditionalPurchase('${listVar.portfoilio}','${listVar.schemeCode }','${listVar.investType }')">Invest More <i class="fas fa-arrow-left"></i></button>
																		
																		<c:if test="${listVar.schemeInvestment > 0 }">
																		<button class="dropdown-item" type="button" style="font-size: 12px;color: #da2323;font-weight: 600;" onclick="MFRedeem('${listVar.portfoilio}','${listVar.schemeCode }','${listVar.investType }')">Redeem <i class="fas fa-arrow-right"></i>
																			</button>
																		</c:if>
																	</div>
																</div>

															</td>
														</tr>
													</c:forEach>

												</tbody>

											</table>
										</c:when>
										<c:when test="${ORDERHISTORY == 'ERROR' }">
											<span>Failed to fetch your data. Please try after
												sometime</span>
										</c:when>
										<c:when test="${ORDERHISTORY == 'EMPTY' }">
											<span>You have not yet purchased any Registry.</span>
										</c:when>
									</c:choose>
									<div style="overflow-x: auto;"></div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</section>

	</div>



</body>
</html>