package ee.taltech.procurementSystemBackend.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
@AllArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;

    public Boolean sendEmail(String emailTitle, String contents, List<String> attachmentPaths, String recipientEmail) {

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message;
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject(emailTitle);
            message.setFrom("thymeleaf@example.com");
            message.setTo(recipientEmail);
            message.setText(contents, true );
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
