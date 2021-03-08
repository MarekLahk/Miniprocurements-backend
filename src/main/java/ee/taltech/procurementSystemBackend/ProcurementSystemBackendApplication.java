package ee.taltech.procurementSystemBackend;

import ee.taltech.procurementSystemBackend.SMTP.EmailManger;
import ee.taltech.procurementSystemBackend.SMTP.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcurementSystemBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementSystemBackendApplication.class, args);

		EmailSender emailSender = new EmailSender();
		EmailManger emailManger = new EmailManger(emailSender.getJavaMailSender());
		emailManger.sendEmail();
	}

}
