console.log = function(){};
function formOnLoad(){
	document.getElementById("changebutton").style.display="block";
	document.getElementById("passwordchangeform").style.display="none";
}


function showpassCHangeForm(){
	document.getElementById("changebutton").style.display="none";
	document.getElementById("passwordchangeform").style.display="block";
}

function hidepassChangeForm(){
	document.getElementById("changebutton").style.display="block";
	document.getElementById("passwordchangeform").style.display="none";
}


$(document).ready(function() {
	$("#ifsc").blur(function() {
		var regex = new RegExp('^[a-zA-Z]{4}[0][0-9a-zA-Z]{6}$');
//		console.log("IFSC search");
		var ifsc = $("#ifsc").val();

		if (regex.test(ifsc)) {
			$("#ifsc").css('border-left','2px solid #43c253');
			$.get("https://ifsc.razorpay.com/"+ ifsc,function(data,status) {
//				console.log(data.BANK);
				//console.log(data.BRANCH);
				//console.log(data.ADDRESS);

				$("#bankCity").val(data.CITY);
				$("#branch").val(data.BRANCH);
				$("#bankAddress").val(data.ADDRESS);
				$("#bankName").val(data.BANK);
				$("#bankState").val(data.STATE);
				$("#invalidifsc").text("");
			})
			.fail(function(data,status) {
				//console.log("Error retrieving data");
				//console.log(data);
//				console.log(status);
				$("#invalidifsc").text("Invalid IFSC code");
				$("#bankCity").val("");					
				$("#branch").val("");
				$("#bankAddress").val("");
				$("#bankName").val("");
				$("#bankState").val("");
			});
		} else {
			$("#ifsc").css('border-left','2px solid #ff6a6a');
		}

	});
});



function changepass(e) {
	e.preventDefault();

	var oldpass = document.forms["profilePasswordChangeForm"]["oldPassword"].value;
	var newpass = document.forms["profilePasswordChangeForm"]["newPassword"].value;
	var newpassconf = document.forms["profilePasswordChangeForm"]["confirmNewPassword"].value;

	$("#passmsg").text("");

	if (oldpass.length == 0) {
		$("#passmsg").text("Old password is mandatory!");
		return false;
	}
	if (newpass.length == 0 || newpassconf.length == 0) {
		$("#passmsg").text("New password fields cannot be blank.");
		return false;
	}
	if (newpass !== newpassconf) {
		$("#passmsg").text("Confirm password do not match!")
		return false;
	} else {
		$("#passmsg").text("");
		/* Process password */

		var token = $('input[name="_csrf"]').attr('value');
		$.ajaxSetup({
			headers : {
				'X-CSRF-TOKEN' : token
			}
		});

		var request;
		var str = $("#profilePasswordChangeForm").serialize();

		request = $.ajax({
			url : "/products/changePassword.do",
			method : "POST",
			data : str,
			async : true,
			datatype : "json",
			beforeSend : function() {
				disableChangePassButton();
			}
		});

		request.done(function(msg) {
			if (msg == "SUCCESS") {
				$("#passmsg").text("Password changed successfully.");
				$("#passmsg").css("color", "green");
			} else {
				//			Error
				$("#passmsg").text(msg);
			}

		});

		request.fail(function(jqXHR, textStatus) {
			$("#passmsg").text("Request failed: " + textStatus);
		});

		request.always(function(msg) {
			$("#resetbutton").prop("disabled", false);
			$("#passchangespin").hide();
			$("#passchangebasic").show();

		});

	}

	return false;
}

function disableChangePassButton() {
	$("#passchangebasic").hide();
	$("#passchangespin").show();
	$("#resetbutton").prop("disabled", true);

}


$('.reuploadimage').change(function(e) {
	//console.log("Trgiiered - "+ event.target.id)
	var fileName = document.getElementById(event.target.id).files[0].name;
	var nextSibling = e.target.nextElementSibling;
	nextSibling.innerText = fileName;
	if(this.files[0].size/1024 > 256){
		$("#"+event.target.id+"-msg").text("File size exceeded limit!");
	}else{
		document.getElementById(event.target.id+'-pic').src = window.URL.createObjectURL(this.files[0]);
		$("#"+event.target.id+"-pic").css({"height": "5rem"});
		$("#"+event.target.id+"-msg").text("");
	}
});

