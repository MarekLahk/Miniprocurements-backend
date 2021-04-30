package ee.taltech.procurementSystemBackend.models.email;

import ee.taltech.procurementSystemBackend.utils.Statics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Locale;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ProcurementEmail implements EmailBase {

    private String procurementTitle;
    private String procurementNumber;
    private String procurementRequirements;
    private LocalDateTime procurementDeadline;
    private String Bid_link;
    private String questionLink;
    private String recipientEmail;
    private Locale locale;


    public Context buildContext() {
        Context ctx = new Context(locale);
        ctx.setVariable("procurementTitle", this.procurementTitle);
        ctx.setVariable("procurementNumber", this.procurementNumber);
        ctx.setVariable("procurementRequirements", this.procurementRequirements);
        ctx.setVariable("procurementDeadline", this.procurementDeadline.format(Statics.formatter));
        ctx.setVariable("Bid_link", this.Bid_link);
        ctx.setVariable("questionLink", this.questionLink);
        return ctx;
    }


}
