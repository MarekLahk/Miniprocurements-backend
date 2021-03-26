package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.model.Announcement;
import ee.taltech.procurementSystemBackend.model.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.repository.AnnouncementRepository;
import ee.taltech.procurementSystemBackend.utils.AnnouncementUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementUtils announcementUtils;

    public AnnouncementDto getAnnouncementById(Integer id) {
        return announcementUtils.createDtoFromAnnouncement(
                announcementRepository.getAnnouncementByAnnouncementId(id)
        );
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
        return announcementUtils.createDtoFromAnnouncement(
                announcementRepository.save(announcement)
        );
    }
}
