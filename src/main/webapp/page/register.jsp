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
<link href="<c:url value=" ../products/resources/css/new-login.css" />"
rel="stylesheet">
<%--<link
	href="<c:url value="${contextcdn}/resources/css/register.component.css"/>"
	rel="stylesheet">
<link href="<c:url value="${contextcdn}/resources/css/styles.css"/>"
	rel="stylesheet"> --%>

<link
	href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css"
	rel="stylesheet" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<title>New to FreEMI? Get registered today.</title>
<style type="text/css">
@media screen and (max-width: 320px) {
	.custombtnclass {
		padding: .5rem 1.6rem;
		font-size: .84rem;
	}
}
</style>

</head>
<body class="login_design">
	<jsp:include page="include/GoogleBodyTag.jsp"></jsp:include>
	<div class="d-none d-md-none">
		<jsp:include page="include/header.jsp"></jsp:include>
	</div>
	<div class="container-login mb-3 mt-md-5 mt-4 pt-md-5 pt-3 mx-auto">
		<div class="header">
			<div class="header-image">
				<div class="header-image-particle header-image-particle-1"></div>
				<div class="header-image-particle header-image-particle-2"></div>
				<div class="header-image-particle header-image-particle-3"></div>
	
				<img src="<c:url value="../products/resources/images/f.png" />" alt="FreEMI" width="65" height="65">
			</div>
	
			<h1 class="header-title text-center">
				Welcome to <span class="brand-color">FreEMI!</span>
			</h1>
	
			<p class="text-sub text-center">
				Live Life Digital
			</p>
		</div>
		<div class="row mx-auto">
			<div class="col-md-12 col-lg-12 text-danger mb-0">
				<span id="displaymsg">${message}</span>
			</div>
		</div>
	
		<!--<div class="google">
			<div class="p-2 rounded col-12 mx-auto text-center font-weight-bold google-btn shadow-sm" style="pointer-events: none;">
				<a href="" class="google-button-a">
					<img src="../products/resources/images/google.png" alt="google"> &nbsp; CONTINUE WITH GOOGLE
				</a>
	
			</div>
		</div>
	
		<div class="demo">
			<div class="or or--x" aria-role="presentation">Or</div>
		</div>
	-->
		<div class="phone mt-2">
			<div class="p-2 rounded col-12 mx-auto text-center font-weight-bold phone-btn">
				<img src="../products/resources/images/phone.png" alt="phone"> &nbsp;
				CONTINUE WITH MOBILE NUMBER
			</div>
		</div>
		
		<div class="mobile">
		<form:form method="POST" id="registerForm" action="${pageContext.request.contextPath}/register.do" commandName="registerForm">
			<div class="form-floating mb-2">
					<form:input type="text" class="form-control log-mod" name="fname" id="fnameid" path="fname" aria-label="First name"
								pattern="[a-zA-Z0-9 .]*" maxlength="64" autocomplete="off" placeholder="First Name"/>
					<label for="floatingInput" class="log-lable">First Name <i class="far fa-user icon_style"></i></label>
				</div>
				<div class="form-floating">
					<form:input type="text" class="form-control log-mod" id="lnameid" name="lname" path="lname" aria-label="Last name"
								pattern="[a-zA-Z0-9 .]*" maxlength="64" autocomplete="off" placeholder="Last Name"/>
					<label for="floatingInput" class="log-lable">Last Name <i class="far fa-user icon_style"></i></label>
				</div>
				<div class="mb-2 text-center">
					<span id="msgName" class="text-center font-weight-bold py-1 text-danger" style="font-size: 13px;"></span>
				</div>
				<div class="row mx-auto">
					<div class="form-floating col-8 px-0">
						<form:input type="tel" class="form-control log-mod" id="registermobile" name="mobile" path="mobile"
							pattern="[0-9]*" maxlength="10"
						autocomplete="off"  placeholder="Mobile Number"/>
						<label for="floatingInput" class="log-lable">Mobile Number 
							<i class="fas fa-mobile-alt icon_style"></i>
						</label>
	
					</div>
					<button type="button" class="border-0 verifybtn col-4 mt-1 bg-transparent" id="send-otp-btn" data-mdb-toggle="tooltip"
						data-mdb-placement="top" title="Click to send OTP" data-mdb-toggle="modal" data-mdb-target="#otpblock"> <span id="otptext"
							class="text-danger font-weight-bold border border-danger py-1 px-2 rounded-pill ">Verify</span>
					</button>
					<button class="border-0 verifybtn col-4 mt-1 bg-transparent" id="otpVerifiedBox">
						<span id="otptextVerified" class="text-danger font-weight-bold border border-danger py-1 px-2 rounded-pill "><i class="fas fa-check"></i></span>
					</button>
				</div>
				<div class="mb-2 text-center">
					<span id="msgMobile" class="text-center font-weight-bold py-1 text-danger" style="font-size: 13px;"></span>
				</div>
				<!-- Modal Start-->
				<div class="otpTog">
					<div class="modal fade" id="otpblock" tabindex="-1" aria-labelledby="otpModalLabel" aria-hidden="true" data-backdrop="static" 
					data-keyboard="false">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title modal-p fs-5" id="exampleModalLabel">Verify Your Mobile Number</h1>
									<!-- <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
								</div>
								<div class="modal-body">
									<div class="row mx-auto text-center" id="displayboxOTP">
										<div class="col-md-12 col-lg-12 text-danger mb-0">
											<span id="displaymsgOTP"></span>
										</div>
									</div>
										<div class="form-row mb-0">
											<div class="col-md-12 col-lg-12 mx-auto" id="motpdiv">
												<div class="row">
													<div class="col-sm-12 mx-auto pb-4">
														<p class="mb-0 text-dash pt-0 text-center">
															<span id="verifytext">Please Enter OTP</span> <sup class="text-danger">*</sup>
														</p>
													</div>
													<div class="col-sm-6 mx-auto mb-3">
														<div class="otp-input-fields">
															<input type="number" class="otp__digit otp__field__1 otp-box" maxlength="1">
															<input type="number" class="otp__digit otp__field__2 otp-box" maxlength="1">
															<input type="number" class="otp__digit otp__field__3 otp-box" maxlength="1">
															<input type="number" class="otp__digit otp__field__4 otp-box" maxlength="1">
															<input type="number" class="otp__digit otp__field__5 otp-box" maxlength="1">
															<input type="number" class="otp__digit otp__field__6 otp-box" maxlength="1">
														</div>
														<input type="hidden" class="form-control rounded  _notok" maxlength="6" id="otpbox"
															name="otp" path="otp" />
													</div>
												</div>
												<div class="row mx-auto ">
													<p class="text text-center" id="timeCount">Resend OTP after <span class="text text-danger font-weight-bold"
															id="timeShow">00:00</span></p>
												</div>
											</div>

								</div>
							</div>
							<div class="modal-footer">
								
								<button type="button" class="btn btn-success" id="verify-otp-btn"><span
										id="otpverify" disabled>Verify</span></button>
							</div>
						</div>
					 </div>
					</div>
					
				</div>
				
				<div class="form-floating">
					<form:input type="text" class="form-control log-mod" id="useremail"
						path="email" name="email" placeholder="Email ID" maxlength="128" />
					<label for="floatingInput" class="log-lable">Email Address <i class="fas fa-at icon_style"></i></label>
				</div>
					<div class="mb-2 text-center">
						<span id="msgEmail" class="text-center font-weight-bold py-1 text-danger" style="font-size: 13px;"></span>
					</div>				
				<div class="form-floating">
					<form:input type="password" class="form-control log-mod"
						id="registerpassword" path="password" name="password" placeholder="Password" minlength="8" maxlength="24"
					 autocomplete="new-password"/>
					<label for="floatingInput" class="log-lable">Password <i class="fas fa-key icon_style"></i></label>
				</div>
				<div class="text-center">
					<span id="msgPass" class="text-center font-weight-bold py-1 text-danger" style="font-size: 13px;"></span>
				</div>				
				<small id="msg2" style="font-size: 10px; padding-left: 2.5rem; color: #f33c3c;"></small>
				<div>
					<form:hidden path="sessionid" />
					<form:hidden path="otpverified" />
					<button type="submit" id="registerSubmit" class="button app-login-button" >
						<span>Register</span>
					</button>
				</div>
			</form:form>
		</div>
	
		<div class="mobile">
			<div class="footer my-3">
				<!-- <pre id="result">
				</pre> -->
				<p class="text text-center">
					Already Registered?
					<a href="${pageContext.request.contextPath}/login" class="text link">Login!</a>
				</p>
			</div>
		</div>
	</div>
