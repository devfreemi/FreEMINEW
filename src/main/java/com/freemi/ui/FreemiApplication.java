package com.freemi.ui;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import com.freemi.common.util.CommonConstants;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.freemi")
//@EnableJpaRepositories
@EnableCaching
@EnableScheduling
@EnableAsync
@EnableJpaRepositories(basePackages = "com.freemi")
@EntityScan(basePackages = {"com.freemi.entity.database","com.freemi.entity.investment"})
public class FreemiApplication implements WebApplicationInitializer {
	@Autowired
	private Environment env;
	public static void main(String[] args) {
		SpringApplication.run(FreemiApplication.class, args);
		System.out.println("products - MVC loading complete...");
	}

	@Bean
	public DataSource dataSource() {
		System.out.println("@@@@ products app ->  Datasource Created @@@@@@");
		
//		String s = decryptPassword(env.getProperty("spring.datasource.password"));
//		System.out.println("Password- "+ s);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
//		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setPassword(decryptPassword(env.getProperty("spring.datasource.password")));
		return dataSource;
	}

	
	// Encryption was generated using JAVA 1.8 with unlimited strength JCE placed inside jdk security folder. All environments must be synced to use the same
	private String decryptPassword(String encryptedText){
		TextEncryptor t = Encryptors.text(CommonConstants.ENCRYPTION_SECUENCE, CommonConstants.ENCRYPTION_SALT);
//		System.out.println(t.decrypt(encryptedText));
//		System.out.println(t.encrypt("Freemi1234!"));
		return(t.decrypt(encryptedText));
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
	
	
	
}
