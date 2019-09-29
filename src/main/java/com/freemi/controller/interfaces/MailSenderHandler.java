package com.freemi.controller.interfaces;

import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.SelectMFFund;

@Service
public interface MailSenderHandler {

	public void mfpurchasenotofication(SelectMFFund selectedFund, BseMFInvestForm userDetails, String transactionType) throws InterruptedException;
	public void loginOTPMail(String userid, String otp,String email, String validityTimeinMin) throws InterruptedException;
	public void sendMFRegisterNotification(String mailType, String customerName, String mobile, String mailId, String bseClientID,String pan, String kycStatus);
	
}
