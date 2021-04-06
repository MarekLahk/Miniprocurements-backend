package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.email.EmailManger;
import ee.taltech.procurementSystemBackend.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
@RequestMapping("api/email")
public class EmailTestController {

    private final EmailService emailService;
    private final EmailManger emailManger;

    @GetMapping
    public String sendEmail() throws MessagingException {

        String content = emailService.generateProcurementInvite();

        emailManger.sendHTMLEmail(content);
        return "Success";
    }
}
