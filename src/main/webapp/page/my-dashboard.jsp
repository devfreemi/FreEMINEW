<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Dashboard</title>
<link href="<c:url value="${contextPath}/resources/css/my-dashboard.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/freemi-dashboard.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/fsecure-dashboard.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/registry-dashboard.component.css"/>"
	rel="stylesheet">

<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<%-- <script src="<c:url value="${contextPath}/resources/js/freemi.js" />"></script> --%>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>

<body class="back_set"
	>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container" style="padding: 0px;">
  <div class="dashboard_header">
    <span>DASHBOARD</span>
  </div>

  <div class="home-view">
    <span>HOME VIEW</span>
  </div>

  <div class="row dashboard_content">
    <div class="col-md-4 col-lg-4 outer_box">
      <div class="box"> 
        <div class="header">
          <span class="product_desc">FreEMI Credit</span>
        </div>

        <div class="row">
          <div class="col-6"> Loan Status</div>
          <div class="col-6">Active</div>
        </div>
        <div class="row" >
            <div class="col-6">Total loan</div>
            <div class="col-6">2</div>
          </div>
          <div class="footer_link nav nav-tabs">
              <span >VIEW DETAILS</span>
            </div>
      </div>
      
    </div>
    <div class="col-md-4 col-lg-4 outer_box">
        <div class="box" > 
          <div class="header">
            <span class="product_desc">Registry</span>
          </div>
  
          <div class="row">
            <div class="col-6">Registry</div>
            <div class="col-6">Active</div>
          </div>
          <div class="row" >
              <div class="col-6">Total loan</div>
              <div class="col-6">2</div>
            </div>
            <div class="footer_link">
              <span >VIEW DETAILS</span>
              </div>
        </div>
       
      </div>
      <div class="col-md-4 col-lg-4 outer_box">
          <div class="box"> 
            <div class="header">
              <span class="product_desc">FSecure</span>
            </div>
    
            <div class="row">
              <div class="col-6"> Loan Status</div>
              <div class="col-6">Active</div>
            </div>
            <div class="row">
                <div class="col-6">Total loan</div>
                <div class="col-6">2</div>
              </div>
              <div class="footer_link" >
                <span >VIEW DETAILS</span>
                 
                </div>
          </div>
         
        </div>
  </div>

<ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#freemi" role="tab" aria-controls="freemi" aria-selected="true">FreEMI</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#fsecure" role="tab" aria-controls="fsecure" aria-selected="false">F-Secure</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#registry" role="tab" aria-controls="registry" aria-selected="false">Registry</a>
  </li>
</ul>

<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active custom-tab" id="freemi" role="tabpanel" aria-labelledby="home-tab">
  
  <div class="fremi-outer-shell">
  <div class="freemi-profiles">
    <i class="fas fa-list" style="color: aliceblue;"></i>
  </div>

  <div class="freemi-outer">
      <div style="overflow-x: auto;">
        <table class="table table-sm table-bordered">
          <caption>FreEMI Purchse History</caption>
          <thead class="freemi-records">
            <th scope="col">Loan Category</th>
            <th scope="col">Loan Type</th>
            <th scope="col">Loan Amount</th>
            <th scope="col">Loan Tenure</th>
            <th scope="col">Monthly EMI</th>
            <th scope="col">Premium Amount</th>
          </thead>
          <tbody>
          <!-- <tr *ngFor="let data of freemiData">
            <td>{{data.instype}}</td>
            <td>{{data.insname}}</td>
            <td>{{data.nominee}}</td>
            <td>{{data.term}}</td>
            <td>{{data.payingterm}}</td>
            <td>&#8377; {{data.amount | number: '.2'}}</td>
            <td>{{data.statement }}</td>
          </tr> -->
        </tbody>
  
        </table>
      </div>
    </div>
    
</div>
  
  </div>
  <div class="tab-pane fade custom-tab" id="fsecure" role="tabpanel" aria-labelledby="profile-tab">
  
  <div class="fremi-outer-shell">
  <div class="fsecure-profiles">
    <i class="fas fa-list" style="color: aliceblue;"></i>
  </div>

  <div class="fsecure-outer">
    <div style="overflow-x: auto;">
      <table class="table table-sm table-bordered">
        <caption>FSecure Purchse History</caption>
        <thead class="fsecure-records">
          <th scope="col">Insrunce Type</th>
          <th scope="col">Insurer Name</th>
          <th scope="col">Nominee Name</th>
          <th scope="col">Term</th>
          <th scope="col">Premium Paying Term</th>
          <th scope="col">Premium Amount</th>
          <th scope="col">Premium Statement</th>
        </thead>
        <tbody>
        <!-- <tr *ngFor="let data of fsecureData">
          <td>{{data.instype}}</td>
          <td>{{data.insname}}</td>
          <td>{{data.nominee}}</td>
          <td>{{data.term}}</td>
          <td>{{data.payingterm}}</td>
          <td>&#8377; {{data.amount | number: '.2'}}</td>
          <td>{{data.statement }}</td>
        </tr> -->
      </tbody>

      </table>
    </div>
  </div>


</div>
  
  </div>
  <div class="tab-pane fade custom-tab" id="registry" role="tabpanel" aria-labelledby="contact-tab">
  
  <div class="fremi-outer-shell">
    <div class="registry-profiles">
      <i class="fas fa-list" style="color: aliceblue;"></i>
    </div>
  
    <div class="registry-outer">
      <div style="overflow-x: auto;">
        <table class="table table-sm table-bordered">
          <caption>Registry Purchse History</caption>
          <thead class="registry-records">
            <th scope="col">Type</th>
            <th scope="col">Days to go</th>
            <th scope="col">SIP Amount</th>
            <th scope="col">AMC Name</th>
            <th scope="col">Scheme Name</th>
            <th scope="col">XIRR</th>
            <th scope="col">Accumulated Amount</th>
            <th scope="col">Topup SIP</th>
            <th scope="col">Action</th>
          </thead>
          <tbody>
          <!-- <tr *ngFor="let data of fsecureData">
            <td>{{data.instype}}</td>
            <td>{{data.insname}}</td>
            <td>{{data.nominee}}</td>
            <td>{{data.term}}</td>
            <td>{{data.payingterm}}</td>
            <td>&#8377; {{data.amount | number: '.2'}}</td>
            <td>{{data.statement }}</td>
          </tr> -->
        </tbody>
  
        </table>
      </div>
    </div>
  </div>
  
  </div>
</div>


</div>



</body>
</html>