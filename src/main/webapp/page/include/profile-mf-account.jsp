<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="mf-account-details">
	<c:choose>
		<c:when test="${INV_PROF =='F' }">
			<c:if test="${not empty errorinv }">
				<div class="col-md-12 col-lg-12 alert alert-danger" role="alert"
					style="text-align: center;">
					<span>${errorinv}</span>
				</div>
			</c:if>
		</c:when>
		<c:when test="${INV_PROF =='Y' }">

			<section class="basc_details" style="margin-top: 2rem;">
				<div style="text-align: center; margin-bottom: 1rem;">
					<h5 style="font-weight: 500; color: #13c2a0;">Investment
						Profile</h5>
				</div>
				<div class="row" style="margin: auto;">
					<div class="col-md-6 col-lg-6"
						style="padding: 10px; margin-bottom: 2rem;">

						<div class="leftside" style="border: 1px solid #e4e4e4;">
							<div style="text-align: center;">
								<img
									src="<c:url value="${contextPath}/resources/images/invest/investor-3.png"/>"
									class="img-fluid rounded-circle border"
									style="height: 3rem; margin-top: -25px; box-shadow: 0 0 2px 1px #cac0c0;"
									alt="Edit">
							</div>

							<h4
								style="font-weight: 500; text-align: center; margin: 1rem 5px;">Investor
								Details</h4>

							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>Investor Name</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.fname }</div>
							</div>
							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>PAN No.</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.pan1 }</div>
							</div>
							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>PAN validity</label>
								</div>
								<div class="col-6 borderstyle-1">
									<c:choose>
										<c:when test="${profileBasic.pan1verified == 'Y'}">
											<span style="color: green;">VERIFIED</span>
										</c:when>
										<c:when test="${profileBasic.pan1verified == 'N'}">
											<span style="color: red;">NOT VERIFIED</span>
										</c:when>
										<c:otherwise>
											<span style="color: #88817d;">Unknown</span>
										</c:otherwise>
									</c:choose>

								</div>
							</div>
							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>KYC Status</label>
								</div>
								<div class="col-6">
									<c:choose>
										<c:when test="${profileBasic.pan1Verifiedkyc == 'Y'}">
											<span style="color: green;">VERIFIED</span>
										</c:when>
										<c:when test="${profileBasic.pan1Verifiedkyc == 'N'}">
											<span style="color: red;">NOT VERIFIED</span>
										</c:when>
										<c:otherwise>
											<span style="color: #88817d;">Unknown</span>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>Tax Status</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.taxStatus }</div>
							</div>

							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>Mode of Holding</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.holdingMode }</div>
							</div>
							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>Date of Birth</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.dob }</div>
							</div>

						</div>
					</div>


					<!-- Communication address  -->
					<div class="col-md-6 col-lg-6" style="padding: 10px;">
						<div class="rightside" style="border: 1px solid #e4e4e4;">
							<div style="text-align: center;">
								<img
									src="<c:url value="${contextPath}/resources/images/invest/communication-2.png"/>"
									class="img-fluid rounded-circle border"
									style="height: 3rem; margin-top: -25px; box-shadow: 0 0 2px 1px #cac0c0;"
									alt="Edit">
							</div>
							<h4
								style="font-weight: 500; text-align: center; margin: 1rem 5px;">Communication
								Details</h4>

							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>Email ID</label>
								</div>
								<div class="col-6 borderstyle-1">
									<span style="overflow: auto;">${profileBasic.mail }</span>
								</div>
							</div>
							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>Mobile No.</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.mobile }</div>
							</div>

							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>Address 1</label>
								</div>
								<div class="col-6">${profileBasic.address1 }</div>
							</div>
							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>Address 2</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.address2 }</div>
							</div>

							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>City/State</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.city },
									${profileBasic.state }</div>
							</div>
							<div class="row">
								<div class="col-6 borderstyle-1">
									<label>ZIP Code</label>
								</div>
								<div class="col-6 borderstyle-1">${profileBasic.pincode }</div>
							</div>


						</div>

					</div>
				</div>


			</section>

			<section class="basc_details" style="margin-top: 2rem;">

				<div class="row" style="margin: auto;">

					<!-- Joint Holder Details -->
					<div class="col-md-6 col-lg-6"
						style="padding: 10px; margin-bottom: 2rem;">

						<div class="leftside" style="border: 1px solid #e4e4e4;">
							<div style="text-align: center;">
								<img
									src="<c:url value="${contextPath}/resources/images/invest/joint-holder.png"/>"
									class="img-fluid rounded-circle border"
									style="height: 3rem; margin-top: -25px; box-shadow: 0 0 2px 1px #cac0c0;"
									alt="Edit">
							</div>

							<h4
								style="font-weight: 500; text-align: center; margin: 1rem 5px;">Joint
								Holder Details</h4>

							<c:choose>
								<c:when test="${profileBasic.holdingMode == 'Single'}">
									<div style="text-align: center; margin-top: 2rem;">
										<span style="font-size: 12px;">No Joint Holder Details
											Available</span>
									</div>
								</c:when>

								<c:otherwise>
									<div class="row">
										<div class="col-6 borderstyle-1">
											<label>Investor Name</label>
										</div>
										<div class="col-6 borderstyle-1">${profileBasic.fname }</div>
									</div>
									<div class="row">
										<div class="col-6 borderstyle-1">
											<label>PAN No.</label>
										</div>
										<div class="col-6 borderstyle-1">${profileBasic.pan1 }</div>
									</div>
								</c:otherwise>
							</c:choose>




						</div>
					</div>


					<!-- Nominee Details -->
					<div class="col-md-6 col-lg-6" style="padding: 10px;">
						<div class="rightside" style="border: 1px solid #e4e4e4;">
							<div style="text-align: center;">
								<img
									src="<c:url value="${contextPath}/resources/images/invest/nominee1.png"/>"
									class="img-fluid rounded-circle border"
									style="height: 3rem; margin-top: -25px; box-shadow: 0 0 2px 1px #cac0c0;"
									alt="Edit">
							</div>
							<h4
								style="font-weight: 500; text-align: center; margin: 1rem 5px;">Nominee
								Details</h4>

							<c:choose>
								<c:when test="${profileBasic.nomineeAvailable == 'Y' }">

									<div class="row">
										<div class="col-6 borderstyle-1">
											<label>Nominee Name</label>
										</div>
										<div class="col-6 borderstyle-1">${profileBasic.nomineeName }</div>
									</div>
									<div class="row">
										<div class="col-6 borderstyle-1">
											<label>Relation</label>
										</div>
										<div class="col-6 borderstyle-1">${profileBasic.nomineeRelation }</div>
									</div>


								</c:when>
								<c:otherwise>
									<div style="text-align: center; margin-top: 2rem;">
										<span style="font-size: 12px;">No Nominee Available</span>
									</div>
								</c:otherwise>
							</c:choose>
						</div>

					</div>
				</div>


			</section>

			<!-- BANK DETAILS  -->
			<section class="basc_details" style="margin-top: 2rem;">
				<div class="row" style="margin: auto;">
					<div class="col-md-12 col-lg-12"
						style="padding: 10px; margin-bottom: 2rem;">

						<div class="leftside" style="border: 1px solid #e4e4e4;">
							<div style="text-align: center;">
								<img
									src="<c:url value="${contextPath}/resources/images/invest/bank-2.png"/>"
									class="img-fluid rounded-circle border"
									style="height: 3rem; margin-top: -25px; box-shadow: 0 0 2px 1px #cac0c0;"
									alt="Edit">
							</div>

							<h4
								style="font-weight: 500; text-align: center; margin: 1rem 5px;">Bank
								Details</h4>

							<div class="row">
								<div class="col-md-3 col-lg-3 borderstyle-1">
									<div class="prof-bank-details">
										<label>Bank Name</label>
									</div>
									<div>
										<span>${profileBasic.bankName }</span>
									</div>

								</div>
								<div class="col-md-3 col-lg-3 borderstyle-1">
									<div class="prof-bank-details">
										<label>Account No.</label>
									</div>
									<div>
										<span>${profileBasic.accountNumber }</span>
									</div>

								</div>

								<div class="col-md-3 col-lg-3 borderstyle-1">
									<div class="prof-bank-details">
										<label>IFSC Code</label>
									</div>
									<div>
										<span>${profileBasic.ifscCode }</span>
									</div>

								</div>

								<div class="col-md-3 col-lg-3 borderstyle-1">
									<div class="prof-bank-details">
										<label>Type</label>
									</div>
									<div>
										<span>${profileBasic.accountType }</span>
									</div>

								</div>

							</div>

						</div>
					</div>
				</div>

			</section>


		</c:when>


		<c:when test="${INV_PROF =='N' }">

			<h4>You have not yet setup your investment Profile</h4>
			<a href="/products"><button class="btn blue-gradient white-text">Start
					investing</button></a>
		</c:when>

	</c:choose>
</section>