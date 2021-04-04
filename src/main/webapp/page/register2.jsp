<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="keywords" content="freemi signup, freemi register, investment portal" />
<link rel="canonical" href=" https://www.freemi.in/products/register" />
<meta name="title" content="New to FreEMI? Get registered today." />
<meta name="description" content="Get registered to FreEMI and invest to fill your goals. Apply for personal loans, business loan, credit cards, health insurance, motor insurance, etc. Purchase mutual funds, invest through SIP." />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow" />
<meta name="bingbot" content="index, follow" />

<jsp:include page="include/bootstrap.jsp"></jsp:include>


<link
	href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css"
	rel="stylesheet" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<title>New to FreEMI? Get registered today.</title>

<style type="text/css">
/*custom font*/
@import url(https://fonts.googleapis.com/css?family=Montserrat);

/*basic reset*/
* {
    margin: 0;
    padding: 0;
}

html {
    height: 100%;
    background: #6441A5; /* fallback for old browsers */
    background: -webkit-linear-gradient(to left, #6441A5, #2a0845); /* Chrome 10-25, Safari 5.1-6 */
}

body {
    font-family: montserrat, arial, verdana;
    background: transparent;
}

/*form styles*/
#msform {
    text-align: center;
    position: relative;
    margin-top: 30px;
}

#msform fieldset {
    background: white;
    border: 0 none;
    border-radius: 0px;
    box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.4);
    padding: 20px 30px;
    box-sizing: border-box;
    width: 80%;
    margin: 0 10%;

    /*stacking fieldsets above each other*/
    position: relative;
}

/*Hide all except first fieldset*/
#msform fieldset:not(:first-of-type) {
    display: none;
}

/*inputs*/
#msform input, #msform textarea {
    padding: 15px;
    border: 1px solid #ccc;
    border-radius: 0px;
    margin-bottom: 10px;
    width: 100%;
    box-sizing: border-box;
    font-family: montserrat;
    color: #2C3E50;
    font-size: 13px;
}

#msform input:focus, #msform textarea:focus {
    -moz-box-shadow: none !important;
    -webkit-box-shadow: none !important;
    box-shadow: none !important;
    border: 1px solid #ee0979;
    outline-width: 0;
    transition: All 0.5s ease-in;
    -webkit-transition: All 0.5s ease-in;
    -moz-transition: All 0.5s ease-in;
    -o-transition: All 0.5s ease-in;
}

/*buttons*/
#msform .action-button {
    width: 100px;
    background: #ee0979;
    font-weight: bold;
    color: white;
    border: 0 none;
    border-radius: 25px;
    cursor: pointer;
    padding: 10px 5px;
    margin: 10px 5px;
}

#msform .action-button:hover, #msform .action-button:focus {
    box-shadow: 0 0 0 2px white, 0 0 0 3px #ee0979;
}

#msform .action-button-previous {
    width: 100px;
    background: #C5C5F1;
    font-weight: bold;
    color: white;
    border: 0 none;
    border-radius: 25px;
    cursor: pointer;
    padding: 10px 5px;
    margin: 10px 5px;
}

#msform .action-button-previous:hover, #msform .action-button-previous:focus {
    box-shadow: 0 0 0 2px white, 0 0 0 3px #C5C5F1;
}

/* button:disabled{
  background: #fa83bd;
}
 */
/*headings*/
.fs-title {
    font-size: 18px;
    text-transform: uppercase;
    color: #2C3E50;
    margin-bottom: 10px;
    letter-spacing: 2px;
    font-weight: bold;
}

.fs-subtitle {
    font-weight: normal;
    font-size: 13px;
    color: #666;
    margin-bottom: 20px;
}

/*progressbar*/
#progressbar {
    margin-bottom: 30px;
    overflow: hidden;
    /*CSS counters to number the steps*/
    counter-reset: step;
}

#progressbar li {
    list-style-type: none;
    color: white;
    text-transform: uppercase;
    font-size: 9px;
    width: 50%;
    float: left;
    position: relative;
    letter-spacing: 1px;
}

#progressbar li:before {
    content: counter(step);
    counter-increment: step;
    width: 24px;
    height: 24px;
    line-height: 26px;
    display: block;
    font-size: 12px;
    color: #333;
    background: white;
    border-radius: 25px;
    margin: 0 auto 10px auto;
}

