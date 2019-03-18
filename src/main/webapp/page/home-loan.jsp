<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home loan calculator</title>
<link
	href="<c:url value="${contextPath}/resources/css/registry.form6.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
	<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script
	src="<c:url value="${contextPath}/resources/js/home-loan.js" />"></script>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<script type="text/javascript">
function calculateLoan(){
	
	
	
}
</script> 


<body class="back_set"
	 onload="initialData();">
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
	<div>
    <div class="page_topic">
        <!-- <img src="assets/images/home_loan.png" class="img-fluid"> -->
        <h2>

            <span style="color: #fdff3f;">Home Loan </span> Registry
        </h2>
    </div>

    <div class="row" style="padding: 5px;">
        <div class="col-md-7 col-lg-7" style="border: 1px solid; border-style: groove; padding: 10px 5px 0px 5px;">
            <div class="form-box">
                <form:form commandName="homeLoanForm">

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" style="padding-right: 0px;">Loan Amount</label>
                        <div class="col-7 col-sm-6 col-md-6" style="padding-right: 0px;">
                            <!-- <input type="text" class="form-control form-control-sm" placeholder="Email"> -->
                            <input type="range" min="100" max="10000000" class="slider"
                                onchange="principalrangeBar()"  id="princiaplRange">
                        </div>
                        <div class="col-5 col-sm-4 col-md-4">
                            <!-- <input type="text" class="form-control form-control-sm" placeholder="Email"> -->
                            <span style="position: absolute; margin-top: 0.45em;margin-left: 10px;">&#8377;</span>
                            <form:input class="form-control form-control-sm" style="padding-left: 20px;" path="principal" 
                                onkeyup="principalinput()" id="principalbox" />
                            <!-- | currency : 'INR':'symbol' :'3.0' -->
                            <span id="principalexcess" style="font-size: 12px; color: red;"></span>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" style="padding-right: 0px;">Interest Rate</label>
                        <div class="col-7 col-sm-6 col-md-6" style="padding-right: 0px;">
                            <!-- <input type="text" class="form-control form-control-sm" placeholder="Email"> -->
                            <input type="range" min="6" max="14" step=".01" path="interest" 
                                onchange="interestRangeBar()" class="slider" id="interestRange">
							                                
                        </div>
                        <div class="col-3 col-sm-3 col-md-3" style="padding-right: 0px;">
                            <!-- <input type="text" class="form-control form-control-sm" placeholder="Email"> -->
                            <form:input class="form-control form-control-sm" path="interest" id="interestBox"
                                onkeyup="interestInputBox()" />
                            <span id="interestexcess" style="font-size: 12px; color: red;"></span>
                        </div>
                        <div class="col-2 col-sm-1">
                            <label>p.a</label>
                        </div>
                        
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" style="padding-right: 0px;">Tenure</label>
                        <div class="col-7 col-sm-6 col-md-6" style="padding-right: 0px;">
                            <!-- <input type="text" class="form-control form-control-sm" placeholder="Email"> -->
                            <input type="range" min="1" max="40" class="slider"
                                onchange="tenureRangeBar();" id="tenureRange">
                        </div>
                        <div class="col-3 col-sm-3 col-md-3" style="padding-right: 0px;">
                            <!-- <input type="text" class="form-control form-control-sm" placeholder="Email"> -->
                            <form:input type="text" class="form-control form-control-sm" path="tenure" id="tenureBox"
                                onkeyup="tenureBoxInput();" pattern="[0-9]{2}" />
                            <span id="tenureexcess" style="font-size: 12px; color: red;"></span>
                        </div>
                        <div class="col-2 col-sm-1">
                            <label>yrs</label>
                        </div>
                        
                    </div>


                    <!-- <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-4 col-form-label"></label>
                        <div class="col-sm-8">
                            <button class="btn btn-sm btn-block btn-info">Checkout</button>
                        </div>
                    </div> -->


                </form:form>
            </div>
        </div>
        <div class="col-md-5 col-lg-5">
            <!-- chart -->
            <div>
                <%-- <canvas baseChart [data]="doughnutChartData" [labels]="doughnutChartLabels" [chartType]="doughnutChartType" (chartHover)="chartHovered($event)"
                    (chartClick)="chartClicked($event)"></canvas> --%>
                    <div id="chart_div"></div>
            </div>
            <div class="loan_details">
                <div class="row">
                    <div class="col-5">Total payable</div>
                    <div class="col-2" style="text-align: center">:</div>
                    <div class="col-5" style="margin-left: -1px;">
                        <span>&#8377; </span><span id="totaltobePaid">0</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-5">Interest payable</div>
                    <div class="col-2" style="text-align: center">:</div>
                    <div class="col-5" style="margin-left: -1px;"><span>&#8377; </span><span id="interestpayable">0</span></div>
                </div>
                <div class="row">
                    <div class="col-5">Total cost</div>
                    <div class="col-2" style="text-align: center">:</div>
                    <div class="col-5" style="margin-left: -1px;"><span>&#8377; </span><span id="totalCost">0</span></div>
                </div>
                <div class="row">
                    <div class="col-5">Monthly EMI</div>
                    <div class="col-2" style="text-align: center">:</div>
                    <div class="col-5" style="margin-left: -1px;"><span>&#8377; </span><span id="EMI">0</span></div>
                </div>

            </div>
        </div>
    </div>
</div>
	</div>

</body>
</html>