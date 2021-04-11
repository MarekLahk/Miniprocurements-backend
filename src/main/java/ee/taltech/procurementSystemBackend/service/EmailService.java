package ee.taltech.procurementSystemBackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@Service
@AllArgsConstructor
public class EmailService {

    private final TemplateEngine templateEngine;

    public String generateProcurementInvite() {
        final Context ctx = new Context(Locale.FRANCE);
        ctx.setVariable("recipientName", "recipientName");
        ctx.setVariable("text", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music", "Test"));
        ctx.setVariable("imageResourceName", "a"); // so that we can reference it from HTML

        return this.templateEngine.process("email-test", ctx);
    }

    public void sendProcurementEmail() {

    }
}
