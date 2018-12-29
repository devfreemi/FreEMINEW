<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>BSE registration status</title>
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

</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	<div class="container-fluid">
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

		<section style="padding: 20px 0px;font-family:lato light;">
			<div class="row" style="margin: auto;    border: 1px solid #c1bdb8; padding: 20px 0px;">
				<div class="col-md-10 col-lg-10"
					style="margin: auto; text-align: center;">
					<h1>Congrats. Your application complete</h1>
					<div>
						<p>
							<i class="fas fa-check" style="color: #3fc53f;"></i> You have
							successfully submitted your investment form
						</p>
					</div>
					<div>
						<h4 style="font-size: 20px;color: #da7437;">What are the next steps?</h4>
						<div class="row">
							<div class="col-md-4 col-lg-4"><strong>1. Download and print the
								form</strong></div>
							<div class="col-md-4 col-lg-4">
								<strong>2. Sign the form</strong> <br> Where to sign?
								<div style="margin-top: 5px;">
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-outline-primary btn-sm" style="font-size: 10px;"
									data-toggle="modal" data-target="#exampleModal">
									See Sample file</button>

								<!-- Modal -->
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
											<embed src="https://s3.ap-south-1.amazonaws.com/freemi-product/files/pdf/file1.pdf" frameborder="0" width="100%" height="400px">
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
								</div>
								
							</div>
							<div class="col-md-4 col-lg-4"><strong>3. Upload the signed form</strong></div>
						</div>
					</div>
					<div style="margin-top: 20px;">
						<a href="/products/download/file1.pdf">
							<button type="button" class="btn btn-info">
								<i class="fas fa-download"></i> Download your form
							</button>
						</a>
					</div>
					<!-- <button type="button" class="btn btn-info">Print</button> -->
				</div>
			</div>
		</section>

		<section style="background-color: aliceblue; padding: 30px 0px;">
			<div class="row" style="margin: auto; margin-top: 4rem;">
				<div class="col-md-8 col-lg-8"
					style="margin: auto; text-align: center;">
					<h3>Ready to upload?</h3>
					<div class="input-group">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="inputGroupFile04"> <label class="custom-file-label"
								for="inputGroupFile04">Choose file</label>
						</div>
					</div>
					<button class="btn btn-outline-secondary" type="button">UPLOAD
						YOUR SIGNED FILE</button>
				</div>
			</div>

		</section>
	</div>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>