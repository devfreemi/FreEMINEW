<div class="modal fade" id="emailverifymodal" tabindex="-1" role="dialog"
	aria-labelledby="emailverifymodalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="emailverifymodalLabel" style="font-weight: 500; color: #343739;">Verify personal details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row mb-3 mx-auto">
					<small class="text-center text-danger font-weight-bold" id="emailverifymsg"></small>
				</div>
				<form action="#!" id="emailverifyform">
					<div class="form-row mb-3">
						<div class="col-md-12 col-lg-12 mx-auto mb-4">
							<div class="row">
								<div class="col-sm-6">
									<p class="mb-0 text-dash pt-md-2 pt-0">
										<span id="verifytext">Email Address</span> <sup class="text-danger">*</sup>
									</p>
								</div>
								<div class="col-sm-6">
									<input type="email" class="form-control rounded" maxlength="12" maxlength="48"
									id="emailkey" name="key" required="required" readonly="readonly" />
								</div>
							</div>
						</div>

						<div class="col-md-12 col-lg-12 mx-auto d-none" id="eotpdiv">
							<div class="row">
								<div class="col-sm-6">
									<p class="mb-0 text-dash pt-md-2 pt-0">
										<span id="verifytext">Please Enter OTP</span> <sup class="text-danger">*</sup>
									</p>
								</div>
								<div class="col-sm-6">
									<div class="otp-input-fields">
										<input type="number" class="otp__digitE otp__field__1 otp-box" maxlength="1">
										<input type="number" class="otp__digitE otp__field__2 otp-box" maxlength="1">
										<input type="number" class="otp__digitE otp__field__3 otp-box" maxlength="1">
										<input type="number" class="otp__digitE otp__field__4 otp-box" maxlength="1">
										<input type="number" class="otp__digitE otp__field__5 otp-box" maxlength="1">
										<input type="number" class="otp__digitE otp__field__6 otp-box" maxlength="1">
									</div>
									<input type="hidden" class="form-control rounded  _notokE" maxlength="6" id="emailotpdata" name="otp" />
								</div>
							</div>
						</div>
						<div>
						<input type="hidden" id="everifytype" value="E">
						</div>
					</div>
					<!-- <div class="form-row">
						<button class="btn btn-info btn-rounded my-4 waves-effect z-depth-0 mx-auto" type="button" id="esendotpbutton">Send OTP</button>
						<button class="btn btn-info btn-rounded my-4 waves-effect z-depth-0 mx-auto" type="button" id="everifybutton">Verify Code</button>		
					</div> -->

				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" type="button" id="esendotpbutton">Send OTP</button>
				<button type="submit" class="btn btn-success" id="everifybutton">Verify</button>
			</div>
		</div>
	</div>
</div>


<script>
	var otp_inputs_E = document.querySelectorAll(".otp__digitE")
	var mykey_E = "0123456789".split("")
	otp_inputs_E.forEach((_) => {
		_.addEventListener("keyup", handle_next_input)
	})

	function handle_next_input(event) {
		let current_E = event.target
		let index = parseInt(current_E.classList[1].split("__")[2])
		current_E.value = event.key

		if (event.keyCode == 8 && index > 1) {
			current_E.previousElementSibling.focus()
		}
		if (index < 6 && mykey_E.indexOf("" + event.key + "") != -1) {
			var next_E = current_E.nextElementSibling;
			next_E.focus()
		}
		var _finalKey = ""
		for (let {
			value
		}
			of otp_inputs_E) {
			_finalKey += value
		}
		if (_finalKey.length == 6) {
			document.querySelector("#emailotpdata").classList.replace("_notokE", "_okE")
			document.querySelector("#emailotpdata").value = _finalKey
		} else {
			document.querySelector("#emailotpdata").classList.replace("_okE", "_notokE")
			document.querySelector("#emailotpdata").value = _finalKey
		}
	}
</script>