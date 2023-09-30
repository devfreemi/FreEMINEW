<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="px-0">

	<!-- <table class="table align-middle mb-0 bg-white table-responsive-sm" >
		<caption class="justify-centent-center" style="line-height: normal;">
		<small class="text-muted">
			Important Note: Kindly note that the information available in our website is based on data files available from Registrars of Mutual Funds. There are delay/error/and mismatch in certain cases. It is therefore important to note that before any transaction verify the correct data as per details available from concerned mutual Funds. In case of any discrepancy, the information as shown in Account statement of mutual funds is considered as final. We are not held responsible for any such discrepancy for the reasons beyond our control.
		</small>
		</caption>
		<thead class="bg-light">
			<tr>
				<th>Mutual Fund</th>
				<th>Invested</th>
				<th>Unit</th>
				<th>Nav</th>
				<th>Value</th>
				<th>View</th>
			</tr>
		</thead>

		<tbody id="mfdatatbody2">
			
		</tbody>
	</table> -->
		<div class="row pt-md-4 pt-3" id="mfdatatbody2">
			
		</div>
		<!-- Modal -->
		<div class="modal fade" id="selectfundmodal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="modal-title">
						<img class="my-0 rounded fund-icon-all" id="amcicondisplay"
						src=""
						alt="FreEMI AMC Icon">&nbsp;
						<span class="fw-bold text-center" id="modalHeader"></span>
						
					</span>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true"><i class="far fa-times-circle"></i></span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row justify-content-center">
						<div class="col-md-6 col 6 border-right">
							<p class="mb-0 fw-bold return-text">Invested Amount<br><i class="fas fa-rupee-sign"></i> <span id="modalInvAmt"></span></p>
						</div>
						<div class="col-md-6 col-6">
							<p class="mb-0 text-right fw-bold return-text">Current<br><i class="fas fa-rupee-sign"></i> <span id="modalMarketamt"></span></p>
						</div>
						<div class="col-md-12 col-12 px-4">
							<div class=" px-3 py-1 my-3 border rounded">
								<p class="mb-0 text-center fw-bold return-text">Return &nbsp;<i class="text-return-size fas fa-rupee-sign"></i> 
									<span class="text-return-size" id="modalReturnamt"></span> 
									(<span class="returnClass" id="modalindicate"></span> <span class="returnClass" id="modalReturnPer"></span><span class="returnClass">%</span> ) 
									
								</p>
							</div>
						</div>
						<div class="col-12 col-md-12 px-2">
							<p class="text-center text-info fw-bold">Follio Details</p>
							<div class="row mx-auto justify-content-center">
								<div class="col-md-4 col-4">
									<p class="modal-folio-text fw-bold text-center">Folio Number</p>
									<p class="modal-folio-number fw-bold text-center" id="modalfolioNumber"></p>
								</div>
								<div class="col-md-4 col-4">
									<p class="modal-folio-text fw-bold text-center">Unit</p>
									<p class="modal-folio-number fw-bold text-center" id="modalUnit"></p>
								</div>
								<div class="col-md-4 col-4">
									<p class="modal-folio-text fw-bold text-center">Nav</p>
									<p class="modal-folio-number fw-bold text-center"><span id="modalNav"></span><br><small class="text-muted fw-normal">(<small id="modalNavDate"></small>)</small></p>
								</div>
							</div>
						</div>
						<div class="col-12 col-md-12 px-md-4 px-3">
							<div class="row justify-content-center">
								<div class="col-4 col-lg-4 col-md-4 text-center" id="redeemButton">
									
								</div>
								<div class="col-6 col-lg-6 col-md-6 text-center" id="investButton">
									
								</div>
								<div class="col-2 col-md-2 text-center">
									<button type="button" class="btn btn-outline-dark btn-floating" title="Cancel SIP">
										<i class="fas fa-ellipsis-v"></i>
									  </button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="overflow-x: auto;"></div>
</div>
