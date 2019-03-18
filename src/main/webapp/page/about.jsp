<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="keywords" content="freemi, about freemi" />
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<meta name="description" content="About us." />
<meta name="robots" content="follow,index" />
<link
	href="<c:url value="${contextcdn}/resources/css/about.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Us</title>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<jsp:include page="include/header.jsp"></jsp:include>
<body class="back_set">
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<div class="freemi_container" style="padding-top: 0px;">
		<div class="container-fluid about_us">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-lg-8 about_heading">
						<h1 style="font-family: roboto_slablight; font-weight: 400;">About
							Us</h1>
						<span>Thinking ahead</span>
					</div>
					<div class="col-md-4 col-lg-4">
						<div class="display_full">
							<img
								src="<c:url value="${contextcdn}/resources/images/amfi2.png"/>" alt="URN-141396"
								class="img-fluid" style="height: 240px;">
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="container">
			<div style="text-align: justify;">
				<h3>Why we are:</h3>
				<p class="why_we">"FreEMI" is a fin-tech platform that is
					dedicated to serving those Indian households that are in need of
					banking facilities, but are unfortunately not able to benefit from
					the system. We are working to bring affordable need-based instant
					credit system that will largely benefit the low income group and
					small businesses. We have products in the market come in attractive
					categories and have a sound technological foundation that gains its
					strength from our proprietary algorithms.</p>

				<p>We are also firm believers in the power of social ecosystems
					and are of the view that, if we spread goodness in the society we
					live in, the favour will be returned in kind. This thinking makes
					us open to collaborations with like-minded entities and people on
					our path to growth and expansion.</p>
				<p>Our products are made to help an individual get the required
					funds when they need it the most and in an affordable manner. Our
					one-of-a-kind investment platform works by leveraging the power of
					compounding, thereby turning good expenses into investment. We
					offer products that vary from banking loans to insurance plans.</p>
					
				<div class="row">
				<div class="col-md-3 col-lg-3">
				<img src="<c:url value="${contextcdn}/resources/images/about1.png"/>" alt="Paperless transaction"
								class="img-fluid img_type" >
				
				</div>			
				<div class="col-md-3 col-lg-3">
				<img src="<c:url value="${contextcdn}/resources/images/about2.png"/>" alt="Secure platform"
								class="img-fluid img_type">
				</div>
				<div class="col-md-3 col-lg-3">
				<img src="<c:url value="${contextcdn}/resources/images/about3.png"/>" alt="Team of experts"
								class="img-fluid img_type" >
				</div>
				
				</div>
					
				<p>We have tied-up with many banks and insurers to provide you
					with great alternatives and a wide choice for all your financial
					requirements. For our FreEMI Secure service, our tagline is
					'<strong>Insurance for All</strong>'. This tagline aptly conveys the
					social angle that is intrinsic to our business model, which means
					that an individual at the very least, needs to have a minimum
					health and life cover so as to enable him to live their life with
					dignity and confidence.</p>
				<p>FreEMI also has a strong distribution footprint that is
					evident from our presence in various market segments and across
					demographic. Our B2B model is developed with an open architecture
					that makes our product and process transparent for everybody
					&ndash; the customer, distributor and lending partner. Our social
					approach is one of our strengths and unique points, which can only
					be developed through ethical practices and transparency in managing
					customer relations. Adding to that, we have a strong integration
					process with lending partners and a clear, unambiguous proposition
					for our lending partners. We are always on the lookout for suitable
					partners who share our vision and values and have the zeal to make
					a positive impact on society. Reputed business houses and reliable
					distributors are welcome to join us in our quest and make FreEMI a
					household name in the financial eco-system.</p>

			</div>

		</div>
	</div>
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>