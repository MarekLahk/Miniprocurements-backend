package ee.taltech.procurementSystemBackend.models.Dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap
public class AnnouncementInfoDto extends DtoBase {

    private Integer id;
    @NotNull(message = "Procurement id cannot be null.")
    private Integer procurementId;
    @NotNull(message = "Employee id cannot be null.")
    private Integer employeeId;
    @NotBlank(message = "Announcement cannot be blank")
    private String announcement;

    private List<DocumentDto> documents;
}
