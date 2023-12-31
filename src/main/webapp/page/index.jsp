<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
<meta name="keywords" content="Credits, mutual funds, best mutual fund, interest rate, compare interest rate, investment plans" />


<meta name="description" content="Get best comparison on credit interest rate and mutual fund plans online in India" />
<link rel="canonical" href="https://www.freemi.in/" />
<title>FreEMI - Live life digital</title>
<link href="<c:url value="${contextcdn}/resources/css/styles.min.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="${contextcdn}/resources/css/pace-theme.css"/>" rel="stylesheet" type="text/css">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script src="<c:url value="${contextcdn}/resources/js/home.js" />"></script>
<script src="<c:url value="${contextcdn}/resources/js/pace.min.js" />"></script>

<script>
	function formOnLoad() {
		var isuserpresent = <%=session.getAttribute("userid")%>
	var campaignsubmitted = <%=session.getAttribute("campaignsubmitted")%>
		if ((isuserpresent == null && campaignsubmitted == null)) {
			setTimeout(call, 5000);
		}
	}

	function call() {
		//console.log("Called popup")
		{
			$('#exampleModalCenter').modal('show');
		}
	}

	$(document).ready(function() {
		$('.slider1').slick({
			slidesToShow : 3,
			slidesToScroll : 1,
			autoplay : true,
			autoplaySpeed : 2000,
			arrows : false,
			responsive : [ {
				breakpoint : 1024,
				settings : {
					slidesToShow : 3,
					slidesToScroll : 3,
					infinite : true,
					dots : true
				}
			}, {
				breakpoint : 600,
				settings : {
					slidesToShow : 2,
					slidesToScroll : 2
				}
			}, {
				breakpoint : 480,
				settings : {
					slidesToShow : 1,
					slidesToScroll : 1
				}
			}
			// You can unslick at a given breakpoint now by adding:
			// settings: "unslick"
			// instead of a settings object
			]

		});

	});
</script>

</head>