$( "#kycupdateformid" ).on( "submit", function( e ) {
	e.preventDefault();
	console.log("Submit form");
	console.log("Serialixe="+ $( this ).serialize() );


	var token = $('input[name="_csrf"]').attr('value');
	$.ajaxSetup({
		headers : {
			'X-CSRF-TOKEN' : token
		}
	});

	var request;

//	Get form
	var form = $('#kycupdateformid')[0];
//	var form = $('mfdkycdcupdateform')[0];


//	console.log(form.serialize())
//	Create an FormData object
	var data1 = new FormData(form);
	console.log(data1);
	request = $.ajax({
		url : "/products/api/fixed-deposit/updatekycdoc",
		method : "POST",
		enctype: 'multipart/form-data',
		processData: false,  // Important!
		contentType: false,
		cache: false,
		async : true,
		data: data1,
		timeout: 600000,
		beforeSend : function() {
			$("#docuploadmsg").text("");
			$("#kycupdatebutton").html("Updating <i class=\"fas fa-spinner fa-spin\"></i>");
		}
	});

	request.done(function(msg) {
//		console.log(msg)
		
		if(msg.statusCode==100 && msg.statusMsg=='SUCCESS'){
			$("#docuploadmsg").text("Update status- "+ "Successfully updated");
			$("#docuploadmsg").css("color", "green");	
		}else if(msg.statusCode==101){
			$("#docuploadmsg").text("Update status- "+ msg.statusMsg);
			$("#docuploadmsg").css("color", "red");
		}else{
			$("#docuploadmsg").text("Update status- Unknown request");
			$("#docuploadmsg").css("color", "red");
		}

	});

	request.fail(function(jqXHR, textStatus) {
		$("#docuploadmsg").text("Request failed: " + textStatus);
		$("#docuploadmsg").css("color", "red");
		console.log(textStatus);
	});

	request.always(function(msg) {
		$("#kycupdatebutton").html("Save Changes");

	});

});



$( "#viewmfdkycdoc" ).on( "click", function( e ) {
	e.preventDefault();
	console.log("view data");

	var request;

	request = $.ajax({
		url : "/products/api/fixed-deposit/get-fd-kyc-documents",
		method : "POST",
		async : true,
		data: {"mobile": $("#customermobileval").val()},
		timeout: 600000,
		beforeSend : function() {
			//disableChangePassButton();
			$("#viewmfdkycdoc").html("Fetching <i class=\"fas fa-spinner fa-spin\"></i>");
			var node = document.getElementById('kycdocdata');
			node.innerHTML = "";
		}
	});

	request.done(function(msg) {
		console.log(msg)
		if(msg.statusCode==100 && msg.statusMsg=='RECORD_EXIST'){

			try{

				for (i in msg.obj1) {
					let x = msg.obj1[i];
					console.log(x.customerid)
					var objTo = document.getElementById('kycdocdata');
					var divtest = document.createElement("div");
					divtest.className="col-sm-2 card mx-auto animated fadeIn";
					divtest.innerHTML = "<img class='img-fluid mx-auto' style='height: 8rem;' src='data:image/png;base64,"+x.image+"'> <div class='card-body border-top'><p class='card-text'><strong>Type- </strong>"+x.imagesubtype+"</p></div> ";
						
					objTo.appendChild(divtest);

				}
			}catch(err){
				console.log(err.message)
			}
		}else if(msg.statusCode==100 && msg.statusMsg=='NO_RECORDS'){
			$("#kycdocdata").html("<h6>No records found.</h6>");
		}else{
			$("#kycdocdata").html("<h6>Response- "+msg.statusMsg+"</h6>");
		}
	});

	request.fail(function(jqXHR, textStatus) {
//		console.log(textStatus);
		$("#kycdocdata").html("<h6>Failed to make request. Please try after sometime</h6>");
	});

	request.always(function(msg) {
		$("#viewmfdkycdoc").html("View Documents");

	});

});

$(document).ready(function() {
	$("#addressproofTypeid").change(function() {
		
		let x = $("#addressproofTypeid").val();
		console.log("changed to= "+ x)
		if(x =='A' || x=='B' ){
			console.log("Not requirewd")
			$("#addressproofpxpirydateid").removeAttr("required");
		}else{
			$("#addressproofpxpirydateid").attr("required", true);
		}
	});

});

