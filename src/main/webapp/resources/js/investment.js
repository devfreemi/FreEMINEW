function AdditionalPurchase(folio,code,type){
	/*console.log(x);
	var encrypted = CryptoJS.AES.encrypt(x, "axsd");
	console.log(encrypted.toString());
	var decrypted = CryptoJS.AES.decrypt(encrypted, "axsd");
	console.log(decrypted.toString(CryptoJS.enc.Utf8));*/
//	var base64 = btoa(x);
//	console.log(base64);
//	console.log(window.location.href);
	window.location.assign(window.location.href+"/additional-purchase?p="+btoa(folio+"|"+code+"|"+type));
}

function MFRedeem(folio,code,type){
	/*console.log(x);
	var encrypted = CryptoJS.AES.encrypt(x, "axsd");
	console.log(encrypted.toString());
	var decrypted = CryptoJS.AES.decrypt(encrypted, "axsd");
	console.log(decrypted.toString(CryptoJS.enc.Utf8));*/
//	var base64 = btoa(x);
//	console.log(base64);
//	console.log(window.location.href);
	window.location.assign(window.location.href+"/funds-redeem?r="+btoa(folio+"|"+code+"|"+type));
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


