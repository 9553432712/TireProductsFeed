package com.goodyear.ecdm.TireProductsFeed.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.goodyear.ecdm.TireProductsFeed.interceptor.SwaggerRequestInterceptor;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{   
	
	@Autowired
	private SwaggerRequestInterceptor swaggerRequestInterceptor;
	
	
	@Bean
	public Docket swaggerProductsApi10() { 
		/*return new Docket(DocumentationType.SWAGGER_2)  
          .groupName("products-api-1.0")
          .select()     
          .apis(RequestHandlerSelectors.basePackage("com.goodyear.ecdm.TireProductsFeed.controller"))
          //.apis(RequestHandlerSelectors.any()) 
          //.paths(regex("/.*"))
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(new ApiInfoBuilder().version("1.0")
        		  					   .title("Products REST API")
        		  					   .description("\"Products REST API to pull Goodyear Tire Products\"")
        		  					   .contact(new Contact("Srini Kurra","","srinivasa_kurra@goodyear.com"))
        		  					   .build());*/
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("product-api-1.0")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.goodyear.ecdm.TireProductsFeed.controller"))
				//.apis(RequestHandlerSelectors.any()) 
				.paths(regex("/product/v1.0.*"))
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Products REST API").description("Products REST API to pull Goodyear Tire Products 1.0")
						.contact(new Contact("Srini Kurra","","srinivasa_kurra@goodyear.com")).build());
	}

	@Bean
	public Docket swaggerProductsApi11() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.groupName("product-api-1.1")
				.select()     
				.apis(RequestHandlerSelectors.basePackage("com.goodyear.ecdm.TireProductsFeed.controller"))
				//.apis(RequestHandlerSelectors.any()) 
				.paths(regex("/product/v1.1.*"))
				//.paths(PathSelectors.any())                          
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.1").title("Products REST API").description("\"Products REST API to pull Goodyear Tire Products 1.1\"")
						.contact(new Contact("Srini Kurra","","srinivasa_kurra@goodyear.com")).build());
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(swaggerRequestInterceptor).addPathPatterns("/swagger-ui.html");
	}
}