package ee.taltech.procurementSystemBackend.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.files")
@Data
public class FileCustomConfig {
    private String path;
}
