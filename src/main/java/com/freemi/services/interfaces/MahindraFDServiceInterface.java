package com.freemi.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.freemi.entity.investment.mahindra.MahindraFDEntity;
import com.freemi.entity.investment.mahindra.MahindraFDMappedLocations;
import com.freemi.entity.investment.mahindra.MahindraFDRenewalEntity;
import com.freemi.entity.investment.mahindra.Mahindrapurchasehistory;
import com.freemi.entity.investment.mahindra.MahindraResponse;
import com.freemi.entity.investment.mahindra.MahindraSchemesList;

@Service
public interface MahindraFDServiceInterface {
	
	public String generateCpTransctionId();
	
	public List<MahindraSchemesList> getSchemesListBasedOnCriteria(String tenure, String schemetypeCode, String customerCategory);
	
	public List<MahindraFDMappedLocations> getAllDistrictFromState(String state);
	
	public MahindraResponse getBankDetailsFromIfsc(String ifscCode);
	
	public String generateTransactionId(String value);
	
	public MahindraFDEntity getRegisteredDetails(MahindraFDEntity fdEntity);
	
	public MahindraResponse saveCustomerDetails(MahindraFDEntity fdEntity, String transactionId);
	
	public MahindraResponse saveCustomerAddress(MahindraFDEntity fdEntity, String customerid);
	
	public MahindraResponse saveCustomerNominee(MahindraFDEntity fdEntity, String customerid);
	
	public MahindraResponse saveBankDetails(MahindraFDEntity fdEntity, String customerid);
	
	public MahindraResponse saveCustomerKYCDetails(MahindraFDEntity fdEntity, String customerid);
	
	public MahindraResponse saveOtherCountryTaxDetails(MahindraFDEntity fdEntity, String customerid);
	
	public MahindraResponse saveFDPurchaseDetails(MahindraFDEntity fdEntity,String transactionId, String saveDetailsResponseCode, String addressDetailsId, String bankDetailsId,String nomineemapid, String customerid, String cptransactionId);
	
	public MahindraResponse getMMFDpaymentLink(String customerId, String mobile, String callbackUrl,String cpTransRefNo, String mfSysRefNo, String applicationNo);
	
	public MahindraResponse verifyPaymentResponse(String customerId, String mobile, String msgkey,  String applicationNo);
	
	public MahindraResponse verifyPaymentStatus(String customerId, String mobile,  String applicationNo, String emailid);
	
	public List<Mahindrapurchasehistory> getPurchaseHistory(String mobile);
	
	public MahindraResponse retryDocumentupload(String mobile, String customerid,String applicationno);
	
	public MahindraResponse mahindrauploadDocument(String mobile, String customerid,MahindraFDEntity fdEntity, String cprefno,String mfrefno,String applno);
	
	public void mailAcknowledmentDocumentPostSuccessApplication(String applicationno, String customerid, String mobile, String email, String name);
	
	public MahindraFDRenewalEntity getFDRenewalDetails(String customerid, String mobile, String applicationno);
	
}
