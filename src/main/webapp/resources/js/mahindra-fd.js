var district =[];
var pincodearr =[];
var statebasedData;
var statedatamap = new Map();
var tempstate;
var tempdist;
var index=0;
var foreigntaxcountry = new Map();

/*------------------------------------------------------------------------------------------------------------*/

$(document).on("click", "#radioamount", function() {
	var x = $("input[type='radio'][name='options']:checked").val();
	console.log("Select: " + $("input[type='radio'][name='options']:checked").val()); 
	$("#saveamount").val(x);
	$("input[type='radio'][name='options']:checked").css("background","red");
});



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
//	console.log(this.files[0].size/1048576 + "Mb");
});

document.querySelector('#panproofid').addEventListener('change',function(e){
	var fileName = document.getElementById("panproofid").files[0].name;
	var nextSibling = e.target.nextElementSibling;
	nextSibling.innerText = fileName;
	if(this.files[0].size/1024 > 1000){
		$("#panmsg").text("File size exceeded limit!");
	}
//	console.log(this.files[0].size/1024 + "Kb");
});

document.querySelector('#addproofid').addEventListener('change',function(e){
	var fileName = document.getElementById("addproofid").files[0].name;
	var nextSibling = e.target.nextElementSibling;
	nextSibling.innerText = fileName;
	if(this.files[0].size/1024 > 1000){
		$("#addmsg").text("File size exceeded limit!");
	}
//	console.log(this.files[0].size/1024 + "Kb");
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


$( "#nomineeid" ).change(function() {
//	Choose District based on state-
	console.log("Get Nominee... ");

//	let nominee = document.forms["fdpurchaseform"]["nomineechosen"].value;
	console.log("Chosen Nominee- "+ $('#nomineeid').is(":checked"));
	if($('#nomineeid').is(":checked")){
		console.log("TRUE DISPLAY")
		$("#nomineeDetailsBlock").addClass( "animated fadeIn" );
		$("#nomineeDetailsBlock").show();

	}else{
		$("#nomineeDetailsBlock").removeClass("animated fadeIn");
		$("#nomineeDetailsBlock").hide().prop('required',false);
	}
});

$( ".stateselect").change(function() {
//	Choose District based on state-
	var elementid=$(this).attr('id');
	var distOption;
	console.log("Get Districts... for element id- "+ elementid);
	let state="";
	let districtfieldid='';
	if(elementid === 'addressstateid'){
		state = document.forms["fdpurchaseform"]["addressstate1"].value;
		districtfieldid ='addressDistrict1id';
	}else if(elementid === 'nomineestatecodeid'){
		state = document.forms["fdpurchaseform"]["nomineestatecodeid"].value;
		districtfieldid='nomineedistrictid';
	}else{
		state='NA';
		districtfieldid='NA';
		elementid='NA';
	}


	if(elementid!='NA')
	{

		console.log("Chosen State. Check from temp map first- "+ state);

		var jsonObjects = {"state":state};

		if(statedatamap.has(state)){
			console.log("Data already fetched and stored in MAP. populating from here...");
			statebasedData = statedatamap.get(state);
			getdistricts(state,statebasedData,districtfieldid);

		}else{
			console.log("Making new API call...")
			var request;
			request = $.ajax({
				url: "/products/api/fixed-deposit/mmfd/getDistricts",
				method: "POST",
				contentType: "application/json",
				data: JSON.stringify(jsonObjects),
				async: true,
				datatype: "json",
				beforeSend: function() {
//					disableButon();
					district=[];
					distOption = document.getElementById(districtfieldid);
					$("#"+districtfieldid).empty();
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
//				console.log("Result- "+ data);
				statebasedData = JSON.parse(data);

				getdistricts(state,statebasedData,districtfieldid);

			});

			request.fail(function(xhr, textStatus) {
				alert("Request failed: " + xhr.getResponseHeader('APICALLSTATUS'));

//				location.reload();
			});

			request.always(function(msg){
				/*console.log("Final dstatemap- key");
			for (let entry of statedatamap) { 
				  console.log(entry);
				}*/

			});
		}
	}else{
		console.log("Invalid field");
	}

});


function getdistricts(state,resultdata,districtfieldid){
	console.log("Total district received- "+ statebasedData.length)
	statedatamap.set(state,statebasedData);
	for(var i = 0; i < statebasedData.length; i++) {
		obj = statebasedData[i];
//		console.log(obj.district);
		if( district.indexOf(obj.district) == -1 ) {
			district.push(obj.district);
		}else{
//			console.log("Data already present- "+ obj.district);
		}

	}
	console.log("Final District- "+ district);
//	Push the valus to Dropdown
	var distOption = document.getElementById(districtfieldid);
	$("#"+districtfieldid).empty();

	var option = document.createElement("option");
	option.text ="Select";
	option.value = "";
	distOption.add(option);

	for(i=0;i<district.length ; i++){
//		console.log(dtarray[i]);
		var option = document.createElement("option");
		option.text = district[i];
		option.value = district[i];
		distOption.add(option);
	}
	distOption.selectedIndex = 0;
}




$( ".pincodeid" ).change(function() {
	var elementid=$(this).attr('id');

	console.log("Change triggered by element- "+elementid )
	let districtval='';
	let pincode='';
	let elementstatecode='';
	if(elementid == 'addresspincode1id'){
		districtval = document.forms["fdpurchaseform"]["addressDistrict1"].value;
		pincode = document.forms["fdpurchaseform"]["addresspincode1"].value;
		elementstatecode = document.forms["fdpurchaseform"]["addressstateid"].value;

	}else if(elementid == 'nomineecitypincodeid'){
		districtval = document.forms["fdpurchaseform"]["nomineedistrictid"].value;
		pincode = document.forms["fdpurchaseform"]["nomineecitypincodeid"].value;
		elementstatecode = document.forms["fdpurchaseform"]["nomineestatecodeid"].value;
	}else{
		districtval='NA';
		pincode='NA';
		elementstatecode='NA';
	}
	console.log("Validate PINCODE for district- "+ elementstatecode+ " -->"+  districtval + " : "+ tempdist);

	if(pincode.length==6){
		console.log("Validate PINCODE- "+ pincode);
		let obj;
//		Populate PINCODE of district

		console.log("Get data from state map-");
		statebasedData=statedatamap.get(elementstatecode);

		if(statebasedData!=undefined){



			if(tempdist != districtval ){
				console.log("Temp district is different.. Reselect District pincode")
				pincodearr=[];
				tempdist = districtval;

				for(var i = 0; i < statebasedData.length; i++) {
					obj = statebasedData[i];
//					console.log(obj.district);
					if(obj.district == districtval){

						if( pincodearr.indexOf(obj.pincode) == -1 ) {
							pincodearr.push(Number(obj.pincode));
						}else{
							console.log("PINCODE already present- "+ obj.pincode);
						}
					}
//					district.push(obj.district);
				}

			}
			console.log("Final pincode- "+ pincodearr);

//			if( pincodearr.indexOf(pincode) == -1 ) {
			if( pincodearr.includes(Number(pincode)) ) {
//				console.log("VALID PINCODE");
				if(elementid == 'addresspincode1id'){
					$("#pancodevalidity").text("");
				}else if(elementid == 'nomineecitypincodeid'){
					$("#nomineepancodevalidity").text("");
				}
			}else{
				if(elementid == 'addresspincode1id'){
					$("#pancodevalidity").text("Invalid pincode");
				}else if(elementid == 'nomineecitypincodeid'){

					$("#nomineepancodevalidity").text("Invalid pincode");
				}

			}


		}else{
//			console.log("Select District First")
			if(elementid == 'addresspincode1id'){
				$("#pancodevalidity").text("Select State and District first.");
			}else if(elementid == 'nomineecitypincodeid'){
				$("#nomineepancodevalidity").text("Select State and District first.");
			}
		}

	}else{
		console.log(pincode);
	}

});


$("#addressproofTypeid").change(function() {

	var addressprooftype= document.forms["fdpurchaseform"]["addressproofType"].value;
	console.log(addressprooftype);
	if(addressprooftype == 'D' || addressprooftype == 'E'){
		console.log("Show box expirydatebox");
		$("#expirydatebox").show();
		$("#addressproofpxpirydateid").attr("required", "true");
	}else{
		console.log("hide box expirydatebox");
		$("#addressproofpxpirydateid").attr("required", "false");
		$("#expirydatebox").hide();
	}
});


/*$( "#addressproofTypeid" ).change(function() {
	let addressprroftype = document.forms["fdpurchaseform"]["addressproofType"].value;
	if(addressprroftype === '03'){
		$("#expirydatebox").show();
	}else{
		$("#expirydatebox").hide().prop('required',false);
	}
});*/


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
				$("#micrcodeid").text(obj.micrcode);

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
		$( ".taxResidentOutsideIndia" ).removeClass( "animated fadeIn" );
		$(".taxResidentOutsideIndia").hide().prop('required',false);
	}

});