<body class="back_set" onload="formOnLoad();">
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<div class='thetop'></div>
	<jsp:include page="include/header.jsp"></jsp:include>

	<div class="container-fluid freemi_container"
		style="padding: 2px 0px 20px 0px;">


		<div id="carouselExampleFade" class="carousel slide carousel-fade"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<a href="/products/loans"> <img
						class="d-block img-fluid" style="width: 100%;"
						src="<c:url value="${contextcdn}/resources/images/h1.png"/>" alt="Loans">
					</a>
				</div>
				<div class="carousel-item">
					<a href="/products/fsecure"> <img
						class="d-block img-fluid" style="width: 100%;"
						src="<c:url value="${contextcdn}/resources/images/h3.png"/>"
						alt="Insrurance">
					</a>
				</div>

				<div class="carousel-item">
					<a href="/products/registry-mutual-funds">
						<img class="d-block img-fluid" style="width: 100%;"
						src="<c:url value="${contextcdn}/resources/images/h2.png"/>"
						alt="Registry based Mutual Funds">
					</a>
				</div>
				<div class="carousel-item">
					<img class="d-block img-fluid" style="width: 100%;"
						src="<c:url value="${contextcdn}/resources/images/h4.png"/>"
						alt="aadhaar ekyc">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleFade"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleFade"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>

		<div class="row" style="margin: auto; padding-top: 90px;">
			<div class="col-md-8"
				style="margin: auto; text-align: center ! important;">
				<h3 style="font-size: 48px; font-weight: 300">
					Let's change<br> <span style="font-weight: 600;">the
						way</span> we spend
				</h3>
				<div>
					<p class="freemi_obj">FreEMI is a fin-tech platform of today's
						that is dedicated to providing instant need-based solutions
						to households and small businesses that are not being able to
						benefit from the banking system in India.</p>

				</div>
			</div>
		</div>

		<!-- <div class="container"> -->
		<div class="strike-line">
			<span> <img
				src="<c:url value="${contextcdn}/resources/images/10.png"/>"
				style="margin-bottom: -40px;" alt="Why Us">
			</span>
		</div>
		<!-- </div> -->

		<section style="background: #f87e45;padding: 40px 0px;">
		<div class="row" style="margin: auto;">
			<div class="col-sm-3 col-md-3 col-lg-3 why_inner2"
				style="margin: -1px;">
				<div data-aos="fade-right">
					<div class="why_inner_des1">
						<div>
							<img
								src="<c:url value="${contextcdn}/resources/images/plan1.png"/>"
								class="img-fluid plan_image" alt="technology">
							<h2 class="us_style">Technology</h2>
						</div>
						<div class="plan_content">
							<p>Our products are unique, affordable and simple, yet backed
								by intelligence and efficiency</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-md-3 col-lg-3 why_inner2">
				<div data-aos="fade-up-right">
					<div class="why_inner_des1">
						<div>
							<img
								src="<c:url value="${contextcdn}/resources/images/plan2.png"/>"
								class="img-fluid plan_image" alt="products">
							<h2 class="us_style">Product</h2>
						</div>
						<div class="plan_content">


							<p>Our products include mutual funds, insurance and loans
								that have been designed on proprietary algorithms to meet your
								need for funds in an affordable and convenient way.</p>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-3 col-md-3 col-lg-3 why_inner2">
				<div data-aos="fade-up-left">
					<div class="why_inner_des1">
						<div>
							<img
								src="<c:url value="${contextcdn}/resources/images/plan4.png"/>"
								class="img-fluid plan_image" alt="market reaach">
							<h2 class="us_style">Collaboration</h2>
						</div>
						<div class="plan_content">
							<p>We have collaborated with some of the leading lending
								institutions to offer a wide range of financial solutions to a
								range of needs</p>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-3 col-md-3 col-lg-3 why_inner2">
				<div data-aos="fade-left">
					<div class="why_inner_des1">
						<div>
							<img
								src="<c:url value="${contextcdn}/resources/images/plan3.png"/>"
								class="img-fluid plan_image" alt="market reaach">
							<h2 class="us_style">Reach</h2>
						</div>
						<div class="plan_content">
							<p>What makes our B2B model stand apart is transparency, so
								that all the stakeholders - customers, distributors and lending
								partners - are in the know of the products we offer and how we
								reach out to our target audience</p>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- </div> --> </section>

		<div class="row" style="margin: auto; padding: 50px 5px;">
			<div class="col-md-8 col-lg-8">
				<h1 style="color: #353535;">Benefit of Registry</h1>
				<div>
					<span>Bring a systematic planning for a better return from your investments</span>
				</div>
				<div style="margin-top: 20px;">
					<a href="${pageContext.request.contextPath}/register"
						style="text-decoration: none;">
						<!-- <button
							class="btn btn-lg btn-primary"
							style="background-color: #38b05a; border-color: transparent;">
							<strong>Join Us</strong>
						</button> -->
						<span class="whatsapp_button">Join Us</span>
						
						</a>
				</div>
				<div class="row" style="margin-top: 15px;">
					<div class="col-md-3 col-lg-3">
						<h3>Need Help?</h3>
						<h6 style="color: #e66428;">Call or Whatsapp us at</h6>
					</div>
					<div class="col-md-8 col-lg-8">

						<img alt="Whatsapp"
							src="<c:url value="${contextcdn}/resources/images/whatsapp.png"/>"
							class="img-fluid" style="height: 30px; margin-bottom: 10px;">
						<a
							href="https://api.whatsapp.com/send?phone=918697843404&text=Hey%20there!%20I%20need%20help%20with%20loans.&source=&data="
							target="_blank"><span
							style="font-size: 24px; font-weight: 600;">+91-8697843404</span></a>
					</div>

				</div>
			</div>
		</div>

		<div class="why_design" style="margin: auto;">
			<div class="col-12 how_heading">
				<span>How it works?</span>
			</div>
			<div class="slider1">
				<div>
					<img src="<c:url value="${contextcdn}/resources/images/how1.png"/>" class="img-fluid" alt="Let us know your wish">
				</div>
				<div>
					<img
						src="<c:url value="${contextcdn}/resources/images/how2.png"/>" class="img-fluid" alt="We evaluate using our extensive evaluation process and proprietary algorithms">
				</div>

				<div>
					<img
						src="<c:url value="${contextcdn}/resources/images/how3.png"/>" class="img-fluid" alt="After evaluation, we suggest the best possible alternatives, giving you the flexibility to choose from a range of financial products">
				</div>

				<div>
					<img
						src="<c:url value="${contextcdn}/resources/images/how4.png"/>" class="img-fluid" alt="A one-tap one-view dashboard for both you and FreEMI, such that everything can be viewed real-time">
				</div>

				<div>
					<img src="<c:url value="${contextcdn}/resources/images/how5.png"/>" class="img-fluid" alt="A one-tap one-view dashboard for both you and FreEMI, such that everything can be viewed real-time">
				</div>
			</div>

		</div>

	</div>

	<jsp:include page="campaign/signup.jsp"></jsp:include>
	<%-- <jsp:include page="include/social-bar.jsp"></jsp:include> --%>
	<jsp:include page="include/footer.jsp"></jsp:include>
	<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
	<script>
		AOS.init();
	</script>
	<!-- </div> -->
</body>
</html>