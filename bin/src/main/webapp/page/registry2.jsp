<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta name="keywords" content="Expenses management, Best Mutual Fund,Equity Mutual Fund, debt mutual Fund, Balanlce fund, Small cap Fund,Mid Cap fund,Online SIP, Best SIP Online, Compare mutual Fund,SIP Calculator,Return Calculator, ICICI Prdential balance advantage fund, ICICI Pru value Discovery Fund,ICICI liquied Fund, HDFC prudence Fund, HDFC Equity Fund, Axis Long Term Equity Fund, Buy Mutual Fund" />


<meta name="title" content="Registry mutual fund" />
<meta name="description" content="1. Invest in Equity Linked Savings Schemes and save tax 2. Now calculate your tax like a pro 3. Manage your good expenses by investing in Best Mutual Funds 4. Plan for next travel with FreEMi to avoid Credit Card bills  5. Invest in ICICI Prudentntial Balance Advantage Fund 6. Open a Insta fund account 7. Now redeem and get your cash in bank account instantantly- Oper Insta cash account. 8. Invest in star rate ELSS fund - Axis Long Term Equity Fund 9. Gift your spouse a memorable gift in anniversary with no pain of credit card bills, open Registry account 10. Get your Home Loan free from Interest, Invest in Right Mutual SIP now. Creat Home Loan Registry account" />
<meta name="robots" content="follow,index" />

<title>Registry Product</title>

<style> 
.a {
    white-space: nowrap; 
    width: 40px; 
    overflow: hidden;
    text-overflow:ellipsis;
   
}

.a:hover {
    white-space: wrap; 
    overflow: right;
  
}

