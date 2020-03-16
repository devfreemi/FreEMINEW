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
<title>Fixed Deposit Investment</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="keywords" content="Fixed Desposit" />

<meta name="description" content="fixed Deposit " />

<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow" />
<meta name="bingbot" content="index, follow" />

<link rel="canonical"
	href=" https://www.freemi.in/products/fixed-deposit" />
<jsp:include page="/page/include/bootstrap.jsp"></jsp:include>
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>"
	rel="preload" as="style" onload="this.rel='stylesheet'">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css"
	rel="preload" as="style" onload="this.rel='stylesheet'">
<script
	src="<c:url value="${contextcdn}/resources/js/mahindra-fd.js" />"
	type="text/javascript" defer="defer"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
	crossorigin="anonymous" async="true"></script>

</head>
<body>
	<jsp:include page="/page/include/header.jsp"></jsp:include>
	<div class="container-fluid" style="margin-bottom: 5rem;">
		<section style="margin-top: 5rem;">
			<div class="row" style="margin: auto;">
				<div class="col-md-7 col-lg-7" style="margin: auto;box-shadow: 2px 2px 7px 0px #bbb3b3;">
					<div class="row" style="background: #2ff1e8de;">
						<div class="col-md-4 col-lg-4 d-none d-sm-block" style="margin: auto;text-align: center;">
								<span style="text-align: center;">
								<img src="<c:url value="${contextcdn}/resources/images/invest/fd-puchase-51.svg"/>"class="img-fluid animated slideInLeft" style="height: 5rem;">
								</span>	
						</div>
						<div class="col-md-8 col-lg-8 " style="background: white;border: 1px solid #ececec;">
							<c:choose>
								<c:when test="${VIEW_STATUS == 'PAYLINK' }">
									<h3 class="text-success" style="text-align: center; margin-top: .5rem;font-weight: 500;">Your order has been
										processed successfully</h3>

								</c:when>
								<c:when test="${VIEW_STATUS == 'PAYMENT_CALLBACK_STATUS' }">
									<h3 class="text-success" style="text-align: center;margin-top: .5rem;font-weight: 500;">Your payment has been
										processed</h3>
								</c:when>
								<c:when test="${VIEW_STATUS == 'ERROR' }">
									<h3 class="text-danger" style="text-align: center;margin-top: .5rem;font-weight: 500;">Your request Declined!!</h3>
								</c:when>
								<c:when test="${VIEW_STATUS == 'EXCEPTION' }">
									<h3 class="text-danger" style="text-align: center;margin-top: .5rem;font-weight: 500;">Error processing your
										request!!</h3>
								</c:when>
								<c:otherwise>
									<h3 class="text-muted">Unknown Request</h3>
								</c:otherwise>

							</c:choose>

							<div class="table-responsive text-nowrap animated fadeIn"
								style="overflow-x: auto;">
								<table class="table">
									<!-- <thead>
										<tr>
											<th scope="col"></th>
											<th scope="col">Status</th>
										</tr>
									</thead> -->
									<tbody>
										<tr>
											<th scope="row" ><label class="text-secondary">Transaction ID</label></th>
											<td>${transactionid }</td>
										</tr>
										<tr>
											<th scope="row" ><label class="text-secondary"> Category </label></th>
											<td>Fixed Deposit</td>
										</tr>
										<tr>
											<th scope="row" ><label class="text-secondary">Payment Complete </label></th>
											<td>${paymentcomplete }</td>
										</tr>
									</tbody>
								</table>


							</div>

							<c:if test="${VIEW_STATUS == 'PAYLINK' }">
								<div style="text-align: center;">
									<a href="${paymentLink}"><button
											class="btn btn-sm #f4511e deep-orange darken-1 white-text">COMPLETE YOUR PAYMENT</button></a>
								</div>
							</c:if>
						</div>
					</div>




				</div>

				<%-- <div class="col-md-5 col-lg-5 d-none d-sm-block">
					<img
						src="<c:url value="${contextcdn}/resources/images/invest/fd-1.png"/>"
						class="img-fluid animated slideInRight">
				</div> --%>

			</div>
		</section>

	</div>

	<jsp:include page="/page/include/sub-footer.jsp"></jsp:include>
	<jsp:include page="/page/include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	//Data Picker Initialization
	$(function() {
		$("#investorDOB").datepicker({
			format : 'dd/mm/yyyy'
		});
	});
</script>

<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result).width(150).height(200);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

</html>