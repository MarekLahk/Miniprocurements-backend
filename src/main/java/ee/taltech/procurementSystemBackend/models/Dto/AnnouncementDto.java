package ee.taltech.procurementSystemBackend.models.Dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JGlobalMap

@Schema(name = "AnnouncementDto", description = "DTO for announcement")
public class AnnouncementDto extends DtoBase {
    @Schema(title="ID of person")
    private Integer id;

    @Schema(title="ID of procurement")
    @NotNull(message = "Procurement id cannot be null.")
    private Integer procurementId;

    @Schema(title="ID of employee")
    @NotNull(message = "Employee id cannot be null.")
    private Integer employeeId;

    @Schema(title="Text contents of announcement")
    @NotNull(message = "Announcement cannot be null")
    @NotBlank(message = "Announcement cannot be blank")
    private String announcement;

}
