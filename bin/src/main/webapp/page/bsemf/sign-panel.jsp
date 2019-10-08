<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin-top: 5px;">
	<!-- Button trigger modal -->

	<img
		src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
		class="img-fluid" style="height: 48px;" alt="Signature panel">
	<button type="button" class="btn btn-outline-primary btn-sm"
		style="font-size: 10px;" data-toggle="modal"
		data-target="#exampleModal1">Click to sign</button>

	<!-- Signature Modal -->
	<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel1" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header"
					style="background-color: #f57c45; color: white;">
					<h5 class="modal-title" id="exampleModalLabel">
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
					<div class="row">
						<div class="col-md-12">
							<canvas id="sig-canvas">
		 			Browser not supported!
		 		</canvas>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-sm btn-info" id="sig-submitBtn">Submit
								Signature</button>
							<!-- <button class="btn" id="btndownload">Download</button> -->
							<button class="btn btn-sm btn-secondary" id="sig-clearBtn">Clear
								Signature</button>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-12">
							<textarea id="sig-dataUrl" class="form-control" rows="5"
								style="max-height: 100px; overflow: scroll;" readonly="readonly">Data URL for your signature will go here!</textarea>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-md-12">
							<!-- <img id="sig-image" src="" alt="Signature-image" /> -->
							<img
								src="<c:url value="${contextcdn}/resources/images/invest/sign1.png"/>"
								class="img-fluid" id="sig-image" alt="Signature Image">
						</div>
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
