<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form1" uri="http://www.springframework.org/tags/form"%>

<div class="loan-list mb-2">
	<i class="fas fa-list" style="color: aliceblue;"></i>
</div>

<div class="fd-history-outer m-2" style="overflow: auto;">

	<table class="table table-sm" id="loanprofile">
		<caption style="line-height: normal;">
			Loan Application history <br> <small class="text-muted">
			</small>
		</caption>
		<thead class="#3949ab indigo darken-1 white-text">
			<tr>
				<th scope="col">Bank</th>
				<th scope="col">Request Date</th>
				<th scope="col">Reference No</th>
				<th scope="col">Acknowledgement No</th>
				<th scope="col">Loan Requested</th>
				<th scope="col">Application ID</th>
				<th scope="col">Status</th>
				<th scope="col">Comments</th>
				<th scope="col">Action</th>
			</tr>
		</thead>

		<tbody id="loanlistbody">
		</tbody>
	</table>

	<div class="text-center">
		<span id="loanfetch"><button class="btn btn-sm btn-secondary"
				onclick="gethdfcloanrequestlist(reqid,null);">Fetch your Loan
				Requests</button></span>
		<div>
			<small class="text-danger" id="loanreqmsg"></small>
		</div>
	</div>
	<div style="overflow-x: auto;"></div>
</div>

<!-- Modal -->
<div class="modal fade" id="loandetailsmodal" tabindex="-1"
	role="dialog" aria-labelledby="loanModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="loanModalLabel">Loan request
					Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table table-striped table-sm">
					<tbody>
						<tr>
							<th scope="row" class="text-dark">NBSC</th>
							<td id="nbscid" class="text-muted">NA</td>
						</tr>
						<tr>
							<th scope="row" class="text-dark">Loan Type</th>
							<td id="loantype" class="text-muted">NA</td>
						</tr>
						<tr>
							<th scope="row" class="text-dark">Reference No</th>
							<td id="refno" class="text-muted">NA</td>
						</tr>
						<tr>
							<th scope="row" class="text-dark">Acknowledgement No</th>
							<td id="ackno" class="text-muted">NA</td>
						</tr>
						<tr>
							<th scope="row" class="text-dark">Loan Amount</th>
							<td id="loanamnt" class="text-muted">NA</td>
						</tr>
						<tr>
						<tr>
							<th scope="row" class="text-dark">Application ID</th>
							<td id="applicationid" class="text-muted">NA</td>
						</tr>
						<tr>
							<th scope="row" class="text-dark">Status</th>
							<td id="loanstatus" class="text-muted">NA</td>
						</tr>
						<tr>
							<th scope="row" class="text-dark">Comments</th>
							<td id="comments" class="text-muted">NA</td>
						</tr>
						<tr>
							<th scope="row" class="text-dark">Action</th>
							<td id="loanaction" class="text-muted">NA</td>
						</tr>

					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-secondary"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<!-- Modal -->
<div class="modal fade" id="completeloanreqmodal" tabindex="-1"
	role="dialog" aria-labelledby="completeloanModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="completeloanModalLabel">Complete
					Loan Request</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form1:form id="regForm"
					action="http://localhost:8081/hdfc-loan-upload-document"
					method="POST" commandName="loanrequestcomplform">
					
					<form1:hidden path="mobileno" id="mobno"/>
					<form1:hidden path="loanbank" id="reqbank"/>
					<form1:hidden path="ackno" id="ackno"/>
					<form1:hidden path="applno" id="applno"/>
					<form1:hidden path="action" id="action"/>
					
					<div class="form-row">
						<div class="col-md-6">
							<div class="md-form form-sm">
								<form1:input type="text" path="loanrefno"
									class="form-control form-control-sm" minlenth="1"
									maxlength="64" id="loanrefno" />
								<label for="loanrefno">Loan Reference</label>
							</div>
						</div>

						<div class="col-md-6">
							<div class="md-form form-sm">
								<form1:input type="text" path="other"
									class="form-control form-control-sm" minlenth="1"
									maxlength="64" id="loanapplystatus" />
								<label for="loanapplystatus">Loan Request Status</label>
							</div>

						</div>
					</div>
					
					<button class="btn btn-sm btn-success" type="submit" id="completereq">Complete Request</button>

				</form1:form>
			</div>
		</div>
	</div>
</div>


