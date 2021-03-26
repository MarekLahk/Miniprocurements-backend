package ee.taltech.procurementSystemBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ProcurementSystemBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementSystemBackendApplication.class, args);
	}

}
