<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin-top: 5px;">
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-sm"
		 data-toggle="modal"
		data-target="#exampleModal2">Verify and upload</button>

	<!-- Signature Modal -->
	<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel2" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header"
					style="background-color: #f57c45; color: white;">
					<h5 class="modal-title" id="exampleModalLabel2">
						<img
							src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
							class="img-fluid" style="height: 48px;" alt="Signature panel">
						Signature Panel
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="text-align: center;">

					<div>
						<form:form commandName="mfInvestForm"
							action="${pageContext.request.contextPath}/mutual-funds/uploadsignedaof.do">
							<img alt="" id="aofformsignature" src="" class="img-fluid" style="height: 2rem;">
							<form:hidden path="customerSignature" id="signature1" />
							
							<form:button type="submit" class="btn btn-sm btn-success">Upload AOF</form:button>
						</form:form>
					</div>
				</div>
				<!-- <div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Close</button>
												<button type="button" class="btn btn-primary">Save
													changes</button>
											</div> -->
			</div>
		</div>
	</div>
</div>
