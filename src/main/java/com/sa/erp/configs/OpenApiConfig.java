package com.sa.erp.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /**
     * Set ApiInfo
     * Contact data
     * application version
     * Apache license
     * security schema
     *
     * **/
    @Bean
    public OpenAPI apiInfo() {
        Contact contact = new Contact();
        contact.setEmail("");
        contact.setName("SA-ERP api");
        contact.setUrl("");
        return new OpenAPI().info(new Info().contact(contact)
                        .version("0.1.0")
                        .description("SA ERP API services")
                        .title("SA ERP API services").termsOfService("")
                        .license(new License().name("Apache License Version 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}
