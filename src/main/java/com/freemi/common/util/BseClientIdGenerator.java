package com.freemi.common.util;

public class BseClientIdGenerator {
	
	public static String generateID(String name, String pan, String dob, String mobile, int loopVal){
		// BSE requires a 10 digit based client ID to be maintained
		
		String id="";
		int nameLenth=0;
		int panLength=0;
		int mobileLength=0;
		
		StringBuffer nameSub = new StringBuffer(name.replaceAll(" ", ""));
		if(nameSub.length()<=2){
			id= (
					nameSub.substring(0,nameSub.length())
					+
					pan.substring(6)
					+
					mobile.substring(6)
					)
					.toUpperCase();
		}else{
			id= (
					nameSub.substring(0,3)
					+
					pan.substring(6)
					+
					mobile.substring(8)
					+
					loopVal
					)
					.toUpperCase();
					
		}
		
		
		return id;
		
	}

}
