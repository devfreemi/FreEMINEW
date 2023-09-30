<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<section class="mf-account-details">
				<c:choose>
					<c:when test="INV_PROF =='F'">
						<c:if test="not empty errorinv"> 
							<div class="col-md-12 col-lg-12 alert alert-danger" role="alert"
								style="text-align: center;">
								<span>${errorinv }</span>
							</div>
							 </c:if>
					</c:when>
					<c:when test="${INV_PROF =='Y' }">
					
						<div class="row gutters-sm">
							<div class="col-md-6 pb-md-0 pb-3">
								<div class="card">
									<div class="card-body side">
										<h4 class="fw-bold pb-4 text-center">Investor
											Details</h4>
					
										<!-- <div class="row">
													<div class="col-6 ">
														<label>Investor Name</label>
													</div>
													<div class="col-6 ">${profileBasic.fname }</div>
												</div> -->
										<div class="row">
											<div class="col-6 ">
												<label>PAN No.</label>
											</div>
											<div class="col-6 ">${profileBasic.pan1 }</div>
										</div>
										<div class="row">
											<div class="col-6 ">
												<label>PAN validity</label>
											</div>
											<div class="col-6 ">
												<c:choose>
													<c:when test="${profileBasic.pan1verified == 'Y'}">
														<span class="fw-bold text-success">VERIFIED</span>
													</c:when>
													<c:when test="${profileBasic.pan1verified == 'N'}">
														<span class="fw-bold text-danger">NOT VERIFIED</span>
													</c:when>
													<c:otherwise>
														<span class="text-muted">Unknown</span>
													</c:otherwise>
												</c:choose>
					
											</div>
										</div>
										<div class="row">
											<div class="col-6 ">
												<label>KYC Status</label>
											</div>
											<div class="col-6">
												<c:choose>
													<c:when test="${profileBasic.pan1Verifiedkyc == 'Y'}">
														<span class="fw-bold text-success">VERIFIED</span>
													</c:when>
													<c:when test="${profileBasic.pan1Verifiedkyc == 'N'}">
														<span class="fw-bold text-danger">NOT VERIFIED</span>
													</c:when>
													<c:otherwise>
														<span class="text-muted">Unknown</span>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="row">
											<div class="col-6 ">
												<label>Tax Status</label>
											</div>
											<div class="col-6 ">${profileBasic.taxStatus }</div>
										</div>
					
										<div class="row">
											<div class="col-6 ">
												<label>Mode of Holding</label>
											</div>
											<div class="col-6 ">${profileBasic.holdingMode }</div>
										</div>
										<div class="row">
											<div class="col-6 ">
												<label>Date of Birth</label>
											</div>
											<div class="col-6 ">${profileBasic.dob }</div>
										</div>
					
									</div>
								</div>
							</div>
					
					
							<!-- Communication address  -->
							<div class="col-md-6 ">
								<div class="card">
									<div class=" card-body side">
										<h4 class="fw-bold pb-4 text-center">Communication
											Details</h4>
					
										<div class="row">
											<div class="col-5 ">
												<label>Email ID</label>
											</div>
											<div class="col-7 ">
												<span style="overflow: auto;">${profileBasic.mail }</span>
											</div>
										</div>
										<div class="row">
											<div class="col-5 ">
												<label>Mobile No.</label>
											</div>
											<div class="col-7 ">${profileBasic.mobile }</div>
										</div>
					
										<div class="row">
											<div class="col-5 ">
												<label>Address 1</label>
											</div>
											<div class="col-7">${profileBasic.address1 }</div>
										</div>
										<div class="row">
											<div class="col-5 ">
												<label>Address 2</label>
											</div>
											<div class="col-7 ">${profileBasic.address2 }</div>
										</div>
					
										<div class="row">
											<div class="col-5 ">
												<label>City/State</label>
											</div>
											<div class="col-7 ">${profileBasic.city },
												${profileBasic.state }</div>
										</div>
										<div class="row">
											<div class="col-5 ">
												<label>ZIP Code</label>
											</div>
											<div class="col-7 ">${profileBasic.pincode }</div>
										</div>
					
					
									</div>
								</div>
					
							</div>
						</div>
						<!-- JOINT HOLDER AND NOMINEE -->
						<section class="basc_details mt-3 mt-md-4">
							<div class="row">
								<!-- Joint Holder Details -->
								<div class="col-md-6 col-lg-6 pb-md-0 pb-3">
									<div class="card shadow">
										<div class="card-body side">
											<h4 class="fw-bold pb-4 text-center">Joint
												Holder Details
											</h4>
											<c:choose>
												<c:when test="${profileBasic.holdingMode == 'Single'}">
													<div class="text-center text-muted pb-3">
														<span class="f-11">No Joint Holder Details
															Available</span>
													</div>
												</c:when>
					
												<c:otherwise>
													<div class="row">
														<div class="col-6 ">
															<label>Investor Name</label>
														</div>
														<div class="col-6 ">${profileBasic.fname }</div>
													</div>
													<div class="row">
														<div class="col-6 ">
															<label>PAN No.</label>
														</div>
														<div class="col-6 ">${profileBasic.pan1 }</div>
													</div>
													
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
					
					
								<!-- Nominee Details -->
								<div class="col-md-6 col-lg-6">
									<div class="card shadow">
										<div class="card-body side">
											<h4 class="fw-bold pb-4 text-center">Nominee
												Details</h4>
					
											<c:choose>
												<c:when test="${profileBasic.nomineeAvailable == 'Y' }">
					
													<div class="row">
														<div class="col-6 ">
															<label>Nominee Name</label>
														</div>
														<div class="col-6 ">${profileBasic.nomineeName }</div>
													</div>
													<div class="row">
														<div class="col-6 ">
															<label>Relation</label>
														</div>
														<div class="col-6 ">${profileBasic.nomineeRelation }</div>
													</div>
													<div class="row">
													<div class="col-6 mx-auto">
                                                       <a href="${contextPath}/products/nominee-registration/mutual-funds">Nominee verification/registration</a>
                                                    </div>
													</div>
					
												</c:when>
												<c:otherwise>
													<div class="text-center text-muted pt-3">
														<span class="f-11">No Nominee Available</span>
													</div>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
						</section>
						<!-- JONT HOLDER AND NOMINEE SECTION END -->
					
						<!-- BANK DETAILS  -->
						<section class="basc_details mt-md-3 mb-4 mt-3">
							<div class="row mx-auto">
								<div class="col-md-12 col-lg-12 px-0 ">
									<div class="card shadow">
										<div class="card-body side">
											<h4 class="fw-bold pb-md-3 pb-2 text-center">Bank
												Details</h4>
					
											<div class="row">
												<div class="col-md-3 col-lg-3 col-12">
													<div class="row justify-content-center">
														<div class="col-md-12 col-lg-12 col-4">
															<label>Bank Name</label>
														</div>
														<div class="col-md-12 col-lg-12 col-8">
															<span>${profileBasic.bankName }</span>
														</div>
													</div>
												</div>
												<div class="col-md-3 col-lg-3 col-12">
													<div class="row">
														<div class="col-md-12 col-lg-12 col-4">
															<label>Account No.</label>
														</div>
														<div class="col-md-12 col-lg-12 col-8">
															<span>${profileBasic.accountNumber }</span>
														</div>
													</div>
												</div>
					
												<div class="col-md-3 col-lg-3 col-12">
													<div class="row">
														<div class="col-md-12 col-lg-12 col-4">
															<label>IFSC Code</label>
														</div>
														<div class="col-md-12 col-lg-12 col-8">
															<span>${profileBasic.ifscCode }</span>
														</div>
													</div>
												</div>
					
												<div class="col-md-3 col-lg-3 ">
													<div class="row">
														<div class="col-md-12 col-lg-12 col-4">
															<label>Type</label>
														</div>
														<div class="col-md-12 col-lg-12 col-8">
															<span>${profileBasic.accountType }</span>
														</div>
													</div>
												</div>
												<div class="col-12 mx-auto mt-md-3 mt-2">
													<div class="text-center">
														<a href="${pageContext.request.contextPath}/e-mandates">
															<button class="btn btn-sm btn-success" id="mandatelink">
																Check E-mandates
															</button></a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</section>
					
					
					</c:when>


					<c:when test="INV_PROF =='N'"> 

								<h4 class="fw-bold pt-4 pb-2 text-center f-16">You have not yet setup your investment
									Profile</h4>
								<div class="d-flex flex-column align-items-center text-center pb-5">
									<a href="/products" class="btn blue-gradient white-text">Start
										investing
									</a>
								</div>
					 </c:when>
				</c:choose>
			</section>