<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Modal -->
<div class="modal fade" id="taxResidentModal" tabindex="-1"
	role="dialog" aria-labelledby="TaxResidentLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title text-primary" id="TaxResidentLabel">TAX Resident
					Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="foreigntaxinfoform">

					<div class="mb-3">
					<small class="text-danger" id="taxdetailserrormsg"></small>
					</div>
					<div class="form-row">
						<div class="col">
							<div class="md-form mt-0">
								<!-- <input type="text" class="form-control" id="taxCountry"><label
									for="taxCountry">Tax Country</label> -->
									<select class="custom-select"
												id="taxCountry"
												style="border-top: transparent;border-left: transparent;border-right: transparent;">
												<option value="CHOOSE">Select Country</option>
												<c:forEach var="loop" items="${ckyccountrymaster }">
													<option value="${loop.key}">${loop.value}</option>
												</c:forEach>
											</select>
							</div>
						</div>

						<div class="col">
							<div class="md-form mt-0">
								<input type="text" id="taxIdentype" class="form-control"><label
									for="taxIdentype">Tax Identification Type</label>
							</div>
						</div>
					</div>


					<div class="form-row">
						<div class="col">
							<div class="md-form mt-0">
								<input type="text"  maxlength="12" class="form-control" id="taxIdenNumber" ><label
									for="taxIdenNumber">Tax Identification No</label>
							</div>
						</div>

						<div class="col">
							<div class="md-form mt-0 mb-0">
								<input type="date" id="taxtrcExpiryDate"
									class="form-control form-control-sm"> <small
									>TRC Expiry Date</small>
							</div>
						</div>
					</div>
					
					<div class="form-row">
					<div class="col">
							<div class="md-form mt-0">
								<input type="text" class="form-control" id="taxaddressType"><label
									for="taxaddressType">Tax Address Type</label>
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col">
							<div class="md-form mt-0">
								<input type="text" class="form-control" id="taxAdd1"><label
									for="taxAdd1">Address 1</label>
							</div>
						</div>

						<div class="col">
							<div class="md-form mt-0">
								<input type="text" id="taxAdd2" class="form-control"><label
									for="taxAdd2">Address 2</label>
							</div>
						</div>
					</div>


					<div class="form-row">
						<div class="col">
							<div class="md-form mt-0">
								<input type="text" class="form-control" id="taxCity"><label
									for="taxCity">City</label>
							</div>
						</div>

						<div class="col">
							<div class="md-form mt-0">
								<input type="text" id="taxState" class="form-control"><label
									for="taxState">State</label>
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col">
							<div class="md-form mt-0">
								<input type="text" class="form-control" id="taxPostalCode"><label
									for="taxPostalCode">Postal Code</label>
							</div>
						</div>

						<div class="col">
							<div class="md-form mt-0">
								<input type="text" id="taxLandmark" class="form-control"><label
									for="taxLandmark">Landmark</label>
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col">
							<div class="md-form mt-0">
								<input type="text" class="form-control" id="taxStdCodePr"><label
									for="taxStdCodePr">Std Code primary</label>
							</div>
						</div>

						<div class="col">
							<div class="md-form mt-0">
								<input type="text" id="taxTelNoPr" class="form-control"><label
									for="taxTelNoPr">Primary Tel. No.</label>
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col">
							<div class="md-form mt-0">
								<input type="text" class="form-control" id="taxMobilePr"><label
									for="taxMobilePr">Mobile No.</label>
							</div>
						</div>

						<div class="col">
							<div class="md-form mt-0">
								<input type="text" id="taxStdOther" class="form-control"><label
									for="taxStdOther">STD - Other</label>
							</div>
						</div>
					</div>


				</form>

			</div>
			<div class="modal-footer">
				<button type="reset" class="btn btn-sm btn-secondary" onclick="resetform('foreigntaxinfoform')">Reset</button>
				<button type="button" class="btn btn-sm btn-primary" onclick="insertforeigntaxDetails();">Add
					Details</button>
			</div>
		</div>
	</div>
</div>