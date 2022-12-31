<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mutual Fund registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="keywords" content="Fixed Desposit" />
<meta name="description" content="fixed Deposit " />
<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow" />
<meta name="bingbot" content="index, follow" />

<jsp:include page="/page/include/bootstrap.jsp"></jsp:include>

<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>" rel="preload" as="style" onload="this.rel='stylesheet'">
<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" rel="preload" as="style" onload="this.rel='stylesheet'">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<link href="<c:url value="${contextcdn}/resources/css/bseinvestv2.css"/>" rel="preload" as="style" onload="this.rel='stylesheet'">
<link href="<c:url value="${contextcdn}/resources/css/bseinvestmentformv2.css"/>" rel="stylesheet">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css"
	rel="stylesheet" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
</head>
<body>
	<jsp:include page="/page/include/header.jsp"></jsp:include>
	<div class="container-fluid" style="margin-bottom: 5rem;">
		<section>
			<div class="row" style="padding-top: 2rem;">
				<div class="col-md-6 col-lg-6 mx-auto border rounded">
					<jsp:include page="bse-starmf-form-detailed-v2.jsp"></jsp:include>
				</div>

				<%-- <div class="col-md-5 col-lg-5 d-none d-sm-block">
					<img
						src="<c:url value="${contextcdn}/resources/images/invest/fd-1.png"/>"
						class="img-fluid animated slideInRight">
				</div> --%>

			</div>
		</section>

	</div>
	<jsp:include page="/page/include/mobileverify.jsp"></jsp:include>
	<jsp:include page="/page/include/emailverify.jsp"></jsp:include>
	<jsp:include page="/page/include/sub-footer.jsp"></jsp:include>
	<jsp:include page="/page/include/footer.jsp"></jsp:include>
</body>

<script type="text/javascript">
$(document).ready(function() {
 
	$('#addState').select2({
		placeholder : {
			id : '-1', // the value of the option
			text : 'Select State',
			allowClear: true
		}
	});
	
	

	$('#address_city').select2({
		placeholder : {
			id : '-1', // the value of the option
			text : 'Select City',
			
		},
		tags: true
	});

	$('#pinCode').select2({
		placeholder : {
			id : '-1', // the value of the option
			text : 'Select Pincode',
			
		},
		tags: true
	});

	/*
	 $('#bankName').select2({
			placeholder : {
				id : '-1', // the value of the option
				text : 'Select Bank'
			}
		});
	 */
	$('#countryOfBirth').select2({
		placeholder : {
			id : '-1', // the value of the option
			text : 'Country of birth'
		}
	});

	 $('#addState').find(':selected');
	$('#address_city').find(':selected');
	$('#pinCode').find(':selected');
	$('#countryOfBirth').find(':selected');
	
});

</script>
<script src="<c:url value="${contextcdn}/resources/js/bseinvestv2.js" />"
	defer="defer"></script>
<script src="<c:url value="${contextcdn}/resources/js/investmentv2.js" />" defer="defer"></script>
<script src="<c:url value="${contextcdn}/resources/js/signaturepanelv2.js" />" async="true"></script>
</html>