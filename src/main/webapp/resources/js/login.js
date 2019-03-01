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
		/*$("#validationCustomUsername").css({"border-bottom":"2px solid #3eb755;"});*/
		$("#mobico").css({"color":"#3eb755;"});
		$("#msg1").text("");
		
	}else{
//		console.log("Invalid mobile");
		/*$("#validationCustomUsername").css({"border-bottom":"2px solid #f7445e;"});*/
//		$("#mobico").css({"color":"#f7445e;"});

		document.getElementById("msg1").style.color="f7445e";
		/*$("#msg1").text("Invalid number format!");*/
		$("#msg1").css("color","#f7445e;");
		
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