package com.freemi.entity.investment;

import java.util.ArrayList;
import java.util.Date;

public class RazorpayPayObj {
	
	private String payId;
	private float amount;
	private String captureStatus;
	private String paymentId;
	private Date captureTimeStamp;
	private String captureCurrency;
	private ArrayList<RoutePay> routepay = new ArrayList<RoutePay>();
	
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCaptureStatus() {
		return captureStatus;
	}

	public void setCaptureStatus(String captureStatus) {
		this.captureStatus = captureStatus;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public Date getCaptureTimeStamp() {
		return captureTimeStamp;
	}
	public void setCaptureTimeStamp(Date captureTimeStamp) {
		this.captureTimeStamp = captureTimeStamp;
	}
	public String getCaptureCurrency() {
		return captureCurrency;
	}
	public void setCaptureCurrency(String captureCurrency) {
		this.captureCurrency = captureCurrency;
	}
	public ArrayList<RoutePay> getRoutepay() {
		return routepay;
	}
	public void setRoutepay(ArrayList<RoutePay> routepay) {
		this.routepay = routepay;
	}
	public static class RoutePay{
		private String amcHouse;
		private String amcId;
		private float routeAmout;
		private String razorpayAccountId;
		private String transferId;
		private String CreateResponseTime;
		private String source;
		private String routeCurrency;
		private String responseTransferId;
		private String transactionStatus;
		
		public String getAmcHouse() {
			return amcHouse;
		}
		public void setAmcHouse(String amcHouse) {
			this.amcHouse = amcHouse;
		}
		public String getAmcId() {
			return amcId;
		}
		public void setAmcId(String amcId) {
			this.amcId = amcId;
		}
		public float getRouteAmout() {
			return routeAmout;
		}
		public void setRouteAmout(float routeAmout) {
			this.routeAmout = routeAmout;
		}
		public String getRazorpayAccountId() {
			return razorpayAccountId;
		}
		public void setRazorpayAccountId(String razorpayAccountId) {
			this.razorpayAccountId = razorpayAccountId;
		}
		public String getTransferId() {
			return transferId;
		}
		public void setTransferId(String transferId) {
			this.transferId = transferId;
		}
		public String getCreateResponseTime() {
			return CreateResponseTime;
		}
		public void setCreateResponseTime(String createResponseTime) {
			CreateResponseTime = createResponseTime;
		}
		public String getRouteCurrency() {
			return routeCurrency;
		}
		public void setRouteCurrency(String routeCurrency) {
			this.routeCurrency = routeCurrency;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getResponseTransferId() {
			return responseTransferId;
		}
		public void setResponseTransferId(String responseTransferId) {
			this.responseTransferId = responseTransferId;
		}
		public String getTransactionStatus() {
			return transactionStatus;
		}
		public void setTransactionStatus(String transactionStatus) {
			this.transactionStatus = transactionStatus;
		}
		
		
	}

}
