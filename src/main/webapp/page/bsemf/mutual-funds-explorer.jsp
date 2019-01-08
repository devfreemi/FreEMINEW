<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../include/GoogleHeadTag.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mutual funds explorer</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextPath}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextPath}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextPath}/resources/js/pace.min.js" />"></script>
<script src="<c:url value="${contextPath}/resources/js/bseinvest.js" />"></script>
</head>
<body>
	<jsp:include page="../include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container-fluid">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb" style="margin-left: 0px;">

				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/"><i
						class="fas fa-home"></i></a></li>
				<li class="breadcrumb-item" aria-current="page">Mutual Funds</li>
				<li class="breadcrumb-item active" aria-current="page">Funds
					Explorer</li>
			</ol>
		</nav>
	</div>

	<section class="fund-explorer-top">
		<div class="row" style="margin: auto;">
			<div class="col-md-8 col-lg-8">
				<div class="mf-style1">
					<h1 style="font-size: 28px; border-bottom: 2px solid #ec8655;">Investments
						at your tips</h1>
					<ul>
						<li><i class="fas fa-check-circle" style="color: #30c730;"></i>
							Get better returns</li>
						<li><i class="fas fa-check-circle" style="color: #30c730;"></i>
							Extremely simple and easy</li>
						<li><i class="fas fa-check-circle" style="color: #30c730;"></i>
							SEBI- regulated</li>
					</ul>
				</div>
			</div>

			<div class="col-md-4 col-lg-4"
				style="min-height: 9rem; margin: auto;">
				<div id="carouselExampleControls" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<!-- <div class="carousel-item active">
							<div class="mf-style2">
								Invest to grow fund and save tax upto
								<h2>&#x20B9; 46,800</h2>
							</div>		
						</div> -->
						<div class="carousel-item active">
							<div class="mf-style3 mf-style3-back2">
								<h6>Benefits of Regular funds</h6>
								<ul>
									<li>Market analysis not required</li>
									<li>Always assisted by advisor</li>
									<li>More convenient</li>
									<li>Requires lesser time for investment</li>
								</ul>
							</div>
						</div>
						<div class="carousel-item">
							<div class="mf-style3 mf-style3-back1">
								<h6 style="color: #ffed60;">Investment Objectives</h6>
								<ul>
									<li>Wealth Creation</li>
									<li>Tax planning</li>
									<li>Retirement planning</li>
									<li>Goals based investment</li>
								</ul>
							</div>
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
		</div>
	</section>

	<c:if test="${FUNDSFOUND=='Y' }">
		<section class="fund-explorer">

			<div class="row" style="margin: auto;">
				<div class="col-md-12 col-lg-12" style="margin-bottom: 30px;">
					<h2 style="font-size: 26px;">Discover and explore top
						performing funds</h2>
				</div>
				<div class="col-md-12-col-lg-12"></div>
			</div>

			<div class="row" style="max-width: 100%; margin: auto;">
				<c:forEach items="${topFunds }" var="funds">
					<div class="col-md-2 col-lg-2"
						style="margin-bottom: 10px; overflow: auto;">

						<div class="card mf-box">
							<h5 class="mf-name-custom">${funds.schemeName }</h5>
							<div class="card-body" style="padding-top: 5px;">
								<div class="card-title"></div>
								<h6 class="card-subtitle mb-2 text-muted">
									<%-- ${funds.riskometer_value } | --%>
									${funds.schemeType }
								</h6>
								<div class="risk-bar">
									<div></div>
								</div>
								<div class="row card-text card-text-custom">
									<div class="col-7">
										<p>
											<%-- 	NAV (${funds.nav_Date }) <br> ${funds.nav } --%>
										</p>
									</div>
									<%-- <div class="col-5">
									<p>
										1yr Return <br> <span id="1yrreturn"> <fmt:formatNumber
												type="number" maxFractionDigits="3"
												value="${funds.PReturn_1Y * 100}" /> %
										</span>
									</p>
								</div> --%>
									<div class="col-7">
										<p>
											Minimum Investment <br> <i class="fas fa-rupee-sign"></i>
											<fmt:formatNumber type="number" maxFractionDigits="3"
												value="${funds.minPurchase }" />
										</p>
									</div>

								</div>

								<button type="button" class="btn btn-info btn-sm mf-btn-custom"
									onclick="invest('${funds.schemeCode }','${funds.schemeName }', '${funds.minPurchase }','${funds.amcCode }');">
									INVEST</button>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</section>
	</c:if>

	<c:if test="${FUNDSFOUND == 'N' }">
		<section style="margin-top: 40px;">
			<div class="row" style="margin: auto;">
				<div class="aol-md-12 col-lg-12"
					style="text-align: center; color: #dc2727;">
					<h5>Error fetching funds!!</h5>
				</div>
			</div>

		</section>
	</c:if>

	<section class="top-funds-discuss">
		<div class="row" style="margin: auto;">
			<div class="col-md-9 col-lg-9">
				<h4>Let the experts do their jobs</h4>
				<p style="font: 15px/18px robotoregula; color: #4c4c4c;">
					The <strong>Top 15 funds</strong> are chosen by FreEMI financial
					advisors on the basis of their trailing returns during different
					financial cycle. This may help stakeholders to make wiser decisions
					about their investment and save time from enormous research
					required. Based on this, one can check the performance, NAV, returns, etc of the performing Mutual Funds quickly to make suitable decision as reuired.
					
				</p>
			</div>
			<div class="col-md-3 col-lg-3"></div>
		</div>
	</section>

	<jsp:include page="../include/footer.jsp"></jsp:include>
	<jsp:include page="../include/selectedfund.jsp"></jsp:include>
	<script>
		function invest(mfCode, mfName, minimum, amcCode) {
			console.log("Reached- " + mfCode + " " + mfName);
			$("#myModal").modal();
			$("#schemeNameTitle").text(mfName);
			$("#schemeName").val(mfName);
			$("#minVal").text(minimum);
			$("#schemecode").val(mfCode);
			$("#amcCode").val(amcCode);
			return true;
		}
	</script>


</body>
</html>