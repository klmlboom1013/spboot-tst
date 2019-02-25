package com.lhs;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing	/* JPA 데이터 자동 추가 */
@EnableSwagger2		/* Swagger 사용 설정 */
public class MyLhsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyLhsApplication.class, args);
	}
	
	/**
	 * Rest API Swagger
	 * @return
	 */
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("my-lhs")
				.apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.build();
	}
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("my-lhs")
				.description("My Lhs Swagger")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.contact("Niklas Deidloff")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
				.version("2.0")
				.build();
	}
}
