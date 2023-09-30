
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nominee registration</title>
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>"
	rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<jsp:include page="../include/bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>



	<div class="container">
		<div class="row" style="margin-bottom: 3rem; margin-top: 3rem;">
			<div class="col-md-6 mx-auto">
				<h5 class="mb-3"><strong> Nominee verification </strong></h5>
				
					<div class="mb-3">
					<small class="text-danger mb-3">${errormsg}</small>
					</div>
				<c:if test="${nominee.mutualfundaccountexist == 'Y' }">
				<form:form class="form cf" id="nomineeverification"
					action="${pageContext.request.contextPath}/nominee-registration/mutual-funds"
					method="POST" commandName="nominee">
					<div class="md-form md-outline form-sm mt-0">
						<form:input type="text" class="form-control" id="mobile"
							style="text-transform: uppercase;" maxlength="10"
							required="required" path="mobileno" readonly="true" />
						<label for="mobile">Registered customer<sup
							class="text-danger">*</sup></label>
					</div>
					<div class="md-form md-outline form-sm mt-0">
						<form:input type="text" class="form-control" id="clientid"
							style="text-transform: uppercase;" readonly="true"
							path="nomineedetails.clientID" />
						<label for="clientid">Client ID</label>
					</div>
					<div class="md-form md-outline form-sm mt-0">
						<form:input type="text" class="form-control" id="nomineename"
							readonly="true" path="nomineedetails.nomineeName" />
						<label for="pan1">Nominee name</label>
					</div>

					<div >
						<p class="mb-0" style="font-size: .9rem;">
							Nominee Opt Out <sup class="text-danger">*</sup>
						</p>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input"
								path="param.nominationopt" id="optouty" value="Y" />
							<label class="form-check-label" for="optouty">Yes</label>
						</div>

						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input"
								path="param.nominationopt" id="optoutn" value="N" />
							<label class="form-check-label" for="optoutn">No</label>
						</div>
					</div>
					<div >
						<p class="mb-0" style="font-size: .9rem;">
							Authentication mode <sup class="text-danger">*</sup>
						</p>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input"
								path="param.nominationauthmode" id="otp" value="O" />
							<label class="form-check-label" for="otp">OTP</label>
						</div>
					</div>
					
					<div class="md-form md-outline form-sm mt-0">
						<form:input type="text" class="form-control" id="nomineepan"
							path="param.nomineepan1" maxlength="10" />
						<label for="pan1">Nominee PAN (Optional)</label>
					</div>
					
					<div class="md-form md-outline form-sm mt-0">
						<form:input type="text" class="form-control" id="nomineeguardianpan"
							path="param.nomineeguardianpan1" maxlength="10" />
						<label for="pan1">Nominee Guardian PAN (Optional)</label>
					</div>
					
					<c:if test="${nominee.holdingmode=='AS' }">
					
					<div class="md-form md-outline form-sm mt-0">
						<form:input type="text" class="form-control" id="holder2email"
							path="param.secondholderemail" maxlength="64" />
						<label for="pan1">2nd Holder Email</label>
					</div>
					
					<div class="form-group form-sm">
						<!-- <small class="text-primary">Email ID owner declaration</small> -->
						<div class="field select-mod">
							<form:select class="form-control invest" id="holder2emaildec"
								path="param.secondholderemaildeclaration">
								<form:option value="" disabled="true" selected="true">--Email Id. owner declaration--</form:option>
								<form:options items="${idbelongsto}" />
							</form:select>
							
						</div>
					</div>
					
					<div class="md-form md-outline form-sm mt-0">
						<form:input type="text" class="form-control" id="holder2mob"
							path="param.secondholdermobile" maxlength="10" />
						<label for="pan1">2nd Holder mobile</label>
					</div>
					
					<div class="form-group form-sm">
						<!-- <small class="text-primary">Email ID owner declaration</small> -->
						<div class="field select-mod">
							<form:select class="form-control invest" id="holder2mobdec"
								path="param.secondholdermobiledeclaration">
								<form:option value="" disabled="true" selected="true">--Mobile owner declaration--</form:option>
								<form:options items="${idbelongsto}" />
							</form:select>
							
						</div>
					</div>
					
					</c:if>
					
					<div style="text-align: center; margin-top: 20px;">
						<form:button type="submit"
							class="btn #00796b teal darken-2 white-text" id="confirmbtn">Submit
							</form:button>
					</div>
					
					<form:hidden path="mutualfundaccountexist"/>
				</form:form>
				</c:if>
				<c:if test="${nominee.mutualfundaccountexist == 'N' }">
					<small>Open your mutual fund account</small>
				</c:if>
				
			</div>
		</div>
	</div>


	<jsp:include page="./disclaimer.jsp"></jsp:include>
	<!-- END BSE MF  -->
	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>