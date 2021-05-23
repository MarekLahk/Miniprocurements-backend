package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ProcurementWinnersDto", description = "DTO for Procurement Winners")
public class ProcurementWinnerDto extends DtoBase {
    @Schema(title="procurementId of ProcurementWinnersDto")
    @NotNull(message = "Procurement id cannot be null")
    private Integer procurementId;

    @Schema(title="procurementId of ProcurementWinnersDto")
    @NotNull(message = "winnerId cannot be null")
    private Integer winnerId;

    @Schema(title="procurementId of ProcurementWinnersDto")
    @NotNull(message = "judgeId cannot be null")
    private Integer judgeId;

    @Schema(title="procurementId of ProcurementWinnersDto")
    private String reason;
}

