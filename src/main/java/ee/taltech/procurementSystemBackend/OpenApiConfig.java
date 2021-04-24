package ee.taltech.procurementSystemBackend;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    //TODO: Add definition of security requierments: https://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/security/SecurityRequirement.html


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .servers(List.of(new Server().description("CI/CD production").url("https://minihanked.variksoo.ee"), new Server().description("Local API").url("http://localhost:8080")))
                .info(new Info().title("Taltech Miniprocurement BE").description(
                        "This is spring-doc generated API documentation using annotation in the source code for Taltech miniprocurement system created by Taltech students as part of Tarkvaratehnika subject in spring 2021."));
    }
}