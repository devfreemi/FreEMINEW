package com.freemi.common.util;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class InvestFormConstants {


	public static Map<String,String> taxStatus = new LinkedHashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("01", "Individual");
			//	    put("02", "On behalf of minor");
			put("03", "HUF");
			put("04", "Company");
			put("06", "Partnership Firm");
			put("07", "Body Corporate");
			put("08", "Trust");
			put("09", "Society");
			put("13", "Sole Proprietorship");
		}
	};

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
			put("N", "Not politically exposed person");
			put("Y", "Politically exposed person");
			put("R", "Relative of the politically exposed person");
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

	public static Map<String,String[]> MahindaImageReferenceRequirement = new LinkedHashMap<String,String[]>(){

		private static final long serialVersionUID = 1L;

		{
			//			Kyc_Image_Ref_No is Mandatory,Kyc_Image_Exp_Date is Mandatory
			put("A",new String[] {"Y","Y"});
			put("B",new String[] {"Y","N"});
			put("C",new String[] {"Y","N"});
			put("D",new String[] {"Y","Y"});
			put("E",new String[] {"Y","N"});
			put("F",new String[] {"N","N"});
			put("Z",new String[] {"N","N"});
			put("MFPH",new String[] {"N","N"});
			put("MFCC",new String[] {"Y","N"});
			put("MF15GH",new String[] {"N","N"});
			put("MFTRC",new String[] {"Y","Y"});

		}
	};

	public static Map<String,String> bseCountryList = new LinkedHashMap<String,String>(){

		private static final long serialVersionUID = 1L;

		{
			put("AD","Andorra");
			put("AE","United Arab Emirates");
			put("AF","Afghanistan");
			put("AG","Antigua And Barbuda");
			put("AI","Anguilla");
			put("AL","Albania");
			put("AM","Armenia");
			put("AN","Netherlands Antilles");
			put("AO","Angola");
			put("AQ","Antarctica");
			put("AR","Argentina");
			put("AS","American Samoa");
			put("AT","Austria");
			put("AU","Australia");
			put("AW","Aruba");
			put("AX","Aland Islands");
			put("AZ","Azerbaijan");
			put("BA","Bosnia And Herzegovina");
			put("BB","Barbados");
			put("BD","Bangladesh");
			put("BE","Belgium");
			put("BF","Burkina Faso");
			put("BG","Bulgaria");
			put("BH","Bahrain");
			put("BI","Burundi");
			put("BJ","Benin");
			put("BL","Saint Barthelemy");
			put("BM","Bermuda");
			put("BN","Brunei Darussalam");
			put("BO","Bolivia");
			put("BQ","Bonaire, Sint Eustatius And Saba");
			put("BR","Brazil");
			put("BS","Bahamas");
			put("BT","Bhutan");
			put("BV","Bouvet Island");
			put("BW","Botswana");
			put("BY","Belarus");
			put("BZ","Belize");
			put("CA","Canada");
			put("CC","Cocos (Keeling) Islands");
			put("CD","Congo, The Democratic Republic Of The");
			put("CF","Central African Republic");
			put("CG","Congo");
			put("CH","Switzerland");
			put("CI","Côte Divoire");
			put("CK","Cook Islands");
			put("CL","Chile");
			put("CM","Cameroon");
			put("CN","China");
			put("CO","Colombia");
			put("CR","Costa Rica");
			put("CU","Cuba");
			put("CV","Cape Verde");
			put("CW","Curacao");
			put("CX","Christmas Island");
			put("CY","Cyprus");
			put("CZ","Czech Republic");
			put("DE","Germany");
			put("DJ","Djibouti");
			put("DK","Denmark");
			put("DM","Dominica");
			put("DO","Dominican Republic");
			put("DZ","Algeria");
			put("EC","Ecuador");
			put("EE","Estonia");
			put("EG","Egypt");
			put("EH","Western Sahara");
			put("ER","Eritrea");
			put("ES","Spain");
			put("ET","Ethiopia");
			put("FI","Finland");
			put("FJ","Fiji");
			put("FK","Falkland Islands (Malvinas)");
			put("FM","Micronesia, Federated States Of");
			put("FO","Faroe Islands");
			put("FR","France");
			put("GA","Gabon");
			put("GB","United Kingdom");
			put("GD","Grenada");
			put("GE","Georgia");
			put("GF","French Guiana");
			put("GG","Guernsey");
			put("GH","Ghana");
			put("GI","Gibraltar");
			put("GL","Greenland");
			put("GM","Gambia");
			put("GN","Guinea");
			put("GP","Guadeloupe");
			put("GQ","Equatorial Guinea");
			put("GR","Greece");
			put("GS","South Georgia And The South Sandwich Islands");
			put("GT","Guatemala");
			put("GU","Guam");
			put("GW","Guinea-Bissau");
			put("GY","Guyana");
			put("HK","Hong Kong");
			put("HM","Heard Island And McDonald Islands");
			put("HN","Honduras");
			put("HR","Croatia");
			put("HT","Haiti");
			put("HU","Hungary");
			put("ID","Indonesia");
			put("IE","Ireland");
			put("IL","Israel");
			put("IM","Isle Of Man");
			put("IO","British Indian Ocean Territory");
			put("IQ","Iraq");
			put("IR","Iran, Islamic Republic Of");
			put("IS","Iceland");
			put("IT","Italy");
			put("JE","Jersey");
			put("JM","Jamaica");
			put("JO","Jordan");
			put("JP","Japan");
			put("KE","Kenya");
			put("KG","Kyrgyzstan");
			put("KH","Cambodia");
			put("KI","Kiribati");
			put("KM","Comoros");
			put("KN","Saint Kitts And Nevis");
			put("KP","Korea, Democratic Peoples Republic Of");
			put("KR","Korea, Republic Of");
			put("KW","Kuwait");
			put("KY","Cayman Islands");
			put("KZ","Kazakhstan");
			put("LA","Lao Peoples Democratic Republic");
			put("LB","Lebanon");
			put("LC","Saint Lucia");
			put("LI","Liechtenstein");
			put("LK","Sri Lanka");
			put("LR","Liberia");
			put("LS","Lesotho");
			put("LT","Lithuania");
			put("LU","Luxembourg");
			put("LV","Latvia");
			put("LY","Libyan Arab Jamahiriya");
			put("MA","Morocco");
			put("MC","Monaco");
			put("MD","Moldova, Republic Of");
			put("ME","Montenegro");
			put("MF","Saint Martin");
			put("MG","Madagascar");
			put("MH","Marshall Islands");
			put("MK","Macedonia, The Former Yugoslav Republic Of");
			put("ML","Mali");
			put("MM","Myanmar");
			put("MN","Mongolia");
			put("MO","Macao");
			put("MP","Northern Mariana Islands");
			put("MQ","Martinique");
			put("MR","Mauritania");
			put("MS","Montserrat");
			put("MT","Malta");
			put("MU","Mauritius");
			put("MV","Maldives");
			put("MW","Malawi");
			put("MX","Mexico");
			put("MY","Malaysia");
			put("MZ","Mozambique");
			put("NA","Namibia");
			put("NC","New Caledonia");
			put("NE","Niger");
			put("NF","Norfolk Island");
			put("NG","Nigeria");
			put("NI","Nicaragua");
			put("NL","Netherlands");
			put("NO","Norway");
			put("NP","Nepal");
			put("NR","Nauru");
			put("NU","Niue");
			put("NZ","New Zealand");
			put("OM","Oman");
			put("PA","Panama");
			put("PE","Peru");
			put("PF","French Polynesia");
			put("PG","Papua New Guinea");
			put("PH","Philippines");
			put("PK","Pakistan");
			put("PL","Poland");
			put("PM","Saint Pierre And Miquelon");
			put("PN","Pitcairn");
			put("PR","Puerto Rico");
			put("PS","Palestinian Territory, Occupied");
			put("PT","Portugal");
			put("PW","Palau");
			put("PY","Paraguay");
			put("QA","Qatar");
			put("RE","Reunion Island");
			put("RO","Romania");
			put("RS","Serbia");
			put("RU","Russian Federation");
			put("RW","Rwanda");
			put("SA","Saudi Arabia");
			put("SB","Solomon Islands");
			put("SC","Seychelles");
			put("SD","Sudan");
			put("SE","Sweden");
			put("SG","Singapore");
			put("SH","Saint Helena, Ascension And Tristan da Cunha");
			put("SI","Slovenia");
			put("SJ","Svalbard And Jan Mayen Islands");
			put("SK","Slovakia");
			put("SL","Sierra Leone");
			put("SM","San Marino");
			put("SN","Senegal");
			put("SO","Somalia");
			put("SR","Suriname");
			put("SS","South Sudan");
			put("ST","Sao Tome And Principe");
			put("SV","El Salvador");
			put("SX","Sint Maarten (Dutch Part)");
			put("SY","Syrian Arab Republic");
			put("SZ","Swaziland");
			put("TC","Turks And Caicos Islands");
			put("TD","Chad");
			put("TF","French Southern Territories");
			put("TG","Togo");
			put("TH","Thailand");
			put("TJ","Tajikistan");
			put("TK","Tokelau");
			put("TL","Timor-Leste");
			put("TM","Turkmenistan");
			put("TN","Tunisia");
			put("TO","Tonga");
			put("TR","Turkey");
			put("TT","Trinidad And Tobago");
			put("TV","Tuvalu");
			put("TW","Taiwan, Province Of China");
			put("TZ","Tanzania, United Republic Of");
			put("UA","Ukraine");
			put("UG","Uganda");
			put("UM","United States Minor Outlying Islands");
			put("US","United States");
			put("UY","Uruguay");
			put("UZ","Uzbekistan");
			put("VA","Vatican City State");
			put("VC","Saint Vincent And The Grenadines");
			put("VE","Venezuela, Bolivarian Republic Of");
			put("VG","Virgin Islands, British");
			put("VI","Virgin Islands, U.S.");
			put("VN","Viet Nam");
			put("VU","Vanuatu");
			put("WF","Wallis And Futuna");
			put("WS","Samoa");
			put("XX","Not categorised");
			put("YE","Yemen");
			put("YT","Mayotte");
			put("ZA","South Africa");
			put("ZM","Zambia");
			put("ZW","Zimbabwe");
			put("ZZ","Others");

		}
	};

	public static Map<String,String> registryTransaporttype = new LinkedHashMap<String,String>(){
		private static final long serialVersionUID = 1L;

		{
			put("Bike", "Bike");
			put("Car", "Car");
			put("Taxi-Hailing App", "Taxi-Hailing App");
			put("Auto", "Auto");
			put("Bus", "Bus");
			put("Shuttle", "Shuttle");
			put("Train", "Train");
		}
	};

	public static Map<String,String> registryOfficeRole = new LinkedHashMap<String,String>(){
		private static final long serialVersionUID = 1L;

		{
			put("Officer", "Officer");
			put("Executive", "Executive");
			put("Manager", "Manager");
			put("Management", "Management");
			put("Others", "Others");
		}
	};

	public static Map<String,String> registryInvestmentype = new LinkedHashMap<String,String>(){
		private static final long serialVersionUID = 1L;

		{
			put("SIPT", "SIP based on your target amount");
			put("SIPM", "SIP based contribution");
			put("TARGET_PLAN", "Target Plan");
		}
	};


	public static Map<String,String> amchouseicon = new LinkedHashMap<String,String>(){
		private static final long serialVersionUID = 1L;

		{
			put("BirlaSunLifeMutualFund_MF","aditya-birla.jpg");
			put("ICICIPrudentialMutualFund_MF","icici-prudential.jpg");
			put("AXISMUTUALFUND_MF","Axis-Mutual-Fund.png");
			put("BARODAMUTUALFUND_MF","baroda-logo.png");
			put("BNPPARIBAS_MF","bnp-logo.jpg");
			put("BHARTIAXAMUTUALFUND_MF","boi-axa.png");
			put("DHFLPRAMERICAMUTUALFUND_MF","dhfl.png");
			put("DSP_MF","dspmf_logo.png");
			put("EDELWEISSMUTUALFUND_MF","edelweisspng.png");
			put("ESSELMUTUALFUND_MF","essel.png");
			put("FRANKLINTEMPLETON","franklin-templeton.png");
			put("HDFCMutualFund_MF","hdfc.png");
			put("IDBIMUTUALFUND_MF","idbi.png");
			put("INDIABULLSMUTUALFUND_MF","indiabulls.jpg");
			put("INVESCOMUTUALFUND_MF","invesco.png");
			put("KOTAKMAHINDRAMF","kotak.jpg");
			put("L&TMUTUALFUND_MF","landt.png");
			put("LICMUTUALFUND_MF","lic.jpg");
			put("MAHINDRA MUTUAL FUND_MF","mahindra.jpg");
			put("MIRAEASSET","mirae.jpg");
			put("MOTILALOSWAL_MF","motilal.jpeg");
			put("PPFAS_MF","ppfas-logo.png");
			put("QUANTUMMUTUALFUND_MF","quantum.png");
			put("RelianceMutualFund_MF","Reliance-mf.png");
			put("SBIMutualFund_MF","sbi-mutual-funds.png");
			put("SUNDARAMMUTUALFUND_MF","sundaram.png");
			put("TATAMutualFund_MF","tata.png");
			put("UTIMUTUALFUND_MF","uti.png");
			put("IDFCMUTUALFUND_MF","idfc.jpg");
			put("JM FINANCIAL MUTUAL FUND_MF","jm-financial.jpg");
			put("BNPSUNDARAMPARIBAS_MF","sundaram-bnp-paribas.jpg");
			put("NipponIndiaMutualFund_MF","nippon-india.jpg");
		}
	};



}
