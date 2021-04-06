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
        final Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("name", "recipientName");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        ctx.setVariable("imageResourceName", "a"); // so that we can reference it from HTML

        return this.templateEngine.process("email-test.html", ctx);
    }
}
