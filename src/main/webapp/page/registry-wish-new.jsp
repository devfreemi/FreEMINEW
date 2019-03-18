<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Registry wish</title>
   <jsp:include page="include/header.jsp"></jsp:include>
   
  <body class="register_page">
     <jsp:include page="include/menu.jsp"></jsp:include>
     <jsp:include page="include/banner_registry.jsp"></jsp:include>

<div class="container freemi_container">

<div>
    <div class="page_topic">
        <h2>
            <span style="color:#FDF906;">${type}</span> Registry</h2>
    </div>

    <div class="row form_entry" >
        <div class="col-md-8 col-lg-8 form_style">
            <!-- <div class="form_style"> -->
            <form:form commandName="registryWishForm" action="${pageContext.request.contextPath}/registry-mutual-funds/registryFunds.do" method="POST">
                <!-- <form [formGroup]="registryForm"> -->
                
                <div>
                	<form:hidden path="wishType"/>
                	<form:hidden path="schemeId"/>
                	
                </div>
                <div class="row">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b>Investment Type
                                <sup style="color: red;">*</sup>
                            </b>
                        </label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01" style="color: red;">
                                    <i class="fa fa-dot-circle-o"></i>
                                </label>
                            </div>
                            <form:select class="custom-select" id="investmenttype" path="investType" onchange="investmenttypeBox();">
                                <form:options items = "${investmentType}"   />
                            </form:select>	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b>Plan frequency
                                <sup style="color: red;">*</sup>
                            </b>
                        </label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01" style="color: #2326CF;">
                                    <i class="fa fa-dot-circle-o"></i>
                                </label>
                            </div>
                            <form:select class="custom-select" id="inputGroupSelect01" path="tenure">                                
                                  <option value="4">Quarterly</option>
						          <option value="12">Monthly</option>
						        
                            </form:select>
                        </div>
                    </div>
                </div>
                <!-- 
                 <div class="row" id="sipt">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b style="color: rgb(0, 194, 125);">Your desired amount</b><sup style="color: red;">*</sup>
                        </label>

                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1" style="background-color: #2e58da;">
                                    <img src="<c:url value="${contextPath}/resources/images/sip.png"/>" class="img-fluid;" style="height: 25px;" />
                                </span>
                            </div>
                            <form:input type="text" id="amount_sip" pattern="[0-9]*" maxlength="5" class="form-control" placeholder="0" path="amount" aria-describedby="sizing-addon1" />
                        </div>
                    </div>
                </div>
                 -->
               
                <div class="row" id="sip">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label id="sipt" name="sipt">
                            <b style="color: rgb(0, 194, 125);">Your desired amount</b><sup style="color: red;">*</sup>
                        </label>
                         <label id="sipm" name="sipm">
                            <b style="color: rgb(0, 194, 125);">Your contribution amount</b><sup style="color: red;">*</sup>
                        </label>

                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1" style="background-color: #2e58da;">
                                    <img src="<c:url value="${contextPath}/resources/images/sip.png"/>" class="img-fluid;" style="height: 25px;" />
                                </span>
                            </div>
                            <form:input type="text" id="amount_sip" pattern="[0-9]*" maxlength="5" class="form-control" placeholder="0" path="amount" aria-describedby="sizing-addon1" />
                        </div>
                    </div>
                </div>
                <!-- 
                 <div class="row" id="amountbox">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b style="color: #fd5c5c">What is the expected expenditure?</b><sup style="color: red;">*</sup>
                        </label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1" style="background-color: #fdebd8;">
                                    <img src="<c:url value="${contextPath}/resources/images/loan-amount.png"/>" class="img-fluid;" style="height: 25px;" />
                                </span>
                            </div>
                            <form:input id="amount" type="text" pattern="[0-9]*" maxlength="5" class="form-control" placeholder="0" path="amount" aria-describedby="sizing-addon1" />
                        </div>
                    </div>
                </div>
                
                 -->
                

              
                <div class="row">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b>When is the occasion?
                                <sup style="color: red;">*</sup>
                            </b>
                        </label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <button class="btn btn-outline-info">
                                    <i class="fa fa-calendar"></i>
                                </button>
                            </div>
                            <!-- <input class="form-control" placeholder="yyyy-mm-dd" name="dp" [(ngModel)]="model" ngbDatepicker [markDisabled]="isDisabled" -->
                            <form:input type="date" path="date" class="form-control" id="eventdate" 
									placeholder="Age 18 - 65 yrs (yyyy-mm-dd)" />
                        </div>
                        <span style="font-size: 10px; color: blue; line-height: 1;">Event plan to be between 1 month to 12 months in advance</span>
                        <!-- <div style="margin-bottom: 10px;">
                            You have
                            <span class="days_calc">{{dateDiff}} </span> days to go
                        </div> -->
                    </div>
                </div>
                  <div class="row">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b>Who is it for?
                                <sup style="color: red;">*</sup>
                            </b>
                        </label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">
                                    <i class="fa fa-users" aria-hidden="true"></i>
                                </label>
                            </div>
                            <form:select class="custom-select" id="inputGroupSelect01" path="person">
                                <form:options items = "${personType}" />
                            </form:select>
                        </div>
                    </div>
                </div>

                <div class="draw-line">
                    <span></span>
                </div>


                <!-- Result -->

                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <!-- <div class="row" >
                            <div class="col-md-6 col-lg-6 label_style">
                                <label>
                                    <b>
                                        Projected SIP amount
                                    </b>
                                </label>

                            </div>
                            <div class="col-md-6 col-lg-6" style="background-color: #ffb963;" >
                                <i class="fas fa-rupee-sign"></i> 0
                            </div>
                        </div> -->
                        <div class="row">
                            <div class="col-md-6 col-lg-6 label_style">
                                <label>
                                    <b>Your event budget
                                    </b>
                                </label>
                            </div>
                            <div class="col-md-6 col-lg-6" style="background-color: #ffb963;">
                                <i class="fas fa-rupee-sign"></i><span id="final-installment-amount">  </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="row">
                            <div class="col-md-6 col-lg-6 label_style">
                                <label>
                                    <b>Your Contribution (SIP)
                                    </b>
                                </label>
                            </div>
                            <div class="col-md-6 col-lg-6" style="background-color: #f96868;">
                                <i class="fas fa-rupee-sign"></i><label id="final-sip-amount">
                                   
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- <div class="row">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b>Do you want the product delivered?</b> (Optional)</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" value="" disabled> Deliver the product
                            </label>
                        </div>
                    </div>
                </div> -->

                <div class="row" style="margin-top: 20px;">
                    <div class="col-md-12 col-lg-12" style="text-align: center;">
                        <!-- <button type="button" class="btn btn-outline-info" [routerLink]="['giftfunds']" [queryParams]="{category: 'family',type: 'birthday', chosen: 'daughter'}"> 
                                CREATE REGISTRY
                            </button> -->
                        <button type="submit" class="btn btn-md btn-outline-info">
                            CREATE REGISTRY
                        </button>
                    </div>
                </div>
            </form:form>
            <!-- </div> -->
        </div>
        <!-- </div> -->

         <div class="col-md-4 col-lg-4">
            <%-- <div class="event_image">
                <img src="<c:url value="${contextPath}/resources/images/${event_based_image}"/>" class="img-fluid">
            </div> --%>
            <div class="event_content">
            	<c:if test="${type ==  'birthday' }">
                <div>
                    <p>
                        Celebrating birthday for family and friend is like giving happiness to life. Lets celebrate it with more affordability and
                        pain less with smart financial management tool like Registry account. We ensure it will be a journey
                        of joy and full of happiness to your life.
                    </p>
                </div>
                </c:if>
                <c:if test="${type ==  'anniversary' }">
                <div>
                    <p>
                        Plan a "Anniversary" in advance to make it more memorable to your partner. You can plan it for others as well for a right
                        gift at the time.
                    </p>
                </div>
                </c:if>
                <c:if test="${type ==  'parties' }">
                <div >
                    <p>
                        Lets do the party with family or with friends to enjoy the life with full energy and make it largeâ€¦. Lets not increase the
                        credit card billâ€¦.. create Registry account and "Njoy" with dignity and confidence.
                    </p>
                </div>
                </c:if>
                <c:if test="${type ==  'marriage' }">
                <div >
                    <p>
                        Have "Wedding" in family or friends are required planing for style &amp; gifting, so lets plan for it and make it a grand ceremony,
                        which you will remember for long and bless the groom from the heart. Let not wast you energy on financial
                        planing, we are hare to take care and manage it as well. Lets plan it in advance and enjoy the ceremony
                        with full energyâ€¦.
                    </p>
                </div>
                </c:if>
            </div>

            <div class="ending">
                <div style="padding: 5px; background-color: #ffb963;">
                    <h5>
                        <i class="fas fa-thumbs-up"></i> Congratulation!!!</h5>
                </div>
                <span style="background: #f96868; padding: 10px;color: aliceblue;border: 1px groove;">
                    To your debt free life..
                </span>

            </div>
        </div>
    </div>
    
<!--     <div *ngIf="dataSubmitted">
        <registry-form1-funds></registry-form1-funds>
        <button type="button" class="btn btn-outline-info" (click)="dataSubmitted=!dataSubmitted">
            View Previous
        </button>
    </div> -->


    <!-- </div> -->
</div>

</div>
  <jsp:include page="include/footer1.jsp"></jsp:include>


</body>
</html>