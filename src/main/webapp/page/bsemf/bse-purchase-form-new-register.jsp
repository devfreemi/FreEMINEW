<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form
	action="${pageContext.request.contextPath}/mutual-funds/mfpurchasefund.do"
	method="POST" modelAttribute="selectedFund" id="newuserpurhcase">
	
	<c:if test="${errormsg != null }">
		<div class="alert alert-danger p-2" style="font-size: 12px;"
			role="alert">${errormsg }</div>
	</c:if>
	
	<form:hidden path="clientID" />
	<form:hidden path="transactionID" />
	<form:hidden path="schemeOptionType" id="schemetypeid" />
	<form:hidden path="portfolio" />
	<form:hidden path="schemeCode" />
	<form:hidden path="reinvSchemeCode" />
	<form:hidden path="sipStartMonth" />
	<form:hidden path="sipStartYear" />
	<form:hidden path="mandateType" />
	<form:hidden path="noOfInstallments" />
	<form:hidden path="noOfInstallments" />
	<form:hidden path="eMandateRegRequired" />
	<form:hidden path="mandateId" />
	<form:hidden path="amcimg" />

	<div class="form-group row">
		<label for="custName" class="col-sm-5 col-form-label text-muted">Investor
			Name</label>
		<div class="col-sm-7">
			<div class="md-form mt-0 mb-0">
				<form:input path="investorName"
					class="form-control-plaintext form-control-sm" id="custName"
					readonly="true" />
			</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="mobileid" class="col-sm-5 col-form-label text-muted">Mobile</label>
		<div class="col-sm-7">
			<div class="md-form mt-0 mb-0">
				<form:input path="mobile"
					class="form-control-plaintext form-control-sm" id="mobileid"
					readonly="true" />
			</div>
		</div>
	</div>


	<div class="form-group row">
		<label for="schemeName" class="col-sm-5 col-form-label text-muted">Scheme
			Name</label>
		<div class="col-sm-7 input-group">
			<div class="input-group-prepend" style="margin-right: 0.5rem;">
					<span class="input-group-text md-addon" id="amcname"><img class="img-fluid" style="height: 1.7rem;" alt="AMC" src="https://resources.freemi.in/products/resources/images/partnerlogo/mf/${selectedFund.amcimg}"></span>
				</div>
			<div class="md-form mt-0 mb-0">
				<form:input path="schemeName" readonly="true"
					class="form-control-plaintext form-control-sm" id="schemeName" aria-describedby="amcname" />
			</div>
		</div>
	</div>



	<div class="form-group row">
		<label for="schemeName" class="col-sm-5 col-form-label text-muted">Fund
			category</label>
		<div class="col-sm-7">
			<div
				class="custom-control form-control-sm custom-checkbox custom-control-inline">
				<form:radiobutton path="invCategory" value="Z" id="growthCategory"
					name="catval" class="custom-control-input" />
				<label class="custom-control-label" for="growthCategory">Growth</label>
			</div>

			<c:if test="${not empty selectedFund.reinvSchemeCode}">
				<div
					class="custom-control form-control-sm custom-checkbox custom-control-inline">
					<form:radiobutton path="invCategory" value="Y"
						id="reinvestcategory" name="catval" class="custom-control-input" />
					<label class="custom-control-label" for="reinvestcategory">
						Dividend Re-investment</label>
				</div>
			</c:if>
		</div>
	</div>


	<div class="form-group row">
		<label for="investype" class="col-5 col-form-label text-muted">Investment
			Type</label>
		<div class="col-7">
			<div class="md-form mt-0 mb-0">
				<form:input path="investype" readonly="true"
					class="form-control-plaintext form-control-sm" id="invtype" />
			</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="invAmount" class="col-5 col-form-label text-muted">Investment
			Amount</label>
		<div class="col-7">
			<div class="md-form input-group mt-0 mb-0">
				<div class="input-group-prepend">
					<span class="input-group-text md-addon" id="material-addon1">&#x20B9;</span>
				</div>
				<form:input path="investAmount" class="form-control form-control-sm"
					id="invAmount" aria-describedby="material-addon1" />
			</div>

		</div>
	</div>

	<c:if test="${selectedFund.investype == 'SIP' }">

		<div class="form-group row">
			<label for="schemeDate" class="col-5 col-form-label text-muted">Monthly
				SIP date</label>
			<div class="col-7">
				<div class="md-form mt-0 mb-0">
					<form:input path="sipDate" readonly="true"
						class="form-control-plaintext" id="schemeDate" />
				</div>
			</div>
		</div>

		<div class="form-group row">
			<label for="payFirst1"
				class="col-6 col-md-5 col-lg-5 col-form-label text-muted">Pay
				First Installment?</label>
			<div class="col-6 col-md-7 col-lg-7">
				<div class="custom-control custom-checkbox">
					<form:checkbox path="payFirstInstallment"
						class="custom-control-input" id="payFirst1" checked="checked" />
					<label class="custom-control-label" for="payFirst1"> Yes </label>
				</div>
			</div>
		</div>
		<p><b>NOTE: </b> <small class="text-info"> E-mandate registration will be done for Systematic Investment Plan (SIP). You have to add BSE
			through your Internet banking portal. Your Internet banking must
			support biller.</small>
		</p>


	</c:if>

	<hr>
	<div class="custom-control custom-checkbox">

		<small class="text-info" data-toggle="modal"
			data-target="#mfdisclaimer"
			style="text-decoration: underline; cursor: pointer; font-size: 12px">Disclaimer
		</small> <input type="checkbox" class="custom-control-input" id="agreecheck1"
			checked="checked"> <label class="custom-control-label"
			for="agreecheck1" style="font-size: 11px; text-align: justify;">
			<span class="text-muted"> 1. Performance history may or may
				not be in sync with the future performance and should not be
				considered as a base for investments. <br> 2. The size of the
				Assets Under Management (AuM) are based on the last published
				Monthly AUM by the corresponding fund house. <br> 3. If the
				investors are confused about whether the product is suitable for
				them or not, then he should consult their financial advisers for a
				better guidance. <br>

		</span>
		</label>
	</div>

	<div style="text-align: center; margin-top: 20px;">
		<form:button type="submit"
			class="btn #00796b teal darken-2 white-text" id="orderconfirmbtn">Confirm Order <span id="submiticon"><i
				class="fab fa-opencart"></i></span>
		</form:button>
	</div>
</form:form>