<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head lang="en">
<meta name="keywords" content="Credit Line, Instant Credit,Personal Loan,Home Loan, Business Loan, Instant Credit, Credit Crad, No Cost EMI,personal loan, personal loan rate" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description" content="Details of Personal Loan. Personal Loan bridges the gap between what we have and what we need. Personal Loan is an unsecured loan." />
<meta name="robots" content="index,follow" />
<meta property="og:url" content="https://www.freemi.in/products/loans" />
<meta property="og:title" content="One stop solution for your personal loan, home loan, Credit cards" /> 
<meta property="og:description" content="Details of Personal Loan. Personal Loan bridges the gap between what we have and what we need. Personal Loan is an unsecured loan." />
<meta property="og:type" content="website" />
<meta property="og:image" content="https://resources.freemi.in/fbshare/images/loans2.png" />
<meta property="og:image:secure_url" content="https://resources.freemi.in/fbshare/images/loans2.png" />
<meta property="og:image:type" content="image/png" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://www.freemi.in/products/loans/" rel="canonical">
<title>FreEMI Loans</title>
<link href="<c:url value="${contextcdn}/resources/css/freeEmi2.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/animate.css"/>"
	type="text/css" rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>" rel="stylesheet">
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>
<script src="<c:url value="${contextcdn}/resources/js/freemi.js" />"></script>

<!-- EMI Calculator Widget START --><script async src="https://emicalculator.net/widget/2.0/js/emicalc-loader.min.js"></script><!-- EMI Calculator Widget END -->

<jsp:include page="include/bootstrap.jsp"></jsp:include>
<style>
.custom_box {
	box-shadow: unset;
	border: none none none none;
	border-left: none;
	border-right: none;
	border-top: none;
	border-radius: 0;
}
</style>
</head>

