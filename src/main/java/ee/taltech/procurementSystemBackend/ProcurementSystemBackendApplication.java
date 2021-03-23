package ee.taltech.procurementSystemBackend;

import ee.taltech.procurementSystemBackend.SMTP.EmailManger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class ProcurementSystemBackendApplication implements CommandLineRunner {

	private final EmailManger emailManger;

	public ProcurementSystemBackendApplication(EmailManger emailManger) {
		this.emailManger = emailManger;
	}

	public static void main(String[] args) throws MessagingException {
		SpringApplication.run(ProcurementSystemBackendApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {

			emailManger.sendHTMLEmail();
			//sendEmailWithAttachment();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}


}
