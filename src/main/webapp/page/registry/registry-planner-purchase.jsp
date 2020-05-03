<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registry wish</title>


<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<%-- <script
	src="<c:url value="${contextcdn}/resources/js/registry-planner.js" />"></script> --%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css">
<link
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">

<script type="text/javascript">
$( function () { 
	$(" #targetdate ") .datepicker ({ format : '
dd /mm/yyyy' } ); } ); 
</ script>
<style type="text /css"> /* Style the form */ #regForm {
	background-color: #ffffff;
	margin: 100px auto;
	padding: 40px;
	width: 70%;
	min-width: 300px;
}
</script>

<style type="text/css">
/* Style the input fields */
input {
	padding: 10px;
	width: 100%;
	font-size: 17px;
	font-family: Raleway;
	border: 1px solid #aaaaaa;
}

/* Mark input boxes that gets an error on validation: */
input.invalid {
	background-color: #ffdddd;
}

/* Hide all steps by default: */
.tab {
	display: none;
}

/* Make circles that indicate the steps of the form: */
.step {
	height: 15px;
	width: 15px;
	margin: 0 2px;
	background-color: #bbbbbb;
	border: none;
	border-radius: 50%;
	display: inline-block;
	opacity: 0.5;
}

/* Mark the active step: */
.step.active {
	opacity: 1;
}

/* Mark the steps that are finished and valid: */
.step.finish {
	background-color: #4CAF50;
}
</style>

<style type="text/css">
table.dataTable thead th, table.dataTable thead td {
	border-bottom: 1px solid #dcdcdc;
}

.custom-select-modified {
	border-top: transparent;
	border-left: transparent;
	border-right: transparent;
	border-bottom-left-radius: 0px;
	border-bottom-right-radius: 0px;
}

.custom-select select {
	border-top: none;
}
</style>

</head>

<body>

	<jsp:include page="../include/header.jsp"></jsp:include>

	<section>
		<div class="row p-1" style="margin: auto;">
			<div class="col-md-6 col-lg-6" style="margin: auto;">
				<h1 class="text-muted mt-3 mb-3"
					style="font-size: 16px; font-weight: 600;">Plan your registry</h1>
				<div class="row" style="box-shadow: 0 0 11px 1px #dcd4d4;border: 1px solid #cdcdcd; border-radius: 5px;">
					<div class="col-md-4 col-lg-4" style="margin: auto;">
						<img class="d-none d-sm-block img-fluid"
							src="<c:url value="${contextcdn}/resources/images/registry/registry-checklist.svg"/>"
							alt="Checklist">
					</div>
					<div class="col-md-8 col-lg-8">


						<form:form commandName="plannerdetails"
							action="${pageContext.request.contextPath}/registry-planner-purchase.do"
							method="POST">

							<!-- <h1>Register:</h1> -->
							<div class="mb-4">
								<span class="text-danger" id="infomsg"><c:if
										test="${error !=null }">${error }</c:if> </span>
							</div>
							<!-- One "tab" for each step in the form: -->
							<div class="tab">
								<div class="animated fadeIn">
									<div class="md-form" style="margin: auto;">
										<span class="text-primary">Your Mobile no</span>
										<form:input type="number" path="mobile" id="mobileid"
											maxlength="10" class="form-control" />
									</div>
									<div class="md-form mb-2" style="margin: auto;">
										<span class="text-primary">Your PAN no</span>
										<form:input type="text" path="pan" id="panid" maxlength="10"
											class="form-control" />
									</div>
									<div class="md-form mb-2" style="margin: auto;">
										<span class="text-primary">Monthly contribution</span>
										<form:input type="text" path="investAmount" id="amountid"
											class="form-control" />
									</div>


									<div class="md-form mb-4" style="margin: auto;">
										<span class="text-primary">Contribution period (in
											months)</span>
										<form:input type="text" path="noOfInstallments" id="tenureid"
											class="form-control" />
									</div>


									<div class="md-form mb-2" style="margin: auto;">
										<span class="text-primary">Select the monthly
											contribution date</span>
										<div class="row">
											<div class="col">
												<div class="md-form mt-0">
													<form:select
														class="custom-select custom-select-modified mt-1"
														id="sipDateid" path="sipDate">
														<form:option value="">Select Date</form:option>
														<form:options items="${fundsipdates }" />
													</form:select>
												</div>
											</div>

											<div class="col">
												<div class="md-form mt-0">
													<form:select
														class="custom-select custom-select-modified mt-1"
														id="sipmonth" path="sipStartMonth">
														<form:options items="${calendarmonths}" />
													</form:select>
												</div>
											</div>
											<div class="col">
												<!-- Material input -->
												<div class="md-form mt-0">
													<form:select
														class="custom-select custom-select-modified mt-1"
														id="sipyear" path="sipStartYear">
														<form:options items="${sipyear}" />
													</form:select>
												</div>
											</div>

										</div>

									</div>


									<form:hidden path="producttype" />
									<form:hidden path="schemeCode" />
									<form:hidden path="productrefid" />
								</div>

							</div>

							<div class="tab animated fadeIn">
								
								<div class="unique-color white-text p-2"><h3 class="mb-0"><i class="fas fa-shopping-cart"></i> Summary</h3></div>
								
								<div class="dataTables_wrapper dt-bootstrap4 animated fadeIn"
										style="margin-top: 30px; overflow: auto;">
										<table class="table" id="registry-summary">
											
											<tbody id="registryfundslist" >
											<tr>
											<th>Registry Name</th>
											<td>${registrywish.wishType }</td>
											</tr>
											<tr>
											<th>Customer mobile</th>
											<td>${plannerdetails.mobile }</td>
											</tr>
											<tr>
											<th>Event Date</th>
											<td>${registrywish.date } </td>
											</tr>
											<tr>
											<th>Target Goal</th>
											<td>	&#8377;${registrywish.amount }</td>
											</tr>
											<tr>
											<th>Registry fund</th>
											<td>${registrywish.schemeCode }</td>
											</tr>
											<tr>
											<th>Monthly Contribution amount</th>
											<td>	&#8377;${plannerdetails.investAmount }</td>
											</tr>
											<tr>
											<th>Tenure</th>
											<td>${plannerdetails.noOfInstallments }</td>
											</tr>

											</tbody>

										</table>
									</div>

							</div>

							<div id="progressdisplay"></div>

							<div class="mb-4" style="overflow: auto;">
								<div class="d-flex justify-content-center">
									<button type="button" class="btn btn-sm btn-secondary"
										id="prevBtn" onclick="nextPrev(-1)"><i class="fas fa-backward"></i> Previous</button>
									<button type="button" class="btn btn-sm btn-default"
										id="nextBtn" onclick="nextPrev(1)">Next <i class="fas fa-forward"></i></button>
								</div>
							</div>

							<!-- Circles which indicates the steps of the form: -->
							<div style="text-align: center; margin-top: 40px;display: none;">
								<span class="step"></span> <span class="step"></span>
							</div>

						</form:form>
					</div>
				</div>




			</div>
		</div>

	</section>

	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var currentTab = 0; // Current tab is set to be the first tab (0)
	showTab(currentTab); // Display the current tab

	function showTab(n) {
		// This function will display the specified tab of the form ...
		console.log(n);
		var x = document.getElementsByClassName("tab");
		x[n].style.display = "block";
		// ... and fix the Previous/Next buttons:

		if (n == 2) {
			calculateregistrydetails();
		}
		if (n == 0) {
			document.getElementById("prevBtn").style.display = "none";
		} else {
			document.getElementById("prevBtn").style.display = "inline";
		}
		if (n == (x.length - 1)) {
			document.getElementById("nextBtn").innerHTML = "Create this Registry <i class='fab fa-opencart'></i>";
		} else {
			document.getElementById("nextBtn").innerHTML = "Next <i class='fas fa-forward'></i>";
		}
		// ... and run a function that displays the correct step indicator:
		fixStepIndicator(n)
	}

	function nextPrev(n) {
		// This function will figure out which tab to display
		//console.log(n + " -> "+ currentTab)
		if (n == 1 && currentTab == 1) {
				showprogress();
			}
		var x = document.getElementsByClassName("tab");
		// Exit the function if any field in the current tab is invalid:
		if (n == 1 && !validateForm())
			return false;
		// Hide the current tab:
		x[currentTab].style.display = "none";
		// Increase or decrease the current tab by 1:
		currentTab = currentTab + n;
		// if you have reached the end of the form... :
		
		
		
		if (currentTab >= x.length) {
			//...the form gets submitted:
			document.getElementById("plannerdetails").submit();
			return false;
		}
		// Otherwise, display the correct tab:
		showTab(currentTab);
	}

	function validateForm() {
		// This function deals with validation of the form fields
		var x, y, i, valid = true;
		x = document.getElementsByClassName("tab");
		y = x[currentTab].getElementsByTagName("input");
		// A loop that checks every input field in the current tab:
		for (i = 0; i < y.length; i++) {
			// If a field is empty...
			if (y[i].value == "") {
				// add an "invalid" class to the field:
				y[i].className += " invalid";
				// and set the current valid status to false:
				valid = false;
			}
		}
		// If the valid status is true, mark the step as finished and valid:
		if (valid) {
			document.getElementsByClassName("step")[currentTab].className += " finish";
		}
		return valid; // return the valid status
	}

	function fixStepIndicator(n) {
		// This function removes the "active" class of all steps...
		var i, x = document.getElementsByClassName("step");
		for (i = 0; i < x.length; i++) {
			x[i].className = x[i].className.replace(" active", "");
		}
		//... and adds the "active" class to the current step:
		x[n].className += " active";
	}

	/* $(function() {
		$("#eventdate").datepicker({
			format : 'dd/mm/yyyy',
			startDate : '+1y',
			endDate : '+15y',
			autoclose : true
		});
	}); */

	$(document).ready(function() {
		var d= new Date();
		var currentYear= d.getFullYear(); 
		$("#sipyear").val(currentYear);
	});

	function showprogress(){
		$("#errormsgbox").text("");
		$("#progressdisplay").html("<div class=\"mb-3 mt-3 text-center text-secondary\"><h5>Processing your request. Please do not press 'Back' Button or refresh the page.</h5><img alt=\"Fetching your portfolio\" src=\"https://resources.freemi.in/products/resources/images/invest/progress2.gif\">");
		$("#prevBtn").attr("disabled", "disabled");
		$("#nextBtn").attr("disabled", "disabled");
		}
	
</script>
</html>