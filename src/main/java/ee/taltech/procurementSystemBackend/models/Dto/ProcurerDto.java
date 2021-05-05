package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcurerDto extends DtoBase {

    private Integer procurerId;
    @NotNull(message = "Procurement id cannot be null")
    private Integer procurementId;
    @NotNull(message = "Employee id cannot be null")
    private Integer employeeId;
}
