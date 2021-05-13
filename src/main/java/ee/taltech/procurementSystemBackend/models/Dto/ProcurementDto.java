package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
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
public class ProcurementDto extends DtoBase  {

    private Integer id;
    private String name;
    @Positive(message = "Amount cannot be zero or negative.")
    private Integer amount;
    private String description;
    private String requirements;
    private Integer contractId;
    private Integer contractSubId;
    private Boolean hasContract;
    private Integer createdById;
    private Timestamp deadline;
    private Short status;
    private Timestamp finishedAt;
    private Timestamp completionDeadline;
    private Timestamp completionDeadlineDays;
}
