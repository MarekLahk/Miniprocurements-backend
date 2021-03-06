package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.AnnouncementException;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.models.mapper.AnnouncementMapper;
import ee.taltech.procurementSystemBackend.models.model.Announcement;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.AnnouncementRepository;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AnnouncementService extends ServiceBase<Announcement, AnnouncementDto> {

    private final AnnouncementRepository announcementRepository;
    private final AuthUtils authUtils;

    public AnnouncementService(AnnouncementRepository repository,
                               AnnouncementRepository announcementRepository,
                               AuthUtils authUtils) {
        super(repository, AnnouncementMapper.INSTANCE);
        this.announcementRepository = announcementRepository;
        this.authUtils = authUtils;
    }


    public AnnouncementDto addAnnouncement(AnnouncementDto dto, Authentication authentication) {
        Person person = authUtils.getPersonToPerformOperations(authentication);
        Announcement announcement = toModelOptional(dto)
                .orElseThrow(() -> new AnnouncementException("No announcement dto provided"));
        announcement.setCreatedAt(null);
        announcement.setEmployeeId(person.getId());
        return toDtoOptional(announcementRepository.save(announcement)).get();
    }

    public AnnouncementDto updateAnnouncement(Integer id, AnnouncementDto dto, Authentication authentication) {
        Person person = authUtils.getPersonToPerformOperations(authentication);
        Optional<Announcement> optionalAnnouncement =
                announcementRepository.findByIdAndEmployeeId(id, person.getId());
        if (optionalAnnouncement.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Announcement with id [%d] and employee id [%d] does not exist", id, person.getId()));
        }
        Announcement announcement = toModelOptional(dto)
                .orElseThrow(() -> new AnnouncementException("No announcement dto provided"));
        announcement.setId(id);
        announcement.setEmployeeId(person.getId());
        return toDtoOptional(announcementRepository.save(announcement)).get();
    }

    public void deleteAnnouncement(Integer id) {
        announcementRepository.deleteById(id);
    }
}
