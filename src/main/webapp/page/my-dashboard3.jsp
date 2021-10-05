<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Dashboard</title>
<link rel="canonical" href="https://www.freemi.in/products/my-dashboard/" />
<meta name="robots" content="index, nofollow">
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextcdn}/resources/css/my-dashboard.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<link
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/investment.js" />" async="true"></script>

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script> -->

<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" async="true"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.62/pdfmake.min.js" async="true"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.62/vfs_fonts.js" async="true"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js" async="true"></script>
<script type="text/javascript">
var reqid='<%=session.getAttribute("userid").toString()%>';
</script>
<style>
table td {
	font-size: 11px;
	font-weight: 500;
}

table th {
	font-size: 12px;
	font-weight: 500;
}

.mftransdetailhead>th {
	font-size: 11px;
	padding: 2px;
}

.table thead th {
	vertical-align: middle;
}

.collapsed .fas {
	transform: rotate(-180deg);
	transition-duration: 1s;
}
</style>

</head>
<body
	onload="getMfData('${PROFILE_STATUS}','${pan }','<%=session.getAttribute("userid").toString()%>');">
	<jsp:include page="include/header.jsp"></jsp:include>

	<div class="container">

		<section class="top-section">
			<div class="row">
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style1 animated fadeIn">
						<div class="header header-back1">
							<h5>Loans</h5>
						</div>
						<div class="box-body">
							<!-- <div class="row">
								<div class="col-6">Loan Status</div>
								<div class="col-6">Active</div>
							</div>
							<div class="row">
								<div class="col-6">Total loan(s)</div>
								<div class="col-6">2</div>
							</div> -->
							<div class="row">
								<div class="col-8">Track your HDFC Personal Loan Status.</div>
							</div>

						</div>
						<div class="footer_link">
							<!-- <span>VIEW DETAILS</span> -->
						</div>
					</div>

				</div>
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style2 animated fadeIn" id="dashbox2">
						<div class="header header-back2">
							<h5>Mutual Funds</h5>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-6">
									<h6>TOTAL ASSETS</h6>
								</div>
								<div class="col-6">
									<h5>
										<i class="fas fa-rupee-sign"> </i> <span id="inval">0</span>
									</h5>
								</div>
							</div>

							<div class="row">
								<div class="col-6">
									<h6>MARKET VALUE</h6>
								</div>
								<div class="col-6">
									<h5>
										<i class="fas fa-rupee-sign"> </i> <span id="marketval">0</span>
									</h5>
								</div>
							</div>

							<div>
								<span id="mffetchmsg" class=""
									style="font-size: 9px; color: #fffa66;"></span>
							</div>

						</div>
						<div class="footer_link">
							<a href="/products/mutual-funds/view-purchase-history"
								style="text-decoration: none; color: white;"> <span>
									TRANSACTIONS HISTORY</span>
							</a>

						</div>
					</div>

				</div>
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style3 animated fadeIn">
						<div class="header header-back3">
							<h5>Fixed Deposit</h5>
						</div>
						<div class="box-body" id="balancedata">
						
							<div class="text-center">
								<span class="btn btn-sm btn-white" id="balancecheckid" onclick="getfdbalance('<%=session.getAttribute("userid").toString()%>','${pan }');">View Balance</span>
							</div>

							<div>
								<span id="fdfetchmsg" class=""
									style="font-size: 9px; color: #fffa66;"></span>
							</div>
						</div>
						<div class="footer_link">
								<a href="/products/fixed-deposit/view-purchase-history"
									style="text-decoration: none; color: white;"> <span>
										TRANSACTIONS HISTORY</span>
								</a>


						</div>
					</div>

				</div>
			</div>

		</section>


		<section class="profile-status-section">

			<jsp:include page="include/my-dashboard-mf-aof-status.jsp"></jsp:include>
		</section>


		<section class="desctiopn-section">
			<div class="row" style="margin: auto;">
				<div class="col-md-12 col-lg-12">

					<ul class="nav nav-tabs" id="myTab" role="tablist">

						<li class="nav-item"><a class="nav-link active" id="home-tab"
							data-toggle="tab" href="#freemi" role="tab"
							aria-controls="freemi" aria-selected="true">Loans</a></li>
							
						<li class="nav-item" ><a class="nav-link" id="fd-tab"
							data-toggle="tab" href="#fixeddeposit" role="tab"
							aria-controls="fixeddeposit" aria-selected="false">Fixed Deposit</a></li>

						<li class="nav-item" ><a
							class="nav-link" id="contact-tab" data-toggle="tab"
							href="#registry" role="tab" aria-controls="registry"
							aria-selected="false">Mutual Funds</a></li>
					</ul>

					<div class="tab-content" id="myTabContent">

						<div class="tab-pane fade show active custom-tab" id="freemi"
							role="tabpanel" aria-labelledby="home-tab">

							<div class="fremi-outer-shell">
								<jsp:include page="include/my-dashboard-loan-profile.jsp"></jsp:include>
							</div>

						</div>
						<div class="tab-pane fade custom-tab" id="fixeddeposit" role="tabpanel"
							aria-labelledby="fd-tab">

							<div class="fremi-outer-shell">
								<jsp:include page="include/my-dashboard-fd-profile.jsp"></jsp:include>
							</div>

						</div>


						<div class="tab-pane fade custom-tab" id="registry"
							role="tabpanel" aria-labelledby="contact-tab">
							<div class="fremi-outer-shell">
								<jsp:include page="include/my-dashboard-mf-profile.jsp"></jsp:include>
							</div>
						</div>

					</div>
				</div>
			</div>
		</section>

	</div>
	
	<jsp:include page="include/sub-footer.jsp"></jsp:include>
	<jsp:include page="include/footer.jsp"></jsp:include>
	<jsp:include page="include/loan-status-modal.jsp"></jsp:include>
</body>
<script src="<c:url value="${contextcdn}/resources/js/signaturepanel.js" />" defer="defer"></script>
</html>