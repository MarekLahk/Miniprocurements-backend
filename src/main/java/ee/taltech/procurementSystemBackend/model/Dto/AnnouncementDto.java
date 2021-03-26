package ee.taltech.procurementSystemBackend.model.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class AnnouncementDto {

    private Integer announcementId;
    private Integer procurementId;
    private Integer employeeId;
    private String announcement;
    private Timestamp dateAdded;

}