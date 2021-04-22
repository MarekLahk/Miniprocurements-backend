package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.email.EmailSender;
import ee.taltech.procurementSystemBackend.email.ProcurementEmail;
import ee.taltech.procurementSystemBackend.utils.Statics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

@Service
@AllArgsConstructor
public class EmailService {

    private final TemplateEngine templateEngine;
    private final EmailSender emailSender;

    public String generateProcurementInvite() {
        final Context ctx = new Context(Locale.FRANCE);
        ctx.setVariable("recipientName", "recipientName");
        ctx.setVariable("text", LocalDateTime.from(LocalDateTime.now()).format(Statics.formatter));
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music", "Test"));
        ctx.setVariable("imageResourceName", "a"); // so that we can reference it from HTML

        return this.templateEngine.process("email-test", ctx);
    }

    public void sendProcurementEmail() throws MessagingException {
        Context procurementEmailContext = ProcurementEmail.builder()
                .recipientEmail("asd")
                .procurementDeadline(LocalDateTime.now())
                .procurementNumber("123456")
                .procurementTitle("hello world")
                .Bid_link("https://google.com")
                .procurementRequirements("Requirements")
                .locale(Locale.forLanguageTag("ee"))
                .questionLink("https://youtube.com")
                .build()
                .buildContext();
        final String htmlContent = this.templateEngine.process("html/ProcurementEmailTemplate", procurementEmailContext);
        emailSender.sendEmail("Title", htmlContent, null, "receiver@test.com");

    }
}
