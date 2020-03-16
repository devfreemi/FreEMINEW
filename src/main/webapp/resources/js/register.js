$("#fnameid,#lnameid,#registermobile,#useremail,#registerpassword").keyup(function(){
	validateForm();
});

function formOnLoad(){
	validateForm();
}

function validateForm(){
//	var username = document.forms["registerForm"]["username"].value;
	var fname = document.forms["registerForm"]["fname"].value;
	var lname = document.forms["registerForm"]["lname"].value;
	var registermobile = document.forms["registerForm"]["registermobile"].value;
	var useremail = document.forms["registerForm"]["useremail"].value;
	var password = document.forms["registerForm"]["registerpassword"].value;
	
	var mobdes = document.getElementById("mobico");
	var namedes = document.getElementById("userico");
	var emaildes = document.getElementById("mailico");
	var passdes = document.getElementById("passico");
	
	var letterregexp = /^[a-z A-Z]+$/;
	var digitregexp = /^([6-9]{1})[0-9]{9}$/;
	var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	
	var formInvalid= true;
	
	var cond1 = false, cond2 = false,cond3 = false,cond4 = false, cond5=false;
	
 	if((fname == "" || !letterregexp.test(fname)) || (lname == "" || !letterregexp.test(lname))){
 		namedes.style.color="#e83a3a";
	}else{
		cond1 = true;
		namedes.style.color="rgb(60, 177, 59)";
	}
 	
 	if( password == undefined || password == "" || password.length < 8){
 		passdes.style.color="#e83a3a";
	}else{
		cond2= true;
		passdes.style.color="rgb(60, 177, 59)";
	}
 	
 	if(useremail == "" || !emailregexp.test(useremail)){
 		emaildes.style.color="#e83a3a";
	}else{
		cond3= true;
		emaildes.style.color="rgb(60, 177, 59)";
	}
 	
	if(registermobile == "" || !digitregexp.test(registermobile)){
		mobdes.style.color="#e83a3a";
	}else{
		cond4= true;
		mobdes.style.color="rgb(60, 177, 59)";
	}
	
	if(cond1 && cond2 && cond3 && cond4){
		document.getElementById("registerSubmit").disabled = false;
	}else{
		document.getElementById("registerSubmit").disabled = true;
	}
	
}

var input = document.getElementById("registerpassword");
input.addEventListener("keyup", function(event) {

	  // If "caps lock" is pressed, display the warning text
	  if (event.getModifierState("CapsLock")) {
	    $("#msg2").html("Caps lock ON!");
	  } else {
		  $("#msg2").html("");
	  }
	});
