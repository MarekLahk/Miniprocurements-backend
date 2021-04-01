package ee.taltech.procurementSystemBackend.model.Dto;

import ee.taltech.procurementSystemBackend.model.Announcement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDto {

    private Integer announcementId;
    private Integer procurementId;
    private Integer employeeId;
    private String announcement;
    private Timestamp dateAdded;

    public AnnouncementDto(Announcement announcement) {
        this.announcementId = announcement.getAnnouncementId();
        this.procurementId = announcement.getProcurementId();
        this.employeeId = announcement.getEmployeeId();
        this.announcement = announcement.getAnnouncement();
        this.dateAdded = announcement.getDateAdded();
    }

}
