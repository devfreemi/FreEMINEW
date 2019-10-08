<link href="${pageContext.request.contextPath}/resources/css/signup-campaign.css" rel="stylesheet"> 
	
<div class="modal fade" id="exampleModalCenter" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">

		<div class="modal-content signupcampaign">
			<div class="modal-header"
				style="border-bottom: unset; height: 90px; background: linear-gradient(to bottom right, #11998e, #38ef7d); color: aliceblue;">
				<img alt="NA" src="${pageContext.request.contextPath}/resources/images/rocket.png"
					class="img-fluid"
					style="height: 80px; margin: -12px 0px auto auto;">
				<h5 class="modal-title" id="exampleModalLongTitle"
					style="margin: auto auto auto 0px;">FREEMI NEWSLETTER</h5>
				<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button> -->
			</div>
			<div class="modal-body" style="padding: 30px;">
				<div>
					<span id="signup"></span>
				</div>
				<div class="topic" style="font-family: serif;font-weight: 600;">
					<p>Get access to our exclusive products and know more about
						FreEMI.</p>
				</div>
				<form>
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm"><i
								class="fas fa-mobile-alt"></i></span>
						</div>
						<input type="text" id="signupmob" pattern="[6-9][0-9]{9}"
							maxlength="10" class="form-control" aria-label="Small"
							placeholder="Your mobile number" onblur="formValidation();"
							aria-describedby="inputGroup-sizing-sm" required>
					</div>
					
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm"><i
								class="fas fa-at"></i></span>
						</div>
						<input type="email" id="email" class="form-control"
							aria-label="Small" placeholder="Your email id"
							aria-describedby="inputGroup-sizing-sm" maxlength="64" onkeyup="formValidation();" required>
					</div>

					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroup-sizing-sm"><i
								class="fas fa-map-marker"></i></span>
						</div>
						<input type="text" id="location" class="form-control"
							aria-label="Small" placeholder="Your city"
							aria-describedby="inputGroup-sizing-sm" maxlength="24" required>
					</div>
					
					<button type="submit" id="campaignsignup"
						class="btn btn-info btn-sm btn-block" disabled="disabled">Submit</button>
						
					<div>
					
					<label> <input type="checkbox" name="agree" id="agree" value="Y" checked="checked" /> <span
							class="checkmark" ></span>
							<span
							style="font-size: 12px; color: #21a35e;line-height: 0;"> <strong>I authorize FreEMI.in &amp; associate company to contact me overriding my registry on NDNC.
							</strong>
						</span> 
						</label>
					
					</div>
				<!-- <button type="button"
					class="btn btn-outline-danger btn-sm btn-block"
					data-dismiss="modal">Close</button> -->
				</form>
			</div>
			<!-- <div class="modal-footer" style="border-top: unset;">
				
				<button type="button" class="btn btn-primary">Save changes</button>
			</div> -->
		</div>
	</div>
</div>