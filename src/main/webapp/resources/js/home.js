
$(document).ready(function(){
$("#campaignsignup").on("tap click",function(e){
	
	e.preventDefault();
	var x = "N";
	if(document.getElementById("agree").checked){
		x= "Y";
	}	
	
	$.get("/products/campaignsignup.do",
	{
		mobile: $("#signupmob").val(),
		location: $("#location").val(),
		email: $("#email").val(),
		agree: x
	},		
	function(result){
		$('#exampleModalCenter').modal('hide');
		
	});
});
});


$('#agree').change(function(){
    if($(this).attr('checked')){
         $(this).val('Y');
         console.log("True");
    }else{
         $(this).val('N');
         console.log("False");
    }
});


function formValidation(){
//	console.log("mobile validation;");
	var digitregexp = /^[6-9][0-9]{9}$/;
	var mobile = document.getElementById("signupmob").value;
	var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var email = document.getElementById("email").value;
	var mobileflag, emailflag;
	
	if((mobile == "" || !digitregexp.test(mobile))){
		document.getElementById("signupmob").style.borderLeft = '2px solid red';
		mobileflag=false;
	}else{
		document.getElementById("signupmob").style.borderLeft = '2px solid green';
//		document.getElementById("campaignsignup").disabled = false;
		mobileflag=true;
	}
	
	if(email == "" || !emailregexp.test(email)){
		document.getElementById("email").style.borderLeft = '2px solid red';
		emailflag=false;
	}else{
		document.getElementById("email").style.borderLeft = '2px solid green';
		emailflag=true;
	}
	
	if(mobileflag && emailflag){
		document.getElementById("campaignsignup").disabled = false;
	}else{
		document.getElementById("campaignsignup").disabled = true;
	}
	
	
}


function formEmailValidation(){
//	console.log("mobile validation;");
	var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var email = document.getElementById("email").value;
	
	if(email == "" || !emailregexp.test(email)){
		document.getElementById("email").style.borderLeft = '2px solid red';
	}else{
		document.getElementById("email").style.borderLeft = '2px solid green';
		document.getElementById("campaignsignup").disabled = false;
	}
	
}


/*$(document).ready(function(){
	$("#campaignsignup").click(function(){
		var url = "/products/campaignsignup.do";
		var arr = {mobile: $("#signupmob").val(), location: $("#location").val()}
		$.ajax({
			  type: "POST",
			  url: url,
			  data: arr,
			  dataType: 'json'
			});
	});
	});*/