function validateTab1(){

}


function showprogress(){
	$("#errormsgbox").text("");
	$("#progressdisplay").html("<div style=\"text-align: center;margin-bottom: 3rem;\"><h3>Processing your request. Please do not press 'Back' Button or refresh the page.</h3><img alt=\"Fetching your portfolio\" src=\"https://resources.freemi.in/products/resources/images/invest/progress2.gif\">");
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
//	$("#occupationdisplay").text(formdata.occupation.value);
	$("#occupationdisplay").text($("#occupationid :selected").text());

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
	$("#nomineechosendisplay").text(formdata.nomineechosen.value);
	$("#taxresidentoutsideIndia").text(formdata.taxResidentOtherCountry.value);
}


function insertforeigntaxDetails(formdata){

	var foreigntaxform = document.forms.foreigntaxinfoform;

	/*var e = document.getElementById("taxCountry");
	var strUser = e.options[e.selectedIndex].text;
	console.log("Request for- "+ strUser + " --> "+ $("#taxCountry :selected").text());*/

	if(foreigntaxcountry.has(foreigntaxform.taxCountry.value)){
		console.log("This country details are already present..");
		$("#taxdetailserrormsg").text("Selected country details are already present!");
		return false;
	}

	var table = document.getElementById("fdforeigntaxdetailsbody");
//	table.setAttribute("class","animated fadeIn");
	var datarow = table.insertRow();


	for(var loop=0;loop<=99;loop++){
		if(!document.getElementById("foreignTaxDetails["+loop +"].taxCountry")){
			console.log("index is available....")

//			datarow.insertCell(0).innerHTML="<td><input type='hidden' id='foreignTaxDetails["+(loop) +"].taxCountry' name='foreignTaxDetails["+(loop) +"].taxCountry' value='"+foreigntaxform.taxCountry.val+"'/> <input class='form-control form-control-sm' disabled='true' value='"+$('#taxCountry :selected').text()+"' /></td>";
			datarow.insertCell(0).innerHTML="<td><input class='form-control form-control-sm' readonly='readonly' id='foreignTaxDetails["+(loop) +"].taxCountry' name='foreignTaxDetails["+(loop) +"].taxCountry' value='"+$('#taxCountry :selected').text()+"'/></td>";
			datarow.insertCell(1).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].taxidentificationtype' name='foreignTaxDetails["+(loop) +"].taxidentificationtype' value='"+foreigntaxform.taxIdentype.value+"'/></td>";
			datarow.insertCell(2).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].taxidentificationno' name='foreignTaxDetails["+(loop) +"].taxidentificationno' value='"+foreigntaxform.taxIdenNumber.value+"'/></td>";
			datarow.insertCell(3).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].trcexpirydate' name='foreignTaxDetails["+(loop) +"].trcexpirydate' value='"+foreigntaxform.taxtrcExpiryDate.value+"'/></td>";
			datarow.insertCell(4).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].taxaddresstype' name='foreignTaxDetails["+(loop) +"].taxaddresstype' value='"+foreigntaxform.taxaddressType.value+"'/></td>";
			datarow.insertCell(5).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].ftaxaddress1' name='foreignTaxDetails["+(loop) +"].ftaxaddress1' value='"+foreigntaxform.taxAdd1.value+"'/></td>";

			datarow.insertCell(6).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].ftaxaddress2' name='foreignTaxDetails["+(loop) +"].ftaxaddress2' value='"+foreigntaxform.taxAdd2.value+"'/></td>";
			datarow.insertCell(7).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].ftaxcity' name='foreignTaxDetails["+(loop) +"].ftaxcity' value='"+foreigntaxform.taxCity.value+"'/></td>";
			datarow.insertCell(8).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].ftaxstate' name='foreignTaxDetails["+(loop) +"].ftaxstate' value='"+foreigntaxform.taxState.value+"'/></td>";
			datarow.insertCell(9).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].ftaxpostalcode' name='foreignTaxDetails["+(loop) +"].ftaxpostalcode' value='"+foreigntaxform.taxPostalCode.value+"'/></td>";
			datarow.insertCell(10).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].ftaxlandmark' name='foreignTaxDetails["+(loop) +"].ftaxlandmark' value='"+foreigntaxform.taxLandmark.value+"'/></td>";
			datarow.insertCell(11).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].stdcodeprimary' name='foreignTaxDetails["+(loop) +"].stdcodeprimary' value='"+foreigntaxform.taxStdCodePr.value+"'/></td>";
			datarow.insertCell(12).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].primarytelno' name='foreignTaxDetails["+(loop) +"].primarytelno' value='"+foreigntaxform.taxTelNoPr.value+"'/></td>";
			datarow.insertCell(13).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].ftaxmobileno' name='foreignTaxDetails["+(loop) +"].ftaxmobileno' value='"+foreigntaxform.taxMobilePr.value+"'/></td>";
			datarow.insertCell(14).innerHTML="<td><input class='form-control form-control-sm' id='foreignTaxDetails["+(loop) +"].ftaxstdother' name='foreignTaxDetails["+(loop) +"].ftaxstdother' value='"+foreigntaxform.taxStdOther.value+"'/></td>";
			datarow.insertCell(15).innerHTML="<td style='text-align: center;'> <i class='fas fa-minus-circle' style='color: red;cursor: pointer;' onclick='deleteRow(this)'></i></td>";
			foreigntaxcountry.set(foreigntaxform.taxCountry.value);
			index=loop;
			$("#taxResidentModal").on('hide.bs.modal', function(){});
			break;
		}else{
			console.log("Increment the ID counter...");
		}
	}

	index+=1;

}


