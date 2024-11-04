package com.alpinia.valoraseries.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Valorar Series API",
                version = "1.0",
                description = "Documentation for endpoints in Valorar Series")
)
public class OpenApiConfig {
}