<%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt1" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="registry-profiles">
	<i class="fas fa-list" style="color: aliceblue;"></i>
</div>

<div class="registry-outer">

	<table class="table table-sm" id="mfprofiledata2">
		<caption style="line-height: normal;">Mutual Funds Purchase History
		<br>
		<small class="text-muted">
		Important Note: Kindly note that the information available in our website is based on data files available from Registrars of Mutual Funds. There are delay/error/and mismatch in certain cases. It is therefore important to note that before any transaction verify the correct data as per details available from concerned mutual Funds. In case of any discrepancy, the information as shown in Account statement of mutual funds is considered as final. We are not held responsible for any such discrepancy for the reasons beyond our control.
		</small>
		</caption>
		<thead class="#3949ab indigo darken-1 white-text">
			<tr>
				<th scope="col" valign="middle" style="min-width: 12rem;">Mutual Fund</th>
				<th scope="col" valign="middle">Invested Amount (Rs.)</th>
				<th scope="col" valign="middle">Market Value (Rs.)</th>
				<th scope="col" valign="middle">VIEW</th>
			</tr>
		</thead>

		<tbody id="mfdatatbody2">
		</tbody>
	</table>
	
	<div style="text-align: center;">
	<span id="msgmfapi"><button class="btn btn-sm btn-secondary" onclick="getMFPortfolioData(<%=session.getAttribute("userid").toString()%>,'${PROFILE_STATUS}');">Fetch your Portfolio</button></span>
	</div>
	<div style="overflow-x: auto;"></div>
</div>
