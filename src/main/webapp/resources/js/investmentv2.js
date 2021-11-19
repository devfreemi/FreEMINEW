//console.log = function(){};
var mfdatapulled=false;
var mfdatainprogress=false;
var loanhandler=false;
var statusobj;

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



function completePendingPayments(){

	request = $.ajax({
		url: "/products/api/mutual-funds/pending-payments",
		method: "POST",
		data: {
			"mobile" : "NA"
		},
		async: true,
		datatype: "json",
		contentType: "application/json",
		beforeSend: function() {
			showtaskProgress("pendinglink");
		}
	});

	request.done(function(data) {

		if (data == 'NO_SESSION') {
			alert("Your session has expired. Kindly login again.");
		} else if (data == 'SERVICE_DISABLED') {
			alert("Services are currently disabled. Kindly try again later.")
		}else if (data == 'NO_REGISTERED' ) {
			alert("You need to complete MF account registration to avail this service. Kindly contact admin if you are registered and facing issue.");
		} else if (data == 'UNAVAILABLE' || data == 'INTERNAL_ERROR') {
			alert("Unable to process the request. Kindly contact admin");
		} else {
			window.location.href = data;
		}
	});

	request.fail(function(jqXHR, textStatus) {
		alert("Failed to process your request!");

	});

	request.always(function(msg){
		completetaskProgress("pendinglink","COMPLETE PENDING PAYMENTS");
	});

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
//	console.log("Order staus for id- " + clientId + " : " + orderNo);
	$.get("/products/mutual-funds/orderpaymentStatus", {
		client : clientId,
		order : orderNo
	}, function(data, status) {

		console.log(data);
		$('#exampleModal1').modal('hide');
		if (data == 'NO_SESSION') {

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

function getMFPortfolioData(mobile,profileStatus) {
	console.log("Get Profile Data as requested- "+mobile);

	if(profileStatus === 'PROFILE_READY'){
		if(!mfdatapulled){

			let checkexpiretime =window.sessionStorage.getItem("expiremfdata");
//			console.log(checkexpiretime);
			if(checkexpiretime!= null && (new Date() > new Date(checkexpiretime))){
				console.log("Time expired");
				window.sessionStorage.removeItem("mftata");
				window.sessionStorage.removeItem("expiremfdata");
			}else{
//				console.log("Within time or null");
			}

			$("#mfdatatbody2").children().remove();

			var storedmfdata=sessionStorage.getItem("mftata");
//			console.log("storedmfdata- "+ storedmfdata);
			if(storedmfdata == null && mfdatainprogress == false){
				request = $.ajax({
					url: "/products/api/mf/getmfprofileData",
					method: "POST",
					data: {
						"mobile" : mobile

					},
					async: true,
					datatype: "json",
					beforeSend: function() {
						showprogress("msgmfapi");
						mfdatainprogress=true;
					}
				});

				request.done(function(msg) {

					if(msg=="NO_SESSION"){
						$("#msgmfapi").text("Session expired. Login to your account again.");

					}else if(msg=="REQUEST_DENIED"){
						$("#msgmfapi").text("Request Denied. Please try again or contact admin.");
					}else if(msg=="NO_DATA"){
						$("#msgmfapi").html("<a href=\"/mutual-funds\"><button class=\"btn btn-sm\"><img src=\"https://resources.freemi.in/products/resources/images/invest/investment4.png\" class=\"img-fluid\" style=\"height: 2rem;\"> Start Investing</button> </a>");
					}else{
						$("#msgmfapi").text("");
						$("#msgmfapi").html("");

						if(msg.length>0){
							createmfdataView(msg);

							if (typeof(Storage) !== "undefined") {
								window.sessionStorage.setItem("mftata",msg);
								window.sessionStorage.setItem("expiremfdata",(new Date(new Date().getTime() + (60000 * 60))));
							}


						}else{
							$("#msgmfapi").html("<a href=\"/mutual-funds\"><button class=\"btn btn-sm\"><img src=\"https://resources.freemi.in/products/resources/images/invest/investment4.png\" class=\"img-fluid\" style=\"height: 2rem;\"> Start Investing</button> </a>");
						}

					}

				});

				request.fail(function(jqXHR, textStatus) {
					$("#msgmfapi").text("Failed to fetch your data. Please try after sometime");

				});


				request.always(function(msg){
					console.log("MF data fetch complete.");
					mfdatainprogress=false;
				});

			}else{
				console.log("Populate stored data");
				$("#msgmfapi").html("");
				createmfdataView(storedmfdata);

			}

		}
	}else{
		$("#msgmfapi").html("<a href=\"/products/mutual-funds/register?mf=01\"><button class=\"btn btn-sm\"> Complete you Mutual Fund Account registrastion</button></a>");
	}
}


function createmfdataView(result){
//	console.log("Tranasaction Data- " +result.length + " -> " + result);
	mfdatapulled = true;
	var data = JSON.parse(result);
	var table = document.getElementById("mfdatatbody2");
	table.setAttribute("class","animated fadeIn");

	for (var i = 0; i < data.length; i++) {
		/*    console.log(jsonData[i]); */

		var x= data[i];
//		console.log(x);
		var fundName = x.fundName;
		var folioNumber = x.folioNumber;
//		console.log(data[i].fundName.toString() + " ->" + data[i].folioNumber.toString() + " -> " + data[i].collaboratedMarketValue);

		var row = table.insertRow();
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);

//		Add some text to the new cells:

		cell1.innerHTML =  "<img src=\"https:\/\/resources.freemi.in\/products\/resources\/images\/partnerlogo\/mf\/"+data[i].amcicon+"\" class=\"img-fluid mr-2\" style=\"width: 3rem; float: left;\" >" +data[i].fundName ;
		cell2.innerHTML = Number(data[i].collaboratedAmount).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');

		if(Number(data[i].collaboratedMarketValue)>Number(data[i].collaboratedAmount) ){
//			cell3.innerHTML = "<span class='text-success' >"+Number(data[i].collaboratedMarketValue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"</span>";
			cell3.innerHTML = "<span class='text-success'>"+convertNumberToIndianFormat(Number(data[i].collaboratedMarketValue).toFixed(2))+"</span>";
		}else if(Number(data[i].collaboratedMarketValue)<Number(data[i].collaboratedAmount)){
//			cell3.innerHTML = "<span class='text-danger' >"+Number(data[i].collaboratedMarketValue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"</span>";
			cell3.innerHTML = "<span class='text-danger'>"+convertNumberToIndianFormat(Number(data[i].collaboratedMarketValue).toFixed(2))+"</span>";
		}else{
//			cell3.innerHTML = "<span class='grey-text'>"+Number(data[i].collaboratedMarketValue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"</span>";
			cell3.innerHTML = "<span class='grey-text'>"+convertNumberToIndianFormat(Number(data[i].collaboratedMarketValue).toFixed(2))+"</span>";
		}

		cell4.innerHTML = "<a class=\"collapsed\" data-toggle=\"collapse\" href=\"#collapse"+i+"\" role=\"button\" aria-expanded=\"false\" aria-controls=\"collapse"+i+"\"> <span class=\"fas fa-chevron-down\" id=\"rotate\"></span></a>";

		var innerrow = table.insertRow();
		var cell11 = innerrow.insertCell(0);
		cell11.colSpan = 7;
		cell11.style.padding= "0px";
//		cell11.innerHTML =  "<div class=\"collapse\" id=\"collapse"+i+"\" style=\"margin: .2rem;\"></div>" ;
		var div = document.createElement("div");
		div.setAttribute("class", "collapse");
		div.setAttribute("id", "collapse"+i);
		div.style.margin= ".2rem";

		cell11.appendChild(div);

//		Insert table inside div
		var div1 = document.createElement("div");
		div1.setAttribute("class", "card card-body");
		div1.style.padding= "0";
		div.appendChild(div1);

		var amcfoliotable1 = document.createElement("TABLE");
		amcfoliotable1.setAttribute("class", "table table-sm");

		div1.appendChild(amcfoliotable1);

		var amcfoliotablethead1 = document.createElement("THEAD");
		amcfoliotablethead1.setAttribute("class","#00796b teal darken-2 white-text");
		amcfoliotable1.appendChild(amcfoliotablethead1);

		var datarow = amcfoliotablethead1.insertRow();
		datarow.setAttribute("class", "mftransdetailhead");

		datarow.insertCell(0).outerHTML="<th scope=\"col\" style=\"width: 20rem;\" valign=\"middle\">Folio <br> Scheme Code/Name </th>";
		datarow.insertCell(1).outerHTML="<th scope=\"col\" valign=\"middle\">Invested Value (Rs.) </th>";
		datarow.insertCell(2).outerHTML="<th scope=\"col\" valign=\"middle\">Bal. Units </th>";
		datarow.insertCell(3).outerHTML="<th scope=\"col\" valign=\"middle\">NAV (As on Date) </th>";
		datarow.insertCell(4).outerHTML="<th scope=\"col\" valign=\"middle\">Current Value (Rs.) </th>";
		datarow.insertCell(5).outerHTML="<th scope=\"col\" valign=\"middle\">Action </th>";

		var amcfoliotabletbody1 = document.createElement("tbody");
		amcfoliotable1.appendChild(amcfoliotabletbody1);

//		console.log(data[i].karvyFolioList);

		for (var j = 0; j < data[i].karvyFolioList.length; j++) {
			var amcfoliodesc = amcfoliotabletbody1.insertRow();

			var amccell1 = amcfoliodesc.insertCell(0);
			var amccell2 = amcfoliodesc.insertCell(1);
			var amccell3 = amcfoliodesc.insertCell(2);
			var amccell4 = amcfoliodesc.insertCell(3);
			var amccell5 = amcfoliodesc.insertCell(4);

			amccell1.innerHTML =data[i].karvyFolioList[j].folioNumber + "<br>"+data[i].karvyFolioList[j].fundDescription+"/"+data[i].karvyFolioList[j].bsemfschemeCode;
			amccell1.setAttribute("valign","middle");

			amccell2.innerHTML =Number(data[i].karvyFolioList[j].invAmount).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
			amccell2.setAttribute("valign","middle");

			amccell3.innerHTML =data[i].karvyFolioList[j].units;
			amccell3.setAttribute("valign","middle");

			amccell4.innerHTML =data[i].karvyFolioList[j].nav +"<br>"+"<span style=\"font-size: 9px; color: grey;\">("+data[i].karvyFolioList[j].navdate+")</span>";
			amccell4.setAttribute("valign","middle");

			if(Number(data[i].karvyFolioList[j].marketValue)>Number(data[i].karvyFolioList[j].invAmount)){
//				amccell5.innerHTML ="<span class='green-text'>"+ Number(data[i].karvyFolioList[j].marketValue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"</span>";
				amccell5.innerHTML ="<span class='green-text'>"+ convertNumberToIndianFormat(Number(data[i].karvyFolioList[j].marketValue).toFixed(2))+"</span>";
			}else if(Number(data[i].karvyFolioList[j].marketValue)<Number(data[i].karvyFolioList[j].invAmount)){
//				amccell5.innerHTML ="<span class='deep-orange-text'>"+ Number(data[i].karvyFolioList[j].marketValue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"</span>";
				amccell5.innerHTML ="<span class='deep-orange-text'>"+ convertNumberToIndianFormat(Number(data[i].karvyFolioList[j].marketValue).toFixed(2))+"</span>";
			}else{
//				amccell5.innerHTML ="<span class='grey-text'>"+ Number(data[i].karvyFolioList[j].marketValue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"</span>";
				amccell5.innerHTML ="<span class='grey-text'>"+ convertNumberToIndianFormat(Number(data[i].karvyFolioList[j].marketValue).toFixed(2))+"</span>";
			}
			amccell5.setAttribute("valign","middle");

//			convertNumberToIndianFormat(Number(data.marketvalue).toFixed(2))

//			console.log(data[i].karvyFolioList[j]);
			var div2 = document.createElement("div");
			div2.setAttribute("class", "btn-group");
			amcfoliodesc.insertCell(5).appendChild(div2);

			var button1 = document.createElement("button");
			button1.setAttribute("class","btn btn-secondary dropdown-toggle btn-sm ");
			button1.setAttribute("data-toggle","dropdown");
			button1.setAttribute("aria-haspopup","true");
			button1.setAttribute("aria-expanded","false");
			button1.style.fontSize="11px";
			button1.style.padding="5px";
			button1.style.width="7rem";
			button1.innerHTML="ACTION";
			div2.appendChild(button1);

			var div3 = document.createElement("div");
			div3.setAttribute("class", "dropdown-menu dropdown-menu-right");
			div3.innerHTML="<button class=\"dropdown-item\" type=\"button\" style=\"font-size: 12px; color: #238019; font-weight: 600;\" onclick=\"AdditionalPurchase('"+data[i].karvyFolioList[j].folioNumber+"','"+data[i].karvyFolioList[j].bsemfschemeCode+"','"+data[i].karvyFolioList[j].trasanctionType +"','"+data[i].karvyFolioList[j].rtaAgent+"','"+data[i].karvyFolioList[j].channelProductCode+"')\"> Invest More <i class=\"fas fa-arrow-left\"></i></button>";
			div2.appendChild(div3);

			if(Number(data[i].karvyFolioList[j].invAmount)>0){
				var button3 = document.createElement("button");
				button3.setAttribute("class", "dropdown-item");
				button3.setAttribute("onclick","MFRedeem('"+data[i].karvyFolioList[j].folioNumber+"','"+data[i].karvyFolioList[j].bsemfschemeCode+"','"+data[i].karvyFolioList[j].trasanctionType +"','"+data[i].karvyFolioList[j].rtaAgent+"','"+data[i].karvyFolioList[j].channelProductCode+"')");
				button3.style.fontSize="12px";
				button3.style.color="#da2323";
				button3.style.fontWeight="600";

				button3.innerHTML="Redeem <i class=\"fas fa-arrow-right\"></i>";
				div3.appendChild(button3);

			}


//			var button2 = document.createElement("button").innerHTML="";
//			button1.setAttribute("class","dropdown-item");

		}

	}
}


function getMfData(profileStatus,pan,mobile){
	console.log("Request received to get invested data - "+ pan + " -> "+ profileStatus);
	$("#mffetchmsg").text("");
	let checkexpiretime =window.sessionStorage.getItem("expiremfbaldata");
//	console.log(checkexpiretime);
	if(checkexpiretime!= null && (new Date() > new Date(checkexpiretime))){
		console.log("Time expired");
		window.sessionStorage.removeItem("mfbalance");
		window.sessionStorage.removeItem("expiremfbaldata");
	}else{
//		console.log("Within time or null");
	}

	if(profileStatus === 'PROFILE_READY')
	{
		var storeddata=sessionStorage.getItem("mfbalance");
//		console.log("storeddata- "+ storeddata);
		if(storeddata == null){
			request = $.ajax({
				url: "/products/api/mf/getmfportfoliototal",
				method: "POST",
				data: {
					"mobile" : mobile,
					"pan" : pan

				},
				async: true,
				datatype: "json",
				beforeSend: function() {
					displayMfFetchProgress();
				}
			});
			request.done(function(msg) {
//				console.log(msg);
				if(msg=="NO_SESSION"){
					$("#inval").html("0");
					$("#marketval").html("0");
					$("#mffetchmsg").text("Invalid session. Kindly login again.");

				}else if(msg==="REQUEST_DENIED" || msg==="PAN_INVALID"){
					$("#inval").html("0");
					$("#marketval").html("0");
					$("#mffetchmsg").text("Invalid request.");
				}else if(msg==="NO_DATA"){
					$("#inval").html("0");
					$("#marketval").html("0");
				}else{
					if (typeof(Storage) !== "undefined") {
						window.sessionStorage.setItem("mfbalance",msg);
						window.sessionStorage.setItem("expiremfbaldata",(new Date(new Date().getTime() + (60000 * 60))));
					} /*else {
					console.log("Unsupported");
					}*/
					var data = JSON.parse(msg);
					if(msg.invvalue !='null'){
						/*$("#inval").text(Number(data.invvalue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,'));
						$("#marketval").text(Number(data.marketvalue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,'));*/
						var result;
						result=convertNumberToIndianFormat(Number(data.invvalue).toFixed(2));
						$("#inval").text(result);
						result= convertNumberToIndianFormat(Number(data.marketvalue).toFixed(2));
						$("#marketval").text(result);

					}
				}

			});

			request.fail(function(jqXHR, textStatus) {
//				console.log("Failed to fetch your data. Please try after sometime: " + textStatus);
				$("#inval").html("NaN");
				$("#marketval").html("NaN");
				$("#mffetchmsg").text("*Failed to display your data");

			});

			request.always(function(msg){
			});

		}else{
//			console.log("Read from session-");
			var data = JSON.parse(storeddata);
			if(storeddata.invvalue !='null'){
				var result;
				result=convertNumberToIndianFormat(Number(data.invvalue).toFixed(2));
				$("#inval").text(result);
				result= convertNumberToIndianFormat(Number(data.marketvalue).toFixed(2));
				$("#marketval").text(result);
			}

		}
	}else{
//		console.log("Profile not ready..");
	}

}



function displayMfFetchProgress(){
	$("#inval").html("<i class=\"fas fa-spinner fa-spin\"></i>");
	$("#marketval").html("<i class=\"fas fa-spinner fa-spin\"></i>");
}

function showtaskProgress(elementid){
	$("#"+elementid).html("PROCESSING <i class=\"fas fa-spinner fa-spin\"></i>");
	$('#'+elementid).attr('disabled','disabled');
}

function completetaskProgress(elementid,msg){
	$("#"+elementid).html(msg);
	$('#'+elementid).removeAttr('disabled');
}

function convertNumberToIndianFormat(value){
//	console.log("Called- "+ value);
	var res;
	var x=value;
	x=x.toString();
	var afterPoint = '';
	if(x.indexOf('.') > 0)
		afterPoint = x.substring(x.indexOf('.'),x.length);
	x = Math.floor(x);
	x=x.toString();
	var lastThree = x.substring(x.length-3);
	var otherNumbers = x.substring(0,x.length-3);
	if(otherNumbers != '')
		lastThree = ',' + lastThree;
	var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
//	console.log(res);
	return res;
}


$(document).ready(function(){
	$("#sipcancelform").submit(function(e){
		e.preventDefault();
		console.log("Submitted");

		request = $.ajax({
			url: "/products/api/mutual-funds/cancel-sip",
			method: "POST",
			data: JSON.stringify({
				"orderno" : $("#orderno").val(),
				"clientid" : $("#formclientid").val(),
				"transactionid" : $("#siptransactionid").val()
			}),
			async: true,
			datatype: "json",
			contentType: 'application/json',
			beforeSend: function() {
				$("#cancelsubmitbtn").html("Processing <i class=\"fas fa-spinner fa-spin\"></i>");
				$('#cancelsubmitbtn').attr('disabled','disabled');
			}
		});
		request.done(function(msg) {
//			console.log(msg);
			if(msg=="SUCCESS"){
				$("#cancelresponse").text("SIP Cancelled Successfully");
			}
			if(msg=="NO_SESSION"){
				$("#cancelresponse").text("Invalid session. Kindly login again.");

			}else if(msg==="REQUEST_DENIED" || msg==="PAN_INVALID"){
				$("#cancelresponse").text("Invalid request.");
			}else if(msg==="NO_DATA"){
				$("#cancelresponse").text("Invalid data");
			}else{
				$("#cancelresponse").text(msg);

			}

		});

		request.fail(function(jqXHR, textStatus) {
			$("#cancelresponse").text("Failed to process your request.");

		});

		request.always(function(msg){
			$("#cancelsubmitbtn").html("Cancel SIP");
			$('#cancelsubmitbtn').removeAttr('disabled');
		});


	});
});




function getfdbalance(mobileno,pan){
	fdapifundcall(mobileno,pan,'BALANCE');
}

function calculatefdtotals(result){
	console.log("Call received to generate balance view.." + result)
	var msg = result;
	if(msg!='undefined' || msg != undefined){
	if(msg==="NO_SESSION"){
		$("#balancecheckid").html("View Balance");
		$("#fdfetchmsg").text("Invalid session. Kindly login again.");
	}else if(msg==="NO_PAN"){
		$("#balancecheckid").html("View Balance");
		$("#fdfetchmsg").text("Invalid request. ");
	}else if(msg==="NO_DATA"){
		$("#balancecheckid").html("View Balance");
		$("#fdfetchmsg").text("No savings found!");
		$("#balancecheckid").html("View Balance");
	}else if(msg==="INAVLID_REQUEST" || msg === 'INTERNAL_ERROR' || msg === 'API_ERROR'){
		$("#balancecheckid").html("View Balance");
		$("#fdfetchmsg").text("Request could not be processed.");
	}else{
		$("#fdfetchmsg").text("");
		
		var data = JSON.parse(result);
		console.log("Tranasaction Data- " +data.length + " -> " + data);
		let amount=0;
		let matured=0;
		for (var i = 0; i < data.length; i++) {
			var x= data[i];
			if(x.DEP_STATUS_CODE == 'L'){
				amount+=x.PRINC_AMT;
				matured+=x.MATU_AMT;
			}
		}
		
		$("#balancedata").html("<div class='row animated fadeIn'><div class='col-6'>" +
				"<h6>TOTAL FD SAVINGS</h6></div><div class='col-6'><h5><i class='fas fa-rupee-sign'> </i> <span id='totalfdprincipal'>"+convertNumberToIndianFormat(amount)+" </span></h5></div></div>" +
				"<div class='row animated fadeIn'><div class='col-6'>" +
				"<h6>TOTAL MATURITY</h6></div><div class='col-6'><h5><i class='fas fa-rupee-sign'> </i> <span id='totalfdmaurity'>"+convertNumberToIndianFormat(matured)+"</span></h5></div></div>");
	}
	}else{
		$("#balancecheckid").html("View Balance");
		$("#fdfetchmsg").text("Unable to process request.");
	}
}

function getfixeddepositdata(mobileno,pan){

	console.log("Call receivedfor FD fetch...")

	fdapifundcall(mobileno,pan,'CREATEVIEW');

	/*	console.log("Response from api- "+ msg);
		if(msg=="NO_SESSION"){
			$("#fdfetch").html("Session expired. Please login again");
		}else if(msg==="NO_PAN"){
		}else if(msg==="NO_DATA"){
			$("#fdfetch").html("<a href=\"/fixed-deposit\"><button class=\"btn btn-sm\"><img src=\"https://resources.freemi.in/products/resources/images/invest/investment4.png\" class=\"img-fluid\" style=\"height: 2rem;\"> Start Saving</button> </a>");
		}else if(msg==="INAVLID_REQUEST"){
			$("#fdfetch").html("Failed to process request. Please contact admin if issue persist.");
		}else if(msg==="INTERNAL_ERROR" || msg==="API_ERROR"){
			$("#fdfetch").html("Error processing request. Please try again after sometime");
		}else{
			let result1=JSON.stringify(msg);
			if (typeof(Storage) !== "undefined") {
				window.sessionStorage.setItem("fdfolios",result1);
			} 
			console.log(msg);

			createfddataView(result1);
		}
	 */


}


function fdapifundcall(mobileno,pan,postcallfunc){
	var apiresponse;
	var fdtempdata=sessionStorage.getItem("fdfolios");
	if(fdtempdata == null){
		request = $.ajax({
			url: "/products/api/fixed-deposit/get-fd-portfolios",
			method: "POST",
			data: {
				"mobile" : mobileno,
				"pan" : pan
			},
			async: true,
			datatype: "json",
			beforeSend: function() {
				if(postcallfunc==='BALANCE'){
						$("#balancecheckid").html("Processing <i class=\"fas fa-spinner fa-spin\"></i>");
				}else{
					showprogress("fdfetch");
				}
			}
		});
		request.done(function(msg) {
			console.log("RECEIVED- "+ msg)
			apiresponse = msg;
			//let result1=JSON.stringify(msg);
			if(msg=="NO_SESSION" || msg==="NO_PAN" || msg==="NO_DATA" || msg==="INAVLID_REQUEST" || msg === 'INTERNAL_ERROR' || msg === 'API_ERROR'){
				console.log("Response not success..")
			}else{
				apiresponse=JSON.stringify(msg);
				if (typeof(Storage) !== "undefined") {
					window.sessionStorage.setItem("fdfolios",apiresponse);
				}
			}

			if(postcallfunc==='BALANCE'){
				calculatefdtotals(apiresponse);
			}else if(postcallfunc==='CREATEVIEW'){
				createfddataView(apiresponse);
			}
		});
		request.fail(function(jqXHR, textStatus) {
			console.log("Failed to fetch your data. Please try after sometime: " + textStatus);
			apiresponse="API_ERROR";
		});

		request.always(function(msg){
			console.log("Request complete..")
			if(postcallfunc==='BALANCE'){
				$("#balancecheckid").html("View Balance");
		}else{
//			progresscomplete("fdfetch");
		}
			
		});
	}else{
		apiresponse = fdtempdata;

		if(postcallfunc==='BALANCE'){
			calculatefdtotals(apiresponse);
		}else if(postcallfunc==='CREATEVIEW'){
			createfddataView(apiresponse);
		}
	}



}


function createfddataView(result){
	console.log("Request received to generate folio view...");
	$("#fddatabody").children().remove();
	
	if(result=="NO_SESSION"){
		$("#fdfetch").html("Session expired. Please login again");
	}else if(result==="NO_PAN"){
		$("#fdfetch").html("PAN record not present. Please contact admin if you carried out transaction.");
	}else if(result==="NO_DATA"){
		$("#fdfetch").html("<p>No savings record.</p><a href=\"/fixed-deposit/\"><button class=\"btn btn-sm btn-default\"><img src=\"https://resources.freemi.in/products/resources/images/invest/investment4.png\" class=\"img-fluid\" style=\"height: 2rem;\"> Start Saving</button> </a>");
	}else if(result==="INAVLID_REQUEST"){
		$("#fdfetch").html("Failed to process request. Please contact admin if issue persist.");
	}else if(result==="INTERNAL_ERROR" || result==="API_ERROR"){
		$("#fdfetch").html("Error processing request. Please try again after sometime");
	}else{

		var data = JSON.parse(result);
		console.log("Tranasaction Data- " +data.length + " -> " + data);

		var table = document.getElementById("fddatabody");
		table.setAttribute("class","animated fadeIn");
		console.log("Total records- " + data.length + " -> "+ data);
		for (var i = 0; i < data.length; i++) {
			/*    console.log(jsonData[i]); */

			var x= data[i];

			var row = table.insertRow();
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);
			var cell7 = row.insertCell(6);
			var cell8 = row.insertCell(7);

//			Add some text to the new cells:

			cell1.innerHTML =  "<img src=\"https:\/\/resources.freemi.in\/products\/resources\/images\/invest\/mahindra-finance-icon.png\" class=\"img-fluid mr-2\" >";
			cell2.innerHTML = x.FOLIO;
			cell3.innerHTML =  x.FDR_NO;
			cell4.innerHTML = x.APL_NO;
			cell5.innerHTML = convertNumberToIndianFormat(x.PRINC_AMT);
			cell6.innerHTML = convertNumberToIndianFormat(x.MATU_AMT) +"<br>" + "<small class='text-muted'>(On "+x.MATU_DATE+")</small>";
			cell7.innerHTML = x.DEP_STATUS_DESC;
			cell8.innerHTML = "<span class=\"text-danger\" type=\"button\" style=\"font-size: 12px; color: #238019; font-weight: 600;\" data-toggle='modal' " +
			"data-fdrno='"+x.FDR_NO + "'" +
			"data-appl='"+x.APL_NO + "'" +
			"data-folio='"+x.FOLIO +"'" +
			"data-invname='"+x.INVESTOR_NAME +"'" +
			"data-fdrdate='"+x.FDR_DATE +"'" +
			"data-principal='"+x.PRINC_AMT +"'" +
			"data-maturity='"+x.MATU_AMT +"'" +
			"data-maturitydate='"+x.MATU_DATE +"'" +
			"data-schemetype='"+x.SCHEMETYPE_CODE +"'" +
			"data-tenure='"+x.PERIOD_MM +"'" +
			"data-intrate='"+x.INT_RATE +"'" +
			"data-renewaleligibility='"+x.RENEWAL_ELIGIBILITY +"'" +
			"data-comments='"+x.CONDITIONAL_REASON +"'" +
			"' data-target='#fddetailsmodal'> Details</button>";

		}
		
		progresscomplete("fdfetch");
	}
}


/* ------------------------------------------------------------------------------------------------- */
function gethdfcloanrequestlist(mobileno,pan){
	console.log("Call receivedfor FD fetch...")
	$("#loanreqmsg").html("");
	loanapilistcall(mobileno,pan,'CREATEVIEW');
}

function loanapilistcall(mobileno,pan,postcallfunc){
	var apiresponse;
	var loantempdata=sessionStorage.getItem("loanrequestlist");
	if(loantempdata == null){
		request = $.ajax({
			url: "/products/api/loan/get-hdfc-loan",
			method: "POST",
			data: {
				"mobile" : mobileno,
				"pan" : pan
			},
			async: true,
			datatype: "json",
			beforeSend: function() {
				showprogress("loanfetch");
			}
		});
		request.done(function(msg) {
			
			apiresponse = msg;
			apiresponse=JSON.stringify(msg);
//			console.log("RECEIVED- "+ apiresponse);
			createloandataView(msg);				
		});
		request.fail(function(jqXHR, textStatus) {
			console.log("Failed to fetch your data. Please try after sometime: " + textStatus);
			apiresponse="API_ERROR";
		});

		request.always(function(msg){
			console.log("Request complete..")
//			progresscomplete("loanfetch");
		});
	}else{
		apiresponse = loantempdata;

		if(postcallfunc==='BALANCE'){
//			calculatefdtotals(apiresponse);
		}else if(postcallfunc==='CREATEVIEW'){
			createloandataView(apiresponse);
		}
	}



}


function createloandataView(result){
	console.log("Request received to display loan data");
	$("#loanlistbody").children().remove();

	if(result.status == "0"){
		$("#loanfetch").html("<button class=\"btn btn-sm btn-secondary\" onclick=\"gethdfcloanrequestlist(reqid);\">Fetch your Loan Requests</button>");
		if(result.msg=="NO_SESSION"){
			$("#loanreqmsg").html("Session expired. Please login again");
		}else if(result.msg==="NO_PAN"){
			$("#loanreqmsg").html("PAN record not present. Please contact admin if you carried out transaction.");
		}else if(result.msg==="NO_DATA"){
			$("#loanreqmsg").html("<p>No loan request record.</p><a href=\"/fixed-deposit/\"><button class=\"btn btn-sm btn-primary\">Apply for HDFC Loan</button> </a>");
		}else if(result.msg==="INAVLID_REQUEST"){
			$("#loanreqmsg").html("Failed to process request. Please contact admin if issue persist.");
		}else if(result.msg==="SERVICE_CONN_FAILURE"){
			$("#loanreqmsg").html("Failed to fetch data. Please try after sometime.");
		}else if(result.msg==="INTERNAL_ERROR" || result.msg==="API_ERROR"){
			$("#loanreqmsg").html("Error processing request. Please try again after sometime");
		}
	}else{
//		var data = JSON.parse(result);
//		console.log("Tranasaction Data- " +data.data.length + " -> " + data.data);
		if(result.data!=null && result.data.length > 0){
		
		var table = document.getElementById("loanlistbody");
		table.setAttribute("class","animated fadeIn");
//		console.log("Total records- " + data.data.length + " -> "+ data.data);
		for (var i = 0; i < result.data.length; i++) {
			/*    console.log(jsonData[i]); */

			var x= result.data[i];
			var row = table.insertRow();
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);
			var cell7 = row.insertCell(6);
			var cell8 = row.insertCell(7);
			var cell9 = row.insertCell(8);
//			Add some text to the new cells:
			
			cell1.innerHTML =  "<img src=\"https:\/\/resources.freemi.in\/products\/resources\/images\/partnerlogo\/loans\/hdfc-bank.svg\" class=\"img-fluid mr-2\" style=\"height: 1rem\" >";
			cell2.innerHTML = x[1];
			cell3.innerHTML = x[0];
			cell4.innerHTML = x[2];
			cell5.innerHTML = "&#8377;"+convertNumberToIndianFormat(x[3]);
			cell6.innerHTML = x[11];
			cell7.innerHTML = x[8];
			cell8.innerHTML = x[9];
			
			if(x[7] == 'Y'){
				cell9.innerHTML = "<button class=\"btn btn-sm btn-success p-1 mt-0 loanact\" type=\"button\" style=\"font-size: 12px; width: 5rem;\" data-action='STAT' data-bank='HDFC' data-refno='"+x[0] +"' data-ackno='"+x[2] +"' data-applno='"+x[11] +"'> Status</button>";
			}else if(x[7] == 'Incomplete'){
				cell9.innerHTML = "<button class=\"btn btn-sm btn-primary p-1 mt-0 loanact\" type=\"button\" style=\"font-size: 12px; width: 5rem;\" data-action='COMP' data-bank='HDFC' data-refno='"+x[0] +"' data-ackno='"+x[2] +"' data-applno='"+x[2] +"'> Complete</button>";
			}else{
				cell9.innerHTML = "";
			}
		}
		progresscomplete("loanfetch");
		
		} else{
			$("#loanfetch").html("<a href=\"/hdfc-loan\" class=\"btn btn-sm btn-primary\"\">Apply HDFC Loan</button>");
			$("#loanreqmsg").html("No loan history. Apply for personal loan  bank.");
		}
		
	}
}


$(document.body).on('click', '.loanact' ,function(){
	
//    alert($(this).data('action') + " - " + $(this).data('refno') + "- " + $(this).data('ackno'));
	if(!loanhandler){
		loanhandler = true;
		console.log("Clikced- "+ $(this) );	
		if($(this).data('action') == 'STAT'){
			getloanrequestcurrentstat($(this),$(this).data('bank'),$(this).data('refno'),$(this).data('ackno'),$(this).data('appno'),reqid);
//			getloanrequestcurrentstat($(this))
		}else if($(this).data('action') == 'COMP'){
			completeloanrequest($(this),$(this).data('bank'),$(this).data('refno'),$(this).data('ackno'),$(this).data('appno'),reqid);
		}else{
			alert("Invalid action!")
		}
	}else{
		alert("Last action is still under progress");
	}
	
});


function getloanrequestcurrentstat(event, bank,loanref, ackno, appno,mobileno){
//function getloanrequestcurrentstat(event){
	var apiresponse;
	var temptext;
	request = $.ajax({
		url: "/products/api/loan/get-loan-status",
		method: "POST",
		data: {
			"mobile" : mobileno,
			"refno" : loanref,
			"ackno" : ackno,
			"appno" : appno,
			"bank" : bank
		},
		async: true,
		datatype: "json",
		beforeSend: function() {
//			showprogress("loanfetch");
			temptext= event.text();
			console.log(temptext);
			event.html("Wait <i class=\"fas fa-spinner fa-spin\"></i>");
		}
	});
	request.done(function(msg) {
		apiresponse = msg;
//		statusobj=apiresponse;
		console.log("RECEIVED- "+ apiresponse);
		createloanstatusview(msg);				
	});
	request.fail(function(jqXHR, textStatus) {
		console.log("Failed to fetch your data. Please try after sometime: " + textStatus);
		alert("Your request failed! Kindly try after sometime.");
		apiresponse="API_ERROR";
	});

	request.always(function(msg){
		console.log("Request complete..");
		event.html(temptext);
		loanhandler = false;
	});

	
	
}

function completeloanrequest(event, bank,loanref, ackno, appno,mobileno){
	console.log("Complete loan request post submit");
	$('#completeloanreqmodal').modal('show');
	$("#mobno").val(mobileno);
	$("#reqbank").val(bank);
	$("#ackno").val(ackno);
	$("#applno").val(appno);
	$("#action").val("COMPLETE_REQUEST");
	$("#loanrefno").val(loanref);
	$("#loanapplystatus").val("Incomplete")
	
}



function createloanstatusview(msg){
	$('#loanstatusmodal').modal('show');
	if(msg.applicationstatus == 'Success'){
		if(msg.loanstatus!=null){
			$("#loancustomername").html(msg.loanstatus.getStatusEnquiryResponse.getStatusEnquiryResult.CUSTNAME);
			$("#loanapplno").html(msg.applicaitonid);
			$("#loanapplstatus").html(msg.loanstatus.getStatusEnquiryResponse.getStatusEnquiryResult.status);
		}else{
			$("loanstatusmsg").text("Status data could not fetched. Please try after sometime");
		}
	}else{
		$("loanstatusmsg").text("The application was not successful.");
	}
	
}


/* --------------------------------------------------------------------------------------------------------- */


function showprogress(elementid){
	$("#"+elementid).html("<div style=\"text-align: center;margin-bottom: 3rem;\"><img alt=\"Fetching your portfolio\" src=\"https://resources.freemi.in/products/resources/images/invest/progress2.gif\">");
}

function progresscomplete(elementid){
	$("#"+elementid).html("");
}

$("#fddetailsmodal").on('show.bs.modal', function(event) {
//	console.log("Modal called")
	$("#nbscid").text("Mahindra Finance");
	if ($(event.relatedTarget).attr("data-fdrno") != undefined) {
		$("#fdrnoid").text($(event.relatedTarget).attr("data-fdrno"));
	}
	if ($(event.relatedTarget).attr("data-invname") != undefined) {
		$("#invnameid").text($(event.relatedTarget).attr("data-invname"));
	}
	if ($(event.relatedTarget).attr("data-fdrdate") != undefined) {
		$("#purchasedateid").text($(event.relatedTarget).attr("data-fdrdate"));
	}
	if ($(event.relatedTarget).attr("data-principal") != undefined) {
		$("#principalamntid").text(convertNumberToIndianFormat($(event.relatedTarget).attr("data-principal")));
	}
	if ($(event.relatedTarget).attr("data-schemetype") != undefined) {
		let type = $(event.relatedTarget).attr("data-schemetype");
		if(type =='C'){
			$("#schemetypeid").text("Cumulative");
		}else if(type =='NC'){
			$("#schemetypeid").text("Non Cumulative");
		}else{
			$("#schemetypeid").text(type);
		}
	}
	if ($(event.relatedTarget).attr("data-tenure") != undefined) {
		$("#fdtenureid").text($(event.relatedTarget).attr("data-tenure") + " months");
	}
	if ($(event.relatedTarget).attr("data-intrate") != undefined) {
		$("#purchaserateid").text($(event.relatedTarget).attr("data-intrate"));
	}
	if ($(event.relatedTarget).attr("data-maturity") != undefined) {
		$("#maturityamountid").text(convertNumberToIndianFormat($(event.relatedTarget).attr("data-maturity")));
	}
	if ($(event.relatedTarget).attr("data-maturitydate") != undefined) {
		$("#maturitydate").text($(event.relatedTarget).attr("data-maturitydate"));
	}

});

