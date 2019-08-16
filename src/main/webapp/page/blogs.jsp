<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="keywords" content="investment blogs, glogs, financial blogs, mutual fund blogs, how to invest" />


<meta name="title" content="Blogs on investment" />
<meta name="description" content="Read the blogs to understand more about invetments. Get information on Mutual Funds & loans @ FreEMI." />
<meta name="robots" content="index,follow" />
<link href="<c:url value="${contextPath}/resources/css/blog-home.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="${contextPath}/resources/css/pagination-contents.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextPath}/resources/css/styles.css"/>"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<jsp:include page="include/bootstrap.jsp"></jsp:include>
</head>
<body class="back_set"
	>
	<jsp:include page="include/header.jsp"></jsp:include>
	<div class="container freemi_container">
		<div class="blog-header">
			<h4>Welcome to the Blog</h4>
		</div>

		<div>
			<div class="container">
				<div>

					<div class="row">
					
						<div class="col-md-3 col-lg-3 post-topic-outer">
						<div class="post-topic-inner">
							<div class="blog_topic">
								<span>Why FreEMI.in?</span>
							</div>
							<div class="blog_task">
								<a href="/freemi/blogs/awareness-freemi-investment-marketplace"
									style="text-decoration: none;"><button type="button"
										class="btn btn-sm btn-primary">Read it</button></a>
							</div>
							</div>
						</div>
					
						<div class="col-md-3 col-lg-3 post-topic-outer">
						<div class="post-topic-inner">
							<div class="blog_topic">
								<span>Why Insurance??</span>
							</div>
							<div class="blog_task">
								<a href="/freemi/blogs/why-insurance"
									style="text-decoration: none;"><button type="button"
										class="btn btn-sm btn-primary">Read it</button></a>
							</div>
							</div>
						</div>

						<div class="col-md-3 col-lg-3 post-topic-outer">
						<div class="post-topic-inner">
							<div class="blog_topic">
								<span>All about life insurance</span>
							</div>
							<div class="blog_task">
								<a href="/freemi/blogs/all-about-life-insurance"
									style="text-decoration: none;"><button type="button"
										class="btn btn-sm btn-primary">Read it</button></a>
							</div>
							</div>
						</div>

						<div class="col-md-3 col-lg-3 post-topic-outer">
						<div class="post-topic-inner">
							<div class="blog_topic">
								<span>Online Mutual Fund SIP Investment</span>
							</div>
							<div class="blog_task">
								<a href="/freemi/blogs/online-mutual-fund-sip-investment"
									style="text-decoration: none;"><button type="button"
										class="btn btn-sm btn-primary">Read it</button></a>
							</div>
							</div>
						</div>

					</div>

				</div>
			</div>
		</div>

	</div>
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>