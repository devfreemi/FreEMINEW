package com.freemi.ui;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.freemi.common.util.CommonConstants;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.freemi")
//@EnableJpaRepositories
@EnableAsync
@EnableJpaRepositories(basePackages = "com.freemi")
@EntityScan(basePackages = {"com.freemi.entity.database","com.freemi.entity.investment"})
public class FreemiApplication {
	@Autowired
	private Environment env;
	public static void main(String[] args) {
		SpringApplication.run(FreemiApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		System.out.println("@@@@ Datasource Created @@@@@@");
		
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
		return(t.decrypt(encryptedText));
	}
	
	
	
}
