package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ProcurementDto", description = "DTO for Procurement")
public class ProcurementDto extends DtoBase  {
    @Schema(title="id of ProcurementDto")
    private Integer id;

    @Size(max = 50)
    @Schema(title="name of ProcurementDto")
    private String name;

    @Schema(title="amount of ProcurementDto")
    private Integer amount;

    @Schema(title="description of ProcurementDto")
    private String description;

    @Schema(title="requirements of ProcurementDto")
    private String requirements;

    @Schema(title="contractId of ProcurementDto")
    private Integer contractId;

    @Schema(title="contractSubId of ProcurementDto")
    private Integer contractSubId;

    @Schema(title="hasContract of ProcurementDto")
    private Boolean hasContract;

    @Schema(title="createdById of ProcurementDto")
    private Integer createdById;

    @Schema(title="deadline of ProcurementDto")
    private Timestamp deadline;

    @Schema(title="status of ProcurementDto")
    private Short status;

    @Schema(title="finishedAt of ProcurementDto")
    private Timestamp finishedAt;

    @Schema(title="completionDeadline of ProcurementDto")
    private Timestamp completionDeadline;

    @Schema(title="completionDeadlineDays of ProcurementDto")
    private Integer completionDeadlineDays;
}
