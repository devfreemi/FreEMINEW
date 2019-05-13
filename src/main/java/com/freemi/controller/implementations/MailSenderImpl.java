package com.freemi.controller.implementations;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.freemi.common.util.CommonConstants;
import com.freemi.entity.general.Mail;
import com.freemi.entity.investment.BseMFInvestForm;
import com.freemi.entity.investment.MFInvestForm;
import com.freemi.entity.investment.SelectMFFund;

import freemarker.template.Configuration;

@Component
//@PropertySource("classpath:app-config.properties")
public class MailSenderImpl implements com.freemi.controller.interfaces.MailSenderHandler {
	private static final Logger logger = LogManager.getLogger(MailSenderImpl.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	@Qualifier("getFreeMarkerConfiguration")
	private Configuration fmConfiguration;


	@Autowired
	Environment env;



	private Mail processMailAddress(InternetAddress address,Map<String, Object> replacementContent, String to, String cc, String subject,String mailName) throws UnsupportedEncodingException{
		Mail mail = new Mail();
		if(replacementContent!=null){
			mail.setModel(replacementContent);
		}
		mail.setFrom(address!=null?(address).toString():(new InternetAddress(env.getProperty(CommonConstants.MAIL_ACCOUNT_ID), "FreEMI")).toString());
		mail.setTo(to);

		if(cc!=null){
			mail.setCc(cc);
		}

		mail.setSubject(subject);
		
		mail.setContent(getContentFromTemplate(mail.getModel(), mailName));

		return mail;
	}

	private void processMailRequest(Mail mail, boolean bccSupport) throws MessagingException{

		logger.info("Begin process to format mail...");
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.toString());	//True indicate we need multipart message
		helper.setFrom(mail.getFrom());
		helper.setTo(mail.getTo());
		if(bccSupport){
			helper.setBcc(env.getProperty("investment.bse.mail.support.account"));
		}
		
		helper.setText(mail.getContent(),true);	//True flag to indicate the text included is HTML
		message.setSubject(mail.getSubject());
		
		sendMail(mail, message);
		logger.info("Mail task complete. Returning");
	}

	public String getContentFromTemplate(Map<String, Object> mailData, String templateName){
		logger.info("Starting processing mail content");
		StringBuffer mailContent = new StringBuffer();
		try{
			mailContent.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(templateName), mailData));
		}catch(Exception e){
			logger.info("Mail template freemarker engine error ", e);
		}

		return mailContent.toString();
	}

	private void sendMail(Mail mail, MimeMessage message){
		try{
			if(env.getProperty(CommonConstants.EMAIL_SEND_ENABLED).equalsIgnoreCase("Y")){
				javaMailSender.send(message);
				logger.info("Mail sent for- "+ mail.getTo().toLowerCase());
			}else{
				logger.info("Mail service is disabled currently. SKipping mail trigger.");
			}
		}catch(Exception e){
			logger.error("Failed to send mail to : "+ mail.getTo()+"\n",e);
		}
	}
	

	@Async
	@Override
	public void mfpurchasenotofication(SelectMFFund selectedFund, BseMFInvestForm userDetails,String transactionCategory) throws InterruptedException {
		logger.info("Request received to process MF transaction mail for client- "+ userDetails.getClientID());
		try{
			if(userDetails.getEmail()!=null){
				InternetAddress address = new InternetAddress(env.getProperty(CommonConstants.MAIL_ACCOUNT_ID), "FreEMI");

				Map<String, Object> replacementContent= new HashMap<String,Object>();
				String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				
				replacementContent.put("investcategory", transactionCategory!=null?transactionCategory:"<error>");
				replacementContent.put("username", userDetails.getInvName()!=null?userDetails.getInvName():"investor");
				replacementContent.put("mffundname", selectedFund.getSchemeName()!=null?selectedFund.getSchemeName():"<Name Missing>");
				replacementContent.put("transactionno", selectedFund.getTransactionID()!=null?selectedFund.getTransactionID():"<blank>");

				replacementContent.put("investtype", selectedFund.getInvestype()!=null?selectedFund.getInvestype():"<blank>");
				replacementContent.put("navdate", date);
				replacementContent.put("amount", selectedFund.getInvestAmount()!=0?selectedFund.getInvestAmount():"<blank>");

				Mail mail = processMailAddress(address, replacementContent, userDetails.getEmail(), null, "Mutual Fund Transaction","mf-transaction.txt");
				
				boolean bccSupport =false;
				if(env.getProperty("investment.bse.mail.support").equalsIgnoreCase("Y")){
					bccSupport = true;
				}
				processMailRequest(mail,bccSupport);

			}else{
				logger.info("Investor mail ID not found. Not proceesing to send transaction mail.");
			}
		}catch(MessagingException exp){
			logger.error("MessageHelper Issue. ",exp);
		} catch (UnsupportedEncodingException e) {
			logger.error("Error setting Internet address.\n", e);
		}

	}

	@Override
	@Async
	public void loginOTPMail(String userid, String otp,String emailId, String validateTime) throws InterruptedException {
		try{
			if(userid!=null){
				InternetAddress address = new InternetAddress(env.getProperty(CommonConstants.MAIL_ACCOUNT_ID), "FreEMI");

				Map<String, Object> replacementContent= new HashMap<String,Object>();
//				String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				
//				replacementContent.put("transactionDate", date);
				replacementContent.put("otp", otp);
				replacementContent.put("validityTime", validateTime);

				Mail mail = processMailAddress(address, replacementContent, emailId, null, otp+ " is your OTP to login to your FreEMI Account","otp-login-mail.txt");
				
				processMailRequest(mail,false);

			}else{
				logger.info("Investor mail ID not found. Not proceesing to send OTP mail.");
			}
		}catch(MessagingException exp){
			logger.error("loginOTPMail(): MessageHelper Issue. ",exp);
		} catch (UnsupportedEncodingException e) {
			logger.error("loginOTPMail(): Error setting Internet address.\n", e);
		}
		
	}







}
