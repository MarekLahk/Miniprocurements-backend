package ee.taltech.procurementSystemBackend.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FlywayConfiguration {

    @Bean
    @Profile("test")
    public FlywayMigrationStrategy cleanMigrateStrategy() {

        return new FlywayMigrationStrategy() {
            @Override
            public void migrate(Flyway flyway) {
                flyway.clean();
                flyway.migrate();
            }
        };
    }
}
