var district =[];
var pincodearr =[];
var statebasedData;
var tempstate;
var tempdist;

/*------------------------------------------------------------------------------------------------------------*/




/*------------------------------------------------------------------------------------------------------------*/
function readURL(input,elementid) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#'+elementid)
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };

        reader.readAsDataURL(input.files[0]);
    }
}


document.querySelector('#photoid').addEventListener('change',function(e){
	  var fileName = document.getElementById("photoid").files[0].name;
	  var nextSibling = e.target.nextElementSibling;
	  nextSibling.innerText = fileName;
	  if(this.files[0].size/1024 > 1000){
		  $("#photomsg").text("File size exceeded limit!");
	  }
//	  console.log(this.files[0].size/1048576 + "Mb");
	});

document.querySelector('#panproofid').addEventListener('change',function(e){
	  var fileName = document.getElementById("panproofid").files[0].name;
	  var nextSibling = e.target.nextElementSibling;
	  nextSibling.innerText = fileName;
	  if(this.files[0].size/1024 > 1000){
		  $("#panmsg").text("File size exceeded limit!");
	  }
//	  console.log(this.files[0].size/1024 + "Kb");
	});

document.querySelector('#addproofid').addEventListener('change',function(e){
	  var fileName = document.getElementById("addproofid").files[0].name;
	  var nextSibling = e.target.nextElementSibling;
	  nextSibling.innerText = fileName;
	  if(this.files[0].size/1024 > 1000){
		  $("#addmsg").text("File size exceeded limit!");
	  }
//	  console.log(this.files[0].size/1024 + "Kb");
	});
/*------------------------------------------------------------------------------------------------------------*/


/*------------------------------------------------------------------------------------------------------------*/


function fdsaveForm(category){

	document.forms["fdform"]["category"].value = category;
	$("#mahindrafdModal").modal();

	if(category == 'PU'){
		$("#category").text("PUBLIC");
	}
	else if(category == 'SR'){
		$("#category").text("SENIOR CITIZEN");
	}else{
		$("#category").text("NA");
	}


	/*	rbtn = document.getElementById(category);
	rbtn.setAttribute("checked", "checked");
	rbtn.parentNode.classList.add("active");*/

}

function openTaxResidentBox(){
	$('#taxResidentModal').modal('show');
}


//Trigger when choosing tenure
$( "#radioamount,#frequencyid" ).change(function() {
//	e.preventDefault();
	let rate ="NA";
//	console.log("Selected Tenure- "+ document.forms["fdpurchaseform"]["saveTenure"].value + " --> " + document.forms["fdpurchaseform"]["intFreq"].value);
//	console.log("Tenure interest rate- "+ tenureInt.get(Number(document.forms["fdpurchaseform"]["saveTenure"].value)));
	//$("#interestrateview").text(tenureInt.get(document.forms["fdpurchaseform"]["schemeCode"].value)+"%");
	for(var i = 0; i < schemeList.length; i++) {
		var obj = schemeList[i];
//		console.log(obj.tenuremonthto + " --> "+obj.interestfrequency );
		if(obj.tenuremonthto == document.forms["fdpurchaseform"]["saveTenure"].value && obj.interestfrequency == document.forms["fdpurchaseform"]["intFreq"].value) {
//			console.log("Int- "+ obj.interestRate);
			rate=obj.interestRate;
			document.forms["fdpurchaseform"]["schemeCode"].value=obj.schemeCode;
			document.forms["fdpurchaseform"]["interestRate"].value=rate;
			console.log("Scheme code: "+ document.forms["fdpurchaseform"]["schemeCode"].value);
			break;
		}
//		console.log("Done finding...")
	}
//	console.log("Interest chosen...");

	$("#interestrateview").text(rate=='NA'?'NA':obj.interestRate +"%");

});


