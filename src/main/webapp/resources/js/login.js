function formOnLoad(){
	var digitregexp = /^[6-9]{1}[0-9]{9}$/;
	var mobile = document.forms["login"]["usermobile"].value;
	var flag1=false, flag2=false;
	if(digitregexp.test(mobile)){
		flag1=true;
	}
	if(flag1){
	document.getElementById("loginsubmit").disabled = false;
	}else{
		document.getElementById("loginsubmit").disabled = true;
	}
}

function validateForm(){
//	console.log("validate");
	var digitregexp = /^[6-9]{1}[0-9]{9}$/;
	var mobile = document.forms["login"]["usermobile"].value;
	var pass = document.forms["login"]["userpassword"].value;
	var flag1=false, flag2=false;
	
	if(digitregexp.test(mobile)){
		flag1=true;
	}
	if(pass.length >= 8){
		flag2=true;
//		console.log("more")
	}else{
//		console.log("less")
	}
	
	if(flag1 && flag2){
		document.getElementById("loginsubmit").disabled = false;
	}else{
		document.getElementById("loginsubmit").disabled = true;
	}
}