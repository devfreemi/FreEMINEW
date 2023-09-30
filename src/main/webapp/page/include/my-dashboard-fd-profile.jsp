<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="fd-history-outer m-2" style="overflow: auto;">
	
	<table class="table align-middle mb-0 bg-white table-responsive-sm table-striped" id="fdprofile">
		<caption style="line-height: normal;">
			Fixed Deposit Folio history <br> <small class="text-muted">

			</small>
		</caption>
		<thead class="bg-light">
			<tr>
				<th scope="col">NBFC</th>
				<th scope="col">Folio</th>
				<th scope="col">FDR No</th>
				<th scope="col">Application No</th>
				<th scope="col">Principal (Rs.)</th>
				<th scope="col">Maturity</th>
				<th scope="col">Status</th>
				<th scope="col">Action</th>
			</tr>
		</thead>

		<tbody id="fddatabody">
		</tbody>
	</table>

<div class="col-md-12 col-12 mx-auto">
  <div class="row mx-auto">
    <div class="col-12.mx-auto">
      <p class="text-center fw-bold text-danger" id="fdMsg"></p>
    </div>
    <div class="col-12 col-md-6 text-center mt-4">
      <span id="fdfetch">
        <button class="btn btn-primary" onclick="getfixeddepositdata('<%=session.getAttribute("userid").toString()%>','${pan }');">Fetch your Portfolio</button>
      </span>
    </div>
    <!-- <div class="col-12 col-md-6 text-center mt-4">
    </div> -->
    <div class="col-12 col-md-6 mt-md-4 mt-0 mx-auto text-center">
      <a href="/products/fixed-deposit/view-purchase-history" class="btn btn-dark py-2"> <span>
          Transaction Histoty &nbsp;&nbsp; <i class="fas fa-history"></i></span>
      </a>
    </div>
  </div>
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
        <button type="button" class="btn btn-load-more" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


