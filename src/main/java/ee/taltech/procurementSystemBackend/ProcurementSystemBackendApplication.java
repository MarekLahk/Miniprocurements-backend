package ee.taltech.procurementSystemBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class ProcurementSystemBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementSystemBackendApplication.class, args);
		System.out.println(SpringVersion.getVersion());
	}

}
