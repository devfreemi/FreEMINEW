package com.freemi.services.partners.Impl;

import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.crypto.provider.SunJCE;

import sun.misc.BASE64Encoder;

public class BirlaCheckSumGeneration {
	public static String getEncodedSaltValue(String dateTimeString, String saltValue, String checkSum)
	        throws Exception {

	    List<Integer> indexList = getStringSubSequence(dateTimeString, 2);
	    int strSubSequenceLen = indexList.size();
	    StringBuilder key = new StringBuilder();
	    int len = checkSum.length();
	    for (Integer i : indexList)
	    {
	        if(len > i) {
	            if(i % 2 == 0) {
	                key.append(checkSum.charAt(i));
	            } else {
	                key.append(checkSum.charAt(len - 1 - i));
	            }

	        } else {
	            i %= len;
	            key.append(checkSum.charAt(i));
	        }
	    }

	    return key.toString().substring(0, (strSubSequenceLen / 2)) + saltValue + key.toString().substring(strSubSequenceLen / 2);
	}

	private static List<Integer> getStringSubSequence(String dateTimeStr, int jumpStep)
	{
	    List<Integer> indexList = new ArrayList<>();

	    for (int i = jumpStep-1; i < dateTimeStr.length(); i += jumpStep) {
	        indexList.add(Character.getNumericValue(dateTimeStr.charAt(i)));
	    }
	    return indexList;
	}


	public static String encrypt(String adharNo, String passwd, String encodedSaltValue, String ivValue) throws Exception {
	    Cipher c = getCipher(Cipher.ENCRYPT_MODE, passwd, encodedSaltValue, ivValue);

	    byte[] encryptedVal = c.doFinal(adharNo.getBytes("UTF-8"));
	    return new BASE64Encoder().encode(encryptedVal);
	}

	private static Cipher getCipher(int mode, String passwd, String encodedSaltValue, String ivValue) throws Exception {
	    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding", new SunJCE());

	    byte[] iv = ivValue.getBytes("UTF-8");

	    c.init(mode, generateKey(passwd, encodedSaltValue), new IvParameterSpec(iv));
	    return c;
	}

	/*private static String decrypt(String encrypted, String passwd, String encodedSaltValue, String ivValue) throws Exception {

	    byte[] decodedValue = new BASE64Decoder().decodeBuffer(encrypted);

	    Cipher c = getCipher(Cipher.DECRYPT_MODE, passwd, encodedSaltValue, ivValue);
	    byte[] decValue = c.doFinal(decodedValue);

	    return new String(decValue);
	}*/

	private static SecretKeySpec generateKey(String passwd, String encodedSaltValue) throws Exception {
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    char[] password = passwd.toCharArray();
	    byte[] salt = encodedSaltValue.getBytes("UTF-8");

	    KeySpec spec = new PBEKeySpec(password, salt, 1000, 128);
	    SecretKey tmp = factory.generateSecret(spec);
	    byte[] encoded = tmp.getEncoded();
	    return new SecretKeySpec(encoded, "AES");

	}
	
	/*
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
		LocalDateTime date = LocalDateTime.now();
		System.out.println("Date- "+date.format(formatter));
		
		String inputString = "ARN-141396";

		String saltValue = "RN1DF139-8B0B-018A-B27C-B141EC7BC396";
		String saltedvalue="";
		try {
			saltedvalue = getEncodedSaltValue(date.format(formatter), saltValue, inputString);
			System.out.println(saltedvalue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
		LocalDateTime date1 = LocalDateTime.now();
		System.out.println(date.format(formatter).toString());
		String checksum = "";
		
		try {
			 checksum = encrypt(inputString, "Pass@w3rd96", saltedvalue, "e141f413e018f396");
			System.out.println(checksum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject json = new JSONObject();
		try {
			json.put("UserId", "ARN-141396");
			json.put("Password", "MTQxMzk2");
			json.put("State", "West Bengal");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(json.toString());
		
		
		JSONObject json1 = new JSONObject();
		try {
			json1.put("StateCityRequest", json);
			System.out.println(json1.toString());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BirlaConnectorInterfaces birlaCon = new BirlaConnectorsImpl();
		ResponseEntity<String> response =null;
		ObjectMapper mapper = new ObjectMapper();
		OauthAccessToken token = new OauthAccessToken();
		response= null;
		 try {
			token = mapper.readValue(response.getBody(), OauthAccessToken.class);
			System.out.println(token.getExpires_in());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+"f84a8918-0f08-3896-96d9-e7cbad377d39");
		headers.set("CheckSum",checksum);
		headers.set("DateTimeStamp", date.format(formatter).toString());
		headers.set("COntent-type", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<String>(json1.toString(), headers);
		System.out.println("Start call...");
		try{
		ResponseEntity<String> response1=restTemplate.postForEntity("https://mfapiuat.birlasunlife.com/GetStateCityList/1.0.0", entity,  String.class);
		System.out.println(response1.getBody());
		
		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		JSONObject json5 = new JSONObject();
		
		json5.put("isExistingInvestor", "Y");
		json5.put("NameAsPerPAN", "TARUN K  POOJARY");
		json5.put("PANNumber", "AACPP0404B");
		json5.put("ReturnCode", "1");
		json5.put("ReturnMsg", "Record retrieved successfully");
		json5.put("isFATCAVerified", "N.A.");
		json5.put("IsEKYCVerified", "Y");
		json5.put("xyz", "sdfsd");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}*/
}