$( "#addressstateid" ).change(function() {
//	Choose District based on state-
	console.log("Get Districts... ");

	let state = document.forms["fdpurchaseform"]["addressstate1"].value;
	console.log("Chosen State- "+ state);
	var jsonObjects = {"state":state};

	var request;
	request = $.ajax({
		url: "/products/api/fixed-deposit/mmfd/getDistricts",
		method: "POST",
		contentType: "application/json",
		data: JSON.stringify(jsonObjects),
		async: true,
		datatype: "json",
		beforeSend: function() {
//			disableButon();
			district=[];
			var distOption = document.getElementById("addressDistrict1id");
			$("#addressDistrict1id").empty();
			var option = document.createElement("option");
			option.text ="Fetching Data...";
			option.value = "";
			distOption.add(option);
			distOption.selectedIndex = 0;
		}
	});

	request.done(function(data, textStatus, xhr) {

		console.log("Existing- "+ district);
		district=[];
		var obj; 
		console.log("Get status from header- "+ xhr.getResponseHeader('APICALLSTATUS')); 
//		console.log("Result- "+ data);
		statebasedData = JSON.parse(data);
		for(var i = 0; i < statebasedData.length; i++) {
			obj = statebasedData[i];
//			console.log(obj.district);
			if( district.indexOf(obj.district) == -1 ) {
				district.push(obj.district);
			}else{
//				console.log("Data already present- "+ obj.district);
			}

		}
		console.log("Final District- "+ district);
//		Push the valus to Dropdown
		var distOption = document.getElementById("addressDistrict1id");
		$("#addressDistrict1id").empty();
		
		var option = document.createElement("option");
		option.text ="Select";
		option.value = "";
		distOption.add(option);

		for(i=0;i<district.length ; i++){
//			console.log(dtarray[i]);
			var option = document.createElement("option");
			option.text = district[i];
			option.value = district[i];
			distOption.add(option);
		}
		distOption.selectedIndex = 0;

	});

	request.fail(function(xhr, textStatus) {
		alert("Request failed: " + xhr.getResponseHeader('APICALLSTATUS'));

//		location.reload();
	});

	request.always(function(msg){
//		console.log("first step request done- "+msg);
		/*$("#loginspin").hide();
		$("#loginbasic").show();
		$("#loginsubmit").prop("disabled", false);*/
	});

});



$( "#addresspincode1id" ).change(function() {

	let districtval = document.forms["fdpurchaseform"]["addressDistrict1"].value;
	let pincode = document.forms["fdpurchaseform"]["addresspincode1"].value;

	console.log("Validate PINCODE for district- "+ districtval + " : "+ tempdist);

	if(pincode.length==6){
		console.log("Validate PINCODE- "+ pincode);
		let obj;
//		Populate PINCODE of district
		if(statebasedData!=undefined){
		
		if(tempdist != districtval ){
			console.log("Temp district is different.. Reselect District pincode")
			pincodearr=[];
			tempdist = districtval;

			for(var i = 0; i < statebasedData.length; i++) {
				obj = statebasedData[i];
//				console.log(obj.district);
				if(obj.district == districtval){

					if( pincodearr.indexOf(obj.pincode) == -1 ) {
						pincodearr.push(Number(obj.pincode));
					}else{
						console.log("PINCODE already present- "+ obj.pincode);
					}
				}
//				district.push(obj.district);
			}

		}
		console.log("Final pincode- "+ pincodearr);

//		if( pincodearr.indexOf(pincode) == -1 ) {
		if( pincodearr.includes(Number(pincode)) ) {
//			console.log("VALID PINCODE");
			$("#pancodevalidity").text("");
		}else{
			$("#pancodevalidity").text("Invalid pincode");
		}
		
		
		}else{
			$("#pancodevalidity").text("Select State and District first.");
			console.log("Select District First")
		}

	}else{
		console.log(pincode);
	}

});


