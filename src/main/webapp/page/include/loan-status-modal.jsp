<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="loanstatusmodal" tabindex="-1" role="dialog"
	aria-labelledby="loanstatusmodal" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="loanstatusmodal">Loan Status</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<small class="text-alert" id="loanstatusmsg"></small>
				<table class="table">
					<tbody>
						<tr>
							<th scope="row">Customer</th>
							<td id="loancustomername"></td>
						</tr>
						<tr>
							<th scope="row">Application ID</th>
							<td id="loanapplno"></td>
						</tr>
						<tr>
							<th scope="row">Status</th>
							<td id="loanapplstatus"></td>
						</tr>
					</tbody>
				</table>

			</div>
			<!-- <div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div> -->
		</div>
	</div>
</div>