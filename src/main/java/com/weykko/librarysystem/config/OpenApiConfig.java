package com.weykko.librarysystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Library Management System",
                description = "REST API documentation for LMS",
                version = "1.0"
        ),
        servers = @Server(url = "/")
)
public class OpenApiConfig {
}
