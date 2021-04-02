package ee.taltech.procurementSystemBackend.models.Dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import ee.taltech.procurementSystemBackend.models.model.Announcement;
import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class AnnouncementDto extends DtoBase {

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
