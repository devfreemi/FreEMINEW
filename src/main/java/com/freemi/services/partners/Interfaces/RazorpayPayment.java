package com.freemi.services.partners.Interfaces;

import org.springframework.stereotype.Component;

import com.freemi.entity.investment.RazorpayPayObj;

@Component
public interface RazorpayPayment {
	
	public boolean captureInitiatedPayment(String payId, int amount, String PAN);
	public String refundFailedPayment();
	public RazorpayPayObj routePayment(RazorpayPayObj razorpay);

}
