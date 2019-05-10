<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Un-subscribe email</title>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<!-- <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" /> -->
<meta http-equiv="pragma" content="no-cache" />
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body style="background:background: #f0f8ffab;;">
	<div class="container">
		<div class="row" style="margin-top: 3rem;">
			<div class="col-md-10 col-lg-10"
				style="margin: auto; border: 1px solid #a2a7a7; padding: 2rem;background: white;">
				<div>
					<div style="text-align: center;">
						<h3 style="font-weight: 600;">EMAIL ID</h3>
						<h5 style="font-weight: 500;">is subscribed to our mailing list(s).</h5>
					</div>
					<hr>
					<div>
						<form:form method="POST"
							action="${pageContext.request.contextPath}/unsubscribe.do"
							commandName="unsubscribeform">
							
							<div style="text-align: center;margin-bottom: 3rem;">
								<h3 style="color: #ff3f12;font-weight: 600;">Unsubscribe from our mailing list</h3>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">To help us improve our services, we would be grateful if you could tell us why:</label>
								<form:select class="custom-select form-control-sm" id="unsubreason"
									path="reasonCode" onchange="reason();">
									<form:option value="">Please select reason </form:option>
									<form:option value="1">Your e-mails are not relevant to me</form:option>
									<form:option value="2">Your e-mails are too frequent</form:option>
									<form:option value="3">I do not remember signing up for this</form:option>
									<form:option value="4">I no longer want to receive these e-mails</form:option>
									<form:option value="5">The e-mails are spam and should be reported</form:option>
									<form:option value="6">Others</form:option>

								</form:select>
								<small id="emailHelp" class="form-text text-muted">We'll
									never share your email with anyone else.</small>
							</div>

							<div class="form-group reasonbox" style="display: none;">
								<label for="exampleInputEmail1">Reasons (200 characters)</label>
								<div class="otherreasonbox">
									<form:textarea style="width: 100%; max-height: 200px;" path="comment" />
								</div>
							</div>

							<div class="form-group" style="text-align: center;">
								<button type="submit" class="btn btn-primary btn-sm">
									<span class="button_work">Unsubscribe
									</span>
								</button>
							</div>


						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function reason(){
	console.log($("#unsubreason").val());
	if($("#unsubreason").val()=='6'){
		$(".reasonbox").show();
		
	}else{
		$(".reasonbox").hide();
	}
}
</script>
</html>