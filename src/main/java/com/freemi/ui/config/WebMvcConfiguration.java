package com.freemi.ui.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.freemi"})
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories
@EntityScan(basePackages = "com.freemi.database")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	/*@Autowired
	private Environment env;*/
	//private static final Logger LOGGER = Logger.getLogger(WebMvcConfiguration.class);

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	    configurer.enable();
	}

	/*@Bean
	  public DataSource dataSource() {
		System.out.println("@@@@ Datasource Created @@@@@@");
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    
	    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	    dataSource.setUrl(env.getProperty("spring.datasource.url"));
	    dataSource.setUsername(env.getProperty("spring.datasource.username"));
	    dataSource.setPassword(decryptPassword(env.getProperty("spring.datasource.password")));
	   
	    return dataSource;
	  }*/


@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	System.out.println("@@@@ addResourceHandlers @@@@");
    registry.addResourceHandler("/freemi/**")
            .addResourceLocations("classpath:/freemi/");
  
}

@Bean
public ViewResolver configureViewResolver() {
    InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
    viewResolve.setPrefix("/page/");
    viewResolve.setSuffix(".jsp");
    System.out.println("@@@@ configureViewResolver @@@@");
    return viewResolve;
}

	/*private String decryptPassword(String encryptedText){
		TextEncryptor t = Encryptors.text("freemipass", "5c0744940b5c369b");
		System.out.println(t.decrypt(encryptedText));
		return(t.decrypt(encryptedText));
	}*/
}