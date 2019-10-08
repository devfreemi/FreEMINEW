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
<body style="background:background: #f0f8ffab;">
	<div class="container">
		<div class="row" style="margin-top: 3rem;">
			<div class="col-md-10 col-lg-10"
				style="margin: auto; border: 1px solid #a2a7a7; padding: 2rem;background: white;">
				<div>
					<div style="text-align: center;">
						<h3 style="font-weight: 600;color: #ff3f12;">Done!</h3>
					</div>
					<hr>
					<div style="text-align: center;">
						<h4 style="font-weight: 500;">${emailid}</h4>
						<p style="font-weight: 500; font-size: 16px;">has been successfully unsubscribed to our mailing list(s)</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>