function calculateTax() {
	var digitregexp = /^[0-9]+$/;
	var earnerType = document.getElementById("earnerTypeid").value;
//	console.log(earnerType);
	var annualIncome = Number(!digitregexp.test(document.getElementById("annualIncomeid").value)?0 : document.getElementById("annualIncomeid").value );
//	console.log(annualIncome);
//	console.log(isNaN(annualIncome));
	var elss = !digitregexp.test(document.getElementById("elssid").value)? 0 : document.getElementById("elssid").value ;
	var ppf = !digitregexp.test(document.getElementById("ppfid").value)? 0 : document.getElementById("ppfid").value ;
	var nsc = !digitregexp.test(document.getElementById("nscid").value)? 0 : document.getElementById("nscid").value ;
	var fixedDeposit = !digitregexp.test(document.getElementById("fixedDepositid").value)? 0 : document.getElementById("fixedDepositid").value ; 
	var ulip = !digitregexp.test(document.getElementById("ulipid").value)?0 : document.getElementById("ulipid").value ;
	var lifeInsurance = !digitregexp.test(document.getElementById("lifeInsuranceid").value)? 0 : document.getElementById("lifeInsuranceid").value ; 
	var investment80d = Number(!digitregexp.test(document.getElementById("healthInsuranceid").value)? 0 : document.getElementById("healthInsuranceid").value ); 
	var nps = Number(!digitregexp.test(document.getElementById("npsid").value)? 0 : document.getElementById("npsid").value );

	var taxableIncome = 0;
	var tax = 0;
	var taxWithoutInvestment=0
	var cess = 0;
	var surcharge = 0;
	var calculatedTax = 0;
	var relief87a = 0;
	var taxFinal = 0;
	var totalTax = 0;
	var investment80c = 0;
	investment80c += Number(elss) + Number(ppf) + Number(nsc) + Number(fixedDeposit) + Number(ulip) + Number(lifeInsurance);
//	console.log(investment80c);
	document.getElementById("total80c").innerHTML = investment80c;
	
	if(investment80c > 150000){
		document.getElementById("excessinvestment").innerHTML = "Maximum benefit: &#8377; 1,50,000";
	}else{
		document.getElementById("excessinvestment").innerHTML = "";
	}
		
	if(annualIncome > 250000){
		
		investment80c = (investment80c > 150000) ? 150000 : investment80c;
		investment80d = (investment80d > 60000) ? 60000 : investment80d;
		nps = (nps > 50000) ? 50000 : nps;
		taxableIncome = (annualIncome - (investment80c + investment80d + nps));
		
		if ((taxableIncome <= 500000)) {

			if (earnerType == 'MF' && (taxableIncome > 250000)) {

				if (taxableIncome <= 300000) {
					tax = ((taxableIncome - 250000) * .05);
					console.log("MF tax -> "+ tax)
					calculatedTax = tax;
					relief87a = tax;
					calculatedTax = tax - relief87a;
				} else if ((taxableIncome > 300000) && (taxableIncome <= 350000)) {
					tax = ((taxableIncome - 250000) * .05);
					calculatedTax = tax;
					relief87a = 2500;
					calculatedTax = tax - relief87a;
				} else {
					calculatedTax = tax = ((taxableIncome - 250000) * .05);

				}
				cess = (calculatedTax * .03);
			} else if (earnerType == 'SC' && (taxableIncome > 300000)) {
				tax = ((taxableIncome - 300000) * .05);
				calculatedTax = tax;
				if (taxableIncome <= 350000) {
					relief87a = 2500;
					calculatedTax = tax - relief87a;
				} else {
					calculatedTax = tax = ((taxableIncome - 300000) * .05);
					// relief87a = 2500;
					// calculatedTax = tax - relief87a;
				}

			} else { // For SSC, Nil tax upto 5L
				calculatedTax = 0;
			}
			cess = (calculatedTax * .03);   // Cess on Income tax

		} else if ((taxableIncome > 500000) && (taxableIncome <= 1000000)) { // Taxable slab same for all above Rs.5 lac, so no seperate calculation required
			if (earnerType == 'MF') {
				calculatedTax = tax = 12500 + ((taxableIncome - 500000) * .2);

			} else if (earnerType == 'SC') {
				calculatedTax = tax = 10000 + ((+taxableIncome - 500000) * .2);
			} else {
				calculatedTax = tax = ((taxableIncome - 500000) * .2);
			}
			cess = (tax * .03);
		} else {   // Any income above 10Lac
			if (earnerType == 'MF') {
				if ((taxableIncome <= 5000000)) {
					calculatedTax = tax = 12500 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03)
				} else if ((taxableIncome > 5000000) && (taxableIncome <= 10000000)) {
					calculatedTax = tax = 12500 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .1);
				} else {
					calculatedTax = tax = 12500 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .15);
				}
			}
			else if (earnerType == 'SC') {
				if ((taxableIncome <= 5000000)) {
					calculatedTax = tax = 10000 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03)
				} else if ((taxableIncome > 5000000) && (taxableIncome <= 10000000)) {
					calculatedTax = tax = 10000 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .1);
				} else {
					calculatedTax = tax = 10000 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .15);
				}
			} else { //For SSC
				if ((taxableIncome <= 5000000)) {
					calculatedTax = tax = 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03)
				} else if ((taxableIncome > 5000000) && (taxableIncome <= 10000000)) {
					calculatedTax = tax = 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .1);
				} else {
					calculatedTax = tax = 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .15);
				}
			}
		}

//		var taxableIncome = taxableIncome;
		cess = Math.round(cess * 100) / 100;
		surcharge = Math.round(surcharge * 100) / 100;
		relief87a = Math.round(relief87a * 100) / 100;
