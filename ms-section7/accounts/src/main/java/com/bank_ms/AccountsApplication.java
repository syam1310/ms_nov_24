package com.bank_ms;

import com.bank_ms.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.eazybytes.accounts.controller") })
@EnableJpaRepositories("com.eazybytes.accounts.repository")
@EntityScan("com.eazybytes.accounts.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Bank MS Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Shyam Patel",
						email = "tutor@bank_ms.com",
						url = "https://www.bank_ms.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.bank_ms.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Bank MS Accounts microservice REST API Documentation",
				url = "https://www.bank_ms.com/swagger-ui.html"
		)
)
public class
AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