<body>
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="freemi_container">

		<div class="section1">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-lg-12 col_design1">
						<div class="topic1 animated bounceInUp">
							<span>The Next Generation Loans &amp; Cards Marketplace</span>
						</div>
					</div>
				</div>

				<c:if test="${REQUESTSUCCESS=='Y' }">
					<div class="alert alert-success" role="alert">
						<img
							src="<c:url value="${contextcdn}/resources/images/agent-support.png"/>" alt="Personal Loan"
							class="img-fluid rounded-circle"
							style="height: 50px; border: 1px solid #ec8439; padding: 5px;">
						<span>${requestmessage }</span>
					</div>
				</c:if>


				<div class="row">
					<div class="col-md-4 col-lg-4">
						<div class="col_design2 animated bounceInLeft">
							<h5>PERSONAL LOAN</h5>
							<img
								src="<c:url value="${contextcdn}/resources/images/personal_loan.png"/>" alt="Personal loan"
								class="img-fluid rounded-circle"
								style="height: 50px; border: 1px solid #ec8439; padding: 5px;">


							<div class="row topic2">
								<div class="col-md-12 col-lg-12 ">
									<div>
										<span id="plerror"></span>
									</div>
									<form:form method="POST" action="${pageContext.request.contextPath}/personalLoan.do"
										commandName="personalLoan">
										<form:hidden path="requestCategory" />

										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="name" id="plusername" maxlength="64"
											placeholder="Your Name" onkeyup="validateplForm()" />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="mobile" id="plusermobile" maxlength="10"
											pattern="[0-9]*" placeholder="Mobile number"
											onkeyup="validateplForm()" data-toggle="tooltip"
											data-placement="top" title="We guarantee your privacy." />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="email" id="pluseremail" maxlength="64"
											placeholder="Email Id (Optional)" onkeyup="validateplForm()" />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="location" id="pluserlocation"
											maxlength="16" placeholder="City" onkeyup="validateplForm()" />
										<form:checkbox value="Y" path="dndAgree" id="plagree"
											onclick="validateplForm();" label="I have read and agreed to " />
										<a href="${pageContext.request.contextPath}/products/terms-of-use" style="font-size: 12px;">Terms of Use</a>
										
										<div>
										<button type="submit" class="btn btn-sm btn-primary mb-1"
											id="plsubmit" disabled="disabled"
											onclick="return validateplForm();">APPLY NOW</button>
										</div>
									</form:form>
								</div>
							</div>

							<div class="content1">
								<span><strong>Loan Duration:</strong> 12 - 60 months </span> <br>
								<span><strong>Interest rate:</strong> 10.99% &amp; above</span>
								<br> <span>T&amp;C Applied</span>
							</div>

						</div>

					</div>
					<div class="col-md-4 col-lg-4">
						<div class="col_design2 animated fadeIn">
							<h5>HOME LOAN</h5>
							<img
								src="<c:url value="${contextcdn}/resources/images/home_loan2.png"/>" alt="Home Loan"
								class="img-fluid rounded-circle"
								style="height: 50px; border: 1px solid #1c7fd4; padding: 5px;">
							<div class="row topic2">
								<div class="col-md-12 col-lg-12">
									<form:form method="POST" action="${pageContext.request.contextPath}/homeLoan.do"
										commandName="homeLoan">
										<form:hidden path="requestCategory" />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="name" id="hlusername" maxlength="64"
											placeholder="Your Name" onkeyup="validatehlForm()" />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="mobile" id="hlusermobile" maxlength="10"
											pattern="[0-9]*" placeholder="Mobile number"
											onkeyup="validatehlForm()" data-toggle="tooltip"
											data-placement="top" title="We guarantee your privacy." />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="email" id="hluseremail" maxlength="64"
											placeholder="Email Id (Optional)" onkeyup="validatehlForm()" />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="location" id="hluserlocation"
											maxlength="16" placeholder="City" onkeyup="validatehlForm()" />
										<form:checkbox value="Y" path="dndAgree" id="hlagree"
											onclick="validatehlForm();" label="I have read and agreed to " />
										<a href="${pageContext.request.contextPath}/products/terms-of-use" style="font-size: 12px;">Terms of Use</a>
										
										<div>
										<button type="submit" class="btn btn-sm btn-primary mb-1"
											id="hlsubmit" disabled="disabled"
											onclick="return validatehlForm();">APPLY NOW</button>
										</div>
									</form:form>
								</div>
							</div>
							<div class="content1">
								<span><strong>Loan Duration:</strong> Upto 30 yrs </span> <br>
								<span><strong>Interest rate:</strong> 8.35% &amp; and
									above <sup>*</sup></span> <br> <span>T&amp;C Applied</span>
							</div>

						</div>
					</div>
					<div class="col-md-4 col-lg-4">
						<div class="col_design2 animated bounceInRight">
							<h5>CREDIT CARD</h5>
							<img
								src="<c:url value="${contextcdn}/resources/images/credit_card.png"/>" alt="Credit card"
								class="img-fluid rounded-circle"
								style="height: 50px; border: 1px solid #d43b2c; padding: 5px;">
							<div class="row topic2">
								<div class="col-md-12 col-lg-12">
									<form:form method="POST" action="${pageContext.request.contextPath}/creditCard.do"
										commandName="creditCard">
										<form:hidden path="requestCategory" />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="name" id="ccusername" maxlength="64"
											placeholder="Your Name" onkeyup="validateccForm()" />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="mobile" id="ccusermobile" maxlength="10"
											pattern="[0-9]*" placeholder="Mobile number"
											onkeyup="validateccForm()" data-toggle="tooltip"
											data-placement="top" title="We guarantee your privacy." />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="email" id="ccuseremail" maxlength="64"
											placeholder="Email Id (Optional)" onkeyup="validateccForm()" />
										<form:input
											class="form-control form-control-sm mb-1 custom_box"
											type="text" path="location" id="ccuserlocation"
											maxlength="16" placeholder="City" onkeyup="validateccForm()" />
										<form:checkbox value="Y" path="dndAgree" id="ccagree"
											onclick="validateccForm();" label="I have read and agreed to " />
										<a href="${pageContext.request.contextPath}/products/terms-of-use" style="font-size: 12px;">Terms of Use</a>
										
										<div>
										<button type="submit" class="btn btn-sm btn-primary mb-1"
											id="ccsubmit" disabled="disabled"
											onclick="return validateccForm();">APPLY NOW</button>
										</div>
									</form:form>
								</div>
							</div>
							<div class="content1">
								<span>T&amp;C Applied</span>
							</div>

						</div>
					</div>


				</div>

				<div class="row animated zoonIn" style="margin-top: 20px;">
					<div class="col-md-6 col-lg-6" style="margin: auto;">
						<div class="button_eli animated fadeIn">
							<a href="${pageContext.request.contextPath}/register">
								<button type="button" class="btn btn-sm btn-block btn-light"
									style="color: #ff7826; font-weight: 500;">Sign Up</button>
							</a>
						</div>
					</div>
				</div>
				<div class="row" style="padding: 0px 30px;">
					<div class="col-md-6 col-lg-6 loan_share" style="margin: auto;">
						<div><strong>Share our page.</strong></div>
						<div class="fb-share-button" data-href="https://www.freemi.in/products/loans/" data-layout="button_count" data-size="large" data-mobile-iframe="true"><a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.freemi.in%2Fproducts%2Floans%2F&amp;src=sdkpreparse" class="fb-xfbml-parse-ignore">Share</a></div>
					</div>
				</div>
				
			</div>

		</div>
		
		<div class="section2" style="padding-bottom: 20px;">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-lg-12">
						<div style="color: #fbff40;text-align: center;font-size: 16px;">
						<!-- <h3 >EMI Calculator</h3> -->
						<img
								src="<c:url value="${contextcdn}/resources/images/fm_calculator.png"/>"
								class="img-fluid rounded-circle"
								style="height: 60px; border: 1px solid white; padding: 5px;">
						<p>Simple Calculator to check your Interest</p>
						</div>
						<div id="ecww-widgetwrapper" style="min-width:250px;width:100%;"><div id="ecww-widget" style="position:relative;padding-top:0;padding-bottom:280px;height:0;overflow:hidden;"></div>
						<!-- <div id="ecww-more" style="background:#333;font:normal 13px/1 Helvetica, Arial, Verdana, Sans-serif;padding:10px 0;color:#FFF;text-align:center;width:100%;clear:both;margin:0;clear:both;float:left;"><a style="background:#333;color:#FFF;text-decoration:none;border-bottom:1px dotted #ccc;" href="https://emicalculator.net/" title="Loan EMI Calculator" rel="nofollow" target="_blank">emicalculator.net</a>
						</div> -->
						</div>
					</div>
				</div>
			</div>

		</div>
		
		<div class="section3">
			<div class="container animated slideInUp">
				<div class="row">
					<div class="col-md-12 col-lg-12">
						<h3 style="color: #fbff40;">Our Partners</h3>
						<div class="partners">
							<span>We are partnered with major Banks and NBFCs.</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		

		<div class="section4">
			<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-12">
				<div id="accordion">
					<div class="card">
						<div class="card-header" id="headingOne">
							<h5 class="mb-0">
								<button class="btn btn-link" data-toggle="collapse"
									data-target="#collapseOne" aria-expanded="false"
									aria-controls="collapseOne">
									<span class="loan_header">PERSONAL LOAN AT A GLANCE  <i class="fas fa-arrow-circle-down"></i></span>
								</button>
								
							</h5>
						</div>

						<div id="collapseOne" class="collapse show"
							aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body" style="max-height: 400px; overflow: scroll; color: #727374;">

								<p>Personal Loan bridges the gap between what we have and
									what we need.</p>
								<p>Personal Loan is an unsecured loan.</p>

								<h5 class="loan_header_topic">TO DECIDE: WHETHER TO TAKE LOAN
									OR NOT?</h5>
								<p>
									One need to first figure out whether to meet the need of fund
									from our savings or to take up a personal loan.<br />You need
									to consider thoroughly the Pros and Cons of both Personal Loan
									and Money taken from<br />Savings.<br />If you decide to take
									out money from savings, you need to Plan Your Savings, so that,
									you can manage your good expenses <a href="https://www.freemi.in/products/registry-mutual-funds">Registry Planner</a><br />But,
									if you decide to opt for a Personal Loan, you need to get full
									information on it.
								</p>

								<h5 class="loan_header_topic">Details regarding PERSONAL LOAN.</h5>

								<p>
									Here, we provide full information regarding the process of
									initializing and<br />completing the process of Personal Loan.
								</p>
								<h5 class="loan_header_topic">LOAN FROM BANK OR NBFC</h5>
								<p>One need to decide from where to take the Loan- BANK or
									NBFC(NON-BANKING FINANCIAL COMPANY).</p>
								<p>If you decide to take the loan from Bank-</p>
								<ul>
									<li>You need to have a good credit history and CIBIL
										score.</li>
									<li>The Interest rate is set under the regulation of RBI,
										which is quite high.</li>
									<li>Banks operate under the norms of RBI Banking Act.</li>
									<li>The loan approval process is quite rigorous.</li>
								</ul>
								<p>If you decide to take it from NBFC-</p>
								<ul>
									<li>You need to have good CIBIL score, but also with low
										credit score on high interest rate.</li>
									<li>The interest rate is generally low.</li>
									<li>NBFC operate under Companies Act.</li>
									<li>The loan approval process is quite lenient.</li>
								</ul>
								<h5 class="loan_header_topic">ELIGIBILITY CRITERIA</h5>
								<p>Check the eligibility criteria by talking to a personal
									finance executive.</p>
								<p>Following are the criterias which are essential to follow
									to approve the process of personal loan:</p>
								<ul style="list-style-type: square;">
									<li><strong>Age Limit-</strong> Min 23 years and Max 58
										years</li>
									<li><strong>Nationality-</strong> Indian</li>
									<li><strong>Employment Type-</strong> Salaried or Self
										Employed</li>
									<li><strong>Employment Status-</strong> Employed for at
										least 2 years and at least 1 year under the present employer</li>
									<li><strong>Minimum Income-</strong> Rs.20,000 (excluding
										incentives)</li>
									<li><strong>Maximum Loan amount-</strong> Rs 50 lakhs
										(differs accordingly)</li>
									<li><strong>Loan Tenure-</strong> Min 6 months and Max 60
										months</li>
									<li><strong>Credit Score-</strong> 750 points preferred</li>
								</ul>
								<p>&nbsp;</p>
								<h5 class="loan_header_topic">DOCUMENTS REQUIRED</h5>
								<p>
									You need to inquire the documents required for the loan.<br />Following
									are the important documents to apply for the personal loan:
								</p>
								<ul style="list-style-type: square;">
									<li>Last 3 months salary slip.</li>
									<li>2 years old salary slip or appointment letter.</li>
									<li>Photocopy of Company ID card.</li>
									<li>Last 6months bank statement (where salary is
										credited).</li>
									<li>Photocopy of PAN Card.</li>
									<li>Photocopy of AADHAR Card.</li>
									<li>Latest Property Tax Receipt (if owned house) Or
										Notarized Rent Agreement.</li>
									<li>1 Passport Size Photograph.</li>
									<li>Personal Loan Repayment Schedule/SOA (Statement of
										Account) i.e, Personal Loan EMI statement from 1st till
										date.(IF YOU ARE ALREADY HAVING A PERSONAL LOAN)</li>
								</ul>
								<h5 class="loan_header_topic">FILL UP APPLICATION</h5>
								<p>Along with the proper documents, you should fill up the
									application&nbsp; with the help of an expert.</p>
								<h5 class="loan_header_topic">SUBMISSION</h5>
								<p>After the successful fill up of documents. the
									application is submitted.</p>
								<h5 class="loan_header_topic">VERIFICATION PROCESS</h5>
								<p>Then the verification process initiates. You should
									follow the approval process and cooperate with the financial
									institution for physical verification and on calls.</p>
								<h5 class="loan_header_topic">APPROVAL PROCESS</h5>
								<p>The approval of the loan application will be based on
									positive verification and positive CIBIL report.</p>
								<h5 class="loan_header_topic">DISBURSEMENT</h5>
								<p>After the approval of the loan, bank personnel officials
									will contact you for disbursement agreement/loan agreement.</p>
								<h5 class="loan_header_topic">CREDIT</h5>
								<p>Once the agreement process is completed, finally bank
									will credit the amount into your account or will issue a
									cheque.</p>
								<p>&nbsp;</p>
							</div>
						</div>
					</div>

				</div>



				<!-- <div class="row">
					<div class="col-md-10 col-lg-10 contents_pl" style="float: left;">
						<h5 style="color: #246e90;">Personal loan Overview</h5>
						<div class=" row content_style">
							<div class="col-md-4 col-lg-4">
								<div>
									<span>Personal Loan Interest Rates:</span>
								</div>
								<div>
									<label><strong>Interest Rate:</strong> </label><span> 10.99% onwards</span>
								</div>
								<div>
									<label><strong>Loan Tenure:</strong> </label><span> Up to 5 Years</span>
								</div>
								<div>
									<label><strong>Processing Fees :</strong> </label><span> Vary from bank to
										bank, NBFC's</span>
								</div>
							</div>
							<div class="col-md-8 col-lg-8" >
								<div style="pl_content2">
									<span>Personal Loan pre-closure charges vary from bank
										to bank, NBFC's as well. Personal loans carry highest interest
										rates across debt products only superseded by credit card
										interest rates. 
										<br>
										Presently interest rate for personal loans are
										available 10.99% onwards in India. The actual interest rate
										vary from bank to bank &amp; NBFc's on the basis of the loan
										amount, tenure, applicant income &amp; your credit history.</span>
								</div>
							</div>


						</div>

					</div>
				</div> -->

				<!-- Home loan  -->
				<!-- <div class="row">
					<div class="col-md-10 col-lg-10 contents_pl" style="float: right;">
						<h5 style="color: #246e90;">Home loan Overview</h5>
						<div class=" row content_style">
							<div class="col-md-4 col-lg-4">
								<div>
									<span>Home Loan Interest Rates:</span>
								</div>
								<div>
									<label><strong>Interest Rate:</strong> </label><span> 10.35% onwards</span>
								</div>
								<div>
									<label><strong>Loan Tenure:</strong> </label><span> Up to 30 Years</span>
								</div>
								<div>
									<label><strong>Processing Fees :</strong> </label><span> Vary from bank to bank</span>
								</div>
							</div>
							<div class="col-md-8 col-lg-8" >
								<div style="pl_content2">
									<span></span>
								</div>
							</div>


						</div>

					</div>
				</div> -->
				<!-- End HL  -->

				</div>
				</div>

			</div>

		</div>


	</div>



	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>

