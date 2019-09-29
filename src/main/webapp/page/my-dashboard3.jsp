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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<script src="<c:url value="${contextcdn}/resources/js/investment.js" />"></script>

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script> -->

<link
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js"></script>

<script  type="text/javascript">
var FILE_UPLOAD=${FILE_IPLOAD};

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
</style>
</head>

<body class="back_set">
	<jsp:include page="include/header.jsp"></jsp:include>
	<fmt:setLocale value="en_IN" />

	<div class="container" style="margin-bottom: 5rem;">
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
							<!-- <div class="row">
								<div class="col-6">Loan Status</div>
								<div class="col-6">Active</div>
							</div>
							<div class="row">
								<div class="col-6">Total loan(s)</div>
								<div class="col-6">2</div>
							</div> -->
							<div class="row">
								<div class="col-8">We will be available soon!</div>
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
										<fmt:formatNumber type="number" pattern="##,###.00"
											minFractionDigits="1" minIntegerDigits="1"
											maxFractionDigits="2" groupingUsed="true"
											value="${totalasset }" />
									</h5>
								</div>
							</div>
							
							<div class="row">
								<div class="col-6">
									<h6>MARKET VALUE</h6>
								</div>
								<div class="col-6">
									<h5>
										<i class="fas fa-rupee-sign"> </i>
										<fmt:formatNumber type="number" pattern="##,###.00"
											minFractionDigits="1" minIntegerDigits="1"
											maxFractionDigits="2" groupingUsed="true"
											value="${totalmarketval }" />
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
							<!-- <div class="row">
								<div class="col-6">Life Secured</div>
								<div class="col-6">No</div>
							</div>
							<div class="row">
								<div class="col-6">Plans Activated</div>
								<div class="col-6">0</div>
							</div> -->

							<div class="row">
								<div class="col-8">We will be available soon!</div>
							</div>
						</div>
						<div class="footer_link">
							<!-- <span>VIEW DETAILS</span> -->

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
						
						<div>
								<span id="signuploadstatus" style="font-size: 11px;"></span>
						</div>
						<hr>
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
								<strong style="color: #346be6;">Sign &amp; Upload your
									AOF Form</strong>
								<div class="progress" style="font-size: 10px;">
									<div class="progress-bar bg-success" role="progressbar"
										id="myBar" style="width: 50%" aria-valuenow="50"
										aria-valuemin="0" aria-valuemax="100">
										<span id="statusp">Details Registered</span>
									</div>
								</div>
								<p>You need to sign and upload your investment form before
									you can start investing.
									<c:if test="${not empty pan}">
											<input type="hidden" id="mobdata"
												value="${investForm.mobile}"> <a
												href="/products/download/aof/${pan}.pdf"
												target="_blank">
												Download you AOF
											</a>
									</c:if>
								</p>
									
									<div style="text-align: center;">
										<button type="button" class="btn #59698d mdb-color lighten-1 white-text btn-sm" style="font-size: 12px;" onclick="getmodal('sign1')">Applicant 1</button>
										<button type="button" class="btn #59698d mdb-color lighten-1 white-text btn-sm" style="font-size: 12px;" onclick="getmodal('sign2')">Applicant 2</button>
									</div>
									
								<!-- Signature Modal - Applicant-->
									<jsp:include page="bsemf/bse-mf-signature-panel.jsp"></jsp:include>

								<hr>
								
								<div class="row" >
									<div class="col-md-12 col-lg-12">
									<div style="font-size: 11px; color: #c02fc1;">
									<strong>You can also upload signed form in .pdf format</strong>
								</div>
										<form:form method="POST" enctype="multipart/form-data"
											action="/products/uploadaoffile.do" commandName="fileform">
											<div style="padding-left: 5px; margin-bottom: .1rem;">
												<span style="color: red; font: 12px;">${message}</span>
												<form:input type="file" path="file" accept=".pdf"
													plaeholder="Upload signed AOF"
													style="border: 1px solid #bcc2c7; border-radius: 2px;" />
											</div>
											<form:hidden path="fileowner" />
											<form:button type="submit" class="btn btn-sm btn-info">Submit</form:button>
											
										</form:form>

									</div>
								</div>
								<hr>

								<c:choose>
									<c:when test="${FILE_UPLOAD == 'S'}">
										<div id="aofuploadbutton">
											<button class="btn btn-sm btn-primary" id="aofuploadbtn"
												onclick="initiateAOFUpload(<%=session.getAttribute("userid").toString()%>);">
												UPLOAD YOUR AOF <i class="fas fa-upload"></i>
											</button>
										</div>
									</c:when>
									<c:otherwise>
										<div id="aofuploadbutton">
											<button class="btn btn-sm btn-primary" id="aofuploadbtn"
												hidden="hidden"
												onclick="initiateAOFUpload(<%=session.getAttribute("userid").toString()%>);">
												<span id="uploadtxt">UPLOAD YOUR AOF <i
													class="fas fa-upload"></i></span> <span id="uploadingtxt"
													style="display: none;">Uploading... <i
													class="fas fa-spinner fa-spin"></i></span>
											</button>
										</div>
									</c:otherwise>
								</c:choose>

								<!-- End of modal  -->
								

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
											<caption>FSecure Purchase History</caption>
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

						<%-- <%int counter =1;%> --%>

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
	<script src="<c:url value="${contextcdn}/resources/js/signaturepanel.js" />"></script>
</body>

</html>