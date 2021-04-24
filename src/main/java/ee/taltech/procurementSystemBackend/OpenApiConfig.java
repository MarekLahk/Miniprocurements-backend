package ee.taltech.procurementSystemBackend;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Miniprocurement BE API").description(
                        "This is spring-doc generated API documentation using annotation in the source code for Taltech miniprocurement system created by Taltech students as part of Tarkvaratehnika subject in spring 2021."));
    }
}