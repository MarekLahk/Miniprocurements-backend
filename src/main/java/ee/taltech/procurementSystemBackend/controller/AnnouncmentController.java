package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.service.AnnouncmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/announcement")
public class AnnouncmentController {

    private final AnnouncmentService announcmentService;
}
