var finalPrincipal = 0;
var interesttoPay= 0;

function initialData(){
	var principal = 0;
	
	principal = document.getElementById("princiaplRange").value;
	document.getElementById("principalbox").value = principal;
	principal = document.getElementById("interestRange").value;
	document.getElementById("interestBox").value = principal;
	principal = document.getElementById("tenureRange").value;
	document.getElementById("tenureBox").value = principal;
	calculateLoanAmount();
	
}

function principalrangeBar(){
	var principal = document.getElementById("princiaplRange").value;
	console.log(principal);
	document.getElementById("principalbox").value = principal;
	calculateLoanAmount();
	
}

function principalinput(){
	var digitregexp = /^[0-9]+$/;
	var principal = document.getElementById("principalbox").value;
//	console.log(principal);
	
	if(!digitregexp.test(document.getElementById("principalbox").value)){
		document.getElementById("principalexcess").innerHTML = "Invalid format!";
	}else if(principal > 10000000){
		document.getElementById("principalexcess").innerHTML = "Limit exceeded";
	}else{
		document.getElementById("principalexcess").innerHTML = "";
		document.getElementById("princiaplRange").value = principal;
		calculateLoanAmount();
	}
	
}

function interestRangeBar(){
	var principal = document.getElementById("interestRange").value;
//	console.log(principal);
	
	document.getElementById("interestBox").value = principal;
	calculateLoanAmount();
}

function interestInputBox(){
	var digitregexp = /^([0-9]*[.])?[0-9]+$/;
	var principal = document.getElementById("interestBox").value;
//	console.log(principal);
	
	if(!digitregexp.test(document.getElementById("interestBox").value)){
		document.getElementById("interestexcess").innerHTML = "Invalid format";
	}
	else if(principal > 14){
		document.getElementById("interestexcess").innerHTML = "Limit exceeded";
	}else{
		document.getElementById("interestexcess").innerHTML = "";
		document.getElementById("interestexcess").value = principal;
		calculateLoanAmount();
	}
	
}

function tenureRangeBar(){
	var principal = document.getElementById("tenureRange").value;
//	console.log(principal);
	document.getElementById("tenureBox").value = principal;
	calculateLoanAmount();
}

function tenureBoxInput(){
	var digitregexp = /^[0-9]+$/;
	var principal = document.getElementById("tenureBox").value ;
//	console.log(principal);
	
	if(!digitregexp.test(document.getElementById("tenureBox").value)){
		document.getElementById("tenureexcess").innerHTML = "Invalid format!";
	}
	else if(principal > 40){
		document.getElementById("tenureexcess").innerHTML = "Limit exceeded";
	}else{
		document.getElementById("tenureexcess").innerHTML = "";
		document.getElementById("tenureRange").value = principal;
		calculateLoanAmount();
	}
	
}

function calculateLoanAmount(){
	
//	console.log("Calculate")
	var digitregexp = /^[0-9]+$/;
	var principal = document.forms["homeLoanForm"]["principal"].value;
	finalPrincipal = principal;
    var interest = Number(((document.forms["homeLoanForm"]['interest'].value)/(12*100)));
    var tenure = Number(((document.forms["homeLoanForm"]['tenure'].value)*12));
    
//    console.log(principal + " - "+ interest + " - "+tenure );
    
    var emi = (principal * interest * Math.pow(1 + interest, tenure) / (Math.pow(1 + interest, tenure) - 1));
    var EMI = Math.round(emi);
    var totalCost = Math.round(emi * tenure);
    var interestPaid = Math.round(totalCost - principal);
    var totalAmouttoBePaid = Number(principal)+ Number(interestPaid);
    document.getElementById("totaltobePaid").innerHTML = totalAmouttoBePaid;
    document.getElementById("interestpayable").innerHTML = interestPaid;
    document.getElementById("totalCost").innerHTML = totalCost;
    document.getElementById("EMI").innerHTML = EMI;
    
    interesttoPay = interestPaid;
    
    drawChart();
    
    
}


//Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {
	
//	var principal = document.getElementById("principalalloted").value;
//    var interestPayable = document.getElementById("interestpayable").value;

  // Create the data table.
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Type');
  data.addColumn('number', 'amount');
  
  
  data.addRows([
    ['Principal', Number(finalPrincipal)],
    ['Interest', Number(interesttoPay)]

  ]);

  // Set chart options
  var options = {'title': 'Total payable amount details ',
                 'width':400,
                 'height':300};

  // Instantiate and draw our chart, passing in some options.
  var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
  chart.draw(data, options);
}

