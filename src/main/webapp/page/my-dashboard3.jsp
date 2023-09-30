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
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"> -->
<!-- <link
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet"> -->
<script src="<c:url value="${contextcdn}/resources/js/investment.js" />" async="true"></script>


<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script> -->



<script type="text/javascript">
var reqid='<%=session.getAttribute("userid").toString()%>';
</script>

</head>
<body
	onload="getMfData('${PROFILE_STATUS}','${pan }','<%=session.getAttribute("userid").toString()%>');">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="col-md-10 col-12 my-4 mx-auto">
		<div class="row mx-auto mw-100">
			<div class="col-md-3 col-12 px-2 px-md-2">
				<div class="row">
					<!-- <div class="accordion" id="accordionExample"> -->
					<div class="col-4 col-md-12 card z-depth-0 my-3 my-md-0 px-md-2 px-0">
						<div class="card-header p-0 border-bottom-0" id="headingOne">
							<h2 class="elss-bottom-topic-active panelOne mb-0 p-md-3 p-2 rounded-top collapsed text-center">
								Mutual Fund
							</h2>
						</div>
					</div>
					<div class="col-4 col-md-12  card z-depth-0 my-3 px-md-2 px-2">
						<div class="card-header p-0 border-bottom-0" id="headingTwo">
							<h2 class="elss-bottom-topic panelTwo mb-0 p-md-3 p-2 collapsed text-center">
								Loan
							</h2>
						</div>
					</div>
					<div class="col-4 col-md-12  card z-depth-0 my-3 my-md-0 px-md-2 px-0">
						<div class="card-header p-0 border-bottom-0" id="headingThree">
							<h2 class="elss-bottom-topic panelThree mb-0 p-md-3 p-2 rounded-bottom collapsed text-center">
								Fixed Deposit
							</h2>
						</div>
					</div>
					
					<div class="col-12 col-md-12 my-3 my-md-5 px-md-2 px-0">
						<div class="card card-body px-2 shadow" id="aof">
							<jsp:include page="include/my-dashboard-mf-aof-status2.jsp"></jsp:include>
					
						</div>
					</div>
					<!-- </div> -->
				</div>
			</div>
			<div class="col-12 col-md-9 px-0 px-md-2">
				<!--Main layout-->
				<main>
					<!-- One Start -->
					<div id="collapseOne" class="description-content py-3 px-0 px-md-4">
						<!-- Loader -->
						<!-- <div class="col-3 loader mx-auto text-center my-5">
	            							<div class="d-flex justify-content-center">
	            								<div class="spinner-grow text-warning" role="status">
	            									<span class="visually-hidden">Loading...</span>
	            								</div>
	            							</div>
	            						</div> -->
						<!-- Loader -->
						<div class="row mx-auto">
							<div class="col-12">
								<p class="text-center tab-head">Your Investment Protfolio</p>
								<div class="card card-body card-bg">
									<div class="row content-justify-center">
										<div class="col-md-3 col-12 mx-auto text-md-center">
											<p class="mf-dash-head mb-md-3 mb-0">Current Value</p>
											<p class="mf-dash-value"><i class="fas fa-rupee-sign"></i>&nbsp;<span id="marketval"> 0</span></p>
										</div>
										<div class="col-md-3 col-12 mx-auto text-md-center d-none d-lg-block d-md-none">
											<p class="mf-dash-head mb-md-3 mb-0">Investment</p>
											<p class="mf-dash-value mb-md-3 mb-0"><i class="fas fa-rupee-sign"></i>&nbsp;<span id="inval">
												0</span></p>
										</div>
										<div class="col-md-3 col-12 mx-auto text-md-center">
											<p class="mf-dash-head mb-md-3 mb-0">Returns</p>
											<p class="mf-dash-value-ret mb-md-3 mb-0"><i class="fas fa-rupee-sign">&nbsp;</i><span id="resultRet">
												0 </span>&nbsp;<i class="fas fa-arrow-alt-circle-up"></i></p>
										</div>
										<div class="col-md-3 col-12 mx-auto text-md-center d-none d-lg-block d-md-none">
											<p class="mf-dash-head mb-md-3 mb-0">ANN Returns %</p>
											<p class="mf-dash-value-ret mb-md-3 mb-0" id="resultRetPer">0%</p>
										</div>
									</div>
								</div>
								<div class="col-12 my-4 px-0">
									<p class="text-center tab-head pt-md-3 pt-0">Your Investments</p>
									
									<div class="row mt-3 mt-md-3 justify-content-center">
										<!-- <button class="col-md-3 col-lg-4 col-8 btn btn-load-more all-trans" >Show all Investment <i
												class="fas fa-spinner"></i></button> -->
												<span id="msgmfapi" class="text-center"></span>
												<button class="col-md-3 col-lg-4 col-8 btn btn-load-more all-trans" id="fetchFolio" onclick="getMFPortfolioData(<%=session.getAttribute("userid").toString()%>,'${PROFILE_STATUS}');">Fetch your Portfolio</button>

									</div>
									
									<div class="all-pur">
										<jsp:include page="include/my-dashboard-mf-profile.jsp"></jsp:include>
									</div>
								</div>
							</div>
						</div>
					</div>
	
					<!-- One End || Two Start -->
					<div id="collapseTwo" class="description-content py-3 px-0 px-md-4">
						<div class="row mx-auto">
							<div class="col-12">
								<div class="card card-body">
									<div class="row justify-content-center">
										<div class="col-12 col-md-6">
											<p class="text-center text-loan-status">Track your HDFC Personal Loan Status.
											</p>
										</div>
										<div class="col-12 col-md-12 loan-status my-0">
											<jsp:include page="include/my-dashboard-loan-profile.jsp"></jsp:include>
										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
	
					<!-- TWO END || THREE START -->
					<div id="collapseThree" class="description-content py-3 px-0 px-md-4">
						<div class="row mx-auto">
							<div class="col-12 mb-3">
								<div class="card card-body shadow">
									<div class="row content-justify-center">

												<div class="col-md-4 col-6 mx-auto text-md-center  text-center">
													<p class="fd-dash-head mb-md-3 mb-0">Total Maturity</p>
													<p class="fd-dash-value mb-md-0 mb-0"><i class="fas fa-rupee-sign"></i> <span id="totalfdmaurity">0.00</span> </p>
												</div>
												<div class="col-md-4 col-6 mx-auto text-md-center  text-center">
													<p class="fd-dash-head mb-md-3 mb-0">Total FD Savings</p>
													<p class="fd-dash-value mb-md-0 mb-0"><i class="fas fa-rupee-sign"></i> <span id='totalfdprincipal'>0.00
														</span></p>
												</div>
												<div class="col-md-4 col-12 d-md-none d-none d-lg-block mx-auto text-md-center  text-center">
													<p class="fd-dash-head mb-md-3 mb-0">Tenure (Month)</p>
													<p class="fd-dash-value mb-md-0 mb-0"><i class="fas fa-calendar-week"></i> <span id='tenure'>12
														</span></p>
												</div>
												<div class="col-12 mx-auto text-center py-md-0 py-3">
													<span id="fdfetchmsg" class="font-weight-bold text-danger"></span>
												</div>
												<div class="col-md-12 col-lg-12 col-12 mx-auto text-center ">
													<span class="btn btn-info" id="balancecheckid" onclick="getfdbalance('<%=session.getAttribute("userid").toString()%>','${pan }');">
														Refresh balance &nbsp;&nbsp; <i class="fas fa-sync-alt"></i>
													</span>
												</div>

										
									</div>
								</div>
							</div>
							<div class="col-12">
								<div class="card card-body">
									<p class="text-center fd-status-text mb-0">Track your Mahindra Finance Fixed Deposit Status.
									</p>
									<div class="fd-status mt-3 mb-2">
										<jsp:include page="include/my-dashboard-fd-profile.jsp"></jsp:include>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</main>
				<!--Main layout-->
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function () {

			$("#collapseOne").show();
			$("#aof").show();
			$("#fdTab").show(); 
			$("#collapseTwo").hide();
			$("#collapseThree").hide();
			$(".loader").hide();
			$(".all-pur").hide();

			$("#headingOne").click(function () {
				$("#collapseOne").show();
				$("#aof").show();
				$("#fdTab").hide(); 
				$("#collapseTwo").hide();
				$("#collapseThree").hide();
				// PANEL HIDE AND SHOW FOR MF
				$(".panelOne").addClass("elss-bottom-topic-active");
				$(".panelTwo").removeClass("elss-bottom-topic-active");
				$(".panelTwo").addClass("elss-bottom-topic");
				$(".panelThree").removeClass("elss-bottom-topic-active");
				$(".panelThree").addClass("elss-bottom-topic");
			});
			$("#headingTwo").click(function () {
				$("#collapseOne").hide();
				$("#aof").hide();
				$("#fdTab").hide(); 
				$("#collapseTwo").show();
				$("#collapseThree").hide();
				// PANEL HIDE AND SHOW FOR LOAN
				$(".panelTwo").addClass("elss-bottom-topic-active");
				$(".panelTwo").removeClass("elss-bottom-topic");
				$(".panelThree").removeClass("elss-bottom-topic-active");
				$(".panelThree").addClass("elss-bottom-topic");
				$(".panelOne").removeClass("elss-bottom-topic-active");
				$(".panelOne").addClass("elss-bottom-topic");
			});
			$("#headingThree").click(function () {
				$("#collapseOne").hide();
				$("#aof").hide();
				$("#fdTab").show(); 
				$("#collapseTwo").hide();
				$("#collapseThree").show();
				// PANEL HIDE AND SHOW FOR FD
				$(".panelThree").addClass("elss-bottom-topic-active");
				$(".panelThree").removeClass("elss-bottom-topic");
				$(".panelOne").removeClass("elss-bottom-topic-active");
				$(".panelOne").addClass("elss-bottom-topic");
				$(".panelTwo").removeClass("elss-bottom-topic-active");
				$(".panelTwo").addClass("elss-bottom-topic");

			});

			$(".all-trans").click(function () {
				$(".all-trans").hide();
				$(".all-pur").show();
			});


		});
	</script>
	
	<jsp:include page="include/sub-footer.jsp"></jsp:include>
	<jsp:include page="include/footer.jsp"></jsp:include>
	<jsp:include page="include/loan-status-modal.jsp"></jsp:include>
</body>
<%-- <script src="<c:url value="${contextcdn}/resources/js/signaturepanel.js" />" defer="defer"></script> --%>
</html>