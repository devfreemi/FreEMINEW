<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Modal -->
<div class="modal fade" id="mahindrafdModal" tabindex="-1" role="dialog"
	aria-labelledby="mahindrafdModal" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div class="text-center" style="width: 100%;">
					<img
						data-src="<c:url value="${contextcdn}/resources/images/invest/mahindra-finance.jpg"/>"
						class="img-fluid lazy" style="height: 2rem;">
				</div>

				<!--  <h5 class="modal-title" id="mahindrafdModal">DETAILS</h5> -->
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="text-default">Provide the required details to
					process your deposit request</div>
				<div style="padding-top: 2rem;">
					<form:form method="POST"
						action="${pageContext.request.contextPath}/fixed-deposit/capture-fd-request"
						modelAttribute="fdform">

						<c:choose>
							<c:when test="${STATUS == 'N' }">
								<div class="alert alert-danger" role="alert"
									style="padding: 7px; font-size: 13px;">Sorry, we are
									unable to process your request currently</div>
							</c:when>
							<c:when test="${FORM_VALID == 'N' }">
								<div class="alert alert-danger" role="alert"
									style="padding: 7px; font-size: 13px;">${error_msg }</div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger" role="alert"
									style="padding: 7px; font-size: 13px; display: none;"
									id="validmsg">
									<span id="jsmsg"></span>
								</div>
							</c:otherwise>
						</c:choose>

						<div class="row" style="background: aliceblue; margin: auto;">
							<div class="col-6">
								<label>Category</label>
							</div>
							<div class="col-6 text-right">
								<span id="category"
									style="font-size: 16px; font-weight: 500; color: #cc42f9;"></span>
							</div>



						</div>

						<%-- <div class="form-group row">
							<!-- Material input -->
							<label for="tenure" class="col-md-4 col-form-label">Category</label>
							<div class="col-md-8" id="tenure">
								<div class="btn-group btn-group-sm btn-group-toggle"
									data-toggle="buttons" id="categorymain">
									<label class="btn btn-info" > <form:radiobutton
											path="category" value="PU" autocomplete="off" id="PU" /> PUBLIC
									</label> <label class="btn btn-info" > <form:radiobutton
											path="category" value="SR" autocomplete="off" id="SR" /> SR CITIZEN
									</label> 

								</div>
							</div>
						</div> --%>

						<form:hidden path="category" />

						<div class="md-form form-sm">
							<i class="fas fa-rupee-sign prefix"></i>
							<form:input type="text" path="saveAmount" class="form-control"
								min="10000" minlength="4" maxlength="7" pattern="[0-9]*"
								id="saveamount" required="required" />
							<label for="saveamount">Fixed Deposit Amount</label>
						</div>
						
						<div class="btn-group btn-group-sm btn-group-toggle"
							data-toggle="buttons" id="radioamount" style="padding-left: 2.5rem;">
							<label class="btn btn-info"> <input type="radio"
								value="10000" name="options" id="option1">
								10K
							</label> <label class="btn btn-info"> <input type="radio"
								name="options" value="25000" id="option2">
								25K
							</label> <label class="btn btn-info"> <input type="radio"
								name="options" value="50000" id="option3">
								50K
							</label> <label class="btn btn-info"> <input type="radio"
								name="options" value="100000" id="option4">
								1lac
							</label>
						</div>

						<div class="md-form form-sm">
							<i class="fas fa-mobile-alt prefix"></i>
							<form:input type="text" path="mobile" class="form-control"
								minlenth="10" maxlength="10" pattern="[6-9]{1}[0-9]{9}"
								id="mobileno" required="required" />
							<label for="mobileno">Your mobile number (+91)</label>
						</div>

						<div class="md-form form-sm">
							<i class="fas fa-user-alt prefix"></i>
							<form:input type="text" path="pan" class="form-control"
								style="text-transform:uppercase" minlenth="8" maxlength="10"
								pattern="[a-zA-Z0-9]*" id="panid" required="required" />
							<label for="panid">Your PAN number</label> <small id="panid"
								class="form-text text-muted"> If you have already
								registered your PAN with us, only that PAN will be considered
								for transaction. </small>
						</div>

						<div class="form-group row">
							<!-- Material input -->
							<label for="tenure" class="col-md-2 col-form-label desc">Scheme
							</label>
							<div class="col-md-10 text-md-right" id="scheme">
								<div class="btn-group btn-group-sm btn-group-toggle"
									data-toggle="buttons" id="schemeid" >
									<%-- <label class="btn btn-info"> <form:radiobutton
											path="scheme" value="MICRO DEPOSIT" id="option1"
											autocomplete="off" /> MICRO DEPOSIT
									</label> --%> 
									<label class="btn btn-info active"> <form:radiobutton
											path="scheme" value="C" id="option2"
											autocomplete="off" checked="checked" /> CUMULATIVE
									</label> <label class="btn btn-info"> <form:radiobutton
											path="scheme" value="NC" id="option3"
											autocomplete="off" /> NON CUMULATIVE
									</label>

								</div>
							</div>
						</div>

						<div class="mx-auto text-center mt-2">
							<button type="submit"
								class="btn btn-primary mt-md-4 px-3 py-2 custom_btn label-size">
								<strong>Process Now <i
									class="fas fa-angle-double-right"></i></strong>
							</button>
						</div>
					</form:form>
				</div>
			</div>
			<!--  
      <div class="modal-footer">
       <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button> 
      </div>
      -->
		</div>
	</div>
</div>








