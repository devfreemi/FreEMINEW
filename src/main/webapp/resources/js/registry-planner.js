//console.log = function() {}
var registryfundsdatareceive=false;

/*(document).ready(function(){
	$("form").submit(function(e){
		e.preventDefault();


	});
});
*/
function calculateregistrydetails(){
	console.log("Submitted");
	
	calculatesip();
	registryfunds();

}


function calculatesip(){
	console.log("Calculate SIP");
	
	var eventdate = new Date($("#eventdate").val());
	console.log(eventdate);
	var enteredYear= eventdate.getFullYear();
	console.log("Entered year - "+enteredYear );
	var investmenttype = document.getElementById('investmenttype').value;
	var p = document.getElementById('amountid').value;
	
	
	eventdate = eventdate.toDateString("yyyy-mm-dd");
	var today = new Date().toDateString("yyyy-mm-dd");
	var d= new Date();
	var currentYear= d.getFullYear();
	console.log("  --> current year: "+ currentYear);
	
	
	
	if(enteredYear>=currentYear){
	var day=daysBetween(new Date(today),new Date(eventdate));
//	console.log("Days in between- "+ day);
	var month = Math.floor(day/30);
	
	console.log(eventdate + "|-> "+ investmenttype + "|--> "+ p);
	
	if(month>0 && investmenttype!=null && p!=""){
	
//    console.log(eventdate+"::;"+today);
	
	
	if(month>0){
		console.log("Correct month detected");
//		 document.getElementById("final-sip-amount").innerHTML ="";
//		 document.getElementById("final-installment-amount").innerHTML = "";
		 calculate(month);
//		 $('#registrybtn').prop('disabled', false);
		 
	}else{
		console.log("Data blank");
		$('#registrybtn').prop('disabled', true);
//		document.getElementById("final-sip-amount").innerHTML ="0";
//		document.getElementById("final-installment-amount").innerHTML = "0";
	}
	}
	}else{
		$("#infomsg").text("Event must be in future!");
//		$('#registrybtn').prop('disabled', true);
	}
}


function calculate(month){
//	 investmenttype
	$("#tenureid").val(month);
	
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
      
      var p = document.getElementById('amountid').value;
      // var t = document.getElementById('timeIA').value;
//       var n = document.getElementById('inputGroupSelect01').value;
      var  n =t;
       var r = 6.5;//document.getElementById('interestIA').value;

    /*  console.log(p);
      console.log(t);
      console.log(n);
      console.log(r);*/

      var amount_array = [];
      var x = calculate_x(r,n);
      
      for (var i = t; i>=1; i--){
        a = p*Math.pow(x,n*calculate_months_in_year(i));
//        console.log(a);
        amount_array.push(a);
      }
      var maturity_amount = amount_array.reduce(function(previousValue, currentValue, index, array) {
        return previousValue + currentValue;
      });
//      console.log(maturity_amount);
//      document.getElementById("final-sip-amount").innerHTML = Math.round(maturity_amount);
//      document.getElementById("final-installment-amount").innerHTML = p;
      $("#maturityvalue").text(maturity_amount);
      $("#monthlySavings").val(Math.round(+p));
    }

    // var x = (1+r/n)
    function calculate_x(r,n){
      var x =  1+(r/100)/n;
//      console.log(x);
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

    function clearMAForm(){
      var form = document.getElementById("maCalculator");
      form.reset();
      // document.getElementById("final-maturity-amount").innerHTML = '<i class="fa fa-inr "></i>';
    }
    function clearIAForm(){
      var form = document.getElementById("iaCalculator");
      form.reset();
      // document.getElementById("final-installment-amount").innerHTML = '<i class="fa fa-inr "></i>';
    }

    function calculateInstallmentAmount(t ){

      var a = document.getElementById('amountid').value;
//      console.log("amount_sip="+a);
     // var t = document.getElementById('timeIA').value;
//      var n = document.getElementById('inputGroupSelect01').value;
      var n = t;
      
      var r = 6.5;//document.getElementById('interestIA').value;
      var x = calculate_x(r,n);
      var z = 0; 
      for (var i =t; i>=1; i--){
        z = z + Math.pow(x,n*calculate_months_in_year(i));
//		   console.log(z);
      }

      var principal_sip_amount = Math.round(a/z);
//      document.getElementById("final-installment-amount").innerHTML = a;// Math.round(principal_amount)
//      document.getElementById("final-sip-amount").innerHTML = Math.round(principal_amount);
      $("#maturityvalue").text(a);
      $("#monthlySavings").val(Math.round(principal_sip_amount));
//     console.log(principal_amount)

    }
    function daysBetween( date1, date2 ) {   //Get 1 day in milliseconds   
   	 var one_day=1000*60*60*24;    // Convert both dates to milliseconds
   	 var date1_ms = date1.getTime();   
   	 var date2_ms = date2.getTime();    // Calculate the difference in milliseconds  
   	 var difference_ms = date2_ms - date1_ms;        // Convert back to days and return   
   	 return Math.round(difference_ms/one_day); 
  } 


function registryfunds(){
	if(!registryfundsdatareceive){
		request = $.ajax({
			url: "/products/api/registry/get-registry-funds",
			method: "POST",
			data: JSON.stringify({
				"mobile" : "NA"
			}),
			async: true,
			datatype: "json",
			contentType: 'application/json',
			/*beforeSend: function() {
					$("#cancelsubmitbtn").html("Processing <i class=\"fas fa-spinner fa-spin\"></i>");
					$('#cancelsubmitbtn').attr('disabled','disabled');
				}*/
		});
		request.done(function(data) {
			registryfundsdatareceive=true;
			console.log(data.length + " -> "+ data );
			var records = JSON.parse(data);
			var table = document.getElementById("registryfundslist");
			table.setAttribute("class","animated fadeIn");

			for(i=0;i<records.length;i++){
				console.log(records[i].registryfundCode + " -> "+ records[i].fundName);
//				console.log(records[i].registryfundCode);
				var row = table.insertRow();
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);

				cell1.innerHTML = records[i].fundName;
				cell3.innerHTML = "<div class='custom-control custom-checkbox text-center'><input type='radio' name='schemeCode' class='custom-control-input selectedscheme' id='scheme"+i+"' value='"+records[i].schemeCode+"' data-schemename='"+records[i].fundName+"'><label class='custom-control-label' style='cursor: pointer;' for='scheme"+i+"'></label></div>";
			}

			$('#dtBasicExample').DataTable({
				"searching": false,
				"paging": false,
				"columns" : [ 
					{
						"orderable" : false
					},
					{
						"orderable" : false
					}, 
					{
						"orderable" : false
					} 
					],
					"order" : [ [ 1, "asc" ] ]
			});
			$('.dataTables_length').addClass('bs-select');


		});

		request.fail(function(jqXHR, textStatus) {
			/*$("#cancelresponse").text("Failed to process your request.");*/

		});

		request.always(function(msg){
			/*$("#cancelsubmitbtn").html("Cancel SIP");
				$('#cancelsubmitbtn').removeAttr('disabled');*/
		});

	}else{

	}
}


$(document).ready(function(e) {  
	$(".definedvalue").on('click', function(e) {
		$("#amountid").val(Number($(this).data("value")));
	});
});


/*$(document).ready(function(e) {  
$("input:radio[name='scheme']").on('change', function(e) {
	console.log("CALL")
    console.log(e.type );
});
});*/

$(document).on("click","input:radio[name='scheme']",function(){
//	   alert('made it');
	console.log($(this).data("schemename"))
	$("#schemenameid").val($(this).data("schemename"));
	
	 });

