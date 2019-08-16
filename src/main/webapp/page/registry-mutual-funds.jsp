<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<html>
<head>
<meta name="keywords" content="Expenses management, Best Mutual Fund,Equity Mutual Fund, debt mutual Fund, Balanlce fund, Small cap Fund,Mid Cap fund,Online SIP, Best SIP Online, Compare mutual Fund,SIP Calculator,Return Calculator, ICICI Prdential balance advantage fund, ICICI Pru value Discovery Fund,ICICI liquied Fund, HDFC prudence Fund, HDFC Equity Fund, Axis Long Term Equity Fund, Buy Mutual Fund" />


<meta name="title" content="Registry mutual fund" />
<meta name="description" content="1. Invest in Equity Linked Savings Schemes and save tax 2. Now calculate your tax like a pro 3. Manage your good expenses by investing in Best Mutual Funds 4. Plan for next travel with FreEMi to avoid Credit Card bills  5. Invest in ICICI Prudentntial Balance Advantage Fund 6. Open a Insta fund account 7. Now redeem and get your cash in bank account instantantly- Oper Insta cash account. 8. Invest in star rate ELSS fund - Axis Long Term Equity Fund 9. Gift your spouse a memorable gift in anniversary with no pain of credit card bills, open Registry account 10. Get your Home Loan free from Interest, Invest in Right Mutual SIP now. Creat Home Loan Registry account" />
<meta name="robots" content="follow,index" />

<title>Registry Product</title>
<link
	href="<c:url value="${contextPath}/resources/css/registry.general.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/animate.css"/>"
	type="text/css" rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/pace-theme.css"/>" rel="stylesheet">
<script src="<c:url value="${contextPath}/resources/js/pace.min.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set"">
<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container freemi_container">
<div>
    <div class="row page_heading">
        <div class="registry_logo">
            <img src="<c:url value="${contextPath}/resources/images/registry.png"/>" alt="Product logo" class="img-fluid" style="width: 90px;">
        </div>
        <div class="registry_title">
            <h1>Registry</h1>
            <h5>A Product of Suitability</h5>
        </div>
    </div>

    <div class="row registry_category">
        <div class="col-12 head_1">
            <span style="font-family: serif; font-size: 25px;">Turn your good expenses into investment now</span>
        </div>
        <div class="col-md-4 col-lg-4 margin_type" style="margin: -1px;">
            <div class="group_border">
                <div class="topic_color">
                    <h3>Family</h3>
                    <p style="font-size: 12px;">Planning for family?</p>
                </div>
                <div style="padding: 10px; overflow-y: auto; max-height: 300px;">
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=family&type=birthday"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <i class="fas fa-birthday-cake custom_style" aria-hidden="true"></i>Birthday</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=family&type=anniversary"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_an.png"/>" class="img_fluid custom_img_fluid">Anniversary</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=family&type=marriage"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_m.png"/>" class="img_fluid custom_img_fluid">Marriage</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=family&type=parties"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_p.png"/>" class="img_fluid custom_img_fluid">Parties</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/tax-calculator"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_st.png"/>" class="img_fluid custom_img_fluid">Tax Saving</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-retirement"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_r.png"/>" class="img_fluid custom_img_fluid">Retirement</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-child-education"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_ce.png"/>" class="img_fluid custom_img_fluid">Child Education</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-travel"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_t.png"/>" class="img_fluid custom_img_fluid">Travel</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/home-loan"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_hl.png"/>" class="img_fluid custom_img_fluid">Home Loan</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/"/>" ><button type="button" class="btn btn-outline-primary btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_pl.png"/>" class="img_fluid custom_img_fluid">Personal Loan</button></a>

                </div>
            </div>

        </div>

        <div class="col-md-4 col-lg-4 margin_type">
            <div class="group_border">
                <div class="topic_color">
                    <h3>Friends</h3>
                    <p style="font-size: 12px;">Need to gift your friend for his/her upcoming birthday or marriage? How can plan your budget?</p>
                </div>
                <div style="padding: 10px; overflow-y: auto; max-height: 300px;">
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=friends&type=birthday"/>" ><button type="button" class="btn btn-outline-info btn-sm btn-block">
                        <i class="fas fa-birthday-cake custom_style" aria-hidden="true"></i> Birthday</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=friends&type=marriage"/>" ><button type="button" class="btn btn-outline-info btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_m.png"/>" class="img_fluid custom_img_fluid">Wedding gift</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=friends&type=others"/>" ><button type="button" class="btn btn-outline-info btn-sm btn-block">
                        <img src="<c:url value="${contextPath}/resources/images/registry_o.png"/>" class="img_fluid custom_img_fluid">Others</button></a>

                </div>

            </div>
        </div>


        <div class="col-md-4 col-lg-4 margin_type">
            <div class="group_border">
                <div class="topic_color">
                    <h3>Official</h3>
                    <p style="font-size: 12px;">Manage your expanse for colleagues.</p>
                </div>
                <div style="padding: 10px; overflow-y: auto; max-height: 300px;">
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/office-expense"/>" ><button type="button" class="btn btn-outline-danger btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_do.png"/>" class="img_fluid custom_img_fluid">Daily Office Expanses</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/"/>" ><button type="button" class="btn btn-outline-danger btn-sm btn-block" >
                        <i class="fas fa-birthday-cake custom_style" aria-hidden="true"></i>Birthday</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=office&type=parties"/>" ><button type="button" class="btn btn-outline-danger btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_p.png"/>" class="img_fluid custom_img_fluid">Parties</button></a>
                    <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/registry-wish?category=office&type=marriage"/>" ><button type="button" class="btn btn-outline-danger btn-sm btn-block" >
                        <img src="<c:url value="${contextPath}/resources/images/registry_m.png"/>" class="img_fluid custom_img_fluid">Wedding gift</button></a>

                </div>
                <div class="im1">
                    <img src="<c:url value="${contextPath}/resources/images/corp.png"/>" class="img-fluid" style="max-width: 60%; float: right;">
                </div>
            </div>
        </div>
    </div>

    <div class="arrow_border">

        <div class="arrow_container">
            <span>
                <!-- <i class="fa fa-caret-down arrow_custom" aria-hidden="true"></i> -->
                <i class="arrow_custom"></i>
            </span>
        </div>
        <div>
            <span class="find_style">Find out more</span>
        </div>
    </div>

    <div style="margin-top: 30px;margin-bottom: 30px; text-align: center;">
        <a style="text-decoration: none;"  href="<c:url value="/registry-mutual-funds/"/>" ><button class="btn btn-danger btn-md" style="padding-left: 20px; padding-right: 20px;" >Register & Explore</button></a>
    </div>

    <div class="row">
        <div class="col-md-5 col-lg-5 image_style">
            <img src="<c:url value="${contextPath}/resources/images/registry_pic1.png"/>" class="img-fluid" style="width: 90%;">
        </div>
        <div class="col-md-6 col-lg-6 contents">

            <div class="registry_c1">
                <span>Live life debt-free.</span>
            </div>
            <div class="registry_c2">
                <span>Self-efficient &amp; Intelligent Registry account will define and execute smart investment</span>
            </div>
            <div class="registry_c3">
                <span>
                    Create Registry for birthday, anniversary, daily office expenses and many more
                </span>
            </div>

        </div>

    </div>
</div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>