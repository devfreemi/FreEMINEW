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
<script
	src="<c:url value="${contextPath}/resources/js/signaturepanel.js" />"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script> -->

</head>

<body class="back_set">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container">
		<div style="color: #FF5722;">
			<!-- class="dashboard_header" -->
			<h2>
				<span style="border-bottom: 1px solid;">DASHBOARD</span>
			</h2>
		</div>
		<!-- 
		<div class="home-view">
			<span>HOME VIEW</span>
		</div>
 -->
		<section class="top-section">
			<div class="row">
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style1 animated fadeIn">
						<div class="header header-back1">
							<h5>Loans</h5>
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
							<h5>Mutual Funds</h5>
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
							<a href="/products/mutual-funds/view-purchase-history"
								style="text-decoration: none; color: white;"> <span>VIEW
									DETAILS</span>
							</a>

						</div>
					</div>

				</div>
				<div class="col-md-4 col-lg-4 outer_box">
					<div class="box-dashboard box-style3 animated fadeIn">
						<div class="header header-back3">
							<h5>Insurance</h5>
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
			<div class="row">
				<div class="col-md-4 col-lg-4">
					<div class="profile-status-left">
						<h5 style="background: #dbdee6; padding: 3px;">Profile Status</h5>
						<c:choose>

							<c:when test="${PROFILE_STATUS == 'NOT_FOUND' }">
								<h4 style="font-family: none; color: #e6643c;">Register for
									investment</h4>
								<div class="progress" style="margin-bottom: 20px;">
									<div class="progress-bar progress-bar-striped bg-danger"
										role="progressbar" style="width: 10%" aria-valuenow="10"
										aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<a href="/products/mutual-funds/register?mf=01">
									<button class="btn btn-sm btn-secondary">Get
										Registered</button>
								</a>
							</c:when>

							<c:when test="${PROFILE_STATUS == 'REGISTRATION_INCOMPLETE' }">
								<h4 style="font-family: none; color: #e6643c;">Profile
									Incomplete</h4>
								<div class="progress" style="margin-bottom: 20px;">
									<div class="progress-bar progress-bar-striped bg-danger"
										role="progressbar" style="width: 10%" aria-valuenow="10"
										aria-valuemin="0" aria-valuemax="100"></div>
								</div>
								<a href="/products/mutual-funds/register?mf=04">
									<button class="btn btn-sm btn-secondary">Complete
										Registration</button>
								</a>
							</c:when>

							<c:when test="${PROFILE_STATUS == 'PROFILE_READY' }">
								<h4 style="color: #408ad8; font-family: serif;">Investment
									profile ready.</h4>
								<div class="progress"
									style="font-size: 10px; margin-bottom: 20px;">
									<div class="progress-bar bg-success" role="progressbar"
										style="width: 100%" aria-valuenow="100" aria-valuemin="0"
										aria-valuemax="100">Registration Complete</div>

								</div>
								<div style="text-align: center;">
									<!-- <p>Mode of Holding: </p> -->

									<a href="/products/mutual-funds/pending-payments"
										style="margin-bottom: 20px;">
										<button class="btn btn-sm btn-info" style="font-size: 12px;">Complete
											Pending payments</button>
									</a>
								</div>
							</c:when>

							<c:when test="${PROFILE_STATUS == 'AOF_PENDING' }">
								<strong style="color: #d41b1b;">Sign &amp; Upload your
									AOF Form</strong>
								<div class="progress" style="font-size: 10px;">
									<div class="progress-bar bg-success" role="progressbar"
										id="myBar" style="width: 50%" aria-valuenow="50"
										aria-valuemin="0" aria-valuemax="100">
										<span id="statusp">Details Registered</span>
									</div>
								</div>
								<p>You need to sign and upload your investment form before
									you can start investing.</p>
								<button type="button" class="btn btn-default btn-sm"
									style="font-size: 12px;" data-toggle="modal"
									data-target="#exampleModal1">Applicant 1</button>

								<button type="button" class="btn btn-default btn-sm"
									style="font-size: 12px;" data-toggle="modal"
									data-target="#exampleModal1">Applicant 2</button>

								<!-- Signature Modal - Applicant 1-->
								<div class="modal fade" id="exampleModal1" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel1"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header"
												style="background-color: #f57c45; color: white;">
												<h5 class="modal-title" id="exampleModalLabel">
													<img
														src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
														class="img-fluid" style="height: 48px;"
														alt="Signature panel"> E-Signature Panel
												</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body" style="text-align: center;">
												<div class="row">
													<div class="col-md-12">
														<canvas id="sig-canvas" style="border: 1px solid #b6b7b9;">Browser not supported!</canvas>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<button class="btn btn-sm btn-info" id="sig-submitBtn">Submit
															Signature</button>
														<!-- <button class="btn" id="btndownload">Download</button> -->
														<button class="btn btn-sm btn-secondary" id="sig-clearBtn">Clear
															Signature</button>
													</div>
												</div>
												<br />
												<div class="row">
													<div class="col-md-12">
														<textarea hidden="hidden" id="sig-dataUrl"
															class="form-control" rows="5"
															style="max-height: 100px; overflow: scroll;"
															readonly="readonly">NA</textarea>
													</div>
												</div>
												<br />
												<div class="row">
													<div class="col-md-12">
														<!-- <img id="sig-image" src="" alt="Signature-image" /> -->
														<img
															src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
															class="img-fluid" id="sig-image" alt="Signature Image">
													</div>
												</div>
											</div>
											<!-- <div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Close</button>
												<button type="button" class="btn btn-primary">Save
													changes</button>
											</div> -->
										</div>
									</div>
								</div>

								<!-- End of modal  -->
								<div>
									<span id="signuploadstatus" style="font-size: 11px;"></span>
								</div>
								<div id="aofuploadbutton">
									<button class="btn btn-sm btn-primary" id="aofuploadbtn"
										hidden="hidden"
										onclick="initiateAOFUpload(<%=session.getAttribute("userid").toString()%>);">
										UPLOAD YOUR AOF <i class="fas fa-upload"></i>
									</button>
								</div>

							</c:when>

							<c:when test="${PROFILE_STATUS == 'ERROR' }">
								<p>Failed to fetch your profile status!</p>
							</c:when>

							<c:otherwise>
								<p>Failed to check status currently.</p>
							</c:otherwise>

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
							aria-controls="freemi" aria-selected="true">Loans</a></li>
						<li class="nav-item"><a class="nav-link" id="profile-tab"
							data-toggle="tab" href="#fsecure" role="tab"
							aria-controls="fsecure" aria-selected="false">Insurance</a></li>
						<li class="nav-item"><a class="nav-link" id="contact-tab"
							data-toggle="tab" href="#registry" role="tab"
							aria-controls="registry" aria-selected="false">Mutual Funds</a></li>
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
											<caption>FreEMI Purchase History</caption>
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
																		aria-expanded="false"
																		style="font-size: 11px; padding: 10px; width: 5rem;">ACTION</button>
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
<script>
	(function() {
		window.requestAnimFrame = (function(callback) {
			return window.requestAnimationFrame
					|| window.webkitRequestAnimationFrame
					|| window.mozRequestAnimationFrame
					|| window.oRequestAnimationFrame
					|| window.msRequestAnimaitonFrame || function(callback) {
						window.setTimeout(callback, 1000 / 60);
					};
		})();

		var canvas = document.getElementById("sig-canvas");
		var ctx = canvas.getContext("2d");
		ctx.strokeStyle = "#305ab3";
		ctx.lineWidth = 2;

		var drawing = false;
		var mousePos = {
			x : 0,
			y : 0
		};
		var lastPos = mousePos;

		canvas.addEventListener("mousedown", function(e) {
			drawing = true;
			lastPos = getMousePos(canvas, e);
		}, false);

		canvas.addEventListener("mouseup", function(e) {
			drawing = false;
		}, false);

		canvas.addEventListener("mousemove", function(e) {
			mousePos = getMousePos(canvas, e);
		}, false);

		// Add touch event support for mobile
		canvas.addEventListener("touchstart", function(e) {

		}, false);

		canvas.addEventListener("touchmove", function(e) {
			var touch = e.touches[0];
			var me = new MouseEvent("mousemove", {
				clientX : touch.clientX,
				clientY : touch.clientY
			});
			canvas.dispatchEvent(me);
		}, false);

		canvas.addEventListener("touchstart", function(e) {
			mousePos = getTouchPos(canvas, e);
			var touch = e.touches[0];
			var me = new MouseEvent("mousedown", {
				clientX : touch.clientX,
				clientY : touch.clientY
			});
			canvas.dispatchEvent(me);
		}, false);

		canvas.addEventListener("touchend", function(e) {
			var me = new MouseEvent("mouseup", {});
			canvas.dispatchEvent(me);
		}, false);

		function getMousePos(canvasDom, mouseEvent) {
			var rect = canvasDom.getBoundingClientRect();
			return {
				x : mouseEvent.clientX - rect.left,
				y : mouseEvent.clientY - rect.top
			}
		}

		function getTouchPos(canvasDom, touchEvent) {
			var rect = canvasDom.getBoundingClientRect();
			return {
				x : touchEvent.touches[0].clientX - rect.left,
				y : touchEvent.touches[0].clientY - rect.top
			}
		}

		function renderCanvas() {
			if (drawing) {
				ctx.moveTo(lastPos.x, lastPos.y);
				ctx.lineTo(mousePos.x, mousePos.y);
				ctx.stroke();
				lastPos = mousePos;
			}
		}

		// Prevent scrolling when touching the canvas
		document.body.addEventListener("touchstart", function(e) {
			if (e.target == canvas) {
				e.preventDefault();
			}
		}, false);
		document.body.addEventListener("touchend", function(e) {
			if (e.target == canvas) {
				e.preventDefault();
			}
		}, false);
		document.body.addEventListener("touchmove", function(e) {
			if (e.target == canvas) {
				e.preventDefault();
			}
		}, false);

		(function drawLoop() {
			requestAnimFrame(drawLoop);
			renderCanvas();
		})();

		function clearCanvas() {
			canvas.width = canvas.width;
		}

		// Set up the UI
		var sigText = document.getElementById("sig-dataUrl");
		var sigImage = document.getElementById("sig-image");
		var clearBtn = document.getElementById("sig-clearBtn");
		var submitBtn = document.getElementById("sig-submitBtn");
		clearBtn.addEventListener("click", function(e) {
			clearCanvas();
			sigText.innerHTML = "NA";
			sigImage.setAttribute("src",
					"/products/resources/images/invest/sign1.png");

			/* var aofformimg = document.getElementById("aofformsignature");
					aofformimg.setAttribute("src", ""); */
			$("#signature1").val("");
			ctx.strokeStyle = "#305ab3";
			ctx.lineWidth = 2;
		}, false);
		submitBtn.addEventListener("click", function(e) {
			var dataUrl = canvas.toDataURL();
			sigText.innerHTML = dataUrl;
			sigImage.setAttribute("src", dataUrl);

			/* var aofformimg = document.getElementById("aofformsignature");
					aofformimg.setAttribute("src", dataUrl); */
			$("#signature1").val(dataUrl);
			submitSign();

		}, false);

	})();

	window.onload = function() {
		/* var dwn = document.getElementById('btndownload'), canvas = document
						.getElementById('sig-canvas'), context =
				// Event handler for download
				dwn.onclick = function() {
					download(canvas, 'myimage.png');
				} */

	}

	function download(canvas, filename) {
		/// create an "off-screen" anchor tag
		var lnk = document.createElement('a'), e;

		/// the key here is to set the download attribute of the a tag
		lnk.download = filename;

		/// convert canvas content to data-uri for link. When download
		/// attribute is set the content pointed to by link will be
		/// pushed as "download" in HTML5 capable browsers
		lnk.href = canvas.toDataURL("image/png;base64");

		/// create a "fake" click-event to trigger the download
		if (document.createEvent) {
			e = document.createEvent("MouseEvents");
			e.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0, false,
					false, false, false, 0, null);

			lnk.dispatchEvent(e);
		} else if (lnk.fireEvent) {
			lnk.fireEvent("onclick");
		}
	}

	/* $("button").click(function(){ */
	function submitSign() {

		/* console.log("signature- "+ $("#sig-dataUrl").text()); */
		$('#exampleModal1').modal('hide');
		$
				.post(
						"/products/mutual-funds/uploadsignRegisteredCustomer",
						/* $.post("/products/api/saveloanquery", */

						{
							sign1 : $("#sig-dataUrl").text(),
							sign2 : ""
						},
						function(data, status) {
							/* alert("Data: " + data + "\nStatus: " + status); */
							console.log(data);
							
							if (data == 'SUCCESS') {

								$("#signuploadstatus").text("Your AOF form is signed and ready for upload.");
								$("#signuploadstatus").css({"color": "green", "font-weight": "400"});
								$("#aofuploadbtn").removeAttr('hidden');
								move(75);

							}
							else if (data == 'NO_SESSION') {
								/* $('#exampleModal1').modal('hide'); */
								$("#signuploadstatus").text("Session lost. Kindly login to complete registration.");
								$("#signuploadstatus").css({"color": "red", "font-weight": "400"});
							}else if(data == 'FAIL'){
								$("#signuploadstatus").text("Unable to process your signature. Kindly contact admin to resolve your issue.");
								$("#signuploadstatus").css({"color": "red", "font-weight": "400"});
							}else{
								$("#signuploadstatus").text("Internal error. Kindly contact admin to resolve your issue..");
								$("#signuploadstatus").css({"color": "red", "font-weight": "400"});
							}

						})
				.fail(
						function(response) {
							/* $('#exampleModal1').modal('hide'); */
							$("#signuploadstatus").text("Failed to submit your signature. Please try again.");
							$("#signuploadstatus").css({"color": "red", "font-weight": "400"});
						});
	}

	function initiateAOFUpload(mobile) {

		console.log("Upload AOF for- " + mobile);
		$
				.get(
						"/products/mutual-funds/uploadsignedaof",
						/* $.post("/products/api/saveloanquery", */

						{
							mobile : mobile
						},
						function(data, status) {
							/* alert("Data: " + data + "\nStatus: " + status); */
							console.log(data);
							if (data == 'SUCCESS') {

								$("#signuploadstatus")
										.text(
												"Your AOF uploaded successfully. Registration process complete.");
								$("#aofuploadbtn").hide();
								$("#purchasecon").removeAttr('hidden');
								move(100);

							}
							if (data == 'INTERNAL_ERROR') {
								$('#exampleModal1').modal('hide');
								$("#signuploadstatus")
										.text(
												"Failed to upload your AOF. Kindly contact Admin.");

							}

							if (data == 'SESSION_MOB_MISMATCH') {
								$('#exampleModal1').modal('hide');
								$("#signuploadstatus")
										.text(
												"Session data mismatch. Kindly contact admin");

							}
							if (data == 'REQUEST_DENIED') {
								$('#exampleModal1').modal('hide');
								$("#signuploadstatus")
										.text(
												"Session lost. Kindly login to complete registration");

							}

						})
				.fail(
						function(response) {
							$('#exampleModal1').modal('hide');
							$("#signuploadstatus")
									.text(
											"Failed to make request. Please try again.");
						});
		/* 	}); */
	}

	function move(value) {
		var elem = document.getElementById("myBar");
		var val = document.getElementById("statusp");
		var width = 50;
		var id = setInterval(frame, 10);
		function frame() {
			/* if (width >= 100) { */
			if (width >= value) {
				clearInterval(id);
			} else {
				width++;
				elem.style.width = width + '%';
				$("#statusp").text('Invest profile status ' + width + '%');
			}
		}
	}
</script>
</html>