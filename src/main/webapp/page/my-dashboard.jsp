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
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextcdn}/resources/css/my-dashboard.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<script
	src="<c:url value="${contextPath}/resources/js/investment.js" />"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script> -->

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
					<div class="box-dashboard box-style1 animated fadeIn">
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
					<div class="box-dashboard box-style2 animated fadeIn" id="dashbox2">
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
									<h5>
										<i class="fas fa-rupee-sign"> </i>
										<fmt:formatNumber value="${totalasset }" type="number" />
									</h5>
								</div>
							</div>
						</div>
						<div class="footer_link">
							<span>VIEW DETAILS</span>
						</div>
					</div>

				</div>
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style3 animated fadeIn">
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


		<section class="profile-status-section">
			<div class="row" style="margin: auto;">
				<div class="col-md-4 col-lg-4">
					<div class="profile-status-left">
						<h5 style="background: #dbdee6; padding: 3px;">Profile Status</h5>
						<c:choose>
							<c:when test="${PROFILE_STATUS == 'NOT_FOUND' }">
								<h4 style="font-family: none; color: #e6643c;">Register for investment</h4>
								<div class="progress" style="margin-bottom: 20px;">
									<div class="progress-bar progress-bar-striped bg-danger"
										role="progressbar" style="width: 10%" aria-valuenow="10"
										aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<a href="/products/mutual-funds/register">
									<button class="btn btn-sm btn-secondary">Get Registered</button>
								</a>
							</c:when>

							<c:when test="${PROFILE_STATUS == 'Y' }">
								<h4 style="color: #408ad8;font-family: serif;">Investment profile ready.</h4>
								<div class="progress" style="font-size: 10px;margin-bottom: 20px;">
									<div class="progress-bar bg-success" role="progressbar"
										style="width: 100%" aria-valuenow="100" aria-valuemin="0"
										aria-valuemax="100">Registration Complete</div>
									
								</div>
								<div style="text-align: center;">
								<!-- <p>Mode of Holding: </p> -->
								
								<a href="/products/mutual-funds/pending-payments" style="margin-bottom: 20px;">
									<button class="btn btn-sm btn-info" style="font-size: 12px;">Complete Pending payments</button>
								</a>
								</div>
							</c:when>
					
							<c:when test="${PROFILE_STATUS == 'N' }">
								<h4 style="color: #2558a7; font-family: serif;">Upload your
									AOF Form</h4>
								<div class="progress" style="font-size: 10px;">
									<div class="progress-bar bg-success" role="progressbar"
										style="width: 50%" aria-valuenow="50" aria-valuemin="0"
										aria-valuemax="100">Registered</div>
									<div class="progress-bar bg-secondary" role="progressbar"
										style="width: 50%" aria-valuenow="50" aria-valuemin="0"
										aria-valuemax="100">Complete your profile</div>
								</div>
								<p>You need to sign and upload your investment form before
									you can start investing.</p>
								<button class="btn btn-sm btn-secondary">Upload AOF</button>
							</c:when>

							<c:when test="${PROFILE_STATUS == 'E' }">
								<p>Failed to fetch your profile status!</p>
							</c:when>

						</c:choose>
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
													<th scope="col">Insurance Type</th>
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
															<td style="font-weight: 600;"><fmt:formatNumber
																	value="${listVar.schemeInvestment }" type="number"
																	maxFractionDigits="3" /></td>
															<td style="text-align: center;">
																<div class="btn-group">
																	<button type="button"
																		class="btn btn-secondary dropdown-toggle btn-sm"
																		data-toggle="dropdown" aria-haspopup="true"
																		aria-expanded="false" style="font-size: 11px;">ACTION</button>
																	<div class="dropdown-menu dropdown-menu-right">
																		<button class="dropdown-item" type="button"
																			style="font-size: 12px; color: #238019; font-weight: 600;"
																			onclick="AdditionalPurchase('${listVar.portfoilio}','${listVar.schemeCode }','${listVar.investType }')">
																			Invest More <i class="fas fa-arrow-left"></i>
																		</button>

																		<c:if test="${listVar.schemeInvestment > 0 }">
																			<button class="dropdown-item" type="button"
																				style="font-size: 12px; color: #da2323; font-weight: 600;"
																				onclick="MFRedeem('${listVar.portfoilio}','${listVar.schemeCode }','${listVar.investType }')">
																				Redeem <i class="fas fa-arrow-right"></i>
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