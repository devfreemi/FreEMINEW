<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="col-md-4 col-lg-4">
		<div class="profile-status-left">
			<h5 style="background: #dbdee6; padding: 3px;">Mutual Fund
				Profile Status</h5>
			<div>
				<span id="signuploadstatus" style="font-size: 11px;"></span>
			</div>
			<hr>
			<c:choose>

				<c:when test="${PROFILE_STATUS == 'NOT_FOUND' }">
					<h4 style="font-family: none; color: #e6643c;">Register for
						investment</h4>
					<div class="container">
						<ul class="progressbar" style="padding-inline-start: 0px;">
							<li><small>Registration</small></li>
							<li><small>FATCA</small></li>
							<li><small>AOF Upload</small></li>
						</ul>
					</div>
					<a href="/products/mutual-funds/register?mf=01"> Create Mutual
						Fund Account </a>
				</c:when>

				<c:when test="${PROFILE_STATUS == 'REGISTRATION_INCOMPLETE' }">
					<div class="container">
						<ul class="progressbar" style="padding-inline-start: 0px;">
							<li><small>Registration</small></li>
							<li><small>FATCA</small></li>
							<li><small>AOF Upload</small></li>
						</ul>
					</div>

					<div class="text-center">
						<a href="/products/mutual-funds/register?mf=04">
							<button class="btn btn-sm btn-secondary">Complete
								Registration</button>
						</a>
					</div>

				</c:when>

				<c:when test="${PROFILE_STATUS == 'PROFILE_READY' }">
					<div class="text-center mb-2">
						<h5 style="color: #408ad8;">Investment profile ready.</h5>
					</div>
					<div class="container">
						<ul class="progressbar" style="padding-inline-start: 0px;">
							<li class="active"><small>Registration</small></li>
							<li class="active"><small>FATCA</small></li>
							<li class="active"><small>AOF Upload</small></li>
						</ul>
					</div>
					<div class="mb-2 custom-line-height">
						<small class="text-danger"><sup>*</sup>Account is
							registered. KYC must be completed for investing in mutual fund.</small>
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
				<c:when test="${PROFILE_STATUS == 'FATCA_AOF_PENDING' }">
					<div class="container">
						<ul class="progressbar" style="padding-inline-start: 0px;">
							<li class="active"><small>Registration</small></li>
							<li><small>FATCA</small></li>
							<li><small>AOF Upload</small></li>
						</ul>
					</div>

				</c:when>
				<c:when test="${PROFILE_STATUS == 'AOF_PENDING' }">
					<div class="container">
						<ul class="progressbar" style="padding-inline-start: 0px;">
							<li class="active"><small>Registration</small></li>
							<li class="active"><small>FATCA</small></li>
							<li><small>AOF Upload</small></li>
						</ul>
					</div>

					<div class="text-center">
						<button class="btn btn-sm btn-secondary" id="retryaofupload"
							onclick="retryaofupload(<%=session.getAttribute("userid").toString()%>)">Retry
							AOF Upload</button>
					</div>

				</c:when>

				<c:when test="${PROFILE_STATUS == 'ERROR' }">
					<p>Failed to fetch your profile status!</p>
				</c:when>

				<c:otherwise>
					<p>Failed to check status currently.</p>
				</c:otherwise>

			</c:choose>
			<div class="text-center">
				<a href="${pageContext.request.contextPath}/e-mandates">
				<button class="btn btn-sm btn-success" id="mandatelink">
					E-mandates
				</button></a>
			</div>
		</div>

	</div>
</div>