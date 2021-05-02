package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.email.EmailSender;
import ee.taltech.procurementSystemBackend.models.email.ProcurementEmail;
import ee.taltech.procurementSystemBackend.models.model.Email;
import ee.taltech.procurementSystemBackend.models.model.Procurement;
import ee.taltech.procurementSystemBackend.models.model.ProcurementPartner;
import ee.taltech.procurementSystemBackend.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
@AllArgsConstructor
public class EmailService {

    private final TemplateEngine templateEngine;
    private final EmailSender emailSender;
    private final EmailRepository emailRepository;

    private void addEmail(Email email) {
        emailRepository.save(email);
    }


    public void sendProcurementEmail(Procurement procurement) throws MessagingException {
        ProcurementEmail.ProcurementEmailBuilder emailBuilder = ProcurementEmail.builder();
        emailBuilder.procurementDeadline(procurement.getDeadline().toLocalDateTime());
        emailBuilder.procurementTitle(procurement.getName());
        emailBuilder.procurementDescription(procurement.getDescription());
        emailBuilder.locale(Locale.forLanguageTag("ee"));

        if (procurement.getHasContract()) {
            // TODO: 29/04/2021 Implement id generation with contract
            return;
        } else {
            emailBuilder.procurementNumber(procurement.getId().toString());
        }

        // TODO: 29/04/2021 Implement link generation with configurable url base
        for (ProcurementPartner procurementPartner : procurement.getProcurementPartners()) {
            emailBuilder.recipientEmail(procurementPartner.getPartner().getEMail());
            emailBuilder.Bid_link("https://minihanked.variksoo.ee/bid/" + procurementPartner.getLinkId().toString());
            emailBuilder.questionLink("https://minihanked.variksoo.ee/question/" + procurementPartner.getLinkId());
            System.out.println(procurementPartner.getLinkId());
            Context procurementEmailContext = emailBuilder.build().buildContext();

            final String htmlContent = this.templateEngine.process("html/ProcurementEmailTemplate", procurementEmailContext);
            Boolean emailSent = emailSender.sendEmail(procurement.getName(), htmlContent, null, procurementPartner.getPartner().getEMail());
            Email.EmailBuilder emailModelBuilder = Email.builder();
            emailModelBuilder.procurementId(procurement.getId());
            emailModelBuilder.recipientId(procurementPartner.getPartnerId());
            if (emailSent) {
                emailModelBuilder.sentAt(LocalDateTime.now());
            }
            addEmail(emailModelBuilder.build());
        }



    }
}
