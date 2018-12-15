<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="/" />
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-bg-dark"
	style="background-color: #18445b !important">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}"> <img
			src="<c:url value="${contextcdn}/resources/images/freemi.png"/>"
			class="img-fluid" alt="FreEMI logo" style="height: 45px;"> <span
			style="font-size: 12px; margin-left: -10px;"> <sub> <i>beta</i>
			</sub>
		</span>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link home"
					href="${contextPath}"> <span> <i class="fa fa-home"
							style="font-size: xx-large; color: blanchedalmond;"
							aria-hidden="true"></i>
					</span>
				</a></li>

				<li class="nav-item dropdown" style="padding-top: 7px;"><img
					src="<c:url value="${contextcdn}/resources/images/freemi_icon1.png"/>"
					alt="Loans icon" width="30" height="5" style="float: left;">
					<a class="nav-link dropdown-toggle" style="display: initial;"
					href="/loans/"
					id="navbarDropdownLoansLink" data-hover="dropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Loans
				</a>
					<div class="dropdown-menu custom-dropdown"
						aria-labelledby="navbarDropdownLoansLink">
						<a class="dropdown-item" href="/products/loans/">Home</a> <a
							class="dropdown-item"
							href="/loans/">Personal
							loan</a> <a class="dropdown-item"
							href="/loans/personal-loan-kolkata">Personal
							loan in Kolkata</a>
					</div></li>
				<li class="nav-item dropdown" style="padding-top: 7px;"><img
					src="<c:url value="${contextcdn}/resources/images/fsecure.png"/>"
					alt="Insurace" width="30" height="5" style="float: left;"> <a
					class="nav-link dropdown-toggle" style="display: initial;"
					href="/insurance/"
					id="navbarDropdownInsuranceLink" data-hover="dropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Insurance
				</a>
					<div class="dropdown-menu custom-dropdown"
						aria-labelledby="navbarDropdownInsuranceLink">
						<a class="dropdown-item"
							href="/insurance/">Home</a> <a
							class="dropdown-item"
							href="/health-insurance/">Health
							Insurance</a> <a class="dropdown-item"
							href="/car-insurance/">Car
							Insurance</a>
					</div></li>
				<li class="nav-item dropdown" style="padding-top: 7px;"><img
					src="${pageContext.request.contextPath}/resources/images/cards/credit-card-ico.svg"
					class="img-fluid" style="height: 32px;" alt="Credit Card"><a
					class="nav-link dropdown-toggle" style="display: initial;"
					href="${pageContext.request.contextPath}/insurance/"
					id="navbarDropdownInsuranceLink" data-hover="dropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Cards
				</a>
					<div class="dropdown-menu custom-dropdown"
						aria-labelledby="navbarDropdownInsuranceLink">
						<a class="dropdown-item"
							href="/credit-card/">Apply for Credit Card</a>
					</div></li>
				<li class="nav-item" style="padding-top: 7px;">
					<!-- 	<div class="nav-div"> --> <img
					src="<c:url value="${contextcdn}/resources/images/registry.png"/>"
					alt="Registry product" class="img-fluid" width="30" height="5"
					style="float: left;"> <a class="nav-link"
					href="mutual-funds/"
					style="margin-left: 30px; padding: 0;margin-right: 5px;">Mutual Funds<!-- <br> <span
							class="header_subname">A product of Mutual Funds</span> -->
				</a> <!-- </div> -->
				</li>

				<li class="nav-item dropdown" style="padding-top: 7px;"><i
					class="fas fa-calculator" style="color: darkorange;"
					aria-hidden="true"></i> <a class="nav-link dropdown-toggle"
					style="display: initial;" href="/products/tax-calculator"
					id="navbarDropdownCalLink" data-hover="dropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Calculators
				</a>
					<div class="dropdown-menu custom-dropdown"
						aria-labelledby="navbarDropdownCalLink">
						<a class="dropdown-item" href="/products/tax-calculator">Tax
							Calculator</a> <a class="dropdown-item"
							href="/mutual-funds/sip-calculator">SIP
							Calculator</a> <a class="dropdown-item"
							href="/loans/emi-calculator">EMI
							Calculator</a>
					</div></li>

				<c:choose>
					<c:when test="${not empty sessionScope.loggedSession }">
						<li class="nav-item dropdown user"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownLogLink" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <img
								src="<c:url value="${contextcdn}/resources/images/avatar.png"/>"
								class="img-fluid" style="height: 1.75em; margin-top: -5px;">
								<%-- <span> Welcome ${loggedInUser }</span> --%> <span>Welcome
									<c:out value="${sessionScope.loggedSession }"></c:out>
							</span>
						</a>
							<div class="dropdown-menu"
								aria-labelledby="navbarDropdownLogLink">
								<a class="dropdown-item" href="/products/my-dashboard"> <i
									class="fas fa-archive" style="color: rgb(84, 84, 236);"></i> My
									Dashboard
								</a> <a class="dropdown-item" href="/products/profile"> <i
									class="fas fa-user"></i> Profile
								</a> <a class="dropdown-item" href="/products/logout"> <i
									class="fas fa-power-off" style="color: rgb(238, 56, 56)"></i>
									Log out

								</a>
							</div></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="/products/register"> <span style="color: coral;">
									Sign up <i class="fas fa-user-plus"></i>
							</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/products/login"> <span style="color: coral;">
									Log In <i class="fas fa-sign-in-alt"></i>
							</span>
						</a></li>

					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</div>
</nav>