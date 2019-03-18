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

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class CheckSumGeneration {
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

	private static String decrypt(String encrypted, String passwd, String encodedSaltValue, String ivValue) throws Exception {

	    byte[] decodedValue = new BASE64Decoder().decodeBuffer(encrypted);

	    Cipher c = getCipher(Cipher.DECRYPT_MODE, passwd, encodedSaltValue, ivValue);
	    byte[] decValue = c.doFinal(decodedValue);

	    return new String(decValue);
	}

	private static SecretKeySpec generateKey(String passwd, String encodedSaltValue) throws Exception {
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    char[] password = passwd.toCharArray();
	    byte[] salt = encodedSaltValue.getBytes("UTF-8");

	    KeySpec spec = new PBEKeySpec(password, salt, 1000, 128);
	    SecretKey tmp = factory.generateSecret(spec);
	    byte[] encoded = tmp.getEncoded();
	    return new SecretKeySpec(encoded, "AES");

	}
	
/*	public static void main(String[] args) {
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
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer 5d1f7346-db59-32ed-bf72-0575f0b274eb");
		headers.set("CheckSum",checksum);
		headers.set("DateTimestamp", date.format(formatter).toString());
		headers.set("Content-type", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<String>(json1.toString(), headers);
		System.out.println("Start call...");
		try{
		ResponseEntity<String> response=	restTemplate.postForEntity("https://mfapiuat.birlasunlife.com/GetStateCityList/1.0.0", entity,  String.class);
		System.out.println(response.getBody());
		}catch(HttpStatusCodeException  e){
			System.out.println("test failure - " + e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			System.out.println(e.getResponseHeaders());
			System.out.println(e.getRawStatusCode());
			System.out.println(e.getStatusText());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		JSONObject json2 = new JSONObject();
		JSONObject json3 = new JSONObject();
		try {
			json2.put("ActivatedAmount", "ActivatedAmount");
			json2.put("DefaultFlag", "DefaultFlag");
			json2.put("EmailID", "EmailID");
			json3.put("Folios", json2);
			System.out.println(json3);
			
			Gson gson = new Gson();
			String res = "{ \"ValidatePANResult\": { \"Folios\": [ { \"ActivatedAmount\": \"N.A\", \"DefaultFlag\": \"Y\", \"EmailID\": \"tarunkp2@gmail.com\", \"Folio_No\": \"1016779822\", \"InvestorName\": \"TARUN K POOJARY\", \"LiquidFlag\": \"YES\", \"MAXDATE\": \"03-OCT-16\", \"MobileNumber\": \"+919702903111\", \"PANNo\": null } ], \"IsEKYCVerified\": \"Y\", \"IsExistingInvestor\": \"Y\", \"NameAsPerPAN\": \"TARUN K POOJARY\", \"PANNumber\": \"AACPP0404B\", \"ReturnCode\": \"1\", \"ReturnMsg\": \"Record retrieved successfully\", \"UDP\": \"\", \"isFATCAVerified\": \"N.A.\" } }";
//			Folios f = gson.fromJson(res, Folios.class);
//			PANDetails r = gson.fromJson(res, PANDetails.class);
			ObjectMapper mapper = new ObjectMapper();
			PANDetails r =  new ObjectMapper().readValue(res, PANDetails.class);
			
			System.out.println(r.getValidatePANResult().getIsExistingInvestor());
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}*/
}
