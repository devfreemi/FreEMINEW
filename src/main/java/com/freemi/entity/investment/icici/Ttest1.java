package com.freemi.entity.investment.icici;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ttest1 {


	public static void testMethod(){
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OccupationList l = null;
		ArrayList<OccupationList> l1 = null;
		IciciCommonStatusOutput c =null;
		String str = "[{\"OCC_CODE\":\"4\",\"OCC_NAME\":\"Agriculturist\"},{\"OCC_CODE\":\"1\",\"OCC_NAME\":\"Business\"},{\"OCC_CODE\":\"43\",\"OCC_NAME\":\"Forex Dealer\"},{\"OCC_CODE\":\"6\",\"OCC_NAME\":\"Housewife\"},{\"OCC_CODE\":\"8\",\"OCC_NAME\":\"Others\"},{\"OCC_CODE\":\"41\",\"OCC_NAME\":\"Private Sector Service\"},{\"OCC_CODE\":\"3\",\"OCC_NAME\":\"Professional\"},{\"OCC_CODE\":\"9\",\"OCC_NAME\":\"Public Sector / Government Service\"},{\"OCC_CODE\":\"5\",\"OCC_NAME\":\"Retired\"},{\"OCC_CODE\":\"2\",\"OCC_NAME\":\"Service\"},{\"OCC_CODE\":\"7\",\"OCC_NAME\":\"Student\"}]";
		String str1="{\"STATUS\":\"1\"}";
		try {
			//					OccupationList[] obj = mapper.readValue(str, OccupationList[].class);

			IciciOccupationListOutput[] obj2 = mapper.readValue(str, IciciOccupationListOutput[].class);
			//					c= mapper.readValue(str, IciciCommonStatusOutput.class);
			System.out.println(obj2[0].getOcc_code());


			/*		Object j= new JsonParser().parse(str1);
					JsonObject ob = (JsonObject) j;
					System.out.println("code- "+ ob.get("OCC_CODE"));*/
			//					System.out.println("Status- "+c.getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	public static void main(String[] args) {
		Ttest1.testMethod();
	}*/

}
