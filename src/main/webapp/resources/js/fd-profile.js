
function reuploadkycdoc(applicationno, mobile) {
//	console.log("Order staus for id- " + clientId + " : " + orderNo);
	$.post("/products/api/fd/retry-kyc-doc-upload", {
		"appl_no" : applicationno,
		"mobile" : mobile
	}, function(data, status) {

//		console.log(data);
//		$('#exampleModal1').modal('hide');
		if (data == 'NO_SESSION') {
			alert("Invalid request");
		} else if (data == 'REQUEST_DENIED') {
			alert("Session not found!")
		} else {
			alert("Image reupload complete for appl_no: "+applicationno+" : "+ data);
		}

	}).fail(function(response) {
		/* $('#exampleModal1').modal('hide');
		$("#signuploadstatus")
				.text(
						"Failed to submit your signature. Please try again."); */
		/* alert(response); */
		alert("Failed to get status for application no- "+ applicationno);
	});

}


function getmahindrafdpaymentstatus(applicationno, mobile) {
//	console.log("Order staus for id- " + clientId + " : " + orderNo);
	$.post("/products/api/fixed-deposit/mahindraapplpaymentstatus", {
		"appl_no" : applicationno,
		"mobile" : mobile,
		"NBFC" : "MAHINDRA"
	}, function(data, status) {

		console.log(data);
//		$('#exampleModal1').modal('hide');
		if (data == 'NO_SESSION') {

			alert("Invalid request");

		} else if (data == 'REQUEST_DENIED') {
			alert("Session not found!")
		} else {
			alert("Status of order no: "+applicationno+"\n"+ data);
		}

	}).fail(function(response) {
		/* $('#exampleModal1').modal('hide');
		$("#signuploadstatus")
				.text(
						"Failed to submit your signature. Please try again."); */
		/* alert(response); */
		alert("Failed to get status for application no- "+ applicationno);
	});

}

function completefdpayment(applicationno, mobile, cptrsansid,mfsysno){
//	console.log("Order staus for id- " + clientId + " : " + orderNo);
	$.post("/products/api/fd/mahindrafdinitiatepayment", {
		"appl_no" : applicationno,
		"mobile" : mobile,
		"cp_trans_id" : cptrsansid,
		"mf_sys_ref_no" : mfsysno
	}, function(data, status,request) {

//		console.log(data);
//		$('#exampleModal1').modal('hide');
		if (data == 'NO_SESSION') {
			alert("Session not found!");
		} else if (data == 'REQUEST_DENIED') {
			alert("Invalid request.")
		}else if (data == 'EXCEPTION' || data == 'INTERNAL_ERROR') {
			alert("Failed to process. Kindly contact admin")
		} else {
			if(request.getResponseHeader('STATUS') == 'S'){
				window.location = data;
			}else{
				alert("Status of order no: "+applicationno+"\n"+ data);
			}
		}

	}).fail(function(response) {
		/* $('#exampleModal1').modal('hide');
		$("#signuploadstatus")
				.text(
						"Failed to submit your signature. Please try again."); */
		/* alert(response); */
		alert("Failed to get status for application no- "+ applicationno);
	});
}
