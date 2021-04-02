package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.models.model.Announcement;
import ee.taltech.procurementSystemBackend.models.Dto.AnnouncementDto;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AnnouncementUtils {

    public Announcement createAnnouncementFromDto(AnnouncementDto dto) {
        return Announcement.builder()
                .procurementId(dto.getProcurementId())
                .employeeId(dto.getEmployeeId())
                .announcement(dto.getAnnouncement())
                .dateAdded(new Timestamp(System.currentTimeMillis())).build();
    }

    public AnnouncementDto createDtoFromAnnouncement(Announcement announcement) {
        return AnnouncementDto.builder()
                .announcementId(announcement.getAnnouncementId())
                .procurementId(announcement.getProcurementId())
                .employeeId(announcement.getEmployeeId())
                .announcement(announcement.getAnnouncement()).build();
    }
}
