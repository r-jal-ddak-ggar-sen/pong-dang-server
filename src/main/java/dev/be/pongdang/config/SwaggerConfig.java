package dev.be.pongdang.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("https://pongdang.ml");
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        return new OpenAPI().servers(List.of(server, localhostServer));
    }
}