/*progressbar connectors*/
#progressbar li:after {
    content: '';
    width: 100%;
    height: 2px;
    background: white;
    position: absolute;
    left: -50%;
    top: 9px;
    z-index: -1; /*put it behind the numbers*/
}

#progressbar li:first-child:after {
    /*connector not needed before the first step*/
    content: none;
}

/*marking active/completed steps green*/
/*The number of the step and the connector before it = green*/
#progressbar li.active:before, #progressbar li.active:after {
    background: #ee0979;
    color: white;
}


/* Not relevant to this form */
.dme_link {
    margin-top: 30px;
    text-align: center;
}
.dme_link a {
    background: #FFF;
    font-weight: bold;
    color: #ee0979;
    border: 0 none;
    border-radius: 25px;
    cursor: pointer;
    padding: 5px 25px;
    font-size: 12px;
}

.dme_link a:hover, .dme_link a:focus {
    background: #C5C5F1;
    text-decoration: none;
}
</style>



<body>
<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>

<!-- MultiStep Form -->
<div class="row">
    <div class="col-md-6 col-md-offset-3 mx-auto">
        <form:form id="msform" commandName="registerForm">
            <!-- progressbar -->
            <ul id="progressbar">
                <li class="active">Basic Details</li>
                <li>Account Setup</li>
            </ul>
            <!-- fieldsets -->
            <fieldset>
                <h2 class="fs-title">Basic Details</h2>
                <h3 class="fs-subtitle">Tell us something more about you</h3>
                <div class="row mx-auto">
					<div class="col-md-12 col-lg-12 alert text-danger" role="alert">
								<span id="displaymsg">${message}</span>
					</div>

				</div>
                
                <form:input type="tel" id="registermobile" path="mobile" placeholder="Indian Mobile No" pattern="[0-9]*" maxlength="10" />
                <form:input type="email" id="useremail" path="email" placeholder="Email" maxlength="128"/>
                <div class="md-form col-md-6 mx-auto text-center mt-0" id="otpblock">
						<form:input type="tel" id="otpbox"
							class="form-control form-control-sm mb-0 p-2 text-center" style="letter-spacing: .5rem; font-stretch: extra-expanded; font-weight: 600; font-size: medium;" path="otp" maxlength="6"
							autocomplete="off" placeholder="OTP"></form:input>
							
							<!-- <button type="button" class="btn btn-sm btn-deep-orange p-2" id="verify-otp-btn"><span id="otpverify">Verify</span> </button> -->
					</div>
                <!-- <input type="button" id="send-otp-btn" name="next" class="send-otp-btn action-button" value="Get OTP"/>
                <input type="button" id="next-btn" name="next" class="next action-button" value="Verify"/> -->
                <!-- <input type="button" id="send-otp-btn" name="next" class="btn btn-deep-orange" value="Get OTP"/>
                <input type="button" id="next-btn" name="next" class="next btn btn-deep-orange" value="Verify"/> -->
                
            </fieldset>
            
            <fieldset>
                <h2 class="fs-title">Create your account</h2>
                <h3 class="fs-subtitle">Fill in your credentials</h3>
                
                <div class="row mx-auto">
					<div class="col-md-12 col-lg-12 alert text-danger" role="alert">
								<span id="msg2"></span>
					</div>

				</div>
                <form:input type="text" path="fname" placeholder="First Name" pattern="[a-zA-Z0-9 .]*" maxlength="64" />
                <form:input type="text" path="lname" placeholder="Last Name" pattern="[a-zA-Z0-9 .]*" maxlength="64"/>
                
                <form:input type="password" id="registerpassword" path="password" placeholder="Password"/>
                <input type="password" name="cpass" placeholder="Confirm Password"/>
                <input type="button" name="previous" class="previous action-button-previous" value="Previous"/>
                <input type="submit" id="registerSubmit" class="submit action-button" value="Submit" />
            </fieldset>
        </form:form>
    </div>
</div>
<!-- /.MultiStep Form -->
</body>
<script type="text/javascript">
	var basepathdata = '<c:out value='${pageContext.request.contextPath}'></c:out>';
</script>
<script src="<c:url value="${contextcdn}/resources/js/register2.js" />"></script>
<script type="text/javascript">


</script>
</html>