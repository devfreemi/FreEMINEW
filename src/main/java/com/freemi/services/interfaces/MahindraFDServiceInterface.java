package com.freemi.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freemi.entity.investment.mahindra.MahindraFDEntity;
import com.freemi.entity.investment.mahindra.MahindraFDMappedLocations;
import com.freemi.entity.investment.mahindra.MahindraFDTransactionStatus;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.entity.investment.mahindra.MahindraSchemesList;

@Service
public interface MahindraFDServiceInterface {
	
	public List<MahindraSchemesList> getSchemesListBasedOnCriteria(String tenure, String schemetypeCode, String customerCategory);
	
	public List<MahindraFDMappedLocations> getAllDistrictFromState(String state);
	
	public MahindraResponse getBankDetailsFromIfsc(String ifscCode);
	
	public String generateTransactionId(String value);
	
	public MahindraFDEntity getRegisteredDetails(MahindraFDEntity fdEntity);
	
	public MahindraResponse saveCustomerDetails(MahindraFDEntity fdEntity, String transactionId);
	
	public MahindraResponse saveCustomerAddress(MahindraFDEntity fdEntity, String customerid);
	
	public MahindraResponse saveBankDetails(MahindraFDEntity fdEntity, String customerid);
	
	public MahindraResponse saveCustomerKYCDetails(MahindraFDEntity fdEntity, String customerid);
	
	public MahindraResponse saveFDPurchaseDetails(MahindraFDEntity fdEntity,String transactionId, String saveDetailsResponseCode, String addressDetailsId, String bankDetailsId, String customerid);
	
	public MahindraResponse getMMFDpaymentLink(String customerId, String mobile, String callbackUrl,String cpTransRefNo, String mfSysRefNo, String applicationNo);
	
	public MahindraResponse verifyPaymentResponse(String customerId, String mobile, String msgkey,  String applicationNo);
	
	public MahindraResponse verifyPaymentStatus(String customerId, String mobile, String msgkey,  String applicationNo);
	
	public List<MahindraFDTransactionStatus> getPurchaseHistory(String mobile);
	
	
}
