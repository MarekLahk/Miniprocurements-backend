package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.model.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/announcement")
public class AnnouncmentController {

    private final AnnouncementService announcementService;

    @GetMapping("{id}")
    public AnnouncementDto getAnnouncementById(@PathVariable Integer id) {
        return announcementService.getAnnouncementById(id);
    }

    @GetMapping
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("procurement/{procurementId}")
    public List<AnnouncementDto> getAllAnnouncementsByProcurement(@PathVariable Integer procurementId) {
        return announcementService.getAllAnnouncementsByProcurement(procurementId);
    }

    @PostMapping
    public AnnouncementDto addAnnouncement(@RequestBody AnnouncementDto dto) {
        return announcementService.addAnnouncement(dto);
    }

}
