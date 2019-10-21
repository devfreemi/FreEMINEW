<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="registry-profiles">
	<i class="fas fa-list" style="color: aliceblue;"></i>
</div>

<div class="registry-outer">

	<table class="table table-sm" id="mfprofiledata2">
		<caption>Mutual Funds Purchase History</caption>
		<thead class="#3949ab indigo darken-1 white-text">
			<tr>
				<th scope="col" valign="middle">Mutual Fund</th>
				<th scope="col" valign="middle">Invested Amount (Rs.)</th>
				<th scope="col" valign="middle">Market Value (Rs.)</th>
				<th scope="col" valign="middle">VIEW</th>
			</tr>
		</thead>

		<tbody id="mfdatatbody2">
		</tbody>
	</table>
	
	<div style="text-align: center;">
	<span id="msgmfapi"><img alt="Fetching your portfolio" class="img-fluid" style="margin: auto;" src="/products/resources/images/invest/progress2.gif"></span>
	</div>
	<div style="overflow-x: auto;"></div>
</div>
