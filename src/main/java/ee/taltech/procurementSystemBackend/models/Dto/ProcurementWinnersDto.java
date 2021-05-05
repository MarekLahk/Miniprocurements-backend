package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.*;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcurementWinnersDto extends DtoBase {

    @NotNull(message = "Procurement id cannot be null")
    private Integer procurementId;
    private Integer winnerId;
    private Integer judgeId;
    private String reason;
}

