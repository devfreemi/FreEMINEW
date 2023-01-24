<!-- Modal -->
<div class="modal fade" id="mandatemodal" tabindex="-1"
	aria-labelledby="mandatemodallabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="mandatemodallabel">Mandate Details</h5>
				<!-- <button type="button" class="btn-close" data-dismiss="modal"
					aria-label="Close"></button> -->
			</div>
			<div class="modal-body">
				<p><small class="text-danger" id="errmsg"></small></p>
				<div class="row">
					<div class="col-6">
						<label><strong>Mandate ID</strong></label>
					</div>
					<div class="col-6"><label id="mandateid"></label></div>
				</div>
				<div class="row">
					<div class="col-6">
						<label><strong>Status</strong></label>
					</div>
					<div class="col-6" ><label id="mandatestatus"></label></div>
				</div>
				<div class="row d-none" id="authact">
					<div class="col-6">
						<label>Action</label>
					</div>
					<div class="col-6" ><button class="btn btn-sm btn-primary" id="mandateauthact">Authenticate</button> </div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-secondary"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>