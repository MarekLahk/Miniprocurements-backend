package ee.taltech.procurementSystemBackend.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail.config")
@Data
public class CustomConfiguration {

    private String path;

}
