package com.freemi.common.util;

import java.util.Random;

public class PasswordGenerator {

	public static String  generatePassword(){

		int letterleftLimit = 97; // letter 'A'
		int letterrightLimit = 122; // letter 'z'
		int digitleftLimit = 48; // letter '0'
		int digitrightLimit = 57; // letter '9'
		/*int specialleftLimit = 65; // letter 'a'
		int specialrightLimit = 122; // letter 'z'
*/		
		int lettertargetStringLength = 7;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(lettertargetStringLength);
		for (int i = 0; i < lettertargetStringLength; i++) {
			int randomLimitedInt = letterleftLimit + (int) 
					(random.nextFloat() * (letterrightLimit - letterleftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}

		//	    choose random digit
		int randomLimitedInt = digitleftLimit + (int) (random.nextFloat() * (digitrightLimit - digitleftLimit + 1));
		buffer.append((char) randomLimitedInt);

		String generatedString = buffer.toString();


//		System.out.println(generatedString);
		return generatedString;
	}
/*
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			PasswordGenerator.generatePassword();
		}

	}
*/
}
