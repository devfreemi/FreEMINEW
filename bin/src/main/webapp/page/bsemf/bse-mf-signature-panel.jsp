<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel1" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header"
				style="background-color: #f57c45; color: white;">
				<h5 class="modal-title" id="exampleModalLabel">
					<img
						src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
						class="img-fluid" style="height: 48px;" alt="Signature panel">
					E-Signature Panel
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="text-align: center;">
				<div class="row">
					<span id="signmsg" style="color: red;"></span>
				</div>
				<div class="row">
					<div class="col-md-12">
						<canvas id="sig-canvas" style="border: 1px solid #969696; cursor: crosshair;box-shadow: 0 0 6px 0px #b7b3b3;">
		 			Browser not supported!
		 		</canvas>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<button class="btn btn-sm btn-info" id="set-signature">
							<span id="signtxtset">Set Signature</span>
						</button>
						<!-- <button class="btn" id="btndownload">Download</button> -->
						<button class="btn btn-sm btn-secondary" id="sig-clearBtn">Clear
							Signature</button>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-12">
						<textarea hidden="hidden" id="sig-dataUrl" class="form-control"
							rows="5" style="max-height: 100px; overflow: scroll;"
							readonly="readonly">NA</textarea>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-12">
						<!-- <img id="sig-image" src="" alt="Signature-image" /> -->
						<figure>
							<img
								src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
								class="img-fluid" id="sig-image" alt="Signature Image">
							<figcaption style="color: #a09b93; font-size: 11px;">
								Signature : <span id="signid"></span>
							</figcaption>
						</figure>

						<div style="text-align: center;">
							<button class="btn btn-sm #00695c teal darken-2 white-text"
								id="sig-submitBtn" onclick="submitSign();">
								<span id="signtxt">Upload Signature</span> <span id="signingtxt"
									style="display: none;">Submitting Signature <i
									class="fas fa-spinner fa-spin"></i>
								</span>
							</button>
							<p>Upload signature when complete</p>
						</div>
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