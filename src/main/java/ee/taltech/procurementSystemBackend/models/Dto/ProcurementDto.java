package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @NotBlank(message = "Procurement name cannot be blank.")
    @NotNull(message = "Procurement name cannot be null.")
    @Schema(title="name of ProcurementDto")
    private String name;

    @Positive(message = "Amount cannot be zero or negative.")
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
    @NotNull(message = "hasContract cannot be null.")
    private Boolean hasContract;

    @Schema(title="createdById of ProcurementDto")
    @NotNull(message = "createdById cannot be null.")
    private Integer createdById;

    @NotNull(message = "Deadline cannot be null")
    @Schema(title="deadline of ProcurementDto")
    private Timestamp deadline;

    @Schema(title="status of ProcurementDto")
    @NotNull(message = "createdById cannot be null.")
    private Short status;

    @Schema(title="finishedAt of ProcurementDto")
    private Timestamp finishedAt;

    @Schema(title="completionDeadline of ProcurementDto")
    private Timestamp completionDeadline;

    @Schema(title="completionDeadlineDays of ProcurementDto")
    private Timestamp completionDeadlineDays;
}
