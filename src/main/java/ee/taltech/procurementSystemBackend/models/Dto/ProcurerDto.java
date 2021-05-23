package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "ProcurerDto", description = "DTO for Procurer")
public class ProcurerDto extends DtoBase {
    @Schema(title="id of ProcurerDto")
    private Integer id;

    @NotNull(message = "Procurement id cannot be null")
    @Schema(title="procurementId of ProcurerDto")
    private Integer procurementId;

    @NotNull(message = "Employee id cannot be null")
    @Schema(title="employeeId of ProcurerDto")
    private Integer employeeId;
}
