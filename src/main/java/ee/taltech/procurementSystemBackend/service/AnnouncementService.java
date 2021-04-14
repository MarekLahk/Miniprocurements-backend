package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.AnnouncementException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.models.mapper.AnnouncementMapper;
import ee.taltech.procurementSystemBackend.models.model.Announcement;
import ee.taltech.procurementSystemBackend.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class AnnouncementService extends ServiceBase<Announcement, AnnouncementDto> {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository repository, AnnouncementRepository announcementRepository) {
        super(repository, AnnouncementMapper.INSTANCE);
        this.announcementRepository = announcementRepository;
    }


    public AnnouncementDto addAnnouncement(AnnouncementDto dto) {
        Announcement announcement = toModelOptional(dto)
                .orElseThrow(() -> new AnnouncementException("No announcement dto provided"));
        announcement.setDateAdded(new Timestamp(System.currentTimeMillis()));
        return toDtoOptional(announcementRepository.save(announcement))
                .orElseThrow(() -> new AnnouncementException("Could not save announcement"));
    }

    public AnnouncementDto updateAnnouncement(Integer id, AnnouncementDto dto) {
        if (announcementRepository.findById(id).isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Announcement with id [%d] does not exist", id));
        }
        Announcement announcement = toModelOptional(dto)
                .orElseThrow(() -> new AnnouncementException("No announcement dto provided"));
        announcement.setAnnouncementId(id);
        return toDtoOptional(announcementRepository.save(announcement))
                .orElseThrow(() -> new AnnouncementException("Could not update announcement"));
    }

    public void deleteAnnouncement(Integer id) {
        announcementRepository.deleteById(id);
    }
}
