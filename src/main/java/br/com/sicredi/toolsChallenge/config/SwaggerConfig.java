package br.com.sicredi.toolsChallenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Pagamentos")
                        .version("1.0")
                        .description("API para gerenciar pagamentos (ToolsChallenge) do SICREDI"));
    }
}
