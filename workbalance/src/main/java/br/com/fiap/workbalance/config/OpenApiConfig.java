package br.com.fiap.workbalance.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI workBalanceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("WorkBalance Hub API")
                        .version("v1")
                        .description("API central de bem-estar para o Global Solution FIAP"));
    }
}