function deleteRow(r) {
	var i = r.parentNode.parentNode.rowIndex;
	document.getElementById("mfprofiledata2").deleteRow(i);
}

function resetform(formelementid){
	document.getElementById(formelementid).reset();
}


$( "#confirmaccountNumberid" ).blur(function() {
	console.log("Validate acc");
	if($("#confirmaccountNumberid").val() != $("#accountNumberid").val()){
		$("#accountvalidationmsg").text("Account details mismatch");
		$("#accountvalidationmsg").addClass("text-danger");
		$("#accountvalidationmsg").removeClass("text-success");
	}else{
		$("#accountvalidationmsg").text("Account details matched");
		$("#accountvalidationmsg").addClass("text-success");
		$("#accountvalidationmsg").removeClass("text-danger");
	}
});



function customamount() {
	if ($('input:radio[name="options"]:checked')) {
		console.log("Check active")
		$('#radioamount label').removeClass('active');
	}
}

function formsubmitvalidation(){
	let saveamount = $("#saveamount").val();
	console.log("VAlidaet - "+ saveamount);
	$("#jsmsg").html("");
	if(saveamount%1000 != 0){
		console.log("Invalid amount")
		$("#jsmsg").text("Amount must be multiple of 1,000");
		return false;
	}

	var panregex = new RegExp('^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}$');
	var pandata = $("#panid").val();
	if(!(panregex.test(pandata))){
		$("#jsmsg").text("Invalid PAN number format!");
		return false;
	}
	return true;
}