package com.freemi.ui.config;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.freemi.common.util.CommonConstants;
import com.freemi.common.util.CommonTask;
import com.freemi.controller.interfaces.ProfileRestClientService;
import com.freemi.ui.restclient.RestClient;

public class RequestInceptorCustom extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(RequestInceptorCustom.class);

	@Autowired
	ProfileRestClientService profileRestClientService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/*
		 * // set few parameters to handle ajax request from different host
		 * response.addHeader("Access-Control-Allow-Origin", "*");
		 * response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		 * response.addHeader("Access-Control-Max-Age", "1000");
		 * response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		 * response.addHeader("Cache-Control", "private");
		 */

		logger.debug("RequestInceptorCustom- preHandle(): Validating request received for url -" + request.getRequestURI());

		String reqUri = request.getRequestURI();
		/*
		 * String serviceName = reqUri.substring(reqUri.lastIndexOf("/") + 1,
		 * reqUri.length()); if (serviceName.equals("SOMETHING")) {
		 * 
		 * }
		 */

		if (CommonConstants.protectedUrl.contains(reqUri)) {
			logger.info("Accessing protected URL. Validate session.." + request.getRequestURI());
			HttpSession session = request.getSession();
			if (session.getAttribute("token") == null) {
				logger.debug("pre-handle- User sesssion not found. Rejecting access");
				logger.debug("Context path- " + request.getContextPath());
				response.sendRedirect(request.getContextPath() + "/login?ref="+ URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString()));
				return false;
			} else {
				logger.info("preHandle(): Validate session token.");

				try {
					ResponseEntity<String> apiresponse = profileRestClientService.validateUserToken(
							session.getAttribute("userid") != null ? session.getAttribute("userid").toString()
									: "BLANK",
							session.getAttribute("token").toString(), CommonTask.getClientSystemIp(request));
					logger.info("RESPONSE- " + apiresponse.getBody());
					if (apiresponse.getBody().equals("VALID")) {
						return true;
					} else if (apiresponse.getBody().equals("EXPIRED")) {
						logger.info("Session has expired. Clear current sessions and relogin..");
						session.invalidate();
						response.sendRedirect(request.getContextPath() + "/login?ref=" + URLEncoder
								.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8.toString()));
						return false;

					} else {
						logger.info("Session mismtach or invalid.. Not allowing to access.");
						response.sendRedirect(request.getContextPath());
						return false;
					}
				} catch (Exception e) {
					logger.error("RequestInceptorCustom- preHandle(): Failed to validate session..", e);
					response.sendRedirect(request.getContextPath());
					return false;
				}
			}
		}

//        return super.preHandle(request, response, handler);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try {

			String logged = request.getAttribute("LOGGED") != null ? request.getAttribute("LOGGED").toString() : "NULL";
			if (logged != null && logged.equalsIgnoreCase("FALSE")) {
				modelAndView.setViewName("redirect:/login");
			}
			if (modelAndView != null) {
				logger.info("[postHandle]"  + modelAndView.getViewName());
			} else {
				logger.info("[postHandle][modelAndviwe is null]");
			}
		} catch (NullPointerException e) {
			logger.error("attribute null");
		}

	}

	/*
	 * @Override public void afterCompletion( HttpServletRequest request,
	 * HttpServletResponse response, Object handler, Exception ex) {
	 * System.out.println("Called after laoding view submitted...");
	 * 
	 * try { Thread.sleep(10000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * System.out.println("Completed..."); }
	 */

}
