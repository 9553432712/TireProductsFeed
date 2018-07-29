package com.goodyear.ecdm.TireProductsFeed;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.goodyear.ecdm.TireProductsFeed.auth.AdalFilter;

@SpringBootApplication
public class DigitasProductsFeedApplication extends SpringBootServletInitializer {
	
	@Value("${authenticatedpaths}")
	private String authenticatedpaths;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DigitasProductsFeedApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DigitasProductsFeedApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }*/
    
    @Bean
	public FilterRegistrationBean registration(AdalFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}

}