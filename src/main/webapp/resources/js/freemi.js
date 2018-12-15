
	var loanamount=96000;
	function formOnLoad() {
		document.getElementById("activeForm1").style.display = "block";
		document.getElementById("activeForm2").style.display = "none";
		document.getElementById("allowedloan").innerHTML ="96,000";
		document.getElementById("desc").innerHTML = "Fixed Credit Feature";
		document.getElementById("proceed").disabled = true;
	}
	function changeForm1to2()

	{

		document.getElementById("activeForm1").style.display = "none";
		document.getElementById("activeForm2").style.display = "block";

	}
	function changeForm2to1()

	{

		document.getElementById("activeForm2").style.display = "none";
		document.getElementById("activeForm1").style.display = "block";

	}

	function fixedCredit() {
		//console.log("Fixed");
		document.getElementById("change_img").src = "/products/resources/images/loan1.png";
		document.getElementById("allowedloan").innerHTML ="96,000";
		loanamount = 96000;
		document.getElementById("desc").innerHTML = "Fixed Credit Feature";

	}

	function flexibleCredit() {
		//console.log("Flexible");
		document.getElementById("change_img").src = "/products/resources/images/loan2.png";
		document.getElementById("allowedloan").innerHTML ="96,000";
		loanamount = 96000;
		document.getElementById("desc").innerHTML = "Flexible Credit Feature";
	}
	function noCostEmi() {
		//console.log("no cost");
		document.getElementById("change_img").src = "/products/resources/images/loan3.png";
		document.getElementById("allowedloan").innerHTML ="32,000";
		loanamount = 32000;
		document.getElementById("desc").innerHTML = "EMI @ No Cost";

	}

	function loanamountcheck() {
		var pattern = [0-9];
		var amount = document.getElementById("loanbox").value;
		//console.log("Check amount- " + loanamount + " --> " + amount);
		
		if(document.getElementById("loanbox").validity.patternMismatch){
			document.getElementById("amount_error").innerHTML = "*Invalid pattern";
		}
		else if(amount == ""){
			document.getElementById("proceed").disabled = true;
		}else if(amount < 2000){
			document.getElementById("amount_error").innerHTML = "";
			document.getElementById("proceed").disabled = true;
		}
		else if (amount > loanamount) {
			document.getElementById("amount_error").innerHTML = "*Maximum amount crossed";
			document.getElementById("proceed").disabled = true;
		} else {
			document.getElementById("amount_error").innerHTML = "";
			document.getElementById("proceed").disabled = false;
		}
	}
	
	function validateplForm(){
		var letterregexp = /^[a-z A-Z]+$/;
		var digitregexp = /^([6-9]{1})[0-9]{9}$/;
		var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		
		var cond1 = false, cond2 = false,cond3 = false,cond4 = false, flag=false;
		
		var username = document.forms["personalLoan"]["plusername"].value;
		var usermobile = document.forms["personalLoan"]["plusermobile"].value;
		var useremail = document.forms["personalLoan"]["pluseremail"].value;
		//var userlocation = document.forms["personalLoan"]["userlocation"].value;
		var plagree = document.getElementById("plagree").checked;
//		console.log(plagree);
//		console.log(username + " -> "+ usermobile + " -> "+ useremail);
		
		if(username == "" || !letterregexp.test(username)){
			document.getElementById("plusername").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond1 = true;
			document.getElementById("plusername").style.borderBottom = '1px solid #43e534';
		}
		
		if(usermobile == "" || !digitregexp.test(usermobile)){
			document.getElementById("plusermobile").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond2 = true;
			document.getElementById("plusermobile").style.borderBottom = '1px solid #43e534';
		}
		
//		if(useremail == "" || !emailregexp.test(useremail)){
		if(useremail != "" && !emailregexp.test(useremail)){
			document.getElementById("pluseremail").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond3 = true;
			document.getElementById("pluseremail").style.borderBottom = '1px solid #43e534';
		}
		if(plagree){
			cond4 = true;
		}
		
		
		if(cond1 && cond2 && cond4){
//		if(cond1 && cond2 && cond3 && cond4){
//			console.log('true');
			document.getElementById("plsubmit").disabled = false;
			flag=true;
		}else{
//			console.log('false');
			document.getElementById("plsubmit").disabled = true;
		}
		return flag;
		
	}
	
	
	function validatehlForm(){
		var letterregexp = /^[a-z A-Z]+$/;
		var digitregexp = /^([6-9]{1})[0-9]{9}$/;
		var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		
		var cond1 = false, cond2 = false,cond3 = false,cond4 = false, flag=false;
		
		var username = document.forms["homeLoan"]["hlusername"].value;
		var usermobile = document.forms["homeLoan"]["hlusermobile"].value;
		var useremail = document.forms["homeLoan"]["hluseremail"].value;
		//var userlocation = document.forms["personalLoan"]["userlocation"].value;
		var plagree = document.getElementById("hlagree").checked;
//		console.log(plagree);
//		console.log(username + " -> "+ usermobile + " -> "+ useremail);
		
		if(username == "" || !letterregexp.test(username)){
			document.getElementById("hlusername").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond1 = true;
			document.getElementById("hlusername").style.borderBottom = '1px solid #43e534';
		}
		
		if(usermobile == "" || !digitregexp.test(usermobile)){
			document.getElementById("hlusermobile").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond2 = true;
			document.getElementById("hlusermobile").style.borderBottom = '1px solid #43e534';
		}
		
//		if(useremail == "" || !emailregexp.test(useremail)){
		if(useremail != "" && !emailregexp.test(useremail)){
			document.getElementById("hluseremail").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond3 = true;
			document.getElementById("hluseremail").style.borderBottom = '1px solid #43e534';
		}
		if(plagree){
			cond4 = true;
		}
		
		
//		if(cond1 && cond2 && cond3 && cond4){
		if(cond1 && cond2 && cond4){
//			console.log('true');
			document.getElementById("hlsubmit").disabled = false;
			flag=true;
		}else{
//			console.log('false');
			document.getElementById("hlsubmit").disabled = true;
		}
		return flag;
		
	}
	
	
	function validateccForm(){
		var letterregexp = /^[a-z A-Z]+$/;
		var digitregexp = /^([6-9]{1})[0-9]{9}$/;
		var emailregexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		
		var cond1 = false, cond2 = false,cond3 = false,cond4 = false, flag=false;
		
		var username = document.forms["creditCard"]["ccusername"].value;
		var usermobile = document.forms["creditCard"]["ccusermobile"].value;
		var useremail = document.forms["creditCard"]["ccuseremail"].value;
		//var userlocation = document.forms["personalLoan"]["userlocation"].value;
		var plagree = document.getElementById("ccagree").checked;
//		console.log(plagree);
//		console.log(username + " -> "+ usermobile + " -> "+ useremail);
		
		if(username == "" || !letterregexp.test(username)){
			document.getElementById("ccusername").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond1 = true;
			document.getElementById("ccusername").style.borderBottom = '1px solid #43e534';
		}
		
		if(usermobile == "" || !digitregexp.test(usermobile)){
			document.getElementById("ccusermobile").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond2 = true;
			document.getElementById("ccusermobile").style.borderBottom = '1px solid #43e534';
		}
		
//		if(useremail == "" || !emailregexp.test(useremail)){
		if(useremail != "" && !emailregexp.test(useremail)){
			document.getElementById("ccuseremail").style.borderBottom = '1px solid #c62d2d';
		}else{
			cond3 = true;
			document.getElementById("ccuseremail").style.borderBottom = '1px solid #43e534';
		}
		if(plagree){
			cond4 = true;
		}
		
		
//		if(cond1 && cond2 && cond3 && cond4){
		if(cond1 && cond2 && cond4){
//			console.log('true');
			document.getElementById("ccsubmit").disabled = false;
			flag=true;
		}else{
//			console.log('false');
			document.getElementById("ccsubmit").disabled = true;
		}
		return flag;
		
	}
	
	
	