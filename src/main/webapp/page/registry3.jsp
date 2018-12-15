<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="keywords"
	content="Expenses management, Best Mutual Fund,Equity Mutual Fund, debt mutual Fund, Balanlce fund, Small cap Fund,Mid Cap fund,Online SIP, Best SIP Online, Compare mutual Fund,SIP Calculator,Return Calculator, ICICI Prdential balance advantage fund, ICICI Pru value Discovery Fund,ICICI liquied Fund, HDFC prudence Fund, HDFC Equity Fund, Axis Long Term Equity Fund, Buy Mutual Fund" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description" content="Wish based nutual funds." />
<meta name="robots" content="follow,index" />

<title>Registry mutual fund</title>

<link
	href="<c:url value="${contextcdn}/resources/css/registry.general.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>"
	type="text/css" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>" rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="freemi_container">
		<div class="section1">
			<div class="container">

				<div class="row page_heading">
					<div class="registry_logo">
						<img
							src="<c:url value="${contextcdn}/resources/images/registry.png"/>" alt="Registry"
							class="img-fluid" style="width: 90px;">
					</div>
					<div class="registry_title">
						<h1>Registry</h1>
						<h5>A Product of Mutual Funds</h5>
					</div>
				</div>


				<div class="row registry_category">
					<div class="col-12 head_1">
						<span style="font-family: serif; font-size: 25px;">Turn
							your good expenses into investment now</span>
					</div>
					<div class="col-md-4 col-lg-4 margin_type"
						style="margin-right: -1px;">
						<div class="group_border">
							<div class="topic_color">
								<div class="row">
									<div class="col-3">
										<img src="<c:url value="${contextcdn }/resources/images/registry_r.png"/>" alt="retirement"
											class="img_fluid custom_img_fluid">
									</div>
									<div class="col-7" style="margin-left: -10px;">
										<h3>Retirement</h3>
									</div>
								</div>

							</div>
							<div
								style="padding: 10px 2px 10px 5px; max-height: 300px; overflow-y: auto; overflow-x: hidden;">

								<div class="row" style="text-align: right; margin-bottom: 20px;">

									<div class="col-8" style="text-align: left; padding-left: 3em;">
										<span><strong>Scheme Name</strong></span>
									</div>
									<div class="col-4" style="padding-left: 1px;">
										<span><strong>1yr Return</strong></span>
									</div>
								</div>
								<c:forEach var="product" items="${productlist}">
									<div class="row">
										<div class="col-1 radio_style">
											<input type="radio" name="scheme"
												value="${product.productScId}">
										</div>
										<div class="col-9" style="margin-left: -1px;">
											<strong><a href="#" data-toggle="modal"
												data-target="#${product.fundName}">${product.fundName}</a></strong>
										</div>
										<div class="col-2"
											style="margin-left: -10px; padding-left: 1px;">
											<fmt:formatNumber value="${product.PReturn_1Y * 100}"
												pattern="0.00" />
										</div>
									</div>

									<!--  Modal Code -->
									<div class="modal fade" id="${product.fundName}" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Scheme
														Details</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Name</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundName}</label>
														</div>
													</div>
													
													<div class="row">
														<div class="col-4">
															<label class="label_style">Scheme code</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.schemeCode}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Type</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundType}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV Date</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav_Date}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Risk</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.riskometer_value}</label>
														</div>
													</div>

												</div>
												<div class="modal-footer"></div>
											</div>
										</div>
									</div>

									<!--  Modal Code -->
								</c:forEach>

								<div class="row" style="margin-top: 15px;">
									<div class="col-6" style="margin: auto;">
										<a href="" id="birthdayhrf"
											style="text-decoration: none;">
											<button type="button"
												class="btn btn-outline-info btn-sm btn-block">
												Create Registry</button>
										</a>
									</div>
								</div>

							</div>
						</div>

					</div>

					<div class="col-md-4 col-lg-4 margin_type">
						<div class="group_border">
							<div class="topic_color">
								<div class="row">
									<div class="col-3">
										<img src="<c:url value="${contextcdn}/resources/images/registry_an.png"/>" alt="anniversary"
											class="img_fluid custom_img_fluid">
									</div>
									<div class="col-7" style="margin-left: -10px;">
										<h3>Anniversary</h3>
									</div>
								</div>

							</div>
							<div
								style="padding: 10px 2px 10px 5px; max-height: 300px; overflow-y: auto; overflow-x: hidden;">

								<div class="row" style="text-align: right; margin-bottom: 20px;">

									<div class="col-8" style="text-align: left; padding-left: 3em;">
										<span><strong>Scheme Name</strong></span>
									</div>
									<div class="col-4" style="padding-left: 1px;">
										<span><strong>1yr Return</strong></span>
									</div>
								</div>
								<c:forEach var="product" items="${annivesarylist}">
									<div class="row">
										<div class="col-1 radio_style">
											<input type="radio" name="scheme"
												value="${product.productScId}">
										</div>
										<div class="col-9" style="margin-left: -1px;">
											<strong><a href="#" data-toggle="modal"
												data-target="#${product.fundName}">${product.fundName}</a></strong>
										</div>
										<div class="col-2"
											style="margin-left: -10px; padding-left: 1px;">
											<fmt:formatNumber value="${product.PReturn_1Y * 100}"
												pattern="0.00" />
										</div>
									</div>

									<!--  Modal Code -->
									<div class="modal fade" id="${product.fundName}" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Scheme
														Details</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Name</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundName}</label>
														</div>
													</div>
													
													<div class="row">
														<div class="col-4">
															<label class="label_style">Scheme code</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.schemeCode}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Type</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundType}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV Date</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav_Date}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Risk</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.riskometer_value}</label>
														</div>
													</div>

												</div>
												<div class="modal-footer"></div>
											</div>
										</div>
									</div>

									<!--  Modal Code -->
								</c:forEach>

								<div class="row" style="margin-top: 15px;">
									<div class="col-6" style="margin: auto;">
										<a href="" style="text-decoration: none;" id="annivhrf"
											>
											<button type="button"
												class="btn btn-outline-info btn-sm btn-block">
												Create Registry</button>
										</a>
									</div>
								</div>

							</div>

						</div>
					</div>


					<div class="col-md-4 col-lg-4 margin_type">
						<div class="group_border">
							<div class="topic_color">
								<div class="row">
									<div class="col-3">
										<img src="<c:url value="${contextcdn }/resources/images/registry_m.png"/>" alt="wedding gift"
											class="img_fluid custom_img_fluid">
									</div>
									<div class="col-7" style="margin-left: -10px;">
										<h3>Wedding Gift</h3>
									</div>
								</div>
							</div>
							<div
								style="padding: 10px 2px 10px 5px; max-height: 300px; overflow-y: auto; overflow-x: hidden;">

								<div class="row" style="text-align: right; margin-bottom: 20px;">

									<div class="col-8" style="text-align: left; padding-left: 3em;">
										<span><strong>Scheme Name</strong></span>
									</div>
									<div class="col-4" style="padding-left: 1px;">
										<span><strong>1yr Return</strong></span>
									</div>
								</div>
								<c:forEach var="product" items="${annivesarylist}">
									<div class="row">
										<div class="col-1 radio_style">
											<input type="radio" name="scheme"
												value="${product.productScId}">
										</div>
										<div class="col-9" style="margin-left: -1px;">
											<strong><a href="#" data-toggle="modal"
												data-target="#${product.fundName}">${product.fundName}</a></strong>
										</div>
										<div class="col-2"
											style="margin-left: -10px; padding-left: 1px;">
											<fmt:formatNumber value="${product.PReturn_1Y * 100}"
												pattern="0.00" />
										</div>
									</div>

									<!--  Modal Code -->
									<div class="modal fade" id="${product.fundName}" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Scheme
														Details</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Name</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundName}</label>
														</div>
													</div>
													
													<div class="row">
														<div class="col-4">
															<label class="label_style">Scheme code</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.schemeCode}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Type</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundType}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV Date</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav_Date}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Risk</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.riskometer_value}</label>
														</div>
													</div>

												</div>
												<div class="modal-footer"></div>
											</div>
										</div>
									</div>

									<!--  Modal Code -->
								</c:forEach>

								<div class="row" style="margin-top: 15px;">
									<div class="col-6" style="margin: auto;">
										<a href="" style="text-decoration: none;" id="marriagehrf"
											>
											<button type="button"
												class="btn btn-outline-info btn-sm btn-block">
												Create Registry</button>
										</a>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>


				<!--  2nd row -->
				<div class="row registry_category">

					<div class="col-md-4 col-lg-4 margin_type" style="margin: -1px;">
						<div class="group_border">
							<div class="topic_color">
								<div class="row">
									<div class="col-3">
										<img src="<c:url value="${contextcdn }/resources/images/registry_bd.png"/>" alt="birthday"
											class="img_fluid custom_img_fluid">
									</div>
									<div class="col-7" style="margin-left: -10px;">
										<h3>Birthday</h3>
									</div>
								</div>

							</div>
							<div
								style="padding: 10px 2px 10px 5px; max-height: 300px; overflow-y: auto; overflow-x: hidden;">

								<div class="row" style="text-align: right; margin-bottom: 20px;">

									<div class="col-8" style="text-align: left; padding-left: 3em;">
										<span><strong>Scheme Name</strong></span>
									</div>
									<div class="col-4" style="padding-left: 1px;">
										<span><strong>1yr Return</strong></span>
									</div>
								</div>
								<c:forEach var="product" items="${productlist}">
									<div class="row">
										<div class="col-1 radio_style">
											<input type="radio" name="scheme"
												value="${product.productScId}">
										</div>
										<div class="col-9" style="margin-left: -1px;">
											<strong><a href="#" data-toggle="modal"
												data-target="#${product.fundName}">${product.fundName}</a></strong>
										</div>
										<div class="col-2"
											style="margin-left: -10px; padding-left: 1px;">
											<fmt:formatNumber value="${product.PReturn_1Y * 100}"
												pattern="0.00" />
										</div>
									</div>

									<!--  Modal Code -->
									<div class="modal fade" id="${product.fundName}" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Scheme
														Details</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Name</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundName}</label>
														</div>
													</div>
													
													<div class="row">
														<div class="col-4">
															<label class="label_style">Scheme code</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.schemeCode}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Type</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundType}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV Date</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav_Date}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Risk</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.riskometer_value}</label>
														</div>
													</div>

												</div>
												<div class="modal-footer"></div>
											</div>
										</div>
									</div>

									<!--  Modal Code -->
								</c:forEach>

								<div class="row" style="margin-top: 15px;">
									<div class="col-6" style="margin: auto;">
										<a href="" style="text-decoration: none;" id="retirementhrf"
											>
											<button type="button"
												class="btn btn-outline-info btn-sm btn-block">
												Create Registry</button>
										</a>
									</div>
								</div>

							</div>
						</div>

					</div>

					<div class="col-md-4 col-lg-4 margin_type">
						<div class="group_border">
							<div class="topic_color">
								<div class="row">
									<div class="col-3">
										<img src="<c:url value="${contextcdn }/resources/images/registry_st.png"/>" alt="tax saving"
											class="img_fluid custom_img_fluid">
									</div>
									<div class="col-7" style="margin-left: -10px;">
										<h3>Tax Saving</h3>
									</div>
								</div>

							</div>
							<div
								style="padding: 10px 2px 10px 5px; max-height: 300px; overflow-y: auto; overflow-x: hidden;">

								<div class="row" style="text-align: right; margin-bottom: 20px;">

									<div class="col-8" style="text-align: left; padding-left: 3em;">
										<span><strong>Scheme Name</strong></span>
									</div>
									<div class="col-4" style="padding-left: 1px;">
										<span><strong>1yr Return</strong></span>
									</div>
								</div>
								<c:forEach var="product" items="${taxsavinglist}">
									<div class="row">
										<div class="col-1 radio_style">
											<input type="radio" name="scheme"
												value="${product.productScId}">
										</div>
										<div class="col-9" style="margin-left: -1px;">
											<strong><a href="#" data-toggle="modal"
												data-target="#${product.fundName}">${product.fundName}</a></strong>
										</div>
										<div class="col-2"
											style="margin-left: -10px; padding-left: 1px;">
											<fmt:formatNumber value="${product.PReturn_1Y * 100}"
												pattern="0.00" />
										</div>
									</div>

									<!--  Modal Code -->
									<div class="modal fade" id="${product.fundName}" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Scheme
														Details</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Name</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundName}</label>
														</div>
													</div>
													
													<div class="row">
														<div class="col-4">
															<label class="label_style">Scheme code</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.schemeCode}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Type</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundType}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV Date</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav_Date}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Risk</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.riskometer_value}</label>
														</div>
													</div>

												</div>
												<div class="modal-footer"></div>
											</div>
										</div>
									</div>

									<!--  Modal Code -->
								</c:forEach>

								<div class="row" style="margin-top: 15px;">
									<div class="col-6" style="margin: auto;">
										<a href="" style="text-decoration: none;" id="taxsavinghrf"
											>
											<button type="button"
												class="btn btn-outline-info btn-sm btn-block">
												Create Registry</button>
										</a>
									</div>
								</div>

							</div>

						</div>
					</div>


					<div class="col-md-4 col-lg-4 margin_type">
						<div class="group_border">
							<div class="topic_color">
								<div class="row">
									<div class="col-3">
										<img src="<c:url value="${contextcdn }/resources/images/registry_p.png"/>" alt="parties"
											class="img_fluid custom_img_fluid">
									</div>
									<div class="col-7" style="margin-left: -10px;">
										<h3>Parties</h3>
									</div>
								</div>
							</div>
							<div
								style="padding: 10px 2px 10px 5px; max-height: 300px; overflow-y: auto; overflow-x: hidden;">

								<div class="row" style="text-align: right; margin-bottom: 20px;">

									<div class="col-8" style="text-align: left; padding-left: 3em;">
										<span><strong>Scheme Name</strong></span>
									</div>
									<div class="col-4" style="padding-left: 1px;">
										<span><strong>1yr Return</strong></span>
									</div>
								</div>
								<c:forEach var="product" items="${annivesarylist}">
									<div class="row">
										<div class="col-1 radio_style">
											<input type="radio" name="scheme"
												value="${product.productScId}">
										</div>
										<div class="col-9" style="margin-left: -1px;">
											<strong><a href="#" data-toggle="modal"
												data-target="#${product.fundName}">${product.fundName}</a></strong>
										</div>
										<div class="col-2"
											style="margin-left: -10px; padding-left: 1px;">
											<fmt:formatNumber value="${product.PReturn_1Y * 100}"
												pattern="0.00" />
										</div>
									</div>

									<!--  Modal Code -->
									<div class="modal fade" id="${product.fundName}" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Scheme
														Details</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Name</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundName}</label>
														</div>
													</div>
													
													<div class="row">
														<div class="col-4">
															<label class="label_style">Scheme code</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.schemeCode}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Fund Type</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.fundType}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">NAV Date</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.nav_Date}</label>
														</div>
													</div>

													<div class="row">
														<div class="col-4">
															<label class="label_style">Risk</label>
														</div>
														<div class="col-1 radio_style">
															<label>:</label>
														</div>
														<div class="col-7" style="margin-left: -10px;">
															<label>${product.riskometer_value}</label>
														</div>
													</div>

												</div>
												<div class="modal-footer"></div>
											</div>
										</div>
									</div>

									<!--  Modal Code -->
								</c:forEach>

								<div class="row" style="margin-top: 15px;">
									<div class="col-6" style="margin: auto;">
										<a href="" style="text-decoration: none;" id="partieshrf"
											>
											<button type="button"
												class="btn btn-outline-info btn-sm btn-block">
												Create Registry</button>
										</a>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

				<!--  -->
			</div>
			<!-- End of section 2  -->
		</div>

		<div class="section2">
			<div class="container">

				<div class="arrow_border">

					<div class="arrow_container">
						<span> <!-- <i class="fa fa-caret-down arrow_custom" aria-hidden="true"></i> -->
							<i class="arrow_custom"></i>
						</span>
					</div>
					<div>
						<span class="find_style">Find out more</span>
					</div>
				</div>

				<div
					style="margin-top: 30px; margin-bottom: 30px; text-align: center;">
					<a style="text-decoration: none;"
						href="<c:url value="/registry-mutual-funds/"/>"><button
							class="btn btn-danger btn-md"
							style="padding-left: 20px; padding-right: 20px;">Register
							&amp; Explore</button></a>
				</div>

				<div class="row">
					<div class="col-md-5 col-lg-5 image_style">
						<img
							src="<c:url value="${contextcdn }/resources/images/registry_pic1.png"/>" alt="registry representation"
							class="img-fluid" style="width: 90%;">
					</div>
					<div class="col-md-6 col-lg-6 contents">

						<div class="registry_c1">
							<span>Live life debt-free.</span>
						</div>
						<div class="registry_c2">
							<span>Self-efficient &amp; Intelligent Registry account
								will define and execute smart investment</span>
						</div>
						<div class="registry_c3">
							<span> Create Registry for birthday, anniversary, daily
								office expenses and many more </span>
						</div>

					</div>

				</div>
			</div>
		</div>
		<!--  End of section 2 -->

	</div>
	<jsp:include page="include/footer.jsp"></jsp:include>
	<script src="<c:url value="${contextcdn }/resources/js/freemi.main.js"/>"></script>
</body>
</html>