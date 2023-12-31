package com.freemi.services.interfaces;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.database.ContactUsForm;
import com.freemi.entity.database.FSecure;
import com.freemi.entity.general.ClientSystemDetails;
import com.freemi.entity.general.ForgotPassword;
import com.freemi.entity.general.HttpClientResponse;
import com.freemi.entity.general.Login;
import com.freemi.entity.general.ProfilePasswordChangeForm;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.general.ResetPassword;
import com.freemi.entity.general.UserProfile;
import com.freemi.entity.general.UserProfileLdap;

@Component
public interface ProfileRestClientService {
	
	public ResponseEntity<String> login(String userid, String password, String ip);
	
	public HttpClientResponse validateusersession(HttpServletRequest request, HttpSession session, String requestingmobile, String requestingemail, String pan, String token,String requestingip, boolean registeredvalue);
	
	public ResponseEntity<String> validateuserIdAndGetMail(String userId) throws JsonProcessingException;
	
	public HttpClientResponse registerUser(Registerform registerForm, ClientSystemDetails systemDetails);
	
	public ResponseEntity<String> otpLogin(Login loginForm, String ip) throws JsonProcessingException;
	
	public ResponseEntity<String> contactUs(ContactUsForm contactUsData) throws JsonProcessingException;
	
	public ResponseEntity<String> campaignSingUp(CampaignSignupForm campaignSignUpForm) throws JsonProcessingException;
	
	public ResponseEntity<String> fsecureRequest(FSecure contactUsData,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> forgotPassword(ForgotPassword forgotPasswordForm) throws JsonProcessingException;
	
	public UserProfileLdap getProfileData(String userid, String token, String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> updateProfileData(UserProfile profileData,String userid, String token, String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> updateProfilePassword(ProfilePasswordChangeForm passchangeForm,String userid, String token,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> forgotPasswordUpdate(ResetPassword forgotPasswordChangeForm,String userid, String token,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> validateResetPasswordToken(String userid, String token,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> extractdatafromtoken(String userid, String token,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> isUserExisitng(String mobile) throws JsonProcessingException;
	
	public ResponseEntity<String> isEmailExisitng(String email) throws JsonProcessingException;
	public ResponseEntity<String> isEmailExisitngforothers(String mobile, String email) throws JsonProcessingException;
	
	public String isPanExisitngForOthers(String mobile, String pan);
	
	public ResponseEntity<String> validateUserToken(String userid, String token,String requestingIp);
	
	public ResponseEntity<String> linkmfaccountDetails(String mobile, String pan,String bseclientId);
	
	public ResponseEntity<String> updateprofiledetails(UserProfile profiledetails);
	
	

}
