package com.freemi.ui.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.freemi.common.util.CommonConstants;

import freemarker.template.TemplateException;

@Configuration
@Component
public class ApplicationConfig {
	private static final Logger logger = LogManager.getLogger(ApplicationConfig.class);
	
	@Autowired
	private Environment env;
	
	@Bean("javaMailSender")
    public JavaMailSender javaMailSender(){
    	logger.info("Loading mail config...");
    	
    	Properties javaMailProperties = new Properties();
    	JavaMailSenderImpl sender = new JavaMailSenderImpl();
    	
    	
//    	javaMailProperties.put(MailTemplaete.TLS_TRANSPORT_REQUIRED, env.getProperty(MailTemplaete.TLS_TRANSPORT_REQUIRED, "true"));
//        javaMailProperties.put(MailTemplaete.AUTHENTICATION_REQUIRED, env.getProperty(MailTemplaete.AUTHENTICATION_REQUIRED, "true"));
        
    	javaMailProperties.put(CommonConstants.TLS_TRANSPORT_REQUIRED, env.getProperty(CommonConstants.TLS_TRANSPORT_REQUIRED));
        javaMailProperties.put(CommonConstants.AUTHENTICATION_REQUIRED, env.getProperty(CommonConstants.AUTHENTICATION_REQUIRED));
        javaMailProperties.put("mail.transport.protocol", env.getProperty(CommonConstants.MAIL_SERVER_PROTOCOL));
//        User during GODADDY
//        javaMailProperties.put("mail.smtp.ssl.enable", "true");
        
//        User during AWS SES
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        
        javaMailProperties.put("mail.smtp.socketFactory.port", env.getProperty(CommonConstants.MAIL_SERVER_PORT));
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        sender.setHost(env.getRequiredProperty(CommonConstants.MAIL_SERVER_HOST));
    	sender.setPort(Integer.parseInt(env.getRequiredProperty(CommonConstants.MAIL_SERVER_PORT)));
        if((env.getProperty(CommonConstants.AUTHENTICATION_REQUIRED)).toLowerCase().equals("true")){
        	sender.setUsername(env.getProperty(CommonConstants.MAIL_SERVER_USERNAME));
//        	sender.setPassword(decryptPassword(env.getProperty(CommonConstants.MAIL_SERVER_USERPASSWORD)));
        	sender.setPassword(env.getProperty(CommonConstants.MAIL_SERVER_USERPASSWORD));
        }
  
//        javaMailProperties.put(MailTemplaete.MAIL_SERVER_PROTOCOL, env.getProperty(MailTemplaete.MAIL_SERVER_PROTOCOL));
        
//        javaMailProperties.put("mail.debug", "true");
    	
        sender.setJavaMailProperties(javaMailProperties);
    	return sender;
    }
	
	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration(){
		logger.info("Configuring FreeMarker...");
		FreeMarkerConfigurationFactoryBean config = new FreeMarkerConfigurationFactoryBean();
		config.setPreferFileSystemAccess(false);
		config.setTemplateLoaderPath("classpath:/templates/");
		try {
			config.afterPropertiesSet();
		} catch (IOException | TemplateException e) {
			logger.error("Failed to configure FreeMArker",e);
			
		}
		return config;
	}
	
	
	// Encryption was generated using JAVA 1.8 with unlimited strength JCE placed inside jdk security folder. All environments must be synced to use the same
		private String decryptPassword(String encryptedText){
			TextEncryptor t = Encryptors.text(CommonConstants.ENCRYPTION_SECUENCE, CommonConstants.ENCRYPTION_SALT);
//			System.out.println(t.decrypt(encryptedText));
			return(t.decrypt(encryptedText));
		}
		

		
		/*@Bean
		public ErrorPageFilter errorPageFilter() {
		    return new ErrorPageFilter();
		}

		@Bean
		public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
		    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		    filterRegistrationBean.setFilter(filter);
		    filterRegistrationBean.setEnabled(false);
		    return filterRegistrationBean;
		}*/
		
		
		
}
