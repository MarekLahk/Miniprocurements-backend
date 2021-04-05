package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.SMTP.EmailManger;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static java.nio.file.Files.readString;

@RestController
@AllArgsConstructor
@RequestMapping("api/email")
public class EmailTestController {

    private final EmailManger emailManger;

    @GetMapping
    public String sendEmail() throws MessagingException {

        String filePath = "src/main/resources/html/ProcurementEmailTemplate.html";
        Path path = Path.of(filePath);
        String content = null;
        try {
            content = readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (content == null) {
            return "File error";
        }

        emailManger.sendHTMLEmail(content);
        return "Success";
    }
}
