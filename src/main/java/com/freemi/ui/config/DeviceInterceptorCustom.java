package com.freemi.ui.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
//public class RequestInterceptor extends HandlerInterceptorAdapter {
public class DeviceInterceptorCustom extends DeviceResolverHandlerInterceptor{
	
	private static final Logger logger = LogManager.getLogger(DeviceInterceptorCustom.class);
	
	private final DeviceResolver deviceResolver;
	
	/**
	 * Create a device resolving {@link HandlerInterceptor} that defaults to a {@link LiteDeviceResolver} implementation.
	 */
	public DeviceInterceptorCustom() {
		this(new LiteDeviceResolver());
	}
	
	public DeviceInterceptorCustom(DeviceResolver deviceresolver) {
		this.deviceResolver = deviceresolver;
	}
	 
	@Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {

	       /* // set few parameters to handle ajax request from different host
	        response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	        response.addHeader("Access-Control-Max-Age", "1000");
	        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.addHeader("Cache-Control", "private");*/
		 	
	        Device device = deviceResolver.resolveDevice(request);
	        	
	        logger.debug("Device- "+ device.isMobile() + " : "+ device.getDevicePlatform());
	        
	        request.setAttribute(DeviceUtils.CURRENT_DEVICE_ATTRIBUTE, device);
	        
	        return super.preHandle(request, response, handler);
	    }
}
