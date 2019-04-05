package com.freemi.common.util;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.ModelAttribute;

public class InvestFormConstants {
	
	public static Map<String,String> holdingMode = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("SI", "Single");
			put("JO", "Joint");
			put("AS", "Anyone or Survivor");
		}
	};
	
	public static Map<String,String> nomineeRelation = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Son", "Son");
			put("Daughter", "Daughter");
			put("Parents", "Parents");
			put("Spouse", "Spouse");
		}
	};

	public static Map<String,String> occupationList = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("01", "Business");
			put("02", "Service");
			put("03", "Professional");
			put("04", "Agriculturist");
			put("05", "Retired");
			put("06", "Housewife");
			put("07", "Student");
			put("08", "Others");
			put("09", "Not Seprcified");
			put("41", "Private Sector Service");
			put("42", "Public Sector / Government Service");
			put("43", "Forex Dealer");
		}
	};


	public static Map<String,String> dividendPayMode = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("01", "Cheque");
			put("02", "Direct Credit");
			put("03", "ECS");
			put("04", "NEFT");
			put("05", "RTGS");
		}
	};


	public static Map<String,String> bankNames = new TreeMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("ICICI Bank", "ICICI Bank");
			put("State Bank of India", "State Bank of India");
			put("A.P. Mahesh Co-operative Urban Bank","A.P. Mahesh Co-operative Urban Bank");
			put("Axis Bank","Axis Bank");
			put("Bank of Baroda","Bank of Baroda");
			put("Airtel Payments Bank","Airtel Payments Bank");
			put("Andhra Bank","Andhra Bank");
			put("Bank of Bahrein and Kuwait","Bank of Bahrein and Kuwait");
			put("Central Bank of India","Central Bank of India");
			put("Corporation Bank","Corporation Bank");
			put("IDBI","IDBI");
			put("Dena Bank","Dena Bank");
			put("Himachal Pradesh State Co-operative Bank","Himachal Pradesh State Co-operative Bank");
			put("Indian Overseas Bank","Indian Overseas Bank");
			put("Indusind Bank","Indusind Bank");
			put("Syndicate Bank","Syndicate Bank");
			put("Yes Bank","Yes Bank");
			put("IDFC Bank","IDFC Bank");
			put("Prathama Bank","Prathama Bank");
			put("Karnataka Vikas Grameena Bank","Karnataka Vikas Grameena Bank");
			put("City Union Bank","City Union Bank");
			put("Abhyudaya Co-operative Bank","Abhyudaya Co-operative Bank");
			put("Almora Urban Co-operative Bank","Almora Urban Co-operative Bank");
			put("Nagpur Nagarik Sahakari Bank","Nagpur Nagarik Sahakari Bank");
			put("Jammu and Kashmir Bank","Jammu and Kashmir Bank");
			put("Dhanlaxmi Bank","Dhanlaxmi Bank");
			put("Shivalik Mercantile Co-operative Bank","Shivalik Mercantile Co-operative Bank");
			put("Delhi State Co-operative Bank","Delhi State Co-operative Bank");
			put("Punjab National Bank","Punjab National Bank");
			put("Thane Bharat Sahakari Bank","Thane Bharat Sahakari Bank");
			put("Janata Sahakari Bank (Pune)","Janata Sahakari Bank (Pune)");
			put("Saraswat Co-operative Bank","Saraswat Co-operative Bank");
			put("Akola Janata Commercial Co-operative Bank","Akola Janata Commercial Co-operative Bank");
			put("Canara Bank","Canara Bank");
			put("NKGSB Co-operative Bank","NKGSB Co-operative Bank");
			put("West Bengal State Co-operative Bank","West Bengal State Co-operative Bank");
			put("Karad Urban Co-operative Bank","Karad Urban Co-operative Bank");
			put("Development Bank of Singapore","Development Bank of Singapore");
			put("Suryoday Small Finance Bank","Suryoday Small Finance Bank");
			put("Andhra Pradesh State Co-operative Bank","Andhra Pradesh State Co-operative Bank");
			put("Janaseva Sahakari Bank","Janaseva Sahakari Bank");
			put("Equitas Small Finance Bank","Equitas Small Finance Bank");
			put("United Bank of India","United Bank of India");
			put("Vijaya Bank","Vijaya Bank");
			put("Shamrao Vithal Co-operative Bank","Shamrao Vithal Co-operative Bank");
			put("Pragathi Krishna Gramin Bank","Pragathi Krishna Gramin Bank");
			put("Union Bank of India","Union Bank of India");
			put("HDFC Bank","HDFC Bank");
			put("Karur Vysya Bank","Karur Vysya Bank");
			put("Rajasthan State Co-operative Bank","Rajasthan State Co-operative Bank");
			put("Indian Bank","Indian Bank");
			put("Oriental Bank of Commerce","Oriental Bank of Commerce");
			put("The Sindhudurg District Central Co-operative Bank","The Sindhudurg District Central Co-operative Bank");
			put("Kallappanna Awade Ichalkaranji Janata Sahakari Bank","Kallappanna Awade Ichalkaranji Janata Sahakari Bank");
			put("Capital Small Finance Bank","Capital Small Finance Bank");
			put("Apna Sahakari Bank","Apna Sahakari Bank");
			put("South Indian Bank","South Indian Bank");
			put("Bharat Co-operative Bank","Bharat Co-operative Bank");
			put("Dombivli Nagari Sahakari Bank","Dombivli Nagari Sahakari Bank");
			put("Rajarambapu Sahakari Bank","Rajarambapu Sahakari Bank");
			put("Karnataka Bank","Karnataka Bank");
			put("Tamilnadu Mercantile Bank","Tamilnadu Mercantile Bank");
			put("Punjab & Sind Bank","Punjab & Sind Bank");
			put("Kangra Central Co-operative Bank","Kangra Central Co-operative Bank");
			put("UCO Bank","UCO Bank");
			put("Bank of India","Bank of India");
			put("Bank of Maharashtra","Bank of Maharashtra");
			put("CITI Bank","CITI Bank");
			put("Utkarsh Small Finance Bank","Utkarsh Small Finance Bank");
			put("Allahabad Bank","Allahabad Bank");
			put("Maharashtra Gramin Bank","Maharashtra Gramin Bank");
			put("Fino Payments Bank","Fino Payments Bank");
			put("Nasik Merchants Co-operative Bank","Nasik Merchants Co-operative Bank");
			put("Pandharpur Urban Co Op. Bank Pandharpur","Pandharpur Urban Co Op. Bank Pandharpur");
			put("Kotak Mahindra Bank","Kotak Mahindra Bank");
			put("Rajgurunagar Sahakari Bank","Rajgurunagar Sahakari Bank");
			put("Thane Janata Sahakari Bank","Thane Janata Sahakari Bank");
			put("Paytm Payments Bank","Paytm Payments Bank");
			put("Zoroastrian Co-operative Bank","Zoroastrian Co-operative Bank");
			put("Kerala Gramin Bank","Kerala Gramin Bank");
			put("AU Small Finance Bank","AU Small Finance Bank");
			put("Kozhikode District Co-operatiave Bank","Kozhikode District Co-operatiave Bank");
			put("Gurgaon Gramin Bank","Gurgaon Gramin Bank");
			put("Gopinath Patil Parsik Janata Sahakari Bank","Gopinath Patil Parsik Janata Sahakari Bank");
			put("DCB Bank","DCB Bank");
			put("Citizen Credit Co-operative Bank","Citizen Credit Co-operative Bank");
			put("Telangana State Co-operative Apex Bank","Telangana State Co-operative Apex Bank");
			put("Kurla Nagarik Sahakari Bank","Kurla Nagarik Sahakari Bank");
			put("Esaf Small Finance Bank","Esaf Small Finance Bank");
			put("Punjab & Maharashtra Co-operative Bank","Punjab & Maharashtra Co-operative Bank");
			put("Ujjivan Small Finance Bank","Ujjivan Small Finance Bank");
			put("North East Small Finance Bank","North East Small Finance Bank");
			put("Reserve Bank of India","Reserve Bank of India");
			put("RBL Bank","RBL Bank");
			put("Cosmos Co-operative Bank","Cosmos Co-operative Bank");
			put("Surat District Co-operative Bank","Surat District Co-operative Bank");
			put("Mumbai District Central Co-operative Bank","Mumbai District Central Co-operative Bank");
			put("Nainital Bank","Nainital Bank");
			put("Akola District Central Co-operative Bank","Akola District Central Co-operative Bank");
			put("Nutan Nagarik Sahakari Bank","Nutan Nagarik Sahakari Bank");
			put("Bandhan Bank","Bandhan Bank");
			put("Laxmi Vilas Bank","Laxmi Vilas Bank");
			put("Andhra Pragathi Grameena Bank","Andhra Pragathi Grameena Bank");
			put("Federal Bank","Federal Bank");
			put("Ahmedabad Mercantile Co-operative Bank","Ahmedabad Mercantile Co-operative Bank");
			put("Thane District Central Co-operative Bank","Thane District Central Co-operative Bank");
			put("Shri Chhatrapati Rajashri Shahu Urban Co-operative Bank","Shri Chhatrapati Rajashri Shahu Urban Co-operative Bank");
			put("Jalgaon Peoples Co-operative Bank","Jalgaon Peoples Co-operative Bank");
			put("Mysore Chamarajanagar District Co-operative Bank","Mysore Chamarajanagar District Co-operative Bank");
			put("Municipal Co-operative Bank","Municipal Co-operative Bank");
			put("Solapur Janata Sahakari Bank","Solapur Janata Sahakari Bank");
			put("Catholic Syrian Bank","Catholic Syrian Bank");
			put("Standard Chartered Bank","Standard Chartered Bank");
			put("Sutex Co-operative.bank","Sutex Co-operative.bank");

		}
	};

	public static Map<String,String> accountTypes = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("SB","Savings");
			put("CB","Current");
		}
	};


	public static Map<String,String> states = new TreeMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("AN","ANDAMAN &amp; NICOBAR");
			/*put("AO","ARMY OFFICER");*/
			put("AP","ANDHRA PRADESH");
			put("AR","ARUNACHAL PRADESH");
			put("AS","ASSAM");
			put("BH","BIHAR");
			put("CG","CHHATTISGARH");
			put("CH","CHANDIGARH");
			put("DD","DIU AND DAMAN");
			put("DL","DELHI");
			put("DN","DADRA AND NAGAR HAVELI");
			put("GO","GOA");
			put("GU","GUJARAT");
			put("HA","HARYANA");
			put("HP","HIMACHAL PRADESH");
			put("JH","JHARKHAND");
			put("JK","JHARKHAND");
			put("KA","KARNATAKA");
			put("KE","KERALA");
			put("KR","JAMMU &amp; KASHMIR");
			put("LA","LAKSHWADEEP");
			put("LD","LAKSHADWEEP");
			put("MA","MAHARASHTRA");
			put("ME","MEGHALAYA");
			put("MI","MIZORAM");
			put("MN","MANIPUR");
			put("MP","MADHYA PRADESH");
			put("NA","NAGALAND");
			put("ND","NEW DELHI");
			put("NG","NAGPUR");
			put("OH","OTHERS");
			put("OR","ORISSA");
			put("PO","PONDICHERRY");
			put("PU","PUNJAB");
			put("RA","RAJASTHAN");
			put("SI","SIKKIM");
			put("SU","SURAT");
			put("TG","TELANGANA");
			put("TN","TAMIL NADU");
			put("TR","TRIPURA");
			put("UC","UTTARANCHAL");
			put("UL","UTTARAKHAND");
			put("UP","UTTAR PRADESH");
			put("WB","WEST BENGAL");
		}
	};

	public static Map<String,String> bsePaymentMethod = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("01", "INTERNET BANKING");
			put("02", "NEFT");
			put("03", "CARD");
			put("04", "UPI");
		}
	};

	public static Map<Integer,String> bseInvestMonths = new LinkedHashMap<Integer,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(1, "January");
			put(2, "February");
			put(3, "March");
			put(4, "April");
			put(5, "May");
			put(6, "June");
			put(7, "July");
			put(8, "August");
			put(9, "September");
			put(10, "October");
			put(11, "November");
			put(12, "December");

		}
	};
	
	
	public static Map<String,String> fatcaTaxStatusIndividual = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("01", "Individual");
			put("02", "On Behalf Of Minor");
			put("21", "NRI - NRE (Repatriation)");
			put("24", "NRI - NRO [Non Repatriation]");
		}
	};
	
	
	public static Map<String,String> fatcaOccupationType = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("B", "Business");
			put("O", "Others");
			put("S", "Service");
		}
	};
	
	public static Map<String,String> fatcaTaxStatusNonIndividual = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			/*put("01", "Individual");
			put("02", "On Behalf Of Minor");
			put("21", "NRI - NRE (Repatriation)");
			put("24", "NRI - NRO [Non Repatriation]");*/
		}
	};
	
	public static Map<String,String> fatcaAddressType = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("1", "Residential Or Business");
			put("2", "Residential");
			put("3", "Business");
			put("4", "Registered Office");
			put("5", "Unspecified");
		}
	};
	
	public static Map<String,String> fatcaPoliticalView = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			/*put("", "--Select--");*/
			put("Y", "The investor is politically exposed person");
			put("N", "The investor is not politically exposed person");
			put("R", "The investor is a relative of the politically exposed person");
		}
	};
	
	public static Map<String,String> fatcaWealthSource = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("01", "Salary");
			put("02", "Business Income");
			put("03", "Gift");
			put("04", "Ancestral Property");
			put("05", "Rental Income");
			put("06", "Prize Money");
			put("07", "Royalty");
			put("08", "Others");
		}
	};
	
	
	public static Map<String,String> fatcaIncomeSlab = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("31", "Below 1 Lakh");
			put("32", "> 1 <=5 Lacs");
			put("33", ">5 <=10 Lacs");
			put("34", ">10 <= 25 Lacs");
			put("35", "> 25 Lacs < = 1 Crore");
			put("36", "Above 1 Crore");

		}
	};
	
	public static Map<String,String> countryCode = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("IN", "India");
			
		}
	};
	
	public static Map<String,String> identityDocumentType = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("A", "Passport");
			put("B", "Election ID Card");
			put("C", "PAN Card");
			put("C1", "Company Identification Number");
			put("D", "ID Card");
			put("E", "Driving License");
			put("E1", "Global Entity Identification Number");
			put("G", "UIDIA / Aadhar letter");
			put("G1", "US GIIN");
			put("H", "NREGA Job Card");
			put("T", "TIN [Tax Payer Identification Number]");
			put("X", "Not categorized");
			
		}
	};


	public static Map<Integer,String> bseInvestStartYear = new LinkedHashMap<Integer,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			int initialYear = LocalDate.now().getYear();
			int initialMonth = LocalDate.now().getMonthValue();
			if(initialMonth==12){
				for(int i=initialYear; i<=initialYear+1;i++){
					put(i, Integer.toString(i));
				}
			}
			else{
				put(initialYear, Integer.toString(initialYear));
			}

		}
	};

}
