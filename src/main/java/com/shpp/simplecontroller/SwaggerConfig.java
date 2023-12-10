package com.shpp.simplecontroller;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${application.openapi.url}")
    private String url;

    @Value("${application.version}")
    private String version;

    @Bean
    public OpenAPI openApiInformation() {
        Server localServer = new Server()
                .url(url)
                .description("Localhost Server URL");

        Contact contact = new Contact()
                .email("zlatnikovyevgeniy@gmail.com")
                .name("Yevgeniy Pustovit");

        Info info = new Info()
                .contact(contact)
                .description("Application with simple controller that realizes crud operations.")
                .title("Web application")
                .version(version)
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        return new OpenAPI().info(info).addServersItem(localServer);
    }

}
