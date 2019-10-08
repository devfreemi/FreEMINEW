function AdditionalPurchase(folio,code,type,rtaAgent,productCode){
	if(folio == 'NEW'){
		alert("You cannot make additional purhcase until portfolio is assigned!");
	}else{
		window.location.assign(window.location.href+"/additional-purchase?p="+btoa(folio+"|"+code+"|"+type+"|"+rtaAgent+"|"+productCode));
	}
}

function MFRedeem(folio,code,type,rtaAgent,productCode){
	if(folio == 'NEW'){
		alert("No portfolio number to redeem your investment! Please wait for portfolio number.");
	}else{
	window.location.assign(window.location.href+"/funds-redeem?r="+btoa(folio+"|"+code+"|"+type+"|"+rtaAgent+"|"+productCode));
	}
//	window.location.assign(window.location.href+"/funds-redeem?r="+btoa(folio+"|"+code+"|"+type));
}


function cancelOrder(schemeCode,orderno,type,category,transactionId){
	console.log("Cancel order request...")
	if(type== 'SIP_COMMNTED'){
		alert("SIP order cancel process will be available soon. ");
	}else if(type == 'LUMPSUM' || type == 'SIP'){
		window.location.assign("/products/my-dashboard/cancel-order?ref="+btoa(schemeCode+"|"+orderno+"|"+type+"|"+category+"|"+transactionId));
		
	}else{
		alert("Invalid type of investment");
	}
	
}


function getbseOrderPaymentStatus(clientId, orderNo) {
	console.log("Order staus for id- " + clientId + " : " + orderNo);
	$.get("/products/mutual-funds/orderpaymentStatus", {
		client : clientId,
		order : orderNo
	}, function(data, status) {

		console.log(data);
		$('#exampleModal1').modal('hide');
		if (data == 'NO_SESSIION') {

			alert("Invalid request");

		} else if (data == 'REQUEST_DENIED') {
			alert("Session not found!")
		} else {
			alert("Status of order no: "+orderNo+"\n"+ data);
		}

	}).fail(function(response) {
		/* $('#exampleModal1').modal('hide');
		$("#signuploadstatus")
				.text(
						"Failed to submit your signature. Please try again."); */
		/* alert(response); */
		alert("Failed to get status for order- "+ orderNo);
	});

}


/*$(document).ready(function(){
	$(".box-style2").hover(
			function(){
				console.log("animated")
				$(this).addClass("animated pulse");
			});
});

$(document).ready(function(){
	$(".box-style2").blur(
			function(){
				console.log("remove animated");
				$(this).removeClass("animated pulse");
			});
});*/


