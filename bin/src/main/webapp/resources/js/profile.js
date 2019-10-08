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