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


//selectfund

$(document).on("click", "#radioamount", function() {
	var x = $("input[type='radio'][name='options']:checked").val();
	/* console.log("Test" + $("input[type='radio'][name='options']:checked").val()); */
	$("#amount").val(x);
});

function customamount() {
	if ($('input:radio[name="options"]:checked')) {
		console.log("Check active")
		$('label').removeClass('active');
	}

}

$(document).on("click", "#transactionType1", function() {
	$("#sipbox").show();
});

$(document).on("click", "#transactionType2", function() {
	$("#sipbox").hide();
});
