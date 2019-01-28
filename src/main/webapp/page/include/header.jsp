<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="${contextcdn}/resources/css/styling-header.css"/>" rel="stylesheet" type="text/css">

<c:set var="contextPath" value="/" />
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-bg-dark">
	<div class="container-fluid">
	
		<a class="navbar-brand" href="${contextPath}"> <img
			src="<c:url value="${contextcdn}/resources/images/freemi.png"/>"
			class="img-fluid logo-styling" alt="FreEMI logo" > <span class="beta-styling"> <sub> <i>beta</i>
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
				<li class="nav-item dropdown">
				<img src="<c:url value="${contextcdn}/resources/images/freemi_icon1.png"/>"
					alt="Loans icon" class="img-fluid icon-for-mobileview">
					<a class="nav-link dropdown-toggle styling-header"
					href="/loans/"
					id="navbarDropdownLoansLink" data-hover="dropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Loans
				</a>
					<div class="dropdown-menu custom-dropdown showdrop"
						aria-labelledby="navbarDropdownLoansLink">
						<!-- <a class="dropdown-item" href="/products/loans/">Loans</a> --> 
						<a
							class="dropdown-item navbar-dropdown-hover-effect "
							href="/personal-loan/">Personal
							loan</a>
							 <a
							class="dropdown-item navbar-dropdown-hover-effect"
							href="/business-loan/">Business
							loan</a>
							 <a
							class="dropdown-item navbar-dropdown-hover-effect"
							href="/home-loan/">Home
							loan</a>
						
						<%-- <a class="dropdown-item"
							href="${pageContext.request.contextPath}/loans/personal-loan-kolkata">Personal
							loan in Kolkata</a> --%>
					</div></li>
				<li class="nav-item dropdown">
				<img src="<c:url value="${contextcdn}/resources/images/fsecure.png"/>"
					alt="Insurace" class="img-fluid icon-for-mobileview"> <a
					class="nav-link dropdown-toggle styling-header" href="/insurance/"
					id="navbarDropdownInsuranceLink" data-hover="dropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Insurance
				</a>
					<div class="dropdown-menu custom-dropdown showdrop"
						aria-labelledby="navbarDropdownInsuranceLink">
						<a class="dropdown-item navbar-dropdown-hover-effect"
							href="/insurance/">Insurance</a>
						<a class="dropdown-item navbar-dropdown-hover-effect"
							href="/health-insurance/">Health
							Insurance</a> <a class="dropdown-item navbar-dropdown-hover-effect"
							href="/car-insurance/">Car
							Insurance</a>
					</div></li>

				<li class="nav-item dropdown"> 
				<img src="${contextcdn}/resources/images/cards/credit-card-ico.svg"
					class="img-fluid icon-for-mobileview"  alt="Credit Card"><a
					class="nav-link dropdown-toggle styling-header " href="/credit-card/"
					id="navbarDropdownInsuranceLink" data-hover="dropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Cards
				</a>
					<div class="dropdown-menu custom-dropdown showdrop"
						aria-labelledby="navbarDropdownInsuranceLink">
						<a class="dropdown-item navbar-dropdown-hover-effect"
							href="/credit-card/">Apply
							for Credit Card</a>
					</div></li>

				<li class="nav-item">
					<!-- 	<div class="nav-div"> --> <img
					src="<c:url value="${contextcdn}/resources/images/registry.png"/>"
					alt="Registry product" class="img-fluid icon-for-mobileview"> 
					<a class="nav-link nav-link-styling styling-header"
					href="/mutual-funds/">Mutual Fund<!-- <br> <span
							class="header_subname">A product of Mutual Funds</span> -->
				</a> <!-- </div> -->
				</li>

				<li class="nav-item dropdown">
				<img src="<c:url value="${contextcdn}/resources/images/calculator.png"/>"
					class="img-fluid icon-for-mobileview"  alt="Calculator"> 
					<a class="nav-link dropdown-toggle styling-header" href="/products/tax-calculator"
					id="navbarDropdownCalLink" data-hover="dropdown"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Calculators
				</a>
					<div class="dropdown-menu custom-dropdown showdrop"
						aria-labelledby="navbarDropdownCalLink">
						<a class="dropdown-item navbar-dropdown-hover-effect" href="/products/tax-calculator">Tax
							Calculator</a> <a class="dropdown-item navbar-dropdown-hover-effect"
							href="/mutual-funds/sip-calculator">SIP
							Calculator</a> <a class="dropdown-item navbar-dropdown-hover-effect"
							href="/loans/emi-calculator">EMI
							Calculator</a>
					</div></li>

				<c:choose>
					<c:when test="${not empty sessionScope.loggedSession }">
						<li class="nav-item dropdown user"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownLogLink" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> 
							<img src="<c:url value="${contextcdn}/resources/images/avatar.png"/>"
								class="img-fluid icon-for-mobileview">
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
							href="/products/register"> <span class="signup-styling">
									<i class="fas fa-user-plus icon-for-mobileview"></i> <strong>Sign up </strong>
							</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/products/login"> <span class="login-styling">
									<i class="fas fa-sign-in-alt icon-for-mobileview"></i><strong>Log In </strong> 
							</span>
						</a></li>

					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</div>
</nav>