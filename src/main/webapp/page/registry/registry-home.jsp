<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registry</title>
<jsp:include page="../include/bootstrap.jsp"></jsp:include>
<style type="text/css">
hr {
	border: 0;
	height: 1px;
	background-image: linear-gradient(to right, rgba(0, 0, 0, 0),
		rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
}

.btn-primary{
border-radius: 5px;
}

.card-title{
    color: #ff7e40;
   }
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>

	<section>

		<div class="row">
			<div class="col-md-6 col-lg-6" style="margin: auto;">

				<ul class="row"
					style="list-style: none; padding-left: 0; margin: 0 0rem;">
					<li class="col-md-6 col-lg-6 mt-4 mb-4">
						<!-- Card -->
						<div class="card">

							<!-- Card image -->
							<div class="view overlay pt-2">
								<img
									src="<c:url value="${contextPath}/resources/images/registry/birthday.svg"/>"
									class="img-fluid;" style="height: 3rem; margin: auto;" /> <a>
									<div class="mask rgba-white-slight"></div>
								</a>
							</div>
							<hr>
							<!-- Card content -->
							<div class="card-body">

								<!-- Title -->
								<h4 class="card-title">Birthday wish</h4>
								<!-- Text -->
								<p class="card-text">Need to save for upcoming birthday celebration?</p>
								<!-- Plan this wish -->
								<a href="#" class="btn btn-sm btn-primary">Plan this wish</a>

							</div>

						</div> <!-- Card -->
					</li>
					<li class="col-md-6 col-lg-6 mt-4 mb-4">
						<!-- Card -->
						<div class="card">

							<!-- Card image -->
							<div class="view overlay pt-2">
								<img
									src="<c:url value="${contextPath}/resources/images/registry/registry_an.png"/>"
									class="img-fluid;" style="height: 3rem; margin: auto;" /> <a>
									<div class="mask rgba-white-slight"></div>
								</a>
							</div>
							<hr>
							<!-- Card content -->
							<div class="card-body">

								<!-- Title -->
								<h4 class="card-title">Anniversary Registry</h4>
								<!-- Text -->
								<p class="card-text">Need to save for upcoming birthday
									party? Plan</p>
								<!-- Plan this wish -->
								<a href="#" class="btn btn-sm btn-primary">Plan this wish</a>
							</div>
						</div> <!-- Card -->
					</li>
					<li class="col-md-6 col-lg-6 mt-4 mb-4">
						<!-- Card -->
						<div class="card">

							<!-- Card image -->
							<div class="view overlay pt-2">
								<img
									src="<c:url value="${contextPath}/resources/images/registry/registry_m.png"/>"
									class="img-fluid;" style="height: 3rem; margin: auto;" /> <a>
									<div class="mask rgba-white-slight"></div>
								</a>
							</div>
							<hr>
							<!-- Card content -->
							<div class="card-body">

								<!-- Title -->
								<h4 class="card-title">Wedding Gift</h4>
								<!-- Text -->
								<p class="card-text">Need to save for upcoming birthday
									party? Plan</p>
								<!-- Plan this wish -->
								<a href="#" class="btn btn-sm btn-primary">Plan this wish</a>

							</div>
							<!-- Card -->
						</div>
					</li>
					<li class="col-md-6 col-lg-6 mt-4 mb-4">
						<!-- Card -->
						<div class="card">

							<!-- Card image -->
							<div class="view overlay pt-2">
								<img
									src="<c:url value="${contextPath}/resources/images/registry/registry_st.png"/>"
									class="img-fluid;" style="height: 3rem; margin: auto;" /> <a>
									<div class="mask rgba-white-slight"></div>
								</a>
							</div>
							<hr>
							<!-- Card content -->
							<div class="card-body">

								<!-- Title -->
								<h4 class="card-title">Tax Saving</h4>
								<!-- Text -->
								<p class="card-text">Need to save for upcoming birthday
									party? Plan</p>
								<!-- Plan this wish -->
								<a href="#" class="btn btn-sm btn-primary">Plan this wish</a>
							</div>
						</div> <!-- Card -->
					</li>
					
					<li class="col-md-6 col-lg-6 mt-4 mb-4">
						<!-- Card -->
						<div class="card">

							<!-- Card image -->
							<div class="view overlay pt-2">
								<img
									src="<c:url value="${contextPath}/resources/images/registry/party.png"/>"
									class="img-fluid;" style="height: 3rem; margin: auto;" /> <a>
									<div class="mask rgba-white-slight"></div>
								</a>
							</div>
							<hr>
							<!-- Card content -->
							<div class="card-body">

								<!-- Title -->
								<h4 class="card-title">Party wish</h4>
								<!-- Text -->
								<p class="card-text">Need to save for upcoming birthday
									party? Plan</p>
								<!-- Plan this wish -->
								<a href="#" class="btn btn-sm btn-primary">Plan this wish</a>
							</div>
						</div> <!-- Card -->
					</li>

				</ul>

			</div>
		</div>
	</section>

	<jsp:include page="../include/sub-footer.jsp"></jsp:include>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>