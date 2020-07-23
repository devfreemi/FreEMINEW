package com.freemi.services.interfaces;

import org.springframework.stereotype.Service;

import com.freemi.entity.database.EmailBounceReport;
import com.freemi.entity.investment.MFCustomers;
import com.freemi.entity.investment.MFinitiatedTrasactions;
import com.freemi.entity.investment.SelectMFFund;

@Service
public interface MailSenderInterface {

	public void mfpurchasenotofication(SelectMFFund selectedFund, MFCustomers userDetails, String transactionType) throws InterruptedException;
	public void loginOTPMail(String userid, String otp,String email, String validityTimeinMin) throws InterruptedException;
	public void sendMFRegisterNotification(String mailType, String customerName, String mobile, String mailId, String bseClientID,String pan, String kycStatus);
	
	public void sendMFInitiatedNotice(MFinitiatedTrasactions initiatedData);
	
	public void sendMahindraFDAcknowledgementdocument(String binarydoc, String emailid, String mobile, String customername, String applicationno,int amount, String nbfcname,String schemecategory);
	
	
	public void notifyTransactionErrorToAdmin(Object data1, String message, String requesteddata, String errorType, String subcategory, String customerid, String name);
	
	public void emailbouncereportcapture(EmailBounceReport bouncereport);
	
}