//		result.cess = cess;
//		result.rebate87a = relief87a;
//		result.surcharge = surcharge;
		taxFinal = Math.round(tax * 100) / 100;
		totalTax = Math.round((calculatedTax + cess + surcharge) * 100) / 100;
		
		document.getElementById("taxableIncome").innerHTML = "&#8377; "+taxableIncome;
		document.getElementById("tax").innerHTML = "&#8377; "+taxFinal;
		document.getElementById("rebate87a").innerHTML = "&#8377; "+relief87a;
		document.getElementById("cess").innerHTML = "&#8377; "+cess;
		document.getElementById("surcharge").innerHTML = "&#8377; "+surcharge;
		document.getElementById("totalTax").innerHTML = "&#8377; "+totalTax;
		
		

	}else{
		document.getElementById("taxableIncome").innerHTML = "&#8377; 0";
		document.getElementById("tax").innerHTML = "&#8377; 0";
		document.getElementById("rebate87a").innerHTML = "&#8377; 0";
		document.getElementById("cess").innerHTML = "&#8377; 0";
		document.getElementById("surcharge").innerHTML = "&#8377; 0";
		document.getElementById("totalTax").innerHTML = "&#8377; 0";
	}

	OptimizedTax();
} 

function OptimizedTax(){
	var digitregexp = /^[0-9]+$/;
	var earnerType = document.getElementById("earnerTypeid").value;
	var annualIncome = Number(!digitregexp.test(document.getElementById("annualIncomeid").value)?0 : document.getElementById("annualIncomeid").value );
	var taxableIncome = 0;
	var tax = 0;
	var taxWithoutInvestment=0
	var cess = 0;
	var surcharge = 0;
	var calculatedTax = 0;
	var relief87a = 0;
	var taxFinal = 0;
	var totalTax = 0;
	var investment80c = Number(150000);
	var investment80d = Number(60000);
	var nps = Number(50000);
	
	if(annualIncome > 250000){
		
		taxableIncome = (annualIncome - (investment80c + investment80d + nps));
		
		if ((taxableIncome <= 500000)) {

			if (earnerType == 'MF' && (taxableIncome > 250000)) {

				if (taxableIncome <= 300000) {
					tax = ((taxableIncome - 250000) * .05);
					console.log("MF tax -> "+ tax)
					calculatedTax = tax;
					relief87a = tax;
					calculatedTax = tax - relief87a;
				} else if ((taxableIncome > 300000) && (taxableIncome <= 350000)) {
					tax = ((taxableIncome - 250000) * .05);
					calculatedTax = tax;
					relief87a = 2500;
					calculatedTax = tax - relief87a;
				} else {
					calculatedTax = tax = ((taxableIncome - 250000) * .05);

				}
				cess = (calculatedTax * .03);
			} else if (earnerType == 'SC' && (taxableIncome > 300000)) {
				tax = ((taxableIncome - 300000) * .05);
				calculatedTax = tax;
				if (taxableIncome <= 350000) {
					relief87a = 2500;
					calculatedTax = tax - relief87a;
				} else {
					calculatedTax = tax = ((taxableIncome - 300000) * .05);
					// relief87a = 2500;
					// calculatedTax = tax - relief87a;
				}

			} else { // For SSC, Nil tax upto 5L
				calculatedTax = 0;
			}
			cess = (calculatedTax * .03);   // Cess on Income tax

		} else if ((taxableIncome > 500000) && (taxableIncome <= 1000000)) { // Taxable slab same for all above Rs.5 lac, so no seperate calculation required
			if (earnerType == 'MF') {
				calculatedTax = tax = 12500 + ((taxableIncome - 500000) * .2);

			} else if (earnerType == 'SC') {
				calculatedTax = tax = 10000 + ((+taxableIncome - 500000) * .2);
			} else {
				calculatedTax = tax = ((taxableIncome - 500000) * .2);
			}
			cess = (tax * .03);
		} else {   // Any income above 10Lac
			if (earnerType == 'MF') {
				if ((taxableIncome <= 5000000)) {
					calculatedTax = tax = 12500 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03)
				} else if ((taxableIncome > 5000000) && (taxableIncome <= 10000000)) {
					calculatedTax = tax = 12500 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .1);
				} else {
					calculatedTax = tax = 12500 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .15);
				}
			}
			else if (earnerType == 'SC') {
				if ((taxableIncome <= 5000000)) {
					calculatedTax = tax = 10000 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03)
				} else if ((taxableIncome > 5000000) && (taxableIncome <= 10000000)) {
					calculatedTax = tax = 10000 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .1);
				} else {
					calculatedTax = tax = 10000 + 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .15);
				}
			} else { //For SSC
				if ((taxableIncome <= 5000000)) {
					calculatedTax = tax = 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03)
				} else if ((taxableIncome > 5000000) && (taxableIncome <= 10000000)) {
					calculatedTax = tax = 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .1);
				} else {
					calculatedTax = tax = 100000 + ((taxableIncome - 1000000) * .3);
					cess = (tax * .03);
					surcharge = ((calculatedTax + cess) * .15);
				}
			}
		}

//		var taxableIncome = taxableIncome;
		cess = Math.round(cess * 100) / 100;
		surcharge = Math.round(surcharge * 100) / 100;
		relief87a = Math.round(relief87a * 100) / 100;
//		result.cess = cess;
//		result.rebate87a = relief87a;
//		result.surcharge = surcharge;
		taxFinal = Math.round(tax * 100) / 100;
		totalTax = Math.round((calculatedTax + cess + surcharge) * 100) / 100;
		
		document.getElementById("totalReducedTax").innerHTML = "&#8377; "+totalTax;
	

	}else{
		document.getElementById("totalReducedTax").innerHTML = "&#8377; 0";
	}
}
