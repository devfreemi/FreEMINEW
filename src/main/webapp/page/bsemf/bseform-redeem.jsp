<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form id="regForm"
	action="${pageContext.request.contextPath}/mutual-funds/mfRedeem.do"
	method="POST" commandName="mfRedeemForm">


	<!-- One "tab" for each step in the form: -->
	<div class="animated fadeIn">

		<c:if test="${error != null }">
			<div>
				<span style="color: red; font-size: 11px;">${error }</span>
			</div>
		</c:if>
		<div>
			<span id="mandateField" style="color: red;"></span>
		</div>

		<div class="">
			<h5>Unit Holder Information</h5>
		</div>

		<div class="form-group row">
			<label for="folio" class="col-sm-2 col-form-label col-form-label-sm">Folio
				No:</label>
			<div class="col-sm-10">
				<form:input readonly="true" path="portfolio" id="folio"
					class="form-control form-control-sm form-control-plaintext" />
			</div>
		</div>

		<div class="form-group row">
			<label for="fundname"
				class="col-sm-2 col-form-label col-form-label-sm">Scheme
				Name:</label>
			<div class="col-sm-10">
				<form:input readonly="true" path="fundName" id="fundname"
					class="form-control form-control-sm form-control-plaintext" />
			</div>
		</div>

		<div class="form-group row">
			<label for="availableFund"
				class="col-sm-2 col-form-label col-form-label-sm">Available
				Amount:</label>
			<div class="col-sm-10">
				<form:input path="totalValue" id="availableFund"
					class="form-control form-control-sm" />
			</div>
		</div>

		<div class="form-group row">
			<label for="redeemamount"
				class="col-sm-2 col-form-label col-form-label-sm">Redeem
				Amount:</label>
			<div class="col-sm-10">
				<form:input path="redeemAmounts" id="redeemamount"
					class="form-control form-control-sm" />
			</div>
		</div>

		<div class="form-group row">
			<div class="col-sm-3" style="margin: auto;">
				<form:button
					class="btn btn-outline-secondary btn-block btn-sm confirm-tab">Next</form:button>
			</div>
		</div>

	</div>
</form:form>