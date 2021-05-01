package ee.taltech.procurementSystemBackend.models.Dto;

import ee.taltech.procurementSystemBackend.models.DtoBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDto extends DtoBase  {

    private Integer procurementId;
    @NotBlank(message = "Procurement name cannot be blank.")
    private String procurementName;
    @Positive(message = "Amount cannot be zero or negative.")
    private Integer amount;
    private String description;
    private String requirements;
    private Integer contractId;
    private Integer contractSubId;
    private Boolean hasContract;
    private Timestamp timeAdded;
    private Integer addedBy;
    @NotNull(message = "Deadline cannot be null")
    private Timestamp deadline;
    private Short status;
    private Timestamp timeFinished;
}
