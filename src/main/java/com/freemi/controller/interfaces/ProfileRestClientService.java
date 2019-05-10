package com.freemi.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.freemi.entity.database.CampaignSignupForm;
import com.freemi.entity.general.ContactUsForm;
import com.freemi.entity.general.FSecure;
import com.freemi.entity.general.ForgotPassword;
import com.freemi.entity.general.Login;
import com.freemi.entity.general.ProfilePasswordChangeForm;
import com.freemi.entity.general.Registerform;
import com.freemi.entity.general.ResetPassword;
import com.freemi.entity.general.UserProfile;

@Component
public interface ProfileRestClientService {
	
	public ResponseEntity<String> login(String userid, String password, String ip);
	
	public ResponseEntity<String> validateuserIdAndGetMail(String userId) throws JsonProcessingException;
	
	public ResponseEntity<String> registerUser(Registerform registerForm) throws JsonProcessingException;
	
	public ResponseEntity<String> otpLogin(Login loginForm) throws JsonProcessingException;
	
	public ResponseEntity<String> contactUs(ContactUsForm contactUsData) throws JsonProcessingException;
	
	public ResponseEntity<String> campaignSingUp(CampaignSignupForm campaignSignUpForm) throws JsonProcessingException;
	
	public ResponseEntity<String> fsecureRequest(FSecure contactUsData,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> forgotPassword(ForgotPassword forgotPasswordForm) throws JsonProcessingException;
	
	public ResponseEntity<String> getProfileData(String userid, String token, String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> updateProfileData(UserProfile profileData,String userid, String token, String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> updateProfilePassword(ProfilePasswordChangeForm passchangeForm,String userid, String token,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> forgotPasswordUpdate(ResetPassword forgotPasswordChangeForm,String userid, String token,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> validateResetPasswordToken(String userid, String token,String requestingIp) throws JsonProcessingException;
	
	public ResponseEntity<String> isUserExisitng(String mobile) throws JsonProcessingException;
	
	

}
