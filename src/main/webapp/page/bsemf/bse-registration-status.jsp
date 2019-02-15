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

		<section style="padding: 20px 0px; font-family: lato light;">
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


						<div class="progress">
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

						<h4 style="font-size: 20px; color: #da7437;">What are the
							next steps?</h4>
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="aofoption">
									<h5>Option 1</h5>
									<ul style="list-style: none;">
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
																src="https://s3.ap-south-1.amazonaws.com/freemi-product/files/pdf/file1.pdf"
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

										<li>3. Upload the signed form</li>
									</ul>

									<c:if test="${not empty investForm.pan1}">
										<div style="margin-top: 20px; text-align: center;">
											<input type="hidden" id="mobdata" value="${investForm.mobile}">
											<a href="/products/download/aof/${investForm.pan1}.pdf"
												target="_blank">
												<button type="button" class="btn btn-sm btn-info">
													<i class="fas fa-download"></i> Download your form
												</button>
											</a>
										</div>
									</c:if>
								</div>

							</div>
							<div class="col-md-4 col-lg-4">
								<div class="aofoption">
									<h5>Option 2</h5>
									<ul style="list-style: none;">
										<li>1. Sign using platform panel</li>
										<li>2. Verify and submit the signed form</li>
									</ul>
									<div style="margin-top: 20px;">

										<div style="margin-top: 5px; text-align: center;">
											<!-- Button trigger modal -->

											<img
												src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
												class="img-fluid" style="height: 48px;"
												alt="Signature panel">
											<button type="button" class="btn btn-outline-primary btn-sm"
												style="font-size: 12px;" data-toggle="modal"
												data-target="#exampleModal1">Applicant 1</button>
											<button type="button" class="btn btn-outline-primary btn-sm"
												style="font-size: 12px;" data-toggle="modal"
												data-target="#exampleModal2">Applicant 2</button>

										</div>

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
																alt="Signature panel"> Signature Panel
														</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body" style="text-align: center;">
														<div class="row">
															<div class="col-md-12">
																<canvas id="sig-canvas">
		 			Browser not supported!
		 		</canvas>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<button class="btn btn-sm btn-info" id="sig-submitBtn">Submit
																	Signature</button>
																<!-- <button class="btn" id="btndownload">Download</button> -->
																<button class="btn btn-sm btn-secondary"
																	id="sig-clearBtn">Clear Signature</button>
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

									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="" style="text-align: center; margin-top: 20px;">
						<!-- <button type="button" class="btn btn-info">AOF Ready for upload</button> -->
						<%-- <jsp:include page="aof-form-generation.jsp"></jsp:include>	 --%>
						<span id="signuploadstatus"></span>
						<div>
							<button class="btn btn-sm btn-primary" id="aofuploadbtn"
								hidden="hidden" onclick="initiateAOFUpload();">UPLOAD YOUR AOF</button>
						</div>

					</div>
				</div>
			</div>
			<div></div>
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
					<button class="btn btn-sm btn-outline-secondary" type="button"
						style="margin-top: 20px;" >UPLOAD YOUR SIGNED FILE</button>
				</div>
			</div>

		</section>
	</div>
	<jsp:include page="../include/footer.jsp"></jsp:include>

	<script>
		(function() {
			window.requestAnimFrame = (function(callback) {
				return window.requestAnimationFrame
						|| window.webkitRequestAnimationFrame
						|| window.mozRequestAnimationFrame
						|| window.oRequestAnimationFrame
						|| window.msRequestAnimaitonFrame
						|| function(callback) {
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
				e.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0,
						false, false, false, false, 0, null);

				lnk.dispatchEvent(e);
			} else if (lnk.fireEvent) {
				lnk.fireEvent("onclick");
			}
		}

		/* $("button").click(function(){ */
		function submitSign() {

			/* console.log("signature- "+ $("#sig-dataUrl").text()); */
			$
					.post(
							"/products/mutual-funds/uploadsign",
							/* $.post("/products/api/saveloanquery", */

							{
								sign1 : $("#sig-dataUrl").text(),
								sign2 : ""
							},
							function(data, status) {
								/* alert("Data: " + data + "\nStatus: " + status); */
								console.log(data);
								$('#exampleModal1').modal('hide');
								if (data == 'SUCCESS') {
									
									$("#signuploadstatus")
											.text(
													"Your AOF form is signed and ready for upload.");
									$("#aofuploadbtn").removeAttr('hidden');
									move(75);
									
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
												"Failed to submit your signature. Please try again.");
							});
		}
		
					function initiateAOFUpload() {

						 console.log("signature- "+ $("#mobdata").val());
						$
								.get(
										"/products/mutual-funds/uploadsignedaof",
										/* $.post("/products/api/saveloanquery", */

										{
											mobile : $("#mobdata").val()
										},
										function(data, status) {
											/* alert("Data: " + data + "\nStatus: " + status); */
											console.log(data);
											if (data == 'SUCCESS') {
												
												$("#signuploadstatus")
														.text(
																"Your AOF uploaded successfully. Registration process complete.");
												$("#aofuploadbtn").removeAttr('hidden');
												move(100);
												
											}
											if (data == 'FAIL') {
												$('#exampleModal1').modal('hide');
												$("#signuploadstatus")
														.text(
																"Failed to upload your AOF. Kindly contact Admin.");
												
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
			      $("#statusp").text('Invest profile status '+ width + '%');
			    }
			  }
			}
	</script>
</body>
</html>