$( "#ifscid" ).change(function() {
	
	console.log("Get IFSC code data..")
	var digitregexp = /^[a-zA-Z]{4}[0]{1}[a-zA-Z0-9]{6}$/;
	var ifsccode= document.forms["fdpurchaseform"]["ifscid"].value;
	console.log(ifsccode);
	
	
	if(ifsccode.length == 11 && digitregexp.test(ifsccode)){

		var jsonObjects = {"ifsccode":ifsccode};
		var request;
		request = $.ajax({
			url: "/products/api/fixed-deposit/mmfd/getBankDeailsFromIfsc",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify(jsonObjects),
			async: true,
			datatype: "json",
			beforeSend: function() {
//				disableButon();
				$("#banknameid").html("<i class=\"fas fa-spinner fa-spin\"></i>");
				$("#bankbranchid").html("<i class=\"fas fa-spinner fa-spin\"></i>");
			}
		});

		request.done(function(data, textStatus, xhr) {
			console.log("Get status from header- "+ xhr.getResponseHeader('APICALLSTATUS')); 
			if(xhr.getResponseHeader('APICALLSTATUS') == 'S'){
				console.log(data);
				var obj=JSON.parse(data);
				document.forms["fdpurchaseform"]["micrCode"].value=obj.micrcode;
				document.forms["fdpurchaseform"]["bankname"].value=obj.bankname;
				document.forms["fdpurchaseform"]["bankbranch"].value=obj.bankbranch;
				
				$("#banknameid").text(obj.bankname);
				$("#bankbranchid").text(obj.bankbranch);
				/*$("#").text();*/
			}else{
				console.log("Failed to fetch IFSC Data.")
			}
				

		});

		request.fail(function(xhr, textStatus) {
			alert("Request failed: " + xhr.getResponseHeader('APICALLSTATUS'));

//			location.reload();
		});

		request.always(function(msg){
//			console.log("first step request done- "+msg);
			/*$("#loginspin").hide();
		$("#loginbasic").show();
		$("#loginsubmit").prop("disabled", false);*/
		});

	}else{
		console.log("IFSC Code pattern do not match with length- "+ ifsccode.length);
	}
});

$( "#taxResOtherThanIndiaid" ).change(function() {
	
	let taxresidentOtherCountry = document.forms["fdpurchaseform"]["taxResidentOtherCountry"].value;
	console.log(taxresidentOtherCountry);
	if(taxresidentOtherCountry == 'YES'){
		$( ".taxResidentOutsideIndia" ).addClass( "animated fadeIn" );
		$(".taxResidentOutsideIndia").show();
	}else{
		$(".taxResidentOutsideIndia").hide();
	}

});


function validateTab1(){
	
}


function showprogress(){
	$("#errormsgbox").text("");
	$("#progressdisplay").html("<div style=\"text-align: center;margin-bottom: 3rem;\"><img alt=\"Fetching your portfolio\" src=\"https://resources.freemi.in/products/resources/images/invest/progress2.gif\">");
	$("#prevBtn").attr("disabled", "disabled");
	$("#nextBtn").attr("disabled", "disabled");
}


function populatesummary(){
	var formdata = document.forms.fdpurchaseform;
	$("#fullnamedisplay").text(formdata.primaryHolderTitle.value + " "+formdata.firstName.value + " "+formdata.middleName.value + " "+formdata.lastName.value);
	$("#mobiledisplay").text(formdata.mobile.value);
	$("#pandisplay").text(formdata.pan.value);
	$("#emaildisplay").text(formdata.email.value);
	$("#genderdisplay").text(formdata.gender.value);
	$("#occupationdisplay").text(formdata.occupation.value);
	$("#genderdisplay").text(formdata.gender.value);
	$("#occupationdisplay").text(formdata.occupation.value);
	$("#birthplacedisplay").text(formdata.cityOfBirth.value);
	$("#ckycdisplay").text(formdata.ckyc.value);
	
	
	$("#amountdisplay").text(formdata.saveAmount.value);
	$("#tenuredisplay").text(formdata.saveTenure.value);
	$("#categorydisplay").text(formdata.category.value);
	$("#frequencydisplay").text(formdata.intFreq.value);
	$("#schemecodedisplay").text(formdata.schemeCode.value);
	$("#intratedisplay").text(formdata.interestRate.value);
	
	$("#addressdisplay").html(formdata.address1.value+"<br>"+formdata.address2_1.value+(formdata.address3_1.value!=""?("<br>"+formdata.address3_1.value):"")+"<br><strong>City:</strong> "+formdata.addressCity1.value+"<br><strong>District:</strong> "+formdata.addressDistrict1.value+"<br><strong>State:</strong> "+formdata.addressstate1.value+"<br><strong>Pincode:</strong> "+ formdata.addresspincode1.value);
	$("#bankdetailsdisplay").html("<strong>A/C No:</strong> "+ formdata.accountNumber.value+"<br><strong>Bank:</strong> "+formdata.bankname.value+"<br><strong>Branch:</strong> "+formdata.bankbranch.value+"<br><strong>IFSC Code:</strong> "+formdata.ifscCode.value);
	
}

