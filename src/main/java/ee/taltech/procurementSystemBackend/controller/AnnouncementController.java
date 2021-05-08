package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.model.Announcement;
import ee.taltech.procurementSystemBackend.models.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.models.search.AnnouncementSearch;
import ee.taltech.procurementSystemBackend.service.AnnouncementService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/announcements")
public class AnnouncementController extends ControllerBase<Announcement, AnnouncementDto, AnnouncementSearch> {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        super(announcementService);
        this.announcementService = announcementService;
    }

    @PostMapping
    public AnnouncementDto addAnnouncement(@Valid @RequestBody AnnouncementDto dto,
                                           Authentication authentication) {
        return announcementService.addAnnouncement(dto, authentication);
    }

    @PutMapping("{id}")
    public AnnouncementDto updateAnnouncement(@PathVariable Integer id,
                                              @RequestBody AnnouncementDto dto,
                                              Authentication authentication) {
        return announcementService.updateAnnouncement(id, dto, authentication);
    }

    @Deprecated
    @DeleteMapping("{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
        announcementService.deleteAnnouncement(id);
    }

}
