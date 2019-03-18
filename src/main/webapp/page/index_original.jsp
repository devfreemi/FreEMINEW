<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="keywords" content="freemi, mutual fund, investment,SIP,SIP Calculator, Tax Calculator, Loan Canculator,Personal Loan, Home Loan Credit Card,Equity Mutual Find,Mutual Fund, Health Insurance, Life Insurance, Personal Finance, Expenses Management, Car Insurance, Mutual Fund SIP, Best Mutual Fund, 3D Insurance, and Accidental Insurance" />
<meta name="Copyright" content="Copyright 2018 @ freei.in" />
<meta name="author" content="https://www.freemi.in"/>
<meta name="title" content="FreEMI - Live life Digital" />
<meta name="description" content="1. Create a Mutual Fund investment account instant with E-KYC.  2. Free E-KYC can make your expenses managemnt better by investment in Mutual Fund. 3. Get a Health Insurance @ Rs. 500 per year per person by Registering into FreEMI.  4. Secure your self and family from Dengue, Malaria, Typhoid Hospitalisation Cover. Get Rs. 25000 benefit instantly, login to FreEMI 5. An Insurance can manage Three Loan EMI of yours if you met with an accident. Check-Out plans 6. Get best Loan deal, sign up and check eligibility. 7. A right personal loan can solve your financial crisis, Sign Up to avail it. 8. Mutual Fund Calculator 9. SIP calculator 10. Loan EMI Calculatotor    "/>

 <link href="<c:url value="${contextPath}/resources/css/pagination-contents.css"/>" rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/funds.css"/>" rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/homePage.css"/>" rel="stylesheet">
 <link href="<c:url value="${contextPath}/resources/css/animate.css"/>" type="text/css" rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>" rel="stylesheet">

<link href="<c:url value="${contextPath}/resources/css/pace-theme.css"/>" rel="stylesheet">

<jsp:include page="include/bootstrap.jsp"></jsp:include>
<title>Home Page</title>

<!-- <script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script> -->
  <script src="<c:url value="${contextPath}/resources/js/common.js" />"></script>
  <script src="<c:url value="${contextPath}/resources/js/home.js" />"></script>
  <script src="<c:url value="${contextPath}/resources/js/pace.min.js" />"></script>

<script type="text/javascript">
function formOnLoad(){
	var isuserpresent = <%=session.getAttribute("userid")%>
	var campaignsubmitted = <%=session.getAttribute("campaignsubmitted")%>
		//console.log("user logged in");
	if((isuserpresent == null && campaignsubmitted == null) ){
		setTimeout(call, 5000);
	}
		
}

function call() {
	//console.log("Called popup")
	{ $('#exampleModalCenter').modal('show'); }
}


</script>

</head>

