<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="col-md-4 col-lg-4">
		<div class="profile-status-left">
			<h5 style="background: #dbdee6; padding: 3px;">Mutual Fund Profile Status</h5>

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
						<button class="btn btn-sm btn-secondary">Get Registered</button>
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
					<div class="progress" style="font-size: 10px; margin-bottom: 20px;">
						<div class="progress-bar bg-success" role="progressbar"
							style="width: 100%" aria-valuenow="100" aria-valuemin="0"
							aria-valuemax="100">Registration Complete</div>

					</div>
					<div style="text-align: center;">
						<!-- <p>Mode of Holding: </p> -->

						<div style="margin-bottom: 20px;">
							<button class="btn btn-sm btn-info" id="pendinglink"
								style="font-size: 12px;" onclick="completePendingPayments()">
								Complete Pending payments <img
									src="<c:url value="${contextcdn}/resources/images/invest/payment21.svg"/>"
									class="img-fluid" style="height: 1rem;">
							</button>
						</div>
					</div>
				</c:when>

				<c:when test="${PROFILE_STATUS == 'AOF_PENDING' }">
					<strong style="color: #346be6;">Sign &amp; Upload your AOF
						Form</strong>
					<div class="progress" style="font-size: 10px;">
						<div class="progress-bar bg-success" role="progressbar" id="myBar"
							style="width: 50%" aria-valuenow="50" aria-valuemin="0"
							aria-valuemax="100">
							<span id="statusp">Details Registered</span>
						</div>
					</div>
					<p>
						You need to sign and upload your investment form before you can
						start investing.
						<c:if test="${not empty pan}">
							<input type="hidden" id="mobdata" value="${investForm.mobile}">
							<a href="/products/download/aof/${pan}.pdf" target="_blank">
								Download you AOF </a>
						</c:if>
					</p>

					<div style="text-align: center;">
						<button type="button"
							class="btn #59698d mdb-color lighten-1 white-text btn-sm"
							style="font-size: 12px;" onclick="getmodal('sign1')">Applicant
							1</button>
						<button type="button"
							class="btn #59698d mdb-color lighten-1 white-text btn-sm"
							style="font-size: 12px;" onclick="getmodal('sign2')">Applicant
							2</button>
					</div>

					<!-- Signature Modal - Applicant-->
					<jsp:include page="../bsemf/bse-mf-signature-panel.jsp"></jsp:include>

					<hr>

					<div class="row">
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