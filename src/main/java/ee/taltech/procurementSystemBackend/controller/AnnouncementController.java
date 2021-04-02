package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.model.Announcement;
import ee.taltech.procurementSystemBackend.models.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.models.search.AnnouncementSearch;
import ee.taltech.procurementSystemBackend.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("api/announcement")
public class AnnouncementController extends ControllerBase<Announcement, AnnouncementDto, AnnouncementSearch>{

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        super(announcementService, Announcement.class, AnnouncementDto.class);
        this.announcementService = announcementService;
    }


    @PostMapping
    public AnnouncementDto addAnnouncement(@RequestBody AnnouncementDto dto) {
        return announcementService.addAnnouncement(dto);
    }

    @PutMapping("{id}")
    public AnnouncementDto updateAnnouncement(@PathVariable Integer id, @RequestBody AnnouncementDto dto) {
        return announcementService.updateAnnouncement(id, dto);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
        announcementService.deleteAnnouncement(id);
    }

}
