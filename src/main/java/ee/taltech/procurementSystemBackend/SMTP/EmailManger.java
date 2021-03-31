package ee.taltech.procurementSystemBackend.SMTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailManger {

    final private JavaMailSender javaMailSender;

    public EmailManger(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("");
        msg.setFrom("");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        System.out.println(javaMailSender);

        javaMailSender.send(msg);
    }

    public void sendHTMLEmail() throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("");
        msg.setFrom("");
        helper.setSubject("Testing from Spring Boot");

//        helper.setFrom("");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<!DOCTYPE html>\n" +
                "<html lang=\"ee\" style=\"height: 0;\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html charset=UTF-8\" />\n" +
                "    <title>Title</title>\n" +
                "    <style>\n" +
                "\n" +
                "        body {\n" +
                "            font-family: Proxima nova, Regular,Verdana,serif ;\n" +
                "            color: #353535;\n" +
                "            background-color: #F6F9FC;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .mainWrapper {\n" +
                "            color: #353535;\n" +
                "            background-color: #F6F9FC;\n" +
                "            height: 100%;\n" +
                "            table-layout: fixed;\n" +
                "            padding-bottom: 40px;\n" +
                "            /*text-align: center;*/\n" +
                "        }\n" +
                "\n" +
                "        div {\n" +
                "            color: #353535;\n" +
                "            display: block;\n" +
                "            flex-direction: column;\n" +
                "            background-color: #ffffff;\n" +
                "            margin: 10px 20px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .webkit {\n" +
                "            max-width: 680px;\n" +
                "            background-color: white;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        .outer {\n" +
                "            Margin: 0 auto 40px;\n" +
                "            width: 100%;\n" +
                "            max-width: 600px;\n" +
                "            border-spacing: 0;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        h1 {\n" +
                "            text-transform: uppercase;\n" +
                "            text-align: center;\n" +
                "            font-style: normal;\n" +
                "            font-weight: bold;\n" +
                "            font-size: 36px;\n" +
                "            margin-top: 0;\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            text-transform: uppercase;\n" +
                "            font-style: normal;\n" +
                "            text-align: left;\n" +
                "            font-weight: bold;\n" +
                "            font-size: 28px;\n" +
                "            margin-bottom: 15px;\n" +
                "            margin-top: 18px;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "        .bodyText {\n" +
                "            font-size: 14px;\n" +
                "            line-height: 20px;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        .textWarning {\n" +
                "            color: #FE3030;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "\n" +
                "        .ttButton {\n" +
                "            background-color: #E4067E;\n" +
                "            border: none;\n" +
                "            color: white;\n" +
                "            padding: 20px 20px;\n" +
                "            text-align: center;\n" +
                "            font-weight: bold;\n" +
                "            line-height: 17px;\n" +
                "            font-style: normal;\n" +
                "            font-size: 14px;\n" +
                "            margin: 4px 2px;\n" +
                "            cursor: pointer;\n" +
                "            border-radius: 16px;\n" +
                "            width: 100%;\n" +
                "            max-width: 200px;\n" +
                "            float: left;\n" +
                "        }\n" +
                "\n" +
                "        .footerText {\n" +
                "            margin-top: 23px;\n" +
                "            margin-bottom: 30px;\n" +
                "            line-height: 19px;\n" +
                "            font-size: 10px;\n" +
                "            color:#818182;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        .titleTD {\n" +
                "            padding-top: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<!--    Body wrapper    -->\n" +
                "<div class=\"mainWrapper\">\n" +
                "    <div class=\"webkit\">\n" +
                "        <table class=\"outer\">\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <img style=\"max-width: 82%; height: auto\" src=\"https://portal-int.taltech.ee/sites/default/files/news-image/Logo_veeb_esitlus_png.png\">\n" +
                "                    <h1>\n" +
                "                        Mööbli hange\n" +
                "                    </h1>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titleTD\">\n" +
                "                    <hr>\n" +
                "                    <h2>\n" +
                "                        Hanke Tingimused\n" +
                "                    </h2>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <p class=\"bodyText\">\n" +
                "                        <!--     Framework agreement number       -->\n" +
                "                        Palume teil esitada pakkumus raamlepingu nr <b>207933</b> alusel.\n" +
                "                    </p>\n" +
                "                    <!--    Requirements    -->\n" +
                "                    <p class=\"bodyText\" style=\"white-space: pre-wrap\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean et pellentesque dolor.\n" +
                "Integer arcu urna, imperdiet sit amet gravida sit amet, tristique sed eros.\n" +
                "\n" +
                "Donec arcu risus, luctus vel rutrum venenatis, tristique id nibh.\n" +
                "Ut accumsan lectus risus, nec lacinia arcu pretium egestas. Morbi consectetur,\n" +
                "lorem sed semper placerat, ante lorem bibendum turpis, et pulvinar est neque quis ante.\n" +
                "\n" +
                "In convallis est at iaculis semper. Integer sed mattis orci, eu aliquet metus.\n" +
                "Vestibulum mattis, lacus at aliquet molestie, nibh purus tincidunt dui, non ullamcorper purus diam at dui.\n" +
                "Nam elit diam, scelerisque vel pulvinar eget, egestas vitae massa.\n" +
                "                        Nam nec nisl et libero tincidunt auctor non ac risus.</p>\n" +
                "                    <p class=\"bodyText\">\n" +
                "                        Hankeleping sõlmitakse pakkujaga, kes on esitanud madalaima pakkumuse.\n" +
                "                    </p>\n" +
                "                    <p class=\"bodyText\">\n" +
                "                        <!--    Procurement offers deadline    -->\n" +
                "                        Pakkumuste esitamise tähtaeg: <b>15.03.2021 12:00</b>.\n" +
                "                    </p>\n" +
                "                    <p class=\"bodyText textWarning\">\n" +
                "                        Pärast pakkumuste esitamise tähtaega laekunud pakkumusi ei arvestata.\n" +
                "                    </p>\n" +
                "                    <p class=\"bodyText\">\n" +
                "                        Kui pakkumuse esitamise tähtpäevaks ei ole laekunud pakkumust, loeb hankija seda minikonkursil osalemisest loobumiseks.\n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titleTD\">\n" +
                "                    <hr>\n" +
                "                    <h2>\n" +
                "                        Pakkumuse esitamine\n" +
                "                    </h2>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <p class=\"bodyText\">\n" +
                "                        Pakkumuse saate esitada vajutates alljärgnevale nupule “Esita pakkumus”.\n" +
                "                    </p>\n" +
                "                    <a href=\"https://google.com\"><button class=\"ttButton\">Esita pakkumus</button></a>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titleTD\">\n" +
                "                    <hr>\n" +
                "                    <h2>Küsimused</h2>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "\n" +
                "                    <p class=\"bodyText\">\n" +
                "                        Kõik tekkinud küsimused saate esitada kasutades alljärgnevat nuppu “Esita küsimus”.\n" +
                "                    </p>\n" +
                "                    <a href=\"https://google.com\"><button class=\"ttButton\">Esita küsimus</button></a>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"titleTD\">\n" +
                "                    <hr>\n" +
                "                    <p class=\"footerText\">\n" +
                "                        <!--     Procurement contacts name       -->\n" +
                "                        Nimi Nimeste<br>\n" +
                "                        <!--     Procurement contacts job position       -->\n" +
                "                        ametikoht<br>\n" +
                "                        <!--     Procurement contacts department       -->\n" +
                "                        struktuuriüksus (vajadusel)<br>\n" +
                "                        Tallinna Tehnikaülikool<br>\n" +
                "                        <!--     Procurement contacts phone number       -->\n" +
                "                        tel 620 2002, 5123 4567 (mobiilinumber vajadusel)<br>\n" +
                "                        <!--     Procurement contacts email adress       -->\n" +
                "                        nimi.nimeste@taltech.ee<br>\n" +
                "                        www.taltech.ee<br>\n" +
                "                    </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>", true);

        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        javaMailSender.send(msg);

    }


}
