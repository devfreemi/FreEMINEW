<!-- <link href="/freemi/resources/css/kyc.css" rel="stylesheet"> --> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="exampleModalCenter" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">

		<div class="modal-content otpsubmission">
			<div class="modal-header otp_header" >
				<img alt="NA" src="/freemi/resources/images/aadhaar_icon.png"
					class="img-fluid" style="height: 50px; margin: auto;">
				<h5 class="modal-title" id="exampleModalLongTitle"
					style="margin: auto">AADHAAR VERIFICATION</h5>
				<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button> -->
			</div>
			<div class="modal-body" style="padding: 30px;">
				<div>
					<span id="signup"></span>
				</div>
				<div class="topic_otp">
					<p>Please enter the OTP sent to your registered mobile number</p>
				</div>
				<form:form action="${pageContext.request.contextPath}/validateAadhaarOTP" method="POST" commandName="aadhaarotp">
					<div class="form-group row">
					
						<form:hidden path="pan"/>
						<form:hidden path="aadhaar"/>
						<div class="col-6" style="margin: auto;">
    					<form:input class="form-control" id="otpbox" path="otp" type="text" pattern="[0-9]+" maxlength="6" style="text-align: center; font-weight: bold;" />
  						</div>
  						  						
					</div>
					<div class="timer_style">
					<span id="timer"></span>
					</div>
					<button type="submit" id="otpsubmit"
						class="btn btn-info btn-sm btn-block" disabled="disabled">Submit</button>
				<!-- <button type="button"
					class="btn btn-outline-danger btn-sm btn-block"
					data-dismiss="modal">Close</button> -->
				</form:form>
			</div>
			<!-- <div class="modal-footer" style="border-top: unset;">
				
				<button type="button" class="btn btn-primary">Save changes</button>
			</div> -->
		</div>
	</div>
</div>