.modalDialog {
		position: fixed;
		font-family: Arial, Helvetica, sans-serif;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		background: rgba(0,0,0,0.8);
		z-index: 99999;
		opacity:0;
		-webkit-transition: opacity 400ms ease-in;
		-moz-transition: opacity 400ms ease-in;
		transition: opacity 400ms ease-in;
		pointer-events: none;
	}

	.modalDialog:target {
		opacity:1;
		pointer-events: auto;
	}

	.modalDialog > div {
		width: 400px;
		position: relative;
		margin: 10% auto;
		padding: 5px 20px 13px 20px;
		border-radius: 10px;
		background: #fff;
		background: -moz-linear-gradient(#fff, #999);
		background: -webkit-linear-gradient(#fff, #999);
		background: -o-linear-gradient(#fff, #999);
	}

	.close {
		background: #606061;
		color: #FFFFFF;
		line-height: 25px;
		position: absolute;
		right: -12px;
		text-align: center;
		top: -10px;
		width: 24px;
		text-decoration: none;
		font-weight: bold;
		-webkit-border-radius: 12px;
		-moz-border-radius: 12px;
		border-radius: 12px;
		-moz-box-shadow: 1px 1px 3px #000;
		-webkit-box-shadow: 1px 1px 3px #000;
		box-shadow: 1px 1px 3px #000;
	}

	.close:hover { background: #00d9ff; 
	}
</style>
 <!--  Include Header -->
   <jsp:include page="include/header.jsp"></jsp:include>
   
  <body class="register_page">
     <jsp:include page="include/menu.jsp"></jsp:include>
     <jsp:include page="include/banner_registry.jsp"></jsp:include>

    <main role="main" class="main" id="div1">
	
	<section class="wow fadeInUp" data-wow-duration="2s">
        <div class="container">
          <div class="row padd_25">
            <div class="col-md-12 col-sm-12 col-xs-12">
             
				<marquee direction="left">
				<c:forEach var="product" items="${productlist}">
				<strong>${product.fundName}:    </strong>     ${product.nav}        |
				</c:forEach>
				</marquee>
			
			</div>
		  </div>
		</div>
	</section>     

      <section class="whoweare_section wow fadeInUp" data-wow-duration="2s">
        <div class="container">
          <div class="row padd_60">
            <div class="offset-md-1 col-md-10 md-offset-1 text-center about_sec_full whoweare_sec_full">
              <h4>Registry</h4>
              <h2>A Product of Suitability</h2>
              <p>Turn your good expenses into investment now.</p>
            </div> 
          
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInRightBig" data-wow-duration="3s">

              <div class="about_div whoweare_div">
               <div class="row registry_log_bg">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Birthday</h3>
               <!-- <p>Donec sollicitudin molestie malesuada.</p>-->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                    <i class="fa fa-birthday-cake" aria-hidden="true"></i>
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
              <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="birthdayhrf" name="birthdayhrf" class="btn btn-warning"   href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
           
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInRightBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row registry_log_bg">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Anniversary</h3>
              <!--  <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                  
                   <i class="fa fa-gift" aria-hidden="true"></i>
                   <!-- <img src="<c:url value="${contextPath}/resources/images/registry_an.png"/>" class="img_fluid custom_img_fluid">  -->
                   
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
              <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${annivesarylist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="annivhrf" name="annivhrf" class="btn btn-warning" href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInRightBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row registry_log_bg">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Marriage</h3>
              <!--  <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                   <i class="fa fa-heart" aria-hidden="true"></i>
                    <!-- <img src="<c:url value="${contextPath}/resources/images/registry_m.png"/>" class="img_fluid custom_img_fluid"> -->
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                  <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                  <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="marriagehrf" name="marriagehrf" class="btn btn-warning" href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInLeftBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Parties</h3>
              <!-- <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                   
                   <img src="<c:url value="${contextPath}/resources/images/registry_p.png"/>" class="img_fluid custom_img_fluid">
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                 <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                  <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="partieshrf" name="partieshrf" class="btn btn-warning" href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
            
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInLeftBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Tax Saving</h3>
               <!-- <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                 <!-- <i class="fa fa-graduation-cap" aria-hidden="true"></i>  --> 
                  <img src="<c:url value="${contextPath}/resources/images/registry_st.png"/>" class="img_fluid custom_img_fluid">
                  
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${taxsavinglist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                  <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="taxsavinghrf" name="taxsavinghrf" class="btn btn-warning" href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInLeftBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Retirement</h3>
               <!-- <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                    <img src="<c:url value="${contextPath}/resources/images/registry_r.png"/>" class="img_fluid custom_img_fluid">
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
               <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="retirementhrf" name="retirementhrf" class="btn btn-warning" href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInUpBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Child Education</h3>
              <!--  <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                  <i class="fa fa-graduation-cap" aria-hidden="true"></i>
                  <!--  <img src="<c:url value="${contextPath}/resources/images/registry_ce.png"/>" class="img_fluid custom_img_fluid"> -->
                   
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                            <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="childeduhrf" name="childeduhrf" class="btn btn-warning" href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInUpBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Travel</h3>
               <!-- <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                    <img src="<c:url value="${contextPath}/resources/images/registry_t.png"/>" class="img_fluid custom_img_fluid">
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                              <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                  <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="travelhrf" name="travelhrf" class="btn btn-warning" href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInDownBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Personal Loan</h3>
               <!-- <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                    <img src="<c:url value="${contextPath}/resources/images/registry_pl.png"/>" class="img_fluid custom_img_fluid">
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                             <div class="row">
              <div class="col-md-1 col-sm-1 col-xs-12 a"></div>
		                <div class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
	                  <div class="col-md-1 col-sm-1 col-xs-12"><input type="radio" name="scheme" value="${product.productScId}"></div>
	                  
		                <div id="fund" class="col-md-8 col-sm-8 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                  <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                     <a style="text-decoration: none;" id="plhrf" name="plhrf" class="btn btn-warning" href="" >Create Registry</a>
                </div>
              </div>    
             </div> 
            </div>
            </div>
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInDownBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Wedding gift</h3>
               <!-- <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                    <img src="<c:url value="${contextPath}/resources/images/registry_m.png"/>" class="img_fluid custom_img_fluid">
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                             <div class="row">
		                <div class="col-md-9 col-sm-9 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
		                <div id="fund" class="col-md-9 col-sm-9 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                 <a style="text-decoration: none;" id="weedgfthrf" name="weedgfthrf" class="btn btn-warning" href="" >Create Registry</a>
                     
                </div>
              </div>    
             </div> 
             </div>  
            </div>
            <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInDownBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Daily Office Expanses</h3>
               <!-- <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                   <img src="<c:url value="${contextPath}/resources/images/registry_do.png"/>" class="img_fluid custom_img_fluid">
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                              <div class="row">
		                <div class="col-md-9 col-sm-9 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
		                <div id="fund" class="col-md-9 col-sm-9 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                 <a style="text-decoration: none;" id="officeexprf" name="officeexprf" class="btn btn-warning" href="" >Create Registry</a>
                    
                </div>
              </div>    
             </div> 
             </div>  
            </div>
                  <div class="col-md-4 text-center about_sec_divi whoweare_sec_divi wow fadeInDownBig" data-wow-duration="3s">
              <div class="about_div whoweare_div">
               <div class="row">
                <div class="col-md-9 col-sm-9 col-xs-12">
               <h3>Others</h3>
               <!-- <p>Donec sollicitudin molestie malesuada.</p> -->
                </div>
                <div class="col-md-3 col-sm-3 col-xs-12">
                  <div class="ico">
                   <img src="<c:url value="${contextPath}/resources/images/registry_do.png"/>" class="img_fluid custom_img_fluid">
                  </div>
                </div> 
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              </div>
                              <div class="row">
		                <div class="col-md-9 col-sm-9 col-xs-12 a">
		                  <h5 style="font-size: 12px;">Scheme Name</h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12">	
		                   <h5 style="font-size: 12px;">1Y Return</h5>	               
		                
		                        <!-- <i class="fa fa-star-o" aria-hidden="true"></i> -->
		              
		                </div>
		              
	              </div>
                 <c:forEach var="product" items="${productlist}">
	                  <div class="row">
		                <div id="fund" class="col-md-9 col-sm-9 col-xs-12 a">
		                  <h5 style="font-size: 12px;"><a href="#openModal">${product.fundName}</a></h5>
						  </div> 
		                  
		                 <div class="col-md-3 col-sm-3 col-xs-12"><fmt:formatNumber value="${product.PReturn_1Y * 100}" pattern="0.00"/></div>
		              
	              </div>
	               <!--  Modal Code -->
				   <div id="openModal" class="modalDialog">
					<div>
						<a href="#close" title="Close" class="close">X</a>
						<h2>Scheme Details</h2>
						 <div class="col-md-12 col-sm-12 col-xs-12">
		                  <hr/>
		                </div>
						 <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Name:</h5>
							  </div> 
			                  
			                 <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundName}</h5>	           
			                </div>
		                 </div>
		                  <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Fund Type:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.fundType}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Nav Date:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.nav_Date}</h5>	           
			                </div>
		                 </div>
		                   <div class="row">
			                <div class="col-md-3 col-sm-3 col-xs-12">
			                  <h5 style="font-size: 12px;">Risk:</h5>
							  </div> 
			                  
			                <div class="col-md-9 col-sm-9 col-xs-12">	
			                   <h5 style="font-size: 12px;">${product.riskometer_value}</h5>	           
			                </div>
		                 </div>
					</div>
				</div>
        <!--  Modal Code -->    
                 </c:forEach>
            
              <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                  <hr/>
                </div>
              <div class="row">
                
                <div class="col-md-12 col-sm-12 col-xs-12 text-left">
                <a style="text-decoration: none;" id="othershrf" name="othershrf" class="btn btn-warning" href="" >Create Registry</a>
                     
                </div>
              </div>    
             </div> 
             </div>  
            </div>
            <div class="clr"></div>
            <div class="offset-md-1 col-md-10 md-offset-1 text-center about_sec_full whoweare_sec_full">
              <h4>OUR CLIENTS</h4>
              <h2>WHO LOVE TO WORK WITH US</h2>
              <p>Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Vivamus suscipit tortor eget felis porttitor volutpat. Curabitur aliquet quam id dui posuere blandit. Nulla porttitor accumsan tincidunt. Cras ultricies ligula sed magna dictum porta. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi. Mauris blandit aliquet elit, eget tincidunt ni dictum porta.</p>
            </div>
            <div class="col-md-12 text-center client_sec_full">
              <div class="owl-carousel owl-theme">
                <div class="item">
                <img src="images/c_1.png" alt=""/>
                  
                </div>
                <div class="item">
                <img src="images/c_2.png" alt=""/>
                 
                </div>
                <div class="item">
                <img src="images/c_3.png" alt=""/>
                 
                </div>
                <div class="item">
                <img src="images/c_4.png" alt=""/>
                 
                </div>
                <div class="item">
                <img src="images/c_5.png" alt=""/>
                 
                </div>
                <div class="item">
                <img src="images/c_1.png" alt=""/>
                
                </div>
                
              </div>
            </div>
            
          </div> 

          </div>
      </section>

      <section class="dark_blue testimonials_section wow fadeInDownBig" data-wow-duration="4s">
        <div class="container">
          <div class="row padd_60">

            <div class="offset-md-3 col-md-6 md-offset-3 text-center testimonials_sec_full">
              <div class="owl-carousel owl-theme">
                <div class="item">
                <p>"An image is not simply a trademark, a design, a slogan or an easily remembered picture. It is a studiously crafted personality profile of an individual, institution,corporation, product or service"</p>
                <div class="author">Daniel J. Boorstin</div> 
                </div>
                <div class="item">
                <p>"An image is not simply a trademark, a design, a slogan or an easily remembered picture. It is a studiously crafted personality profile of an individual, institution,corporation, product or service"</p>
                <div class="author">Daniel J. Boorstin</div> 
                 
                </div>
                <div class="item">
                <p>"An image is not simply a trademark, a design, a slogan or an easily remembered picture. It is a studiously crafted personality profile of an individual, institution,corporation, product or service"</p>
                <div class="author">Daniel J. Boorstin</div> 
                 
                </div>
                <div class="item">
                <p>"An image is not simply a trademark, a design, a slogan or an easily remembered picture. It is a studiously crafted personality profile of an individual, institution,corporation, product or service"</p>
                <div class="author">Daniel J. Boorstin</div> 
                 
                </div>
                <div class="item">
                <p>"An image is not simply a trademark, a design, a slogan or an easily remembered picture. It is a studiously crafted personality profile of an individual, institution,corporation, product or service"</p>
                <div class="author">Daniel J. Boorstin</div> 
                 
                </div>
                <div class="item">
                <p>"An image is not simply a trademark, a design, a slogan or an easily remembered picture. It is a studiously crafted personality profile of an individual, institution,corporation, product or service"</p>
                <div class="author">Daniel J. Boorstin</div> 
                
                </div>
                
              </div>
            </div>
          </div>
                
        </div>
      </section>
     
    </main>
   <jsp:include page="include/footer.jsp"></jsp:include>