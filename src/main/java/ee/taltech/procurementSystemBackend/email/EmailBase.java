package ee.taltech.procurementSystemBackend.email;

import org.thymeleaf.TemplateEngine;

public class EmailBase {

    private final TemplateEngine templateEngine;

    public EmailBase(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
}
