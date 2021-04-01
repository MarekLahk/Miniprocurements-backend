package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.model.Announcement;
import ee.taltech.procurementSystemBackend.model.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.repository.AnnouncementRepository;
import ee.taltech.procurementSystemBackend.utils.AnnouncementUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementUtils announcementUtils;

    public AnnouncementDto getAnnouncementById(Integer id) {
        Optional<Announcement> optional = announcementRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RequestedObjectNotFoundException(
                    String.format("Announcement with id [%d] does not exist", id));
        }
        Announcement announcement = optional.get();
        return announcementUtils.createDtoFromAnnouncement(announcement);
    }

    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementRepository.findAll().stream()
                .map(announcementUtils::createDtoFromAnnouncement)
                .collect(Collectors.toList());
    }

    public List<AnnouncementDto> getAllAnnouncementsByProcurement(Integer procurementId) {
        return announcementRepository.findAllByProcurementId(procurementId).stream()
                .map(announcementUtils::createDtoFromAnnouncement)
                .collect(Collectors.toList());
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
