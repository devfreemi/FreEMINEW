<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form id="regForm"
	action="${pageContext.request.contextPath}/profile/submitfatca.do"
	method="POST" commandName="fatcaform">

	<div class="row">
		<div class="col-md-4 col-lg-4">
			<div class="md-form mt-0">
				<form:input type="text" path="pan1"
					class="form-control form-control-sm" placeholder="PAN Number"></form:input>
			</div>
		</div>

		<div class="col-md-4 col-lg-4">
			<!-- Material input -->
			<div class="md-form mt-0">
				<form:input type="text" path="applicant1"
					class="form-control form-control-sm" placeholder="Applicant Name"></form:input>
			</div>
		</div>

		<div class="col-md-4 col-lg-4">
			<!-- Material input -->
			<div class="md-form mt-0">
				<form:input type="text" path="applicant1"
					class="form-control form-control-sm" placeholder="Tax Status"></form:input>
			</div>
		</div>
	</div>
	
	<!-- 2nd row  -->
	
	<div class="row">
		<div class="col-md-4 col-lg-4">
			<div class="md-form mt-0">
				<form:input type="text" path="pan1"
					class="form-control form-control-sm" placeholder="Address Type"></form:input>
			</div>
		</div>

		<div class="col-md-4 col-lg-4">
			<!-- Material input -->
			<div class="md-form mt-0">
				<form:input type="text" path="applicant1"
					class="form-control form-control-sm" placeholder="Place of birth"></form:input>
			</div>
		</div>

		<div class="col-md-4 col-lg-4">
			<!-- Material input -->
			<div class="md-form mt-0">
  				<form:input placeholder="Date of Birth" path="applicant1DOB" type="date" id="date-picker-dob" class="form-control form-control-sm datepicker"></form:input>
  				<!-- <label for="date-picker-example">Try me...</label> -->
			</div>
		</div>
	</div>
	
	<!-- 3rd row  -->
	
	<div class="row">
		<div class="col-md-4 col-lg-4">
			<div class="md-form mt-0">
				<form:select class="fatca-form-select form-control form-control-sm" id="holdingMode"
							path="pan1" style="border-top: none; border-left: none; border-right: none;">
							<form:option value="" selected="true">Income slab</form:option>
							<form:option value="Single" >Single</form:option>
							<form:option value="Single">Joint</form:option>
							<%-- <form:options items="${holingNature}" /> --%>
						</form:select>
				
				
			</div>
		</div>

		<div class="col-md-4 col-lg-4">
			<!-- Material input -->
			<div class="md-form mt-0">
				<form:select class="fatca-form-select form-control form-control-sm" id="holdingMode"
							path="pan1" style="border-top: none; border-left: none; border-right: none;">
							<form:option value="" selected="true">Income slab</form:option>
							<form:option value="Single" >Single</form:option>
							<form:option value="Single" >Joint</form:option>
							<%-- <form:options items="${holingNature}" /> --%>
						</form:select>
			</div>
		</div>

		<div class="col-md-4 col-lg-4">
			<div class="md-form mt-0">
				<form:select class="fatca-form-select form-control form-control-sm" id="holdingMode"
							path="pan1" style="border-top: none; border-left: none; border-right: none;">
							<form:option value="" selected="true">Occupation type</form:option>
							<form:option value="Single" >Single</form:option>
							<form:option value="Single" >Joint</form:option>
							<%-- <form:options items="${holingNature}" /> --%>
						</form:select>
			</div>
		</div>
	</div>

</form:form>

<script type="text/javascript">
//Data Picker Initialization
$('.datepicker').pickadate();
</script>