<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.2/js/toastr.min.js"></script>
<div class="modal fade" id="mobileverifymodal"  tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title modal-p fs-5" id="exampleModalLabel">Verify Your Mobile Number</h1>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row mb-3 mx-auto">
					<small class="text-center text-danger font-weight-bold" id="mobileverifymsg"></small>
				</div>
				<form action="#!" id="mobileverifyform">
					<div class="form-row mb-3">
						<div class="col-md-12 col-lg-12 mx-auto mb-4">
							<div class="row">
								<div class="col-sm-6">
									<p class="mb-0 text-dash pt-md-2 pt-0">
										<span id="verifytext">10-digit mobile no</span> <sup class="text-danger">*</sup>
									</p>
								</div>
								<div class="col-sm-6">
									<input type="text" class="form-control rounded" maxlength="12" id="verifykey" name="key" required="required"
										readonly="readonly" />
								</div>
							</div>
						</div>
						<div class="col-md-12 col-lg-12 mx-auto d-none" id="motpdiv">
							<div class="row">
								<div class="col-sm-6">
									<p class="mb-0 text-dash pt-md-2 pt-0">
										<span id="verifytext">Please Enter OTP</span> <sup class="text-danger">*</sup>
									</p>
								</div>
								<div class="col-sm-6">
									<div class="otp-input-fields">
										<input type="number" class="otp__digit otp__field__1 otp-box" maxlength="1">
										<input type="number" class="otp__digit otp__field__2 otp-box" maxlength="1">
										<input type="number" class="otp__digit otp__field__3 otp-box" maxlength="1">
										<input type="number" class="otp__digit otp__field__4 otp-box" maxlength="1">
										<input type="number" class="otp__digit otp__field__5 otp-box" maxlength="1">
										<input type="number" class="otp__digit otp__field__6 otp-box" maxlength="1">
									</div>
									<input type="hidden" class="form-control rounded  _notok" maxlength="6" id="mobileotpdata" name="otp" />
								</div>
							</div>
						</div>
						<div>
						<input type="hidden" id="verifytype" value="M">
						</div>
					</div>
					<!-- <div class="form-row">
						<button class="btn btn-info btn-rounded my-4 waves-effect z-depth-0 mx-auto" type="button" id="msendotpbutton">Send OTP</button>
						<button class="btn btn-info btn-rounded my-4 waves-effect z-depth-0 mx-auto" type="button" id="mverifybutton">Verify Code</button>		
					</div> -->

				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" type="button" id="msendotpbutton">Send OTP</button>
				<button type="submit" class="btn btn-success" id="mverifybutton">Verify</button>
			</div>
		</div>
	</div>
</div>
<script>
	var otp_inputs = document.querySelectorAll(".otp__digit")
	var mykey = "0123456789".split("")
	otp_inputs.forEach((_) => {
		_.addEventListener("keyup", handle_next_input)
	})

	function handle_next_input(event) {
		let current = event.target
		let index = parseInt(current.classList[1].split("__")[2])
		current.value = event.key

		if (event.keyCode == 8 && index > 1) {
			current.previousElementSibling.focus()
		}
		if (index < 6 && mykey.indexOf("" + event.key + "") != -1) {
			var next = current.nextElementSibling;
			next.focus()
		}
		var _finalKey = ""
		for (let {
			value
		}
			of otp_inputs) {
			_finalKey += value
		}
		if (_finalKey.length == 6) {
			document.querySelector("#mobileotpdata").classList.replace("_notok", "_ok")
			document.querySelector("#mobileotpdata").value = _finalKey
		} else {
			document.querySelector("#mobileotpdata").classList.replace("_ok", "_notok")
			document.querySelector("#mobileotpdata").value = _finalKey
		}
	}
</script>