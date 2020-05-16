<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="mahindra-account-display">
	<h4>Mahindra FD KYC Documents</h4>
	<div class="row mx-auto">
		<button type="button"
			class="btn btn-sm btn-default"  id="viewmfdkycdoc">View
			Documents</button>
		<button type="button"
			class="btn btn-sm blue-gradient white-text waves-effect waves-light"
			data-toggle="modal" data-target="#mfdkycdoc">update
			Documents</button>
	</div>
	<div class="row mx-auto" id="kycdocdata"></div>
	
	
	<!-- Modal -->
		<div class="modal fade" id="mfdkycdoc" tabindex="-1" role="dialog"
			aria-labelledby="kycupdatedocModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="kycupdatedocModalLabel">Update
							KYC documents</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="mx-auto">
							<form id="kycupdateformid" name="mfdkycdcupdateform"
								enctype="multipart/form-data">
								
								<input type="hidden" name="mobile" value="${mobile }">
								
								<div class="md-form mb-1 mt-1">
									<input type="file" class="custom-file-input reuploadimage"
										name="kycphotoproof" id="kycphotoid"
										accept="image/jpg,image/jpeg,image/png" /> <label
										class="custom-file-label" for="kycphotoid">Upload
										Photo (1MB)</label> <small id="kycphotoid-msg"
										class="form-text text-danger mt-3"></small>
									<div>
										<img id="kycphotoid-pic" class="img-fluid" height=50>
									</div>
								</div>

								<div class="md-form mb-1 mt-1">
									<input type="file" name="kycpanproof" id="panproofid"
										class="custom-file-input reuploadimage"
										accept="image/jpg,image/jpeg,image/png" /> <label
										class="custom-file-label" for="panproofid">PAN Proof
										(1MB)</label> <small id="panproofid-msg"
										class="form-text text-danger mt-3"></small>
									<div>
										<img id="panproofid-pic" class="img-fluid" height=50>
									</div>
								</div>

								<div class="md-form mb-1 mt-1">
									<input type="file" name="cancelledcheque" id="chequeid"
										class="custom-file-input reuploadimage"
										accept="image/jpg,image/jpeg,image/png" /> <label
										class="custom-file-label" for="chequeid">Cancelled
										Cheque (1MB)</label> <small id="chequeid-msg"
										class="form-text text-danger mt-3"></small>
									<div>
										<img id="chequeid-pic" class="img-fluid" height=50>
									</div>
								</div>

								<div class="md-form mb-1 mt-1">
									<input type="file" name="addressproof" id="addressid"
										class="custom-file-input reuploadimage"
										accept="image/jpg,image/jpeg,image/png" /> <label
										class="custom-file-label" for="addressid">Address
										Proof (1MB)</label> <small id="addressid-msg"
										class="form-text text-danger mt-3"></small>
									<div>
										<img id="addressid-pic" class="img-fluid" height=50>
									</div>
								</div>

								<div class="md-form form-sm mb-0">
									<select class="custom-select" name="addressproofType" id="addressproofTypeid"
										style="border-top: transparent; border-left: transparent; border-right: transparent;">
										<option value="A">Passport</option>
										<option value="B">Voter ID</option>
										<option value="D" selected="selected">Driving License</option>
										<option value="E">UID</option>
									</select>
								</div>

								<div class="md-form form-sm mb-0">
									<input type="text" name="addressproofrefno"
										class="form-control" value="NA" maxlength="24"
										id="addressproofrefnoid" /> <label for="addressproofrefnoid">Reference
										No.</label>
								</div>
								
								<div class="md-form form-sm"
											id="expirydatebox">
											<i class="fas fa-calendar-day prefix"></i>
											<input type="text" name="addressproofpxpirydate"
												data-provide="datepicker" data-date-format="yyyy-mm-dd" required="required"
												data-date-start-date="+1d" data-date-end-date="+20y"
												maxlength="10"
												class="form-control form-control-custom datepicker"
												id="addressproofpxpirydateid" />
											<label for="addressproofpxpirydateid">Expiry Date</label>
										</div>

								<small id="docuploadmsg"></small>
								<div>
									<button type="submit" class="btn btn-sm btn-primary"
										id="kycupdatebutton">Save changes</button>
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
</section>