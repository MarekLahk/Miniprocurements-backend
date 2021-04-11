package ee.taltech.procurementSystemBackend.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Locale;

@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProcurementEmail {

//    private final TemplateEngine templateEngine;

    private String procurementTitle;
    private String procurementNumber;
    private String procurementRequirements;
    private LocalDateTime procurementDeadline;
    private String Bid_link;
    private String questionLink;
    private String recipientEmail;
//    private String attachmentFileName;
    //        final byte[] attachmentBytes,
//        final String attachmentContentType,
    private Locale locale;
    // Prepare the evaluation context


    public String buildEmail() {
        Context ctx = new Context(locale);
        ctx.setVariable("procurementTitle", this.procurementTitle);
        ctx.setVariable("procurementNumber", this.procurementNumber);
        ctx.setVariable("procurementRequirements", this.procurementRequirements);
        ctx.setVariable("procurementDeadline", this.procurementDeadline);
        ctx.setVariable("Bid_link", this.Bid_link);
        ctx.setVariable("questionLink", this.questionLink);
        return "";
    }


}