</body>
<script>
	// FOR OTP BOX UI
var otp_inputs = document.querySelectorAll(".otp__digit");
var mykey = "0123456789".split("");
otp_inputs.forEach((_) => {
  _.addEventListener("keyup", handle_next_input);
});

function handle_next_input(event) {
  let current = event.target;
  let index = parseInt(current.classList[1].split("__")[2]);
  current.value = event.key;

  if (event.keyCode == 8 && index > 1) {
    current.previousElementSibling.focus();
  }
  if (index < 6 && mykey.indexOf("" + event.key + "") != -1) {
    var next = current.nextElementSibling;
    next.focus();
  }
  var _finalKey = "";
  for (let { value } of otp_inputs) {
    _finalKey += value;
  }
  if (_finalKey.length == 6) {
    document.querySelector("#otpbox").classList.replace("_notok", "_ok");
    document.querySelector("#otpbox").value = _finalKey;
  } else {
    document.querySelector("#otpbox").classList.replace("_ok", "_notok");
    document.querySelector("#otpbox").value = _finalKey;
  }
}
</script>
<script type="text/javascript">
	var basepathdata = '<c:out value='${pageContext.request.contextPath}'></c:out>';
</script>
<script src="<c:url value="${contextcdn}/resources/js/register.js" />"></script>

</html>