<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="/" />
<link rel="shortcut icon" type="image/x-icon"
	href="${contextPath}/freemi.ico" />
<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-bg-dark"
	style="background-color: #18445b !important">
	<div class="container">
		<a class="navbar-brand" href="${contextPath}"> <img
			src="<c:url value="${contextcdn}/resources/images/freemi.png"/>" class="img-fluid"
			style="height: 45px;" alt="Logo"> <span
			style="font-size: 12px; margin-left: -10px;"> <sub> <i>beta</i>
			</sub>
		</span>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link home"
					href="${contextPath}"> <span> <i class="fa fa-home"
							style="font-size: xx-large; color: blanchedalmond;"
							aria-hidden="true"></i>
					</span>
				</a></li>
				<!-- <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span><i class="fa fa-cogs" style="color: chartreuse; font-size: 14px;" aria-hidden="true"></i> Products</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="freeEmi"><span><img src="assets/images/freeemi.png" width="30" height="5"> freeMI</span></a>
                        <a class="dropdown-item" href="fsecure"><span><img src="assets/images/fsecure.png" alt="Insurance" width="30" height="5"> F-Secure</span></a>
                        <a class="dropdown-item" (click)="goToRegistry()"><span><img src="assets/images/registry.jpg" width="30" height="5"> Registry</span> </a>
                    </div>
                </li> -->
				<li class="nav-item">
					<div class="nav-div">
						<img src="<c:url value="${contextcdn}/resources/images/freemi_icon1.png"/>" alt="FreEMI logo"
							width="30" height="5" style="position: absolute;"> <a
							class="nav-link" style="margin-left: 30px; padding: 0;" href="/products/loans/">Loans <!-- <span
							class="header_subname"> Loans</span> -->
						</a>
					</div>
				</li>
				<li class="nav-item">
					<div class="nav-div">
						<img src="<c:url value="${contextcdn}/resources/images/fsecure.png"/>" alt="Insurance product"
							width="30" height="5" style="position: absolute;"> <a
							class="nav-link" style="margin-left: 30px; padding: 0;" href="/products/fsecure-insurance/">F-Secure <br> <span
							class="header_subname"> Insurance</span>
						</a>
					</div>

				</li>
				<li class="nav-item">
					<div class="nav-div">
						<img src="<c:url value="${contextcdn}/resources/images/registry.png"/>" alt="Mutual fund based product icon"
							class="img-fluid" width="30" height="5"
							style="position: absolute;"> <a class="nav-link"
							href="/products/registry-mutual-funds/"
							style="margin-left: 30px; padding: 0;">Mutual Fund Registry<!-- <br> <span
							class="header_subname">A product of Mutual Funds</span> -->
						</a>
					</div>
				</li>

				<!-- <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      Registry
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                      <a class="dropdown-item" href="#">Action</a>
                      <a class="dropdown-item" href="#">Another action</a>
                      <a class="dropdown-item" href="#">Something else here</a>
                      <li class="dropdown-submenu">
                        <a  class="dropdown-item" tabindex="-1" href="#">Family</a>
                        <ul class="dropdown-menu">
                          <li class="dropdown-item"><a tabindex="-1" href="#">Tax Planning</a></li>
                        </ul>
                      </li>
                    </div>
                </li> -->

				<li class="nav-item">
                    <a class="nav-link" href="http://blog.freemi.in/freemiblogs/" target="_blank">
                        <span>
                            <i class="fas fa-rss" style="color: aqua;" aria-hidden="true"></i> Blogs</span>
                    </a>
                </li>
				<li class="nav-item">
					<a
					class="nav-link" href="/products/tax-calculator"> <span>
							<i class="fas fa-calculator" style="color: darkorange;"
							aria-hidden="true"></i> Calculator
					</span>
				</a>
				</li>
				<%-- <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/contact">
                        <span>
                            <i class="fas fa-envelope" style="color: whitesmoke;" aria-hidden="true"></i> Contact Us</span>
                    </a>
                </li> --%>

				<c:choose>
					<c:when test="${not empty sessionScope.loggedSession }">
						<li class="nav-item dropdown user"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <img
								src="<c:url value="${contextcdn}/resources/images/avatar.png"/>"
								class="img-fluid" style="height: 1.75em; margin-top: -5px;">
								<%-- <span> Welcome ${loggedInUser }</span> --%> <span>Welcome
									<c:out value="${sessionScope.loggedSession }"></c:out>
							</span>
						</a>
							<div class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<a class="dropdown-item" href="/products/my-dashboard">
									<i class="fas fa-archive" style="color: rgb(84, 84, 236);"></i>
									My dashboard
								</a> <a class="dropdown-item" href="/products/profile"> <i
									class="fas fa-user"></i> Profile
								</a> <a class="dropdown-item" href="/products/logout"> <i
									class="fas fa-power-off" style="color: rgb(238, 56, 56)"></i>
									Log out

								</a>
							</div></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="/products/register"> <span style="color: coral;">
									Sign up <i class="fas fa-user-plus"></i>
							</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/products/login"> <span style="color: coral;">
									Log In <i class="fas fa-sign-in-alt"></i>
							</span>
						</a></li>

					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</div>
</nav>