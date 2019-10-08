<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>BSE registration status</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="robots" content="noindex,nofollow" />

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link
	href="<c:url value="${contextcdn}/resources/css/bseinvestmentform.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
<style>
#sig-canvas {
	border: 1px solid #969696;
	cursor: crosshair;
	box-shadow: 0 0 6px 0px #b7b3b3;
}
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container-fluid" style="margin-bottom: 10rem;">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb" style="margin-left: 0px;">

				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/"><i
						class="fas fa-home"></i></a></li>
				<li class="breadcrumb-item active" aria-current="page">
					Register for Mutual Funds</li>
				<li class="breadcrumb-item active" aria-current="page">
					Registration status</li>
			</ol>
		</nav>

		<section style="padding: 20px 0px;">
			<div class="row"
				style="margin: auto; border: 1px solid #c1bdb8; padding: 20px 0px;">
				<div class="col-md-10 col-lg-10"
					style="margin: auto; text-align: center;">
					<h1 style="font-size: 25px; color: #464fd2;">Great! Your
						application registered.</h1>
					<div>
						<p>
							<i class="fas fa-check" style="color: #3fc53f;"></i> You have
							successfully submitted your investment details
						</p>
					</div>
					<div>


						<div class="progress mb-3">
							<div class="progress-bar bg-success" role="progressbar"
								id="myBar" style="width: 50%" aria-valuenow="50"
								aria-valuemin="0" aria-valuemax="100">
								<span id="statusp">Invest profile status 50%</span>
							</div>

						</div>
						<!-- <div class="progress">
							<div class="progress-bar bg-success" role="progressbar"
								style="width: 50%" aria-valuenow="50" aria-valuemin="0"
								aria-valuemax="100">Personal details registered</div>
							<div class="progress-bar progress-bar-striped bg-secondary"
								role="progressbar" style="width: 50%" aria-valuenow="30"
								aria-valuemin="0" aria-valuemax="100">Upload signed form</div>

						</div> -->
						
						</div>
						
						<c:choose>
						<c:when test="${KYCVERIFIED == 'Y'  }">
							
							<h4 style="font-size: 20px; color: #da7437;">What are the
							next steps?</h4>
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="aofoption">
									<h5>Option 1</h5>
									<ul style="list-style: none;font-size: 12px;">
										<li>1. Download and print the form</li>
										<li>2. Sign the form<br> Where to sign? <!-- <div style="margin-top: 5px;"> -->
											<!-- Button trigger modal --> <!-- <button type="button" class="btn btn-outline-primary btn-sm"
													style="font-size: 12px;" data-toggle="modal"
													data-target="#exampleModal">See Sample file</button> --> <span
											style="font-size: 12px; text-decoration: underline; color: #e86835; cursor: pointer;"
											data-toggle="modal" data-target="#exampleModal">See
												Sample file</span> <!-- </div> --> <!-- Modal -->
											<div class="modal fade" id="exampleModal" tabindex="-1"
												role="dialog" aria-labelledby="exampleModalLabel"
												aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="exampleModalLabel">Sample
																file</h5>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
															<embed
																src="https://resources.freemi.in/files/pdf/file1.pdf"
																frameborder="0" width="100%" height="400px">
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
										</li>
										<li>3. Login to account and go to your Dashboard</li>
										<li>4. Upload the signed form and complete registration</li>
									</ul>

									<c:if test="${not empty investForm.pan1}">
										<div style="margin-top: 20px; text-align: center;">
											<input type="hidden" id="mobdata"
												value="${investForm.mobile}"> <a
												href="/products/download/aof/${investForm.pan1}.pdf"
												target="_blank">
												<button type="button" class="btn btn-sm btn-info">
													<i class="fas fa-download"></i> Download your form <i
														class="fas fa-download"></i>
												</button>
											</a>
										</div>
									</c:if>
								</div>

							</div>
							<div class="col-md-4 col-lg-4">
								<div class="aofoption">
									<h5>Option 2</h5>
									<ul style="list-style: none;font-size: 12px;">
										<li>1. Sign using platform panel</li>
										<li>2. In case of joint holder, applicant 2 signature is required</li>
										<li>3. Upload signatures when complete to generate your AOF form</li>
									</ul>
									<div style="margin-top: 20px;">

										<div style="margin-top: 5px; text-align: center;">
											<!-- Button trigger modal -->

											<img
												src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
												class="img-fluid" style="height: 48px;"
												alt="Signature panel">
											<!-- <button type="button" class="btn btn-outline-primary btn-sm"
												style="font-size: 12px;" data-toggle="modal"
												data-target="#exampleModal1">Applicant 1</button>
											<button type="button" class="btn btn-outline-primary btn-sm"
												style="font-size: 12px;" data-toggle="modal"
												data-target="#exampleModal2">Applicant 2</button> -->

											<button type="button" class="btn btn-outline-primary btn-sm"
												style="font-size: 12px;" onclick="getmodal('sign1')">Applicant
												1</button>
											<button type="button" class="btn btn-outline-primary btn-sm"
												style="font-size: 12px;" onclick="getmodal('sign2')">Applicant
												2</button>



										</div>
										<jsp:include page="bse-mf-signature-panel.jsp"></jsp:include>
									
									</div>
								</div>
							</div>

						</div>
						
						<div class="" style="text-align: center; margin-top: 20px;">
						<!-- <button type="button" class="btn btn-info">AOF Ready for upload</button> -->
						<%-- <jsp:include page="aof-form-generation.jsp"></jsp:include>	 --%>
						<span id="signuploadstatus"></span>
						<div id="aofuploadbutton">
							<button class="btn btn-sm btn-primary" id="aofuploadbtn"
								hidden="hidden" onclick="initiateAOFUpload();">
								<span id="uploadtxt">UPLOAD YOUR AOF <i
									class="fas fa-upload"></i></span> <span id="uploadingtxt"
									style="display: none;">Uploading... <i
									class="fas fa-spinner fa-spin"></i></span>
							</button>
							<a href="/products/mutual-funds/purchase" id="purchasecon"
								hidden="hidden">
								<button class="btn btn-sm btn-success">
									COMPLETE PURCHASE <i class="fas fa-shopping-cart"></i>
								</button>
							</a>
						</div>

					</div>
							
							
						</c:when>
						
						<c:when test="${KYCVERIFIED == 'N'  }">
							
							<div>
							<h3>Thank you getting registered with us</h3>
							<h4>Our team will reach out to you to complete your KYC profile. Once completed, you will be able to carry out transaction.</h4>
							
							</div>
							
							
						</c:when>
						<c:otherwise>
						
						</c:otherwise>
						</c:choose>
						
				</div> 
			</div>
		</section>
		<!-- 
		<section style="background-color: aliceblue; padding: 30px 0px;">
			<div class="row" style="margin: auto; margin-top: 4rem;">
				<div class="col-md-8 col-lg-8"
					style="margin: auto; text-align: center;">
					<h3>Ready to upload?</h3>
					<div class="file-upload-wrapper">
						<input type="file" id="input-file-now" class="file-upload" />
					</div>
					<button class="btn btn-sm btn-outline-secondary" type="button"
						onclick="aoffileUpload();" style="margin-top: 20px;">UPLOAD
						YOUR SIGNED FILE</button>
						
					<div id="fileContents"></div>
				</div>
			</div>

		</section>
		 -->

	</div>
	
	<!-- BSE MF  -->
	<jsp:include page="./bsestarmfpowered.jsp"></jsp:include>
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>


<script src="<c:url value="${contextcdn}/resources/js/signaturepanel.js" />" async="true"></script>
</body>

</html>