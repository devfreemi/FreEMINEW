function mandatestatus(clientid, mandateid){
	var token =  $('input[name="_csrf"]').attr('value');
	$("#m").val(mandateid);
	$("#c").val(clientid);
	document.getElementById("authact").classList.remove("d-block-inline");
	document.getElementById("authact").classList.add("d-none");
	$("errormsg").text("");
	var data = {
			"clientid" : clientid,
			"mandateid" : mandateid
			}
	$.ajaxSetup({
		headers:
		{ 'X-CSRF-TOKEN': token,
			'Accept' : 'application/json' }
	});
	
	request = $.ajax({
		url: "/products/mutual-funds/mandate-status",
		method: "POST",
		data: JSON.stringify(data),
		async: true,
		datatype: "json",
		contentType: 'application/json',
		beforeSend: function() {
		}
	});

	request.done(function(data, textStatus, xhr) {
		if(data.statusCode == '100'){
			$("#mandatemodal").modal('show');
			$("#mandateid").text(mandateid);
			$("#mandatestatus").text(data.data1);
			if(data.data2=='AUTHENTICATE'){
				document.getElementById("authact").classList.remove("d-none");
				document.getElementById("authact").classList.add("d-block-inline");
			}
			}
		else{
			alert(data.remarks);
		}
	});

	request.fail(function(jqXHR, textStatus) {
		alert("Request failed to process..");
	});

	request.always(function(msg){

	});
}


//function getauthurl(request, url){
$("#mandateauthact").on('click', function (){
	var token =  $('input[name="_csrf"]').attr('value');
	var data = {
			"clientid" : $("#c").val(),
			"mandateid" : $("#m").val()
			}
	$.ajaxSetup({
		headers:
		{ 'X-CSRF-TOKEN': token,
			'Accept' : 'application/json' }
	});
	
	request = $.ajax({
		url: "/products/mutual-funds/emandateurl",
		method: "POST",
		data: JSON.stringify(data),
		async: true,
		datatype: "json",
		contentType: 'application/json',
		beforeSend: function() {
		}
	});

	request.done(function(data, textStatus, xhr) {
		if(data.statusCode == '100'){
			window.open(data.remarks,"_blank");
			}
		else{
		$("#errmsg").text(data.remarks);
			}
	});

	request.fail(function(jqXHR, textStatus) {
		$("#errmsg").text("Request failed to process..");
	});

	request.always(function(msg){
		
	});
});