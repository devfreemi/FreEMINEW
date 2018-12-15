function formOnLoad(){
	var username = document.forms["registerForm"]["username"].value;
//	var fname = document.forms["registerForm"]["fname"].value;
	var registermobile = document.forms["registerForm"]["registermobile"].value;
	var useremail = document.forms["registerForm"]["useremail"].value;
	var password = document.forms["registerForm"]["registerpassword"].value;
	
	
	if(username == ""){
		document.getElementById("username").style.borderLeft = '2px solid red';
	}else{
		document.getElementById("username").style.borderLeft = '2px solid green';
	}
	if(useremail == ""){
		document.getElementById("useremail").style.borderLeft = '2px solid red';
	}else{
		document.getElementById("useremail").style.borderLeft = '2px solid green';
	}
	if(registermobile == ""){
		document.getElementById("registermobile").style.borderLeft = '2px solid red';
	}else{
		document.getElementById("registermobile").style.borderLeft = '2px solid green';
	}
	if(password == ""){
		document.getElementById("registerpassword").style.borderLeft = '3px solid red';
	}else{
		document.getElementById("registerpassword").style.borderLeft = '3px solid green';
	}
	
	document.getElementById("registerSubmit").disabled = true;
}


function validateForm(){
	
	var username = document.forms["registerForm"]["username"].value;
	var registermobile = document.forms["registerForm"]["registermobile"].value;
	var useremail = document.forms["registerForm"]["useremail"].value;
	var password = document.forms["registerForm"]["registerpassword"].value;
	
	var letterregexp = /^[a-z A-Z]+$/;
	var digitregexp = /^([6-9]{1})[0-9]{9}$/;
	var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
//	console.log("Date diff- "+ dateDifference);
	var formInvalid= true;
	
	var cond1 = false, cond2 = false,cond3 = false,cond4 = false;
	
 	if(username == "" || !letterregexp.test(username)){
		document.getElementById("username").style.borderLeft = '2px solid red';
	}else{
		cond1 = true;
		document.getElementById("username").style.borderLeft = '2px solid green';
	}
 	if(password == ""){
		document.getElementById("registerpassword").style.borderLeft = '2px solid red';
	}else{
		cond2= true;
		document.getElementById("registerpassword").style.borderLeft = '2px solid green';
	}
 	if(useremail == "" || !emailregexp.test(useremail)){
		document.getElementById("useremail").style.borderLeft = '2px solid red';
	}else{
		cond3= true;
		document.getElementById("useremail").style.borderLeft = '2px solid green';
	}
	if(registermobile == "" || !digitregexp.test(registermobile)){
		document.getElementById("registermobile").style.borderLeft = '2px solid red';
	}else{
		cond4= true;
		document.getElementById("registermobile").style.borderLeft = '2px solid green';
	}
	
	
	if(cond1 && cond2 && cond3 && cond4){
//		console.log('true');
		document.getElementById("registerSubmit").disabled = false;
	}else{
//		console.log('false');
		document.getElementById("registerSubmit").disabled = true;
	}
	
}