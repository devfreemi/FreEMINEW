<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fixed Deposit Investment, interest rate upto 8.50%*</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="keywords" content="Fixed Desposit" />
<meta name="description" content="fixed Deposit " />
<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow" />
<meta name="bingbot" content="index, follow" />
<link rel="canonical" href="/products/fixed-deposit" />

<jsp:include page="/page/include/bootstrap.jsp"></jsp:include>
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>" rel="preload" as="style" onload="this.rel='stylesheet'">
<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" rel="preload" as="style" onload="this.rel='stylesheet'">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script> -->
<script type="text/javascript">

</script>

</head>
<body>
	<jsp:include page="/page/include/header.jsp"></jsp:include>
	<div class="container-fluid" style="margin-bottom: 5rem;">
		<section>
			<div class="row">
				<div class="col-md-7 col-lg-7">
					<jsp:include page="mahindra-fd-form-detailed.jsp"></jsp:include>
				</div>

				<div class="col-md-5 col-lg-5 d-none d-sm-block">
					<img
						src="<c:url value="${contextcdn}/resources/images/invest/fd-1.png"/>"
						class="img-fluid animated slideInRight">
				</div>

			</div>
		</section>

	</div>

	<jsp:include page="/page/include/sub-footer.jsp"></jsp:include>
	<jsp:include page="/page/include/footer.jsp"></jsp:include>
	<jsp:include page="tax-resident-details-modal.jsp"></jsp:include>
</body>
<script type="text/javascript">
var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
	// This function will display the specified tab of the form...
	//console.log("currenttab- "+currentTab + " : n: "+n );
	
	var x = document.getElementsByClassName("tab");
	x[n].style.display = "block";
	//... and fix the Previous/Next buttons:
	if (n == 0) {
		document.getElementById("prevBtn").style.display = "none";
	} else {
		document.getElementById("prevBtn").style.display = "inline";
	}
	if (n == (x.length - 1)) {
		document.getElementById("nextBtn").innerHTML = "Submit";
	} else {
		document.getElementById("nextBtn").innerHTML = "Next";
	}
	//... and run a function that will display the correct step indicator:
	fixStepIndicator(n)
}

function nextPrev(n) {
	console.log("nextprev- "+currentTab + " : n: "+n );
	// This function will figure out which tab to display
	
	if(currentTab == 2 && n ==1){
		console.log("Show progress");
		showprogress();
	}
	if(currentTab == 1 && n ==1){
		console.log("Populate summary");
		populatesummary();
	}
	
	var x = document.getElementsByClassName("tab");
	// Exit the function if any field in the current tab is invalid:
	if (n == 1 && !validateForm())
		return false;
	// Hide the current tab:
	x[currentTab].style.display = "none";
	// Increase or decrease the current tab by 1:
	currentTab = currentTab + n;
	// if you have reached the end of the form...
	if (currentTab >= x.length) {
		// ... the form gets submitted:
		document.getElementById("fdpurchaseform").submit();
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
		if (y[i].value == "" && y[i].hasAttribute('required')) {
			// add an "invalid" class to the field:
			y[i].className += " invalid";
			// and set the current valid status to false
			valid = false;
			/* valid = true; */ // Test field
		}else{
				if(y[i].classList.contains('invalid')){
						//console.log("Remove class");
						y[i].classList.remove('invalid');
					}
			}
	}
	// If the valid status is true, mark the step as finished and valid:
	if (valid) {
		$("#tabvalidationerrormsg").text("")
		document.getElementsByClassName("step")[currentTab].className += " finish";
	}else{
			$("#tabvalidationerrormsg").text("Please fill in the mandatory fields.")
		}
	return valid; // return the valid status
}

function fixStepIndicator(n) {
	// This function removes the "active" class of all steps...
	var i, x = document.getElementsByClassName("step");
	for (i = 0; i < x.length; i++) {
		x[i].className = x[i].className.replace(" active", "");
	}
	//... and adds the "active" class on the current step:
	x[n].className += " active";
}



	var tenureInt = new Map();
	var schemeList = JSON.parse('${schemeslistjson}');


	//Data Picker Initialization
	$(function() {
		$("#nomineedobid","investorDOB").datepicker({
			format : 'dd/mm/yyyy',
			startDate: '-65y',
			endDate: '-21d',
			autoclose: true
			});
	/* 	$("#addressproofpxpirydateid").datepicker({
			format : 'dd/mm/yyyy',
			startDate: '1d',
			endDate: '10y'
			}); */
	});


	
	/* $(function() {
		$("#investorDOB").datepicker({
			format : 'dd/mm/yyyy',
			startDate: '-65y'
		});
	}); */
	
</script>
<script src="<c:url value="${contextcdn}/resources/js/mahindra-fd.js" />" type="text/javascript"></script>
<script src="<c:url value="${contextcdn}/resources/js/dropzone.js" />" type="text/javascript"></script>
</html>