<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Modal -->
<div class="modal fade" id="selectfundmodal" role="dialog"
	style="padding-right: 0;">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header  mf-modal-title-custom">
				<span class="modal-title" style="padding: 0px 5px;font-weight: 400;">
				<img class="img-fluid" id="amcicondisplay" style="height: 1.7rem;float:left; padding: 2px;" src="" alt="AMC Icon"  >
				<span
					id="schemeNameTitle"></span></span>
				<button type="button" class="close" data-dismiss="modal">
					<i class="far fa-times-circle"></i>
				</button>

			</div>
			<form:form method="POST"
				action="${pageContext.request.contextPath}/mutual-funds/purchase.do"
				modelAttribute="selectFund" onsubmit="return validateFundForm();">
				<div class="modal-body">
					<div>
						<div>
							<small class="text-danger" id="error1">${error1}</small>
						</div>
						<form:hidden path="schemeCode" id="schemecode" />
						<form:hidden path="reinvSchemeCode" id="reinvSchemeCode" />
						<form:hidden path="schemeName" id="schemeName" />
						<form:hidden path="rtaAgent" id="rtaAgent"/>
						<form:hidden path="amcCode" id="amcCode" />
						
						<div class="fund-data">Select Investment Type</div>
						<div class="custom-control custom-radio custom-control-inline">
							<form:radiobutton path="investype" value="SIP"
								id="transactionType1" name="transactionTypeVal"
								class="custom-control-input" />
							<label class="custom-control-label" for="transactionType1">SIP
								(Monthly)</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<form:radiobutton path="investype" value="LUMPSUM"
								id="transactionType2" name="transactionTypeVal"
								class="custom-control-input" />
							<label class="custom-control-label" for="transactionType2">
								LUMPSUM</label>
						</div>
					</div>

					<div style="margin-top: 15px;">
						<div class="fund-data">
							Investment amount (Minimum: <i class="fas fa-rupee-sign"></i><span
								id="minvalreq" style="color: #dc7931;"></span>)
						</div>

						<div class="md-form mb-3" style="margin-top: 0px;">
							 <img class="img-fluid prefix lazy" style="height: 2rem;" data-src="<c:url value="${contextcdn}/resources/images/invest/investment-funds.png"/>" ><form:input type="text" id="amount" path="investAmount"
								class="form-control form-control-sm" value="2000" pattern="[0-9]*"
								onkeyup="customamount();" required="required" maxlength="7"
								placeholder="Investment amount" /> 
						</div>


						<div class="btn-group btn-group-sm btn-group-toggle"
							data-toggle="buttons" id="radioamount" style="padding-left: 2.5rem;">
							<label class="btn btn-info active" for="option1"> <input type="radio"
								value="2000" name="options" id="option1" autocomplete="off">
								2,000
							</label> <label class="btn btn-info" for="option2"> <input type="radio"
								name="options" value="5000" id="option2" autocomplete="off">
								5,000
							</label> <label class="btn btn-info" for="option3"> <input type="radio"
								name="options" value="10000" id="option3" autocomplete="off">
								10,000
							</label> <label class="btn btn-info" for="option4"> <input type="radio"
								name="options" value="15000" id="option4" autocomplete="off">
								15,000
							</label>
						</div>
					</div>
					
					<div class="md-form mb-3">
							 <img class="img-fluid prefix lazy" style="height: 2rem;" data-src="<c:url value="${contextcdn}/resources/images/invest/fund_mobile.svg"/>" ><form:input type="text" class="form-control form-control-sm"
							id="mobile" path="mobile" style="height: 2rem;"
					  placeholder="10-digit mobile number"
							maxlength="10" pattern="[0-9]*" /> 
						</div>
					
					<div class="md-form mb-3">
							 <img class="img-fluid prefix lazy" style="height: 1.7rem;" data-src="<c:url value="${contextcdn}/resources/images/invest/pan-card.png"/>" ><form:input path="pan" type="text"
							class="form-control fomr-control-sm" id="panval"
							style="height: 2rem;text-transform: uppercase;"
							required="required" placeholder="PAN" maxlength="10;" />
						</div>
					<div class="form-group col-md-6 mb-1" id="sipbox">

						<div class="input-group" style="margin-left: 1.7rem;">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon3">SIP DATES</span>
								</div>
								<form:select class="custom-select" id="sipOtherDates"
									path="sipDate">
									
								</form:select>
							</div>
						</div>
					
					
					<div>
						<span id="selectmsg" style="color: red; font-size: 11px;font-weight: 400;"></span>
					</div>
				</div>
				<div class="modal-footer">
					<form:button type="submit" class="btn rgba(244, 67, 54, 0.7) rgba-red-strong btn-sm btn-block text-white" id="selectfundbtn">BUY NOW <i
							class="fas fa-shopping-cart"></i>
					</form:button>
				</div>
			</form:form>
		</div>

	</div>
</div>

<script>
	
</script>