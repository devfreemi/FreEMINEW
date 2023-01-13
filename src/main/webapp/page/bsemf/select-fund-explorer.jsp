<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>FreEMI MF Transaction</title>
<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="" />
<meta name="robots" content="index,follow" />
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"
	async="true"></script>

<link
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js"></script>

<style type="text/css">
table td {
	font-size: 12px;
	font-weight: 300;
}

table.dataTable tbody th, table.dataTable tbody td {
	padding: 2px 2px;
}

table.dataTable thead th, table.dataTable thead td {
	padding: 2px 18px;
	border-bottom: 1px solid #6f6d6d;
}

.dataTables_wrapper .dataTables_filter input {
	margin-left: 0;
}

.investbutton {
	margin: 0;
	padding: 5px 10px;
	width: 5.5rem;
	font-size: .64rem;
}
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container" style="min-height: 100vh;">
		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">
				<h3>
					<span
						style="color: #fb7736; font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem;">Equity
						Large Cap</span>
				</h3>
				
				<div
					class="table-responive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample1"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead style="background-color: #fb3f36c7; color: white;">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'EQ_LARGE_CAP'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p>
										</td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>

											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>


					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #2897c3;">Equity
						Large &amp; Mid Cap</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="success-color-dark #007E33 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'EQ_LARGE_MID_CAP'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #a062ef;">Equity
						- Multi Cap</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="primary-color-dark #0d47a1 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'EQ_MULTI_CAP'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #127096;">Equity
						- Mid Cap</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#e53935 red darken-1 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'EQ_MID_CAP'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #cd3be6e8;">Equity
						- Small Cap</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#1565c0 blue darken-3 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'EQ_SMALL_CAP'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #ff8d28e8;">Equity-
						Value-Oriented</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#f4511e deep-orange darken-1 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'EQ_VAL_ORIENTED'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #1fca35e8;">Equity-
						Tax Saving</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#2e3951 mdb-color darken-2 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'EQ_TAX_SAVING'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #09a960e8;">Hybrid-
						Equity Savings</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="rgba(233, 30, 99, 0.7) rgba-pink-strong text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'HYD_EQ_SAVING'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #17a1b9e8;">Hybrid-
						Aggressive (Equity Oriented)</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#43a047 green darken-1 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'HYB_AGRESSIVE_EQ_ORNT'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #e434bde8;">Hybrid-
						Conservative (Debt Oriented)</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#00796b teal darken-2 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'HYB_CONS_DEBT_ORNT'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
										<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>



		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #39a932e8;">Debt
						- Medium Term</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#e53935 red darken-1 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'DEBT_MEDIUM_TERM'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
										<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #ec663ce8;">Debt
						- Short Term</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#1976d2 blue darken-2 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'DEBT_SHORT_TERM'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
										<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #37c2c3e8;">Debt
						- Dynamic Bond</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#00838f cyan darken-3 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'DEBT_DYNAMIC_BOND'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


		<div class="row" style="margin: auto; overflow: auto;">
			<div class="col-md-12 col-lg-12"
				style="margin: auto; margin-top: 30px;">

				<h3>
					<span
						style="font-weight: 400; border-bottom: 2px solid #c1c1c1; padding-right: 2rem; color: #ad66f9e8;">Debt
						- Corporate Bond</span>
				</h3>
				<div
					class="table-responsive dataTables_wrapper dt-bootstrap4 animated fadeIn"
					style="margin-top: 30px;">
					<!-- <table class="table table-striped table-sm" id="dtBasicExample"> -->
					<table class="table table-striped table-sm">
						<%-- <caption>Funds Explorer</caption> --%>
						<thead class="#f57c00 orange darken-2 text-white">
							<tr>
								<th scope="col" style="padding-right: 10rem;">Fund Name</th>
								<th scope="col">Scheme Type</th>
								<th scope="col" style="padding-right: 4rem;">NAV</th>
								<!--<th scope="col">Dividend Nav</th>-->
								<th scope="col">Minimum Lumpsum (&#8377;)</th>
								<th scope="col">Minimum SIP (&#8377;)</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="listVar" items="${fundsexplorer}">
								<c:if test="${listVar.fundCatergory == 'DEBT_CORP_BOND'}">
									<tr>
										<td><strong>${listVar.schemeName }</strong></td>
										<td>${listVar.schemeType }</td>
										<td>${listVar.nav }<strong>(G)</strong>
											<p class="navDateStyle mb-1">(As on ${listVar.gNavDate })</p></td>
										<!--<td>${listVar.reinvNav }</td>-->
										<td>${listVar.lumpsumminPurchaseAmt }</td>
										<td>${listVar.sipMinInstallAmnt }</td>
										<td>
											<button type="button" class="btn btn-primary investbutton"
												data-toggle="modal" data-target="#selectfundmodal"
												data-gscode="${listVar.growthSchemeCode }"
												data-rscode="${listVar.reinvSchemeCode }"
												data-scname="${listVar.schemeName }"
												data-lmminpamnt="${listVar.lumpsumminPurchaseAmt }"
												data-amccode="${listVar.amcCode }"
												data-smiamnt="${listVar.sipMinInstallAmnt }"
												data-sipdate="${listVar.sipDates }"
												data-rta="${listVar.rtaAgent }"
												data-icon="${listVar.amcicon}">
												INVEST <i class="fas fa-shopping-cart"></i>
											</button>

										</td>
									</tr>
								</c:if>
							</c:forEach>

						</tbody>

					</table>
				</div>

			</div>
		</div>


	</div>


	<div class="row" style="margin-top: 3rem;">
		<div class="col-md-8 col-lg-8"
			style="text-align: center; font-size: 11px; color: black; font-weight: 400; margin: auto;">
			<hr
				style="border: 0; height: 1px; background-image: -webkit-linear-gradient(left, #f0f0f0, #f97d4d, #f0f0f0);">
			<span>*Mutual fund investments are subject to market risks.
				Please read the offer document(s) carefully before investing.</span>
		</div>
	</div>

	<!-- BSE MF  -->
	<jsp:include page="./bsestarmfpowered.jsp"></jsp:include>
	<!-- END BSE MF  -->
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
	<jsp:include page="../include/selectedfund.jsp"></jsp:include>
</body>

<script type="text/javascript">
	/* for(i=1;i<=2;i++){
	var nameid="#dtBasicExample"+i; */

	$(document).ready(function() {
		$("#dtBasicExample1").DataTable({
			"columns" : [ {
				"orderable" : true
			}, {
				"orderable" : false
			}, {
				"orderable" : false
			},
			/*   null,
			  null,
			  null,
			  null, */
			{
				"orderable" : false
			}, {
				"orderable" : false
			}, {
				"orderable" : false
			} ],
			"pagingType" : "numbers",
		});
		$('.dataTables_length').addClass('bs-select');

	});
</script>
<script src="<c:url value="${contextcdn}/resources/js/bseinvest.js" />"></script>

<script type="text/javascript">
	$("#selectfundmodal")
			.on(
					'show.bs.modal',
					function(event) {

						var radioValue = $("input[name='investype']:checked")
								.val();
						//console.log("Selected- " + $(event.relatedTarget).data("lmminpamnt"));

						//console.log("SIP dates- "+ $(event.relatedTarget).data("sipdate"));
						minsip = $(event.relatedTarget).data("smiamnt");
						minlumpsum = $(event.relatedTarget).data("lmminpamnt");
						$("#schemeNameTitle").text(
								$(event.relatedTarget).data("scname"));
						$("#schemeName").val(
								$(event.relatedTarget).data("scname"));
						$("#rtaAgent").val($(event.relatedTarget).data("rta"));
						var dtarray = ($(event.relatedTarget).data("sipdate"))
								.split(",");

						if ($(event.relatedTarget).data("icon") != undefined) {
							document.getElementById("amcicondisplay").src = "https://resources.freemi.in/products/resources/images/partnerlogo/mf/"
									+ $(event.relatedTarget).data("icon");
							$("amcicondisplay").css("background", "white");
						} else {
							document.getElementById("amcicondisplay").src = "https://resources.freemi.in/products/resources/images/partnerlogo/mf/default-amc.png";
							$("amcicondisplay").css("background", "white");
						}

						var x = document.getElementById("sipOtherDates");
						$("#sipOtherDates").empty();
						/*
						for (i = 0; i < x.length; i++) {
							  x.remove(i);
							}*/

						for (i = 0; i < dtarray.length; i++) {
							//		console.log(dtarray[i]);
							var option = document.createElement("option");
							option.text = dtarray[i];
							option.value = dtarray[i];
							x.add(option);
						}
						x.selectedIndex = 1;

						if (radioValue == 'SIP') {
							$("#sipbox").show();

							$("#minvalreq").text(
									$(event.relatedTarget).data("smiamnt"));
						} else if (radioValue == 'LUMPSUM') {
							$("#sipbox").hide();
							$("#minvalreq").text(
									$(event.relatedTarget).data("lmminpamnt"));
						} else {
							$("#sipbox").hide();
							$("#minvalreq").text("0");
						}
						$("#minValls").hide();
						$("#schemecode").val(
								$(event.relatedTarget).data("gscode"));
						console.log("Reonv code- "
								+ $(event.relatedTarget).data("rscode"));
						$("#reinvSchemeCode").val(
								$(event.relatedTarget).data("rscode"));
						$("#amcCode").val($(event.relatedTarget).data("amccode"));

					});
</script>
</html>