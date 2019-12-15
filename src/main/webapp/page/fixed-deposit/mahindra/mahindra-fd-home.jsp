<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fixed Deposit Investment, interest rate upto 8.50%*</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fixed Deposit Investment</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="keywords" content="Fixed Desposit" />

<meta name="description" content="fixed Deposit " />

<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow" />
<meta name="bingbot" content="index, follow" />

<link rel="canonical" href="<c:url value="${pageContext.request.contextPath}/fixed-deposit/"/>" />
<jsp:include page="/page/include/bootstrap.jsp"></jsp:include>
<%-- <link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	 rel="preload" as="style" onload="this.rel='stylesheet'"> --%>
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>" rel="preload" as="style" onload="this.rel='stylesheet'">
<script src="<c:url value="${contextcdn}/resources/js/mahindra-fd.js" />"
	type="text/javascript" defer="defer"></script>

</head>
<body>
	<jsp:include page="/page/include/header.jsp"></jsp:include>
	<div class="container-fluid">
		<section style="background: #f5fffaf2;">
			<div class="row">
				<div class="col-md-7 col-lg-7" style="margin: auto;">
					<div class="row" style="margin: auto;">
						<div class="col-md-8 col-lg-8 animated fadeIn"
							style="margin: auto; text-align: justify;">
							<h1 class="text-center text-sm-left" style="margin-top: 2rem;">
								<span class="text-white #f4511e deep-orange darken-1"
									style="padding: 1px 10px; border-radius: 4px;">Fixed
									Deposit</span>
							</h1>
							<h5>Invest in Fixed Deposit to take better control of your
								savings with Mahindra FInance.</h5>
							<h6>
								Get Interest Rate upto <strong class="text-muted">8.50%
									<sup>*</sup>
								</strong>
							</h6>

							<p style="font-size: 18px; font-weight: 600;">Dhan Samruddhi
								Scheme</p>
							<div class="row">
								<div class="col-md-6 col-lg-6" style="margin-bottom: 2rem;">
									<!-- Card -->
									<div class="card">
										<img
											src="<c:url value="${contextcdn}/resources/images/invest/people1.svg"/>"
											class="img-fluid" style="height: 5rem;">

										<!-- Card content -->
										<div class="card-body">

											<h4 class="card-title">
												<span>Public </span>
											</h4>
											<hr>
											<div class="card-text">
												<p class="text-right">Min. Investment</p>
												<div class="row">
													<div class="col-8">
														<label class="text-default">CUMULATIVE</label>
													</div>
													<div class="col-4 text-right">&#8377; 10,000</div>
													<div class="col-8">
														<label class="text-default">NON-CUMULATIVE</label>
													</div>
													<div class="col-4 text-right">&#8377; 25,000</div>

												</div>

											</div>
											<div class="text-center">
												<span class="btn btn-sm purple-gradient"
													style="margin: auto;" onclick="fdsaveForm('PU');">START SAVING <i
													class="fas fa-piggy-bank"></i></span>
											</div>

										</div>

									</div>
									<!-- Card -->
								</div>


								<div class="col-md-6 col-lg-6" style="margin-bottom: 2rem;">
									<!-- Card -->
									<div class="card" >
										<img
											src="<c:url value="${contextcdn}/resources/images/invest/senior-people1.svg"/>"
											class="img-fluid" style="height: 5rem;">

										<!-- Card content -->
										<div class="card-body">

											<h4 class="card-title"><span>Senior Citizen </span> </h4>
											<hr>
											<div class="card-text">
												<p class="text-right">Min. Investment</p>
												<div class="row">
													<div class="col-8">
														<label class="text-default">CUMULATIVE</label>
													</div>
													<div class="col-4 text-right">&#8377; 10,000</div>
													<div class="col-8">
														<label class="text-default">NON-CUMULATIVE</label>
													</div>
													<div class="col-4 text-right">&#8377; 25,000</div>

												</div>

											</div>
											<div class="text-center">
												<span class="btn btn-sm purple-gradient"
													style="margin: auto;" onclick="fdsaveForm('SR');">START SAVING <i
													class="fas fa-piggy-bank"></i></span>
											</div>
										</div>

									</div>
									<!-- Card -->
								</div>
							</div>
						</div>
					</div>

				</div>

				<div class="col-md-5 col-lg-5 d-none d-sm-block">
					<img
						src="<c:url value="${contextcdn}/resources/images/invest/fd-1.png"/>"
						class="img-fluid animated slideInRight">
				</div>

			</div>
		</section>

		<section style="margin-top: 3rem; margin-bottom: 5rem;">
			<div class="row" style="margin: auto;">
				<%-- <div class="col-md-3 col-lg-3"
					style="box-shadow: 0 0 6px 3px #ccc2c2; padding: 2rem;margin-bottom: 3rem;">
					<jsp:include page="mahindra-fd-form-basic.jsp"></jsp:include>
					<div class="d-block d-sm-none">
					<img
						src="<c:url value="${contextcdn}/resources/images/invest/fd-1.png"/>"
						class="img-fluid animated slideInRight">
					</div>
				</div> --%>



				<div class="col-md-9 col-lg-9" style="margin: auto;">
					<nav>
						<div class="nav nav-tabs" id="nav-tab" role="tablist">
						
							<a class="nav-item nav-link active" id="nav-features-tab"
								data-toggle="tab" href="#nav-fd-features" role="tab"
								aria-controls="nav-fd-features" aria-selected="true">Features</a> 
							<a
								class="nav-item nav-link" id="nav-doc-tab" data-toggle="tab"
								href="#nav-fd-doc" role="tab" aria-controls="nav-fd-doc"
								aria-selected="false">Documentation</a> 
							
							<a class="nav-item nav-link"
								id="nav-rate-tab" data-toggle="tab" href="#nav-fd-rate"
								role="tab" aria-controls="nav-fd-rate" aria-selected="false">Interest Rates</a>
							
							<a class="nav-item nav-link"
								id="nav-calc-tab" data-toggle="tab" href="#nav-fd-calc"
								role="tab" aria-controls="nav-fd-calc" aria-selected="false">Fixed Deposit Calculator</a>
						</div>
					</nav>
					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active" id="nav-fd-features"
							role="tabpanel" aria-labelledby="nav-features-tab">
							Features
							</div>
						<div class="tab-pane fade" id="nav-fd-doc" role="tabpanel"
							aria-labelledby="nav-profile-tab">
							DOC
						</div>
						<div class="tab-pane fade" id="nav-fd-rate" role="tabpanel"
							aria-labelledby="nav-contact-tab">
						Rate
						</div>
						<div class="tab-pane fade" id="nav-fd-calc" role="tabpanel"
							aria-labelledby="nav-contact-tab">
						Calculator
						</div>
					</div>
				</div>
			</div>

		</section>


	</div>

	<jsp:include page="/page/include/sub-footer.jsp"></jsp:include>
	<jsp:include page="/page/include/footer.jsp"></jsp:include>
	<jsp:include page="mahindra-fd-form-basic.jsp"></jsp:include>
</body>
</html>