<body class="back_set" onload="formOnLoad();">
<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
<!-- <div class="loader"> -->
<div class='thetop'></div>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container freemi_container">

    <div id="carouselExampleIndicators" class="carousel slide carousel_style" data-ride="carousel" style="margin-top: -18px;">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
                <a href="${contextPath}/loans">
                    <img class="d-block img-fluid" src="<c:url value="${contextPath}/resources/images/h1.jpg"/>" alt="First slide">
                </a>
            </div>
            <div class="carousel-item">
                <a href="${contextPath}/fsecure">
                    <img class="d-block img-fluid" src="<c:url value="${contextPath}/resources/images/h2.jpg"/>" alt="Second slide">
                </a>
            </div>
            <div class="carousel-item">
                <a href="${contextPath}/registry-mutual-funds">
                    <img class="d-block img-fluid" src="<c:url value="${contextPath}/resources/images/h3.jpg"/>" alt="Third slide">
                </a>
            </div>
            <div class="carousel-item">
                <img class="d-block img-fluid" src="<c:url value="${contextPath}/resources/images/h4.jpg"/>" alt="Fourth slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <!-- </div> -->
    <div class="call-action1">
        <div class="row">
            <div class="col-md-4 col-lg-4" style="color: azure;">
                <h5 class="animated bounceIn">Make your wishes come true!</h5>
                <span>Invest for your dreams.</span>
            </div>
            <div class="col-md-4 col-lg-4"></div>
            <div class="col-md-4 col-lg-4">
                    <a href="${contextPath}/register" style="text-decoration: none;"><button class="btn btn-lg btn-danger"  ><span style="margin-right: 10px;">Register Now </span> <i class="fas fa-caret-right"></i></button></a>
            </div>
        </div>
            
    </div>

    <!-- <div class="container"> -->
    <div class="strike-line">
        <span>
            <img src="<c:url value="${contextPath}/resources/images/10.png"/>" style="margin-bottom: -40px;">
        </span>
    </div>
    <!-- </div> -->

    <!-- <div class="container"> -->
    <div class="row animated fadeInUp">
        <div class="col-sm-4 col-md-4 col-lg-4" style="margin: -1px;">
            <div>
                <img src="<c:url value="${contextPath}/resources/images/plan1.jpg"/>" class="img-fluid plan_image">
                <h2 class="us_style">Technology</h2>
            </div>
            <div class="plan_content">
                <p>
                    FreEMI.in is most cutting edge fin-tech platform based on proprietary algorithms. We built a first of its kind product platform
                      with efficient technology, which is intelligent too.
                </p>
            </div>
        </div>
        <div class="col-sm-4 col-md-4 col-lg-4">
            <div>
                <img src="<c:url value="${contextPath}/resources/images/plan2.jpg"/>" class="img-fluid plan_image">
                <h2 class="us_style">Product</h2>
            </div>
            <div class="plan_content">
                <p>
                    FreEMI is an innovative product platform. With
                    <strong>unique, affordable &amp; simplified</strong> product  category makes it natural digital marketplace  to meet
                    all your needs. All product category  have inbuilt efficiency, intelligence in simplified  manner. We
                    tried to simplify financial decision  making with digital execution.
                </p>
            </div>
        </div>
        <div class="col-sm-4 col-md-4 col-lg-4">
            <div>
                <img src="<c:url value="${contextPath}/resources/images/plan3.png"/>" class="img-fluid plan_image">
                <h2 class="us_style">Reach</h2>
            </div>
            <div class="plan_content">
                <p>
                    We believe digital technology with physical  touch or you can call our presence as <strong>physi-tal</strong>.  FreEMI has strong POS all across
                    geography. We partner with people who believes on digital. We believe in collaboration and grow together.
                </p>
            </div>
        </div>
    </div>
    <!-- </div> -->

    <div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
        <!-- <a routerLink="/register">Join Us</a> -->
        <a href="${contextPath}/register" style="text-decoration: none;"><button class="btn btn-lg btn-primary" ><h5 style="margin: 0px 15px;">Join Us</h5></button></a>
    </div>


    <div class="row why_design animated fadeInUp" style="margin-left: -15px; margin-right: -15px;">
        <div class="col-12 how_heading">
            <span>How it works?</span>
        </div>
        <div class="col-6 col-md-4 col-lg-4">
            <div class="how_design">
                <img src="<c:url value="${contextPath}/resources/images/why_img11.png"/>" class="img-fluid img_size">
                
                <p>
                    <span>Make a wish or tell us your needs</span>
                </p>
            </div>
        </div>
        <div class="col-6 col-md-4 col-lg-4">
            <div class="how_design">
                <img src="<c:url value="${contextPath}/resources/images/why_img12.png"/>" class="img-fluid img_size">
                <p>
                    <span>We evaluate by technology</span>
                </p>
            </div>
        </div>
        <div class="col-6 col-md-4 col-lg-4">
            <div class="how_design">
                <img src="<c:url value="${contextPath}/resources/images/why_img13.png"/>" class="img-fluid img_size">
                <p>
                    <span>Enable you the best alternatives</span>
                </p>
            </div>
        </div>
        <div class="col-6 col-md-4 col-lg-4">
            <div class="how_design">
                <img src="<c:url value="${contextPath}/resources/images/why_img14.png"/>" class="img-fluid img_size">
                <p>
                    <span>Ensure smooth execution</span>
                </p>
            </div>
        </div>
        <div class="col-6 col-md-4 col-lg-4">
            <div class="how_design">
                <img src="<c:url value="${contextPath}/resources/images/why_img15.png"/>" class="img-fluid img_size">
                <p>
                    <span>One view dashboard</span>
                </p>
            </div>
        </div>
        <div class="col-6 col-md-4 col-lg-4" style="margin-right: -1px;">
            <div class="how_design">
                <img src="<c:url value="${contextPath}/resources/images/why_img16.png"/>" class="img-fluid img_size">
                <p>
                    <span>Efficient &amp; intelligent evaluation process</span>
                </p>
            </div>
        </div>
    </div>


</div>
<div class='scrolltop'>
    <div class='scroll icon bounceIn'><i class="fa fa-4x fa-angle-up" style="font-size: 30px;"></i></div>
    
</div>
<jsp:include page="campaign/signup.jsp"></jsp:include>
<jsp:include page="include/footer.jsp"></jsp:include>

<!-- </div> -->
</body>
</html>