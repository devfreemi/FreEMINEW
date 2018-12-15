function openRedirectDisclaimer(){
	
}

function formOnLoad(){
	var mobile = document.forms["fsecureForm"]["mobile"].value;
	var fname = document.forms["fsecureForm"]["fname"].value;
	var lname = document.forms["fsecureForm"]["lname"].value;
	var email = document.forms["fsecureForm"]["email"].value;
	var dob = document.forms["fsecureForm"]["dob"].value;
	
	if(fname == ""){
		document.getElementById("fname").style.borderLeft = '2px solid red';
	}else{
		document.getElementById("fname").style.borderLeft = '2px solid green';
	}
	if(lname == ""){
		document.getElementById("lname").style.borderLeft = '2px solid red';
	}else{
		document.getElementById("lname").style.borderLeft = '2px solid green';
	}
	if(email == ""){
		document.getElementById("email").style.borderLeft = '2px solid red';
	}else{
		document.getElementById("email").style.borderLeft = '2px solid green';
	}
	if(mobile == ""){
		document.getElementById("mobile").style.borderLeft = '2px solid red';
	}else{
		document.getElementById("mobile").style.borderLeft = '2px solid green';
	}
	if(dob == ""){
		document.getElementById("dob").style.borderLeft = '3px solid red';
	}else{
		document.getElementById("dob").style.borderLeft = '3px solid green';
	}
	
	document.getElementById("fsecureSubmit").disabled = true;
}

function validateForm(){
//	console.log("Validate form");
	
	var mobile = document.forms["fsecureForm"]["mobile"].value;
	var fname = document.forms["fsecureForm"]["fname"].value;
	var lname = document.forms["fsecureForm"]["lname"].value;
	var email = document.forms["fsecureForm"]["email"].value;
	var dob = document.forms["fsecureForm"]["dob"].value;
	var allowcalls = document.forms["fsecureForm"]["allowcalls"].value;
//	console.log(allowcalls);
	var letterregexp = /^[a-zA-Z]+$/;
	var digitregexp = /^[0-9]{10}$/;
	var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var today = (new Date()).getTime();
	var dobTime = Date.parse(dob);
	var dateDifference = (((today - dobTime)/(1000*60*60*24))/365);
//	console.log("Date diff- "+ dateDifference);
	var formInvalid= true;
	
	var cond1 = false, cond2 = false,cond3 = false,cond4 = false,cond5 = false,cond6 = false,cond7 = false;
	
 	if(fname == "" || !letterregexp.test(fname)){
		document.getElementById("fname").style.borderLeft = '2px solid red';
	}else{
		cond1 = true;
		document.getElementById("fname").style.borderLeft = '2px solid green';
	}
	if(lname == "" || !letterregexp.test(lname)){
		document.getElementById("lname").style.borderLeft = '2px solid red';
	}else{
		cond2= true;
		document.getElementById("lname").style.borderLeft = '2px solid green';
	}
	if(email == "" || !emailregexp.test(email)){
		document.getElementById("email").style.borderLeft = '2px solid red';
	}else{
		cond3= true;
		document.getElementById("email").style.borderLeft = '2px solid green';
	}
	if(mobile == "" || !digitregexp.test(mobile)){
		document.getElementById("mobile").style.borderLeft = '2px solid red';
	}else{
		cond4= true;
		document.getElementById("mobile").style.borderLeft = '2px solid green';
	}
	if(dob == ""){
		document.getElementById("dob").style.borderLeft = '3px solid red';
	}else if(dateDifference> 45 || dateDifference<18){
		
		document.getElementById("dob").style.borderLeft = '3px solid red';
		document.getElementById("doberror").innerHTML="Age must be between 18 to 45 yrs";
		document.getElementById("doberror").style.color="red";
		document.getElementById("doberror").style.fontSize="12px";
	}
	else{
		cond5= true;
		document.getElementById("dob").style.borderLeft = '3px solid green';
		document.getElementById("doberror").innerHTML="";
	}
	
	if(cond1 && cond2 && cond3 && cond4 && cond5){
//		console.log('true');
		document.getElementById("fsecureSubmit").disabled = false;
	}else{
//		console.log('false');
		document.getElementById("fsecureSubmit").disabled = true;
	}
	
}

function validateSubmit(){
	
	
}
