<div class="modal fade" id="mobileverifymodal" tabindex="-1" role="dialog"
	aria-labelledby="mobileverifymodalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="mobileverifymodalLabel" style="font-weight: 500; color: #343739;">Verify personal details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row mb-3 mx-auto">
				<small class="text-danger" id="mobileverifymsg"></small>
				</div>
				<form class="text-center" action="#!" id="mobileverifyform">
					<div class="form-row mb-3">
						<div class="col-md-12 col-lg-12">
							<div class="md-form md-outline form-sm mt-0">
								<input type="text" class="form-control" maxlength="48"
									id="verifykey" name="key" required="required" readonly="readonly" />
								<label for="verifykey"><span id="verifytext">10-digit mobile no</span> <sup class="text-danger">*</sup>
								</label>
							</div>
						</div>

						<div class="col-md-12 col-lg-12 d-none" id="motpdiv">
							<div class="md-form md-outline form-sm mt-0">
								<input type="tel" class="form-control" maxlength="6"
									id="mobileotpdata" name="otp" />
								<label for="mname">Verification code</label>
							</div>
						</div>
						<div>
						<input type="hidden" id="verifytype" value="M">
						</div>
					</div>
					<div class="form-row">
						<button class="btn btn-info btn-rounded my-4 waves-effect z-depth-0 mx-auto" type="button" id="msendotpbutton">Send OTP</button>
						<button class="btn btn-info btn-rounded my-4 waves-effect z-depth-0 mx-auto" type="submit" id="mverifybutton">Verify Code</button>		
					</div>

				</form>
			</div>
			<!-- <div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div> -->
		</div>
	</div>
</div>