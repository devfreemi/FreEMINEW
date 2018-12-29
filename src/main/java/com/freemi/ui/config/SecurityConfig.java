package com.freemi.ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
    protected void configure(HttpSecurity security) throws Exception
    {
		
     security.httpBasic().disable()
//     .csrf().disable()
//     .headers().cacheControl().disable()
     .headers()
     	.httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000)
     	.and()
     	.frameOptions().sameOrigin()
     	
     ;
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("user").password("password").roles("USER");
	}

	

}
