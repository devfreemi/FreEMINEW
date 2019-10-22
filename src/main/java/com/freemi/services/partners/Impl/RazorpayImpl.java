package com.freemi.services.partners.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.freemi.entity.investment.RazorpayPayObj;
import com.freemi.services.interfaces.RazorpayPayment;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Transfer;

@Service
public class RazorpayImpl implements RazorpayPayment {
	
	public static final String KEY_ID="rzp_test_EoKq4RCiyXYAgT";
	public static final String KEY_PASS="l9CRWNhUEKZw5hTv8NDaP3HX";
	private static final Logger logger = LogManager.getLogger(RazorpayImpl.class);
	
	@Override
	public boolean captureInitiatedPayment(String payId, int amount, String PAN) {
		
		logger.info("Initiating capturing of the payment for pay id- "+ payId + " : amount- "+ (amount) );
		boolean flag=false;
		try {
			RazorpayClient razorpayClient = new RazorpayClient(KEY_ID, KEY_PASS);
//			logger.debug("Initiating capturing of the payment for pay id- "+ payId + " amount- "+ (amount));

			/*List<Payment> payments = razorpayClient.Payments.fetchAll();
			
			for(int i=0;i<payments.size();i++){
				Payment p = payments.get(i);
				System.out.println(p);
				
			}*/
			
			
			JSONObject options = new JSONObject();
			options.put("amount", (amount*100));	// Exact amount is paisa
			Payment p= razorpayClient.Payments.capture(payId, options);
//			System.out.println("Captured- "+ p);
//			System.out.println(p.get("captured").toString());
			if((p.get("captured").toString().equalsIgnoreCase("true"))){
				logger.info("Capture is successful for pay id- "+ payId + " --> "+ p);
				
				flag=true;
			}
			
			
		} catch (RazorpayException | JSONException e) {
			e.printStackTrace();
			logger.error("Failed to capture for pay id- "+ payId);
			logger.error(e.getMessage());
		}
		return flag;
	}

	@Override
	public String refundFailedPayment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RazorpayPayObj routePayment(RazorpayPayObj razorpay) {
		logger.info("Initiating routre of payment received- "+ "[ ");
		
		RazorpayClient razorpayClient;
		try {
			razorpayClient = new RazorpayClient(KEY_ID, KEY_PASS);
			JSONObject request = new JSONObject();
			request.put("amount", (razorpay.getRoutepay().get(0).getRouteAmout()*100)); // The amount should be in paise.
			request.put("currency", "INR");
			request.put("account", razorpay.getRoutepay().get(0).getRazorpayAccountId());

			Transfer transfer = razorpayClient.Transfers.create(request);
			System.out.println("Route status- "+ transfer);
			logger.info("Status of route- "+ transfer);
			razorpay.getRoutepay().get(0).setCreateResponseTime(transfer.get("created_at"));
			razorpay.getRoutepay().get(0).setTransferId(transfer.get("id"));
			
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return razorpay;
	}
	
/*	public static void main(String[] args){
		RazorpayImpl r = new RazorpayImpl();
//		r.captureInitiatedPayment("pay_AoozNtaPn0s0jK", 230, "");
		
		RazorpayPayObj ob = new RazorpayPayObj();
		RazorpayPayObj.RoutePay route = new RoutePay();
		ArrayList<RazorpayPayObj.RoutePay> routeList = new ArrayList<RazorpayPayObj.RoutePay>();
		
		route.setRouteAmout(107);
		route.setRazorpayAccountId("acc_B5mk3S3di8DNk5");
		routeList.add(route);
		ob.setRoutepay(routeList);
		r.routePayment(ob);
	}*/

}
