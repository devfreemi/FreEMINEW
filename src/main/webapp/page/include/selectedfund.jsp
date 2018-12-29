<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" style="padding-right: 0;">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header  mf-modal-title-custom">
				<span class="modal-titl" style="margin: auto;"><span id="asd"></span></span>
				<button type="button" class="" style="cursor: pointer;"
					data-dismiss="modal">
					<i class="far fa-times-circle"></i>
				</button>

			</div>
			<form:form method="POST"
				action="${pageContext.request.contextPath}/mutual-funds/purchase.do"
				commandName="selectFund">
				<div class="modal-body">
						<div>
							
							<form:hidden path="schemeCode" id="schemecode"/>
							<div class="fund-data">Select Investment Type</div>
							<div class="custom-control custom-radio custom-control-inline">
								<form:radiobutton path="investype" value="SIP"
									id="customRadioInline1" name="customRadioInline1"
									class="custom-control-input" />
								<label class="custom-control-label" for="customRadioInline1">SIP (Monthly)</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<form:radiobutton path="investype" value="LUMPSUM"
									id="customRadioInline2" name="customRadioInline1"
									class="custom-control-input" />
								<label class="custom-control-label" for="customRadioInline2">
									LUMPSUM</label>
							</div>
						</div>
						
						<div style="margin-top: 20px;">
							<div class="fund-data">Investment amount (Minimum: &#8377;<span id="minVal" style="color: #dc7931;"></span>)</div>
							<div class="input-group input-group-sm mb-2">
								<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-sm">Rs.</span>
								</div>
								<form:input path="investAmount" id="amount" class="form-control"
									onkeyup="customamount();" aria-label="Small" required="required"
									style="height: 2rem;" aria-describedby="inputGroup-sizing-sm" />
							</div>

							<div class="btn-group btn-group-sm btn-group-toggle"
								data-toggle="buttons" id="radioamount">
								<label class="btn btn-secondary"> <input type="radio"
									value="2000" name="options" id="option1" autocomplete="off">
									2,000
								</label> <label class="btn btn-secondary"> <input type="radio"
									name="options" value="5000" id="option2" autocomplete="off">
									5,000
								</label> <label class="btn btn-secondary"> <input type="radio"
									name="options" value="10000" id="option3" autocomplete="off">
									10,000
								</label>
								<label class="btn btn-secondary"> <input type="radio"
									name="options" value="15000" id="option3" autocomplete="off">
									15,000
								</label>
							</div>
						</div>

				</div>
				<div class="modal-footer">
					<form:button type="submit" class="btn btn-primary btn-sm btn-block">BUY NOW <i class="fas fa-shopping-cart"></i></form:button>
				</div>
			</form:form>
		</div>

	</div>
</div>

<script>
	$(document).on("click", "#radioamount", function() {
		var x = $("input[type='radio'][name='options']:checked").val();
		/* console.log("Test" + $("input[type='radio'][name='options']:checked").val()); */
		$("#amount").val(x);
	});

	function customamount() {
		if ($('input:radio[name="options"]:checked')) {
			console.log("Check active")
			$('label').removeClass('active');
		}

	}
</script>