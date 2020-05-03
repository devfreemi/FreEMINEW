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
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css">
<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" defer="defer"></script>
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js" defer="defer"></script>

<script type="text/javascript">
	$(function() {
		$("#targetdate ").datepicker({
			format : 'dd/mm/yyyy'
		});
	});
</script>
<style type="text/css"> /* Style the form */
#regForm {
	background-color: #ffffff;
	margin: 100px auto;
	padding: 40px;
	width: 70%;
	min-width: 300px;
}

tbody > th {
font-size: 11px;
}

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
				<div class="row p-1" style="box-shadow: 0 0 3px 2px #c9c4c4; border-radius: 5px;">
					<div class="col-md-4 col-lg-4" style="background: #1cb7a1;">
						<img
							src="<c:url value="${contextcdn}/resources/images/registry/registry_goals.svg"/>"
							class="img-fluid">
					</div>
					<div class="col-md-8 col-lg-8" style="min-height: 400px;">
						<form:form commandName="registryWishForm"
							action="${pageContext.request.contextPath}/registry-planner-capture"
							method="POST">

							<div class="mb-4">
								<span class="text-danger" id="infomsg"><c:if
										test="${error !=null }">${error }</c:if> </span>
							</div>
							<!-- One "tab" for each step in the form: -->
							<div class="tab animated fadeIn">
								<div class="row">

									<div class="md-form col-md-8 mb-4" style="margin: auto;">
										<span class="text-dark">How do you want to achieve your
											goal?</span>
										<form:select class="custom-select custom-select-modified mt-2"
											id="investmenttype" path="investType">
											<form:options items="${investmentType}" />
										</form:select>
									</div>
									<div class="md-form col-md-8" style="margin: auto;">
										<span class="text-dark">What is your goal amount?</span>
										<form:input type="text" path="amount" id="amountid"
											class="form-control" />
									</div>
									<div class="md-form col-md-8 mb-4" style="margin: auto;">
										<span class="btn btn-sm btn-secondary definedvalue" data-value="10000">20K</span>
										<span class="btn btn-sm btn-secondary definedvalue" data-value="50000">50K</span>
										<span class="btn btn-sm btn-secondary definedvalue" data-value="100000">1lac</span>
									</div>
								</div>

							</div>

							<div class="tab animated fadeIn" >
								<div class="row">
									<div class="md-form col-md-8 " style="margin: auto;">
										<span class="text-dark">When is your event?</span>
										<form:input type="text" path="date"
											data-provide="datepicker" data-date-format="mm/dd/yyyy"
											placeholder="mm/dd/yyyy" data-date-start-date="+1y"
											data-date-end-date="+15y" maxlength="10"
											class="form-control form-control-custom datepicker"
											id="eventdate" />
									</div>

								</div>

							</div>

							<div class="tab">

								<div class="row">
									<div class="col-md-4 col-lg-4 btn #f4511e deep-orange darken-1 p-2"
										style="margin: auto;">
										<h3 class="d-flex white-text justify-content-center mb-0">
											&#8377;<span id="maturityvalue">0</span>
										</h3>
									</div>
								</div>
								<div class="row">
									<div class="md-form col-md-6 animated fadeIn">
										<span class="text-dark">Monthly Investment</span>
										<form:input type="text" path="monthlySavings"
											id="monthlySavings" class="form-control" />
									</div>
									<div class="md-form col-md-6 animated fadeIn">
										<span class="text-dark">Duration (in months)</span>
										<form:input type="text" path="tenure" id="tenureid"
											class="form-control" />
									</div>


								</div>

								<div>
									<h5 style="color: #e8623a; font-weight: 400;">Select Funds</h5>
									<!-- 	<div
									style="padding: 2rem; box-shadow: 0 0 0 1px #efeeee; overflow: auto;"> -->
									<div class="dataTables_wrapper dt-bootstrap4 animated fadeIn"
										style="margin-top: 30px;">
										<table class="table"
											style="box-shadow: 1px 3px 5px 1px #d4cfcf;"
											id="dtBasicExample">
											<thead class="text-muted">
												<tr>
													<th scope="col" valign="middle">Fund Name</th>
													<th scope="col" valign="middle">Monthly Contribution
														(Rs.)</th>
													<th scope="col" valign="middle">Select</th>
												</tr>
											</thead>
											<tbody id="registryfundslist" >


											</tbody>

										</table>
									</div>

									<!-- </div> -->
								</div>

							</div>

							<div class="tab">

								<div class="md-form col-md-8 animated fadeIn"
									style="margin: auto;">
									<span class="text-dark">Your Mobile no</span>
									<form:input type="number" path="mobile" id="mobileid"
										maxlength="10" class="form-control" />
								</div>
								<div class="md-form col-md-8 animated fadeIn"
									style="margin: auto;">
									<span class="text-dark">Your PAN no</span>
									<form:input type="text" path="pan" id="panid" maxlength="10"
										class="form-control" />
								</div>
								<form:hidden path="wishType" />
								<form:hidden path="registryfundcode" />
								<form:hidden path="registryname" />
								<form:hidden path="schemename" id="schemenameid" />
							</div>

							<div id="progressdisplay"></div>

							<div style="overflow: auto;">
								<div class="d-flex justify-content-center">
									<button type="button" class="btn btn-sm btn-secondary"
										id="prevBtn" onclick="nextPrev(-1)">Previous</button>
									<button type="button" class="btn btn-sm btn-default"
										id="nextBtn" onclick="nextPrev(1)">Next</button>
								</div>
							</div>

							<!-- Circles which indicates the steps of the form: -->
							<div style="text-align: center; margin-top: 40px;">
								<span class="step"></span> <span class="step"></span> <span
									class="step"></span> <span class="step"></span>
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
	console.log(n )
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
			document.getElementById("nextBtn").innerHTML = "Create this Registry";
		} else {
			document.getElementById("nextBtn").innerHTML = "Next";
		}
		// ... and run a function that displays the correct step indicator:
		fixStepIndicator(n)
	}

	function nextPrev(n) {
		// This function will figure out which tab to display
		var x = document.getElementsByClassName("tab");
		// Exit the function if any field in the current tab is invalid:
	console.log(n + " -> " + currentTab);
		if (n == 1 && !validateForm()){
			console.log("Invalid form")
			return false;
		}

		// Hide the current tab:
		x[currentTab].style.display = "none";
		// Increase or decrease the current tab by 1:
		currentTab = currentTab + n;
		// if you have reached the end of the form... :

	
		if (n == 1 && currentTab == 4) {
			showprogress();
		}

		if (currentTab >= x.length) {
			//...the form gets submitted:
			document.getElementById("registryWishForm").submit();
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

	function showprogress() {
		$("#errormsgbox").text("");
		$("#progressdisplay")
				.html(
						"<div class=\"mb-3 mt-3 text-center text-secondary\"><h5>Processing your request. Please do not press 'Back' Button or refresh the page.</h5><img alt=\"Fetching your portfolio\" src=\"https://resources.freemi.in/products/resources/images/invest/progress2.gif\">");
		$("#prevBtn").attr("disabled", "disabled");
		$("#nextBtn").attr("disabled", "disabled");
	}
</script>
<script src="<c:url value="${contextcdn}/resources/js/registry-planner.js" />"></script>
</html>