package com.freemi.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.freemi.entity.investment.MFKarvyValueByCategory2;

public class MfBalanceCalculator {
	
	public static void karvyBalanceCalculator(List<MFKarvyValueByCategory2> karvyFunds) {
		MFKarvyValueByCategory2 fund;
		Double balanceAmount= 0.0;
		Double balanceUnit= 0.0;
		String folio="";
		String productCode="";
		folio = karvyFunds.get(0).getFolioNo();
		productCode =karvyFunds.get(0).getProductCode();
		
		List<String> addCategory = Arrays.asList("SIN","SINR","NEW","NEWR","SWIA","LTIN","ADD");
		List<String> minusCategory = Arrays.asList("SWOF","FUL","RED");
		
		for(int j=0;j<karvyFunds.size();j++){
			if(!folio.equalsIgnoreCase(karvyFunds.get(j).getFolioNo()) && !productCode.equalsIgnoreCase(karvyFunds.get(j).getProductCode())) {
				System.out.println("Calculate for Product Code- "+ productCode);
				folio = karvyFunds.get(j).getFolioNo();
				productCode =karvyFunds.get(j).getProductCode();
			}
			System.out.println("Type- "+ karvyFunds.get(j).getTrasanctionType()+  "Amount " + karvyFunds.get(j).getInvAmount() );
//			if(folio.equalsIgnoreCase(karvyFunds.get(j).getFolioNo()) && productCode.equalsIgnoreCase(karvyFunds.get(j).getProductCode())) {
				
				if(addCategory.contains(karvyFunds.get(j).getTrasanctionType().toUpperCase())){
					System.out.println("ADD--" );
					balanceAmount += karvyFunds.get(j).getInvAmount();
					balanceUnit += karvyFunds.get(j).getUnits();
				}else if(minusCategory.contains(karvyFunds.get(j).getTrasanctionType().toUpperCase())) {
					
					System.out.println("MINUS --");
					balanceAmount -= karvyFunds.get(j).getInvAmount();
					balanceUnit -= karvyFunds.get(j).getUnits();
					
					if(balanceAmount<0) {
						balanceAmount=0.0;
						System.out.println("balance to become 0 for : "+ balanceAmount);
					}
					if(balanceUnit<0) {
						balanceUnit = 0.0;
					}
				}
				
			/*
			 * }else{ System.out.println("Error "); }
			 */

		}
		
		System.out.println("Final balance calulated- "+ balanceAmount);
		System.out.println("Final units calulated- "+ balanceUnit);
		
	}

}
