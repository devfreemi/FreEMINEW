<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

						<h5 class="font-weight-bold text-center mb-3 tab-head px-3">Mutual Fund
							Profile Status</h5>
						<div>
							<span id="signuploadstatus" style="font-size: 11px;"></span>
						</div>
						<c:choose>

							<c:when test="${PROFILE_STATUS == 'NOT_FOUND' }">
								<p style="font-family: none; color: #e6643c;">Register for
									investment</p>
								<div class="container px-0 text-center">
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
								<div class="container px-0 text-center">
									<ul class="progressbar" style="padding-inline-start: 0px;">
										<li><small>Registration</small></li>
										<li><small>FATCA</small></li>
										<li><small>AOF Upload</small></li>
									</ul>
								</div>

								<div class="text-center">
									<a href="/products/mutual-funds/register?mf=04">
										<button class="btn btn-danger">Complete
											Registration</button>
									</a>
								</div>

							</c:when>

							<c:when test="${PROFILE_STATUS == 'PROFILE_READY' }">
								<div class="text-center mb-2">
									<p class="text-warning">Investment profile ready.</p>
								</div>
								<div class="container px-0 text-center">
									<ul class="progressbar" style="padding-inline-start: 0px;">
										<li class="active"><small>Registration</small></li>
										<li class="active"><small>FATCA</small></li>
										<li class="active"><small>AOF Upload</small></li>
									</ul>
								</div>
								<div class="mb-2 px-2 text-center">
									<small class="text-danger"><sup>*</sup>Account is
										registered. KYC must be completed for investing in mutual fund.</small>
								</div>
								<div style="text-align: center;">
									<!-- <p>Mode of Holding: </p> -->

									<div style="margin-bottom: 20px;">
										<button class="btn btn-info" id="pendinglink"
											onclick="completePendingPayments()">
											Complete Pending payments &nbsp; <i class="far fa-credit-card text-white"></i>
										</button>
									</div>
								</div>
							</c:when>
							<c:when test="${PROFILE_STATUS == 'FATCA_AOF_PENDING' }">
								<div class="container px-0 text-center">
									<ul class="progressbar" style="padding-inline-start: 0px;">
										<li class="active"><small>Registration</small></li>
										<li><small>FATCA</small></li>
										<li><small>AOF Upload</small></li>
									</ul>
								</div>

							</c:when>
							<c:when test="${PROFILE_STATUS == 'AOF_PENDING' }">
								<div class="container px-0 text-center">
									<ul class="progressbar" style="padding-inline-start: 0px;">
										<li class="active"><small>Registration</small></li>
										<li class="active"><small>FATCA</small></li>
										<li><small>AOF Upload</small></li>
									</ul>
								</div>

								<div class="text-center">
									<button class="btn btn-primary" id="retryaofupload"
										onclick="retryaofupload(<%=session.getAttribute(" userid").toString()%>)">Retry
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
