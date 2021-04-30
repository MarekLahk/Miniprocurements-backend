package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.email.EmailSender;
import ee.taltech.procurementSystemBackend.models.email.ProcurementEmail;
import ee.taltech.procurementSystemBackend.models.model.Email;
import ee.taltech.procurementSystemBackend.models.model.Miniprocurement;
import ee.taltech.procurementSystemBackend.models.model.MiniprocurementPartner;
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


    public void sendProcurementEmail(Miniprocurement miniprocurement) throws MessagingException {
        ProcurementEmail.ProcurementEmailBuilder emailBuilder = ProcurementEmail.builder();
        emailBuilder.procurementDeadline(miniprocurement.getDeadline().toLocalDateTime());
        emailBuilder.procurementTitle(miniprocurement.getProcurementName());
        emailBuilder.procurementDescription(miniprocurement.getDescription());
        emailBuilder.locale(Locale.forLanguageTag("ee"));

        if (miniprocurement.getHasContract()) {
            // TODO: 29/04/2021 Implement id generation with contract
            return;
        } else {
            emailBuilder.procurementNumber(miniprocurement.getProcurementId().toString());
        }

        // TODO: 29/04/2021 Implement link generation with configurable url base
        for (MiniprocurementPartner procurementPartner : miniprocurement.getMiniprocurementPartners()) {
            emailBuilder.recipientEmail(procurementPartner.getPartner().getEMail());
            emailBuilder.Bid_link("https://minihanked.variksoo.ee/bid/" + procurementPartner.getMiniprocurementPartnerLinkId().toString());
            emailBuilder.questionLink("https://minihanked.variksoo.ee/question/" + procurementPartner.getMiniprocurementPartnerLinkId());
            System.out.println(procurementPartner.getMiniprocurementPartnerLinkId());
            Context procurementEmailContext = emailBuilder.build().buildContext();

            final String htmlContent = this.templateEngine.process("html/ProcurementEmailTemplate", procurementEmailContext);
            Boolean emailSent = emailSender.sendEmail(miniprocurement.getProcurementName(), htmlContent, null, procurementPartner.getPartner().getEMail());
            Email.EmailBuilder emailModelBuilder = Email.builder();
            emailModelBuilder.procurementId(miniprocurement.getProcurementId());
            emailModelBuilder.recipientId(procurementPartner.getMiniprocurementPartnerPartnerId());
            if (emailSent) {
                emailModelBuilder.sentDate(LocalDateTime.now());
            }
            addEmail(emailModelBuilder.build());
        }



    }
}
