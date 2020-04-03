package com.freemi.ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
	System.out.println("http security...");

	http
	.csrf().ignoringAntMatchers("/mutual-funds/uploadsign","/mutual-funds/uploadsignedaof","/mutual-funds/uploadsignRegisteredCustomer","/otpverify.do","/api/**","/serviceapi/**")
	.and()
	.headers()
	.httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000)
	.and()
	.frameOptions().sameOrigin()
	;

	/*  security
	  .authorizeRequests()
	      .antMatchers("/mobileapi/**")
	          .hasRole("USER")
	          .and()
		  .csrf().ignoringAntMatchers("/mutual-funds/uploadsign","/mutual-funds/uploadsignedaof","/mutual-funds/uploadsignRegisteredCustomer","/otpverify.do","/api/**","/mobileapi/**")
		  .and()
		  .headers()
		  	.httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000)
		  	.and()
		  	.frameOptions().sameOrigin()
	        ;*/

	/* security
	 .httpBasic().and()
	 .authorizeRequests()
	   .antMatchers( "/mobileapi", "/mobileapi/**").hasRole("USER")
	   .anyRequest().authenticated()
	   .and()
	  .csrf().ignoringAntMatchers("/mutual-funds/uploadsign","/mutual-funds/uploadsignedaof","/mutual-funds/uploadsignRegisteredCustomer","/otpverify.do","/api/**","/mobileapi/**")
	  .and()
	  .headers()
	  	.httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000)
	  	.and()
	  	.frameOptions().sameOrigin()
	  ;
	 */

	http.logout().logoutUrl("/logout");
    }
	
	
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth
	.inMemoryAuthentication()
	.withUser("user").password("password").roles("USER")
	;
    }



}
