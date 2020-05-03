function initalbox(){
	var investtype = document.getElementById("investmenttype").value;
	console.log( investtype )
//	if( investtype == 'SIP' ) {
//		console.log('SIp matched');
		//document.getElementById("amountbox").style.display = "flex";
	//	document.getElementById("sipbox").style.display = "none";
	//}else 
		if(investtype == 'TARGET_PLAN') {
		console.log('TARGERT MATCH');
		document.getElementById("amountbox").style.display = "none";
//		document.getElementById("sipbox").style.display = "flex";
	}else {
//		console.log('Nothing matched');
		document.getElementById("amountbox").style.display = "flex";
//		document.getElementById("sipbox").style.display = "none";
	}
}

function investmenttypeBox(){
//	console.log("Called");
	var investtype = document.getElementById("investmenttype").value;
//	console.log( investtype )
	if( investtype == 'SIPT' ) {
//		console.log('SIp matched');
//		document.getElementById("amountbox").style.display = "flex";
		document.getElementById("sipt").style.display = "block";
		document.getElementById("sipm").style.display = "none";
		document.getElementById("inputGroupSelect01").value=13;
	}if(investtype == 'SIPM') {
		document.getElementById("sipt").style.display = "none";
		document.getElementById("sipm").style.display = "block";
		document.getElementById("inputGroupSelect01").value=12;
	}
	else {
		document.getElementById("amountbox").style.display = "flex";
//		document.getElementById("sipbox").style.display = "none";
		document.getElementById("inputGroupSelect01").value=13;
	}
}
function getDate(){
	var eventdate = new Date(document.getElementById('eventdate').value);
	var enteredYear= eventdate.getFullYear();
//	console.log("Entered year - "+enteredYear );
	var investmenttype = document.getElementById('investmenttype').value;
	var p = document.getElementById('amount_sip').value;
	
	
	eventdate = eventdate.toDateString("yyyy-mm-dd");
	var today = new Date().toDateString("yyyy-mm-dd");
	var d= new Date();
	var currentYear= d.getFullYear();
//	console.log("  --> current year: "+ currentYear);
	
	
	
	if(enteredYear>=currentYear){
	var day=daysBetween(new Date(today),new Date(eventdate));
//	console.log("Days in between- "+ day);
	var month = Math.floor(day/30);
	
//	console.log(eventdate + "|-> "+ investmenttype + "|--> "+ p);
	
	if(month>0 && investmenttype!=null && p!=""){
	
//    console.log(eventdate+"::;"+today);
	
	
	if(month>0){
		console.log("Correct month detected");
		 document.getElementById("final-sip-amount").innerHTML ="";
		 document.getElementById("final-installment-amount").innerHTML = "";
		 calculate(month);
		 $('#registrybtn').prop('disabled', false);
		 
	}else{
		console.log("Data blank");
		$('#registrybtn').prop('disabled', true);
		document.getElementById("final-sip-amount").innerHTML ="0";
		document.getElementById("final-installment-amount").innerHTML = "0";
	}
	}
	}else{
		$('#registrybtn').prop('disabled', true);
	}
	
}

function calculate(month){
	 investmenttype
	 var investmenttype = document.getElementById("investmenttype").value;
//	 console.log(investmenttype);
	 if(investmenttype == 'SIPT'){
		 calculateInstallmentAmount(month);
	 }else if(investmenttype == 'SIPM'){
		 calculateMaturityAmount(month);
	 }else{
		 calculateMaturityAmount(month);
	 }
}
     function calculateMaturityAmount(t){
    	 /*
       var p = document.getElementById("principalMA").value;
      // var t = document.getElementById("timeMA").value;
       var n = document.getElementById("compoundMA").value;
       var r = document.getElementById("interestMA").value;
       */
       
       var p = document.getElementById('amount_sip').value;
       // var t = document.getElementById('timeIA').value;
        var n = document.getElementById('inputGroupSelect01').value;
        var r = 6.5;//document.getElementById('interestIA').value;

     /*  console.log(p);
       console.log(t);
       console.log(n);
       console.log(r);*/

       var amount_array = [];
       var x = calculate_x(r,n);
       
       for (var i = t; i>=1; i--){
         a = p*Math.pow(x,n*calculate_months_in_year(i));
//         console.log(a);
         amount_array.push(a);
       }
       var maturity_amount = amount_array.reduce(function(previousValue, currentValue, index, array) {
         return previousValue + currentValue;
       });
//       console.log(maturity_amount);
       document.getElementById("final-sip-amount").innerHTML = Math.round(maturity_amount);
       document.getElementById("final-installment-amount").innerHTML = p;
     }

     // var x = (1+r/n)
     function calculate_x(r,n){
       var x =  1+(r/100)/n;
//       console.log(x);
       return x;
     }
     // var y = (1+r/n)^nt
     // function calculate_y(x,n,t){
     //  return Math.pow(x,n*calculate_months_in_year(t));
     // }

     // var t_in_y = t/12; time expressed in year
     function calculate_months_in_year(t){
       return t/12;
     }

       function calculateInstallmentAmount(t ){

       var a = document.getElementById('amount_sip').value;
//       console.log("amount_sip="+a);
      // var t = document.getElementById('timeIA').value;
       var n = document.getElementById('inputGroupSelect01').value;
       var r = 6.5;//document.getElementById('interestIA').value;
       var x = calculate_x(r,n);
       var z = 0; 
       for (var i =t; i>=1; i--){
         z = z + Math.pow(x,n*calculate_months_in_year(i));
//		   console.log(z);
       }

       var principal_amount = Math.round(a/z);
       document.getElementById("final-installment-amount").innerHTML = a;// Math.round(principal_amount);
       document.getElementById("final-sip-amount").innerHTML = Math.round(principal_amount);
//      console.log(principal_amount)

     }
     function daysBetween( date1, date2 ) {   //Get 1 day in milliseconds   
    	 var one_day=1000*60*60*24;    // Convert both dates to milliseconds
    	 var date1_ms = date1.getTime();   
    	 var date2_ms = date2.getTime();    // Calculate the difference in milliseconds  
    	 var difference_ms = date2_ms - date1_ms;        // Convert back to days and return   
    	 return Math.round(difference_ms/one_day); 
   } 