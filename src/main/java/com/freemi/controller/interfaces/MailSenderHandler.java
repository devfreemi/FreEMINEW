package com.freemi.controller.interfaces;

import org.springframework.stereotype.Service;

import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.SelectMFFund;

@Service
public interface MailSenderHandler {

	public void mfpurchasenotofication(SelectMFFund selectedFund, BseMFInvestForm userDetails) throws InterruptedException;
	public void loginOTPMail(String userid, String otp,String email, String validityTimeinMin) throws InterruptedException;
	
}
