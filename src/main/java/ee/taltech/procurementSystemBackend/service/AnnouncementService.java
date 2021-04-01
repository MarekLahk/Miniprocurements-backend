package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.model.Announcement;
import ee.taltech.procurementSystemBackend.model.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.repository.AnnouncementRepository;
import ee.taltech.procurementSystemBackend.utils.AnnouncementUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AnnouncementService extends ServiceBase<Announcement>{

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementUtils announcementUtils;

    public AnnouncementService(AnnouncementRepository repository, AnnouncementRepository announcementRepository, AnnouncementUtils announcementUtils) {
        super(repository);
        this.announcementRepository = announcementRepository;
        this.announcementUtils = announcementUtils;
    }


    public AnnouncementDto addAnnouncement(AnnouncementDto dto) {
        Announcement announcement = announcementUtils.createAnnouncementFromDto(dto);
        announcement.setDateAdded(new Timestamp(System.currentTimeMillis()));
        return announcementUtils.createDtoFromAnnouncement(
                announcementRepository.save(announcement)
        );
    }

    public AnnouncementDto updateAnnouncement(Integer id, AnnouncementDto dto) {
        if (announcementRepository.findById(id).isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Announcement with id [%d] does not exist", id));
        }
        Announcement announcement = announcementUtils.createAnnouncementFromDto(dto);
        announcement.setAnnouncementId(id);
        return announcementUtils.createDtoFromAnnouncement(
                announcementRepository.save(announcement)
        );
    }

    public void deleteAnnouncement(Integer id) {
        announcementRepository.deleteById(id);
    }
}
