package ee.taltech.procurementSystemBackend.email;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailManger {

    final private JavaMailSender javaMailSender;

    public EmailManger(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendHTMLEmail(String email) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("minihanked_projekt@outlook.com");
        msg.setFrom("minihanked_projekt@outlook.com");
        helper.setSubject("Hanke Test");

//        helper.setFrom("");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(email, true);

        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        javaMailSender.send(msg);

    }


}
