package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.repository.MiniprocurementRepository;
import ee.taltech.procurementSystemBackend.service.EmailService;
import ee.taltech.procurementSystemBackend.service.MiniprocurementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
@RequestMapping("api/email")
public class EmailTestController {

    private final EmailService emailService;
    private final MiniprocurementService procurementService;
    private final MiniprocurementRepository miniprocurementRepository;

    @PostMapping
    public void test() {
        System.out.println(miniprocurementRepository.findById(1).get().getMiniprocurementPartners().size());
    }

    @GetMapping
    public String sendMail(
)
            throws MessagingException {
//        final String procurementTitle = "Keemia hange";
//        final String procurementNumber = "132456";
//        final String procurementRequirements = "Donec arcu risus, luctus vel rutrum venenatis, tristique id nibh.\n" +
//                "Ut accumsan lectus risus, nec lacinia arcu pretium egestas. Morbi consectetur,\n" +
//                "lorem sed semper placerat, ante lorem bibendum turpis, et pulvinar est neque quis ante.";
//        final LocalDateTime procurementDeadline = LocalDateTime.of(LocalDate.now(), LocalTime.now());
//        final String Bid_link = "https://wwww.youtube.com";
//        final String questionLink = "https://www.gmail.com";
//        final String recipientEmail = "Email@email";
//        final String attachmentFileName = "";
////        final byte[] attachmentBytes,
////        final String attachmentContentType,
//        final Locale locale = Locale.forLanguageTag("ee");
//        // Prepare the evaluation context
//        final Context ctx = new Context(locale);
//        ctx.setVariable("procurementTitle", procurementTitle);
//        ctx.setVariable("procurementNumber", procurementNumber);
//        ctx.setVariable("procurementRequirements", procurementRequirements);
//        ctx.setVariable("procurementDeadline", procurementDeadline);
//        ctx.setVariable("Bid_link", Bid_link);
//        ctx.setVariable("questionLink", questionLink);
//
//
//
//        // Prepare message using a Spring helper
//        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
//        final MimeMessageHelper message
//                = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        message.setSubject("Example HTML email with attachment");
//        message.setFrom("thymeleaf@example.com");
//        message.setTo(recipientEmail);
//
//        // Create the HTML body using Thymeleaf
//        final String htmlContent = this.templateEngine.process("html/ProcurementEmailTemplate", ctx);
//        message.setText(htmlContent, true );
//
//        // Add the attachment
////        final InputStreamSource attachmentSource = new ByteArrayResource(attachmentBytes);
////        message.addAttachment(
////                attachmentFileName, attachmentSource, attachmentContentType);
//
//        // Send mail
//        this.mailSender.send(mimeMessage);
        emailService.sendProcurementEmail(miniprocurementRepository.findById(1).get());
        return "Success";
    }
}
