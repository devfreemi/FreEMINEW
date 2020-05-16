<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="registry-profiles mb-2">
	<i class="fas fa-list" style="color: aliceblue;"></i>
</div>

<div class="fd-history-outer m-2" style="overflow: auto;">
	
	<table class="table table-sm" id="fdprofile">
		<caption style="line-height: normal;">
			Fixed Deposit Folio history <br> <small class="text-muted">

			</small>
		</caption>
		<thead class="#3949ab indigo darken-1 white-text">
			<tr>
				<th scope="col">NBFC</th>
				<th scope="col">Folio</th>
				<th scope="col">FDR No</th>
				<th scope="col">Application No</th>
				<th scope="col">Principal (Rs.)</th>
				<th scope="col">Maturity</th>
				<th scope="col">Action</th>
			</tr>
		</thead>

		<tbody id="fddatabody">
		</tbody>
	</table>

	<div style="text-align: center;">
	<span id="fdfetch"><button class="btn btn-sm btn-secondary" onclick="getfixeddepositdata('<%=session.getAttribute("userid").toString()%>','${pan }');">Fetch your Portfolio</button></span>
	</div>
	<div style="overflow-x: auto;"></div>
</div>

<!-- Modal -->
<div class="modal fade" id="fddetailsmodal" tabindex="-1" role="dialog" aria-labelledby="fdModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="fdModalLabel">Fixed Deposit Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
      <th scope="row" class="text-dark">FDR No</th>
      <td id="fdrnoid" class="text-muted">NA</td>
    </tr>
    <tr>
      <th scope="row" class="text-dark">Investor Name</th>
      <td id="invnameid" class="text-muted">NA</td>
    </tr>
    <tr>
    
      <th scope="row" class="text-dark">Purchase Date</th>
      <td id="purchasedateid" class="text-muted">NA</td>
    </tr>
    <tr>
      <th scope="row" class="text-dark">Principal Amount</th>
      <td id="principalamntid" class="text-muted">NA</td>
    </tr>
    <tr>
    <tr>
      <th scope="row" class="text-dark">Saving Type</th>
      <td id="schemetypeid" class="text-muted">NA</td>
    </tr>
    <tr>
      <th scope="row" class="text-dark">Tenure</th>
      <td id="fdtenureid" class="text-muted">NA</td>
    </tr>
    <tr>
      <th scope="row" class="text-dark">Interest Rate</th>
      <td id="purchaserateid" class="text-muted">NA</td>
    </tr>
    <tr>
      <th scope="row" class="text-dark">Maturity Amount</th>
      <td id="maturityamountid" class="text-muted">NA</td>
    </tr>
    <tr>
      <th scope="row" class="text-dark">Maturity Date</th>
      <td id="maturitydate" class="text-muted">NA</td>
    </tr>
  </tbody>
</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


