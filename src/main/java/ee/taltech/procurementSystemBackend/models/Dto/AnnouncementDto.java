package ee.taltech.procurementSystemBackend.models.Dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import ee.taltech.procurementSystemBackend.models.model.Announcement;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class AnnouncementDto extends DtoBase {

    private Integer announcementId;
    @NotNull(message = "Procurement id cannot be null.")
    private Integer procurementId;
    @NotNull(message = "Employee id cannot be null.")
    private Integer employeeId;
    @NotBlank(message = "Announcement cannot be blank")
    private String announcement;
    private Timestamp dateAdded;
}
