package ee.taltech.procurementSystemBackend.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "auth")
public class WebAuthConfig {

    private String redirectUrl;

    private String logoutUrl;
}
