<!--Navbar -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="https://resources.freemi.in/loans/resources/css/styling-header.css"/>"  rel="preload" as="style" onload="this.rel='stylesheet'" type="text/css">
<c:set var="contextPath" value="/" />
<style>
.new-header{
background: #000046;  /* fallback for old browsers */
background: -webkit-linear-gradient(to left, #1CB5E0, #000046);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to left, #1CB5E0, #000046); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
</style>
<nav class="mb-1 navbar navbar-expand-lg navbar-dark new-header">
<div class="container-fluid">
  <a class="navbar-brand" href="/" style="padding: 0px;"><img
			src="https://resources.freemi.in/loans/resources/images/freemi.png"
			class="img-fluid" alt="FreEMI logo" > <span class="beta-styling">
		</span></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-4"
    aria-controls="navbarSupportedContent-4" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent-4">
    <ul class="navbar-nav ml-auto">
     <li class="nav-item dropdown" style="display: -webkit-inline-box;">
      
        <a class="nav-link dropdown-toggle" id="link1" data-toggle="dropdown" aria-haspopup="true"
          aria-expanded="false">Loans
        </a>
        <div class="dropdown-menu dropdown-secondary" aria-labelledby="link1">
          <a class="dropdown-item" href="https://www.freemi.in/personal-loan/">Personal Loan</a>
          <a class="dropdown-item" href="https://www.freemi.in/business-loan/">Business Loan</a>
          <a class="dropdown-item" href="https://www.freemi.in/home-loan/">Home Loan</a>
        </div>
      </li>
      <li class="nav-item dropdown" style="display: -webkit-inline-box;">
     
        <a class="nav-link dropdown-toggle" id="link2" data-toggle="dropdown" aria-haspopup="true"
          aria-expanded="false">Mutual Funds
        </a>
        <div class="dropdown-menu dropdown-secondary" aria-labelledby="link2">
         <!--  <a class="dropdown-item" href="/products/mutual-funds/top-performing">Top Performing Funds</a> -->
         <a class="dropdown-item" href="https://www.freemi.in/elss/elss-mutual-fund/">ELSS Tax Saver</a>
          <a class="dropdown-item" href="https://www.freemi.in/mutual-funds/mutual-fund-explorer/">Mutual Funds Explorer</a>
          <a class="dropdown-item" href="https://www.freemi.in/mutual-fund-registry/">Mutual Fund Registry</a>
        </div>
      </li>
       <li class="nav-item dropdown" style="display: -webkit-inline-box;">
     
        <a class="nav-link dropdown-toggle" id="link11" data-toggle="dropdown" aria-haspopup="true"
          aria-expanded="false">Savings
        </a>
        <div class="dropdown-menu dropdown-secondary" aria-labelledby="link11">
          <a class="dropdown-item" href="https://www.freemi.in/fixed-deposit/mahindra-fixed-deposit/">Mahindra Finance Fixed Deposit</a>
        </div>
      </li>
       <li class="nav-item dropdown" style="display: -webkit-inline-box;">
    
        <a class="nav-link dropdown-toggle" id="link3" data-toggle="dropdown" aria-haspopup="true"
          aria-expanded="false">Credit Cards
        </a>
        <div class="dropdown-menu dropdown-secondary" aria-labelledby="link3">
        <a class="dropdown-item" href="https://www.freemi.in/icici-bank/icici-bank-credit-card/">Apply for ICICI Credit Card</a>
          <a class="dropdown-item" href="https://www.freemi.in/credit-card/">Apply for Credit Card</a>
        </div>
      </li>
      <!-- <li class="nav-item dropdown" style="display: -webkit-inline-box;">
     
        <a class="nav-link dropdown-toggle" id="link4" data-toggle="dropdown" aria-haspopup="true"
          aria-expanded="false">Insurance
        </a>
        <div class="dropdown-menu dropdown-secondary" aria-labelledby="link4">
          <a class="dropdown-item" href="https://www.freemi.in/two-wheeler-insurance/">Two Wheeler Insurance</a>
          <a class="dropdown-item" href="https://www.freemi.in/health-insurance/">Health Insurance</a>
          <a class="dropdown-item" href="https://www.freemi.in/car-insurance/">Car Insurance</a>
        </div>
      </li> -->
       <li class="nav-item dropdown" style="display: -webkit-inline-box;">
     
        <a class="nav-link dropdown-toggle" id="link5" data-toggle="dropdown" aria-haspopup="true"
          aria-expanded="false">Calculators
        </a>
        <div class="dropdown-menu dropdown-secondary" aria-labelledby="link5">
          <a class="dropdown-item" href="https://www.freemi.in/sip-calculator/">SIP Calculator</a>
          <a class="dropdown-item" href="https://www.freemi.in/emi-calculator/">EMI Calculator</a>
          <a class="dropdown-item" href="https://www.freemi.in/marriage-calculator/">Marriage Calculator</a>
          <a class="dropdown-item" href="https://www.freemi.in/retirement-planning-calculator/">Retirement Calculator</a>
          <a class="dropdown-item" href="https://www.freemi.in/products/tax-calculator">Tax Calculator</a>
        </div>
      </li>
      
      <c:choose>
			<c:when test="${not empty loggedSession }">
				<li class="nav-item dropdown" style="display: -webkit-inline-box;">
        <a class="nav-link dropdown-toggle" id="link6" data-toggle="dropdown" aria-haspopup="true"
          aria-expanded="false">
          <i class="fas fa-user"></i> <c:out value="${loggedSession }"></c:out> </a>
        <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="link6">
          <a class="dropdown-item" href="/products/my-dashboard"><i
									class="fas fa-archive" style="color: rgb(84, 84, 236);"></i> My
									Dash-board</a>
          <a class="dropdown-item" href="/products/profile"><i
									class="fas fa-user"></i> Profile</a>
          <a class="dropdown-item" href="/products/logout" onclick="clearSessionData()"><i
									class="fas fa-power-off" style="color: rgb(238, 56, 56)"></i>
									Log out</a>
        </div>
      </li>
			</c:when>
			<c:otherwise>
			<li class="nav-item signup-styling" style="display:-webkit-inline-box;">
			<i class="fas fa-user-plus d-block d-sm-none" style="margin-top: 0.55rem;"></i>
        			<a class="nav-link" href="/products/register"> <span class="">
									Sign up
							</span></a>
     		</li>
     		<li class="nav-item login-styling" style="display:-webkit-inline-box;">
     		<i class="fas fa-sign-in-alt d-block d-sm-none" style="margin-top: 0.55rem;"></i>
        			<a class="nav-link" href="/products/login"><span class="">
									Log In 
							</span></a>
     		</li>
			</c:otherwise>
	</c:choose>
     
      
    </ul>
  </div>
  </div>
</nav>
<!--/.Navbar -->