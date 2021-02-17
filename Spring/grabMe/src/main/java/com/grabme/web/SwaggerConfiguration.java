package com.grabme.web;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport{
	private String version;
	private String title;

	@Bean
	public Docket apiV1() {
		version = "V1";
		title = "victolee API " + version;

		return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.grabme.web"))
                .paths(PathSelectors.ant("/user/**"))
                .build()
                .apiInfo(apiInfo(title, version));
	}

//	@Bean
//	public Docket apiV2() {
//		version = "V2";
//		title = "victolee API " + version;
//
//		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).groupName(version).select()
//				.apis(RequestHandlerSelectors.basePackage("com.calendarapp.web.UsersContorller"))
//				.paths(PathSelectors.ant("/v2/api/**")).build().apiInfo(apiInfo(title, version));
//
//	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	private ApiInfo apiInfo(String title, String version) {
		return new ApiInfo(title, "Swagger�� ������ API Docs", version, "www.example.com",
				new Contact("Contact Me", "www.example.com", "foo@example.com"), "Licenses",

				"www.example.com",

				new ArrayList<>());
	}
}
